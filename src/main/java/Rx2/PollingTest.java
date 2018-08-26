package Rx2;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PollingTest {

    public static void main(String[] args) throws InterruptedException {

        final boolean[] isDone = {false};

        System.out.println("Running: " + Thread.currentThread().getName());

        CountDownLatch latch = new CountDownLatch(1);

        Completable doWork = Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Thread.sleep(2500);
            }
        });

        doWork.subscribeOn(Schedulers.computation())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Setting to true: " + Thread.currentThread().getName());
                        isDone[0] = true;
                    }
                });

        Observable
                .interval(500, TimeUnit.MILLISECONDS, Schedulers.computation())
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        System.out.println("Check isDone: " + isDone[0] + " " + Thread.currentThread().getName());
                        return isDone[0];
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("OnNext: " + Thread.currentThread().getName());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("OnComplete: " + Thread.currentThread().getName());
                        latch.countDown();
                    }
                });


        latch.await();


    }


}
