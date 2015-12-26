package com.meizu.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Phonemetadata$PhoneNumberDesc
  implements Externalizable
{
  private static final long serialVersionUID = 1L;
  private String exampleNumber_ = "";
  private boolean hasExampleNumber;
  private boolean hasNationalNumberPattern;
  private boolean hasPossibleNumberPattern;
  private String nationalNumberPattern_ = "";
  private String possibleNumberPattern_ = "";
  
  public static Builder newBuilder()
  {
    return new Builder();
  }
  
  public boolean exactlySameAs(PhoneNumberDesc paramPhoneNumberDesc)
  {
    return (nationalNumberPattern_.equals(nationalNumberPattern_)) && (possibleNumberPattern_.equals(possibleNumberPattern_)) && (exampleNumber_.equals(exampleNumber_));
  }
  
  public String getExampleNumber()
  {
    return exampleNumber_;
  }
  
  public String getNationalNumberPattern()
  {
    return nationalNumberPattern_;
  }
  
  public String getPossibleNumberPattern()
  {
    return possibleNumberPattern_;
  }
  
  public boolean hasExampleNumber()
  {
    return hasExampleNumber;
  }
  
  public boolean hasNationalNumberPattern()
  {
    return hasNationalNumberPattern;
  }
  
  public boolean hasPossibleNumberPattern()
  {
    return hasPossibleNumberPattern;
  }
  
  public PhoneNumberDesc mergeFrom(PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc.hasNationalNumberPattern()) {
      setNationalNumberPattern(paramPhoneNumberDesc.getNationalNumberPattern());
    }
    if (paramPhoneNumberDesc.hasPossibleNumberPattern()) {
      setPossibleNumberPattern(paramPhoneNumberDesc.getPossibleNumberPattern());
    }
    if (paramPhoneNumberDesc.hasExampleNumber()) {
      setExampleNumber(paramPhoneNumberDesc.getExampleNumber());
    }
    return this;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
  {
    if (paramObjectInput.readBoolean()) {
      setNationalNumberPattern(paramObjectInput.readUTF());
    }
    if (paramObjectInput.readBoolean()) {
      setPossibleNumberPattern(paramObjectInput.readUTF());
    }
    if (paramObjectInput.readBoolean()) {
      setExampleNumber(paramObjectInput.readUTF());
    }
  }
  
  public PhoneNumberDesc setExampleNumber(String paramString)
  {
    hasExampleNumber = true;
    exampleNumber_ = paramString;
    return this;
  }
  
  public PhoneNumberDesc setNationalNumberPattern(String paramString)
  {
    hasNationalNumberPattern = true;
    nationalNumberPattern_ = paramString;
    return this;
  }
  
  public PhoneNumberDesc setPossibleNumberPattern(String paramString)
  {
    hasPossibleNumberPattern = true;
    possibleNumberPattern_ = paramString;
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
  {
    paramObjectOutput.writeBoolean(hasNationalNumberPattern);
    if (hasNationalNumberPattern) {
      paramObjectOutput.writeUTF(nationalNumberPattern_);
    }
    paramObjectOutput.writeBoolean(hasPossibleNumberPattern);
    if (hasPossibleNumberPattern) {
      paramObjectOutput.writeUTF(possibleNumberPattern_);
    }
    paramObjectOutput.writeBoolean(hasExampleNumber);
    if (hasExampleNumber) {
      paramObjectOutput.writeUTF(exampleNumber_);
    }
  }
  
  public static final class Builder
    extends Phonemetadata.PhoneNumberDesc
  {
    public Phonemetadata.PhoneNumberDesc build()
    {
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.Phonemetadata.PhoneNumberDesc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */