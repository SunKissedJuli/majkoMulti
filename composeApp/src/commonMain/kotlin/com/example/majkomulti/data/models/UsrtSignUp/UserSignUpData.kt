package com.example.majkomulti.data.models.UsrtSignUp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSignUpData(
    @SerialName("email") var email: String?,
    @SerialName("password") var password: String?,
    @SerialName("name") var name: String?
)