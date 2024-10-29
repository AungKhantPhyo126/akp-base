package com.dev.akp_base

sealed interface MyProducts{
    val productName:String
    val productIcon:Int
    val productStartDate:String
    val productEndDate:String

    data class Bhee(
        override val productName: String,
        override val productIcon: Int,
        override val productStartDate: String,
        override val productEndDate: String,

        val whatIDid : String,
        val myRole : String,
        val techIUsed:String
    ):MyProducts

    data class Windiesel(
        override val productName: String,
        override val productIcon: Int,
        override val productStartDate: String,
        override val productEndDate: String,

        val whatIDid : String,
        val myRole : String,
        val techIUsed:String,
        val apiIntegration:String,
        val challengingTask:String
    ):MyProducts

    data class BetterHR(
        override val productName: String,
        override val productIcon: Int,
        override val productStartDate: String,
        override val productEndDate: String,

        val whatIDid : String,
        val myRole : String,
        val techIUsed:String,
        val apiIntegration:String,
        val challengingTask:String
    ):MyProducts

    data class CityHR(
        override val productName: String,
        override val productIcon: Int,
        override val productStartDate: String,
        override val productEndDate: String,

        val whatIDid : String,
        val myRole : String,
        val techIUsed:String,
        val apiIntegration:String,
        val challengingTask:String
    ):MyProducts

    data class ShweMi(
        override val productName: String,
        override val productIcon: Int,
        override val productStartDate: String,
        override val productEndDate: String,

        val whatIDid : String,
        val myRole : String,
        val techIUsed:String,
        val apiIntegration:String,
        val challengingTask:String
    ):MyProducts

    data class Spotv(
        override val productName: String,
        override val productIcon: Int,
        override val productStartDate: String,
        override val productEndDate: String,

        val whatIDid : String,
        val myRole : String,
        val techIUsed:String,
        val apiIntegration:String,
        val challengingTask:String,
        val platform:List<String>
    ):MyProducts
}

data class MySkill(
    val id:String,
    val name:String,
    val icon:Int
)

val mySkillList = listOf(
    MySkill(
        id = "1",
        name = "Android Studio",
        icon = 1
    ),
    MySkill(
        id = "2",
        name = "Kotlin",
        icon = 2
    ),
    MySkill(
        id = "3",
        name = "Java",
        icon = 3
    ),
    MySkill(
        id = "4",
        name = "MVVM architecture",
        icon = 4
    ),
    MySkill(
        id = "5",
        name = "Multi Module",
        icon = 5
    ),
    MySkill(
        id = "6",
        name = "LiveData",
        icon = 6
    ),
    MySkill(
        id = "7",
        name = "Coroutine Flow",
        icon = 7
    ),
    MySkill(
        id = "8",
        name = "UseCase",
        icon = 8
    ),
    MySkill(
        id = "9",
        name = "Firebase",
        icon = 9
    ),
    MySkill(
        id = "10",
        name = "Jetpack compose",
        icon = 10
    ),
    MySkill(
        id = "11",
        name = "Android Fragment,Activity",
        icon = 11
    ),
    MySkill(
        id = "12",
        name = "Material UI",
        icon = 12
    ),
    MySkill(
        id = "13",
        name = "Retrofit",
        icon = 13
    ),
    MySkill(
        id = "14",
        name = "GraphQL",
        icon = 14
    ),
    MySkill(
        id = "13",
        name = "Retrofit",
        icon = 13
    ),
    MySkill(
        id = "14",
        name = "RxJava",
        icon = 14
    ),
    MySkill(
        id = "15",
        name = "KMP",
        icon = 15
    ),
    MySkill(
        id = "15",
        name = "KMP",
        icon = 15
    ),

)