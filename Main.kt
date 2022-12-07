package chucknorris

fun main() {

    do {
        println("Please input operation (encode/decode/exit):")
        val action = readln()
        when (action) {
            "encode" -> {
                println("Input string:")
                val textToEncode = readln()
                println(encoderToZeroCode(textToEncode))
            }

            "decode" -> {
                println("Input encoded string:")
                val textToDecode = readln()
                println(decoderFromZeroCode(textToDecode))

            }

            "exit" -> println("Bye!")
            else -> println("There is no '$action' operation")

        }
    } while (action != "exit")
}


fun decoderFromZeroCode(toEncode: String): String {
    if (!toEncode.contains('0') || !toEncode.contains(' ')) {
        return "Encoded string is not valid."
    }
    val text = toEncode.split(" ")
    if (text.size % 2 != 0) {
        return "Encoded string is not valid."
    }
    var identificator = -1
    var binaryText = ""
    for (i in text.indices) {
        if (i % 2 == 0) {
            if (text[i] == "00") {
                identificator = 0
            } else if (text[i] == "0") {
                identificator = 1
            } else {
                return "Encoded string is not valid."
            }
        } else {
            repeat(text[i].length) {
                binaryText += identificator
            }
        }
    }
    if (binaryText.length % 7 != 0) {
        return "Encoded string is not valid."
    }
    var totalCounter = 1
    var counter = 0
    var binaryChar = ""
    var outputText = ""
    for (item in binaryText) {
        if (counter < 7) {
            binaryChar += item
            counter++
        } else {
            counter = 1
            outputText += binaryChar.toInt(2).toChar()
            binaryChar = item.toString()
        }
        if (totalCounter == binaryText.length) {
            outputText += binaryChar.toInt(2).toChar()
        }
        totalCounter++
    }
    println("Decoded string:")
    return outputText
}


fun encoderToZeroCode(toEncode: String): String {
    var binaryText = ""
    println("Encoded string:")
    val binaryCharacters = toEncode.map { c -> Integer.toBinaryString(c.code) }
    for (item in binaryCharacters) {
        binaryText += if (item.length < 7) {
            "0$item"
        } else {
            item
        }
    }
    var identificator = " 0 "
    var outputText: String = identificator

    for (char in binaryText) {

        if (char == '1') {
            if (identificator == " 0 ") {
                outputText += "0"
            } else {
                identificator = " 0 "
                outputText += identificator + "0"
            }
        } else {
            if (identificator == " 00 ") {
                outputText += "0"
            } else {
                identificator = " 00 "
                outputText += identificator + "0"
            }
        }
    }
    return outputText.trim()
}
