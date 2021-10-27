package com.example.studyapp_room

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AndroidActivity : AppCompatActivity() {
    lateinit var etTitle: EditText
    lateinit var etDescription: EditText
    lateinit var etDetails: EditText
    lateinit var add: Button
    lateinit var rvMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
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
            val androidLessonOBJ = AndroidLesson(0,title,description,details )
            CoroutineScope(Dispatchers.IO).launch {
                LessonDatabase.getInstance(applicationContext).AndroidDoa().insertLesson(androidLessonOBJ)
                withContext(Dispatchers.Main) {
                    update(LessonDatabase.getInstance(applicationContext).AndroidDoa().getAllAndroid())
                }
            }
            etTitle.setText("")
            etDescription.setText("")
            etDetails.setText("")
        }
    }

    private fun update(list: List<AndroidLesson>){
        rvMain.adapter = AndroidAdapter(this,list)
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun updateRV(){

        CoroutineScope(Dispatchers.IO).launch {

            var list = LessonDatabase.getInstance(applicationContext).AndroidDoa().getAllAndroid()
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

                LessonDatabase.getInstance(applicationContext).AndroidDoa().updateAndroid(AndroidLesson(idLesson,utitle,udescription,udetails))

                withContext(Dispatchers.Main) {
                    update(LessonDatabase.getInstance(applicationContext).AndroidDoa().getAllAndroid())
                }
            }

        }

    }

    fun deleteAlert( id: Int , title: String, description: String, details: String){
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)


        dialogBuilder.setMessage("Confirm delete ?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    _, _ ->

                CoroutineScope(Dispatchers.IO).launch { LessonDatabase.getInstance(applicationContext).AndroidDoa().deleteAndroid(AndroidLesson(id,title,description,details))
                    withContext(Dispatchers.Main) {
                        update(LessonDatabase.getInstance(applicationContext).AndroidDoa().getAllAndroid())
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