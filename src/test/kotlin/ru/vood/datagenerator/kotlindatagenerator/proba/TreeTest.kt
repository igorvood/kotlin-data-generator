package ru.vood.datagenerator.kotlindatagenerator.proba

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.MetaProperty


internal class TreeTest {

    @Test
    fun testTreePlus() {
        val operandLeft = MetaProperty("s1", STRING)
        val operandRight = MetaProperty("s2", STRING)
        val operationLeaf1 = operandLeft + operandRight
        val operationLeaf = OperationTree(Operation.PLUS, operandLeft, operandRight)
        Assertions.assertEquals(operationLeaf1, operationLeaf)
    }

    @Test
    fun testTreeOrder() {
        val operationLeaf2 = MetaProperty("s2", STRING) + MetaProperty("s3", NUMBER)
        val operationLeaf3 = MetaProperty("s2", STRING) + MetaProperty("s3", NUMBER)

        val operationTreeNode1 =
            MetaProperty("s2", STRING) + MetaProperty("s3", NUMBER) * MetaProperty("s2", STRING) + MetaProperty(
                "s3",
                NUMBER
            )
        val operationTreeNode = operationLeaf2 + operationLeaf3

        val operationTreeNode2 =
            (MetaProperty("s2", STRING) + MetaProperty("s3", NUMBER)) * MetaProperty("s2", STRING) + MetaProperty(
                "s3",
                NUMBER
            )

        Assertions.assertEquals("( ( s2 + s3 ) + ( s2 + s3 ) )", operationTreeNode.str())
        Assertions.assertEquals("( ( s2 + ( s3 * s2 ) ) + s3 )", operationTreeNode1.str())
        Assertions.assertEquals("( ( ( s2 + s3 ) * s2 ) + s3 )", operationTreeNode2.str())

    }


}