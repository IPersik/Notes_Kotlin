package com.example.noteskotlin.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

open class NoteData : Parcelable {
    var id:String = ""
    var title: String

    constructor(title: String) {
        this.title = title
        this.id = ""
    }

    protected constructor(ini: Parcel) {
        title = ini.readString()?:""
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
    }

    companion object CREATOR: Creator<NoteData?>  {
            override fun createFromParcel(ini: Parcel): NoteData {
                return NoteData(ini)
            }
            override fun newArray(size: Int): Array<NoteData?> {
                return arrayOfNulls(size)
            }
        }

}
