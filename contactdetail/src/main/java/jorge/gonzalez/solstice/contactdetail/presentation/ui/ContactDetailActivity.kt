package jorge.gonzalez.solstice.contactdetail.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jorge.gonzalez.solstice.base.presentation.functions.loadImage
import jorge.gonzalez.solstice.base.presentation.functions.nonNullObserve
import jorge.gonzalez.solstice.contactdetail.R
import jorge.gonzalez.solstice.contactdetail.presentation.model.Contact
import jorge.gonzalez.solstice.contactdetail.presentation.viewmodel.ContactDetailViewModel
import kotlinx.android.synthetic.main.activity_contact_detail.*
import org.koin.android.ext.android.inject
import android.view.Menu
import androidx.core.content.ContextCompat
import android.view.MenuItem
import jorge.gonzalez.solstice.contactdetail.presentation.model.Address
import jorge.gonzalez.solstice.contactdetail.presentation.model.Phone


class ContactDetailActivity : AppCompatActivity() {

    private val contactDetailViewModel: ContactDetailViewModel by inject()
    private var favoriteMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        check(intent.hasExtra(CONTACT_DETAIL_EXTRA)) { "contact detail is null, put valid contact detail" }
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        startViewModel()
        bindViewModel()
        savedInstanceState?.let { onRestoreState(it) }
    }

    private fun startViewModel() = getContact().apply { contactDetailViewModel.initContactDetail(this) }

    private fun bindViewModel() = contactDetailViewModel.apply {

        name.nonNullObserve(this@ContactDetailActivity) { contactName.text = it }
        companyName.nonNullObserve(this@ContactDetailActivity) { company.text = it }
        largeImageURL.nonNullObserve(this@ContactDetailActivity) { contactImage.loadImage(it, R.drawable.ic_user_large) }
        contactDetail.nonNullObserve(this@ContactDetailActivity) {

            it.phone?.apply {
                if (!home.isNullOrEmpty())
                    addPhoneInfo(home, getString(R.string.tr_home_phone_text))

                if (!mobile.isNullOrEmpty())
                    addPhoneInfo(mobile, getString(R.string.tr_mobile_phone_text))

                if (!work.isNullOrEmpty())
                    addPhoneInfo(work, getString(R.string.tr_work_phone_text))
            }

            it.address?.let { address -> addAddress(address) }

            it.birthdate?.let { birthDate -> addBirthDate(birthDate) }

            it.emailAddress?.let { email -> addEmail(email) }
        }
    }

    private fun onRestoreState(savedInstanceState: Bundle?) = contactDetailViewModel.onRestoreInstanceState(savedInstanceState)

    private fun addPhoneInfo(phone: String, description: String) {
        val contactInfo = ContactInfoView(this@ContactDetailActivity)
        contactInfo.setTypeInfoContact(getString(R.string.tr_phone_text))
        contactInfo.setDescriptionContact(description)
        contactInfo.setInfoContact(phone)
        containerInfoContact.addView(contactInfo)
    }

    private fun addAddress(address: String) {
        val contactInfo = ContactInfoView(this@ContactDetailActivity)
        contactInfo.setTypeInfoContact(getString(R.string.tr_address_text))
        contactInfo.setInfoContact(address)
        containerInfoContact.addView(contactInfo)
    }

    private fun addBirthDate(birthDate: String) {
        val contactInfo = ContactInfoView(this@ContactDetailActivity)
        contactInfo.setTypeInfoContact(getString(R.string.tr_birthdate_text))
        contactInfo.setInfoContact(birthDate)
        containerInfoContact.addView(contactInfo)
    }

    private fun addEmail(email: String) {
        val contactInfo = ContactInfoView(this@ContactDetailActivity)
        contactInfo.setTypeInfoContact(getString(R.string.tr_email_text))
        contactInfo.setInfoContact(email)
        containerInfoContact.addView(contactInfo)
    }

    @SuppressLint("NewApi")
    private fun changeFavorite(favorite: Boolean) {
        favoriteMenu?.getItem(0)?.icon = ContextCompat
                .getDrawable(this@ContactDetailActivity,
                        if (favorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        favoriteMenu = menu
        menuInflater.inflate(R.menu.favorite_menu, menu)
        contactDetailViewModel.isFavorite.nonNullObserve(this@ContactDetailActivity) { changeFavorite(it) }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()

            R.id.actionFavorite -> {
                contactDetailViewModel.setFavorite()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getContact(): Contact = intent?.getParcelableExtra(CONTACT_DETAIL_EXTRA)!!

    private fun hasChanged(): Boolean = (getContact().isFavorite != contactDetailViewModel.isFavorite.value)

    override fun onBackPressed() {
        if (hasChanged()) {
            with(Intent()) {
                putExtra(CONTACT_ID_EXTRA, getContact().id)
                setResult(Activity.RESULT_OK, this)
                finish()
            }
        }

        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        contactDetailViewModel.onSaveInstanceState(outState)
    }

    companion object {
        private const val CONTACTS_DETAIL_REQUEST_CODE = 306
        private const val CONTACT_DETAIL_EXTRA = "contactDetail"
        private const val CONTACT_ID_EXTRA = "contactId"

        fun startActivityForResult(activity: AppCompatActivity, contact: Contact) {
            with(Intent(activity, ContactDetailActivity::class.java)) {
                putExtra(CONTACT_DETAIL_EXTRA, contact)
                activity.startActivityForResult(this, CONTACTS_DETAIL_REQUEST_CODE)
            }
        }

        fun isContactDetailRequestResult(requestCode: Int): Boolean {
            return requestCode == CONTACTS_DETAIL_REQUEST_CODE
        }

        fun getContactId(intent: Intent?): String? = intent?.getStringExtra(CONTACT_ID_EXTRA)

        fun getContactData(name: String,
                           id: String,
                           companyName: String?,
                           isFavorite: Boolean,
                           largeImageURL: String?,
                           emailAddress: String?,
                           birthDate: String?,
                           work: String?,
                           home: String?,
                           mobile: String?,
                           street: String?,
                           city: String?,
                           state: String?,
                           country: String?,
                           zipCode: String?): Contact = Contact(
                name,
                id,
                companyName,
                isFavorite,
                largeImageURL,
                emailAddress,
                birthDate,
                Phone(work, home, mobile),
                Address(street, city, state, country, zipCode)
        )
    }
}
