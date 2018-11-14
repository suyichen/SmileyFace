package com.syc.smileyface

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val face = findViewById<SmileyFace>(R.id.smileyFace)
        face.setImage(R.drawable.skin_tab_icon_conversation_normal, R.drawable.skin_tab_icon_conversation_selected, R.drawable.rvq, R.drawable.rvr)
        face.setOnMenuClickListener(object : SmileyFace.OnMenuClickListener {
            override fun onItemClick(view: View) {
                Toast.makeText(this@MainActivity, "Click " + face.isHasClick(), Toast.LENGTH_SHORT).show()
            }
        })
    }

}
