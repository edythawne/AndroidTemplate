package edy.app.change.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import edy.app.change.models.TopicModel

class ViewModel(val mState: SavedStateHandle) : ViewModel() {

    // TAG Variable
    private val TAG: String = ViewModel::class.java.name

    // LiveData
    private val topicData: MutableLiveData<ArrayList<TopicModel>> by lazy { MutableLiveData<ArrayList<TopicModel>>() }

    /**
     * Add Observer
     * @param lifecycle Lifecycle
     */
    fun addLifecycle(lifecycle: Lifecycle) {

    }

    /**
     * getMldTopics
     * @param list ArrayList<TopicModel>
     * @return MutableLiveData<ArrayList<TopicModel>>
     */
    fun getMldTopics(list: ArrayList<TopicModel>): MutableLiveData<ArrayList<TopicModel>> {
        topicData.postValue(list)
        return topicData
    }
}