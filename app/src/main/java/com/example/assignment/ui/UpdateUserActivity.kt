package com.example.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref = firebaseDatabase.reference.child("user")

}