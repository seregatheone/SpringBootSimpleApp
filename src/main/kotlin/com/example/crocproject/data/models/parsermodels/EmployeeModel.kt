package com.example.crocproject.data.models.parsermodels

data class EmployeeModel(var id : Int = 0,
                         var userCrocCode:String = "",
                         var userName : String = "",
                         var userLastName : String = "",
                         var userFirstName : String = "",
                         var userMiddleName : String = "",
                         var userDepartment : String = "",
                         var isArchive : Boolean = false,
                         var availableBalance : Int  = 0
)