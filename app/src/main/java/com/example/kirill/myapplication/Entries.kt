package com.example.kirill.myapplication

class Account {
    var accountID :String = ""
    var token :String = ""
}

class User {
    companion object {
        var ID: String = "not"
        var Email: String = "not"
    }
}

class Word(id: String, value: String) {
    var Word_id: String = ""
    var Word_value: String = ""

    init {
        Word_id = id
        Word_value = value
    }
}