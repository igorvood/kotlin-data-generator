package ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta

import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*

open class MetaProperty<out PROP_TYPE : DataType>(
    val name: FieldName,
    val type: PROP_TYPE,
    val isNotNull: Boolean = true,
) : OperationConst<String>(object : Value<String> {
    override val value: String
        get() = name
}) {

}

//inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.plus(increment: MetaProperty<PROP_TYPE>): OperationTree<MetaProperty<PROP_TYPE>> =
//    OperationTree(Operation.PLUS, this, increment)
//
//inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.minus(increment: MetaProperty<PROP_TYPE>): OperationTree<MetaProperty<PROP_TYPE>> =
//    OperationTree(Operation.MINUS, this, increment)
//
//inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.div(increment: MetaProperty<PROP_TYPE>): OperationTree<MetaProperty<PROP_TYPE>> =
//    OperationTree(Operation.DIVIDE, this, increment)
//
//inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.times(increment: MetaProperty<PROP_TYPE>): OperationTree<MetaProperty<PROP_TYPE>> =
//    OperationTree(Operation.MULTIPLY, this, increment)

open class MetaPrimaryKey<out PROP_TYPE : DataType>(
    val name: ConstraintName,
    vararg col: MetaProperty<PROP_TYPE>,
) {
    val cols = col.asList()
}


open class MetaCheck(
    val name: ConstraintName,
    val checkFun: OperationTree<BOOLEAN>
) {

}
