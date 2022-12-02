package org.example.ponycafe

data class CartModal(val uid : String? = null, val name : String? = null, val cost : Int? = null, val quantity : Int? = null, val observ : String? = null, val img : String? = null)
//data class MenuModal(val name : String, val img : String, val cost : Int, val desc : String)
data class OrderModal(val uid : String? = null, val prod : String? = null, val cant : String? = null, val total : Int? = null)