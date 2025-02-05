# Changelog

## [2.0.2] - January 13, 2025
- **`[BREAKING]`** [`DonutChart`](https://github.com/Tweener/chartopia/blob/main/chartopia/src/commonMain/kotlin/com/tweener/chartopia/type/donut/DonutChart.kt) color and size customization methods have been renamed to `DonutChartDefault.colors()` and `DonutChartDefault.sizes()`.
- **`[BREAKING]`** [`LineChart`](https://github.com/Tweener/chartopia/blob/main/chartopia/src/commonMain/kotlin/com/tweener/chartopia/type/line/LineChart.kt) color and size customization methods have been renamed to `LineChartDefaults.colors()` and `LineChartDefaults.sizes()`.
- Update Kotlin to 2.1.10.
- Update Corouines to 1.10.1
- Update Czan to 2.9.1
- Update KmpKit to 1.0.2
- Update AndroidActivity to 1.10.0

## [2.0.1] - January 13, 2025
- Add support for JVM, JS and WasmJS targets.

## [2.0.0] - January 13, 2025
- **`[BREAKING]`** `kmp-charts` library has been renamed `Chartopia`.
> [!WARNING]
> **Update library dependency:** `io.github.tweener:kmp-charts:1.*` ➡️ `io.github.tweener:chartopia:2.0.0`.
> 
> **Update imports:** `import com.tweener.charts.*` ➡️ `import com.tweener.chartopia.*`.
- Update Kotlin to 2.1.0.
- Update Compose Multiplatform to 1.7.3.
- Update Gradle to 8.11.1.

## [1.3.1] - December 18, 2024
- 🚀 Upgrade to Compose Multiplatform 1.7.1
- Fixes line drawing when only two points in `LineChart`: When a curved line only has two points, use `path.quadraticTo()`, otherwise use `path.cubicTo()`.
- Use [KMPKit](https://github.com/Tweener/KMPKit) instead of [kmp-common](https://github.com/Tweener/kmp-bom/tree/main/kmp-common).

## [1.3.0] - October 17, 2024
- 🚀 Upgrade to Kotlin 2.0.21 & Gradle 8.5.2
- 🚀 Upgrade to Compose Multiplatform 1.7.0
- Grid X & Y axes now have their own individual padding
- `LineChart`: Ensure first and last values on the X axis are within the axis bounds

## [1.2.0] - September 27, 2024
- Updated project configuration to use Gradle version catalog.
- **`[BREAKING]`** `DonutChart` has been moved to a new package: com.tweener.charts.type.donut.
- **`[BREAKING]`** `Segment` class has been moved to a new package: com.tweener.charts.type.donut.model.
- New chart added: `LineChart` to display plotted points connected with a straight or curved line.
- Fixes a bug where `DonutChart`'s segments were not properly updated
- `DonutChart`'s segments now have a selected property which allows pre-selection of a segment during composition.
- Added a new property to `DonutChart` to allow customization of the StrokeCap.

## [1.1.0] - May 28, 2024
- **kmp-charts** now official support Kotlin 2.0! 🎉

## [1.0.1] - April 28, 2024
- `DonutChart` - Each segment now has an ID to better identify which segment has been clicked by the user.
- `DonutChart` - Each segment can now be enabled/disabled to whether or not respond to click events.

## [1.0.0] - March 6, 2024

### 🚀 Initial Release

The first official release of **kmp-charts**.

#### Features
  - DonutChart
