package jorge.gonzalez.solstice.contactslist.data.model

import com.google.gson.annotations.SerializedName

data class Contact(
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: String,
        @SerializedName("companyName") val companyName: String,
        @SerializedName("isFavorite") var isFavorite: Boolean,
        @SerializedName("smallImageURL") val smallImageURL: String,
        @SerializedName("largeImageURL") val largeImageURL: String,
        @SerializedName("emailAddress") val emailAddress: String,
        @SerializedName("birthdate") val birthDate: String,
        @SerializedName("phone") val phone: Phone,
        @SerializedName("address") val address: Address
)