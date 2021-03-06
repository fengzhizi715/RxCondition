package com.safframework.rxcondition;

import io.reactivex.*;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Tony Shen on 2017/5/9.
 */

public final class Statement {

    public static <R> Observable<R> ifThen(BooleanSupplier condition, Observable<? extends R> then) {
        return ifThen(condition, then, Observable.<R> empty());
    }

    public static <R> Observable<R> ifThen(BooleanSupplier condition, Observable<? extends R> then,
                                           Observable<? extends R> orElse) {
        return RxJavaPlugins.onAssembly(new ObservableIfThen<R>(condition, then, orElse));
    }

    public static <R> Flowable<R> ifThen(BooleanSupplier condition, Publisher<? extends R> then) {

        return ifThen(condition, then, Flowable.<R>empty());
    }

    public static <R> Flowable<R> ifThen(BooleanSupplier condition, Publisher<? extends R> then,
                                         Flowable<? extends R> orElse) {

        return RxJavaPlugins.onAssembly(new FlowableIfThen<R>(condition, then, orElse));
    }

    public static <R> Maybe<R> ifThen(BooleanSupplier condition, Maybe<? extends R> then) {

        return ifThen(condition, then, Maybe.<R>empty());
    }

    public static <R> Maybe<R> ifThen(BooleanSupplier condition, Maybe<? extends R> then,
                                      Maybe<? extends R> orElse) {

        return RxJavaPlugins.onAssembly(new MaybeIfThen<R>(condition, then, orElse));
    }

    public static <R> Single<R> ifThen(BooleanSupplier condition, Single<? extends R> then) {

        return ifThen(condition, then, Single.<R>never());
    }

    public static <R> Single<R> ifThen(BooleanSupplier condition, Single<? extends R> then,
                                       Single<? extends R> orElse) {

        return RxJavaPlugins.onAssembly(new SingleIfThen(condition, then, orElse));
    }

    public static Completable ifThen(BooleanSupplier condition, Completable then) {

        return ifThen(condition, then, Completable.complete());
    }

    public static Completable ifThen(BooleanSupplier condition, Completable then,
                                     Completable orElse) {

        return RxJavaPlugins.onAssembly(new CompletableIfThen(condition, then, orElse));
    }

    public static <K, R> Observable<R> switchCase(Callable<? extends K> caseSelector,
                                                  Map<? super K, ? extends Observable<? extends R>> mapOfCases,
                                                  Observable<? extends R> defaultCase) {
        return RxJavaPlugins.onAssembly(new ObservableSwitchCase<R, K>(caseSelector, mapOfCases, defaultCase));
    }

    public static <K, R> Flowable<R> switchCase(Callable<? extends K> caseSelector,
                                                Map<? super K, ? extends Publisher<? extends R>> mapOfCases,
                                                Publisher<? extends R> defaultCase) {

        return RxJavaPlugins.onAssembly(new FlowableSwitchCase<R, K>(caseSelector, mapOfCases, defaultCase));
    }

    public static <K, R> Maybe<R> switchCase(Callable<? extends K> caseSelector,
                                                  Map<? super K, ? extends Maybe<? extends R>> mapOfCases,
                                             Maybe<? extends R> defaultCase) {
        return RxJavaPlugins.onAssembly(new MaybeSwitchCase<R, K>(caseSelector, mapOfCases, defaultCase));
    }

    public static <K, R> Single<R> switchCase(Callable<? extends K> caseSelector,
                                             Map<? super K, ? extends Single<? extends R>> mapOfCases,
                                             Single<? extends R> defaultCase) {
        return RxJavaPlugins.onAssembly(new SingleSwitchCase<R, K>(caseSelector, mapOfCases, defaultCase));
    }

    public static <K> Completable switchCase(Callable<? extends K> caseSelector,
                                              Map<? super K, ? extends Completable> mapOfCases,
                                             Completable defaultCase) {
        return RxJavaPlugins.onAssembly(new CompletableSwitchCase<K>(caseSelector, mapOfCases, defaultCase));
    }

}
