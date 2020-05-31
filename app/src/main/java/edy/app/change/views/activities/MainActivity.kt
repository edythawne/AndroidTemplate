package edy.app.change.views.activities

//import com.google.android.gms.ads.MobileAds
//import edy.app.tools.advertensing.AdsBanner
//import edy.app.tools.advertensing.AdsInterstitial
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.novoda.merlin.*
import edy.app.change.R
import edy.app.change.databinding.ActivityMainBinding
import edy.app.change.views.BaseApplication
import edy.app.tools.helpers.NotifiersHelper

class MainActivity : BaseApplication(), Connectable, Disconnectable, Bindable {

    // TAG
    private val TAG: String = MainActivity::class.java.name

    // Ads Variables
    //private lateinit var banner: AdsBanner
    //private lateinit var interstitial: AdsInterstitial

    // Variables
    private lateinit var binding: ActivityMainBinding

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
        initAds()
    }

    /**
     * onResume
     */
    override fun onResume() {
        Log.w(TAG, "onResume()")
        super.onResume()
        registerConnectable(this)
        registerDisconnectable(this)
        registerBindable(this)

        //banner.adView.resume()
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
     * Tipo de Conexion a Internet
     * @param networkStatus [ERROR : NetworkStatus]
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
            /**binding.ads.visibility = View.VISIBLE

            val r = Runnable {
            interstitial.init(getString(R.string.admob_intersticial))
            banner.init(getString(R.string.admob_banner))
            binding.ads.addView(banner.adView)
            }
            runOnUiThread(r)**/
        } catch (ex: Exception) {
            Log.w(TAG, "onConnect: $ex")
        }
    }

    /**
     * Sin acceso a internet
     */
    override fun onDisconnect() {
        val r = Runnable {
            NotifiersHelper.snackbar(
                this@MainActivity,
                getString(R.string.app_name),
                NotifiersHelper.SNACK_LENGTH_LONG
            )
        }

        runOnUiThread(r)
    }

    /**
     * initMobileAds
     */
    private fun initAds() {
        /**MobileAds.initialize(this@MainActivity) {}
        banner = AdsBanner(this@MainActivity)
        interstitial = AdsInterstitial(this@MainActivity)**/
    }

}
