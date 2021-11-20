package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.ConstraintName
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.DataType

open class MetaPrimaryKey<out PROP_TYPE : DataType>(
    val name: ConstraintName,
    vararg col: MetaProperty<PROP_TYPE>,
) {
    val cols = col.asList()
}


open class MetaCheck(
    val name: ConstraintName,
//    val checkFun: OperationTree<BOOLEAN>
) {

}
