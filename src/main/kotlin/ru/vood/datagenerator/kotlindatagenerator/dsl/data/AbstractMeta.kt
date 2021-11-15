package ru.vood.datagenerator.kotlindatagenerator.dsl.data

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.MetaPrimaryKey
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.MetaPrimaryKeyBuilder
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.MetaProperty
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.MetaPropertyBuilder
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

//    val ck: MutableSet<MetaCheck<EntityTemplate<*>>> = mutableSetOf()
//    val fk: MutableSet<MetaFk<EntityTemplate<*>>> = mutableSetOf()
 val d =listOf("asda","asdasd")

    /* fun string() = PropBuilder<String>()
     fun number() = PropBuilder<BigDecimal>()
     fun date() = PropBuilder<LocalDateTime>()
     fun bool() = PropBuilder<Boolean>()
     inline fun <reified Z> ref() = PropBuilder<Z>()
     inline fun <reified Z> set() = PropBuilder<Set<Z>>()*/

    /*   inner class PropBuilder<R>(
           var name: FieldName = "",
           val type: DataType<R>,
           val isNotNull: Boolean,

   //        var function: GenerateFieldValueFunction< DataType<R>> = { _, _ ->
   //            error("Необходимо определить ф-цию в мете")
   //        }
       ) : Builder<MetaProperty<R>>*/
}