package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.AbstractMeta._NULL
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

    private fun build(): MetaProperty<R> {
        return MetaProperty(name, type, isNotNull)
    }

}

class MetaPrimaryKeyBuilder internal constructor(
    var name: ConstraintName = "",
    var cols: Array<out MetaProperty<DataType>>,
//    var cols: Array<MetaProperty<DataType>>,
) : Builder<MetaPrimaryKey<DataType>> {

    operator fun provideDelegate(
        thisRef: AbstractMeta,
        property: KProperty<*>
    ): ReadOnlyProperty<AbstractMeta, MetaPrimaryKey<DataType>> {

        this@MetaPrimaryKeyBuilder.name = property.name
        val metaPk = this@MetaPrimaryKeyBuilder.build()
        val nullableCols = cols.filter { !it.isNotNull }.joinToString(", ") { it.name }
        if (nullableCols!="") error("in primary key $name for class ${thisRef::class.java} use nullable columns => $nullableCols ")


        if (thisRef.pk == null) {
            thisRef.pk = metaPk
        } else {
            error("primary key ${thisRef.pk!!.name} for class ${thisRef::class.java} all ready added ")
        }
        return ReadOnlyProperty { thisRef, property ->
            return@ReadOnlyProperty metaPk
        }
    }

    private fun build(): MetaPrimaryKey<DataType> {
        return MetaPrimaryKey(name = name, col = cols)
    }


}