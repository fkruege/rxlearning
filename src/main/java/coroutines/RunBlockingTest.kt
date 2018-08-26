package coroutines

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.suspendCoroutine


fun main(args: Array<String>) {

//    runBlocking {
//        runTheTest()
//    }

//    // works
//    runBlocking {
//        launch (Unconfined){
//            runTheTest()
//        }
//    }

    // works
//    runBlocking {
//        async (Unconfined){ runTheTest() }.await()
//    }
//
//
    runBlocking {
        val deferred = async(start = CoroutineStart.LAZY) { runTheTest() }

        System.out.println("Await")

        deferred.await()
    }


    System.out.println("Ending Main-- ${Thread.currentThread().name}")
}

suspend fun runTheTest() {

//    runBlocking {
    try {
        val value = getTestValue()
//            voidTestValue()
        System.out.println("Value is: $value -- ${Thread.currentThread().name}")
    } catch (ex: NullPointerException) {
        System.out.println("Catching NPE ${Thread.currentThread().name}")
    }
//    }

    System.out.println("Terminating ${Thread.currentThread().name}")
}

suspend fun voidTestValue() {
    return suspendCoroutine { continuation ->
        val simulateAsync = SimulateAsync()
        simulateAsync.doIt(false, object : Callback {
            override fun Success(value: Int) {
                continuation.resume(Unit)
            }

            override fun Error(ex: Exception) {
                continuation.resumeWithException(ex)
            }
        })
    }
}

suspend fun getTestValue(): Int {
    return suspendCoroutine<Int> { continuation ->
        val simulateAsync = SimulateAsync()
        simulateAsync.doIt(false, object : Callback {
            override fun Success(value: Int) {
                continuation.resume(value)
            }

            override fun Error(ex: Exception) {
                continuation.resumeWithException(ex)
            }
        })
    }
}


class SimulateAsync {

    fun doIt(isException: Boolean, callback: Callback) {
        System.out.println("Sleeping ${Thread.currentThread().name}")
//        Thread.sleep(2500)
        if (isException) {
            callback.Error(NullPointerException("fail"))
        } else {
            System.out.println("Callback success ${Thread.currentThread().name}")
            callback.Success(99)
        }

    }

}


interface Callback {
    fun Success(value: Int)
    fun Error(ex: Exception)
}

