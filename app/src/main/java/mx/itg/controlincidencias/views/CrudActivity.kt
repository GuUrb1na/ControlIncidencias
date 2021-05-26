package mx.itg.controlincidencias.views

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import mx.itg.controlincidencias.R
import mx.itg.controlincidencias.adapters.MaestroAdapter
import mx.itg.controlincidencias.adapters.MateriaAdapter
import mx.itg.controlincidencias.rest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CrudActivity : AppCompatActivity(), MaestroAdapter.OnItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        val maestro = intent.getParcelableExtra<Maestro>("objeto")
        val imagen = findViewById<ImageView>(R.id.crud_teacherimagen)
        val nombre = findViewById<TextView>(R.id.crud_teachername)
        val campo = findViewById<TextView>(R.id.crud_teacherfield)
        val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigation.menu.add(Menu.NONE, 1, Menu.NONE, "Editar").setIcon(R.drawable.edit);
        navigation.menu.add(Menu.NONE, 2, Menu.NONE, "Reportar").setIcon(R.drawable.report);
        navigation.menu.add(Menu.NONE, 3, Menu.NONE, "Delete").setIcon(R.drawable.delete);

        Picasso.get().load(maestro.Profile).fit().centerCrop().placeholder(R.drawable.teacher).into(imagen)
        nombre.text = maestro.Name
        campo.text = maestro.Field

        callServiceGetUsers()

    }

    private fun callServiceGetUsers(){
        val service : materiaService = RestEngine.getRestEngine().create(materiaService::class.java)
        val result: Call<List<Materia>> = service.materialistEntities()

        result.enqueue(object : Callback<List<Materia>> {
            override fun onFailure(call: Call<List<Materia>>, t: Throwable) {
                Toast.makeText(this@CrudActivity,"oh no", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Materia>>, response: Response<List<Materia>>) {
                llenarReciclador(response)
            }

        })
    }

    fun llenarReciclador(r : Response<List<Materia>>){
        //instancion
        val recyclerView = findViewById<RecyclerView>(R.id.crud_teachersubject)
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
        Toast.makeText(this@CrudActivity,position, Toast.LENGTH_LONG).show()
    }
}