package com.turskyi.gallery.adapters

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turskyi.gallery.DetailActivity
import com.turskyi.gallery.FileLiveSingleton
import com.turskyi.gallery.R
import com.turskyi.gallery.model.MyFile

class ListRecyclerAdapter(private val aContext: Context, private val aFileList: List<MyFile>) : RecyclerView.Adapter<ListRecyclerAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {

        val viewLarge = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FileViewHolder(viewLarge)
    }

    override fun getItemCount(): Int {
        return aFileList.size
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bindView(aFileList[position],aContext)
    }

    class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fileNameTV: TextView = itemView.findViewById(R.id.file_name)
        var aFileIV: ImageView = itemView.findViewById(R.id.list_iv_preview)
//        private val btnArrowBack: Button = itemView.findViewById(R.id.btn_arrow_back)
        fun bindView(aFile: MyFile, aContext: Context) {
            fileNameTV.text = aFile.name
            itemView.setOnClickListener{
                FileLiveSingleton.getInstance().setPath(aFile.path)

//                btnArrowBack.visibility = View.VISIBLE
            }

            if(aFile.extension in listOf("jpeg", "png","jpg", "JPG")) {
                val aBitmap = BitmapFactory.decodeFile(aFile.path)
                aFileIV.setImageBitmap(aBitmap)

                //to use for opening the picture
                itemView.setOnClickListener {
//                    val btnArrowBack: Button = itemView.findViewById(R.id.btn_arrow_back)
//                    btnArrowBack.visibility = View.VISIBLE
                    val anIntent = Intent(aContext, DetailActivity::class.java)
                    anIntent.putExtra("File", aFile.path)
                    aContext.startActivity(anIntent)
                }
            }
        }
    }
}



