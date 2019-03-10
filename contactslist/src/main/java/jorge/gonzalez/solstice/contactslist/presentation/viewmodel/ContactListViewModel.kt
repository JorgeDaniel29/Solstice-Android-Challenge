package jorge.gonzalez.solstice.contactslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import jorge.gonzalez.solstice.base.presentation.ui.SingleLiveEvent
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact
import jorge.gonzalez.solstice.contactslist.domain.model.MapContacts
import jorge.gonzalez.solstice.contactslist.domain.usecase.ContactListUseCase
import jorge.gonzalez.solstice.contactslist.domain.usecase.UpdateContactUseCase
import jorge.gonzalez.solstice.contactslist.presentation.mapper.ContactItemMapper
import jorge.gonzalez.solstice.contactslist.presentation.model.AvailableContactItem
import jorge.gonzalez.solstice.contactslist.presentation.model.ContactListUiState
import jorge.gonzalez.solstice.contactslist.presentation.model.ContactUpdateUiState
import jorge.gonzalez.solstice.contactslist.presentation.model.MapContactsItem

class ContactListViewModel(
        private val contactListUseCase: ContactListUseCase,
        private val updateContactUseCase: UpdateContactUseCase,
        private val contactItemMapper: ContactItemMapper
): ViewModel() {

    private lateinit var mapContacts: MapContacts
    val contactsListUiState = MutableLiveData<ContactListUiState>()
    val contactsUpdateUiState = MutableLiveData<ContactUpdateUiState>()
    val goToContactDetail = SingleLiveEvent<AvailableContact>()

    fun fetchContactsList() {
        contactListUseCase.execute(object :DisposableSingleObserver<MapContacts>() {
            override fun onSuccess(contacts: MapContacts) {
                mapContacts = contacts
                contactsListUiState.postValue(ContactListUiState.Data(
                        MapContactsItem(
                                mapContacts.favoriteContactsList.map { contactItemMapper.mapToEntity(it) },
                                mapContacts.otherContactsList.map { contactItemMapper.mapToEntity(it) }
                        )))
            }

            override fun onError(e: Throwable) = contactsListUiState.postValue(ContactListUiState.Error)

            override fun onStart() = contactsListUiState.postValue(ContactListUiState.Loading)
        })
    }

    fun updateContact(id: String) {
        updateContactUseCase.execute(object :DisposableCompletableObserver(){
            override fun onComplete() {
                fetchContactsList()
                contactsUpdateUiState.postValue(ContactUpdateUiState.Data)
            }

            override fun onError(e: Throwable) = contactsUpdateUiState.postValue(ContactUpdateUiState.Error)

            override fun onStart() = contactsListUiState.postValue(ContactListUiState.Loading)
        }, id)
    }

    fun onContactClicked(contact: AvailableContactItem) {
        goToContactDetail.postValue(
                if (contact.isFavorite)
                    mapContacts.favoriteContactsList.find { it.id == contact.id }
                else
                    mapContacts.otherContactsList.find { it.id == contact.id }
        )
    }

    override fun onCleared() {
        contactListUseCase.clear()
        updateContactUseCase.clear()
        super.onCleared()
    }
}