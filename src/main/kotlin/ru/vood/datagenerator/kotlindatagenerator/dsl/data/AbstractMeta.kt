package ru.vood.datagenerator.kotlindatagenerator.dsl.data

import ru.vood.datagenerator.kotlindatagenerator.dsl.EntityName
import ru.vood.datagenerator.kotlindatagenerator.dsl.FieldName
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.MetaProperty
import kotlin.reflect.jvm.jvmName

abstract class AbstractMeta internal constructor() {
    val name: EntityName = this::class.jvmName
    val property: MutableMap<String, MetaProperty<*>> = mutableMapOf()
//    val ck: MutableSet<MetaCheck<EntityTemplate<*>>> = mutableSetOf()
//    val fk: MutableSet<MetaFk<EntityTemplate<*>>> = mutableSetOf()


    /* fun string() = PropBuilder<String>()
     fun number() = PropBuilder<BigDecimal>()
     fun date() = PropBuilder<LocalDateTime>()
     fun bool() = PropBuilder<Boolean>()
     inline fun <reified Z> ref() = PropBuilder<Z>()
     inline fun <reified Z> set() = PropBuilder<Set<Z>>()*/

    inner class PropBuilder<R>(
        var name: FieldName = "",
        val type: DataType<R>,
        val isNotNull: Boolean,

//        var function: GenerateFieldValueFunction< DataType<R>> = { _, _ ->
//            error("Необходимо определить ф-цию в мете")
//        }
    ) : Builder<MetaProperty<R>>
}