package com.zorin.test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zorin.test.R
import com.zorin.test.network.model.Element
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.android.synthetic.main.item_selector.view.*
import kotlinx.android.synthetic.main.item_text.view.textView

const val ITEM_TEXT = 0
const val ITEM_PICTURE = 1
const val ITEM_SELECTOR = 2

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list:List<Element> = ArrayList()

    fun setData(list:List<Element>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            ITEM_TEXT ->    {
                                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
                                TextViewHolder(view)
                            }

            ITEM_PICTURE->  {
                                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
                                PictureViewHolder(view)
                            }

            else ->         {
                                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_selector, parent, false)
                                SelectorViewHolder(view)
                            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder){
            is TextViewHolder -> holder.bind(list[position])
            is PictureViewHolder -> holder.bind(list[position])
            is SelectorViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position].data.text.isNullOrEmpty()){
            ITEM_SELECTOR
        }else{
            if(list[position].data.url.isNullOrEmpty()){
                ITEM_TEXT
            } else {
                ITEM_PICTURE
            }
        }
    }

    class TextViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        fun bind(element:Element){
            itemView.textView.text = element.data.text

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "${element.name} ${element.data.text}", Toast.LENGTH_LONG).show()
            }
        }
    }

    class PictureViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        fun bind(element:Element){
            itemView.textView.text = element.data.text

            Picasso.get()
                .load(element.data.url)
                .into(itemView.picture)

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "${element.name} ${element.data.text}", Toast.LENGTH_LONG).show()
            }
        }
    }

    class SelectorViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        fun bind(element:Element){

            for (radioItem in element.data.variants!!){
                val rad = RadioButton(itemView.context)
                rad.text = radioItem.text
                rad.id = radioItem.id
                itemView.radioGroup.addView(rad)
            }

            itemView.radioGroup.check(element.data.selectedId!!)

            itemView.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                Toast.makeText(itemView.context,"ID = $checkedId", Toast.LENGTH_LONG).show()
            }
        }
    }
}