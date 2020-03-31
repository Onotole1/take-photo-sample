package com.example.newactivityresult.future

import android.os.Bundle
import androidx.activity.invoke
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.newactivityresult.R
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    private val camera = prepareCall(ActivityResultContracts.TakePicture()) { bitmap ->
        picture.setImageBitmap(bitmap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        cameraButton.setOnClickListener {
            camera()
        }
    }
}
