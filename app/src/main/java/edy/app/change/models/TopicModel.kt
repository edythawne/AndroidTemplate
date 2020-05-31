package edy.app.change.models

import android.graphics.drawable.Drawable

class TopicModel(
    val title: String,
    val description: String,
    val nav1: Int,
    val nav2: Int,
    val drawable: Drawable
) {
    // TAG Variable
    private val TAG: String = TopicModel::class.java.name
}