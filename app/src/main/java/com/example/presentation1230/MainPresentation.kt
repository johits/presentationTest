package com.example.presentation1230

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainPresentation(outerContext: Context?, display: Display?) :
    Presentation(outerContext, display) {
    var secondPresentation: SecondPresentation? = null
    private lateinit var btn: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_presentation)

        btn = findViewById<View>(R.id.btn) as Button
        textView = findViewById<View>(R.id.textView) as TextView
        btn.setOnClickListener {
            textView.text = "클릭됨"
            onDisplayChanged()
        }
    }


}
