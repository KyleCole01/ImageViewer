package com.example.imageviewer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        val storedImage = intent.getSerializableExtra("uri") as StoredImage
        img_view.setImageURI(Uri.parse(storedImage.uri))

    }
}
