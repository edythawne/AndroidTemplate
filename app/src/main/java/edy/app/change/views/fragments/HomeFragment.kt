package edy.app.change.views.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import edy.app.change.R
import edy.app.change.databinding.FragmentHomeBinding
import edy.app.change.models.TopicModel
import edy.app.change.utilities.Constants
import edy.app.change.utilities.InjectorUtils
import edy.app.change.utilities.adapters.list.TopicAdapter
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
    //private lateinit var interstitial: AdInterstitial

    // Lifecycle
    private var timesInterstitialShown: Int = -1
    private var isInterstitialShown: Boolean = false

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this@HomeFragment

        // Components
        configAds()
        configConnection()
        configInstance(savedInstanceState)
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
    }

    /**
     * onSaveInstanceState
     * @param outState Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Ads
            putInt(Constants.KEY_INTERSTITIAL_SHOWING, timesInterstitialShown)
            putBoolean(Constants.KEY_INTERSTITIAL_IS_SHOWING, isInterstitialShown)
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
     * Toolbar
     */
    private fun configToolbar() {
        setHasOptionsMenu(true)
    }

    /**
     * OnConnected
     */
    private fun configConnection() {
        viewModel.internetAccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                showAdInterstitial()
            }
        })
    }

    /**
     * configInstance
     * @param mState Bundle?
     */
    private fun configInstance(mState: Bundle?) {
        if (mState != null) {
            with(mState) {
                // Ads
                timesInterstitialShown = (getInt(Constants.KEY_INTERSTITIAL_SHOWING, 0))
                isInterstitialShown = getBoolean(Constants.KEY_INTERSTITIAL_IS_SHOWING, false)
            }
        }
    }

    /**
     * Advertising
     */
    private fun configAds() {
        //interstitial = AdInterstitial.getInstance(requireContext(), "ca-app-pub-3940256099942544/1033173712")
        //interstitial = AdInterstitial.getInstance(requireContext(), getString(R.string.key_interstitial))
        /**
        interstitial.showInterstitial.observe(viewLifecycleOwner, Observer { result ->
        isInterstitialShown = result
        })
         **/
    }

    /**
     * Config Interstitial
     */
    private fun showAdInterstitial() {
        try {
            val r = Runnable {
                timesInterstitialShown += 1
                if ((timesInterstitialShown % Constants.INTERSTITIAL_ALLOW_SHOW == 0)) {
                    //interstitial.show()
                }
            }
            handler.post(r)
        } catch (ex: Exception) {
            Log.w(TAG, "configInterstitial()[Error] : $ex")
        }
    }

    /**
     * Subscribe
     */
    private fun subscribe() {
        val adapter = TopicAdapter()
        viewModel.getMldTopics()
            .observe(viewLifecycleOwner, Observer {
                configRecycler(it, adapter)
            })
    }

    /**
     * initRecycler
     * @param topics ArrayList<TopicModel>
     */
    private fun configRecycler(topics: List<TopicModel>, topicAdapter: TopicAdapter) {
        topicAdapter.setTopics(topics)
        binding.tcs.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = topicAdapter
        }
    }

}