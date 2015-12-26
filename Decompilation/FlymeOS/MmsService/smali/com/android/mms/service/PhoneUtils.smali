.class public Lcom/android/mms/service/PhoneUtils;
.super Ljava/lang/Object;
.source "PhoneUtils.java"


# direct methods
.method public static getNationalNumber(Landroid/telephony/TelephonyManager;ILjava/lang/String;)Ljava/lang/String;
    .locals 6
    .param p0, "telephonyManager"    # Landroid/telephony/TelephonyManager;
    .param p1, "subId"    # I
    .param p2, "phoneText"    # Ljava/lang/String;

    .prologue
    .line 44
    invoke-static {p0, p1}, Lcom/android/mms/service/PhoneUtils;->getSimOrDefaultLocaleCountry(Landroid/telephony/TelephonyManager;I)Ljava/lang/String;

    move-result-object v0

    .line 45
    .local v0, "country":Ljava/lang/String;
    invoke-static {}, Lcom/android/i18n/phonenumbers/PhoneNumberUtil;->getInstance()Lcom/android/i18n/phonenumbers/PhoneNumberUtil;

    move-result-object v2

    .line 46
    .local v2, "phoneNumberUtil":Lcom/android/i18n/phonenumbers/PhoneNumberUtil;
    invoke-static {v2, p2, v0}, Lcom/android/mms/service/PhoneUtils;->getParsedNumber(Lcom/android/i18n/phonenumbers/PhoneNumberUtil;Ljava/lang/String;Ljava/lang/String;)Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;

    move-result-object v1

    .line 47
    .local v1, "parsed":Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;
    if-nez v1, :cond_0

    .line 50
    .end local p2    # "phoneText":Ljava/lang/String;
    :goto_0
    return-object p2

    .restart local p2    # "phoneText":Ljava/lang/String;
    :cond_0
    sget-object v3, Lcom/android/i18n/phonenumbers/PhoneNumberUtil$PhoneNumberFormat;->NATIONAL:Lcom/android/i18n/phonenumbers/PhoneNumberUtil$PhoneNumberFormat;

    invoke-virtual {v2, v1, v3}, Lcom/android/i18n/phonenumbers/PhoneNumberUtil;->format(Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;Lcom/android/i18n/phonenumbers/PhoneNumberUtil$PhoneNumberFormat;)Ljava/lang/String;

    move-result-object v3

    const-string v4, "\\D"

    const-string v5, ""

    invoke-virtual {v3, v4, v5}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    goto :goto_0
.end method

.method private static getParsedNumber(Lcom/android/i18n/phonenumbers/PhoneNumberUtil;Ljava/lang/String;Ljava/lang/String;)Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;
    .locals 6
    .param p0, "phoneNumberUtil"    # Lcom/android/i18n/phonenumbers/PhoneNumberUtil;
    .param p1, "phoneText"    # Ljava/lang/String;
    .param p2, "country"    # Ljava/lang/String;

    .prologue
    const/4 v2, 0x0

    .line 59
    :try_start_0
    invoke-virtual {p0, p1, p2}, Lcom/android/i18n/phonenumbers/PhoneNumberUtil;->parse(Ljava/lang/String;Ljava/lang/String;)Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;

    move-result-object v1

    .line 60
    .local v1, "phoneNumber":Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;
    invoke-virtual {p0, v1}, Lcom/android/i18n/phonenumbers/PhoneNumberUtil;->isValidNumber(Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 69
    .end local v1    # "phoneNumber":Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;
    :goto_0
    return-object v1

    .line 63
    .restart local v1    # "phoneNumber":Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;
    :cond_0
    const-string v3, "MmsService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "getParsedNumber: not a valid phone number "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " for country "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Lcom/android/i18n/phonenumbers/NumberParseException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v1, v2

    .line 65
    goto :goto_0

    .line 67
    .end local v1    # "phoneNumber":Lcom/android/i18n/phonenumbers/Phonenumber$PhoneNumber;
    :catch_0
    move-exception v0

    .line 68
    .local v0, "e":Lcom/android/i18n/phonenumbers/NumberParseException;
    const-string v3, "MmsService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "getParsedNumber: Not able to parse phone number "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    move-object v1, v2

    .line 69
    goto :goto_0
.end method

.method private static getSimCountry(Landroid/telephony/TelephonyManager;I)Ljava/lang/String;
    .locals 2
    .param p0, "telephonyManager"    # Landroid/telephony/TelephonyManager;
    .param p1, "subId"    # I

    .prologue
    .line 86
    invoke-virtual {p0, p1}, Landroid/telephony/TelephonyManager;->getSimCountryIso(I)Ljava/lang/String;

    move-result-object v0

    .line 87
    .local v0, "country":Ljava/lang/String;
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 88
    const/4 v1, 0x0

    .line 90
    :goto_0
    return-object v1

    :cond_0
    invoke-virtual {v0}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v1

    goto :goto_0
.end method

.method private static getSimOrDefaultLocaleCountry(Landroid/telephony/TelephonyManager;I)Ljava/lang/String;
    .locals 2
    .param p0, "telephonyManager"    # Landroid/telephony/TelephonyManager;
    .param p1, "subId"    # I

    .prologue
    .line 76
    invoke-static {p0, p1}, Lcom/android/mms/service/PhoneUtils;->getSimCountry(Landroid/telephony/TelephonyManager;I)Ljava/lang/String;

    move-result-object v0

    .line 77
    .local v0, "country":Ljava/lang/String;
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 78
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/Locale;->getCountry()Ljava/lang/String;

    move-result-object v0

    .line 81
    :cond_0
    return-object v0
.end method
