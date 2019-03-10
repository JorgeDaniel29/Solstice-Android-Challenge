package jorge.gonzalez.solstice.contactslist.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import jorge.gonzalez.solstice.base.presentation.functions.hide
import jorge.gonzalez.solstice.base.presentation.functions.nonNullObserve
import jorge.gonzalez.solstice.base.presentation.functions.show
import jorge.gonzalez.solstice.contactslist.R
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact
import jorge.gonzalez.solstice.contactslist.presentation.model.ContactUpdateUiState
import jorge.gonzalez.solstice.contactslist.presentation.model.ContactListUiState
import jorge.gonzalez.solstice.contactslist.presentation.model.MapContactsItem
import jorge.gonzalez.solstice.contactslist.presentation.viewmodel.ContactListViewModel
import kotlinx.android.synthetic.main.activity_contacts_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListActivity : AppCompatActivity() {

    private val contactListViewModel: ContactListViewModel by viewModel()
    private val contactsListAdapter: GroupAdapter<ViewHolder> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)
        startViewModel()
        bindViewModel()
    }

    private fun startViewModel() = contactListViewModel.apply {
        changeContact()?.let { updateContact(it) } ?: fetchContactsList()
    }

    private fun bindViewModel() = contactListViewModel.run {

        contactsListUiState.nonNullObserve(this@ContactListActivity) {
            when (it) {
                is ContactListUiState.Data -> {
                    displayContactsList(it.contactItems)
                    errorView.hide()
                    progressView.hide()
                    contactsListView.show()
                }

                is ContactListUiState.Error -> {
                    progressView.hide()
                    contactsListView.hide()
                    setRetryButton()
                    errorView.show()
                }

                is ContactListUiState.Loading -> {
                    errorView.hide()
                    contactsListView.hide()
                    progressView.show()
                }
            }
        }

        contactsUpdateUiState.nonNullObserve(this@ContactListActivity) {
            when (it) {
                is ContactUpdateUiState.Data -> {
                    Toast.makeText(this@ContactListActivity,
                            getString(R.string.tr_successful_contact_update),
                            Toast.LENGTH_SHORT).show()
                }

                is ContactUpdateUiState.Error -> {
                    Toast.makeText(this@ContactListActivity,
                            getString(R.string.tr_unsuccessful_contact_update),
                            Toast.LENGTH_SHORT).show()
                }
            }
        }

        goToContactDetail.nonNullObserve(this@ContactListActivity) { deliveryContactResult(it) }
    }

    private fun displayContactsList(mapContacts: MapContactsItem) {
        contactsListView.adapter = contactsListAdapter
        contactsListView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        if (!mapContacts.favoriteContactsList.isNullOrEmpty()) {
            val section = Section()
            section.setHeader(HeaderItem(getString(R.string.tr_favorite_contacts_title)))
            section.addAll(mapContacts.favoriteContactsList.map { ContactItem(it) })
            contactsListAdapter.add(section)
        }

        if (!mapContacts.otherContactsList.isNullOrEmpty()) {
            val section = Section()
            section.setHeader(HeaderItem(getString(R.string.tr_other_contacts_title)))
            section.addAll(mapContacts.otherContactsList.map { ContactItem(it) })
            contactsListAdapter.add(section)
        }

        contactsListAdapter.setOnItemClickListener { item: Item<*>, _: View ->
            if (item is ContactItem) contactListViewModel.onContactClicked(item.contactItem)
        }
    }

    private fun setRetryButton() = contactsListRetryButton
            .setOnClickListener { startViewModel() }

    private fun changeContact(): String? = intent.getStringExtra(CONTACT_ID_EXTRA)

    private fun deliveryContactResult(contact: AvailableContact) {
        with(Intent()) {
            putExtra(CONTACT_RESULT, contact)
            setResult(Activity.RESULT_OK, this)
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }

    companion object {

        private const val CONTACTS_LIST_REQUEST_CODE = 305
        private const val CONTACT_RESULT = "contactResult"
        private const val CONTACT_ID_EXTRA = "contactId"

        fun startActivityForResult(activity: AppCompatActivity, contactId: String? = null) {
            val intent = Intent(activity, ContactListActivity::class.java)
            contactId?.let { intent.putExtra(CONTACT_ID_EXTRA, it) }
            activity.startActivityForResult(intent, CONTACTS_LIST_REQUEST_CODE)
        }

        fun isContactRequestResult(requestCode: Int): Boolean {
            return requestCode == CONTACTS_LIST_REQUEST_CODE
        }

        fun getContactFromResult(intent: Intent?): AvailableContact? = intent?.getParcelableExtra(CONTACT_RESULT)
    }
}