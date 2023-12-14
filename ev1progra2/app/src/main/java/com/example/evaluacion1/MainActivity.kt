package com.example.evaluacion1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.example.evaluacion1.Clases.ItemMenu
import com.example.evaluacion1.Clases.ItemMesa
import com.example.evaluacion1.Clases.CuentaMesa
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvValorComida = findViewById<TextView>(R.id.tvValorComida)
        var tvValorPropina = findViewById<TextView>(R.id.tvValorPropina)
        var switchPropina: Switch = findViewById(R.id.switch1)
        var tvValorTotal = findViewById<TextView>(R.id.tvValorTotal)

        val pastel = ItemMenu("Pastel de Choclo", "12000")
        val editText = findViewById<EditText>(R.id.editTextCantidad1)
        val textView = findViewById<TextView>(R.id.tvValorPasteles)
        val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        val cuentaMesa = CuentaMesa(1)

        editText.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val cantidad1 = s.toString().toIntOrNull() ?: 0
                val subtotal = ItemMesa(pastel, cantidad1).calcularSubtotal()
                val subtotalFormateado = format.format(subtotal)
                textView.text = subtotalFormateado

                cuentaMesa.agregarItem(pastel, cantidad1)
                val valComida = cuentaMesa.calcularTotalSinPropina()
                val valComidaFormateada = format.format(valComida)
                tvValorComida.text = valComidaFormateada
            }
        })
        val cazuela = ItemMenu("Cazuela", "10000")
        val editText2 = findViewById<EditText>(R.id.editTextCantidad2)
        val textView2 = findViewById<TextView>(R.id.tvValorCazuelas)
        val cuentaMesa2 = CuentaMesa(2)

        editText2.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val cantidad2 = s.toString().toIntOrNull() ?: 0
                val subtotal2 = ItemMesa(cazuela, cantidad2).calcularSubtotal()
                val subtotal2Formateado = format.format(subtotal2)
                textView2.text = subtotal2Formateado

                cuentaMesa2.agregarItem(cazuela, cantidad2)
                val valComida = cuentaMesa.calcularTotalSinPropina() + cuentaMesa2.calcularTotalSinPropina()
                val valComidaFormateada = format.format(valComida)
                tvValorComida.text = valComidaFormateada
                switchPropina.setOnCheckedChangeListener{_,isChecked ->
                    if (isChecked){
                        val valPropina = valComida/10
                        val valPropinaFormateada = format.format(valPropina)
                        tvValorPropina.text = valPropinaFormateada
                        val valTotal = valComida + valPropina
                        val valTotalFormateado = format.format(valTotal)
                        tvValorTotal.text = valTotalFormateado
                     }
                    else{
                        tvValorPropina.text = "$0."
                        val valTotal = valComida
                        val valTotalFormateado = format.format(valTotal)
                        tvValorTotal.text = valTotalFormateado
                    }
                }
                val valTotal = valComida
                val valTotalFormateado = format.format(valTotal)
                tvValorTotal.text = valTotalFormateado

            }
        })


    }
}