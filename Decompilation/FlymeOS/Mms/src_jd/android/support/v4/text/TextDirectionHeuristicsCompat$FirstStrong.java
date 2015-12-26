package android.support.v4.text;

class TextDirectionHeuristicsCompat$FirstStrong
  implements TextDirectionHeuristicsCompat.TextDirectionAlgorithm
{
  public static final FirstStrong INSTANCE = new FirstStrong();
  
  public int checkRtl(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int j = 2;
    int i = paramInt1;
    while ((i < paramInt1 + paramInt2) && (j == 2))
    {
      j = TextDirectionHeuristicsCompat.access$100(Character.getDirectionality(paramCharSequence.charAt(i)));
      i += 1;
    }
    return j;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.text.TextDirectionHeuristicsCompat.FirstStrong
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */