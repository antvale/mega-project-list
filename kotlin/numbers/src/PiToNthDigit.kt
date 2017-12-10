import java.math.RoundingMode
import kotlin.math.PI

/**
 * Find PI to the Nth Digit
 * Enter a number and have the program generate PI up to that many decimal places.
 * Keep a limit to how far the program will go.
 *
 * This Kotlin file generates a PI number with decimals according to the entered number. Because of using java math.PI
 * constant converted in java BigDecimal (32-bit integer scale) the limit is kept to max decimal scale.
 * For more complex PI decimal generator try to implement the PI Chudnovsky algorithm in Kotlin
 */

fun main(args:Array<String>){

    val decimalArg:String?

    var n:Int?

    val piMaxScale:Int=PI.toBigDecimal().scale()

    println("Simple PI generator with number of decimals as prompted and up to java decimal scale limit.")
    print("How many decimals (please, enter a number less than or equal $piMaxScale)? ")

    decimalArg=readLine()

    n=decimalArg?.toIntOrNull()

    if (n==null || n > piMaxScale || n<0)
        n=piMaxScale


    println("Result PI: ${PI.toBigDecimal().setScale(n, RoundingMode.HALF_UP)}")

}