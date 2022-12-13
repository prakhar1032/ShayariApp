package com.note.shayariapp.Adapter

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.note.shayariapp.AllShayariActivity
import com.note.shayariapp.BuildConfig
import com.note.shayariapp.Model.ShayariModel
import com.note.shayariapp.R
import com.note.shayariapp.databinding.ItemShayariBinding


class AllShayariAdapter(
    val allShayariActivity: AllShayariActivity,
    val shayariList: ArrayList<ShayariModel>
) : RecyclerView.Adapter<AllShayariAdapter.ShayariViewHolder>() {

    class ShayariViewHolder(val binding: ItemShayariBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {
        return ShayariViewHolder(
            ItemShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {


        if (position % 5 == 0) {
            holder.binding.mainBaground.setBackgroundResource(R.drawable.gradient1)
        } else if (position % 5 == 1) {
            holder.binding.mainBaground.setBackgroundResource(R.drawable.gradient2)
        } else if (position % 5 == 2) {
            holder.binding.mainBaground.setBackgroundResource(R.drawable.gradient3)
        } else if (position % 5 == 3) {
            holder.binding.mainBaground.setBackgroundResource(R.drawable.gradient4)
        } else if (position % 5 == 4) {
            holder.binding.mainBaground.setBackgroundResource(R.drawable.gradient5)
        }

        holder.binding.itemShayari.text = shayariList[position].data.toString()
        holder.binding.btnCopy.setOnClickListener {

            val clipboard: ClipboardManager? =
                allShayariActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayariList[position].data.toString())
            clipboard?.setPrimaryClip(clip)

            Toast.makeText(allShayariActivity,"Shayari Copied Successfully",Toast.LENGTH_SHORT).show()

        }
        holder.binding.btnShare.setOnClickListener {

            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${shayariList[position].data}\n\n"
                shareMessage =
                    """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                          """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayariActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
            true

        }
        holder.binding.btnWhatsapp.setOnClickListener {

            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayariList[position].data.toString())
            try {
                allShayariActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {

            }

        }

    }

    override fun getItemCount() = shayariList.size
}