package jorge.gonzalez.solstice.contactdetail.presentation.functions

import android.os.ParcelFormatException
import java.text.SimpleDateFormat
import java.util.*

fun toBirthDateFormat(oldFormat: String?): String? {
    if (oldFormat.isNullOrEmpty()) return null
    try {
        val newFormat = "MMMM d, yyyy"
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val oldDate = simpleDateFormat.parse(oldFormat)

        simpleDateFormat.applyPattern(newFormat)

        return simpleDateFormat.format(oldDate).capitalize()

    } catch (e: ParcelFormatException) {
        e.printStackTrace()
    }

    return null
}