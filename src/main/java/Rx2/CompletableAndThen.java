package Rx2;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fkruege on 8/14/17.
 */
public class CompletableAndThen {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        complete1()
                .andThen(complete2())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onsubscribe");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("oncomplete");
                        latch.countDown();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

        latch.await();

    }

    public static Completable complete1() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Thread.sleep(2500);
                System.out.println("1" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.computation());
    }


    public static Completable complete2() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Thread.sleep(2500);
                System.out.println("2" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io());
    }
}
