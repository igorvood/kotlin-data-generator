package ru.vood.datagenerator.kotlindatagenerator.dsl

import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MetaEntBuilder<ET_ID> : Builder<MetaEntity<ET_ID>> {
    var name: EntityName = ""
    val propertyBuilder: MutableSet<MetaPropertyBuilder<*>> = mutableSetOf()
    val ck: MutableSet<MetaCheckBuilder> = mutableSetOf()
    val fk: MutableSet<MetaFk<ET_ID>> = mutableSetOf()

    override fun build(): MetaEntity<ET_ID> {
        return MetaEntity<ET_ID>(
            name = name,
            property = propertyBuilder.map { it.build() }.toSet(),
            ck = ck.map { it.build() }.toSet(),
            fk = setOf()
        )
    }

    fun string() = MetaPropertyBuilder<String>()
    fun number() = MetaPropertyBuilder<BigDecimal>()
    fun date() = MetaPropertyBuilder<LocalDateTime>()
    fun bool() = MetaPropertyBuilder<Boolean>()

    inline fun <reified Z> ref() = MetaPropertyBuilder<Z>()

    inline fun <reified Z> set() = MetaPropertyBuilder<Set<Z>>()

    val check = MetaCheckBuilder()

    inner class MetaCheckBuilder(
        var name: ConstraintName = "",
        var checkFunction: (EntityTemplate<ET_ID>) -> Boolean = { true }
    ) : Builder<MetaCheck<EntityTemplate<ET_ID>>> {
        override fun build(): MetaCheck<EntityTemplate<ET_ID>> {
            return MetaCheck(name, checkFunction)
        }

        operator fun provideDelegate(
            thisRef: Nothing?,
            property: KProperty<*>
        ): ReadOnlyProperty<Nothing?, MetaCheck<EntityTemplate<ET_ID>>> {
            name = property.name
            this@MetaEntBuilder.ck.add(this@MetaCheckBuilder)
            return ReadOnlyProperty { thisRef, property ->
                return@ReadOnlyProperty this@MetaCheckBuilder.build()
            }
        }

    }

    inner class MetaPropertyBuilder<R>(
        var name: FieldName = "",
        var function: GenerateFieldValueFunction<ET_ID, DataType<R>> = { _, _ ->
            error("Необходимо определить ф-цию в мете")
        }
    ) : Builder<MetaProperty<ET_ID, R>> {

        operator fun provideDelegate(
            thisRef: Nothing?,
            property: KProperty<*>
        ): ReadOnlyProperty<Nothing?, MetaProperty<ET_ID, R>> {
            name = property.name
            val mEntBuild: MetaEntBuilder<ET_ID> = this@MetaEntBuilder
            mEntBuild.propertyBuilder.add(this@MetaPropertyBuilder)
            return ReadOnlyProperty { thisRef, property ->
                return@ReadOnlyProperty this@MetaPropertyBuilder.build()
            }
        }

        override fun build(): MetaProperty<ET_ID, R> {
            return MetaProperty(name, function)
        }

    }
}

inline infix fun <reified R, reified ET_ID_TYPE : DataType<*>> MetaEntBuilder<ET_ID_TYPE>.MetaPropertyBuilder<R>.withFun(
    noinline f: GenerateFieldValueFunction<ET_ID_TYPE, DataType<R>>
): MetaEntBuilder<ET_ID_TYPE>.MetaPropertyBuilder<R> {
    this.function = f
    return this
}


inline infix fun <reified R, reified ET_ID_TYPE> MetaEntBuilder<ET_ID_TYPE>.MetaPropertyBuilder<R>.genVal(
    crossinline f: GenerateFieldValueFunctionDsl<ET_ID_TYPE, R>
): MetaEntBuilder<ET_ID_TYPE>.MetaPropertyBuilder<R> {
    this.function =
        { entityTemplate, parameterName ->
            object : DataType<R> {
                override fun invoke(): R = f(entityTemplate, parameterName)
            }
        }
    return this
}

inline infix fun <reified ET_ID_TYPE> MetaEntBuilder<ET_ID_TYPE>.MetaCheckBuilder.with(
    noinline f: (EntityTemplate<ET_ID_TYPE>) -> Boolean
): MetaEntBuilder<ET_ID_TYPE>.MetaCheckBuilder {
    this.checkFunction = f
    return this
}


inline fun <reified ET_ID_TYPE> entity(
    crossinline body: MetaEntBuilder<ET_ID_TYPE>.() -> Unit
): ReadOnlyProperty<Nothing?, MetaEntity<ET_ID_TYPE>> {

    return ReadOnlyProperty { thisRef, property ->
        val metaEntBuilder = MetaEntBuilder<ET_ID_TYPE>()
        metaEntBuilder.name = property.name
        metaEntBuilder.body()
        metaEntBuilder.build()
    }

}