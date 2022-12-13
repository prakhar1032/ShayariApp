package com.note.shayariapp.Adapter

import android.content.Intent
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.collection.LLRBNode.Color
import com.note.shayariapp.AllShayariActivity
import com.note.shayariapp.MainActivity
import com.note.shayariapp.Model.CategoryModel
import com.note.shayariapp.StartActivity
import com.note.shayariapp.databinding.ItemCategoryBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    val colorList = arrayListOf<String>("#f15bb5", "#D62828", "#F77F00", "#FCBF49", "#EAE2B7")

    class CatViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if (position % 5 == 0) {
            holder.binding.itemTxt.setBackgroundColor(parseColor(colorList[0]))
        } else if (position % 5 == 1) {
            holder.binding.itemTxt.setBackgroundColor(parseColor(colorList[1]))
        } else if (position % 5 == 2) {
            holder.binding.itemTxt.setBackgroundColor(parseColor(colorList[2]))
        } else if (position % 5 == 3) {
            holder.binding.itemTxt.setBackgroundColor(parseColor(colorList[3]))
        } else if (position % 5 == 4) {
            holder.binding.itemTxt.setBackgroundColor(parseColor(colorList[4]))
        }



 holder.binding.itemTxt.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {

            val intent = Intent(mainActivity, AllShayariActivity::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}