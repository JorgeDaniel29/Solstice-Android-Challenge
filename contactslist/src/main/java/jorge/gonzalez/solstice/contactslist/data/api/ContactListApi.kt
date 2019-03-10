package jorge.gonzalez.solstice.contactslist.data.api

import io.reactivex.Single
import jorge.gonzalez.solstice.contactslist.data.model.Contact
import retrofit2.http.GET

interface ContactListApi {
    @GET("v3/contacts.json")
    fun getContacts(): Single<List<Contact>>
}