package io.kaeawc.daggerexperiments

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class RxEventBus {

    private val ps: Subject<Any> = PublishSubject.create<Any>()

    fun send(event: Any) {
        ps.onNext(event)
    }

    fun toObservable(): Observable<Any> {
        return ps
    }

    fun <T> onMainThread(filterBy: Class<T>): Observable<T> {
        return toObservable().observeOn(AndroidSchedulers.mainThread())
                .ofType(filterBy)
    }

    fun <T> onBackgroundThread(filterBy: Class<T>): Observable<T> {
        return toObservable().observeOn(Schedulers.io())
                .ofType(filterBy)
    }

    fun onMainThread(filterBy: String): Observable<String> {
        return toObservable().observeOn(AndroidSchedulers.mainThread())
                .ofType(String::class.java)
                .filter { it == filterBy}
    }

    fun onBackgroundThread(filterBy: String): Observable<String> {
        return toObservable().observeOn(Schedulers.io())
                .ofType(String::class.java)
                .filter { it == filterBy}
    }

    fun hasObservers(): Boolean {
        return ps.hasObservers()
    }
}
