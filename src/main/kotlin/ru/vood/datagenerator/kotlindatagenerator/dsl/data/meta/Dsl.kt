package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun stringNew() = MetaPropertyBuilder<STRING>()

class MetaPropertyBuilder<R : DataType>(
    var name: FieldName = "",
    var type: R? = null,
    var isNotNull: Boolean = false,
//    var function: GenerateFieldValueFunction<ET_ID, DataType<R>> = { _, _ ->
//        error("Необходимо определить ф-цию в мете")
//    }
) : Builder<MetaProperty<R>> {

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
        return MetaProperty(name, type!!, isNotNull)
    }

}