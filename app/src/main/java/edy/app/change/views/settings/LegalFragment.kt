package edy.app.change.views.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import edy.app.change.R
import edy.app.change.adapters.ThemeAdapter
import edy.app.change.databinding.FragmentLegalBinding

class LegalFragment : Fragment() {
    // TAG
    private val TAG: String = LegalFragment::class.java.name
    private lateinit var binding: FragmentLegalBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_legal, container, false)
        binding.lifecycleOwner = this@LegalFragment

        // Toolbar & WebView Call
        initToolbar()
        webView()

        return initBinding(savedInstanceState)
    }

    /**
     * initToolbar
     */
    private fun initToolbar() {
        binding.tlr.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
    }


    /**
     * initBinding
     * @return View
     */
    private fun initBinding(state: Bundle?): View {
        return binding.root
    }

    /**
     * WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun webView() {
        val theme = ThemeAdapter.getNightModeStatus(requireContext())

        binding.web.settings.javaScriptEnabled = true
        binding.web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                if (view != null) {
                    Log.d(TAG, "Theme : $theme")
                    view.loadUrl("javascript:setTheme('${theme}')")
                }
            }
        }

        binding.web.loadUrl("file:///android_asset/legal.html")
    }
}