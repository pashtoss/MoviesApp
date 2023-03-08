package com.petproject.moviesapp.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petproject.moviesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootFragment = RootFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, rootFragment)
            .commit()
    }
}