package com.example.assignment.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.R
import com.example.assignment.databinding.ActivityRegistration2Binding
import com.example.userauthentication.Model.UserModel
import com.example.userauthentication.Utils.ImageUtils
import com.example.userauthentication.ViewModel.UserViewModel
import com.squareup.picasso.Picasso
import java.util.UUID

class RegistrationActivity : AppCompatActivity() {
    lateinit var registrationBinding: ActivityRegistration2Binding

    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    var imageUri : Uri? = null

    lateinit var imageUtils: ImageUtils
    lateinit var userViewmodel : UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        registrationBinding = ActivityRegistration2Binding.inflate(layoutInflater)
        setContentView(registrationBinding.root)

        imageUtils = ImageUtils(this)
        imageUtils.registerActivity { url ->
            url.let {
                imageUri = url
                Picasso.get().load(url).into(registrationBinding.imagebrowse)
            }
        }

        //      registrationBinding.imagebrowse.setOnClickListener{
//            imageUtils.launchGallery(this)
//        }
//        registrationBinding.save.setOnClickListener {
//            if (imageUri != null){
//                uploadImage()
//            }else{
//                Toast.makeText(applicationContext,"Please upload image first",Toast.LENGTH_LONG)
//                    .show()
//            }
//        }

        registrationBinding.buttonregister2.setOnClickListener {
            var name : String = registrationBinding.editname.text.toString()
            var email : String = registrationBinding.editemail.text.toString()
            var number : Int = registrationBinding.editNumber.text.toString().toInt()
            var password : String = registrationBinding.editPassword.text.toString()

            Toast.makeText(applicationContext, "Registration Success", Toast.LENGTH_LONG).show()
        }

        setContentView(R.layout.activity_registration2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun uploadImage(){
        val imageName = UUID.randomUUID().toString()
        imageUri?.let {
            userViewmodel.uploadImage(imageName,it){ success, imageUrl ->
                if (success){
                    addUser(imageUrl.toString(),imageName.toString())
                }
            }

        }

    }
    fun addUser(url:String, imageName: String){
        var name : String = registrationBinding.editname.text.toString()
        var email : String = registrationBinding.editemail.text.toString()
        var number : Int = registrationBinding.editNumber.text.toString().toInt()
        var password : String = registrationBinding.editPassword.text.toString()
        var data = UserModel("",name,email,number,password,url,imageName)


    }
}

