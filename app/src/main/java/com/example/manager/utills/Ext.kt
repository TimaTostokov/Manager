package com.example.manager.utills

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String?){
    Glide.with(this).load(url).into(this)
}
fun Fragment.showToast(message:String){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}