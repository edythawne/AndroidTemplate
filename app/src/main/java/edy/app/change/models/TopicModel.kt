package edy.app.change.models

import android.graphics.drawable.Drawable
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class TopicModel(val title: String, val description: String, val nav1: Int, val nav2: Int, val drawable: Drawable) : Serializable {

    override fun toString(): String {
        return "$title $description"
    }

}