package jorge.gonzalez.solstice.base.domain

import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in PARAMS> : AbstractDisposableUseCase() {

    protected abstract fun buildUseCaseObservable(params: PARAMS?): Completable

    open fun execute(completableObserver: DisposableCompletableObserver, params: PARAMS? = null) {
        disposables.clear()

        val observer = this.buildUseCaseObservable(params)
                .setUpUseCase()
                .subscribeWith(completableObserver)

        observer.run { addDisposable(this) }
    }

    fun Completable.setUpUseCase(): Completable = subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
}