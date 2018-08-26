package Rx2;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class FlowableScheduler {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        getFlowable()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
//                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(100);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("OnNext: " + integer + Thread.currentThread().getName());
                        latch.countDown();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        latch.await();

    }

    public static Flowable<Integer> getFlowable() {

        return Flowable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getInteger();
            }
        });

    }

    public static Integer getInteger() {
        System.out.println("1" + Thread.currentThread().getName());
        return 1;
    }

}
