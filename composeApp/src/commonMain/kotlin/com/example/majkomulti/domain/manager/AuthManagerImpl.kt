package com.example.majkomulti.domain.manager

import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthManagerImpl: AuthManager, KoinComponent {

    private val settings by inject<Settings>()

    override var token: String?
        get() = if (settings.getString(TOKEN).isBlank()) null else settings.getString(TOKEN, "")
        set(value){
            settings.putString(TOKEN, value.orEmpty())
        }

    override var isPrivate: Boolean
        get() = if (settings.getBoolean(IS_PRIVATE)) true else settings.getBoolean(IS_PRIVATE, true)
        set(value){
            settings.putBoolean(IS_PRIVATE, value?:true)
        }

    companion object{
        private const val TOKEN = "TOKEN"
        private const val IS_PRIVATE = "IS_PRIVATE"
    }
}