package es.pmateo.cice.crealistas

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListSelectionRecyclerViewAdapter(val lists: ArrayList<TaskList>,
                                       val _clickListener: ListSelectionRecyclerViewListener):
        RecyclerView.Adapter<ListSelectionViewHolder>() {

    //val titulosLista = arrayOf("Lista de la Compra", "Regalos Reyes", "Cosas por hacer")

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_selection_view_holder,
                p0,
                false)
        return ListSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(p0: ListSelectionViewHolder, p1: Int) {
        if (p0 != null) {
            p0.listPosition.text = (p1 + 1).toString()
            p0.listTitle.text = lists.get(p1).nombre
            p0.itemView.setOnClickListener {
                _clickListener.listItemSeleccionado(lists.get(p1))
            }
        }
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyDataSetChanged()
    }

    interface ListSelectionRecyclerViewListener {
        fun listItemSeleccionado(list: TaskList)
    }
}