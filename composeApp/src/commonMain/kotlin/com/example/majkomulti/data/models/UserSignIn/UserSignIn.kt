package com.example.majkomulti.data.models.UserSignIn
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserSignInData(
    @SerialName("email") var email: String?,
    @SerialName("password") var password: String?,
)