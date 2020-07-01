package edu.rosehulman.boutell.hellobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var numClicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            numClicks++
            updateView()
        }

        // Launch firebase stuff on another thread.
        // When it finishes, call another method.
        // How do we know when it is done?

        // testCoroutine()
        testAnko()


    }

    private fun testAnko() {
        doAsync{
//            val result = "17"
            val result = URL("https://httpbin.org/get").readText()
            uiThread {
                toast(result)
            }
        }
    }

    private fun testCoroutine() {
        GlobalScope.launch {
            // Does something on a different thread.
            // Learn the other library too.
            // launch new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            Log.d("COROUT", "World!") // print after delay
        }
        Log.d("COROUT", "Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    fun updateView() {
        val s = resources.getQuantityString(R.plurals.message_format, numClicks, numClicks)
        message_text_view.text = s
        if (numClicks > 10) {
            // easter egg!
            val color = ContextCompat.getColor(this@MainActivity, R.color.background)
            message_text_view.setTextColor(color)
        }
    }

}
