package com.example.majkomulti.platform.permission

import com.example.majkomulti.platform.permission.delegate.PermissionDelegate
import com.example.majkomulti.platform.permission.service.PermissionsService
import com.example.majkomulti.platform.permission.service.PermissionsServiceImpl
import com.example.majkomulti.platform.permission.delegate.ReadFilesPermissionDelegate
import com.example.majkomulti.platform.permission.delegate.ManageExternalStoragePermissionDelegate
import com.example.majkomulti.platform.permission.model.Permission
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val platformPermissionsModule: Module = module {

    single<PermissionDelegate>(named(Permission.READ_EXTERNAL_STORAGE.name)) { ReadFilesPermissionDelegate() }
    single<PermissionDelegate>(named(Permission.MANAGE_EXTERNAL_STORAGE.name)) { ManageExternalStoragePermissionDelegate() }

    single<PermissionsService> {
        PermissionsServiceImpl()
    }
}

