package com.example.majkomulti

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform