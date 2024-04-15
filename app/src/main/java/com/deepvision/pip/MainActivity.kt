package com.deepvision.pip

import android.app.PictureInPictureParams
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.deepvision.pip.ui.theme.PIPTheme

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView
//    @android.support.annotation.RequiresApi(Build.VERSION_CODES.O)
//    @android.support.annotation.RequiresApi(Build.VERSION_CODES.O)
//    @android.support.annotation.RequiresApi(Build.VERSION_CODES.O)
//    @android.support.annotation.RequiresApi(Build.VERSION_CODES.O)
//    @android.support.annotation.RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        var button =findViewById<Button>(R.id.my_button)
        webView=findViewById(R.id.micView);
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Load the URL
        val userurl = "https://app.aiish.letstalksign.org/page-app-aiish-story.html"
//        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.userAgentString = "useragentstring"
        webView.getSettings().setSupportMultipleWindows(true);

        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        webView.settings.domStorageEnabled = true
//        if (savedInstanceState == null)
//        {
        webView.loadUrl(userurl)
     //   }

        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                userAgentString = "useragentstring"
                javaScriptCanOpenWindowsAutomatically = true
                domStorageEnabled = true
            }
            loadUrl("https://app.aiish.letstalksign.org/page-app-aiish-story.html")
            webViewClient = WebViewClient()
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                    Log.d("WebView Console.log", "${consoleMessage.sourceId()} ${consoleMessage.lineNumber()} ${consoleMessage.message()}")
                    return true
                }
            }
        }


        // webView.loadUrl("file:///android_asset/webview.html");


        webView.clearCache(true) // Clears the cache, including disk and memory caches.
        webView.clearFormData()  // Clears any stored form data in the WebView.
        webView.clearHistory()
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
//                fileHandler.createAndWriteToFile("INFO "+
//                        "WebView Console.log " + consoleMessage.sourceId() + " " + consoleMessage.lineNumber() + " " + consoleMessage.message(),
//                    fileName
//                );
                Log.d(
                    "WebView Console.log",
                    consoleMessage.sourceId() + " " + consoleMessage.lineNumber() + " " + consoleMessage.message()
                );
                return true
            }
        }
        button.setOnClickListener {
            val width = webView.width
            val height = webView.height
            val aspectRatio = Rational(width, height)
//            val aspectRatio = if (height > width) {
//                Rational(width, height * 2) // Making it artificially taller
//            } else {
//                Rational(width, height)
//            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val params = PictureInPictureParams.Builder()
                    .setAspectRatio(aspectRatio)
                    .build()
                this?.enterPictureInPictureMode(params)
            }
        }

    }
}
