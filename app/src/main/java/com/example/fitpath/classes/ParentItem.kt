package com.example.fitpath.classes

data class ParentItem(
    val title:String,
    val array:ArrayList<ChildItem>,
    var isExpandable:Boolean=false
)