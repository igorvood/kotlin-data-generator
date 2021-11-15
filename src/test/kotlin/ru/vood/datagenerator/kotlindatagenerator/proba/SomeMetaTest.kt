package ru.vood.datagenerator.kotlindatagenerator.proba

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*

internal class SomeMetaTest {

    @Test
    fun testMeta() {
        val someMeta = SomeMeta()
        Assertions.assertEquals(someMeta.s1, someMeta.property["s1"])
        Assertions.assertEquals(4, someMeta.property.size)
        Assertions.assertEquals(STRING, someMeta.s1.type)
        Assertions.assertTrue(someMeta.s1.isNotNull)
    }

    @Test
    fun singleton(){
        val someMeta =Singleton
        Assertions.assertEquals(someMeta.s1, someMeta.property["s1"])
        Assertions.assertEquals(someMeta.n1, someMeta.property["n1"])
        Assertions.assertEquals(someMeta.d1, someMeta.property["d1"])
        Assertions.assertEquals(someMeta.b1, someMeta.property["b1"])
        Assertions.assertEquals(4, someMeta.property.size)
        Assertions.assertEquals(STRING, someMeta.s1.type)
        Assertions.assertEquals(NUMBER, someMeta.n1.type)
        Assertions.assertEquals(DATE, someMeta.d1.type)
        Assertions.assertEquals(BOOLEAN, someMeta.b1.type)
        Assertions.assertTrue(someMeta.s1.isNotNull)
        Assertions.assertTrue(someMeta.n1.isNotNull)
        Assertions.assertFalse(someMeta.d1.isNotNull)
        Assertions.assertFalse(someMeta.b1.isNotNull)
        // constraint
        Assertions.assertNotNull(someMeta.pk)
        Assertions.assertEquals(someMeta.pk!!.cols, listOf(someMeta.s1, someMeta.n1))

    }

    internal open class SomeMeta : AbstractMeta() {
        val s1 by string NOT NULL //genVal { q, w -> id } //getFun()// stdStr()
        val n1 by number NOT NULL//genVal { q, w -> id } //getFun()// stdStr()
        val d1 by date //genVal { q, w -> id } //getFun()// stdStr()
        val b1 by boolean //genVal { q, w -> id } //getFun()// stdStr()

        val pk_1 by primaryKey(s1, n1)
    }

    object Singleton:SomeMeta()
}