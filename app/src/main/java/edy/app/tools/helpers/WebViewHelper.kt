package edy.app.tools.helpers

import android.webkit.WebView

object WebViewHelper {
    // TAG
    private val TAG: String = WebViewHelper::class.java.name

    /**
     * Destruir el webview
     */
    fun destroyWebView(mWebView: WebView) {
        // Make sure you remove the WebView from its parent view before doing anything.
        mWebView.clearHistory()
        // NOTE: clears RAM cache, if you pass true, it will also clear the disk cache.
        // Probably not a great idea to pass true if you have other WebViews still alive.
        mWebView.clearCache(true)
        // Loading a blank page is optional, but will ensure that the WebView isn't doing anything when you destroy it.
        mWebView.removeAllViews()
        // NOTE: This can occasionally cause a segfault below API 17 (4.2)
        mWebView.destroy()
    }
}