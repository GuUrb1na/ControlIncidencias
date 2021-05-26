package mx.itg.controlincidencias.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itg.controlincidencias.R
import mx.itg.controlincidencias.rest.Materia

class MateriaAdapter(private val materiatList: List<Materia>, private val listener: MaestroAdapter.OnItemClickListener)  : RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaAdapter.MateriaViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cardview_subject, parent, false)
        return MateriaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MateriaAdapter.MateriaViewHolder, position: Int) {
        val currentItem = materiatList[position]
        holder.textViewMateria.text = currentItem.Name
    }

    override fun getItemCount() = materiatList.size

    inner class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textViewMateria: TextView = itemView.findViewById(R.id.cv_subject)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
}