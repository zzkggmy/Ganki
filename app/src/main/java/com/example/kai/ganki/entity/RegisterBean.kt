package com.example.kai.ganki.entity

data class RegisterBean(var data: Data,
                        var errorCode: Int,
                        var errorMsg: String) {
    data class Data(var email: String,
                    var icon: String,
                    var id: Int,
                    var password: String,
                    var type: Int,
                    var username: String,
                    var collectIds: List<Any>)
}