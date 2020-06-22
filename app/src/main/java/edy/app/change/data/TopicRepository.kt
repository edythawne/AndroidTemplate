package edy.app.change.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import edy.app.change.R
import edy.app.change.models.TopicModel

class TopicRepository private constructor(private val context: Context) {

    companion object {

        @Volatile
        private var instance: TopicRepository? = null

        fun getInstance(context: Context): TopicRepository {
            return instance ?: synchronized(this) {
                instance ?: TopicRepository(context).also {
                    instance = it
                }
            }
        }

    }

    @SuppressLint("Recycle")
    fun getTopics(): MutableLiveData<ArrayList<TopicModel>> {
        val list: ArrayList<TopicModel> = ArrayList()
        val live: MutableLiveData<ArrayList<TopicModel>> = MutableLiveData()

        val t = context.resources.getStringArray(R.array.topics)
        val d = context.resources.getStringArray(R.array.topics_desc)
        val s = context.resources.obtainTypedArray(R.array.topics_src)
        val n = context.resources.obtainTypedArray(R.array.topics_destiny)

        for (i in t.indices) {
            list.add(TopicModel(t[i], d[i], n.getResourceId(i, -1), -1, s.getDrawable(i)!!))
        }

        live.postValue(list)

        return live
    }

}