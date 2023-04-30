package com.example.products.data.network.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ProductColor() : Parcelable {
    @SerializedName("hex_value")
    @Expose
    var hexValue: String? = null

    @SerializedName("colour_name")
    @Expose
    var colourName: String? = null

    constructor(parcel: Parcel) : this() {
        hexValue = parcel.readString()
        colourName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hexValue)
        parcel.writeString(colourName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductColor> {
        override fun createFromParcel(parcel: Parcel): ProductColor {
            return ProductColor(parcel)
        }

        override fun newArray(size: Int): Array<ProductColor?> {
            return arrayOfNulls(size)
        }
    }
}