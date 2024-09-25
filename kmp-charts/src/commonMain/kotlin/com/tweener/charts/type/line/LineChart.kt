package com.tweener.charts.type.line

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tweener.charts._internal.kotlinextension.computeValueMaxHeight
import com.tweener.charts._internal.kotlinextension.computeValueMaxWidth
import com.tweener.charts.drawGrid
import com.tweener.charts.model.Axis
import com.tweener.charts.model.ChartColors
import com.tweener.charts.model.ChartDefaults
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.GridOffsets
import com.tweener.charts.model.GridVisibility
import com.tweener.charts.type.line.model.Line
import com.tweener.charts.type.line.model.drawLines

@Composable
fun LineChart(
    lines: List<Line>,
    xAxis: Axis,
    yAxis: Axis,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    gridVisibility: GridVisibility = LineChartDefaults.gridVisibility(),
    colors: LineChartColors = LineChartDefaults.chartColors(),
    sizes: LineChartSizes = LineChartDefaults.chartSizes(),
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier.fillMaxSize(),
    ) {
        // Compute grid offsets (4 corners)
        val xAxisValueWidth = xAxis.computeValueMaxWidth(textMeasurer, textStyle = textStyle)
        val xAxisValueHeight = xAxis.computeValueMaxHeight(textMeasurer, textStyle = textStyle)
        val yAxisValueWidth = yAxis.computeValueMaxWidth(textMeasurer, textStyle = textStyle)
        val yAxisValueHeight = yAxis.computeValueMaxHeight(textMeasurer = textMeasurer, textStyle = textStyle)

        val startOffset = yAxisValueWidth + sizes.axisValuesPadding().toPx()
        val endOffset = size.width - xAxisValueWidth / 2
        val topOffset = yAxisValueHeight / 2
        val bottomOffset = size.height - xAxisValueHeight - sizes.axisValuesPadding().toPx()

        val gridOffsets = GridOffsets(
            topStartCorner = Offset(startOffset, topOffset),
            bottomStartCorner = Offset(startOffset, bottomOffset),
            topEndCorner = Offset(endOffset, topOffset),
            bottomEndCorner = Offset(endOffset, bottomOffset),
        )

        drawGrid(
            textMeasurer = textMeasurer,
            gridOffsets = gridOffsets,
            xAxis = xAxis,
            yAxis = yAxis,
            textStyle = textStyle,
            colors = colors,
            sizes = sizes,
            gridVisibility = gridVisibility,
        )

        drawLines(lines = lines, gridOffsets = gridOffsets, xAxis = xAxis, yAxis = yAxis)
    }
}

object LineChartDefaults {

    val LineStrokeWidth = 2.dp

    fun gridVisibility(
        showXAxis: Boolean = ChartDefaults.GridVisibility.ShowXAxis,
        showYAxis: Boolean = ChartDefaults.GridVisibility.ShowYAxis,
        showXGrid: Boolean = ChartDefaults.GridVisibility.ShowXGrid,
        showYGrid: Boolean = ChartDefaults.GridVisibility.ShowYGrid,
    ): GridVisibility = GridVisibility(
        showXAxis = showXAxis,
        showYAxis = showYAxis,
        showXGrid = showXGrid,
        showYGrid = showYGrid,
    )

    fun chartSizes(
        axisStrokeWidth: Dp = ChartDefaults.ChartSizes.AxisStrokeWidth,
        axisDashOn: Dp = ChartDefaults.ChartSizes.AxisDashOn,
        axisDashOff: Dp = ChartDefaults.ChartSizes.AxisDashOff,
        axisValuesPadding: Dp = ChartDefaults.ChartSizes.AxisValuesPadding,
    ): LineChartSizes = LineChartSizes(
        axisStrokeWidth = axisStrokeWidth,
        axisDashOn = axisDashOn,
        axisDashOff = axisDashOff,
        axisValuesPadding = axisValuesPadding,
    )

    @Composable
    fun chartColors(
        xAxisValues: Color = ChartDefaults.ChartColors.XAxisValues,
        xAxisGrid: Color = ChartDefaults.ChartColors.XAxisGrid,
        yAxisValues: Color = ChartDefaults.ChartColors.YAxisValues,
        yAxisGrid: Color = ChartDefaults.ChartColors.YAxisGrid,
    ): LineChartColors = LineChartColors(
        xAxisValues = xAxisValues,
        xAxisGrid = xAxisGrid,
        yAxisValues = yAxisValues,
        yAxisGrid = yAxisGrid,
    )
}

@Immutable
class LineChartSizes internal constructor(
    axisStrokeWidth: Dp,
    axisDashOn: Dp,
    axisDashOff: Dp,
    axisValuesPadding: Dp,
) : ChartSizes(axisStrokeWidth = axisStrokeWidth, axisDashOn = axisDashOn, axisDashOff = axisDashOff, axisValuesPadding = axisValuesPadding)

@Immutable
class LineChartColors internal constructor(
    xAxisValues: Color,
    xAxisGrid: Color,
    yAxisValues: Color,
    yAxisGrid: Color,
) : ChartColors(xAxisValues = xAxisValues, xAxisGrid = xAxisGrid, yAxisValues = yAxisValues, yAxisGrid = yAxisGrid)
