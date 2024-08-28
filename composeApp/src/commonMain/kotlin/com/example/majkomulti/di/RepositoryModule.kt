package com.example.majkomulti.di

import com.example.majkomulti.data.repository.UserRepositoryImpl
import com.example.majkomulti.data.repository.TaskRepositoryImpl
import com.example.majkomulti.data.repository.InfoRepositoryImpl
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.domain.repository.TaskRepository
import com.example.majkomulti.domain.repository.UserRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind

val repositoryModule = module {
    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
    factoryOf(::InfoRepositoryImpl) { bind<InfoRepository>() }
}

