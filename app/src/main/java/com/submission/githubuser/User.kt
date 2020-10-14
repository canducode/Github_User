package com.submission.githubuser

import android.os.Parcel
import android.os.Parcelable

data class User (
    var avatar: Int?,
    var name: String?,
    var username: String?,
    var follower: String?,
    var following: String?,
    var company: String?,
    var location: String?,
    var repository: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(avatar)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(follower)
        parcel.writeString(following)
        parcel.writeString(company)
        parcel.writeString(location)
        parcel.writeString(repository)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}