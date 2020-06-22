package edy.app.change.adapters.list

import android.view.View
import androidx.navigation.findNavController

class HandlerAdapter {

    /**
     * Navegation from Button
     * @param v View
     * @param d Int
     */
    fun navigate(v: View, d: Int) {
        if (d != -1) {
            v.findNavController().navigate(d)
        }
    }
}