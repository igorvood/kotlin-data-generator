package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*
import java.math.BigDecimal

open class MetaProperty<out PROP_TYPE : DataType>(
    val name: FieldName,
    val type: PROP_TYPE,
    val isNotNull: Boolean = true,
    val constValue: String = ""
) : OperationConst<String>(object : Value<String> {
        override val value: String
            get() = name
    })

{
    override val value: String
        get() = name
}

fun const(v: String): MetaProperty<STRING> = MetaProperty("const", STRING, true, v)

fun const(v: Int): () -> MetaProperty<NUMBER> = { MetaProperty("const", NUMBER, true, v.toString()) }

fun const(v: Double): () -> MetaProperty<NUMBER> = { MetaProperty("const", NUMBER, true, v.toString()) }

fun const(v: BigDecimal): () -> MetaProperty<NUMBER> = { MetaProperty("const", NUMBER, true, v.toString()) }
