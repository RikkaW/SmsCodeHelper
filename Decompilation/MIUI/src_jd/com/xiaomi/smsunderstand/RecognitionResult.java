package com.xiaomi.smsunderstand;

public class RecognitionResult
{
  private double confidence;
  private int endPosition;
  private EntityType entityType;
  private int groupEndPosition;
  private String groupEntity;
  private int groupStartPosition;
  private String parameter;
  private String regularizationResult;
  private int startPosition;
  
  public RecognitionResult(int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityType paramEntityType, double paramDouble, String paramString1, String paramString2, String paramString3)
  {
    startPosition = paramInt1;
    endPosition = paramInt2;
    groupStartPosition = paramInt3;
    groupEndPosition = paramInt4;
    entityType = paramEntityType;
    confidence = paramDouble;
    regularizationResult = paramString1;
    parameter = paramString2;
    groupEntity = paramString3;
  }
  
  public int getEndPosition()
  {
    return endPosition;
  }
  
  public EntityType getEntityType()
  {
    return entityType;
  }
  
  public String getGroupEntity()
  {
    return groupEntity;
  }
  
  public String getParameter()
  {
    return parameter;
  }
  
  public String getRegularizationResult()
  {
    return regularizationResult;
  }
  
  public int getStartPosition()
  {
    return startPosition;
  }
  
  public String toString()
  {
    return " [startPosition=" + startPosition + ", endPosition=" + endPosition + ", regularizationResult=" + regularizationResult + ", entityType=" + entityType + ", parameter=" + parameter + ", confidence=" + confidence + "]";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.RecognitionResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */