import android.os.Parcel
import android.os.Parcelable

data class ClasswithDetails(
    val postid: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postid)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClasswithDetails> {
        override fun createFromParcel(parcel: Parcel): ClasswithDetails {
            return ClasswithDetails(parcel)
        }

        override fun newArray(size: Int): Array<ClasswithDetails?> {
            return arrayOfNulls(size)
        }
    }
}

