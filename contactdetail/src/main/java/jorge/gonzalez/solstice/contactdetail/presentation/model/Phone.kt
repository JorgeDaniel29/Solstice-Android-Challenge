package jorge.gonzalez.solstice.contactdetail.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Phone(
        val work: String?,
        val home: String?,
        val mobile: String?
) : Parcelable