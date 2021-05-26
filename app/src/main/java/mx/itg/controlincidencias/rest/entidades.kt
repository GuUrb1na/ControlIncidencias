package mx.itg.controlincidencias.rest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


class entidades : ArrayList<Maestro>()

@Parcelize
data class Maestro(
    val Field: String,
    val ID: String,
    val Name: String,
    val Profile: String
) : Parcelable{}

@Parcelize
data class Materia(
    val subject_ID: String,
    val Name: String,
) : Parcelable{}

@Parcelize
data class Reporte(
    val report: String,
    val report_ID: String,
    val subject: String,
    val teacherID: String,
    val time: String
) : Parcelable{}