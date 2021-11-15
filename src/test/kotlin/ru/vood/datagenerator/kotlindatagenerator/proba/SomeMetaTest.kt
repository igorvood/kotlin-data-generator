package ru.vood.datagenerator.kotlindatagenerator.proba

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.AbstractMeta
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.STRING
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.meta.stringNew

internal class SomeMetaTest {

    @Test
    fun testMeta() {
        val someMeta = SomeMeta()
        Assertions.assertEquals(someMeta.s1, someMeta.property["s1"])
        Assertions.assertEquals(1, someMeta.property.size)

        Assertions.assertEquals(STRING, someMeta.s1.type)
        Assertions.assertTrue(someMeta.s1.isNotNull)

    }

    class SomeMeta : AbstractMeta() {
        val s1 by stringNew() //genVal { q, w -> id } //getFun()// stdStr()

    }
}