package com.roughsets

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.nio.file.Paths

fun main() {
    people()
    weather()
}

fun people() {
    val table = mutableListOf<MathObject>()

    val projectPath = Paths.get("").toAbsolutePath().toString()
    val peopleFile = File("$projectPath/src/main/resources/people.csv")
    csvReader {
        delimiter = ";"[0]
    }.open(peopleFile) {
        readAllAsSequence().forEach { row: List<String> ->
            table += createHuman(row)
        }
    }

    println("------------------------------------------")
    println("Data imported from CSV")
    println("------------------------------------------")

    table.forEach { println(it.prettyPrintFormat()) }

    println("------------------------------------------")
    println("Data approximated by: Headache, Temperature")
    println("------------------------------------------")
    println("Elementary sets:")

    val data = groupObjectsBy(table, listOf(HumanConst.HEADACHE, HumanConst.TEMPERATURE))

    data.forEachIndexed { index, list ->
        print("E${index + 1}: ")
        print("[")
        list.forEachIndexed { nestedIndex, value ->
            print(value.getId())
            if (nestedIndex < list.size - 1)
                print(", ")
        }
        println("]")
    }

    println("------------------------------------------")
    println("Sets of objects with decision 'Flu' equal 'yes'")
    println("------------------------------------------")

    val result = approximation(data, listOf(Pair(HumanConst.FLU, true)))

    println("Lower approximation:")
    val lowerApproximation = result[0]
    print("[")
    lowerApproximation.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < lowerApproximation.size - 1)
            print(", ")
    }
    println("]")

    println("Upper approximation")
    val upperApproximation = result[1]
    print("[")
    upperApproximation.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < upperApproximation.size - 1)
            print(", ")
    }
    println("]")

    println("Boundary region")
    val boundaryRegionResult = boundaryRegion(result[0], result[1])

    print("[")
    boundaryRegionResult.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < boundaryRegionResult.size - 1)
            print(", ")
    }
    println("]")

    println("Negative region")
    val negativeRegion = result[2]
    print("[")
    negativeRegion.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < negativeRegion.size - 1)
            print(", ")
    }
    println("]")

    println("------------------------------------------")
    println("Approximation accuracy factor")

    println("⍺ = ${proportion(result[0].size, result[1].size)}")
}

fun weather() {
    val table = mutableListOf<MathObject>()

    val projectPath = Paths.get("").toAbsolutePath().toString()
    val weatherFile = File("$projectPath/src/main/resources/weather.csv")
    csvReader {
        delimiter = ","[0]
    }.open(weatherFile) {
        readAllAsSequence().forEach { row: List<String> ->
            table += createWeather(row)
        }
    }

    println("------------------------------------------")
    println("Data imported from CSV")
    println("------------------------------------------")

    table.forEach { println(it.prettyPrintFormat()) }

    println("------------------------------------------")
    println("Data approximated by: Outlook, Temperature")
    println("------------------------------------------")
    println("Elementary sets:")

    val data = groupObjectsBy(table, listOf(WeatherConst.OUTLOOK, WeatherConst.TEMPERATURE))

    data.forEachIndexed { index, list ->
        print("E${index + 1}: ")
        print("[")
        list.forEachIndexed { nestedIndex, value ->
            print(value.getId())
            if (nestedIndex < list.size - 1)
                print(", ")
        }
        println("]")
    }

    println("------------------------------------------")
    println("Sets of objects with decision 'Play' equal 'no'")
    println("------------------------------------------")

    val approximationData = approximation(data, listOf(Pair(WeatherConst.PLAY, false)))

    println("Lower approximation:")
    val lowerApproximation = approximationData[0]
    print("[")
    lowerApproximation.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < lowerApproximation.size - 1)
            print(", ")
    }
    println("]")

    println("Upper approximation:")
    val upperApproximation = approximationData[1]
    print("[")
    upperApproximation.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < upperApproximation.size - 1)
            print(", ")
    }
    println("]")

    println("Boundary region:")
    val boundaryRegionResult = boundaryRegion(approximationData[0], approximationData[1])
    print("[")
    boundaryRegionResult.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < boundaryRegionResult.size - 1)
            print(", ")
    }
    println("]")

    println("Negative region:")
    val negativeRegion = approximationData[2]
    print("[")
    negativeRegion.forEachIndexed { nestedIndex, value ->
        print(value.getId())
        if (nestedIndex < negativeRegion.size - 1)
            print(", ")
    }
    println("]")

    println("------------------------------------------")
    println("Approximation accuracy factor:")

    println("⍺ = ${proportion(approximationData[0].size, approximationData[1].size)}")
}