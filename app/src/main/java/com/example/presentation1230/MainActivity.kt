package com.example.presentation1230

import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.view.Display
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

//    DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
//    Display[] presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
//    if (presentationDisplays.length > 0) {
//        // If there is more than one suitable presentation display, then we could consider
//        // giving the user a choice.  For this example, we simply choose the first display
//        // which is the one the system recommends as the preferred presentation display.
//        Display display = presentationDisplays[0];
//        Presentation presentation = new MyPresentation(context, presentationDisplay);
//        presentation.show();
//    }
var mainPresentation: MainPresentation? = null
    private val displayManager get() = getSystemService(DISPLAY_SERVICE) as DisplayManager
    val mainDisplay: Display get() = displayManager.displays.getOrNull(0) ?: throw Exception("no display index 0")
    val supportDisplay: Display get() = displayManager.displays.getOrNull(1) ?: throw Exception("no display index 1")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            mainPresentation = MainPresentation(this, supportDisplay)
            mainPresentation!!.show()

    }
}