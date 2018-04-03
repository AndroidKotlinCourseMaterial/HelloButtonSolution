package edu.rosehulman.boutell.hellobutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var numClicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            numClicks++
            updateView()
        }
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
