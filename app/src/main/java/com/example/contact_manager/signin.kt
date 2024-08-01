package com.example.contact_manager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {

lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btn  = findViewById<Button>(R.id.btnSignin)
        var signup = findViewById<TextView>(R.id.tvsignup)

        btn.setOnClickListener {

            var phone= findViewById<EditText>(R.id.etphone).text.toString()

            if(phone.isEmpty()){
                Toast.makeText(this, "Please Enter your Phone Number", Toast.LENGTH_SHORT).show()
            }
            else{

            database = FirebaseDatabase.getInstance().getReference("users")
                database.child(phone).get().addOnSuccessListener {

                    if(it.exists()){

                        var i = Intent(this,add_contact::class.java)
                        startActivity(i)

                    }
                    else{

                        Toast.makeText(this, "User does not exist. Please SignUp", Toast.LENGTH_SHORT).show()

                    }

                }.addOnFailureListener {

                    Toast.makeText(this, "checkyour internet connection", Toast.LENGTH_SHORT).show()

                }



            }



        }

        signup.setOnClickListener {

        var i = Intent(this,sign_Up::class.java)
        startActivity(i)

        }

    }
}