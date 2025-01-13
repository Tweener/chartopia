package com.tweener.chartopia.type.line.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultCap
import androidx.compose.ui.unit.Dp
import com.tweener.chartopia.model.StrokeStyle
import com.tweener.chartopia.model.StrokeStyle.Companion.DefaultStyle
import com.tweener.chartopia.type.line.LineChartDefaults
import com.tweener.chartopia.type.line.model.LineType.Companion.DefaultType

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

data class Line(
    val id: String,
    val plottedPoints: List<PlottedPoint>,
    val color: Color,
    val fillColorAlpha: Float = LineChartDefaults.LineGradientFillAlpha,
    val type: LineType = DefaultType,
    val strokeWidth: Dp = LineChartDefaults.LineStrokeWidth,
    val strokeCap: StrokeCap = DefaultCap,
    val strokeStyle: StrokeStyle = DefaultStyle,
)
