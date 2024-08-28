package com.example.majkomulti.screen.login

data class LoginState(
    val login: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING
){
    companion object{
        const val DEFAULT_STRING = ""

        fun InitState() = LoginState()
    }
}

