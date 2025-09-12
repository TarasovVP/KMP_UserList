package com.tarasovvp.kmpuserlist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tarasovvp.kmpuserlist.android.presentation.App
import com.tarasovvp.kmpuserlist.android.presentation.MyApplicationTheme
import com.tarasovvp.kmpuserlist.di.configureAndroid


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureAndroid(this)
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}