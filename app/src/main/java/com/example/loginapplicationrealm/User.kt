package com.example.loginapplicationrealm

import io.realm.RealmObject

open class User (val name:String, val password:String): RealmObject(){
}