package com.note.shayariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.note.shayariapp.Adapter.AllShayariAdapter
import com.note.shayariapp.Model.ShayariModel
import com.note.shayariapp.databinding.ActivityAllShayariBinding

class AllShayariActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayariBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        super.onCreate(savedInstanceState)
        binding = ActivityAllShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.catName.text = name.toString()

        db.collection("Shayari").document(id!!).collection("all")
            .addSnapshotListener { value, error ->

                val shayariList = arrayListOf<ShayariModel>()
                val data = value?.toObjects(ShayariModel::class.java)
                shayariList.addAll(data!!)

                binding.rcvAllShayari.layoutManager = LinearLayoutManager(this)
                binding.rcvAllShayari.adapter = AllShayariAdapter(this, shayariList)
            }


    }
}