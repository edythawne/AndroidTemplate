package edy.app.change.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import edy.app.change.BuildConfig
import edy.app.change.R
import edy.app.change.databinding.FragmentAboutBinding

class AboutFragment : Fragment(), View.OnClickListener {
    // TAG
    private val TAG: String = AboutFragment::class.java.name

    // Variables
    private lateinit var binding: FragmentAboutBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        binding.lifecycleOwner = this@AboutFragment

        // Toolbar & Listener
        initToolbar()
        initListener()
        initInfo()

        return initBinding()
    }

    /**
     * OnClick
     * @param v View
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fbc -> openFacebook()
            R.id.ins -> openInstagram()
            R.id.twt -> openTwitter()
            R.id.you -> openYoutube()
            R.id.git -> openGithub()
            R.id.leg -> NavHostFragment.findNavController(this@AboutFragment).navigate(R.id.action_fabout_to_flegal)
        }
    }

    /**
     * Iniciar el ViewModel
     */
    private fun initBinding(): View {
        return binding.root
    }

    /**
     * Information
     */
    private fun initInfo() {
        val info = String.format("%s: v%s", getString(R.string.app_name), BuildConfig.VERSION_NAME)
        binding.ctt.inf.text = info
    }


    /**
     *  Listener
     */
    private fun initListener() {
        binding.ctt.fbc.setOnClickListener(this)
        binding.ctt.ins.setOnClickListener(this)
        binding.ctt.twt.setOnClickListener(this)
        binding.ctt.git.setOnClickListener(this)
        binding.ctt.you.setOnClickListener(this)
        binding.ctt.leg.setOnClickListener(this)
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
     * openTwitter
     * @param view View
     */
    private fun openTwitter() {
        val uApp = Uri.parse("twitter://user?screen_name=arteaprogramar")
        val uBrowser = Uri.parse("https://twitter.com/arteaprogramar")

        try {
            startActivity(Intent(Intent.ACTION_VIEW, uApp))
        } catch (ex: Exception) {
            Log.w(TAG, "Twitter: $ex")
            startActivity(Intent(Intent.ACTION_VIEW, uBrowser))
        }
    }

    /**
     * openFacebook
     * @param view View
     */
    private fun openFacebook() {
        val uApp = Uri.parse("fb://page/104198717968351")
        val uBrowser = Uri.parse("https://www.fb.com/arteaprogramar/")

        try {
            startActivity(Intent(Intent.ACTION_VIEW, uApp))
        } catch (ex: Exception) {
            Log.w(TAG, "Facebook: $ex")
            startActivity(Intent(Intent.ACTION_VIEW, uBrowser))
        }
    }

    /**
     * Open Instagram
     * @param view View
     */
    private fun openInstagram() {
        val uApp = Uri.parse("https://instagram.com/_u/arteaprogramar")
        val uBrowser = Uri.parse("https://instagram.com/arteaprogramar/")

        try {
            startActivity(Intent(Intent.ACTION_VIEW, uApp))
        } catch (ex: Exception) {
            Log.w(TAG, "Instagram: $ex")
            startActivity(Intent(Intent.ACTION_VIEW, uBrowser))
        }
    }


    /**
     * Open Github
     * @param view View
     */
    private fun openGithub() {
        val uApp = Uri.parse("https://github.com/arteaprogramar")

        try {
            startActivity(Intent(Intent.ACTION_VIEW, uApp))
        } catch (ex: Exception) {
            Log.w(TAG, "Github: $ex")
        }
    }

    /**
     * Open Github
     * @param view View
     */
    private fun openYoutube() {
        val uApp = Uri.parse("vnd.youtube.com/channel/UCh94p1M7dg1y9f_Yik1vGjw")
        val uBrowser = Uri.parse("https://www.youtube.com/channel/UCh94p1M7dg1y9f_Yik1vGjw")

        try {
            startActivity(Intent(Intent.ACTION_VIEW, uApp))
        } catch (ex: Exception) {
            Log.w(TAG, "Github: $ex")
            startActivity(Intent(Intent.ACTION_VIEW, uBrowser))
        }
    }

}