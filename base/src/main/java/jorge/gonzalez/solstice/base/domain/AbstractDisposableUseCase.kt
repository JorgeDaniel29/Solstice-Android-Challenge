package jorge.gonzalez.solstice.base.domain

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractDisposableUseCase {
    protected val disposables = CompositeDisposable()

    fun clear() = disposables.clear()

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}