package ru.vood.datagenerator.kotlindatagenerator.proba

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.vood.datagenerator.kotlindatagenerator.dsl.data.*
import java.lang.IllegalStateException

internal class SomeMeta2PkTest {

    @Test
    fun testMeta() {
        val assertThrows = Assertions.assertThrows(IllegalStateException::class.java) { SomeMeta() }
        Assertions.assertEquals("primary key pk_1 for class class ru.vood.datagenerator.kotlindatagenerator.proba.SomeMeta2PkTest\$SomeMeta all ready added ", assertThrows.message)
    }

    @Test
    fun testMeta2() {
        val assertThrows = Assertions.assertThrows(IllegalStateException::class.java) { SomeMeta2() }
        Assertions.assertEquals("in primary key pk_1 for class class ru.vood.datagenerator.kotlindatagenerator.proba.SomeMeta2PkTest\$SomeMeta2 use nullable columns => n1, n2 ", assertThrows.message)
    }

    internal open class SomeMeta : AbstractMeta() {
        val s1 by string NOT NULL
        val n1 by number  NOT NULL
        val pk_1 by primaryKey(s1, n1)
        val pk_2 by primaryKey(n1, s1)
    }

    internal open class SomeMeta2 : AbstractMeta() {
        val s1 by string NOT NULL
        val n1 by number
        val n2 by number
        val pk_1 by primaryKey(s1, n1, n2)
    }

}