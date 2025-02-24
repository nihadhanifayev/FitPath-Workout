package com.example.fitpath.data.model

data class ParentItem(
    val title:String,
    val array:ArrayList<ChildItem>,
    var isExpandable:Boolean=false
)