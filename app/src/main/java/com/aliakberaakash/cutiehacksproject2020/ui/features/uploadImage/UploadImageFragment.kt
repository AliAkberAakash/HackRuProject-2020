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
import androidx.fragment.app.Fragment
import com.aliakberaakash.cutiehacksproject2020.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.uploadimage_layout.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.sql.Timestamp
import java.util.*


class UploadImageFragment : Fragment() {
    companion object{
        const val GET_FROM_GALLERY = 3
    }
    lateinit var bitmap:Bitmap
    lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        storage = Firebase.storage


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
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                var storageRef = storage.reference
                val currentTime = Timestamp(System.currentTimeMillis())
                val filename = "$currentTime.jpg"
                val imagesRef = storageRef.child("images/$filename")
                var uploadTask = imagesRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    // Handle unsuccessful uploads
                }.addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}