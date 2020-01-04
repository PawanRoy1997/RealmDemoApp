package com.example.loginapplicationrealm

import io.realm.RealmObject

open class User (): RealmObject(){
    lateinit var name:String
    lateinit var password:String
}