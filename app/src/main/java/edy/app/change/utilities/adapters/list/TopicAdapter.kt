package edy.app.change.utilities.adapters.list

import edy.app.change.R
import edy.app.change.models.TopicModel
import edy.app.change.utilities.adapters.base.BaseAdapter

class TopicAdapter() : BaseAdapter() {

    // TAG Variable
    private val TAG: String = TopicAdapter::class.java.name

    // Variables
    private var list: List<TopicModel>? = null

    /**
     * setTopics
     * @param topicList List<TopicModel>
     */
    fun setTopics(topicList: List<TopicModel>) {
        this.list = topicList
        notifyDataSetChanged()
    }

    /**
     * getObjForPosition
     * @position Int
     */
    override fun getObjForPosition(position: Int): Any {
        return list!![position]
    }

    /**
     * getLayoutIdForPosition
     * @param position
     * @return
     */
    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_topic
    }

    /**
     * getItemCount
     * @return
     */
    override fun getItemCount(): Int {
        return list!!.size
    }

}