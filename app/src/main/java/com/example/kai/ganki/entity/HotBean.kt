package com.example.kai.ganki.entity

data class HotBean(var errorCode: Int,
                   var errorMsg: String,
                   var data: List<Data>) {
    data class Data(var id: Int,
                    var link: String,
                    var name: String,
                    var order: Int,
                    var visible: Int)
}