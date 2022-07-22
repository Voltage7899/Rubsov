package com.example.rubsov

class UserModel(val name:String?="",val phone:String?="",val pass:String?="") {
    companion object{
        var currentUser:UserModel?=null
    }
}