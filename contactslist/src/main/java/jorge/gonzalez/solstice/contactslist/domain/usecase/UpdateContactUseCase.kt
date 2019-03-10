package jorge.gonzalez.solstice.contactslist.domain.usecase

import io.reactivex.Completable
import jorge.gonzalez.solstice.base.domain.CompletableUseCase
import jorge.gonzalez.solstice.contactslist.domain.repository.UpdateRepository

class UpdateContactUseCase(
        private val contactsRepository: UpdateRepository
) : CompletableUseCase<String>() {
    override fun buildUseCaseObservable(params: String?): Completable = params?.run {
        contactsRepository.updateContact(this).setUpUseCase()
    } ?: Completable.error(Exception())
}