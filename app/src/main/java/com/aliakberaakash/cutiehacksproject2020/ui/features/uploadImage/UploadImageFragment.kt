package com.aliakberaakash.cutiehacksproject2020.ui.features.uploadImage

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.aliakberaakash.cutiehacksproject2020.R
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.uploadimage_layout.*
import java.io.FileNotFoundException
import java.io.IOException


class UploadImageFragment : Fragment() {
    companion object{
        const val GET_FROM_GALLERY = 3
    }
    lateinit var bitmap:Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        choose_image.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), GET_FROM_GALLERY
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = data?.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImage)
                imageUpload.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}