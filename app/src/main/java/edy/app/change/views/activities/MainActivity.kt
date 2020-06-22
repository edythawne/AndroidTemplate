package edy.app.change.views.activities

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.novoda.merlin.*
import edy.app.change.R
import edy.app.change.databinding.ActivityMainBinding
import edy.app.change.viewmodels.AppViewModel
import edy.app.change.views.ConnectionActivity
import edy.app.tools.helpers.NotifiersHelper

class MainActivity : ConnectionActivity(), NavController.OnDestinationChangedListener, Connectable, Disconnectable, Bindable {

    // TAG
    private val TAG: String = MainActivity::class.java.name

    // Variables
    private val handler: Handler by lazy { Handler() }
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: AppViewModel by viewModels()

    // Ads Variables
    //private lateinit var banner: AdsBanner

    /**
     * onCreate
     * @param savedInstanceState Bundle?
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this

        // Init Components
        configAds()
        configToolbar()
        configNavListener()
    }

    /**
     * onRestoreInstanceState
     * @param savedInstanceState Bundle
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    /**
     * onSaveInstanceState
     * @param outState Bundle
     * @param outPersistentState PersistableBundle
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    /**
     * onResume
     */
    override fun onResume() {
        super.onResume()
        registerBindable(this)
        registerConnectable(this)
        registerDisconnectable(this)

        //banner.adView.resume()
    }

    /**
     * onPause
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * onDestroy
     */
    override fun onDestroy() {
        super.onDestroy()
        //banner.adView.destroy()
        navController.removeOnDestinationChangedListener(this)
    }


    /**
     * onDestinationChanged
     * @param controller NavController
     * @param destination NavDestination
     * @param arguments Bundle?
     */
    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        Log.d(TAG, "onDestinationChanged | ${destination.label}")
    }

    /**
     * createMerlin
     * @return Merlin?
     */
    override fun createMerlin(): Merlin? {
        return Merlin.Builder()
            .withConnectableCallbacks()
            .withDisconnectableCallbacks()
            .withBindableCallbacks()
            .build(this@MainActivity)
    }

    /**
     * Connection Type
     * @param networkStatus NetworkStatus
     */
    override fun onBind(networkStatus: NetworkStatus?) {
        if (!networkStatus!!.isAvailable) {
            onDisconnect()
        }
    }

    /**
     * Acceso a Internet
     */
    override fun onConnect() {
        viewModel.internetAccess.postValue(true)
        configAdView()
    }

    /**
     * Sin acceso a internet
     */
    override fun onDisconnect() {
        viewModel.internetAccess.postValue(false)
        onDisconnectMessage()
    }

    /**
     * Config Ads
     */
    private fun configAds() {
        //MobileAds.initialize(this)
        //banner = AdBanner(this)
    }

    /**
     * configToolbar
     */
    private fun configToolbar() {
        setSupportActionBar(binding.tlr)
    }

    /**
     * configNavListener
     */
    private fun configNavListener() {
        val appBarConfig = AppBarConfiguration(setOf(R.id.fhome))
        navController = Navigation.findNavController(this, R.id.nav_host)
        NavigationUI.setupWithNavController(binding.tlr, navController, appBarConfig)
    }

    /**
     * configAdView
     */
    private fun configAdView() {
        /**val r = Runnable {
        banner.createAdView(getString(com.novoda.merlin.R.string.key_adview))
        binding.ctt.ads.post {
        binding.ctt.ads.apply {
        removeAllViews()
        visibility = View.VISIBLE
        addView(banner.getAdView())
        }
        }
        }

        handler.post(r) **/
    }

    /**
     * onDisconnectMessage
     */
    private fun onDisconnectMessage() {
        try {
            val r = Runnable {
                NotifiersHelper.snackbar(this, getString(R.string.internet_not_access), NotifiersHelper.SNACK_LENGTH_LONG)
            }
            runOnUiThread(r)
        } catch (ex: Exception) {
            Log.w(TAG, "onDisconnect() [Error]: $ex")
        }
    }

}
