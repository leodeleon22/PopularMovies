package com.leodeleon.popmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCompany implements Parcelable
{

  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private int id;
  public final static Parcelable.Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {


    @SuppressWarnings({
        "unchecked"
    })
    public ProductionCompany createFromParcel(Parcel in) {
      ProductionCompany instance = new ProductionCompany();
      instance.name = ((String) in.readValue((String.class.getClassLoader())));
      instance.id = ((int) in.readValue((int.class.getClassLoader())));
      return instance;
    }

    public ProductionCompany[] newArray(int size) {
      return (new ProductionCompany[size]);
    }

  }
      ;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(name);
    dest.writeValue(id);
  }

  public int describeContents() {
    return 0;
  }

}