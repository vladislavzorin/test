package com.zorin.test.network.model

data class DataElement(
    val text:String?,
    val url:String?,
    val selectedId:Int?,
    val variants:List<VariantsSelector>?
)