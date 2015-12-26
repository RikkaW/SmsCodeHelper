package android.support.v7.internal.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.InputStream;

class ResourcesWrapper
  extends Resources
{
  private final Resources mResources;
  
  public ResourcesWrapper(Resources paramResources)
  {
    super(paramResources.getAssets(), paramResources.getDisplayMetrics(), paramResources.getConfiguration());
    mResources = paramResources;
  }
  
  public XmlResourceParser getAnimation(int paramInt)
  {
    return mResources.getAnimation(paramInt);
  }
  
  public boolean getBoolean(int paramInt)
  {
    return mResources.getBoolean(paramInt);
  }
  
  public int getColor(int paramInt)
  {
    return mResources.getColor(paramInt);
  }
  
  public ColorStateList getColorStateList(int paramInt)
  {
    return mResources.getColorStateList(paramInt);
  }
  
  public Configuration getConfiguration()
  {
    return mResources.getConfiguration();
  }
  
  public float getDimension(int paramInt)
  {
    return mResources.getDimension(paramInt);
  }
  
  public int getDimensionPixelOffset(int paramInt)
  {
    return mResources.getDimensionPixelOffset(paramInt);
  }
  
  public int getDimensionPixelSize(int paramInt)
  {
    return mResources.getDimensionPixelSize(paramInt);
  }
  
  public DisplayMetrics getDisplayMetrics()
  {
    return mResources.getDisplayMetrics();
  }
  
  public Drawable getDrawable(int paramInt)
  {
    return mResources.getDrawable(paramInt);
  }
  
  public Drawable getDrawable(int paramInt, Resources.Theme paramTheme)
  {
    return mResources.getDrawable(paramInt, paramTheme);
  }
  
  public Drawable getDrawableForDensity(int paramInt1, int paramInt2)
  {
    return mResources.getDrawableForDensity(paramInt1, paramInt2);
  }
  
  public Drawable getDrawableForDensity(int paramInt1, int paramInt2, Resources.Theme paramTheme)
  {
    return mResources.getDrawableForDensity(paramInt1, paramInt2, paramTheme);
  }
  
  public float getFraction(int paramInt1, int paramInt2, int paramInt3)
  {
    return mResources.getFraction(paramInt1, paramInt2, paramInt3);
  }
  
  public int getIdentifier(String paramString1, String paramString2, String paramString3)
  {
    return mResources.getIdentifier(paramString1, paramString2, paramString3);
  }
  
  public int[] getIntArray(int paramInt)
  {
    return mResources.getIntArray(paramInt);
  }
  
  public int getInteger(int paramInt)
  {
    return mResources.getInteger(paramInt);
  }
  
  public XmlResourceParser getLayout(int paramInt)
  {
    return mResources.getLayout(paramInt);
  }
  
  public Movie getMovie(int paramInt)
  {
    return mResources.getMovie(paramInt);
  }
  
  public String getQuantityString(int paramInt1, int paramInt2)
  {
    return mResources.getQuantityString(paramInt1, paramInt2);
  }
  
  public String getQuantityString(int paramInt1, int paramInt2, Object... paramVarArgs)
  {
    return mResources.getQuantityString(paramInt1, paramInt2, paramVarArgs);
  }
  
  public CharSequence getQuantityText(int paramInt1, int paramInt2)
  {
    return mResources.getQuantityText(paramInt1, paramInt2);
  }
  
  public String getResourceEntryName(int paramInt)
  {
    return mResources.getResourceEntryName(paramInt);
  }
  
  public String getResourceName(int paramInt)
  {
    return mResources.getResourceName(paramInt);
  }
  
  public String getResourcePackageName(int paramInt)
  {
    return mResources.getResourcePackageName(paramInt);
  }
  
  public String getResourceTypeName(int paramInt)
  {
    return mResources.getResourceTypeName(paramInt);
  }
  
  public String getString(int paramInt)
  {
    return mResources.getString(paramInt);
  }
  
  public String getString(int paramInt, Object... paramVarArgs)
  {
    return mResources.getString(paramInt, paramVarArgs);
  }
  
  public String[] getStringArray(int paramInt)
  {
    return mResources.getStringArray(paramInt);
  }
  
  public CharSequence getText(int paramInt)
  {
    return mResources.getText(paramInt);
  }
  
  public CharSequence getText(int paramInt, CharSequence paramCharSequence)
  {
    return mResources.getText(paramInt, paramCharSequence);
  }
  
  public CharSequence[] getTextArray(int paramInt)
  {
    return mResources.getTextArray(paramInt);
  }
  
  public void getValue(int paramInt, TypedValue paramTypedValue, boolean paramBoolean)
  {
    mResources.getValue(paramInt, paramTypedValue, paramBoolean);
  }
  
  public void getValue(String paramString, TypedValue paramTypedValue, boolean paramBoolean)
  {
    mResources.getValue(paramString, paramTypedValue, paramBoolean);
  }
  
  public void getValueForDensity(int paramInt1, int paramInt2, TypedValue paramTypedValue, boolean paramBoolean)
  {
    mResources.getValueForDensity(paramInt1, paramInt2, paramTypedValue, paramBoolean);
  }
  
  public XmlResourceParser getXml(int paramInt)
  {
    return mResources.getXml(paramInt);
  }
  
  public TypedArray obtainAttributes(AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    return mResources.obtainAttributes(paramAttributeSet, paramArrayOfInt);
  }
  
  public TypedArray obtainTypedArray(int paramInt)
  {
    return mResources.obtainTypedArray(paramInt);
  }
  
  public InputStream openRawResource(int paramInt)
  {
    return mResources.openRawResource(paramInt);
  }
  
  public InputStream openRawResource(int paramInt, TypedValue paramTypedValue)
  {
    return mResources.openRawResource(paramInt, paramTypedValue);
  }
  
  public AssetFileDescriptor openRawResourceFd(int paramInt)
  {
    return mResources.openRawResourceFd(paramInt);
  }
  
  public void parseBundleExtra(String paramString, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    mResources.parseBundleExtra(paramString, paramAttributeSet, paramBundle);
  }
  
  public void parseBundleExtras(XmlResourceParser paramXmlResourceParser, Bundle paramBundle)
  {
    mResources.parseBundleExtras(paramXmlResourceParser, paramBundle);
  }
  
  public void updateConfiguration(Configuration paramConfiguration, DisplayMetrics paramDisplayMetrics)
  {
    super.updateConfiguration(paramConfiguration, paramDisplayMetrics);
    if (mResources != null) {
      mResources.updateConfiguration(paramConfiguration, paramDisplayMetrics);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ResourcesWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */