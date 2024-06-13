package com.example.timenoter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform