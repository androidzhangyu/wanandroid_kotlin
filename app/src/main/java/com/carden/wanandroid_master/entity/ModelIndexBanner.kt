package com.carden.wanandroid_master.entity

data class ModelIndexBanner(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)
{

data class Data(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)
}