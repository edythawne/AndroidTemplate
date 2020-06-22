package edy.app.change.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import edy.app.change.data.TopicRepository
import edy.app.change.viewmodels.TopicsViewModelFactory

object InjectorUtils {

    /**
     * TopicRepository
     * @param context Context
     * @return TopicRepository
     */
    private fun getTopicsRepository(context: Context): TopicRepository {
        return TopicRepository.getInstance(context)
    }

    /**
     * Topics View Model Factory
     * @param fragment Fragment
     * @return TopicsViewModelFactory
     */
    fun provideTopicsViewModelFactory(fragment: Fragment): TopicsViewModelFactory {
        val repository = getTopicsRepository(fragment.requireContext())
        return TopicsViewModelFactory(repository, fragment)
    }

}