package com.xiaomi.nlp;

public class PartOfParseResult
{
  private String leftStr;
  private int nextADDStartPositon;
  private int nextStartPositionInTarget;
  private Boolean parseOK;
  
  public PartOfParseResult(Boolean paramBoolean, int paramInt1, int paramInt2, String paramString)
  {
    parseOK = paramBoolean;
    nextADDStartPositon = paramInt1;
    nextStartPositionInTarget = paramInt2;
    leftStr = paramString;
  }
  
  public String getLeftStr()
  {
    return leftStr;
  }
  
  public int getNextADDStartPositon()
  {
    return nextADDStartPositon;
  }
  
  public int getNextStartPositionInTarget()
  {
    return nextStartPositionInTarget;
  }
  
  public Boolean getParseOK()
  {
    return parseOK;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.PartOfParseResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */