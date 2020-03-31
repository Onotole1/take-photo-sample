package com.example.newactivityresult.old

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newactivityresult.R
import com.example.newactivityresult.isGranted
import com.example.newactivityresult.isNeverAskAgain
import com.example.newactivityresult.requestPermissions
import kotlinx.android.synthetic.main.activity_call.*
import splitties.toast.toast

class CallActivity : AppCompatActivity() {

    private companion object {

        const val CALL_PERMISSION_REQUEST_CODE = 434356
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        callButton.setOnClickListener {
            if (isGranted(Manifest.permission.CALL_PHONE)) {
                call()
            } else {
                requestPermissions(
                    CALL_PERMISSION_REQUEST_CODE,
                    Manifest.permission.CALL_PHONE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when {
            requestCode == CALL_PERMISSION_REQUEST_CODE && grantResults.first() == PackageManager.PERMISSION_GRANTED -> {
                call()
            }

            isNeverAskAgain(Manifest.permission.CALL_PHONE) -> {
                toast("Нужно разрешение! Включите его в настройках")
            }

            else -> {
                toast("Нужно разрешение!")
            }
        }
    }

    private fun call() {
        val intent = Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:${phoneField.text}"))
        startActivity(intent)
    }
}
