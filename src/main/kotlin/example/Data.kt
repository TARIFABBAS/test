package com.example.example

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"         ) var id        : Int?    = null,
  @SerializedName("email"      ) var email     : String? = null,
  @SerializedName("first_name" ) var first_name : String? = null,
  @SerializedName("last_name"  ) var last_name  : String? = null,
  @SerializedName("avatar"     ) var avatar    : String? = null

)