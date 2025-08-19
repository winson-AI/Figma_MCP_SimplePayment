package com.example.simplepayment

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform