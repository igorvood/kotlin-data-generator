package ru.vood.datagenerator.kotlindatagenerator.dsl

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.EntityTemplate

data class MetaEntity<ID_TYPE>
//        where T: EntityTemplate<Q>
    (
    val name: EntityName,
    val property: Set<MetaProperty<ID_TYPE, *>>,
    val ck: Set<MetaCheck<EntityTemplate<ID_TYPE>>> = setOf(),
    val fk: Set<MetaFk<EntityTemplate<ID_TYPE>>> = setOf(),
)

@Deprecated("asda")
data class MetaProperty<ID_TYPE, OUT_TYPE>(
    val name: FieldName,
    val function: GenerateFieldValueFunction<ID_TYPE, DataType<OUT_TYPE>>
) : (EntityTemplate<ID_TYPE>) -> OUT_TYPE
//,Comparable<MetaProperty<ID_TYPE, OUT_TYPE>>/* Comparator<MetaProperty<ID_TYPE, OUT_TYPE>>*/
{
    override fun invoke(p1: EntityTemplate<ID_TYPE>): OUT_TYPE = function(p1, name)()
   /* override fun compareTo(other: MetaProperty<ID_TYPE, OUT_TYPE>): Int {
        return this.name.compareTo(other.name)
    }*/

}

data class MetaCheck<T>(
    val name: ConstraintName,
    val checkFunction: (T) -> Boolean
)

data class MetaFk<T>(
    val name: ConstraintName
)
