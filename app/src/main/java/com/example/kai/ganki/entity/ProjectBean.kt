package com.example.kai.ganki.entity

data class ProjectBean(var errorCode: Int,
                       var errorMsg: String,
                       var data: List<Data>) {
    data class Data(var courseId: Int,
                    var id: Int,
                    var name: String,
                    var order: Int,
                    var parentChapterId: Int,
                    var visible: Int,
                    var children: List<Any>)
}