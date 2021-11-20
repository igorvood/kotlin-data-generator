package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.ConstraintName
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.FieldName

open class MetaProperty<out PROP_TYPE : DataType>(
    val name: FieldName,
    val type: PROP_TYPE,
    val isNotNull: Boolean,
//    val function: GenerateFieldValueFunction<ID_TYPE, DataType<PROP_TYPE>>
) //: (EntityTemplate<ID_TYPE>) -> PROP_TYPE
{
//    override fun invoke(p1: EntityTemplate<ID_TYPE>): PROP_TYPE = function(p1, name)()

}

open class MetaPrimaryKey<out PROP_TYPE : DataType>(
    val name: ConstraintName,
    vararg col: MetaProperty<PROP_TYPE>,
    ){
    val cols = col.asList()
}


open class MetaCheck(
    val name: ConstraintName,
    val checkFun: CheckScope.()-> Boolean
){

}
