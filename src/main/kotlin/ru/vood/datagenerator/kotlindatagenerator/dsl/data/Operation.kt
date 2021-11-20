package ru.vood.datagenerator.kotlindatagenerator.dsl.data

/*interface OperationRun<T> {
    val op: (T, Operation, T) -> T
}

object Print : OperationRun<String> {
    override val op: (String, Operation, String) -> String
        get() = { s1, op, s2 -> """$s1 ${op.strOper} $s2""" }
}*/


enum class Operation(val strOper: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    GRATER(">"),
}

interface Value<T> {
    val value: T
}

sealed interface OperationTreeNode<out T> {
    val value: T
    fun str(): String {
        return qw(this).replace("  ", " ")
    }

    private fun qw(op: OperationTreeNode<*>): String {
        return when (op) {
            is OperationConst -> op.value.toString()
            is OperationTree<*,*,*> -> """( ${qw(op.treeLeft)} ${op.operator.strOper} ${qw(op.treeRight)} )"""
        }
    }
}

abstract class OperationConst<T>(
    private val v: Value<T>
) : OperationTreeNode<T> {
    override val value: T
        get() = v.value
}

data class OperationTree<TL, TR, R>(
    val operator: Operation,
    val treeLeft: OperationTreeNode<TL>,
    val treeRight: OperationTreeNode<TR>,
) : OperationTreeNode<R> {
    override val value: R
        get() = TODO("Not yet implemented")
}

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.plus(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.PLUS, this, increment)

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.minus(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.MINUS, this, increment)

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.div(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.DIVIDE, this, increment)

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.times(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.MULTIPLY, this, increment)

inline infix fun<reified PROP_TYPE: NUMBER> OperationTreeNode<PROP_TYPE>.grater(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<BOOLEAN> =
    OperationTree(Operation.GRATER, this, increment)


/*
inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.minus(increment: MetaProperty<PROP_TYPE>): OperationLeaf<MetaProperty<PROP_TYPE>> =
    OperationLeaf(Operation.MINUS, this, increment)

inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.div(increment: MetaProperty<PROP_TYPE>): OperationLeaf<MetaProperty<PROP_TYPE>> =
    OperationLeaf(Operation.DEVIDE, this, increment)

inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.times(increment: MetaProperty<PROP_TYPE>): OperationLeaf<MetaProperty<PROP_TYPE>> =
    OperationLeaf(Operation.MULTIPLY, this, increment)
*/
