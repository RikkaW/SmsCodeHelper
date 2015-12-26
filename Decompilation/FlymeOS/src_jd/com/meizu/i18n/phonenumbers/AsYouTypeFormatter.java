package com.meizu.i18n.phonenumbers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsYouTypeFormatter
{
  private static final Pattern CHARACTER_CLASS_PATTERN;
  private static final Pattern DIGIT_PATTERN = Pattern.compile(" ");
  private static final String DIGIT_PLACEHOLDER = " ";
  private static final Pattern ELIGIBLE_FORMAT_PATTERN;
  private static final Phonemetadata.PhoneMetadata EMPTY_METADATA = new Phonemetadata.PhoneMetadata().setInternationalPrefix("NA");
  private static final int MIN_LEADING_DIGITS_LENGTH = 3;
  private static final Pattern NATIONAL_PREFIX_SEPARATORS_PATTERN;
  private static final char SEPARATOR_BEFORE_NATIONAL_NUMBER = ' ';
  private static final Pattern STANDALONE_DIGIT_PATTERN;
  private boolean ableToFormat = true;
  private StringBuilder accruedInput = new StringBuilder();
  private StringBuilder accruedInputWithoutFormatting = new StringBuilder();
  private String currentFormattingPattern = "";
  private Phonemetadata.PhoneMetadata currentMetaData;
  private String currentOutput = "";
  private String defaultCountry;
  private Phonemetadata.PhoneMetadata defaultMetaData;
  private StringBuilder formattingTemplate = new StringBuilder();
  private boolean inputHasFormatting = false;
  private boolean isCompleteNumber = false;
  private boolean isExpectingCountryCallingCode = false;
  private int lastMatchPosition = 0;
  private StringBuilder nationalNumber = new StringBuilder();
  private String nationalPrefixExtracted = "";
  private int originalPosition = 0;
  private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  private int positionToRemember = 0;
  private List<Phonemetadata.NumberFormat> possibleFormats = new ArrayList();
  private StringBuilder prefixBeforeNationalNumber = new StringBuilder();
  private RegexCache regexCache = new RegexCache(64);
  private boolean shouldAddSpaceAfterNationalPrefix = false;
  
  static
  {
    CHARACTER_CLASS_PATTERN = Pattern.compile("\\[([^\\[\\]])*\\]");
    STANDALONE_DIGIT_PATTERN = Pattern.compile("\\d(?=[^,}][^,}])");
    ELIGIBLE_FORMAT_PATTERN = Pattern.compile("[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]*(\\$\\d[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]*)+");
    NATIONAL_PREFIX_SEPARATORS_PATTERN = Pattern.compile("[- ]");
  }
  
  AsYouTypeFormatter(String paramString)
  {
    defaultCountry = paramString;
    currentMetaData = getMetadataForRegion(defaultCountry);
    defaultMetaData = currentMetaData;
  }
  
  private boolean ableToExtractLongerNdd()
  {
    boolean bool = false;
    if (nationalPrefixExtracted.length() > 0)
    {
      nationalNumber.insert(0, nationalPrefixExtracted);
      int i = prefixBeforeNationalNumber.lastIndexOf(nationalPrefixExtracted);
      prefixBeforeNationalNumber.setLength(i);
    }
    if (!nationalPrefixExtracted.equals(removeNationalPrefixFromNationalNumber())) {
      bool = true;
    }
    return bool;
  }
  
  private String appendNationalNumber(String paramString)
  {
    int i = prefixBeforeNationalNumber.length();
    if ((shouldAddSpaceAfterNationalPrefix) && (i > 0) && (prefixBeforeNationalNumber.charAt(i - 1) != ' ')) {
      return new String(prefixBeforeNationalNumber) + ' ' + paramString;
    }
    return prefixBeforeNationalNumber + paramString;
  }
  
  private String attemptToChooseFormattingPattern()
  {
    if (nationalNumber.length() >= 3)
    {
      getAvailableFormats(nationalNumber.substring(0, 3));
      if (maybeCreateNewTemplate()) {
        return inputAccruedNationalNumber();
      }
      return accruedInput.toString();
    }
    return appendNationalNumber(nationalNumber.toString());
  }
  
  private String attemptToChoosePatternWithPrefixExtracted()
  {
    ableToFormat = true;
    isExpectingCountryCallingCode = false;
    possibleFormats.clear();
    return attemptToChooseFormattingPattern();
  }
  
  private boolean attemptToExtractCountryCallingCode()
  {
    if (nationalNumber.length() == 0) {}
    int i;
    do
    {
      return false;
      localObject = new StringBuilder();
      i = phoneUtil.extractCountryCode(nationalNumber, (StringBuilder)localObject);
    } while (i == 0);
    nationalNumber.setLength(0);
    nationalNumber.append((CharSequence)localObject);
    Object localObject = phoneUtil.getRegionCodeForCountryCode(i);
    if ("001".equals(localObject)) {
      currentMetaData = phoneUtil.getMetadataForNonGeographicalRegion(i);
    }
    for (;;)
    {
      localObject = Integer.toString(i);
      prefixBeforeNationalNumber.append((String)localObject).append(' ');
      return true;
      if (!((String)localObject).equals(defaultCountry)) {
        currentMetaData = getMetadataForRegion((String)localObject);
      }
    }
  }
  
  private boolean attemptToExtractIdd()
  {
    Matcher localMatcher = regexCache.getPatternForRegex("\\+|" + currentMetaData.getInternationalPrefix()).matcher(accruedInputWithoutFormatting);
    if (localMatcher.lookingAt())
    {
      isCompleteNumber = true;
      int i = localMatcher.end();
      nationalNumber.setLength(0);
      nationalNumber.append(accruedInputWithoutFormatting.substring(i));
      prefixBeforeNationalNumber.setLength(0);
      prefixBeforeNationalNumber.append(accruedInputWithoutFormatting.substring(0, i));
      if (accruedInputWithoutFormatting.charAt(0) != '+') {
        prefixBeforeNationalNumber.append(' ');
      }
      return true;
    }
    return false;
  }
  
  private boolean createFormattingTemplate(Phonemetadata.NumberFormat paramNumberFormat)
  {
    String str = paramNumberFormat.getPattern();
    if (str.indexOf('|') != -1) {}
    do
    {
      return false;
      str = CHARACTER_CLASS_PATTERN.matcher(str).replaceAll("\\\\d");
      str = STANDALONE_DIGIT_PATTERN.matcher(str).replaceAll("\\\\d");
      formattingTemplate.setLength(0);
      paramNumberFormat = getFormattingTemplate(str, paramNumberFormat.getFormat());
    } while (paramNumberFormat.length() <= 0);
    formattingTemplate.append(paramNumberFormat);
    return true;
  }
  
  private void getAvailableFormats(String paramString)
  {
    if ((isCompleteNumber) && (currentMetaData.intlNumberFormatSize() > 0)) {}
    for (Object localObject = currentMetaData.intlNumberFormats();; localObject = currentMetaData.numberFormats())
    {
      boolean bool = currentMetaData.hasNationalPrefix();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)((Iterator)localObject).next();
        if ((bool) && (!isCompleteNumber) && (!localNumberFormat.isNationalPrefixOptionalWhenFormatting()))
        {
          PhoneNumberUtil localPhoneNumberUtil = phoneUtil;
          if (!PhoneNumberUtil.formattingRuleHasFirstGroupOnly(localNumberFormat.getNationalPrefixFormattingRule())) {}
        }
        else if (isFormatEligible(localNumberFormat.getFormat()))
        {
          possibleFormats.add(localNumberFormat);
        }
      }
    }
    narrowDownPossibleFormats(paramString);
  }
  
  private String getFormattingTemplate(String paramString1, String paramString2)
  {
    Object localObject = regexCache.getPatternForRegex(paramString1).matcher("999999999999999");
    ((Matcher)localObject).find();
    localObject = ((Matcher)localObject).group();
    if (((String)localObject).length() < nationalNumber.length()) {
      return "";
    }
    return ((String)localObject).replaceAll(paramString1, paramString2).replaceAll("9", " ");
  }
  
  private Phonemetadata.PhoneMetadata getMetadataForRegion(String paramString)
  {
    int i = phoneUtil.getCountryCodeForRegion(paramString);
    paramString = phoneUtil.getRegionCodeForCountryCode(i);
    paramString = phoneUtil.getMetadataForRegion(paramString);
    if (paramString != null) {
      return paramString;
    }
    return EMPTY_METADATA;
  }
  
  private String inputAccruedNationalNumber()
  {
    int j = nationalNumber.length();
    if (j > 0)
    {
      String str = "";
      int i = 0;
      while (i < j)
      {
        str = inputDigitHelper(nationalNumber.charAt(i));
        i += 1;
      }
      if (ableToFormat) {
        return appendNationalNumber(str);
      }
      return accruedInput.toString();
    }
    return prefixBeforeNationalNumber.toString();
  }
  
  private String inputDigitHelper(char paramChar)
  {
    Matcher localMatcher = DIGIT_PATTERN.matcher(formattingTemplate);
    if (localMatcher.find(lastMatchPosition))
    {
      String str = localMatcher.replaceFirst(Character.toString(paramChar));
      formattingTemplate.replace(0, str.length(), str);
      lastMatchPosition = localMatcher.start();
      return formattingTemplate.substring(0, lastMatchPosition + 1);
    }
    if (possibleFormats.size() == 1) {
      ableToFormat = false;
    }
    currentFormattingPattern = "";
    return accruedInput.toString();
  }
  
  private String inputDigitWithOptionToRememberPosition(char paramChar, boolean paramBoolean)
  {
    accruedInput.append(paramChar);
    if (paramBoolean) {
      originalPosition = accruedInput.length();
    }
    Object localObject;
    if (!isDigitOrLeadingPlusSign(paramChar))
    {
      ableToFormat = false;
      inputHasFormatting = true;
      if (ableToFormat) {
        break label125;
      }
      if (!inputHasFormatting) {
        break label76;
      }
      localObject = accruedInput.toString();
    }
    label76:
    label125:
    String str2;
    String str1;
    do
    {
      return (String)localObject;
      paramChar = normalizeAndAccrueDigitsAndPlusSign(paramChar, paramBoolean);
      break;
      if (attemptToExtractIdd())
      {
        if (attemptToExtractCountryCallingCode()) {
          return attemptToChoosePatternWithPrefixExtracted();
        }
      }
      else if (ableToExtractLongerNdd())
      {
        prefixBeforeNationalNumber.append(' ');
        return attemptToChoosePatternWithPrefixExtracted();
      }
      return accruedInput.toString();
      switch (accruedInputWithoutFormatting.length())
      {
      }
      while (isExpectingCountryCallingCode)
      {
        if (attemptToExtractCountryCallingCode()) {
          isExpectingCountryCallingCode = false;
        }
        return prefixBeforeNationalNumber + nationalNumber.toString();
        return accruedInput.toString();
        if (attemptToExtractIdd())
        {
          isExpectingCountryCallingCode = true;
        }
        else
        {
          nationalPrefixExtracted = removeNationalPrefixFromNationalNumber();
          return attemptToChooseFormattingPattern();
        }
      }
      if (possibleFormats.size() <= 0) {
        break label328;
      }
      str2 = inputDigitHelper(paramChar);
      str1 = attemptToFormatAccruedDigits();
      localObject = str1;
    } while (str1.length() > 0);
    narrowDownPossibleFormats(nationalNumber.toString());
    if (maybeCreateNewTemplate()) {
      return inputAccruedNationalNumber();
    }
    if (ableToFormat) {
      return appendNationalNumber(str2);
    }
    return accruedInput.toString();
    label328:
    return attemptToChooseFormattingPattern();
  }
  
  private boolean isDigitOrLeadingPlusSign(char paramChar)
  {
    return (Character.isDigit(paramChar)) || ((accruedInput.length() == 1) && (PhoneNumberUtil.PLUS_CHARS_PATTERN.matcher(Character.toString(paramChar)).matches()));
  }
  
  private boolean isFormatEligible(String paramString)
  {
    return ELIGIBLE_FORMAT_PATTERN.matcher(paramString).matches();
  }
  
  private boolean isNanpaNumberWithNationalPrefix()
  {
    return (currentMetaData.getCountryCode() == 1) && (nationalNumber.charAt(0) == '1') && (nationalNumber.charAt(1) != '0') && (nationalNumber.charAt(1) != '1');
  }
  
  private boolean maybeCreateNewTemplate()
  {
    Iterator localIterator = possibleFormats.iterator();
    while (localIterator.hasNext())
    {
      Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)localIterator.next();
      String str = localNumberFormat.getPattern();
      if (currentFormattingPattern.equals(str)) {
        return false;
      }
      if (createFormattingTemplate(localNumberFormat))
      {
        currentFormattingPattern = str;
        shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(localNumberFormat.getNationalPrefixFormattingRule()).find();
        lastMatchPosition = 0;
        return true;
      }
      localIterator.remove();
    }
    ableToFormat = false;
    return false;
  }
  
  private void narrowDownPossibleFormats(String paramString)
  {
    int i = paramString.length() - 3;
    Iterator localIterator = possibleFormats.iterator();
    while (localIterator.hasNext())
    {
      Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)localIterator.next();
      if ((localNumberFormat.leadingDigitsPatternSize() > i) && (!regexCache.getPatternForRegex(localNumberFormat.getLeadingDigitsPattern(i)).matcher(paramString).lookingAt())) {
        localIterator.remove();
      }
    }
  }
  
  private char normalizeAndAccrueDigitsAndPlusSign(char paramChar, boolean paramBoolean)
  {
    if (paramChar == '+') {
      accruedInputWithoutFormatting.append(paramChar);
    }
    for (;;)
    {
      if (paramBoolean) {
        positionToRemember = accruedInputWithoutFormatting.length();
      }
      return paramChar;
      paramChar = Character.forDigit(Character.digit(paramChar, 10), 10);
      accruedInputWithoutFormatting.append(paramChar);
      nationalNumber.append(paramChar);
    }
  }
  
  private String removeNationalPrefixFromNationalNumber()
  {
    int i = 1;
    if (isNanpaNumberWithNationalPrefix())
    {
      prefixBeforeNationalNumber.append('1').append(' ');
      isCompleteNumber = true;
    }
    for (;;)
    {
      Object localObject = nationalNumber.substring(0, i);
      nationalNumber.delete(0, i);
      return (String)localObject;
      if (currentMetaData.hasNationalPrefixForParsing())
      {
        localObject = regexCache.getPatternForRegex(currentMetaData.getNationalPrefixForParsing()).matcher(nationalNumber);
        if (((Matcher)localObject).lookingAt())
        {
          isCompleteNumber = true;
          i = ((Matcher)localObject).end();
          prefixBeforeNationalNumber.append(nationalNumber.substring(0, i));
          continue;
        }
      }
      i = 0;
    }
  }
  
  String attemptToFormatAccruedDigits()
  {
    Iterator localIterator = possibleFormats.iterator();
    while (localIterator.hasNext())
    {
      Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)localIterator.next();
      Matcher localMatcher = regexCache.getPatternForRegex(localNumberFormat.getPattern()).matcher(nationalNumber);
      if (localMatcher.matches())
      {
        shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(localNumberFormat.getNationalPrefixFormattingRule()).find();
        return appendNationalNumber(localMatcher.replaceAll(localNumberFormat.getFormat()));
      }
    }
    return "";
  }
  
  public void clear()
  {
    currentOutput = "";
    accruedInput.setLength(0);
    accruedInputWithoutFormatting.setLength(0);
    formattingTemplate.setLength(0);
    lastMatchPosition = 0;
    currentFormattingPattern = "";
    prefixBeforeNationalNumber.setLength(0);
    nationalPrefixExtracted = "";
    nationalNumber.setLength(0);
    ableToFormat = true;
    inputHasFormatting = false;
    positionToRemember = 0;
    originalPosition = 0;
    isCompleteNumber = false;
    isExpectingCountryCallingCode = false;
    possibleFormats.clear();
    shouldAddSpaceAfterNationalPrefix = false;
    if (!currentMetaData.equals(defaultMetaData)) {
      currentMetaData = getMetadataForRegion(defaultCountry);
    }
  }
  
  public int getRememberedPosition()
  {
    int i = 0;
    int k;
    if (!ableToFormat)
    {
      k = originalPosition;
      return k;
    }
    for (int j = 0;; j = k)
    {
      k = i;
      if (j >= positionToRemember) {
        break;
      }
      k = i;
      if (i >= currentOutput.length()) {
        break;
      }
      k = j;
      if (accruedInputWithoutFormatting.charAt(j) == currentOutput.charAt(i)) {
        k = j + 1;
      }
      i += 1;
    }
  }
  
  public String inputDigit(char paramChar)
  {
    currentOutput = inputDigitWithOptionToRememberPosition(paramChar, false);
    return currentOutput;
  }
  
  public String inputDigitAndRememberPosition(char paramChar)
  {
    currentOutput = inputDigitWithOptionToRememberPosition(paramChar, true);
    return currentOutput;
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.AsYouTypeFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */