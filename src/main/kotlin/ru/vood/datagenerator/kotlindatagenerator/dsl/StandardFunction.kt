package ru.vood.datagenerator.kotlindatagenerator.dsl

import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.math.abs

object StandardFunction {

    inline fun <reified T> hash(et: EntityTemplate<T>, pn: String): Int =
        abs(et.id().hashCode() + pn.hashCode())


    val genBool: (EntityTemplate<String>, String) -> Boolean =
        { et, pn -> "${et.id}_$pn".hashCode() % 2 != 0 }
    val genStr: (EntityTemplate<String>, String) -> String = { et, pn -> "${et.id}_$pn" }
    val genNum: (EntityTemplate<String>, String) -> BigDecimal =
        { et, pn -> BigDecimal(abs(et.id.hashCode() + pn.hashCode())) }
    val dateFunction: (EntityTemplate<String>, String) -> LocalDateTime = { et, pn ->
        LocalDateTime
            .of(1970, 1, 1, 12, 12)
            .plusSeconds(et.id.hashCode().toLong() + pn.hashCode().toLong())
    }


    inline fun <reified T> stdStr(): (EntityTemplate<T>, String) -> String = { et, pn -> "${et.id()}_$pn" }
    inline fun <reified T> stdBool(): (EntityTemplate<T>, String) -> Boolean =
        { et, pn -> "${et.id()}_$pn".hashCode() % 2 != 0 }

    inline fun <reified T> stdNum(): (EntityTemplate<T>, String) -> BigDecimal =
        { et, pn -> BigDecimal(abs(et.id.hashCode() + pn.hashCode())) }

    inline fun <reified T> stdDate(): (EntityTemplate<T>, String) -> LocalDateTime = { et, pn ->
        LocalDateTime
            .of(1970, 1, 1, 12, 12)
            .plusSeconds(et.id.hashCode().toLong() + pn.hashCode().toLong())
    }

    inline fun <reified T, reified R> dictVal(dict: List<R>): (EntityTemplate<T>, String) -> R = { et, pn ->
        dict[hash(et, pn) % dict.size]
    }

    inline fun <reified T> rangeVal(min: BigDecimal, max: BigDecimal): (EntityTemplate<T>, String) -> BigDecimal =
        { et, pn ->
            min + (hash(et, pn) % (max - min).toInt()).toBigDecimal()
        }

    inline fun <reified ID_TYPE, reified T> genEntityData(
        meta: MetaEntity<ID_TYPE>,
        idGenerator: () -> Set<ID_TYPE>,
        init: (ID_TYPE, MetaEntity<ID_TYPE>) -> T,
    ): Set<T>
            where T : EntityTemplate<ID_TYPE> {
        return idGenerator().map { init(it, meta) }.toSet()
    }

    inline fun <reified ID_TYPE, reified T> genOneEntityData(
        meta: MetaEntity<ID_TYPE>,
        idGenerator: () -> ID_TYPE,
        init: (ID_TYPE, MetaEntity<ID_TYPE>) -> T,
    ): T
            where T : EntityTemplate<ID_TYPE> {
        return init(idGenerator(), meta)
    }

}