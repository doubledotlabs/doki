package dev.doubledot.doki.api.models

import android.os.Parcel
import android.os.Parcelable

data class DokiManufacturer(
    val name: String,
    val manufacturer: Array<String>,
    val url: String,
    val award: Int,
    val position: Int,
    val explanation: String,
    val user_solution: String,
    val dev_solution: String?
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (other !is DokiManufacturer) return false
        return other.name == name
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (manufacturer.firstOrNull()?.hashCode() ?: 0)
        result = 31 * result + url.hashCode()
        result = 31 * result + award.hashCode()
        return result
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.createStringArray() ?: arrayOf<String>(),
        parcel.readString().orEmpty(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeStringArray(manufacturer)
        parcel.writeString(url)
        parcel.writeInt(award)
        parcel.writeInt(position)
        parcel.writeString(explanation)
        parcel.writeString(user_solution)
        parcel.writeString(dev_solution)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DokiManufacturer> {
        override fun createFromParcel(parcel: Parcel): DokiManufacturer = DokiManufacturer(parcel)
        override fun newArray(size: Int): Array<DokiManufacturer?> = arrayOfNulls(size)
    }
}
