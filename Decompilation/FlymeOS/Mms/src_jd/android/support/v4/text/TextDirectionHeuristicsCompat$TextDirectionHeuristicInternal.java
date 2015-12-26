package android.support.v4.text;

class TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal
  extends TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
{
  private final boolean mDefaultIsRtl;
  
  private TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.TextDirectionAlgorithm paramTextDirectionAlgorithm, boolean paramBoolean)
  {
    super(paramTextDirectionAlgorithm);
    mDefaultIsRtl = paramBoolean;
  }
  
  protected boolean defaultIsRtl()
  {
    return mDefaultIsRtl;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.text.TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */