package mx.itg.controlincidencias.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itg.controlincidencias.R
import mx.itg.controlincidencias.adapters.MaestroAdapter
import mx.itg.controlincidencias.adapters.MateriaAdapter
import mx.itg.controlincidencias.adapters.ReporteAdapter
import mx.itg.controlincidencias.rest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectActivity : AppCompatActivity(), MaestroAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ControlIncidencias)
        setContentView(R.layout.activity_teacher)
        val search = findViewById<ImageView>(R.id.teacher_search)
        callServiceGetUsers()


    }

    private fun callServiceGetUsers(){
        val service : materiaService = RestEngine.getRestEngine().create(materiaService::class.java)
        val result: Call<List<Materia>> = service.materialistEntities()

        result.enqueue(object : Callback<List<Materia>> {
            override fun onFailure(call: Call<List<Materia>>, t: Throwable) {
                Toast.makeText(this@SubjectActivity,"oh no", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Materia>>, response: Response<List<Materia>>) {
                Toast.makeText(this@SubjectActivity,"oh si", Toast.LENGTH_LONG).show()
                llenarReciclador(response)
            }

        })
    }

    fun llenarReciclador(r : Response<List<Materia>>){
        //instancion
        val recyclerView = findViewById<RecyclerView>(R.id.RC_view)
        //cambios esteticos
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.hasFixedSize()
        //Asignacion de layout al reciclador
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        //Asignacion de adapter al reciclador
        val adapter = r.body()?.let {  MateriaAdapter(it, this) }
        recyclerView.adapter = adapter
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
    }
}