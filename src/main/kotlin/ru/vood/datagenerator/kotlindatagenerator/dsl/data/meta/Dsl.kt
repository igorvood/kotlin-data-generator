package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.FieldName
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.AbstractMeta
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.Builder
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MetaPropertyBuilder<R>(
    var name: FieldName = "",
    var type: DataType<R> = object : DataType<R> {
        override fun invoke(): R {
            TODO("Not yet implemented")
        }
    },
    var isNotNull: Boolean = false,
//    var function: GenerateFieldValueFunction<ET_ID, DataType<R>> = { _, _ ->
//        error("Необходимо определить ф-цию в мете")
//    }
) : Builder<MetaProperty<R>> {

    operator fun provideDelegate(
        thisRef: AbstractMeta,
        property: KProperty<*>
    ): ReadOnlyProperty<AbstractMeta, MetaProperty<R>> {
        name = property.name
//        val mEntBuild: MetaEntBuilder<ET_ID> = this@MetaEntBuilder
//        mEntBuild.propertyBuilder.add(this@MetaPropertyBuilder)
        return ReadOnlyProperty { thisRef, property ->
            this@MetaPropertyBuilder.name = property.name
            val metaProp = this@MetaPropertyBuilder.build()
            if (thisRef.property.putIfAbsent(property.name, metaProp)?.let { error("") }==null){
                error("property ${property.name} for class ${thisRef::class.java} all ready added")
            }
//            val putIfAbsent: MetaProperty<*> = thisRef.property.putIfAbsent(property.name, metaProp)?.let { error("") }

//            thisRef.property[property.name] = metaProp
            return@ReadOnlyProperty metaProp
        }
    }

    fun build(): MetaProperty<R> {
        return MetaProperty(name, type, isNotNull)
    }

}