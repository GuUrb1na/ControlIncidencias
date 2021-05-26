package mx.itg.controlincidencias.views

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itg.controlincidencias.R
import mx.itg.controlincidencias.adapters.MaestroAdapter
import mx.itg.controlincidencias.rest.Maestro
import mx.itg.controlincidencias.rest.RestEngine
import mx.itg.controlincidencias.rest.maestroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherActivity : AppCompatActivity(), MaestroAdapter.OnItemClickListener {
    lateinit var objeto : Response<List<Maestro>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ControlIncidencias)
        setContentView(R.layout.activity_teacher)
        val search = findViewById<ImageView>(R.id.teacher_search)
        callServiceGetUsers()



    }

    private fun callServiceGetUsers(){
        val service : maestroService = RestEngine.getRestEngine().create(maestroService::class.java)
        val result: Call<List<Maestro>> = service.maestrolistEntities()

    result.enqueue(object : Callback<List<Maestro>>{
        override fun onFailure(call: Call<List<Maestro>>, t: Throwable) {
            Toast.makeText(this@TeacherActivity,"oh no", Toast.LENGTH_LONG).show()
        }

        override fun onResponse(call: Call<List<Maestro>>, response: Response<List<Maestro>>) {
            Toast.makeText(this@TeacherActivity,"oh si", Toast.LENGTH_LONG).show()
            llenarReciclador(response)
            objeto = response
        }

    })
    }

    fun llenarReciclador(r : Response<List<Maestro>>){
        //instancion
        val recyclerView = findViewById<RecyclerView>(R.id.RC_view)
        //cambios esteticos
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recyclerView.hasFixedSize()
        //Asignacion de layout al reciclador
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        //Asignacion de adapter al reciclador
        val adapter = r.body()?.let {  MaestroAdapter(it, this) }
        recyclerView.adapter = adapter
    }


    override fun onItemClick(position: Int) {
        val intent = Intent(this, CrudActivity::class.java)
        intent.putExtra("objeto", objeto.body()?.get(position))
        startActivity(intent)
    }
}