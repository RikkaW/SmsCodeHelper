package com.android.mms.model;

public class ContentRestrictionFactory
{
  private static ContentRestriction sContentRestriction;
  
  public static ContentRestriction getContentRestriction()
  {
    if (sContentRestriction == null) {
      sContentRestriction = new CarrierContentRestriction();
    }
    return sContentRestriction;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.ContentRestrictionFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */