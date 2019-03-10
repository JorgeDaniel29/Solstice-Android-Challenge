package jorge.gonzalez.solstice.challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import jorge.gonzalez.solstice.base.presentation.ui.SingleLiveEvent
import jorge.gonzalez.solstice.contactdetail.presentation.model.Contact

class NavigationViewModel : ViewModel() {

    val navigateToContactsList = SingleLiveEvent<String>()
    val navigateToContactDetail = SingleLiveEvent<Contact>()

    fun startNavigation(contactId: String? = null) = contactId?.apply { navigateToContactsList.postValue(this) }
            ?: navigateToContactsList.call()

    fun goToContactDetail(contact: Contact) = navigateToContactDetail.postValue(contact)
}