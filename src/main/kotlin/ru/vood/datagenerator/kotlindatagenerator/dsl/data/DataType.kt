package ru.vood.datagenerator.kotlindatagenerator.dsl.data

sealed class DataType

object STRING : DataType()

object NUMBER : DataType()

object DATE : DataType()

object BOOLEAN : DataType()
