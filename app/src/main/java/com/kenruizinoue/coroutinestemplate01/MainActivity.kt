package com.kenruizinoue.coroutinestemplate01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadButton.setOnClickListener {
            // this starts coroutines in the IO thread
            // groups all coroutines within this scope
            CoroutineScope(IO).launch {
                startFetch()
            }
        }

    }

    private suspend fun setText(resultText: String) {
        withContext(Main) {
            textView.text = textView.text.toString() + "\n$resultText"
        }
    }

    private suspend fun startFetch() {
        val result = fetchData1()
        // before setText() is called, it waits for fetchData() to finish
        setText(result)

        val result2 = fetchData2()
        setText(result2)
    }

    private suspend fun fetchData1(): String {
        delay(1000)
        return "Result 1"
    }

    private suspend fun fetchData2(): String {
        delay(1000)
        return "Result 2"
    }

}