package com.tweener.charts.type.line.model

import kotlin.jvm.JvmInline

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

data class PointCoordinates<X, Y>(
    val x: PointX<X>,
    val y: PointY<Y>,
)

@JvmInline
value class PointX<X>(val value: X)

@JvmInline
value class PointY<Y>(val value: Y)
