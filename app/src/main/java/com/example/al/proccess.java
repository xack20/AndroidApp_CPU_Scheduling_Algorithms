package com.example.al;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
class proccess implements Parcelable {

    String name;
    double  at;
    double bt;
    int pv =0 ;
    double tq = 0;

    //empty object
    proccess()
    {

    }

    // priority
    proccess(String N, double A, double B, int P)
    {
        name = N;
        at = A;
        bt = B;
        pv = P;
    }


    // fcfs , sjf
    proccess(String N,double A, double B)
    {
        name = N;
        at = A;
        bt = B;
    }



    // rr
    proccess (String N,double A, double B ,double T)
    {
        name = N;
        at = A;
        bt = B;
        tq = T;
    }




    private proccess(Parcel in) {
        name = in.readString();
        at = in.readDouble();
        bt = in.readDouble();
        pv = in.readInt();
        tq = in.readDouble();
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
        parcel.writeInt(pv);
        parcel.writeDouble(tq);
    }

    void set(proccess p)
    {
        name= p.name;
        at = p.at;
        bt = p.bt;
        pv = p.pv;
        tq = p.tq;
    }

}
