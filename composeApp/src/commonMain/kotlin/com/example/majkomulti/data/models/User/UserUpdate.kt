package com.example.majkomulti.data.models.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class UserUpdateEmail(
    @SerialName("name") val name : String?,
    @SerialName("new_email") val newEmail : String?,
)

@Serializable
data class UserUpdateName(
    @SerialName("name") val name : String?,
)


data class UserUpdateImage(
    val name : String,
    val image : String,
)

@Serializable
data class UserUpdatePassword(
    @SerialName("name") val name : String?,
    @SerialName("new_password") val newPassword : String?,
    @SerialName("confirm_password") val confirmPassword : String?,
    @SerialName("old_password") val oldPassword : String?,
)