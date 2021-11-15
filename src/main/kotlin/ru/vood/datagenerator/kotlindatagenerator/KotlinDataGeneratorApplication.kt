package ru.vood.datagenerator.kotlindatagenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinDataGeneratorApplication

fun main(args: Array<String>) {
    runApplication<KotlinDataGeneratorApplication>(*args)
}
