package com.example.almacenamientoencache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {

    lateinit var edit_text_id:EditText
    lateinit var boton_guardar:Button
    lateinit var boton_leer:Button
    val nombre_archivo = "archivo"
    var datos :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text_id = findViewById<EditText>(R.id.edit_text_id)
        boton_guardar = findViewById<Button>(R.id.boton_guardar)
        boton_leer = findViewById<Button>(R.id.boton_leer)

        boton_guardar.setOnClickListener {
            guardarArchivo()
        }

        boton_leer.setOnClickListener {
            leerArchivo()
        }

    }

    private fun guardarArchivo(){
        try{
            val archivoGuardado = File(cacheDir, nombre_archivo)
            val fos = FileOutputStream(archivoGuardado)
            datos = edit_text_id.text.toString()
            fos.write(datos.toByteArray())
            fos.close()
            Toast.makeText(this@MainActivity, "Datos guardados", Toast.LENGTH_LONG).show()
            edit_text_id.setText("")
        }catch(e:IOException){
            e.printStackTrace()
        }
    }

    private fun leerArchivo(){

        try{
            val archivoLeido = File(cacheDir, nombre_archivo)
            val fis = FileInputStream(archivoLeido)
            val isr = InputStreamReader(fis)
            val buffer = BufferedReader(isr)
            val builder = StringBuilder()

            var linea:String? = null

            while({linea = buffer.readLine();linea}() != null){
                builder.append(linea)
            }
            fis.close()
            edit_text_id.setText(builder.toString())
            Toast.makeText(this@MainActivity, "Datos recuperaados", Toast.LENGTH_LONG).show()

        }catch(e:IOException){
            e.printStackTrace()
        }
    }
}