package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.FieldName
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType

data class MetaProperty<OUT_TYPE>(
    val name: FieldName,
    val type: DataType<OUT_TYPE>,
    val isNotNull: Boolean,
//    val function: GenerateFieldValueFunction<ID_TYPE, DataType<OUT_TYPE>>
) //: (EntityTemplate<ID_TYPE>) -> OUT_TYPE
{
//    override fun invoke(p1: EntityTemplate<ID_TYPE>): OUT_TYPE = function(p1, name)()

}