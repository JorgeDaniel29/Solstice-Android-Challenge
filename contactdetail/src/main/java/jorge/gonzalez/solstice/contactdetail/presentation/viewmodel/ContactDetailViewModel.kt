package jorge.gonzalez.solstice.contactdetail.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jorge.gonzalez.solstice.base.presentation.ui.SingleLiveEvent
import jorge.gonzalez.solstice.contactdetail.presentation.functions.toAddressFormat
import jorge.gonzalez.solstice.contactdetail.presentation.functions.toBirthDateFormat
import jorge.gonzalez.solstice.contactdetail.presentation.functions.toPhoneFormat
import jorge.gonzalez.solstice.contactdetail.presentation.model.Contact
import jorge.gonzalez.solstice.contactdetail.presentation.model.ContactDetail

class ContactDetailViewModel : ViewModel() {

    val name = SingleLiveEvent<String>()
    val companyName = SingleLiveEvent<String>()
    val isFavorite = MutableLiveData<Boolean>()
    val largeImageURL = SingleLiveEvent<String>()
    val contactDetail = SingleLiveEvent<ContactDetail>()

    fun initContactDetail(contact: Contact) {
        name.value = (contact.name)
        companyName.value = contact.companyName
        isFavorite.value = contact.isFavorite
        largeImageURL.value = (contact.largeImageURL)
        contactDetail.value = ContactDetail(contact.emailAddress,
                toBirthDateFormat(contact.birthdate),
                contact.phone.toPhoneFormat(),
                contact.address.toAddressFormat())
    }

    fun setFavorite() {
        isFavorite.value?.let {
            isFavorite.value = !it
        }
    }

    fun onSaveInstanceState(outState: Bundle?) = outState
            ?.let {
                it.putBoolean(FAVORITE_EXTRA, isFavorite.value.takeIf { value -> value != null }
                        ?: false) }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) = savedInstanceState
            ?.let { isFavorite.value = it.getBoolean(FAVORITE_EXTRA) }

    companion object {
        private const val FAVORITE_EXTRA = "favorite"
    }
}