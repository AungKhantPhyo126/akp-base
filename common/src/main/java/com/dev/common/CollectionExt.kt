package com.dev.common

fun <T> List<T>.singleSelectBy(
    selectedId: String,
    selector: (T) -> Pair<String, Boolean>,
    bind: (T, Boolean) -> T
) = this.map {
    val (id, isSelect) = selector(it)
    if (selectedId == id)
        bind(it, !isSelect)
    else bind(it, false)
}

fun <T> List<T>.singleSelectByAtLeastOneSelected(
    selectedId: String,
    selector: (T) -> Pair<String, Boolean>,//String is key to select, Boolean is the clicked item's selected state
    bind: (T, Boolean) -> T
): List<T> {
    return this.map{
        val (id, isSelect) = selector(it)
        if (selectedId == id)
            bind(it, true)
        else bind(it, false)
    }
}


fun <T> List<T>.singleSelectByIndex(
    index: Int,
    selector: (T) -> Boolean,
    bind: (T, Boolean) -> T
) = this.mapIndexed { mapIndex, it ->
    val isSelect = selector(it)
    if (index == mapIndex)
        bind(it, !isSelect)
    else bind(it, false)
}


fun <T> List<T>.multiSelectBy(
    selectedId: String,
    selector: (T) -> Pair<String, Boolean>,
    bind: (T, Boolean) -> T
) = this.map {
    val (id, isSelect) = selector(it)
    if (selectedId == id)
        bind(it, !isSelect)
    else bind(it, isSelect)
}

fun <T, R> List<T>.manyToManySelector(
    second: List<R>,
    predicate: (T, R) -> Boolean,
    bind: (T, Boolean) -> T
): List<T> {

    return map { first ->
        val isSelected = second.any {
            predicate(first, it)
        }
        bind(first, isSelected)
    }
}