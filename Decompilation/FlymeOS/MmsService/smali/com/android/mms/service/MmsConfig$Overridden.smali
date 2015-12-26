.class public Lcom/android/mms/service/MmsConfig$Overridden;
.super Ljava/lang/Object;
.source "MmsConfig.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/MmsConfig;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Overridden"
.end annotation


# instance fields
.field private final mBase:Lcom/android/mms/service/MmsConfig;

.field private final mOverrides:Landroid/os/Bundle;


# direct methods
.method public constructor <init>(Lcom/android/mms/service/MmsConfig;Landroid/os/Bundle;)V
    .locals 0
    .param p1, "base"    # Lcom/android/mms/service/MmsConfig;
    .param p2, "overrides"    # Landroid/os/Bundle;

    .prologue
    .line 347
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 348
    iput-object p1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    .line 349
    iput-object p2, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    .line 350
    return-void
.end method

.method private getBoolean(Ljava/lang/String;)Z
    .locals 3
    .param p1, "key"    # Ljava/lang/String;

    .prologue
    .line 358
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # getter for: Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;
    invoke-static {v1}, Lcom/android/mms/service/MmsConfig;->access$100(Lcom/android/mms/service/MmsConfig;)Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    .line 359
    .local v0, "def":Ljava/lang/Boolean;
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v2

    invoke-virtual {v1, p1, v2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    :goto_0
    return v1

    :cond_0
    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    goto :goto_0
.end method

.method private getInt(Ljava/lang/String;)I
    .locals 3
    .param p1, "key"    # Ljava/lang/String;

    .prologue
    .line 353
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # getter for: Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;
    invoke-static {v1}, Lcom/android/mms/service/MmsConfig;->access$100(Lcom/android/mms/service/MmsConfig;)Ljava/util/Map;

    move-result-object v1

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 354
    .local v0, "def":Ljava/lang/Integer;
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-virtual {v1, p1, v2}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v1

    :goto_0
    return v1

    :cond_0
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v1

    goto :goto_0
.end method

.method private static getLine1(Landroid/content/Context;I)Ljava/lang/String;
    .locals 2
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "subId"    # I

    .prologue
    .line 525
    const-string v1, "phone"

    invoke-virtual {p0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    .line 527
    .local v0, "telephonyManager":Landroid/telephony/TelephonyManager;
    invoke-virtual {v0, p1}, Landroid/telephony/TelephonyManager;->getLine1NumberForSubscriber(I)Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method private static getLine1NoCountryCode(Landroid/content/Context;I)Ljava/lang/String;
    .locals 2
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "subId"    # I

    .prologue
    .line 531
    const-string v1, "phone"

    invoke-virtual {p0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    .line 533
    .local v0, "telephonyManager":Landroid/telephony/TelephonyManager;
    invoke-virtual {v0, p1}, Landroid/telephony/TelephonyManager;->getLine1NumberForSubscriber(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, p1, v1}, Lcom/android/mms/service/PhoneUtils;->getNationalNumber(Landroid/telephony/TelephonyManager;ILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method private getNai(Landroid/content/Context;I)Ljava/lang/String;
    .locals 9
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "subId"    # I

    .prologue
    const/4 v8, 0x2

    .line 543
    const-string v5, "phone"

    invoke-virtual {p1, v5}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/telephony/TelephonyManager;

    .line 545
    .local v4, "telephonyManager":Landroid/telephony/TelephonyManager;
    invoke-static {p2}, Landroid/telephony/SubscriptionManager;->getSlotId(I)I

    move-result v5

    invoke-virtual {v4, v5}, Landroid/telephony/TelephonyManager;->getNai(I)Ljava/lang/String;

    move-result-object v2

    .line 546
    .local v2, "nai":Ljava/lang/String;
    const-string v5, "MmsService"

    invoke-static {v5, v8}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v5

    if-eqz v5, :cond_0

    .line 547
    const-string v5, "MmsService"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "MmsConfig.getNai: nai="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 550
    :cond_0
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_2

    .line 551
    invoke-virtual {p0}, Lcom/android/mms/service/MmsConfig$Overridden;->getNaiSuffix()Ljava/lang/String;

    move-result-object v3

    .line 552
    .local v3, "naiSuffix":Ljava/lang/String;
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_1

    .line 553
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 555
    :cond_1
    const/4 v1, 0x0

    .line 557
    .local v1, "encoded":[B
    :try_start_0
    const-string v5, "UTF-8"

    invoke-virtual {v2, v5}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v5

    const/4 v6, 0x2

    invoke-static {v5, v6}, Landroid/util/Base64;->encode([BI)[B
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v1

    .line 562
    :goto_0
    :try_start_1
    new-instance v2, Ljava/lang/String;

    .end local v2    # "nai":Ljava/lang/String;
    const-string v5, "UTF-8"

    invoke-direct {v2, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V
    :try_end_1
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_1 .. :try_end_1} :catch_1

    .line 567
    .end local v1    # "encoded":[B
    .end local v3    # "naiSuffix":Ljava/lang/String;
    .restart local v2    # "nai":Ljava/lang/String;
    :cond_2
    :goto_1
    return-object v2

    .line 558
    .restart local v1    # "encoded":[B
    .restart local v3    # "naiSuffix":Ljava/lang/String;
    :catch_0
    move-exception v0

    .line 559
    .local v0, "e":Ljava/io/UnsupportedEncodingException;
    invoke-virtual {v2}, Ljava/lang/String;->getBytes()[B

    move-result-object v5

    invoke-static {v5, v8}, Landroid/util/Base64;->encode([BI)[B

    move-result-object v1

    goto :goto_0

    .line 563
    .end local v0    # "e":Ljava/io/UnsupportedEncodingException;
    .end local v2    # "nai":Ljava/lang/String;
    :catch_1
    move-exception v0

    .line 564
    .restart local v0    # "e":Ljava/io/UnsupportedEncodingException;
    new-instance v2, Ljava/lang/String;

    invoke-direct {v2, v1}, Ljava/lang/String;-><init>([B)V

    .restart local v2    # "nai":Ljava/lang/String;
    goto :goto_1
.end method

.method private getString(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p1, "key"    # Ljava/lang/String;

    .prologue
    .line 363
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    invoke-virtual {v0, p1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 364
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    invoke-virtual {v0, p1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 366
    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # invokes: Lcom/android/mms/service/MmsConfig;->getNullableStringValue(Ljava/lang/String;)Ljava/lang/String;
    invoke-static {v0, p1}, Lcom/android/mms/service/MmsConfig;->access$200(Lcom/android/mms/service/MmsConfig;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method


# virtual methods
.method public getHttpParamMacro(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "macro"    # Ljava/lang/String;

    .prologue
    .line 511
    const-string v0, "LINE1"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 512
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    invoke-virtual {v0}, Lcom/android/mms/service/MmsConfig;->getSubId()I

    move-result v0

    invoke-static {p1, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getLine1(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v0

    .line 518
    :goto_0
    return-object v0

    .line 513
    :cond_0
    const-string v0, "LINE1NOCOUNTRYCODE"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 514
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    invoke-virtual {v0}, Lcom/android/mms/service/MmsConfig;->getSubId()I

    move-result v0

    invoke-static {p1, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getLine1NoCountryCode(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 515
    :cond_1
    const-string v0, "NAI"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 516
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    invoke-virtual {v0}, Lcom/android/mms/service/MmsConfig;->getSubId()I

    move-result v0

    invoke-direct {p0, p1, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getNai(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 518
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public getHttpParams()Ljava/lang/String;
    .locals 1

    .prologue
    .line 410
    const-string v0, "httpParams"

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getHttpSocketTimeout()I
    .locals 1

    .prologue
    .line 436
    const-string v0, "httpSocketTimeout"

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getInt(Ljava/lang/String;)I

    move-result v0

    return v0
.end method

.method public getMaxMessageSize()I
    .locals 1

    .prologue
    .line 382
    const-string v0, "maxMessageSize"

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getInt(Ljava/lang/String;)I

    move-result v0

    return v0
.end method

.method public getNaiSuffix()Ljava/lang/String;
    .locals 1

    .prologue
    .line 488
    const-string v0, "naiSuffix"

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getSupportHttpCharsetHeader()Z
    .locals 1

    .prologue
    .line 500
    const-string v0, "supportHttpCharsetHeader"

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getBoolean(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public getUaProfTagName()Ljava/lang/String;
    .locals 1

    .prologue
    .line 398
    const-string v0, "uaProfTagName"

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getUaProfUrl()Ljava/lang/String;
    .locals 2

    .prologue
    .line 402
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    const-string v1, "uaProfUrl"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 403
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    const-string v1, "uaProfUrl"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 405
    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # getter for: Lcom/android/mms/service/MmsConfig;->mUaProfUrl:Ljava/lang/String;
    invoke-static {v0}, Lcom/android/mms/service/MmsConfig;->access$400(Lcom/android/mms/service/MmsConfig;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # getter for: Lcom/android/mms/service/MmsConfig;->mUaProfUrl:Ljava/lang/String;
    invoke-static {v0}, Lcom/android/mms/service/MmsConfig;->access$400(Lcom/android/mms/service/MmsConfig;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    const-string v1, "uaProfUrl"

    # invokes: Lcom/android/mms/service/MmsConfig;->getNullableStringValue(Ljava/lang/String;)Ljava/lang/String;
    invoke-static {v0, v1}, Lcom/android/mms/service/MmsConfig;->access$200(Lcom/android/mms/service/MmsConfig;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method public getUserAgent()Ljava/lang/String;
    .locals 2

    .prologue
    .line 390
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    const-string v1, "userAgent"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 391
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mOverrides:Landroid/os/Bundle;

    const-string v1, "userAgent"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 393
    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # getter for: Lcom/android/mms/service/MmsConfig;->mUserAgent:Ljava/lang/String;
    invoke-static {v0}, Lcom/android/mms/service/MmsConfig;->access$300(Lcom/android/mms/service/MmsConfig;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    # getter for: Lcom/android/mms/service/MmsConfig;->mUserAgent:Ljava/lang/String;
    invoke-static {v0}, Lcom/android/mms/service/MmsConfig;->access$300(Lcom/android/mms/service/MmsConfig;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$Overridden;->mBase:Lcom/android/mms/service/MmsConfig;

    const-string v1, "userAgent"

    # invokes: Lcom/android/mms/service/MmsConfig;->getNullableStringValue(Ljava/lang/String;)Ljava/lang/String;
    invoke-static {v0, v1}, Lcom/android/mms/service/MmsConfig;->access$200(Lcom/android/mms/service/MmsConfig;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method
