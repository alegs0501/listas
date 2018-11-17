package es.pmateo.cice.crealistas

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
        ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewListener {

    companion object {
        val IDENTIFICADOR_INTENT = "Listados"
    }

    //SharedPreferences

    val ID_INTENT = "Listados"
    lateinit var listsRecyclerView: RecyclerView
    val listaDataManager = ListDataManager(this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            mostrarListaDialogo()
        }

        val listas = listaDataManager.leerListasGuardadas()


        //listsRecyclerView = lists_recyclerView
        listsRecyclerView = findViewById(R.id.lists_recyclerView)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
            //GridLayoutManager
            //StaggeredGridLayoutManager
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(listas, this)
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
            val list = TaskList(listTitleEditText.text.toString())
            listaDataManager.guardarLista(list)

            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            //Añadir la lista al recycler
            recyclerAdapter.addList(list)

            dialog.dismiss()
            mostrarListaDetalle(list)
        })
        builder.create().show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarListaDetalle(list: TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
        listDetailIntent.putExtra(IDENTIFICADOR_INTENT, list)
        startActivity(listDetailIntent)
    }

    override fun listItemSeleccionado(list: TaskList) {
        mostrarListaDetalle(list)
    }
}
