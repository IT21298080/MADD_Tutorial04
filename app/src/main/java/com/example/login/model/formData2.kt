package com.example.login.model

import com.example.login.model.validations.validationResult

class formData2(private var studentID:String,
                private var year:String,
                private var Semester:String,
                private var Agree:Boolean, )
     {
    fun validatestudentID(): validationResult{

        return if(studentID.isEmpty()){
            validationResult.empty("Student ID is Empty!")
        }
        else if(studentID.length != 10){
            validationResult.invalid("ID Should Have 10 Digits")
        }
        else if(!studentID.startsWith("IT", true)){
            validationResult.invalid("ID Should Start IT")
        }
        else{
            validationResult.valid
        }

    }

       fun validateYear() : validationResult{

           return if(year.isEmpty()){
               validationResult.invalid("Year is Empty")
           }
           else{
               validationResult.valid
           }
       }

       fun validateSemester() : validationResult{

           return if(Semester.isEmpty()){
               validationResult.invalid("Semester is Empty")
           }
           else{
               validationResult.valid
           }
       }

       fun validateAgree() : validationResult{

           return if(!Agree){
               validationResult.empty("You must agree for the terms & condition")
           }
           else{
               validationResult.valid
           }

       }



   }