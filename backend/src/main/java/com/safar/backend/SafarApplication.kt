package com.safar.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SafarApplication

fun main(args: Array<String>) {
    runApplication<SafarApplication>(*args)
}
