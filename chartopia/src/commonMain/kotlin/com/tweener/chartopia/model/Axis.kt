package com.tweener.chartopia.model

import com.tweener.chartopia.model.StrokeStyle.Companion.DefaultStyle

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class Axis(
    val values: List<AxisValue>,
    val axisStrokeStyle: StrokeStyle = DefaultStyle,
    val gridStrokeStyle: StrokeStyle = DefaultStyle,
)
