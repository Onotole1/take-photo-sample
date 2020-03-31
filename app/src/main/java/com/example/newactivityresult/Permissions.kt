package com.example.newactivityresult

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun Activity.isNeverAskAgain(permission: String): Boolean =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission).not()

fun Activity.isGranted(permission: String): Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.requestPermissions(requestCode: Int, vararg permissions: String): Unit =
    ActivityCompat.requestPermissions(this, permissions, requestCode)