package com.example.majkomulti.platform.permission.delegate

import com.example.majkomulti.di.KoinInjector
import com.example.majkomulti.platform.permission.delegate.PermissionDelegate
import com.example.majkomulti.platform.permission.model.PermissionState
import javax.naming.Context

actual class ReadFilesPermissionDelegate : PermissionDelegate {

    private val context by KoinInjector.koin.inject<Context>()

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