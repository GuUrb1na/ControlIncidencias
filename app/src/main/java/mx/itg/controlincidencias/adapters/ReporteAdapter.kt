package mx.itg.controlincidencias.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itg.controlincidencias.R
import mx.itg.controlincidencias.rest.Reporte

class ReporteAdapter(private val reportList: List<Reporte>, private val listener: MaestroAdapter.OnItemClickListener) :
    RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cardview_report, parent, false)
        return ReporteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        val currentItem = reportList[position]
        holder.textViewprofesor.text = currentItem.teacherID
        holder.textViewfecha.text = currentItem.time
    }

    override fun getItemCount() = reportList.size

    inner class ReporteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textViewprofesor: TextView = itemView.findViewById(R.id.cv_reportteacher)
        val textViewfecha: TextView = itemView.findViewById(R.id.cv_fecha)

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