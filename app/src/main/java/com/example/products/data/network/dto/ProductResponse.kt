package com.example.products.data.network.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ProductResponse() : Parcelable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("brand")
    @Expose
    var brand: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("price_sign")
    @Expose
    var priceSign: String? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("image_link")
    @Expose
    var imageLink: String? = null

    @SerializedName("product_link")
    @Expose
    var productLink: String? = null

    @SerializedName("website_link")
    @Expose
    var websiteLink: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("rating")
    @Expose
    var rating: Any? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("product_type")
    @Expose
    var productType: String? = null

    @SerializedName("tag_list")
    @Expose
    var tagList: List<String>? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("product_api_url")
    @Expose
    var productApiUrl: String? = null

    @SerializedName("api_featured_image")
    @Expose
    var apiFeaturedImage: String? = null

    @SerializedName("product_colors")
    @Expose
    var productColors: List<ProductColor>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        brand = parcel.readString()
        name = parcel.readString()
        price = parcel.readString()
        priceSign = parcel.readString()
        currency = parcel.readString()
        imageLink = parcel.readString()
        productLink = parcel.readString()
        websiteLink = parcel.readString()
        description = parcel.readString()
        category = parcel.readString()
        productType = parcel.readString()
        tagList = parcel.createStringArrayList()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        productApiUrl = parcel.readString()
        apiFeaturedImage = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(brand)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(priceSign)
        parcel.writeString(currency)
        parcel.writeString(imageLink)
        parcel.writeString(productLink)
        parcel.writeString(websiteLink)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeString(productType)
        parcel.writeStringList(tagList)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(productApiUrl)
        parcel.writeString(apiFeaturedImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductResponse> {
        override fun createFromParcel(parcel: Parcel): ProductResponse {
            return ProductResponse(parcel)
        }

        override fun newArray(size: Int): Array<ProductResponse?> {
            return arrayOfNulls(size)
        }
    }
}