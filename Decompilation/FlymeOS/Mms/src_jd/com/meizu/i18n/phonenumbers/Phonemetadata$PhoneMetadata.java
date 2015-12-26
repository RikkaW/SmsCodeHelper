package com.meizu.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Phonemetadata$PhoneMetadata
  implements Externalizable
{
  private static final long serialVersionUID = 1L;
  private int countryCode_ = 0;
  private Phonemetadata.PhoneNumberDesc emergency_ = null;
  private Phonemetadata.PhoneNumberDesc fixedLine_ = null;
  private Phonemetadata.PhoneNumberDesc generalDesc_ = null;
  private boolean hasCountryCode;
  private boolean hasEmergency;
  private boolean hasFixedLine;
  private boolean hasGeneralDesc;
  private boolean hasId;
  private boolean hasInternationalPrefix;
  private boolean hasLeadingDigits;
  private boolean hasLeadingZeroPossible;
  private boolean hasMainCountryForCode;
  private boolean hasMobile;
  private boolean hasNationalPrefix;
  private boolean hasNationalPrefixForParsing;
  private boolean hasNationalPrefixTransformRule;
  private boolean hasNoInternationalDialling;
  private boolean hasPager;
  private boolean hasPersonalNumber;
  private boolean hasPreferredExtnPrefix;
  private boolean hasPreferredInternationalPrefix;
  private boolean hasPremiumRate;
  private boolean hasSameMobileAndFixedLinePattern;
  private boolean hasSharedCost;
  private boolean hasTollFree;
  private boolean hasUan;
  private boolean hasVoicemail;
  private boolean hasVoip;
  private String id_ = "";
  private String internationalPrefix_ = "";
  private List<Phonemetadata.NumberFormat> intlNumberFormat_ = new ArrayList();
  private String leadingDigits_ = "";
  private boolean leadingZeroPossible_ = false;
  private boolean mainCountryForCode_ = false;
  private Phonemetadata.PhoneNumberDesc mobile_ = null;
  private String nationalPrefixForParsing_ = "";
  private String nationalPrefixTransformRule_ = "";
  private String nationalPrefix_ = "";
  private Phonemetadata.PhoneNumberDesc noInternationalDialling_ = null;
  private List<Phonemetadata.NumberFormat> numberFormat_ = new ArrayList();
  private Phonemetadata.PhoneNumberDesc pager_ = null;
  private Phonemetadata.PhoneNumberDesc personalNumber_ = null;
  private String preferredExtnPrefix_ = "";
  private String preferredInternationalPrefix_ = "";
  private Phonemetadata.PhoneNumberDesc premiumRate_ = null;
  private boolean sameMobileAndFixedLinePattern_ = false;
  private Phonemetadata.PhoneNumberDesc sharedCost_ = null;
  private Phonemetadata.PhoneNumberDesc tollFree_ = null;
  private Phonemetadata.PhoneNumberDesc uan_ = null;
  private Phonemetadata.PhoneNumberDesc voicemail_ = null;
  private Phonemetadata.PhoneNumberDesc voip_ = null;
  
  public static Builder newBuilder()
  {
    return new Builder();
  }
  
  public PhoneMetadata addIntlNumberFormat(Phonemetadata.NumberFormat paramNumberFormat)
  {
    if (paramNumberFormat == null) {
      throw new NullPointerException();
    }
    intlNumberFormat_.add(paramNumberFormat);
    return this;
  }
  
  public PhoneMetadata addNumberFormat(Phonemetadata.NumberFormat paramNumberFormat)
  {
    if (paramNumberFormat == null) {
      throw new NullPointerException();
    }
    numberFormat_.add(paramNumberFormat);
    return this;
  }
  
  public PhoneMetadata clearIntlNumberFormat()
  {
    intlNumberFormat_.clear();
    return this;
  }
  
  public int getCountryCode()
  {
    return countryCode_;
  }
  
  public Phonemetadata.PhoneNumberDesc getEmergency()
  {
    return emergency_;
  }
  
  public Phonemetadata.PhoneNumberDesc getFixedLine()
  {
    return fixedLine_;
  }
  
  public Phonemetadata.PhoneNumberDesc getGeneralDesc()
  {
    return generalDesc_;
  }
  
  public String getId()
  {
    return id_;
  }
  
  public String getInternationalPrefix()
  {
    return internationalPrefix_;
  }
  
  public Phonemetadata.NumberFormat getIntlNumberFormat(int paramInt)
  {
    return (Phonemetadata.NumberFormat)intlNumberFormat_.get(paramInt);
  }
  
  public String getLeadingDigits()
  {
    return leadingDigits_;
  }
  
  public boolean getMainCountryForCode()
  {
    return mainCountryForCode_;
  }
  
  public Phonemetadata.PhoneNumberDesc getMobile()
  {
    return mobile_;
  }
  
  public String getNationalPrefix()
  {
    return nationalPrefix_;
  }
  
  public String getNationalPrefixForParsing()
  {
    return nationalPrefixForParsing_;
  }
  
  public String getNationalPrefixTransformRule()
  {
    return nationalPrefixTransformRule_;
  }
  
  public Phonemetadata.PhoneNumberDesc getNoInternationalDialling()
  {
    return noInternationalDialling_;
  }
  
  public Phonemetadata.NumberFormat getNumberFormat(int paramInt)
  {
    return (Phonemetadata.NumberFormat)numberFormat_.get(paramInt);
  }
  
  public Phonemetadata.PhoneNumberDesc getPager()
  {
    return pager_;
  }
  
  public Phonemetadata.PhoneNumberDesc getPersonalNumber()
  {
    return personalNumber_;
  }
  
  public String getPreferredExtnPrefix()
  {
    return preferredExtnPrefix_;
  }
  
  public String getPreferredInternationalPrefix()
  {
    return preferredInternationalPrefix_;
  }
  
  public Phonemetadata.PhoneNumberDesc getPremiumRate()
  {
    return premiumRate_;
  }
  
  public Phonemetadata.PhoneNumberDesc getSharedCost()
  {
    return sharedCost_;
  }
  
  public Phonemetadata.PhoneNumberDesc getTollFree()
  {
    return tollFree_;
  }
  
  public Phonemetadata.PhoneNumberDesc getUan()
  {
    return uan_;
  }
  
  public Phonemetadata.PhoneNumberDesc getVoicemail()
  {
    return voicemail_;
  }
  
  public Phonemetadata.PhoneNumberDesc getVoip()
  {
    return voip_;
  }
  
  public boolean hasCountryCode()
  {
    return hasCountryCode;
  }
  
  public boolean hasEmergency()
  {
    return hasEmergency;
  }
  
  public boolean hasFixedLine()
  {
    return hasFixedLine;
  }
  
  public boolean hasGeneralDesc()
  {
    return hasGeneralDesc;
  }
  
  public boolean hasId()
  {
    return hasId;
  }
  
  public boolean hasInternationalPrefix()
  {
    return hasInternationalPrefix;
  }
  
  public boolean hasLeadingDigits()
  {
    return hasLeadingDigits;
  }
  
  public boolean hasLeadingZeroPossible()
  {
    return hasLeadingZeroPossible;
  }
  
  public boolean hasMainCountryForCode()
  {
    return hasMainCountryForCode;
  }
  
  public boolean hasMobile()
  {
    return hasMobile;
  }
  
  public boolean hasNationalPrefix()
  {
    return hasNationalPrefix;
  }
  
  public boolean hasNationalPrefixForParsing()
  {
    return hasNationalPrefixForParsing;
  }
  
  public boolean hasNationalPrefixTransformRule()
  {
    return hasNationalPrefixTransformRule;
  }
  
  public boolean hasNoInternationalDialling()
  {
    return hasNoInternationalDialling;
  }
  
  public boolean hasPager()
  {
    return hasPager;
  }
  
  public boolean hasPersonalNumber()
  {
    return hasPersonalNumber;
  }
  
  public boolean hasPreferredExtnPrefix()
  {
    return hasPreferredExtnPrefix;
  }
  
  public boolean hasPreferredInternationalPrefix()
  {
    return hasPreferredInternationalPrefix;
  }
  
  public boolean hasPremiumRate()
  {
    return hasPremiumRate;
  }
  
  public boolean hasSameMobileAndFixedLinePattern()
  {
    return hasSameMobileAndFixedLinePattern;
  }
  
  public boolean hasSharedCost()
  {
    return hasSharedCost;
  }
  
  public boolean hasTollFree()
  {
    return hasTollFree;
  }
  
  public boolean hasUan()
  {
    return hasUan;
  }
  
  public boolean hasVoicemail()
  {
    return hasVoicemail;
  }
  
  public boolean hasVoip()
  {
    return hasVoip;
  }
  
  public int intlNumberFormatSize()
  {
    return intlNumberFormat_.size();
  }
  
  public List<Phonemetadata.NumberFormat> intlNumberFormats()
  {
    return intlNumberFormat_;
  }
  
  public boolean isLeadingZeroPossible()
  {
    return leadingZeroPossible_;
  }
  
  public boolean isMainCountryForCode()
  {
    return mainCountryForCode_;
  }
  
  public boolean isSameMobileAndFixedLinePattern()
  {
    return sameMobileAndFixedLinePattern_;
  }
  
  public int numberFormatSize()
  {
    return numberFormat_.size();
  }
  
  public List<Phonemetadata.NumberFormat> numberFormats()
  {
    return numberFormat_;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
  {
    int j = 0;
    Object localObject;
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setGeneralDesc((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setFixedLine((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setMobile((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setTollFree((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setPremiumRate((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setSharedCost((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setPersonalNumber((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setVoip((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setPager((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setUan((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setVoicemail((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setEmergency((Phonemetadata.PhoneNumberDesc)localObject);
    }
    if (paramObjectInput.readBoolean())
    {
      localObject = new Phonemetadata.PhoneNumberDesc();
      ((Phonemetadata.PhoneNumberDesc)localObject).readExternal(paramObjectInput);
      setNoInternationalDialling((Phonemetadata.PhoneNumberDesc)localObject);
    }
    setId(paramObjectInput.readUTF());
    setCountryCode(paramObjectInput.readInt());
    setInternationalPrefix(paramObjectInput.readUTF());
    if (paramObjectInput.readBoolean()) {
      setPreferredInternationalPrefix(paramObjectInput.readUTF());
    }
    if (paramObjectInput.readBoolean()) {
      setNationalPrefix(paramObjectInput.readUTF());
    }
    if (paramObjectInput.readBoolean()) {
      setPreferredExtnPrefix(paramObjectInput.readUTF());
    }
    if (paramObjectInput.readBoolean()) {
      setNationalPrefixForParsing(paramObjectInput.readUTF());
    }
    if (paramObjectInput.readBoolean()) {
      setNationalPrefixTransformRule(paramObjectInput.readUTF());
    }
    setSameMobileAndFixedLinePattern(paramObjectInput.readBoolean());
    int k = paramObjectInput.readInt();
    int i = 0;
    while (i < k)
    {
      localObject = new Phonemetadata.NumberFormat();
      ((Phonemetadata.NumberFormat)localObject).readExternal(paramObjectInput);
      numberFormat_.add(localObject);
      i += 1;
    }
    k = paramObjectInput.readInt();
    i = j;
    while (i < k)
    {
      localObject = new Phonemetadata.NumberFormat();
      ((Phonemetadata.NumberFormat)localObject).readExternal(paramObjectInput);
      intlNumberFormat_.add(localObject);
      i += 1;
    }
    setMainCountryForCode(paramObjectInput.readBoolean());
    if (paramObjectInput.readBoolean()) {
      setLeadingDigits(paramObjectInput.readUTF());
    }
    setLeadingZeroPossible(paramObjectInput.readBoolean());
  }
  
  public PhoneMetadata setCountryCode(int paramInt)
  {
    hasCountryCode = true;
    countryCode_ = paramInt;
    return this;
  }
  
  public PhoneMetadata setEmergency(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasEmergency = true;
    emergency_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setFixedLine(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasFixedLine = true;
    fixedLine_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setGeneralDesc(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasGeneralDesc = true;
    generalDesc_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setId(String paramString)
  {
    hasId = true;
    id_ = paramString;
    return this;
  }
  
  public PhoneMetadata setInternationalPrefix(String paramString)
  {
    hasInternationalPrefix = true;
    internationalPrefix_ = paramString;
    return this;
  }
  
  public PhoneMetadata setLeadingDigits(String paramString)
  {
    hasLeadingDigits = true;
    leadingDigits_ = paramString;
    return this;
  }
  
  public PhoneMetadata setLeadingZeroPossible(boolean paramBoolean)
  {
    hasLeadingZeroPossible = true;
    leadingZeroPossible_ = paramBoolean;
    return this;
  }
  
  public PhoneMetadata setMainCountryForCode(boolean paramBoolean)
  {
    hasMainCountryForCode = true;
    mainCountryForCode_ = paramBoolean;
    return this;
  }
  
  public PhoneMetadata setMobile(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasMobile = true;
    mobile_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setNationalPrefix(String paramString)
  {
    hasNationalPrefix = true;
    nationalPrefix_ = paramString;
    return this;
  }
  
  public PhoneMetadata setNationalPrefixForParsing(String paramString)
  {
    hasNationalPrefixForParsing = true;
    nationalPrefixForParsing_ = paramString;
    return this;
  }
  
  public PhoneMetadata setNationalPrefixTransformRule(String paramString)
  {
    hasNationalPrefixTransformRule = true;
    nationalPrefixTransformRule_ = paramString;
    return this;
  }
  
  public PhoneMetadata setNoInternationalDialling(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasNoInternationalDialling = true;
    noInternationalDialling_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setPager(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasPager = true;
    pager_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setPersonalNumber(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasPersonalNumber = true;
    personalNumber_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setPreferredExtnPrefix(String paramString)
  {
    hasPreferredExtnPrefix = true;
    preferredExtnPrefix_ = paramString;
    return this;
  }
  
  public PhoneMetadata setPreferredInternationalPrefix(String paramString)
  {
    hasPreferredInternationalPrefix = true;
    preferredInternationalPrefix_ = paramString;
    return this;
  }
  
  public PhoneMetadata setPremiumRate(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasPremiumRate = true;
    premiumRate_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setSameMobileAndFixedLinePattern(boolean paramBoolean)
  {
    hasSameMobileAndFixedLinePattern = true;
    sameMobileAndFixedLinePattern_ = paramBoolean;
    return this;
  }
  
  public PhoneMetadata setSharedCost(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasSharedCost = true;
    sharedCost_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setTollFree(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasTollFree = true;
    tollFree_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setUan(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasUan = true;
    uan_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setVoicemail(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasVoicemail = true;
    voicemail_ = paramPhoneNumberDesc;
    return this;
  }
  
  public PhoneMetadata setVoip(Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    if (paramPhoneNumberDesc == null) {
      throw new NullPointerException();
    }
    hasVoip = true;
    voip_ = paramPhoneNumberDesc;
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
  {
    int j = 0;
    paramObjectOutput.writeBoolean(hasGeneralDesc);
    if (hasGeneralDesc) {
      generalDesc_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasFixedLine);
    if (hasFixedLine) {
      fixedLine_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasMobile);
    if (hasMobile) {
      mobile_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasTollFree);
    if (hasTollFree) {
      tollFree_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasPremiumRate);
    if (hasPremiumRate) {
      premiumRate_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasSharedCost);
    if (hasSharedCost) {
      sharedCost_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasPersonalNumber);
    if (hasPersonalNumber) {
      personalNumber_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasVoip);
    if (hasVoip) {
      voip_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasPager);
    if (hasPager) {
      pager_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasUan);
    if (hasUan) {
      uan_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasVoicemail);
    if (hasVoicemail) {
      voicemail_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasEmergency);
    if (hasEmergency) {
      emergency_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeBoolean(hasNoInternationalDialling);
    if (hasNoInternationalDialling) {
      noInternationalDialling_.writeExternal(paramObjectOutput);
    }
    paramObjectOutput.writeUTF(id_);
    paramObjectOutput.writeInt(countryCode_);
    paramObjectOutput.writeUTF(internationalPrefix_);
    paramObjectOutput.writeBoolean(hasPreferredInternationalPrefix);
    if (hasPreferredInternationalPrefix) {
      paramObjectOutput.writeUTF(preferredInternationalPrefix_);
    }
    paramObjectOutput.writeBoolean(hasNationalPrefix);
    if (hasNationalPrefix) {
      paramObjectOutput.writeUTF(nationalPrefix_);
    }
    paramObjectOutput.writeBoolean(hasPreferredExtnPrefix);
    if (hasPreferredExtnPrefix) {
      paramObjectOutput.writeUTF(preferredExtnPrefix_);
    }
    paramObjectOutput.writeBoolean(hasNationalPrefixForParsing);
    if (hasNationalPrefixForParsing) {
      paramObjectOutput.writeUTF(nationalPrefixForParsing_);
    }
    paramObjectOutput.writeBoolean(hasNationalPrefixTransformRule);
    if (hasNationalPrefixTransformRule) {
      paramObjectOutput.writeUTF(nationalPrefixTransformRule_);
    }
    paramObjectOutput.writeBoolean(sameMobileAndFixedLinePattern_);
    int k = numberFormatSize();
    paramObjectOutput.writeInt(k);
    int i = 0;
    while (i < k)
    {
      ((Phonemetadata.NumberFormat)numberFormat_.get(i)).writeExternal(paramObjectOutput);
      i += 1;
    }
    k = intlNumberFormatSize();
    paramObjectOutput.writeInt(k);
    i = j;
    while (i < k)
    {
      ((Phonemetadata.NumberFormat)intlNumberFormat_.get(i)).writeExternal(paramObjectOutput);
      i += 1;
    }
    paramObjectOutput.writeBoolean(mainCountryForCode_);
    paramObjectOutput.writeBoolean(hasLeadingDigits);
    if (hasLeadingDigits) {
      paramObjectOutput.writeUTF(leadingDigits_);
    }
    paramObjectOutput.writeBoolean(leadingZeroPossible_);
  }
  
  public static final class Builder
    extends Phonemetadata.PhoneMetadata
  {
    public Phonemetadata.PhoneMetadata build()
    {
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.Phonemetadata.PhoneMetadata
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */