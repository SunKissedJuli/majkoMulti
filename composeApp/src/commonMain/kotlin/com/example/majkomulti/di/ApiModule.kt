package com.example.majkomulti.di

import com.example.majkomulti.data.api.MajkoApi
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module

internal val apiModule = module {
    factory { get<Ktorfit>().create<MajkoApi>() }
}