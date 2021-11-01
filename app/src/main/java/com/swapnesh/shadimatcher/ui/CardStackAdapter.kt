package com.swapnesh.shadimatcher.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swapnesh.shadimatcher.R
import com.swapnesh.shadimatcher.model.UserListResponce
import com.swapnesh.shadimatcher.model.UserRoom
import java.util.ArrayList

class CardStackAdapter(
    val userList: ArrayList<UserRoom>
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.name.text = "${user.first}. ${user.last}"
        holder.city.text = user.city
        holder.age.text = user.age.toString()
        Glide.with(holder.image)
                .load(user.large)
                .into(holder.image)
        if(user.accept.equals("accept")){
            holder.status.setTextColor(Color.GREEN)
        }
        holder.status.text = user.accept
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var age: TextView = view.findViewById(R.id.item_age)
        val status: TextView = view.findViewById(R.id.item_accept)
        var image: ImageView = view.findViewById(R.id.item_image)
    }

}
