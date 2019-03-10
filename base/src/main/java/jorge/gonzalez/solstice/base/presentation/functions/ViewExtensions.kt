package jorge.gonzalez.solstice.base.presentation.functions

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(imageUrl: String,
                        placeHolder: Int? = null,
                        onError: (() -> Unit)? = null,
                        onDone: (() -> Unit)? = null) {

    val callback = object : Callback {
        override fun onError() {
            onError?.invoke()
        }

        override fun onSuccess() {
            onDone?.invoke()
        }
    }

    val load = Picasso.with(context).load(imageUrl)
    placeHolder?.let { load.placeholder(it) }
    load.into(this, callback)
}