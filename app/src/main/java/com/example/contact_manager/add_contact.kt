package com.example.contact_manager

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_contact : AppCompatActivity() {
    lateinit var database : DatabaseReference
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.background))

        var ok = dialog.findViewById<Button>(R.id.okbtn)
        var btn = findViewById<Button>(R.id.btnAddContact)


        ok.setOnClickListener {

            dialog.dismiss()


        }


        btn.setOnClickListener {

            var name = findViewById<EditText>(R.id.etContactName).text.toString()
            var phone = findViewById<EditText>(R.id.etContactNumber).text.toString()

            if (name.isEmpty() || phone.isEmpty()) {

                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            }
            else{

                var con_add = contact(name,phone)
                database = FirebaseDatabase.getInstance().getReference("contacts")
                database.child(phone).setValue(con_add).addOnSuccessListener {

                    dialog.show()
                    findViewById<EditText>(R.id.etContactName).text.clear()
                    findViewById<EditText>(R.id.etContactNumber).text.clear()


                }.addOnFailureListener {


                    Toast.makeText(this, "check your internet connection", Toast.LENGTH_SHORT).show()

                }


            }



        }


    }
}