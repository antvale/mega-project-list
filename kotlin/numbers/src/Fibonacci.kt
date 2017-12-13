
import java.math.BigInteger
import java.util.stream.Stream
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

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

    //println("Last 4 Fibonacci sequence numbers up to F($n)")

    println("Using recursive function with tailrec prefix")
    val t1 = measureTimeMillis {
        for (i in 1..(n-1))
            fibonacci(i, BigInteger.ONE, BigInteger.ZERO)
            //print("${fibonacci(i, BigInteger.ONE, BigInteger.ZERO)},")
        //print("${fastFibonacci(n)}")
    }

    println("\nTook $t1 msec")

    println("Using traditional iterative function")
    val t2 = measureTimeMillis {
        for (i in 1..(n-1))
            fastFibonacci(i)
            //print("${fastFibonacci(i)},")
        //print("${fastFibonacci(n)}")
    }

    println("\nTook $t2 msec")

    var i:Int=0

    println("Using a Kotlin sequence generator")
    val t3 = measureTimeMillis {
        fib.take(n.toInt()).forEach { i++ }
        println(i)
        //println(fib.take(n.toInt()).joinToString())
    }
    print("\nTook $t3 msec")
}

/**
 * Fibonacci sequence generator using a simple recursive algorithm.
 * This method does not scale with increasing of Nth number and suffers of
 * stackoverflow exception
 * F(0)=0, F(1)=1, F(n+1)=F(n)+F(n-1) for n>=1
 */
fun fibonacci(n:Long):Long{
    if (n<=1) return n
    return fibonacci(n-1) + fibonacci(n-2)
}

/**
 * Fibinacci sequence using a faster recursive algorithm
 * This function uses tailrec kotlin capability to avoid the stackoverflow exception
 */
tailrec fun fibonacci(n:Long, a:BigInteger, b:BigInteger):BigInteger{
    return if (n<1) b  else fibonacci(n-1,a+b,a)
}

/**
 * Fibonacci sequence generator using iterative algorithm.
 * This method is faster than previous recursive one and uses BigInteger type for very high precision
 */
fun fastFibonacci(n:Long):BigInteger{

    var current:BigInteger= BigInteger.ZERO
    var next: BigInteger=BigInteger.ONE
    var tmp:BigInteger

    for (i in 0..(n-1)){
        tmp=current
        current=next
        next=tmp.add(next)
    }

    return current;
}

/**
 * Fibonacci sequence based on Kotlin sequence generator.
 * generateSequence generates a sequence of numbers starting from a Pair of seed
 * Below sequence starts from seed (1,1) and generates the next pair (second,first+second):
 * (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21), (21, 34), (34, 55), (55, 89)
 * to have fib sequence we need to get just the first element of each pair, you can do that
 * using .map(first)
 * Note: it.second to it.first+it.second -> Pair(it.second,it.first+it.second)
 */
val fib = generateSequence(BigInteger.ONE to BigInteger.ONE)
            { it.second to it.first + it.second }.map { it.first }