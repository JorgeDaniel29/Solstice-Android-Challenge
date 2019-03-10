package jorge.gonzalez.solstice.contactslist.presentation.ui

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import jorge.gonzalez.solstice.contactslist.R
import kotlinx.android.synthetic.main.header_item_view_holder.view.*

class HeaderItem(
        private val titleHeader: String
) : Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.header_item_view_holder

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.title.text = titleHeader
    }
}