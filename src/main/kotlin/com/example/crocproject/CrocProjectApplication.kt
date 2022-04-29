package com.example.crocproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class CrocProjectApplication

fun main(args: Array<String>) {
    runApplication<CrocProjectApplication>(*args)
}
