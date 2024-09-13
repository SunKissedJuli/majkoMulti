package com.example.majkomulti.screen.register
import com.example.majkomulti.commons.Constantas.DEFAULT_STRING

data class RegistrationState(
    val login: String,
    val password: String,
    val name: String,
    val passwordRepeat: String
) {
    companion object {
        val InitState = RegistrationState(
            login = DEFAULT_STRING,
            password = DEFAULT_STRING,
            name = DEFAULT_STRING,
            passwordRepeat = DEFAULT_STRING
        )
    }
}