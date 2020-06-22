package edy.app.change.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import edy.app.change.data.TopicRepository

class TopicsViewModelFactory(private val repository: TopicRepository, owner: SavedStateRegistryOwner, args: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, args) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return AppViewModel(repository, handle) as T
    }

}