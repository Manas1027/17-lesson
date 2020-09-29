package com.example.a17_lesson

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_custom_add.*
import kotlinx.android.synthetic.main.dialog_custom_add.view.*

class MainActivity : AppCompatActivity() {

    private val adapter: ListAdapter = ListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RV.adapter = adapter
        RV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        setData(1)
 }

    fun setData(count: Int){
        val models: MutableList<User> = mutableListOf()
        for (i in 0 until count){
            val model = User()
            model.title = "Title ${i+1}"
            model.description = "Description ${i+1}"
            models.add(model)
        }
        adapter.setData(models)
    }

    fun onOptionsButtonClicked(obyekt: View, position: Int){
        val optionsMenu = PopupMenu(this, obyekt)
        val menuInflater = optionsMenu.menuInflater
        menuInflater.inflate(R.menu.menu_item_options, optionsMenu.menu)
        optionsMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add -> {
                    val addDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom_add, null)
                    val dialog = AlertDialog.Builder(this).setView(addDialogView).show()
                    addDialogView.btnPositive.setOnClickListener{
                        adapter.addUser(position+1, addDialogView.etUserTitle.text.toString(), addDialogView.etUserDescription.text.toString())
                        dialog.dismiss()
                    }
                    addDialogView.btnNegative.setOnClickListener {
                        dialog.dismiss()
                    }
                }
                R.id.delete ->{
                    val dialog = AlertDialog.Builder(this)
                        .setTitle("Aniq oshirejaqsanba?")
                        .setMessage("Eger bul itemdi oshirseniz qaytiptikley almaysiz")
                        .setPositiveButton("Awa")
                        {
                                dialog, which -> adapter.removeUser(position)
                        }
                        .setNegativeButton("Yaq")
                        {
                                dialog, which -> dialog.dismiss()
                        }
                    dialog.show()
                }
            }
            return@setOnMenuItemClickListener true
        }
        optionsMenu.show()
    }
}