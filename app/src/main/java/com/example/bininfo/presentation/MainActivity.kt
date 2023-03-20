package com.example.bininfo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bininfo.R
import com.example.bininfo.presentation.fragments.bininfo.BinInfoFragment
import com.example.bininfo.presentation.fragments.binlist.BinListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.content, BinInfoFragment()).commit()
    }
}