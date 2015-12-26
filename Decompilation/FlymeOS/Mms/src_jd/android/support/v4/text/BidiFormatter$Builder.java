package android.support.v4.text;

import java.util.Locale;

public final class BidiFormatter$Builder
{
  private int mFlags;
  private boolean mIsRtlContext;
  private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;
  
  public BidiFormatter$Builder()
  {
    initialize(BidiFormatter.access$000(Locale.getDefault()));
  }
  
  public BidiFormatter$Builder(Locale paramLocale)
  {
    initialize(BidiFormatter.access$000(paramLocale));
  }
  
  public BidiFormatter$Builder(boolean paramBoolean)
  {
    initialize(paramBoolean);
  }
  
  private static BidiFormatter getDefaultInstanceFromContext(boolean paramBoolean)
  {
    if (paramBoolean) {
      return BidiFormatter.access$200();
    }
    return BidiFormatter.access$300();
  }
  
  private void initialize(boolean paramBoolean)
  {
    mIsRtlContext = paramBoolean;
    mTextDirectionHeuristicCompat = BidiFormatter.access$100();
    mFlags = 2;
  }
  
  public BidiFormatter build()
  {
    if ((mFlags == 2) && (mTextDirectionHeuristicCompat == BidiFormatter.access$100())) {
      return getDefaultInstanceFromContext(mIsRtlContext);
    }
    return new BidiFormatter(mIsRtlContext, mFlags, mTextDirectionHeuristicCompat, null);
  }
  
  public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    mTextDirectionHeuristicCompat = paramTextDirectionHeuristicCompat;
    return this;
  }
  
  public Builder stereoReset(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mFlags |= 0x2;
      return this;
    }
    mFlags &= 0xFFFFFFFD;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.text.BidiFormatter.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */