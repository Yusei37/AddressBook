package com.example.yusei.addressbook;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private String name;
    private String phoneNumber;
    private int photoID;

    public Person() {
    }

    protected Person(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        photoID = in.readInt();
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, String phoneNumber, int photoID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photoID = photoID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPhotoID() {
        return photoID;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeInt(photoID);
    }
}
