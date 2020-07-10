package edy.app.tools.helpers

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar

object NotifiersHelper {
    // TAG
    private val TAG: String = NotifiersHelper::class.java.name

    // SnackBar Duration
    val SNACK_LENGTH_LONG: Int = Snackbar.LENGTH_LONG
    val SNACK_LENGTH_SHORT: Int = Snackbar.LENGTH_SHORT
    val SNACK_LENGTH_INDEFINITE: Int = Snackbar.LENGTH_INDEFINITE

    /**
     * Generar Toast
     */
    fun toast(context: Context, msg: String) {
        getToast(context, msg).show()
    }

    /**
     * Generar SnackBar sin Acción
     */
    fun snackbar(activity: Activity, msg: String, duration: Int) {
        getSnackBar(activity, msg, duration).show()
    }

    /**
     * Generar SnackBar con Acción
     */
    fun snackbarWithAction(activity: Activity, titleAction: String, msg: String, listener: View.OnClickListener) {
        getSnackBar(activity, msg, Snackbar.LENGTH_INDEFINITE).setAction(titleAction, listener)
            .show()
    }

    /**
     * Create Toast
     */
    private fun getToast(context: Context, msg: String): Toast {
        return Toast.makeText(context, msg, Toast.LENGTH_LONG)
    }

    /**
     * Create SnackBar
     */
    private fun getSnackBar(activity: Activity, msg: String, duration: Int): Snackbar {
        val snackbar: Snackbar = Snackbar.make(activity.findViewById(android.R.id.content), msg, duration)
        //snackbar.view.setBackgroundColor(com.google.android.material.R.drawable.design_snackbar_background)
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(Color.WHITE)
        snackbar.setActionTextColor(Color.parseColor("#00B0FF"))
        snackbar.config(activity)
        return snackbar
    }

    private fun Snackbar.config(context: Context) {
        val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(12, 12, 12, 12)
        this.view.layoutParams = params

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.view.background = ContextCompat.getDrawable(context, com.google.android.material.R.drawable.design_snackbar_background)
        } else {
            this.view.setBackgroundDrawable(ContextCompat.getDrawable(context, com.google.android.material.R.drawable.design_snackbar_background))
        }

        ViewCompat.setElevation(this.view, 6f)
    }
}