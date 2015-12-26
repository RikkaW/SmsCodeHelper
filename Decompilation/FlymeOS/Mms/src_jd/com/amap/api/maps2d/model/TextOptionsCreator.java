package com.amap.api.maps2d.model;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.mapcore2d.cy;

public class TextOptionsCreator
  implements Parcelable.Creator<TextOptions>
{
  public TextOptions createFromParcel(Parcel paramParcel)
  {
    boolean bool = true;
    TextOptions localTextOptions = new TextOptions();
    Bundle localBundle = paramParcel.readBundle();
    localTextOptions.position(new LatLng(localBundle.getDouble("lat"), localBundle.getDouble("lng")));
    localTextOptions.text(paramParcel.readString());
    localTextOptions.typeface(Typeface.defaultFromStyle(paramParcel.readInt()));
    localTextOptions.rotate(paramParcel.readFloat());
    localTextOptions.align(paramParcel.readInt(), paramParcel.readInt());
    localTextOptions.backgroundColor(paramParcel.readInt());
    localTextOptions.fontColor(paramParcel.readInt());
    localTextOptions.fontSize(paramParcel.readInt());
    localTextOptions.zIndex(paramParcel.readInt());
    if (paramParcel.readByte() == 1) {}
    for (;;)
    {
      localTextOptions.visible(bool);
      paramParcel = paramParcel.readBundle();
      try
      {
        paramParcel = paramParcel.getParcelable("obj");
        if (paramParcel != null) {
          localTextOptions.setObject(paramParcel);
        }
        return localTextOptions;
      }
      catch (Throwable paramParcel)
      {
        cy.a(paramParcel, "TextOptionsCreator", "createFromParcel");
      }
      bool = false;
    }
    return localTextOptions;
  }
  
  public TextOptions[] newArray(int paramInt)
  {
    return new TextOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.TextOptionsCreator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */