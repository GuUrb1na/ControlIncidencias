package mx.itg.controlincidencias.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.itg.controlincidencias.R
import mx.itg.controlincidencias.rest.Maestro

class MaestroAdapter(private val teacherList: List<Maestro>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MaestroAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cardview_teacher, parent, false)
        return TeacherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val currentItem = teacherList[position]
        Picasso.get().load(currentItem.Profile).fit().centerCrop().placeholder(R.drawable.teacher).into(holder.imageView)
        holder.textViewnombre.text = currentItem.Name
        holder.textViewrama.text = currentItem.Field

    }

    override fun getItemCount() = teacherList.size

    inner class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.cv_imagenteacher)
        val textViewnombre: TextView = itemView.findViewById(R.id.cv_teacher)
        val textViewrama: TextView = itemView.findViewById(R.id.cv_rama)

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

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}