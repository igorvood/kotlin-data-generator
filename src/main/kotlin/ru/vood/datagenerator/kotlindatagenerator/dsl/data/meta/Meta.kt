package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.FieldName

data class MetaProperty<OUT_TYPE : DataType>(
    val name: FieldName,
    val type: OUT_TYPE,
    val isNotNull: Boolean,
//    val function: GenerateFieldValueFunction<ID_TYPE, DataType<OUT_TYPE>>
) //: (EntityTemplate<ID_TYPE>) -> OUT_TYPE
{
//    override fun invoke(p1: EntityTemplate<ID_TYPE>): OUT_TYPE = function(p1, name)()

}