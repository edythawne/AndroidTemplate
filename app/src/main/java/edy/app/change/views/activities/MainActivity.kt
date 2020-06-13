package edy.app.change.views.activities

//import com.google.android.gms.ads.MobileAds
//import edy.app.tools.advertensing.AdsBanner
//import edy.app.tools.advertensing.AdsInterstitial
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.novoda.merlin.*
import edy.app.change.R
import edy.app.change.databinding.ActivityMainBinding
import edy.app.change.viewmodels.AppViewModel
import edy.app.change.views.BaseApplication
import edy.app.tools.helpers.NotifiersHelper

class MainActivity : BaseApplication(), Connectable, Disconnectable, Bindable {

    // TAG
    private val TAG: String = MainActivity::class.java.name

    // Variables
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this@MainActivity).get(AppViewModel::class.java)
    }

    // Ads Variables
    //private lateinit var banner: AdsBanner
    //private lateinit var interstitial: AdsInterstitial

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
    }

    /**
     * createMerlin
     * @return Merlin?
     */
    override fun createMerlin(): Merlin? {
        return Merlin.Builder().withConnectableCallbacks().withDisconnectableCallbacks()
            .withBindableCallbacks().build(this@MainActivity)
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
        try {
            viewModel.internetAccess.postValue(true)
            // val r = Runnable { configAdView() } runOnUiThread(r)
        } catch (ex: Exception) {
            Log.w(TAG, "onConnect: $ex")
        }
    }

    /**
     * Sin acceso a internet
     */
    override fun onDisconnect() {
        try {
            val r = Runnable {
                viewModel.internetAccess.postValue(false)
                NotifiersHelper.snackbar(this, getString(R.string.internet_not_access), NotifiersHelper.SNACK_LENGTH_LONG)
            }
            runOnUiThread(r)
        } catch (ex: Exception) {
            Log.w(TAG, "onDisconnect [Error]: $ex")
        }
    }

    /**
     * initMobileAds
     */
    private fun configAds() {
        //MobileAds.initialize(this@MainActivity) {}
        //banner = AdsBanner(this@MainActivity)
    }

    /**
     * configAdView
     */
    private fun configAdView() {
        /**
        banner.init(getString(com.novoda.merlin.R.string.key_adview))
        binding.ads.apply {
        visibility = View.VISIBLE
        addView(banner.adView)
        } **/
    }

}
