package com.example.majkomulti.platform.permission.delegate

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import com.example.majkomulti.di.KoinInjector
import com.example.majkomulti.platform.permission.checkPermissions
import com.example.majkomulti.platform.permission.model.Permission
import com.example.majkomulti.platform.permission.model.PermissionState
import com.example.majkomulti.platform.permission.openAppSettingsPage
import com.example.majkomulti.platform.permission.providePermissions
import com.example.majkomulti.platform.permission.util.PermissionRequestException

actual class ReadFilesPermissionDelegate : PermissionDelegate {

    private val context by KoinInjector.koin.inject<Context>()

    private fun activity(): Activity {
        val temp: Activity by KoinInjector.koin.inject()
        return temp
    }

    override fun getPermissionState(): PermissionState {
        return checkPermissions(context, activity(), permission)
    }

    override suspend fun providePermission() {
        activity().providePermissions(permission) {
            throw PermissionRequestException(Permission.READ_EXTERNAL_STORAGE.name)
        }
    }

    override fun openSettingPage() {
        context.openAppSettingsPage(Permission.READ_EXTERNAL_STORAGE)
    }
}

private val permission: List<String> =
    listOf(
        if (Build.VERSION.SDK_INT < 33) {
            Manifest.permission.READ_EXTERNAL_STORAGE
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        } else Manifest.permission.READ_MEDIA_IMAGES,
    )