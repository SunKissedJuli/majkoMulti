package com.example.majkomulti.commons

fun String.toToken(): String{
    return "Bearer $this"
}