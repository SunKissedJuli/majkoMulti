package com.example.majkomulti.screen.login

import com.example.majkomulti.commons.Constantas.DEFAULT_STRING

data class LoginState(
    val login: String,
    val password: String
) {
    companion object {

        val InitState = LoginState(
            login = DEFAULT_STRING,
            password = DEFAULT_STRING
        )
    }
}
