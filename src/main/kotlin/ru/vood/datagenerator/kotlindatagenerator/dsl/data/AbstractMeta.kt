package ru.vood.datagenerator.kotlindatagenerator.dsl.data

import ru.vood.datagenerator.kotlindatagenerator.dsl.EntityName
import ru.vood.datagenerator.kotlindatagenerator.dsl.MetaCheck
import ru.vood.datagenerator.kotlindatagenerator.dsl.MetaFk
import ru.vood.datagenerator.kotlindatagenerator.dsl.MetaProperty

abstract class AbstractMeta<ID_TYPE> internal constructor() {
    lateinit var  name: EntityName
    val  property: Set<MetaProperty<ID_TYPE, *>> = mutableSetOf()
    val ck: Set<MetaCheck<EntityTemplate<ID_TYPE>>> = mutableSetOf()
    val fk: Set<MetaFk<EntityTemplate<ID_TYPE>>> = mutableSetOf()


}