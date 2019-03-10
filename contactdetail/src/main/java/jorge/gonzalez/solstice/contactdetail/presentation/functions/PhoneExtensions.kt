package jorge.gonzalez.solstice.contactdetail.presentation.functions

import jorge.gonzalez.solstice.contactdetail.presentation.model.Phone
import java.lang.StringBuilder

fun Phone?.toPhoneFormat(): Phone? = this?.run {
    Phone(
            work.format(),
            home.format(),
            mobile.format())
}

private fun String?.format(): String? = takeIf { !it.isNullOrEmpty() }?.run {
    StringBuilder()
            .append("(")
            .append(substringBefore('-'))
            .append(") ")
            .append(substringAfter('-'))
            .toString()
}
