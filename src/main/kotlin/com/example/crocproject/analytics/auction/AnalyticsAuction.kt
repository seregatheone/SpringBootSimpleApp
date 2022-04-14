package com.example.crocproject.analytics.auction

import com.example.crocproject.data.models.EmployeeModel
import com.example.crocproject.data.models.DepartmentModel
import com.example.crocproject.data.models.UserModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import krangl.*
import kotlin.math.abs

class AnalyticsAuction {

    private var _dataFrameAuction: DataFrame? = null
    private val dataFrameAuction get() = _dataFrameAuction!!

    fun makeDataframe(list: List<EmployeeModel>) {
        _dataFrameAuction = list.asDataFrame()
    }

    fun workWithDataframe() {
        var k = 0;
        _dataFrameAuction = dataFrameAuction
            .filter { it["isArchive"] eq false }
            .groupBy(by = arrayOf("userDepartment"))
                .summarize(
                    "giniCoefficient" to { gini(it["availableBalance"].toDoubles().toList() as List<Double>).toString() },
                "numberOfStaff" to { it["availableBalance"].length.toString() },
                "maximumBalance" to { it["availableBalance"].max()!!.toInt().toString() },
                "minimumBalance" to { it["availableBalance"].min()!!.toInt().toString() },
                "id" to { k++ },
                "staff" to { staff ->
                    val listOfUsers = mutableListOf<UserModel>()

                    dataFrameAuction
                        .filter { it["isArchive"] eq false }
                        .filter { it["userDepartment"] eq staff["userDepartment"][0]!! }
                        .remove("userDepartment")
                        .remove("id")
                        .rows.forEach { dataFrameRow ->
                            val dataFrameMap = dataFrameRow.toMap()
                            val userModel = UserModel()
                            userModel.apply {
                                userCrocCode = dataFrameMap["userCrocCode"].toString()
                                availableBalance = dataFrameMap["availableBalance"].toString().toInt()
                                userFirstName = dataFrameMap["userFirstName"].toString()
                                userLastName = dataFrameMap["userLastName"].toString()
                                userMiddleName = dataFrameMap["userMiddleName"].toString()
                                userName = dataFrameMap["userName"].toString()

                            }
                            listOfUsers.add(userModel)
                        }
                    GsonBuilder().setPrettyPrinting().create().toJson(
                        listOfUsers
                    )
                },

                "departmentName" to { it["userDepartment"][0] }
            )
            .remove("userDepartment")
    }

    private fun gini(values: List<Double>): Double {
        val sumOfDifference = values.stream()
            .flatMapToDouble { v1: Double ->
                values.stream().mapToDouble { v2: Double -> abs(v1 - v2) }
            }.sum()
        val mean = values.stream().mapToDouble { v -> v }.average().asDouble
        return sumOfDifference / (2 * values.size * values.size * mean)
    }

    @Suppress("UNCHECKED_CAST")
    fun reformatToListResponse(): List<DepartmentModel> {
        val outputDataFrameIterator = dataFrameAuction.rows.iterator()
        val listOfResponses: MutableList<DepartmentModel?> = mutableListOf()
        while (outputDataFrameIterator.hasNext()) {
            val mapOfData = outputDataFrameIterator.next().toMap()
            val sType = object : TypeToken<List<UserModel>>() {}.type
            val staffList: List<UserModel> = Gson().fromJson(mapOfData["staff"].toString(), sType)
            val departmentModel = DepartmentModel(
                id = mapOfData["id"].toString().toInt(),
                staff = staffList.sortedBy { it.availableBalance },
                departmentName = mapOfData["departmentName"].toString(),
                giniCoefficient = "%4.2f".format(mapOfData["giniCoefficient"].toString().toDouble()),
                maximumBalance = mapOfData["maximumBalance"].toString(),
                minimumBalance = mapOfData["minimumBalance"].toString(),
                numberOfStaff = mapOfData["numberOfStaff"].toString()
            )
            listOfResponses.add(departmentModel)
        }
        return listOfResponses.sortedBy { it!!.giniCoefficient }.reversed() as List<DepartmentModel>
    }
}
