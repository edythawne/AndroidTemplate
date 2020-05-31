package edy.app.change.adapters.list

import edy.app.change.R
import edy.app.change.adapters.base.BaseAdapter
import edy.app.change.models.TopicModel

class TopicAdapter(topicList: List<TopicModel>) : BaseAdapter() {

    // TAG Variable
    private val TAG: String = TopicAdapter::class.java.name

    // Variables
    private val list: List<TopicModel> = topicList

    /**
     * getObjForPosition
     * @position Int
     */
    override fun getObjForPosition(position: Int): Any {
        return list[position]
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
        return list.size
    }

}