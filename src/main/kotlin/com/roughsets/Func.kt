package com.roughsets

fun boundaryRegion(lowerApproximationSet: List<MathObject>, upperApproximationSet: List<MathObject>): List<MathObject> {
    return upperApproximationSet.toMutableList().apply {
        removeAll(lowerApproximationSet)
    }
}

fun proportion(lowerApproximationSetSize: Int, upperApproximationSetSize: Int): Double {
    return lowerApproximationSetSize.toDouble() / upperApproximationSetSize.toDouble()
}

fun approximation(
    sets: List<List<MathObject>>,
    decisions: List<Pair<String, Boolean>>
): List<List<MathObject>> {
    val tmpList: MutableList<MutableList<MathObject>> =
        mutableListOf(
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

    sets.forEach { set ->
        var anyFalse = false
        var anyTrue = false

        set.forEach { mathObject ->
            decisions.forEach { decision ->
                if (mathObject.decisions.contains(decision))
                    anyTrue = true
                else
                    anyFalse = true
            }
        }

        if (anyFalse && anyTrue)
            tmpList[1].addAll(set)
        else if (anyFalse)
            tmpList[2].addAll(set)
        else {
            tmpList[0].addAll(set)
            tmpList[1].addAll(set)
        }
    }

    return tmpList
}

fun groupObjectsBy(
    table: List<MathObject>,
    attributes: List<String>
): List<List<MathObject>> {
    val tmpList: MutableList<List<MathObject>> = arrayListOf()
    table.forEachIndexed { index, _ ->
        val mathObject = findMathObjectsByAttributes(table, index, attributes)
        if (tmpList.size <= 0 || !tmpList.contains(mathObject))
            tmpList.add(mathObject)
    }

    return tmpList
}

fun findMathObjectsByAttributes(
    table: List<MathObject>,
    index: Int,
    attributes: List<String>
): List<MathObject> {
    val tmpList = mutableListOf<MathObject>()
    val selectedAttributes = mutableListOf<Pair<String, Any>>()
    attributes.forEach { attr ->
        table[index].mainAttributes.forEach {
            if (it.first == attr)
                selectedAttributes += it
        }
    }
    table.forEach { mathObject ->
        if (mathObject.mainAttributes.containsAll(selectedAttributes))
            tmpList += mathObject
    }
    return tmpList
}
