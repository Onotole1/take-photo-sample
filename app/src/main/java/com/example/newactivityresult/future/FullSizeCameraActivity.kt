package com.example.newactivityresult.future

import android.os.Bundle
import androidx.activity.invoke
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newactivityresult.R
import kotlinx.android.synthetic.main.activity_camera.*

class FullSizeCameraActivity : AppCompatActivity() {

    private val camera = prepareCall(TakeFullSizePicture(this)) { uri ->
        uri ?: return@prepareCall

        Glide.with(this)
            .load(uri)
            .into(picture)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        cameraButton.setOnClickListener {
            camera()
        }
    }
}
