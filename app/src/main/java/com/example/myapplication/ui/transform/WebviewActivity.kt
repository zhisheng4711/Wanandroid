package com.example.myapplication.ui.transform

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.just.agentweb.AgentWeb


class WebviewActivity : AppCompatActivity() {

    private var agentWeb: AgentWeb? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val extraData = intent.getStringExtra("url")?:return
        Log.d("接收的链接", "接收的链接是$extraData")
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(findViewById<LinearLayout>(R.id.linear), LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(extraData)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (agentWeb == null)  return super.onKeyDown(keyCode, event)
        return if (agentWeb!!.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        agentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        agentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}