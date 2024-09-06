package com.example.majkomulti.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.StiffnessMedium
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
inline fun <T> LazyListScope.animatedItems(
    items: List<T>,
    stiffness: Float = StiffnessMedium,
    noinline key: ((item: T) -> Any)? = { "${it.hashCode()}" },
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    Box(
        modifier = Modifier
            .animateItemPlacement(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = stiffness
                )
            ),
    ){
        itemContent(items[it])
    }
}
