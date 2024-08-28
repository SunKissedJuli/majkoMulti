package com.example.majkomulti.di

import com.example.majkomulti.domain.manager.AuthManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind
import com.example.majkomulti.domain.manager.AuthManagerImpl

val managerModule = module {
    singleOf(::AuthManagerImpl) { bind<AuthManager>() }
}