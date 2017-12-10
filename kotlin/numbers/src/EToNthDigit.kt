import kotlin.system.exitProcess

/**
 * Enter a number and have the program generate e up to that many decimal places.
 * Keep a limit to how far the program will go.
 *
 * The algorithm uses java math Euler's constant that is a double so the max decimals of e number can't exceed
 * this precision.
 */

fun main(args: Array<String>){

    val eulerNumber:Double

    val decimalArg:String?

    var n:Int?

    println("Simple Euler's number generator up to number of prompted decimals.")
    print("How many decimals? ")


    decimalArg=readLine()

    // the line read can be null
    n=decimalArg?.toIntOrNull()

    if (n==null /*check if n is null*/ ||
            n<0 ||
            n > Int.MAX_VALUE) {
        println("Please, enter an integer greater than or equal 0")
        exitProcess(1) /* exit from program */
    }

    // the n variable can't be null at this point so n!!. is not required
    eulerNumber = ( Math.floor(Math.E * Math.pow(10.toDouble(), n.toDouble())) ) / Math.pow(10.toDouble(),
                    n.toDouble())

    println("Eucler's number: $eulerNumber")

}