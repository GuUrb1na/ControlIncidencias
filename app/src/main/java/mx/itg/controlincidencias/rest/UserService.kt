package mx.itg.controlincidencias.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface maestroService {
    @GET("recycler.php?entity=teacher")
    fun maestrolistEntities(): Call<List<Maestro>>
}

interface reporteService {
    @GET("recycler.php?entity=report")
    fun reportelistEntities(): Call<List<Reporte>>
}


interface materiaService {
    @GET("recycler.php?entity=subject")
    fun materialistEntities(): Call<List<Materia>>
}

