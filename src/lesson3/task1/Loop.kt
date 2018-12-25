@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = n
    do {
        count++
        number /= 10
    } while (number != 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var sum = 1
    if (n < 3) return 1
    else for (i in 3..n) {
        sum += fib1
        fib1 = sum - fib1
    }
    return sum
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
// Решение через НОД
fun gsd(m: Int, n: Int): Int {
    var a = abs(n)
    var b = abs(m)
    while ((a > 0 && b > 0)) {
        if (a > b)
            a %= b
        else b %= a
    }
    return a + b
}

fun lcm(m: Int, n: Int): Int = (abs(m) * abs(n)) / gsd(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    //Наименьший простой делитель составного числа а не превосходит √а
    var result = 0
    if (n % 2 == 0) return 2
    else if (isPrime(n)) return n
    else
        for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
            if ((n % i) > 0) continue
            result += i
            break
        }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var result = 0
    when {
        isPrime(n) -> return 1
        n % 2 == 0 -> return (n / 2)
        else -> for (i in n / 2 downTo 3) {
            if (n % i == 0) {
                result += i
                return result
            }
        }
    }
    return result
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
// Решение через НОД
fun isCoPrime(m: Int, n: Int): Boolean = gsd(m, n) == 1

/*//Другое
fun isCoPrime(m: Int, n: Int): Boolean {
    var result = 0
    val number = (min(m, n))
    for (i in 2..number) {
        if (((n % i) == 0) && ((m % i) == 0))
{
            result += i
            break
}
    }
    return result == 0
}*/

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */

fun squareBetweenExists(m: Int, n: Int): Boolean {
    var result = 0
    for (i in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt() + 1)
        if ((i * i <= n) && (i * i >= m)) {
            result++
        }
    return result > 0
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var numberRevert = 0
    while (number != 0) {
        numberRevert = numberRevert * 10 + number % 10
        number /= 10
    }
    return numberRevert
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    // Палиндро́м, пе́ревертень — число, буквосочетание,
    // слово или текст, одинаково читающееся в обоих направлениях.
    var number = n
    var numberRevert = 0
    while (number > 0) {
        numberRevert = numberRevert * 10 + number % 10
        number /= 10
    }
    return numberRevert == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 0
    var number = 0
    var numberConst = 0
    var countResult = 0
    for (i in 1..n) {
        number += sqr(i)
        numberConst = sqr(i)
        while (number > 0) {
            count += 1
            number /= 10
        }
        while (count > 0) {
            count -= 1
            countResult += 1
            if (countResult == n) return ((numberConst / (10.0.pow(count)).toInt()) % 10)
        }
    }
    return ((numberConst / (10.0.pow(count)).toInt()) % 10)
}

/*{
    var count = 0
    var number = 0
    var numberConst = 0
    var countResult = 0
    for (i in 1..n)
{
        number += i * i
        numberConst = i * i
        while (number > 0) {
            count += 1
            number /= 10
        }
        while (count > 0) {
            count -= 1
            countResult += 1
            if (countResult == n) return ((numberConst / (10.0.pow(count)).toInt()) % 10)
        }
    }
    return ((numberConst / (10.0.pow(count)).toInt()) % 10)
}*/

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var firstFib = 1
    var sum = 0
    var count = 0
    var number = 0
    var numberConst = 0
    var countResult = 0
    for (i in 1..n) {
        // фиб 1 - это фиб 1, фиб 2 - это сумма
        sum += firstFib
        number += sum
        numberConst = sum
        firstFib = sum - firstFib
        while (number > 0) {
            count += 1
            number /= 10
        }
        while (count > 0) {
            count -= 1
            countResult += 1
            if (countResult == n) return ((numberConst / (10.0.pow(count)).toInt()) % 10)
        }
    }
    return ((numberConst / (10.0.pow(count)).toInt()) % 10)
}

