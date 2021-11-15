package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.AbstractMeta
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.AbstractMeta._NULL
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.Builder
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.FieldName
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MetaPropertyBuilder<R : DataType> internal constructor(
    var name: FieldName = "",
    val type: R,
    var isNotNull: Boolean = false,
) : Builder<MetaProperty<R>> {

    internal infix fun NOT(n: _NULL): MetaPropertyBuilder<R> {
        isNotNull = true
        return this
    }

    operator fun provideDelegate(
        thisRef: AbstractMeta,
        property: KProperty<*>
    ): ReadOnlyProperty<AbstractMeta, MetaProperty<R>> {
        this@MetaPropertyBuilder.name = property.name
        val metaProp = this@MetaPropertyBuilder.build()
        if (thisRef.property.putIfAbsent(property.name, metaProp) != null) {
            error("property ${property.name} for class ${thisRef::class.java} all ready added")
        }
        return ReadOnlyProperty { thisRef, property ->
            return@ReadOnlyProperty metaProp
        }
    }

    fun build(): MetaProperty<R> {
        return MetaProperty(name, type, isNotNull)
    }

}