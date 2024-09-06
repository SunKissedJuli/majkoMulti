package com.example.majkomulti.platform.permission.delegate

import com.example.majkomulti.platform.permission.delegate.PermissionDelegate
import com.example.majkomulti.platform.permission.model.PermissionState

actual class ManageExternalStoragePermissionDelegate : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        TODO("Not yet implemented")
    }

    override suspend fun providePermission() {
        TODO("Not yet implemented")
    }

    override fun openSettingPage() {
        TODO("Not yet implemented")
    }
}