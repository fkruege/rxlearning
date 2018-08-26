package Rx2


import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.MaybeSource
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.internal.operators.maybe.MaybeLift

import java.util.concurrent.Callable


fun main(args: Array<String>) {

    Maybe.just(1)
            .flatMap(object : Function<Int, MaybeSource<Int>> {
                override fun apply(integer: Int): MaybeSource<Int> {
                    System.out.println("emit empty")
                    return Maybe.empty()
                }
            })
            .doOnComplete { System.out.println("OnComplete earlier") }
            .switchIfEmpty(getMaybeSource())
            .doOnSuccess { integer -> System.out.println("Accept: " + integer!!) }
            .subscribe()
}

fun getMaybeSource(): Maybe<Int> {
    return Maybe.defer {
        System.out.println("on creation")
        Maybe.fromCallable {
            System.out.println("on subscriptin")
            12345
        }
    }

}

