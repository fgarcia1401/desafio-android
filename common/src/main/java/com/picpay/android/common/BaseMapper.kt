package com.picpay.android.common

abstract class BaseMapper<IN, OUT> {

    abstract fun transform(entity: IN): OUT

    fun transform(entities: List<IN>): List<OUT> = entities.map(::transform)

}