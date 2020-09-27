package com.example.foodapp.models

data class UserModel(
    var id: String = "",
    var phone: String = "",
    var email: String = "",
    var fullname: String = "",
    var permission: String = "",
    var date: String = "",
    var mailing: Boolean = false
)