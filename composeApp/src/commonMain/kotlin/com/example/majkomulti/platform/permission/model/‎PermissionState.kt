package com.example.majkomulti.platform.permission.model

enum class PermissionState {
    NOT_DETERMINED,
    GRANTED,
    DENIED;

 fun notGranted(): Boolean {
    return this != GRANTED
}

    fun granted() : Boolean {
        return this == GRANTED
    }
}