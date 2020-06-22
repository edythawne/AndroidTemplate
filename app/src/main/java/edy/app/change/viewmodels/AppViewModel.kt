package edy.app.change.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import edy.app.change.data.TopicRepository
import edy.app.change.models.TopicModel

class AppViewModel internal constructor(private val repository: TopicRepository, private val mState: SavedStateHandle) : ViewModel() {

    // TAG Variable
    private val TAG: String = AppViewModel::class.java.name

    // LiveData
    val internetAccess: MutableLiveData<Boolean> = MutableLiveData(false)
    private val topicData: MutableLiveData<ArrayList<TopicModel>> = repository.getTopics()

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
    fun getMldTopics(): MutableLiveData<ArrayList<TopicModel>> {
        return topicData
    }
}