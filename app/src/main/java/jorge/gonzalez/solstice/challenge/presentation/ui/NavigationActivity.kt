package jorge.gonzalez.solstice.challenge.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import jorge.gonzalez.solstice.base.presentation.functions.nonNullObserve
import jorge.gonzalez.solstice.challenge.R
import jorge.gonzalez.solstice.challenge.presentation.viewmodel.NavigationViewModel
import jorge.gonzalez.solstice.contactdetail.presentation.model.Contact
import jorge.gonzalez.solstice.contactdetail.presentation.ui.ContactDetailActivity
import jorge.gonzalez.solstice.contactdetail.presentation.ui.ContactDetailActivity.Companion.getContactData
import jorge.gonzalez.solstice.contactdetail.presentation.ui.ContactDetailActivity.Companion.getContactId
import jorge.gonzalez.solstice.contactdetail.presentation.ui.ContactDetailActivity.Companion.isContactDetailRequestResult
import jorge.gonzalez.solstice.contactslist.presentation.ui.ContactListActivity
import jorge.gonzalez.solstice.contactslist.presentation.ui.ContactListActivity.Companion.getContactFromResult
import jorge.gonzalez.solstice.contactslist.presentation.ui.ContactListActivity.Companion.isContactRequestResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    private val navigationViewModel: NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigationViewModel.apply {
            if (savedInstanceState == null) startNavigation()
            navigateToContactsList.observe(this@NavigationActivity, Observer { navigateToFetchContactsList(it) })
            navigateToContactDetail.nonNullObserve(this@NavigationActivity) { navigateToContactDetail(it) }
        }
    }

    private fun navigateToFetchContactsList(contactId: String? = null) = ContactListActivity.startActivityForResult(this@NavigationActivity, contactId)
    private fun navigateToContactDetail(contact: Contact) = ContactDetailActivity.startActivityForResult(this@NavigationActivity, contact)
    private fun isRequestCanceled(resultCode: Int) :Boolean = (resultCode == Activity.RESULT_CANCELED)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            isContactRequestResult(requestCode) -> {
                if (isRequestCanceled(resultCode)) finish()
                else getContactFromResult(data)?.apply {
                    navigationViewModel.goToContactDetail(getContactData(
                            name,
                            id,
                            companyName,
                            isFavorite,
                            largeImageURL,
                            emailAddress,
                            birthdate,
                            phone?.work,
                            phone?.home,
                            phone?.mobile,
                            address?.street,
                            address?.city,
                            address?.state,
                            address?.country,
                            address?.zipCode)
                    )
                }
            }

            isContactDetailRequestResult(requestCode) -> {
                if (isRequestCanceled(resultCode)) navigationViewModel.startNavigation()
                else getContactId(data)?.let { navigationViewModel.startNavigation(it) }
            }
        }
    }
}
