package com.example.login

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.login.model.formData
import com.example.login.model.formData2
import com.example.login.model.validations.validationResult

class MainActivity : AppCompatActivity() {

    lateinit var edtStudentID:EditText
    lateinit var spnYear:Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var btnSubmit:Button
    private var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtStudentID = findViewById(R.id.edt_StudentID)
        spnYear = findViewById(R.id.spn_Year)
        spnSemester = findViewById(R.id.spn_Semester)
        cbAgree = findViewById(R.id.cb_Condition)
        btnSubmit = findViewById(R.id.btn_Submit)

    }

    fun displayAlert (title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok"){dialog,which -> }

        val dialog  = builder.create()
        dialog.show()
    }





    override fun onResume() {
        super.onResume()
        btnSubmit.setOnClickListener(
           View.OnClickListener {
                   count = 0;
               val myform = formData2(edtStudentID.text.toString(),
                                      spnYear.selectedItem.toString(),
                                      spnSemester.selectedItem.toString(),
                                       cbAgree.isChecked )

               val studentIDvalidation = myform.validatestudentID()
               val yearValidation = myform.validateYear()
               val semesterValidation = myform.validateSemester()
               val cbAgreeValidation = myform.validateAgree()


               when (studentIDvalidation){
                   is validationResult.empty ->
                       edtStudentID.error = studentIDvalidation.errorMessage

                   is validationResult.invalid ->
                       edtStudentID.error = studentIDvalidation.errorMessage

                   is validationResult.valid ->
                       count++
               }
               when (yearValidation){

                   is validationResult.empty -> {
                       val tv: TextView = spnYear.selectedView as TextView
                       tv.error
                       tv.text = yearValidation.errorMessage
                   }

                   is validationResult.invalid -> {
                       val tv: TextView = spnYear.selectedView as TextView
                       tv.error
                       tv.text = yearValidation.errorMessage
                   }

                   is validationResult.valid -> {
                      count++;

                   }

               }

               when (semesterValidation){

                   is validationResult.empty -> {
                       val tv: TextView = spnSemester.selectedView as TextView
                       tv.error
                       tv.text = semesterValidation.errorMessage
                   }
                   is validationResult.invalid -> {
                       val tv: TextView = spnSemester.selectedView as TextView
                       tv.error
                       tv.text = semesterValidation.errorMessage
                   }

                   is validationResult.valid -> {
                       count++;

                   }
               }

               when(cbAgreeValidation){

                   is validationResult.empty ->
                   {
                       displayAlert("Error", cbAgreeValidation.errorMessage )
                   }
                   is validationResult.invalid ->
                   {

                   }
                   is validationResult.valid ->
                   {
                       count++
                   }
               }

               if(count == 4){

                   displayAlert("Success", "You have Successfully Registered")
                   val dataobject = formData(edtStudentID.text.toString(),
                   Integer.parseInt(spnYear.selectedItem.toString()),
                   spnSemester.selectedItem.toString(),
                   cbAgree.isChecked)

               }

           }
        )
    }

}