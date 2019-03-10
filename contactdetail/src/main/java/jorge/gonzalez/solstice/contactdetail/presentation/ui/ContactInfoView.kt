package jorge.gonzalez.solstice.contactdetail.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import jorge.gonzalez.solstice.contactdetail.R
import kotlinx.android.synthetic.main.view_info_contact.view.*

class ContactInfoView : ConstraintLayout {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {

        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        inflater?.inflate(R.layout.view_info_contact, this, true)

        val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ContactInfoView,
                0, 0)


        typedArray.apply {

            if (hasValue(R.styleable.ContactInfoView_info))
                info.text = getString(R.styleable.ContactInfoView_info)

            if (hasValue(R.styleable.ContactInfoView_typeInfo))
                typeInfo.text = getString(R.styleable.ContactInfoView_typeInfo)

            if (hasValue(R.styleable.ContactInfoView_description))
                description.text = getString(R.styleable.ContactInfoView_description)

            recycle()
        }
    }

    fun setInfoContact(infoContact: String) {
        info.text = infoContact
    }

    fun setTypeInfoContact(typeInfoContact: String) {
        typeInfo.text = typeInfoContact
    }

    fun setDescriptionContact(descriptionContact: String) {
        description.text = descriptionContact
    }
}