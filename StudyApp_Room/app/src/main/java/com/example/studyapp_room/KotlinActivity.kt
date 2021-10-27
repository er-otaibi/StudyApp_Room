package com.example.studyapp_room

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinActivity : AppCompatActivity() {
    lateinit var etTitle: EditText
    lateinit var etDescription: EditText
    lateinit var etDetails: EditText
    lateinit var add: Button
    lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        supportActionBar!!.hide()

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        etDetails = findViewById(R.id.etDetail)
        add = findViewById(R.id.add)
        rvMain = findViewById(R.id.rvMain)

        LessonDatabase.getInstance(applicationContext)

        updateRV()

        add.setOnClickListener {
            var title = etTitle.text.toString()
            var description = etDescription.text.toString()
            var details = etDetails.text.toString()
            val KotlinLessonOBJ = KotlinLesson(0,title,description,details )
            CoroutineScope(Dispatchers.IO).launch {
                LessonDatabase.getInstance(applicationContext).LessonDao().insertLesson(KotlinLessonOBJ)
                withContext(Dispatchers.Main) {
                    update(LessonDatabase.getInstance(applicationContext).LessonDao().getAllMyLessons())
                }
            }
            etTitle.setText("")
            etDescription.setText("")
            etDetails.setText("")
        }



    }

    private fun update(list: List<KotlinLesson>){
        rvMain.adapter = LessonAdapter(this,list)
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun updateRV(){

        CoroutineScope(Dispatchers.IO).launch {

            var list = LessonDatabase.getInstance(applicationContext).LessonDao().getAllMyLessons()
            update(list)

        }
    }
     fun editAlert(idLesson: Int , title: String, description: String, details: String) {
        val builder = AlertDialog.Builder(this)

        val view = layoutInflater.inflate(R.layout.edit_alert, null)

        builder.setView(view)


        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etDescription = view.findViewById<EditText>(R.id.etDescription)
        val etDetails = view.findViewById<EditText>(R.id.etDetail)
        val edit = view.findViewById<Button>(R.id.edit)

        etTitle.setText(title)
        etDescription.setText(description)
        etDetails.setText(details)



        builder.create().show()


        edit.setOnClickListener {
            var utitle = etTitle.text.toString()
            var udescription = etDescription.text.toString()
            var udetails = etDetails.text.toString()
            CoroutineScope(Dispatchers.IO).launch {

                LessonDatabase.getInstance(applicationContext).LessonDao().updateOBJ(KotlinLesson(idLesson,utitle,udescription,udetails))

                withContext(Dispatchers.Main) {
                    update(LessonDatabase.getInstance(applicationContext).LessonDao().getAllMyLessons())
                }
            }

        }

    }

    fun deleteAlert( id: Int , title: String, description: String, details: String){
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)


        dialogBuilder.setMessage("Confirm delete ?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    _, _ ->

                CoroutineScope(Dispatchers.IO).launch { LessonDatabase.getInstance(applicationContext).LessonDao()
                    .deleteOBJ(KotlinLesson(id,title,description,details))
                    withContext(Dispatchers.Main) {
                        update(LessonDatabase.getInstance(applicationContext).LessonDao().getAllMyLessons())
                    }
                }
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()

        alert.setTitle("Delete Alert")
        alert.show()


    }
}