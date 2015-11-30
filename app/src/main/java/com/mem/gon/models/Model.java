package com.mem.gon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mark on 27/11/15.
 */
public class Model implements Parcelable {
    protected long id;
    protected String createdAt, updatedAt;

    public Model() {
        id = 0;
        createdAt = "";
        updatedAt = "";
    }

    protected Model(Parcel in) {
        id = in.readLong();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }
}
