package com.example.newactivityresult.future

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.invoke
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.newactivityresult.R
import com.example.newactivityresult.isGranted
import com.example.newactivityresult.isNeverAskAgain
import kotlinx.android.synthetic.main.activity_call.*
import splitties.toast.toast

class CallActivity : AppCompatActivity() {

    private val callPermission =
        prepareCall(ActivityResultContracts.RequestPermission()) { success ->
            when {
                success -> {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        callButton.setOnClickListener {
            if (isGranted(Manifest.permission.CALL_PHONE)) {
                call()
            } else {
                callPermission(Manifest.permission.CALL_PHONE)
            }
        }
    }

    private fun call() {
        val intent = Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:${phoneField.text}"))
        startActivity(intent)
    }
}
