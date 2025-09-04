package com.AldoP.minimalistcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val operaciones = findViewById<TextView>(R.id.Resultados)
        val visualizacionOp = findViewById<TextView>(R.id.VisualizacionOp)
        val boton0 = findViewById<Button>(R.id.button_Cero)
        val boton1 = findViewById<Button>(R.id.button_Uno)
        val boton2 = findViewById<Button>(R.id.button_Dos)
        val boton3 = findViewById<Button>(R.id.button_Tres)
        val boton4 = findViewById<Button>(R.id.button_Cuatro)
        val boton5 = findViewById<Button>(R.id.button_Cinco)
        val boton6 = findViewById<Button>(R.id.button_Seis)
        val boton7 = findViewById<Button>(R.id.button_Siete)
        val boton8 = findViewById<Button>(R.id.button_Ocho)
        val boton9 = findViewById<Button>(R.id.button_Nueve)
        val botonAC = findViewById<Button>(R.id.Button_AC)
        val botonSuma = findViewById<Button>(R.id.button_Suma)
        val botonRes = findViewById<Button>(R.id.button_Resta)
        val botonIgual = findViewById<Button>(R.id.button_Igual)
        val botonDiv = findViewById<Button>(R.id.button_Division)
        val botonDel = findViewById<Button>(R.id.button_DEL)
        val botonPunto = findViewById<Button>(R.id.button_Punto)
        val botonMulti = findViewById<Button>(R.id.button_Multiplicacion)
        val parentesisIz = findViewById<Button>(R.id.button_ParentesisLeft)
        val parentesisDer = findViewById<Button>(R.id.button_ParentesisRigh)



        fun agregarOperacion(valor: String) {
            val actual = operaciones.text.toString()
            operaciones.text = if (actual == "0") valor else actual + valor
        }

        fun agregarOperacionSimbolos(valor: String){
            val actual = operaciones.text.toString()
            operaciones.text = actual + valor
        }

        fun retrocederOperacion() {
            val actual = operaciones.text.toString()
            if (actual.isNotEmpty()) {
                val nuevo = actual.substring(0, actual.length - 1)
                operaciones.text = nuevo
            }
        }


        boton0.setOnClickListener {agregarOperacion("0")}
        boton1.setOnClickListener {agregarOperacion("1")}
        boton2.setOnClickListener {agregarOperacion("2")}
        boton3.setOnClickListener {agregarOperacion("3")}
        boton4.setOnClickListener {agregarOperacion("4")}
        boton5.setOnClickListener {agregarOperacion("5")}
        boton6.setOnClickListener {agregarOperacion("6")}
        boton7.setOnClickListener {agregarOperacion("7")}
        boton8.setOnClickListener {agregarOperacion("8")}
        boton9.setOnClickListener {agregarOperacion("9")}
        botonSuma.setOnClickListener {agregarOperacionSimbolos("+")}
        botonMulti.setOnClickListener {agregarOperacionSimbolos("*")}
        botonDiv.setOnClickListener {agregarOperacionSimbolos("/")}
        botonRes.setOnClickListener {agregarOperacionSimbolos("-")}
        botonPunto.setOnClickListener {agregarOperacionSimbolos(".")}
        botonDel.setOnClickListener {retrocederOperacion()}
        parentesisIz.setOnClickListener {agregarOperacion("(")}
        parentesisDer.setOnClickListener {agregarOperacion(")")}


        botonIgual.setOnClickListener {
            val input = operaciones.text.toString()
            try {
                val expression = ExpressionBuilder(input).build()
                val result = expression.evaluate()
                operaciones.text = result.toString()
                visualizacionOp.text = input
            } catch (e: Exception) {
                operaciones.text = "Error"
            }
        }

        botonAC.setOnClickListener {
            operaciones.text = "0"
            visualizacionOp.text = ""
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

