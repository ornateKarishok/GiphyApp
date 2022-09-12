package com.mycompany.giphyapp.batabase.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val name: String
) : Parcelable {
    constructor(source: Parcel) : this(source.readString().toString(), source.readString().toString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.url)
        dest?.writeString(this.name)
    }

    companion object {
        @JvmField
        final val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {
            override fun createFromParcel(source: Parcel): Image {
                return Image(source)
            }

            override fun newArray(size: Int): Array<Image?> {
                return arrayOfNulls(size)
            }
        }
    }
}
