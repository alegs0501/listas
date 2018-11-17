package es.pmateo.cice.crealistas

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager(val contexto: Context) {
    fun guardarLista(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contexto).edit()
        sharedPreferences.putStringSet(list.nombre, list.tareas.toHashSet())
        sharedPreferences.apply()
    }

    fun leerListasGuardadas(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contexto)
        val sharedPreferencesContenido = sharedPreferences.all
        val taskList = ArrayList<TaskList>()

        for (listaGuardada in sharedPreferencesContenido) {
            val itemHashSet = listaGuardada.value as HashSet<String>
            val lista = TaskList(listaGuardada.key, ArrayList(itemHashSet))
            taskList.add(lista)
        }
        return taskList
    }

    fun guardarElemento(list: TaskList, elemento: String){
        list.tareas.add(elemento);
    }
}