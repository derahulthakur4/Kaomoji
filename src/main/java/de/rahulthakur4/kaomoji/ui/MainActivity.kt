package de.rahulthakur4.kaomoji.ui

import android.os.Build
import android.os.Bundle
import de.rahulthakur4.kaomoji.R
import de.rahulthakur4.kaomoji.data.KaomojiDbHelper
import de.rahulthakur4.kaomoji.extensions.transparentStatusBar

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            transparentStatusBar()
        }
        KaomojiDbHelper.instance.openDatabase()
    }
}