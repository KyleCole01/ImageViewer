package com.example.imageviewer

import android.widget.TextView
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Context
import android.net.Uri
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val IMAGE_REQUEST_CODE = 1
    var picArray = ArrayList<StoredImage>()
    private lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this


        button_imgselect.setOnClickListener{
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
            if (data != null) {
                val dataUri = data.data as Uri
                val storedImage = StoredImage(dataUri.toString())
                picArray.add(storedImage)
                val listIndex = picArray.indexOf(storedImage)
                createTextView(storedImage.uri, listIndex)
            }
        }
    }


    private fun createTextView(imageText: String, listIndex: Int): TextView {
        val fullIntent = Intent(context,ImageViewer::class.java)
        val textView = TextView(applicationContext)
        parent_layout.addView(textView)
        textView.id = listIndex
        textView.text = imageText
        textView.textSize = 15f
        textView.setPadding(10, 10, 10, 10)
        textView.width = 200
        textView.height = 100
        textView.setOnClickListener{
                val storedImage = StoredImage(imageText)
                fullIntent.putExtra("uri",storedImage)
//                fullIntent.putExtra("index", listIndex)
//                fullIntent.putExtra("name", imageText)
                startActivity(fullIntent)

        }

        return textView
    }}



