package com.example.evaluacion1.Clases

class ItemMesa(val itemMenu:ItemMenu, val cant:Int){
    fun calcularSubtotal(): Int{
        return cant * itemMenu.precio.toInt()
    }
}