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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


class UploadImageFragment : Fragment() {
    companion object{
        val GET_FROM_GALLERY = 3
    }
    lateinit var bitmap:Bitmap
    lateinit var img:ImageView
    lateinit var selectedImage:Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)
        val uploadBtn: MaterialButton = view.findViewById(R.id.uploadBtn)
        img = view.findViewById(R.id.imageUpload)
        uploadBtn.setOnClickListener(View.OnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), GET_FROM_GALLERY
            )
        })

        var postBtn:Button = view.findViewById(R.id.postBtn)
        postBtn.setOnClickListener(View.OnClickListener {

        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                selectedImage = data.data!!
            }
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if(bitmap!=null){
            img.setImageBitmap(bitmap)
        }
    }
}