package com.practice.githubuser.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.githubuser.model.User
import com.practice.githubuser.repository.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

    private var liveDataMeUser: MutableLiveData<User> = MutableLiveData()
    private var liveDataUsersList: MutableLiveData<ArrayList<User>> = MutableLiveData()

    private var gitHubRepo = GitHubRepository


    fun getLiveDataMeUser(): MutableLiveData<User> {
        return liveDataMeUser
    }

    fun getLiveDataUsersList(): MutableLiveData<ArrayList<User>> {
        return liveDataUsersList
    }

    fun fetchMeData() {
        gitHubRepo.getRemoteMeUserObserver()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                liveDataMeUser.postValue(it)
            }
    }


    fun fetchUsersListData() {

        gitHubRepo.getRemoteUsersListObserver()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                liveDataUsersList.postValue(ArrayList(it))
            }

    }
}