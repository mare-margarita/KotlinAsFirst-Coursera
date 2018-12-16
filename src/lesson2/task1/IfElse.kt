
@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return if ((age <= 0) || (age > 199)) "no data"
    else if ((age in 11..14) || (age in 111..114)) "$age лет"
    else if ((age % 10 in 2..4)) "$age года"
    else if ((age % 10 in 5..9)) "$age лет"
    else if ((age % 10 == 0)) "$age лет"
    else "$age год"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val firstWay = (t1 * v1)
    val secondWay = (t2 * v2)
    val thirdWay = (t3 * v3)
    val halfWay = (firstWay + secondWay + thirdWay) / 2.0
    return when {((halfWay - firstWay) == 0.0) -> (t1)
        ((halfWay - firstWay) < 0) -> halfWay / v1

        ((halfWay - (firstWay + secondWay)) == 0.0) -> t1 + t2
        ((halfWay - (firstWay + secondWay)) < 0) -> (halfWay - firstWay) / v2 + t1

        else -> (halfWay - firstWay - secondWay) / v3 + t1 + t2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    return if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) 3
    else if ((kingX == rookX1 || kingY == rookY1)) 1
    else if ((kingX == rookX2 || kingY == rookY2)) 2
    else 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val x0 = kingX - bishopX
    val y0 = kingY - bishopY
    return if ((kingX == rookX || kingY == rookY) && (abs(x0) == abs(y0))) 3
    else if ((kingX == rookX || kingY == rookY)) 1
    else if (abs(x0) == abs(y0)) 2
    else 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return if ((a + b > c) && (a + c > b) && (b + c > a)) {
        if ((sqr(c)) == (sqr(a)) + (sqr(b)) || (sqr(b)) == (sqr(a)) + (sqr(c)) ||
                (sqr(a)) == (sqr(b)) + (sqr(c))) 1
        else if ((sqr(c)) > (sqr(a)) + (sqr(b)) || (sqr(b)) > (sqr(a)) + (sqr(c)) ||
                (sqr(a)) > (sqr(b)) + (sqr(c))) 2
        else 0
    }
    else -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val start = max(a, c)
    return if (b < d) {
        if (b - start >= 0) b - start
        else -1
    } else {
        if (d - start >= 0) d - start
        else -1
    }
}
