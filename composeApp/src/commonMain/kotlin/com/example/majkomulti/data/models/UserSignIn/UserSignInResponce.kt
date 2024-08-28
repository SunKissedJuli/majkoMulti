package com.example.majkomulti.data.models.UserSignIn

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserSignInDataResponse (
    @SerialName("message") var message: String?,
    @SerialName("status") var status: Int?,
    @SerialName("accessToken") var accessToken: String?,
)