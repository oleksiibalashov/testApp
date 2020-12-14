package com.uni.myuniapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uni.myuniapp.R
import kotlinx.android.synthetic.main.my_persong.view.*

class RecAdapter :
    RecyclerView.Adapter<RecAdapter.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    var items = listOf<Person>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.my_persong, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            itemView.firstName.text = person.firstName
            itemView.lastName.text = person.lastName
        }
    }

}

data class Person(
    val firstName: String,
    val lastName: String,
)