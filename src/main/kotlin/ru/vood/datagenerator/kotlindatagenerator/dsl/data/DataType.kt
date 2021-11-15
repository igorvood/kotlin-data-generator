package ru.vood.datagenerator.kotlindatagenerator.dsl.data

sealed interface DataType

object STRING: DataType

object NUMBER: DataType

object DATE: DataType

object BOOLEAN: DataType