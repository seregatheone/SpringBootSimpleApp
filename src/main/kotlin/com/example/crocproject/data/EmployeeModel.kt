package com.example.crocproject.data

import java.util.*

data class EmployeeModel(val id : Int,
                         val userCrocCode:String,
//                         private val userName : String,
//                         private val userLastName : String,
//                         private val userFirstName : String,
//                         private val userMiddleName : String,
                         val userDepartment : String,
//                         private val hireDate : Date,
//                         private val email : String,
//                         private val sex : String,
                         val isArchive : Boolean,
//                         private val isRegion : Boolean,
//                         private val location : String,
                         val availableBalance : Int,
//                         private val earnedBalance : Int
                         )