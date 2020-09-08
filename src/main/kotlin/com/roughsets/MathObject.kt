package com.roughsets

data class MathObject(
    val name: String,
    val helperAttributes: List<Pair<String, Any>>,
    val mainAttributes: List<Pair<String, Any>>,
    val decisions: List<Pair<String, Boolean>>
) {
    fun getId() =
        helperAttributes[0].second as Int

    fun prettyPrintFormat() =
        "$name | ${prettyPrintListOfPair(helperAttributes)} ${prettyPrintListOfPair(mainAttributes)} " +
                prettyPrintListOfPair(decisions)

    private fun prettyPrintPair(pair: Pair<String, Any>) =
        "${pair.first}: ${pair.second}"

    private fun prettyPrintListOfPair(list: List<Pair<String, Any>>): String {
        var result = ""
        list.forEach {
            result += prettyPrintPair(it)
            result += " | "
        }
        return result
    }
}