package com.example.kai.ganki.entity

data class NaviBean(var errorCode: Int,
                    var errorMsg: String,
                    var data: List<Data>) {
    data class Data(var cid: Int,
                    var name: String,
                    var articles: List<Articles>) {
        data class Articles(var apkLink: String,
                            var author: String,
                            var chapterId: Int,
                            var chapterName: String,
                            var collect: Boolean,
                            var courseId: Int,
                            var desc: String,
                            var envelopePic: String,
                            var fresh: Boolean,
                            var id: Int,
                            var link: String,
                            var niceDate: String,
                            var origin: String,
                            var projectLink: String,
                            var publishTime: Long,
                            var superChapterId: Int,
                            var superChapterName: String,
                            var title: String,
                            var type: Int,
                            var userId: Int,
                            var visible: Int,
                            var zan: Int,
                            var tags: List<Any>)
    }
}