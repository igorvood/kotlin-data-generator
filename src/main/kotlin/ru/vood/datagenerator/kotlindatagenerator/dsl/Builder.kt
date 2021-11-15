package ru.vood.datagenerator.kotlindatagenerator.dsl

interface Builder<T> {
    fun build(): T
}