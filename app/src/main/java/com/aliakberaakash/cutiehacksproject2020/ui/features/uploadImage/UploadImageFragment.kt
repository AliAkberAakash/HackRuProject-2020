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
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aliakberaakash.cutiehacksproject2020.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.aliakberaakash.cutiehacksproject2020.data.model.Post
import com.aliakberaakash.cutiehacksproject2020.data.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.uploadimage_layout.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.sql.Timestamp


class UploadImageFragment : Fragment() {
    companion object{
        const val GET_FROM_GALLERY = 3
    }
    lateinit var bitmap:Bitmap
    lateinit var img: ImageView
    lateinit var selectedImage:Uri
    lateinit var storage: FirebaseStorage
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)
        val uploadBtn: MaterialButton = view.findViewById(R.id.uploadBtn)
        img = view.findViewById(R.id.imageUpload)
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

        uploadBtn.setOnClickListener {
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

                val post = Post(
                        id = "",
                        user = User(
                                Firebase.auth.currentUser!!.displayName!!,
                                ""
                        ),
                        description = "",
                        image = imagesRef.path
                )
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                db.collection("posts")
                        .add(post)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->

                        }
            }
        }

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
                imageUpload.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}