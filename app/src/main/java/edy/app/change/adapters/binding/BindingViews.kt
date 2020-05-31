package edy.app.change.adapters.binding

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
//import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.textfield.TextInputLayout

/**
 * Cargar imagen a ImageView
 * @param imageView ImageView
 * @param source Drawable
 */
@BindingAdapter("resourceImage")
fun loadImage(imageView: ImageView, source: Drawable) {
    Glide.with(imageView.context).load(source).transform(CenterInside(), RoundedCorners(24))
        .into(imageView)
}

@BindingAdapter("resourceImage")
fun loadImage(imageView: ImageView, source: Int) {
    Glide.with(imageView.context).load(source).transform(CenterInside(), RoundedCorners(24))
        .into(imageView)
}


/**
 * TextInputLayout
 * @param til TextInputLayout
 * @param error String
 */
@BindingAdapter(value = ["error_message"])
fun setTextWatcher(til: TextInputLayout, error: String) {
    til.editText!!.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().trim { it <= ' ' }.isEmpty()) {
                til.error = error
            } else {
                til.isErrorEnabled = false
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

@BindingAdapter(
    value = ["selected_value", "selected_value_changed"
        /**, "flex_layout"**/
    ], requireAll = false
)
fun setSpinnerValue(
    spinner: AppCompatSpinner,
    newSelectedValue: String,
    newTextAttr: InverseBindingListener
    /**, fl: FlexboxLayout**/
) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (newSelectedValue == parent!!.selectedItem) {
                return
            }
            //fl.removeAllViews()
            newTextAttr.onChange()
        }
    }

    if (newSelectedValue != null) {
        val pos = (spinner.adapter as ArrayAdapter<String?>).getPosition(newSelectedValue)
        spinner.setSelection(pos, true)
    }
}

/**
 * getSpinnerValue
 * @param spinner AppCompatSpinner
 * @return String
 */
@InverseBindingAdapter(attribute = "selected_value", event = "selected_value_changed")
fun getSpinnerValue(spinner: AppCompatSpinner): String {
    return spinner.selectedItem.toString()
}
