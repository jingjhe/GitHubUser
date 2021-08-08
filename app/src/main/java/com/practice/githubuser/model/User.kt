package com.practice.githubuser.model

data class User(var login:String="",
                var id:Int=0,
                var avatar_url: String ="",
                var type: String ="",
                var blog:String="",
                var location:String="",
                val followers:Int=0,
                val following:Int=0) {

}

