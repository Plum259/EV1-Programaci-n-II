package com.example.evaluacion1.Clases

class CuentaMesa(mesa:Int){
    private var _items:MutableList<ItemMesa> = mutableListOf()
    private var AceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cant: Int){
        val itemMesa = ItemMesa(itemMenu, cant)
        _items.add(itemMesa)
    }
    fun agregarItem(itemMesa:ItemMesa){
        _items.add(itemMesa)
    }
    fun calcularTotalSinPropina():Int{
        var total = 0
        for (item in _items){
            total += item.calcularSubtotal()
        }
        return total
    }
    fun calcularPropina():Int{
        var propina = calcularTotalSinPropina()/10
        return propina
    }
    fun calcularTotalConPropina():Int{
        var totalMasPropina = calcularTotalSinPropina()+calcularPropina()
        return totalMasPropina
    }
}