package edy.app.change.adapters.list

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager

class GridLayoutAdapter(val context: Context) {
    private val TAG: String = GridLayoutAdapter::class.java.name

    /**
     * GridLayoutManager
     * @return GridLayoutManager
     */
    fun getGridLayoutManager(): GridLayoutManager {
        return GridLayoutManager(context, calculateNumberOfColumns())
    }

    /**
     * calculateNumberOfColumns
     * @return Int
     */
    private fun calculateNumberOfColumns(): Int {
        var columns: Int = 1

        if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "Size : ${getScreenSizeCategory()}")
            if (getScreenSizeCategory() >= 1) {
                columns = 2
            }
        }

        return columns
    }

    /**
     * getScreenSizeCategory
     * @return Int
     */
    private fun getScreenSizeCategory(): Int {
        return when (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
            // small screens are at least 426dp x 320dp
            Configuration.SCREENLAYOUT_SIZE_SMALL -> 0

            // normal screens are at least 470dp x 320dp
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> 1

            // large screens are at least 640dp x 480dp
            Configuration.SCREENLAYOUT_SIZE_LARGE -> 2

            // xlarge screens are at least 960dp x 720dp
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> 3

            else -> -1
        }
    }

}