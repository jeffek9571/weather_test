package com.jwisdom.mykotlin.data

data class Info(
    val build_date: String,
    val build_id: String,
    val child: List<Child>,
    val edit_date: String,
    val edit_id: String,
    val id: String,
    val sort: String,
    val switch: String,
    val title: String
)