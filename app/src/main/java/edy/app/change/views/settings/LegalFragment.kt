package edy.app.change.views.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import edy.app.change.R
import edy.app.change.databinding.FragmentLegalBinding
import edy.app.change.utilities.adapters.ThemeAdapter
import edy.app.tools.helpers.WebViewHelper

class LegalFragment : Fragment() {
    // TAG
    private val TAG: String = LegalFragment::class.java.name

    // Databinding Variables
    private lateinit var binding: FragmentLegalBinding

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
        binding = FragmentLegalBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this@LegalFragment

        // WebView Call
        configWebView(savedInstanceState)

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
        onBackPressed()
    }

    /**
     * onViewStateRestored
     * @param savedInstanceState Bundle?
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.run {
            binding.web.restoreState(this)
        }
    }

    /**
     * onSaveInstanceState
     * @param outState Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            binding.web.saveState(outState)
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
     * onBackPressed
     */
    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (binding.web.canGoBack()) {
                binding.web.goBack()
            } else {
                WebViewHelper.destroyWebView(binding.web)
                NavHostFragment.findNavController(requireParentFragment())
                    .navigateUp()
            }
        }
    }

    /**
     * Binding
     * @return View
     */
    private fun configBinding(): View {
        return binding.root
    }

    /**
     * WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun configWebView(savedState: Bundle?) {
        val theme = ThemeAdapter.getNightModeStatus(requireContext())

        binding.web.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    view?.loadUrl("javascript:setTheme('${theme}')")
                    view?.loadUrl("javascript:setAppName('${getString(R.string.app_name)}')")
                }
            }

            if (savedState == null) {
                loadUrl("file:///android_asset/legal/index.html")
            }
        }
    }
}