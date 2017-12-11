
import java.math.BigInteger
import kotlin.system.exitProcess

/**
 *
 * Fibonacci Sequence - Enter a number and have the program generate the Fibonacci sequence to that number or
 * to the Nth number.
 *
 */

fun main (args:Array<String>){


    val arg:String?

    val n:Long?

    //this max can be increased depending on how much available memory you have
    val maxNthNumber:Long=20000

    println("Generate a Fibonacci sequence up to number prompted in standard input")
    print("Please, enter the Nth number: ")


    arg=readLine()

    // the line read from standard input can be null or can't be converted in a long
    n=arg?.toLongOrNull()

    if (n==null /*check if n is null*/ ||
            n<0 ||
            n > maxNthNumber) {
        println("Please, enter an integer greater than or equal to 0 and less than or equal to $maxNthNumber")
        exitProcess(1) /* exit from program */
    }

    println("Fibonacci value for F($n) is ${fastFibonacci(n)}")

    println("Fibonacci sequence up to F($n)")

    // print the last 4 numbers
    for (i in (n-3)..(n-1))
        print("F($i)=${fastFibonacci(i)},")
    print("F($n)=${fastFibonacci(n)}")


}

/**
 * Fibonacci sequence generator using a simple recursive algorithm.
 * This method does not scale with increasing of Nth number.
 * F(0)=0, F(1)=1, F(n+1)=F(n)+F(n-1) for n>=1
 */
fun fibonacci(n:Long):Long{
    if (n<=1) return n
    return fibonacci(n-1) + fibonacci(n-2)
}

/**
 * Fibonacci sequence generator using iterative algorithm.
 * This method is faster than previous recursive one and uses BigInteger type for very high precision
 */
fun fastFibonacci(n:Long):BigInteger{

    var result:BigInteger= BigInteger.ZERO
    var next: BigInteger=BigInteger.ONE
    var tmp:BigInteger

    for (i in 0..(n-1)){
        tmp=result
        result=next
        next=tmp.add(next)
    }

    return result;
}