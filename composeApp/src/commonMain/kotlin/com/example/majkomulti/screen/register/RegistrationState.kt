package com.example.majkomulti.screen.register

data class RegistrationState(
    val login: String = DEFAULT_STRING,
    val password: String = DEFAULT_STRING,
    val name: String = DEFAULT_STRING,
    val passwordRepeat: String = DEFAULT_STRING
){
    companion object{
        const val DEFAULT_STRING = ""

        fun InitState() = RegistrationState()
    }
}