package jorge.gonzalez.solstice.base.domain

import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in PARAMS> : AbstractDisposableUseCase() {

    protected abstract fun buildUseCaseObservable(params: PARAMS? = null): Single<T>

    open fun execute(singleObserver: DisposableSingleObserver<T>, params: PARAMS? = null) {
        disposables.clear()

        val observer = this.buildUseCaseObservable(params)
                .setUpUseCase()
                .subscribeWith(singleObserver)

        observer.run { addDisposable(this) }
    }

    fun <T> Single<T>.setUpUseCase(): Single<T> = subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
}