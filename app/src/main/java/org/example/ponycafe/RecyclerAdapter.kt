package org.example.ponycafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RecyclerAdapter(private val cartList : ArrayList<CartModal>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_cart_item,
        parent, false)
        return MyViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cartList[position]
        holder.cartName.text = currentItem.name
        holder.cartCost.text = currentItem.cost.toString()
        holder.cartQuantity.text = currentItem.quantity.toString()
        holder.cartObserv.text = currentItem.observ
        Picasso.get().load(currentItem.img).into(holder.cartImg)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cartName : TextView = itemView.findViewById(R.id.cartTV1)
        val cartCost : TextView = itemView.findViewById(R.id.cartTV2)
        val cartQuantity : TextView = itemView.findViewById(R.id.itemQuanEt)
        val cartObserv : TextView = itemView.findViewById(R.id.cartNotes)
        val cartImg: ImageView = itemView.findViewById(R.id.cartIV)
    }

}