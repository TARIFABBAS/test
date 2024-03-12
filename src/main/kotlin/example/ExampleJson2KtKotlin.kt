package com.example.example

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("page"        ) var page       : Int?            = null,
  @SerializedName("per_page"    ) var per_page    : Int?            = null,
  @SerializedName("total"       ) var total      : Int?            = null,
  @SerializedName("total_pages" ) var total_pages : Int?            = null,
  @SerializedName("data"        ) var data       : ArrayList<Data> = arrayListOf(),
  @SerializedName("support"     ) var support    : Support?        = Support()

)