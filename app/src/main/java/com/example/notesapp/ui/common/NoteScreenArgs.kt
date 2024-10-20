package com.example.notesapp.ui.common

import android.os.Parcel
import android.os.Parcelable
import androidx.navigation.NavArgs

data class NoteScreenArgs(val noteId: String) : NavArgs, Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(noteId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteScreenArgs> {
        override fun createFromParcel(parcel: Parcel): NoteScreenArgs {
            return NoteScreenArgs(parcel)
        }

        override fun newArray(size: Int): Array<NoteScreenArgs?> {
            return arrayOfNulls(size)
        }
    }
}