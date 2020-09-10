package com.example.foodapp.database

import com.example.foodapp.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

const val APP_VERSION = "0.1"

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var CURRENT_UID: String
lateinit var USER: UserModel

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_FULLNAME = "fullname"
const val CHILD_EMAIL = "email"
const val CHILD_PHONE = "phone"