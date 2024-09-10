package com.example.majkomulti.di

import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.platform.MultipartManagerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind
import com.example.majkomulti.domain.manager.AuthManagerImpl
import com.example.majkomulti.platform.MultipartManager

val managerModule = module {
    singleOf(::AuthManagerImpl) { bind<AuthManager>() }
    singleOf(::MultipartManagerImpl) { bind<MultipartManager>() }
}