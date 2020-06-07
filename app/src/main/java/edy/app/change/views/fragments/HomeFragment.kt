package edy.app.change.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import edy.app.change.viewmodels.AppViewModel

class HomeFragment : Fragment() {

    // TAG
    private val TAG: String = HomeFragment::class.java.name

    // Variables
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: AppViewModel by activityViewModels()

    /**
     * onCreateView
     * @param inflater LayoutInflater
     * @param container ViewGroup?
     * @param savedInstanceState Bundle?
     * @return View?
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this@HomeFragment

        // Toolbar & Recycler Observer
        initToolbar()
        subscribe()

        return initBinding()
    }

    /**
     * initToolbar
     */
    private fun initToolbar() {
        binding.tlr.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_setting -> {
                    NavHostFragment.findNavController(this).navigate(R.id.action_fhome_to_fsettings)
                    true
                }
                else -> false
            }
        }
        setHasOptionsMenu(true)
    }

    /**
     * Iniciar el ViewModel
     */
    private fun initBinding(): View {
        binding.vm = viewModel
        return binding.root
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
                initRecycler(it)
            })
        } catch (ex: Exception) {
            Log.w(TAG, "Subscribe : ${ex}")
        }
    }

    /**
     * initRecycler
     * @param topics ArrayList<TopicModel>
     */
    private fun initRecycler(topics: ArrayList<TopicModel>) {
        val adapter = TopicAdapter(topics)
        adapter.run {
            binding.ctt.tcs.layoutManager = GridLayoutAdapter(
                requireContext()
            ).getGridLayoutManager()
            binding.ctt.tcs.itemAnimator = DefaultItemAnimator()
            binding.ctt.tcs.adapter = this
            notifyDataSetChanged()
        }
    }

}