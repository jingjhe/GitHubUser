package com.practice.githubuser.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.githubuser.model.User
import com.practice.githubuser.repository.GitHubRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

    private var liveDataMeUser: MutableLiveData<User> = MutableLiveData()
    private var liveDataUsersList: MutableLiveData<ArrayList<User>> = MutableLiveData()
    private var liveDataUser: MutableLiveData<User> = MutableLiveData()
    private var liveDataMessage:MutableLiveData<String> = MutableLiveData()


    private var gitHubRepo = GitHubRepository


    fun getLiveDataMeUser(): MutableLiveData<User> {
        return liveDataMeUser
    }

    fun getLiveDataUsersList(): MutableLiveData<ArrayList<User>> {
        return liveDataUsersList
    }

    fun getLiveDataUser(): MutableLiveData<User> {
        return liveDataUser
    }

    fun getLiveDataMessage(): MutableLiveData<String>{
        return liveDataMessage
    }
    fun fetchMeData() {
        gitHubRepo.getRemoteMeUserObserver()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(it: User) {
                    liveDataMeUser.postValue(it)
                }

                override fun onError(e: Throwable) {
                    liveDataMessage.postValue(e.message)
                }

                override fun onComplete() {
                }

            })

    }


    fun fetchUsersListData() {

        gitHubRepo.getRemoteUsersListObserver()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<User>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(it: List<User>) {
                    liveDataUsersList.postValue(ArrayList(it))
                }

                override fun onError(e: Throwable) {
                    liveDataMessage.postValue(e.message)
                }

                override fun onComplete() {
                }


            })

    }

    fun fetchUserDataByLogin(login: String) {
        gitHubRepo.getRemoteUserByLoginObserver(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(it: User) {
                    liveDataUser.postValue(it)
                }

                override fun onError(e: Throwable) {
                    liveDataMessage.postValue(e.message)
                }

                override fun onComplete() {
                }

            })

    }

}