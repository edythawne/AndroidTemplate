package edy.app.change.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import edy.app.change.R
import edy.app.change.adapters.list.GridLayoutAdapter
import edy.app.change.adapters.list.TopicAdapter
import edy.app.change.databinding.FragmentHomeBinding
import edy.app.change.models.TopicModel
import edy.app.change.utils.Keys
import edy.app.change.viewmodels.AppViewModel

class HomeFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    // TAG
    private val TAG: String = HomeFragment::class.java.name

    // Variables
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: AppViewModel by activityViewModels()

    // Ads Variables
    private var showInterstitial: Int = 0
    //private lateinit var interstitial: AdsInterstitial

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
        configAdsInterstitial()
        configConnection()
        configToolbar()
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
    }

    /**
     * onViewStateRestored
     * @param savedInstanceState Bundle?
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.run {
            showInterstitial = getInt(Keys.KEY_INTERSTITIAL_SHOWING, 0)
        }
    }

    /**
     * onSaveInstanceState
     * @param outState Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(Keys.KEY_INTERSTITIAL_SHOWING, showInterstitial)
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
     * initToolbar
     */
    private fun configToolbar() {
        binding.tlr.setOnMenuItemClickListener(this)
        setHasOptionsMenu(true)
    }

    /**
     * Iniciar el ViewModel
     */
    private fun configBinding(): View {
        binding.vm = viewModel
        return binding.root
    }

    /**
     * onMenuItemClick
     * @param item MenuItem
     * @return Boolean
     */
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.item_setting -> {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_fhome_to_fsettings)
                true
            }
            else -> false
        }
    }

    /**
     * subscribe
     */
    @SuppressLint("Recycle")
    private fun subscribe() {
        try {
            val list: ArrayList<TopicModel> = ArrayList()
            val t = resources.getStringArray(R.array.topics)
            val d = resources.getStringArray(R.array.topics_desc)
            val s = resources.obtainTypedArray(R.array.topics_src)
            val n = resources.obtainTypedArray(R.array.topics_destiny)

            for (i in 0 until t.size) {
                list.add(TopicModel(t[i], d[i], n.getResourceId(i, -1), -1, s.getDrawable(i)!!))
            }

            viewModel.getMldTopics(list).observe(viewLifecycleOwner, Observer {
                configRecycler(it)
            })
        } catch (ex: Exception) {
            Log.w(TAG, "Subscribe : ${ex}")
        }
    }

    /**
     * configRecycler
     * @param topics ArrayList<TopicModel>
     */
    private fun configRecycler(topics: ArrayList<TopicModel>) {
        val ma = TopicAdapter(topics)
        binding.ctt.tcs.apply {
            layoutManager = GridLayoutAdapter(requireContext()).getGridLayoutManager()
            itemAnimator = DefaultItemAnimator()
            adapter = ma
        }
        ma.notifyDataSetChanged()
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
        if (showInterstitial <= Keys.INTERSTITIAL_ALLOW_SHOW) {
            //interstitial.init()
        }
        showInterstitial += 1
    }

}