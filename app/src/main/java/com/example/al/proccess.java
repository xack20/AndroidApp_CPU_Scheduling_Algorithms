package com.example.al;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
class proccess implements Parcelable {

    String name;
    double at;
    double bt;
    double opt;


    proccess(String N, double A, double B, double O)
    {
        name = N;
        at = A;
        bt = B;
        opt = O;
    }


    private proccess(Parcel in) {
        name = in.readString();
        at = in.readDouble();
        bt = in.readDouble();
        opt = in.readDouble();
    }

    public static final Creator<proccess> CREATOR = new Creator<proccess>() {
        @Override
        public proccess createFromParcel(Parcel in) {
            return new proccess(in);
        }

        @Override
        public proccess[] newArray(int size) {
            return new proccess[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(at);
        parcel.writeDouble(bt);
        parcel.writeDouble(opt);
    }

}
