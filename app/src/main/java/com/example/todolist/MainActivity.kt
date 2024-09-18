package com.example.todolist

import android.R.attr.name
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var input: EditText

    private lateinit var newButton: Button
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button
    private lateinit var newBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        input = findViewById<EditText>(R.id.locationEditText)

        newButton = findViewById<Button>(R.id.newButton)
        editButton = findViewById<Button>(R.id.editButton)
        deleteButton = findViewById<Button>(R.id.deleteButton)

        //New button
        newButton.setOnClickListener {
            //I want that when the button is pressed take the input from locationEditText and put it on the new checkbox
            new(input)
        }

        //Edit button
        editButton.setOnClickListener {
            edit(input)
        }

        //Delete button
        deleteButton.setOnClickListener{
            delete()
        }
    }




        private fun new(edit: EditText) {
            // Todo esto es que se ha guardado en el input en el alamacenaje interno y muestra una pequeña notificación.
            val file: String = input.text.toString()
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput("internalstorage.txt", Context.MODE_PRIVATE)
                fileOutputStream.write(file.toByteArray())
                fileOutputStream.close()
                //new(newButton)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, "data save", Toast.LENGTH_SHORT).show()

            val linearLayout = findViewById<LinearLayout>(R.id.linearlayout)

            if (newButton.isClickable) {
                newBox = CheckBox(this)
                newBox.text = input.text.toString()
                //newBox.isChecked = true
                linearLayout.addView(newBox)
                newButton.setTag(newBox)
            } else {
                try {
                    val cb = newButton.tag as CheckBox
                    linearLayout.removeView(cb)
                    newButton.tag = null
                } catch (e: Exception) {
                }
            }
            input.text.clear()
        }

        private fun edit(edit: EditText) {
            /*check.isFocusable = true
            check.isClickable = true
            check.isInEditMode()
            check.text = input.text.toString()*/

            if(newBox.isChecked){
                // Proceso -> Seleccione, escriba en el input, le de editar: lo cambie y lo guarde.
                // De momento solo puedes editar el último añadido, pero con el delete puedes ir eliminando hasta llegar al último
                //o modificar cada nuevo añadido

                newBox.isChecked = false
                newBox.text = input.text.toString()

                val file: String = input.text.toString()
                val fileOutputStream: FileOutputStream
                try {
                    fileOutputStream = openFileOutput("internalstorage.txt", Context.MODE_PRIVATE)
                    fileOutputStream.write(file.toByteArray())
                    fileOutputStream.close()
                    //new(newButton)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Toast.makeText(applicationContext, "Data saved", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Couldn´t save the data", Toast.LENGTH_SHORT).show()
            }
        }

        private fun delete() {

            val linearLayout = findViewById<LinearLayout>(R.id.linearlayout)

            if(newBox.isChecked){
                try {
                    //val cb = newBox.tag as CheckBox
                    linearLayout.removeView(newBox)
                    newBox.tag = null
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
}