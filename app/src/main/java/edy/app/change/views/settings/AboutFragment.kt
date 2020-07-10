package edy.app.change.views.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import edy.app.change.BuildConfig
import edy.app.change.R
import edy.app.change.databinding.FragmentAboutBinding

class AboutFragment : Fragment(), View.OnClickListener {
    // TAG
    private val TAG: String = AboutFragment::class.java.name

    // Databinding Variables
    private lateinit var binding: FragmentAboutBinding

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
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this@AboutFragment

        // config
        configListener()
        configInformation()

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
            R.id.leg -> NavHostFragment.findNavController(this@AboutFragment)
                .navigate(R.id.action_fabout_to_flegal)
        }
    }

    /**
     * Iniciar el ViewModel
     */
    private fun configBinding(): View {
        return binding.root
    }

    /**
     *  Listener
     */
    private fun configListener() {
        binding.fbc.setOnClickListener(this)
        binding.ins.setOnClickListener(this)
        binding.twt.setOnClickListener(this)
        binding.git.setOnClickListener(this)
        binding.you.setOnClickListener(this)
        binding.leg.setOnClickListener(this)
    }

    /**
     * Information
     */
    private fun configInformation() {
        val info = String.format("%s: v%s", getString(R.string.app_name), BuildConfig.VERSION_NAME)
        binding.inf.text = info
    }

    /**
     * openTwitter
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