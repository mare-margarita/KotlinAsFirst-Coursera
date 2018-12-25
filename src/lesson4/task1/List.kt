@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.abs
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = abs(sqrt(v.map { it * it }.sum()))

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
/*
fun mean(list: List<Double>): Double {
    val sum = list.sum()
    val quantity = list.size
    return if (list.isEmpty()) 0.0
    else sum / quantity
}*/

fun mean(list: List<Double>): Double {
    return if (list.isNotEmpty()) {
        list.map { it }.average()
    } else {
        return 0.0
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()
/* ??????
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val mean = list.map { it }.average()
        (0 until list.size).map { list[it] - mean }
    }
    return list
}
*/
/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
// fun times(a: List<Double>, b: List<Double>): Double = a.zip(b) { x, y -> x * y }.sum()
fun times(a: List<Double>, b: List<Double>): Double =
        (0 until a.size).map { a[it] * b[it] }.sum()
//http://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/zip.html

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */

fun polynom(p: List<Double>, x: Double): Double {
    val xList: MutableList<Double> = mutableListOf()
    for (i in 0 until p.size) {
        val element = (x.pow(i))
        xList.add(element)
    }
    return (0 until p.size).map { p[it] * xList[it] }.sum()
}

//если элементы не повторяются:
//(0 until p.size).map { p[it] * x.pow(p.indexOf(p[it])) }.sum()

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        for (i in list.size - 1 downTo 1) {
            val subList = list.subList(0, i)
            val sum = subList.sum()
            val element = list[i]
            list[i] = sum + element
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun isPrime(n: Int) = n >= 2 && (2..n / 2).all { n % it != 0 }

fun factorize(n: Int): List<Int> {
    val resultList = mutableListOf<Int>()
    var numberList = n
    while (numberList > 1) {
        for (i in 2..numberList) {
            if ((numberList % i == 0) && (isPrime(i))) {
                resultList.add(i)
                numberList /= i
                break
            }
        }
    }
    return resultList.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
// fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

fun factorizeToString(n: Int): String {
    var resultString = ""
    var numberList = n
    while (numberList > 1) {
        for (i in 2..numberList) {
            if ((numberList % i == 0) && (isPrime(i))) {
                resultString = "$resultString*$i"
                numberList /= i
                break
            }
        }
    }
    return resultString.substring(1, resultString.length)
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val resultList = mutableListOf<Int>()
    do {
        resultList.add(0, number % base)
        number /= base
    } while (number > 0)
    return resultList
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val numbers: List<String> = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
            "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "aa", "ab")
    var number = n
    var resultString = ""
    do {
        resultString = numbers[number % base] + resultString
        number /= base
    } while (number > 0)
    return resultString
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var pow = digits.size
    for (element in digits) {
        pow -= 1
        result += element * base.toDouble().pow(pow).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int =
//  return str.toIntOrNull(base)!!
        str.toInt(base)
//    https://hype.codes/how-convert-string-long-kotlin

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val romeNumbers: List<String> = listOf("", "I", "V", "X", "L", "C", "D", "M")
    var result = ""
    var number = n
    var i = 1 // Индекс в списке
    while (number > 0) {
        when (number % 10) {
            0 -> result += ""
            1 -> result = romeNumbers[i] + result
            2 -> result = romeNumbers[i] + romeNumbers[i] + result
            3 -> result = romeNumbers[i] + romeNumbers[i] + romeNumbers[i] + result
            4 -> result = romeNumbers[i] + romeNumbers[i + 1] + result
            5 -> result = romeNumbers[i + 1] + result
            6 -> result = romeNumbers[i + 1] + romeNumbers[i] + result
            7 -> result = romeNumbers[i + 1] + romeNumbers[i] + romeNumbers[i] + result
            8 -> result = romeNumbers[i + 1] + romeNumbers[i] + romeNumbers[i] + romeNumbers[i] + result
            9 -> result = romeNumbers[i] + romeNumbers[i + 2] + result
        }
        number /= 10
        i += 2
    }
    return result
}


/*
{
    val romeNumbers: List<String> = listOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM",
            "M", "MM", "MMM")
    val arabianNumbers: List<Int> = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100,
            200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 3000)
    for (element in arabianNumbers) {
        if (n == element) {
            val index = arabianNumbers.indexOf(element)
            return romeNumbers[index]
        }
    }
    val units = n % 10
    val unitsIndex = arabianNumbers.indexOf(units)
    val tens = (n / 10 % 10) * 10
    val tensIndex = arabianNumbers.indexOf(tens)
    val hundreds = (n / 100 % 10) * 100
    val hundredsIndex = arabianNumbers.indexOf(hundreds)
    val thousands = (n / 1000) * 1000
    val thousandsIndex = arabianNumbers.indexOf(thousands)
    return romeNumbers[thousandsIndex] + romeNumbers[hundredsIndex] + romeNumbers[tensIndex] + romeNumbers[unitsIndex]
}*/

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
//до 999 999 999
fun russian(n: Int): String {
    val numbers: List<String> = listOf("", "один ", "два ", "три ", "четыре ", "пять ", "шесть ", "семь ",
            "восемь ", "девять ", "десять ", "одиннадцать ", "двенадцать ", "тринадцать ", "четырнадцать "
            , "пятнадцать ", "шестнадцать ", "семнадцать ", "восемнадцать ", "девятнадцать ", "двадцать ",
            "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "
            , "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот "
            , "девятьсот ", "одна ", "две ")
    val words1: List<String> = listOf("", "тысяча ")
    val words2: List<String> = listOf("", "тысячи ")
    val words3: List<String> = listOf("", "тысяч ")
    val wordsMil1: List<String> = listOf("", "миллион ")
    val wordsMil2: List<String> = listOf("", "миллиона ")
    val wordsMil3: List<String> = listOf("", "миллионов ")
    var result = ""
    var number = n
    var i = 0 // Индекс в списке слов тысячи
    var y = 0 // Индекс в списке слов миллионы

    while (number > 0) {
        if ((number % 100 > 9) && (number % 100 < 20)) {
            when (number % 100) {
                0 ->
                    if (n > 1000) {
                        result = numbers[0] + wordsMil3[i] + words3[i] + result
                    }
                10 -> result = numbers[10] + wordsMil3[y] + words3[i] + result
                11 -> result = numbers[11] + wordsMil3[y] + words3[i] + result
                12 -> result = numbers[12] + wordsMil3[y] + words3[i] + result
                13 -> result = numbers[13] + wordsMil3[y] + words3[i] + result
                14 -> result = numbers[14] + wordsMil3[y] + words3[i] + result
                15 -> result = numbers[15] + wordsMil3[y] + words3[i] + result
                16 -> result = numbers[16] + wordsMil3[y] + words3[i] + result
                17 -> result = numbers[17] + wordsMil3[y] + words3[i] + result
                18 -> result = numbers[18] + wordsMil3[y] + words3[i] + result
                19 -> result = numbers[19] + wordsMil3[y] + words3[i] + result
            }
        } else
            when (number % 10) {
                0 ->
                    if (n > 1000) {
                        result = numbers[0] + wordsMil3[y] + words3[i] + result
                    }

                1 ->
                    if (n == number) {
                        result = numbers[1] + result
                    } else if (n / 1000 == number) {
                        result = numbers[37] + words1[i] + result
                    } else if (n / 1000000 == number) {
                        result = numbers[1] + wordsMil1[y] + result
                    }
                2 ->
                    if (n == number) {
                        result = numbers[2] + result
                    } else if (n / 1000 == number) {
                        result = numbers[38] + words2[i] + result
                    } else if (n / 1000000 == number) {
                        result = numbers[2] + wordsMil2[y] + result
                    }

                3 -> result = numbers[3] + wordsMil2[y] + words2[i] + result
                4 -> result = numbers[4] + wordsMil2[y] + words2[i] + result
                5 -> result = numbers[5] + wordsMil3[y] + words3[i] + result
                6 -> result = numbers[6] + wordsMil3[y] + words3[i] + result
                7 -> result = numbers[7] + wordsMil3[y] + words3[i] + result
                8 -> result = numbers[8] + wordsMil3[y] + words3[i] + result
                9 -> result = numbers[9] + wordsMil3[y] + words3[i] + result
            }
        number /= 10
        when (number % 10) {
            2 -> result = numbers[20] + result
            3 -> result = numbers[21] + result
            4 -> result = numbers[22] + result
            5 -> result = numbers[23] + result
            6 -> result = numbers[24] + result
            7 -> result = numbers[25] + result
            8 -> result = numbers[26] + result
            9 -> result = numbers[27] + result
        }
        number /= 10
        when (number % 10) {
            1 -> result = numbers[28] + result
            2 -> result = numbers[29] + result
            3 -> result = numbers[30] + result
            4 -> result = numbers[31] + result
            5 -> result = numbers[32] + result
            6 -> result = numbers[33] + result
            7 -> result = numbers[34] + result
            8 -> result = numbers[35] + result
            9 -> result = numbers[36] + result
        }
        number /= 10
        if (((n > 999) && (n / 1000) % 1000 != 0)) {
            if (i == 0) {
                i += 1
            }
        }
        if ((n > 999999) && (n / 1000 > number)) {
            y += 1
            if (i > 0) {
                i -= 1
            }
        }
    }

    return result.trim()
}
/* по условию
{
    val numbers: List<String> = listOf("", "один ", "два ", "три ", "четыре ", "пять ", "шесть ", "семь ",
            "восемь ", "девять ", "десять ", "одиннадцать ", "двенадцать ", "тринадцать ", "четырнадцать "
            , "пятнадцать ", "шестнадцать ", "семнадцать ", "восемнадцать ", "девятнадцать ", "двадцать ",
            "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "
            , "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот "
            , "девятьсот ", "одна ", "две ")
    val words1: List<String> = listOf("", "тысяча ")
    val words2: List<String> = listOf("", "тысячи ")
    val words3: List<String> = listOf("", "тысяч ")
    var result = ""
    var number = n
    var i = 0 // Индекс в списке слов

    while (number > 0) {
        if ((number % 100 > 9) && (number % 100 < 20)) {
            when (number % 100) {
                0 ->
                    if (n > 1000) {
                        result = numbers[0] + words3[i] + result
                    }
                10 -> result = numbers[10] + words3[i] + result
                11 -> result = numbers[11] + words3[i] + result
                12 -> result = numbers[12] + words3[i] + result
                13 -> result = numbers[13] + words3[i] + result
                14 -> result = numbers[14] + words3[i] + result
                15 -> result = numbers[15] + words3[i] + result
                16 -> result = numbers[16] + words3[i] + result
                17 -> result = numbers[17] + words3[i] + result
                18 -> result = numbers[18] + words3[i] + result
                19 -> result = numbers[19] + words3[i] + result
            }
        } else
            when (number % 10) {
                0 ->
                    if (n > 1000) {
                        result = numbers[0] + words3[i] + result
                    }
                1 ->
                    if (n == number) {
                        result = numbers[1] + result
                    } else if (n / 1000 == number) {
                        result = numbers[37] + words1[i] + result
                    }
                2 ->
                    if (n == number) {
                        result = numbers[2] + result
                    } else if (n / 1000 == number) {
                        result = numbers[38] + words2[i] + result
                    }

                3 -> result = numbers[3] + words2[i] + result
                4 -> result = numbers[4] + words2[i] + result
                5 -> result = numbers[5] + words3[i] + result
                6 -> result = numbers[6] + words3[i] + result
                7 -> result = numbers[7] + words3[i] + result
                8 -> result = numbers[8] + words3[i] + result
                9 -> result = numbers[9] + words3[i] + result
            }
        number /= 10
        when (number % 10) {
            2 -> result = numbers[20] + result
            3 -> result = numbers[21] + result
            4 -> result = numbers[22] + result
            5 -> result = numbers[23] + result
            6 -> result = numbers[24] + result
            7 -> result = numbers[25] + result
            8 -> result = numbers[26] + result
            9 -> result = numbers[27] + result
        }
        number /= 10
        when (number % 10) {
            1 -> result = numbers[28] + result
            2 -> result = numbers[29] + result
            3 -> result = numbers[30] + result
            4 -> result = numbers[31] + result
            5 -> result = numbers[32] + result
            6 -> result = numbers[33] + result
            7 -> result = numbers[34] + result
            8 -> result = numbers[35] + result
            9 -> result = numbers[36] + result
        }
        number /= 10

        if (n > 999) {
            i += 1
        }
    }
    return result.trim()
}*/