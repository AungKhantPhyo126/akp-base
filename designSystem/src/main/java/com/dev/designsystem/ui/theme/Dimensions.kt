package com.dev.designsystem.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** usecase
 * val spacing = LocalSpacing.current
 * spacing.default
 * */

data class Dimensions(
    val default: Dp = 0.dp,
    val space2: Dp = 2.dp,
    val space4: Dp = 4.dp,
    val space6: Dp = 6.dp,
    val space8: Dp = 8.dp,
    val space10: Dp = 10.dp,
    val space12: Dp = 12.dp,
    val space14: Dp = 14.dp,
    val space16: Dp = 16.dp,
    val space18: Dp = 18.dp,
    val space20: Dp = 20.dp,
    val space22: Dp = 22.dp,
    val space24: Dp = 24.dp,
    val space28: Dp = 28.dp,
    val space30: Dp = 30.dp,
    val space38: Dp = 38.dp,
    val space32: Dp = 32.dp,
    val space40: Dp = 40.dp,
    val space42: Dp = 42.dp,
    val space44: Dp = 44.dp,
    val space46: Dp = 46.dp,
    val space48: Dp = 48.dp,
    val space52: Dp = 52.dp,
    val space58: Dp = 58.dp,
    val space60: Dp = 60.dp,
    val space64: Dp = 64.dp,
    val space78: Dp = 78.dp,
    val space80: Dp = 80.dp,
    val space84: Dp = 84.dp,
    val space100: Dp = 100.dp,
    val genericTopAppBarContentMargin: Dp = space24,
    val genericHorizontalMargin: Dp = space24,
    val genericBottomMargin: Dp = space24,
    // Generic dimension use in all home screen toolbar height
    val originToolbarHeight: Dp = 56.dp,
    val genericToolbarHeight: Dp = 80.dp,
    val toolbarSpacingX: Dp = 70.dp,
    val topPaddingBottomSheet: Dp = 42.dp,
    val bottomPaddingBottomSheet: Dp = 40.dp,
    val bottomSheetGenericContentHeightFraction: Float = 0.93f,
    val smallDividerThick: Dp = 1.dp,
    val largeDividerThick: Dp = 6.dp,
    val cardPadding: Dp = 35.dp,
    val outlineButtonSpaceX: Dp = 60.dp,
    val lineHeight24: TextUnit = 24.sp,
    val lipContentSpacing: Dp = space28,
    val borderStrokeWidth: Dp = 1.dp,
    val trifectaExpClassHeight: Dp = 130.dp,
    val textFieldMiniHeight: Dp = 48.dp
)

 val LocalSpacing = compositionLocalOf { Dimensions() }

val spacing: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
