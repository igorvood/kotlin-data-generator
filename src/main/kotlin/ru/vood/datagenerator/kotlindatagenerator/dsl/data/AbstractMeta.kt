package ru.vood.datagenerator.kotlindatagenerator.dsl.data

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.*
import kotlin.reflect.jvm.jvmName

abstract class AbstractMeta internal constructor() {
    val name: EntityName = this::class.jvmName
    val property: MutableMap<String, MetaProperty<*>> = mutableMapOf()
    var pk: MetaPrimaryKey<DataType>? = null

    internal object _NULL

    internal val NULL = _NULL

    internal val string = MetaPropertyBuilder(type = STRING)
    internal val date = MetaPropertyBuilder(type = DATE)
    internal val number = MetaPropertyBuilder(type = NUMBER)
    internal val boolean = MetaPropertyBuilder(type = BOOLEAN)

    internal fun primaryKey(vararg col: MetaProperty<DataType>) = MetaPrimaryKeyBuilder(cols = col)

}