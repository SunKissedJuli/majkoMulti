package com.example.majkomulti.platform.permission.delegate

import com.example.majkomulti.platform.permission.model.PermissionState

internal interface PermissionDelegate {
    fun getPermissionState(): PermissionState
    suspend fun providePermission()
    fun openSettingPage()
}