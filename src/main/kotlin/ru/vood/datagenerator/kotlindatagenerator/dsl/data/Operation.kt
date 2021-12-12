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

sealed class OperationTreeNode<out T> {
    fun str(): String {
        return qw(this).replace("  ", " ")
    }

    private fun qw(op: OperationTreeNode<*>): String {
        return when (op) {
            is OperationConst<*,*> -> {
                op.value.toString()
            }
            is OperationTree<*, *, *> -> """( ${qw(op.treeLeft)} ${op.operator.strOper} ${qw(op.treeRight)} )"""
        }
    }
}

open class OperationConst<T, PRN>(
    private val v: Value<PRN>
) : OperationTreeNode<T>(), Value<PRN> {
    override val value: PRN
        get() = v.value
}

data class OperationTree<TL, TR, R>(
    val operator: Operation,
    val treeLeft: OperationTreeNode<TL>,
    val treeRight: OperationTreeNode<TR>,
) : OperationTreeNode<R>() {

}

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.plus(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.PLUS, this, increment)

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.minus(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.MINUS, this, increment)

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.div(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.DIVIDE, this, increment)

inline operator fun <reified PROP_TYPE> OperationTreeNode<PROP_TYPE>.times(increment: OperationTreeNode<PROP_TYPE>): OperationTreeNode<PROP_TYPE> =
    OperationTree(Operation.MULTIPLY, this, increment)

inline fun <reified T> OperationTreeNode<T>.graters(const: OperationTreeNode<T>): OperationTreeNode<BOOLEAN> {
    val operationTreeNode: OperationTreeNode<BOOLEAN> = when (T::class.java) {
        STRING.javaClass, NUMBER.javaClass, DATE.javaClass -> OperationTree(Operation.GRATER, this, const)
        else -> error("class ${T::class.java} not supported")
    }
    return operationTreeNode
}


/*
inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.minus(increment: MetaProperty<PROP_TYPE>): OperationLeaf<MetaProperty<PROP_TYPE>> =
    OperationLeaf(Operation.MINUS, this, increment)

inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.div(increment: MetaProperty<PROP_TYPE>): OperationLeaf<MetaProperty<PROP_TYPE>> =
    OperationLeaf(Operation.DEVIDE, this, increment)

inline operator fun <reified PROP_TYPE: DataType> MetaProperty<PROP_TYPE>.times(increment: MetaProperty<PROP_TYPE>): OperationLeaf<MetaProperty<PROP_TYPE>> =
    OperationLeaf(Operation.MULTIPLY, this, increment)
*/
