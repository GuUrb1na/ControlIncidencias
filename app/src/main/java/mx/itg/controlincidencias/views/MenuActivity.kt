package mx.itg.controlincidencias.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import mx.itg.controlincidencias.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ControlIncidencias)
        setContentView(R.layout.activity_menu)
    }

    fun goToTeachers(view: View){
        val intent = Intent(this, TeacherActivity::class.java)
        startActivity(intent)

    }

    fun goToReports(view: View){
        val intent = Intent(this, ReportActivity::class.java)
        startActivity(intent)

    }

    fun goToSubjects(view: View){
        val intent = Intent(this, SubjectActivity::class.java)
        startActivity(intent)

    }


}