package es.pmateo.cice.crealistas

import android.app.Application
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_list_detail.*

class ListDetailActivity : AppCompatActivity() {

    lateinit var  list: TaskList
    val listaDataManager = ListDataManager(this )
    lateinit var listsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.IDENTIFICADOR_INTENT)
        title = list.nombre

        fab2.setOnClickListener {
            mostrarListaDialogo()
        }

        val listas = listaDataManager.leerListasGuardadas()
        listsRecyclerView = findViewById(R.id.recyclerDetails)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListDetailRecyclerViewAdapter(listas)
    }

    private fun mostrarListaDialogo() {
        val tituloDialogo = getString(R.string.titulo_dialogo)
        val textoBoton = getString(R.string.crear_lista)

        //Dialog
        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(tituloDialogo)
        builder.setView(listTitleEditText)
        builder.setPositiveButton(textoBoton, {
            dialog, numero ->
            val elemento = listTitleEditText.text.toString()
            listaDataManager.guardarElemento(list, elemento)
            listaDataManager.guardarLista(list)

            //val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            //Añadir la lista al recycler
            //recyclerAdapter.addList(list)

            dialog.dismiss()

        })
        builder.create().show()
    }




}
