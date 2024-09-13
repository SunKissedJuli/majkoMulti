package com.example.majkomulti.platform.permission.delegate

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.example.majkomulti.di.KoinInjector
import com.example.majkomulti.platform.checkReadAllFilesPermission
import com.example.majkomulti.platform.permission.checkPermissions
import com.example.majkomulti.platform.permission.model.Permission
import com.example.majkomulti.platform.permission.model.PermissionState
import com.example.majkomulti.platform.permission.openActionSettingsPage
import com.example.majkomulti.platform.permission.providePermissions
import com.example.majkomulti.platform.permission.util.PermissionRequestException

actual class ManageExternalStoragePermissionDelegate : PermissionDelegate {

    private val context by KoinInjector.koin.inject<Context>()

    private fun activity(): Activity {
        val temp: Activity by KoinInjector.koin.inject()
        return temp
    }

    override fun getPermissionState(): PermissionState {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (checkReadAllFilesPermission() == true) PermissionState.GRANTED else PermissionState.DENIED
        } else checkPermissions(context, activity(), permission)
    }

    override suspend fun providePermission() {
        println("Я тут")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            println("и тут")

            openSettingPage()
        } else {
            println("или тут")
            activity().providePermissions(permission) {
                throw PermissionRequestException(Permission.MANAGE_EXTERNAL_STORAGE.name)
            }
        }
    }

    override fun openSettingPage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.openActionSettingsPage(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        }
    }
}

private val permission: List<String> = listOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)
