package org.misha.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertTrue;

public class Emitter {
    private final AtomicReference<BigInteger> value;
    Gen gen = new Gen(BigInteger.TEN);
    private Integer subscriber1 = 0;
    private Integer subscriber2 = 0;

    public Emitter() {
        this.value = new AtomicReference<>(BigInteger.ZERO);
    }

    private void emit() {
        Iterator<BigInteger> it = gen;
        Future<BigInteger> future = CompletableFuture.supplyAsync(() -> {
            while (it.hasNext()) {
                value.set(it.next());
            }
            return value.get();
        });
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            currentThread().interrupt();
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        Observable<BigInteger> emitter = Observable.fromFuture(future);
        emitter.subscribe(System.err::println);
    }

    @Test
    public void test() {
        emit();
        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
        Observable<Object> source = Observable.create(emmiter -> {
            try {
                emmiter.onNext("first");
                emmiter.onNext("second");
                emmiter.onNext("third");
                emmiter.onComplete();
            } catch (Exception ex) {
                emmiter.onError(ex);
            }
        });
        Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onComplete() {
                System.out.println("Emission completed ..");
            }

            @Override
            public void onError(Throwable arg0) {
                System.out.println("Error occurred: " + arg0.getLocalizedMessage());
            }

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribing");

            }

            @Override
            public void onNext(Object arg0) {
                System.out.println("Data is .. " + arg0);
            }
        };

        source.subscribe(observer::onNext);


        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(getFirstObserver());
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(getSecondObserver());
        subject.onNext(4);
        subject.onComplete();

        assertTrue(subscriber1 + subscriber2 == 14);
    }

    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer value) {
                subscriber1 += value;
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }


            @Override
            public void onComplete() {
                System.out.println("Subscriber1 completed");
            }
        };
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer value) {
                subscriber2 += value;
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("Subscriber2 completed");
            }
        };
    }

    static class Gen implements Iterator<BigInteger> {
        final BigInteger upperBound;
        BigInteger current = BigInteger.ZERO;

        Gen(BigInteger upperBound) {
            this.upperBound = upperBound;
        }

        @Override
        public boolean hasNext() {
            return current.compareTo(upperBound) < 0;
        }

        @Override
        public BigInteger next() {
            current = current.add(BigInteger.ONE);
            return current;
        }
    }


}
