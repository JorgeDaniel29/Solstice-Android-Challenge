package jorge.gonzalez.solstice.contactslist.presentation.ui

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import jorge.gonzalez.solstice.base.presentation.functions.hide
import jorge.gonzalez.solstice.base.presentation.functions.loadImage
import jorge.gonzalez.solstice.base.presentation.functions.show
import jorge.gonzalez.solstice.contactslist.R
import jorge.gonzalez.solstice.contactslist.presentation.model.AvailableContactItem
import kotlinx.android.synthetic.main.contact_item_view_holder.view.*

class ContactItem(
        val contactItem: AvailableContactItem
) : Item<ViewHolder>() {

    override fun getLayout(): Int = R.layout.contact_item_view_holder

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.apply {

            contactName.text = contactItem.name
            companyName.text = contactItem.companyName ?: ""

            favoriteIcon?.apply {
                if (contactItem.isFavorite) show() else hide()
            }

            contactItem.smallImageURL?.let { smallImage.loadImage(it, R.drawable.ic_user_small) }
        }
    }
}