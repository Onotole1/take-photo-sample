package com.example.newactivityresult.future

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class TakeFullSizePicture(
    private val context: Context
) : ActivityResultContract<Void, Uri?>() {

    private var photoURI: Uri? = null

    override fun createIntent(input: Void?): Intent =
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                // Continue only if the File was successfully created
                val imageFile = createImageFile()
                photoURI = FileProvider.getUriForFile(
                    context,
                    "com.example.android.fileprovider",
                    imageFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            }
        }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? = photoURI

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }
}