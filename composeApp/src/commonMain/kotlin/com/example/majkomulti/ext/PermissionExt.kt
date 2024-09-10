package com.example.majkomulti.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.majkomulti.platform.permission.model.PermissionState

@Composable
fun State<PermissionState>.granted(granted: () -> Unit): State<PermissionState> {
    if (this.value == PermissionState.GRANTED) {
        granted()
    }
    return this
}

@Composable
fun State<PermissionState>.denied(denied: () -> Unit): State<PermissionState> {
    if (this.value == PermissionState.DENIED) {
        denied()
    }
    return this
}