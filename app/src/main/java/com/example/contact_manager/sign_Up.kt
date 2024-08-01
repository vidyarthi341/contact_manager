package com.example.contact_manager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class sign_Up : AppCompatActivity() {
    lateinit var database :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btn = findViewById<Button>(R.id.btnSignup)


        btn.setOnClickListener {

            var name = findViewById<EditText>(R.id.etname).text.toString()
            var phone = findViewById<EditText>(R.id.phone).text.toString()
            var email = findViewById<EditText>(R.id.email).text.toString()

            if (email.isEmpty() || phone.isEmpty() || name.isEmpty()) {

                Toast.makeText(this, "Please enter all credentials", Toast.LENGTH_SHORT).show()

            }
            else{

                val users = user(name,phone,email)
                database= FirebaseDatabase.getInstance().getReference("users")
                database.child(phone).setValue(users).addOnSuccessListener {

                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()

                    findViewById<EditText>(R.id.etname).text.clear()
                    findViewById<EditText>(R.id.phone).text.clear()
                    findViewById<EditText>(R.id.email).text.clear()

                    var i = Intent(this, signin::class.java)
                    startActivity(i)


                }.addOnFailureListener {

                    Toast.makeText(this, "check your internet connection", Toast.LENGTH_SHORT).show()

                }

            }


        }



    }
}