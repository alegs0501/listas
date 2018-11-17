package es.pmateo.cice.crealistas

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.preference.PreferenceManager

class TaskList constructor(val nombre: String,
               val tareas: ArrayList<String> = ArrayList<String>()): Parcelable {



    constructor(source: Parcel): this(
        source.readString(),
        source.createStringArrayList()

    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nombre)
        dest.writeStringList(tareas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<TaskList> {
        override fun createFromParcel(source: Parcel): TaskList {
            return TaskList(source)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }
}