package com.roughsets

object HumanConst {
    const val NAME = "Human"
    const val ID = "Id"
    const val HEADACHE = "Headache"
    const val MYALGIA = "Myalgia"
    const val TEMPERATURE = "Temperature"
    const val FLU = "Flu"
}

fun createHuman(value: List<String>): MathObject {
    return MathObject(
            HumanConst.NAME,
            listOf(
                    Pair(HumanConst.ID, value[0].toInt())
            ),
            listOf(
                    Pair(HumanConst.HEADACHE, detectBool(value[2])),
                    Pair(HumanConst.MYALGIA, detectBool(value[3])),
                    Pair(HumanConst.TEMPERATURE, value[4])
            ),
            listOf(
                    Pair(HumanConst.FLU, detectBool(value[5]))
            )
    )
}

object WeatherConst {
    const val NAME = "Weather"
    const val ID = "Id"
    const val OUTLOOK = "Outlook"
    const val TEMPERATURE = "Temperature"
    const val HUMIDITY = "Humidity"
    const val WIND = "Windy"
    const val PLAY = "Play"
}

fun createWeather(value: List<String>): MathObject {
    return MathObject(
            WeatherConst.NAME,
            listOf(
                    Pair(WeatherConst.ID, value[0].toInt())
            ),
            listOf(
                    Pair(WeatherConst.OUTLOOK, value[1]),
                    Pair(WeatherConst.TEMPERATURE, value[2]),
                    Pair(WeatherConst.HUMIDITY, value[3]),
                    Pair(WeatherConst.WIND, value[4])
            ),
            listOf(
                    Pair(WeatherConst.PLAY, detectBool(value[5]))
            )
    )
}

private fun detectBool(value: String) =
    when (value) {
        "TRUE" -> true
        "FALSE" -> false
        "yes" -> true
        "no" -> false
        "tak" -> true
        "nie" -> false
        else -> throw RuntimeException("Bad value")
    }