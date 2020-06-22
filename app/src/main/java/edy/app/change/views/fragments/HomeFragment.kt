package edy.app.change.views.fragments

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import edy.app.change.R
import edy.app.change.adapters.list.TopicAdapter
import edy.app.change.databinding.FragmentHomeBinding
import edy.app.change.models.TopicModel
import edy.app.change.utilities.Constants
import edy.app.change.utilities.InjectorUtils
import edy.app.change.viewmodels.AppViewModel

class HomeFragment : Fragment() {

    // TAG
    private val TAG: String = HomeFragment::class.java.name

    // Variables
    private val handler: Handler by lazy { Handler() }
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: AppViewModel by activityViewModels {
        InjectorUtils.provideTopicsViewModelFactory(this)
    }

    // Ads Variables
    private var showingInterstitial: Int = 0
    private var isShowInterstitial: Boolean = false
    //private lateinit var interstitial: AdInterstitial

    /**
     * onCreate
     * @param savedInstanceState Bundle?
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * onCreateView
     * @param inflater LayoutInflater
     * @param container ViewGroup?
     * @param savedInstanceState Bundle?
     * @return View?
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this@HomeFragment

        // Components
        configAds()
        configConnection()
        subscribe()

        return configBinding()
    }

    /**
     * onActivityCreated
     * @param savedInstanceState Bundle?
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * onViewCreated
     * @param view View
     * @param savedInstanceState Bundle?
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configToolbar()
    }

    /**
     * onViewStateRestored
     * @param savedInstanceState Bundle?
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.run {
            showingInterstitial = (getInt(Constants.KEY_INTERSTITIAL_SHOWING, 0) - 1)
            isShowInterstitial = getBoolean(Constants.KEY_INTERSTITIAL_IS_SHOWING, false)
        }
    }

    /**
     * onSaveInstanceState
     * @param outState Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(Constants.KEY_INTERSTITIAL_SHOWING, showingInterstitial)
            putBoolean(Constants.KEY_INTERSTITIAL_IS_SHOWING, isShowInterstitial)
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * onResume
     */
    override fun onResume() {
        super.onResume()
    }

    /**
     * onPause
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * onDestroyView
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * onCreateOptionsMenu
     * @param menu Menu
     * @param inflater MenuInflater
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * onOptionsItemSelected
     * @param item MenuItem
     * @return Boolean
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_setting -> {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_fhome_to_fsettings)
                true
            }

            else -> false
        }
    }

    /**
     * Binding
     */
    private fun configBinding(): View {
        binding.vm = viewModel
        return binding.root
    }

    /**
     * Advertising
     */
    private fun configAds() {
        /**interstitial = AdInterstitial.getInstance(requireContext(), getString(R.string.key_interstitial))

        interstitial.showInterstitial.observe(viewLifecycleOwner, Observer {
        isShowInterstitial = it
        })**/
    }

    /**
     * initToolbar
     */
    private fun configToolbar() {
        setHasOptionsMenu(true)
    }

    /**
     * subscribe
     */
    private fun subscribe() {
        val adapter = TopicAdapter()
        viewModel.getMldTopics()
            .observe(viewLifecycleOwner, Observer {
                configRecycler(it, adapter)
            })
    }

    /**
     * Config Recycler
     * @param topics ArrayList<TopicModel>
     */
    private fun configRecycler(topics: ArrayList<TopicModel>, topicAdapter: TopicAdapter) {
        topicAdapter.setTopics(topics)
        binding.tcs.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = topicAdapter
        }
    }

    /**
     * configConnection
     */
    private fun configConnection() {
        viewModel.internetAccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                configAdsInterstitial()
            }
        })
    }

    /**
     * Config Interstitial
     */
    private fun configAdsInterstitial() {
        /**try {
        val r = Runnable {
        if ((showingInterstitial % Constants.INTERSTITIAL_ALLOW_SHOW == 0) && (!isShowInterstitial)) {
        interstitial.init()
        }
        showingInterstitial += 1
        }

        handler.post(r)
        } catch (ex: Exception) {
        Log.w(TAG, "configInterstitial()[Error] : $ex")
        } **/
    }

}