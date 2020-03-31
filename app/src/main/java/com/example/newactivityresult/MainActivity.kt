package com.example.newactivityresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newactivityresult.old.CallActivity as OldCallActivity
import com.example.newactivityresult.future.CallActivity as NewCallActivity
import com.example.newactivityresult.old.CameraActivity as OldCameraActivity
import com.example.newactivityresult.future.CameraActivity as NewCameraActivity
import com.example.newactivityresult.future.FullSizeCameraActivity as NewFullSizeActivity
import com.example.newactivityresult.old.FullSizeCameraActivity as OldFullSizeCameraActivity
import kotlinx.android.synthetic.main.activity_main.*
import splitties.activities.start

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        call.setOnClickListener {
            if (futureApi.isChecked) {
                start<NewCallActivity>()
            } else {
                start<OldCallActivity>()
            }
        }

        camera.setOnClickListener {
            if (futureApi.isChecked) {
                start<NewCameraActivity>()
            } else {
                start<OldCameraActivity>()
            }
        }

        fullSizeCamera.setOnClickListener {
            if (futureApi.isChecked) {
                start<NewFullSizeActivity>()
            } else {
                start<OldFullSizeCameraActivity>()
            }
        }
    }
}