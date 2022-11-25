package org.example.ponycafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso


class GridAdapter(private val context: Context, private var foodList: ArrayList<MenuModal>) : BaseAdapter(), Filterable {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var foodTV: TextView
    private lateinit var foodIV: ImageView

    // below method is use to return the count of course list
    override fun getCount(): Int {
        return foodList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.gridview_food_item, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        foodIV = convertView!!.findViewById(R.id.idIvGrid)
        foodTV = convertView!!.findViewById(R.id.idTvGrid)
        // on below line we are setting image for our course image view.
        //foodIV.setImageResource(foodList[position].img)
        Picasso.get().load(foodList[position].img).into(foodIV)
        // on below line we are setting text in our course text view.
        foodTV.text = foodList[position].name
        // at last we are returning our convert view.
        return convertView
    }

    override fun getFilter(): Filter {
        val filter: Filter = object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults? {
                val filterResults: FilterResults = FilterResults()
                if (p0 == null || (p0.length) == 0) {
                    filterResults.count = foodList.size
                    filterResults.values = foodList
                }else{
                    val searchChr: String = p0.toString().toLowerCase()
                    var searchResult: List<MenuModal> = ArrayList()
                    for (gridViewModal: MenuModal in foodList) {
                        if(gridViewModal.name?.contains(searchChr)!!){
                            searchResult += gridViewModal
                        }
                    }
                    filterResults.count = searchResult.size
                    filterResults.values = searchResult
                }
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                foodList = p1?.values as ArrayList<MenuModal>
                notifyDataSetChanged()
            }
        }
        return filter
    }
    fun filterList(filterlist: ArrayList<MenuModal>) {
        // below line is to add our filtered
        // list in our course array list.
        foodList = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }
}