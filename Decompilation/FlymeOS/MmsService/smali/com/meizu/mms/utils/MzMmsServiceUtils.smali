.class public Lcom/meizu/mms/utils/MzMmsServiceUtils;
.super Ljava/lang/Object;
.source "MzMmsServiceUtils.java"


# direct methods
.method public static checkIsFailedRespStatus(I)Z
    .locals 2
    .param p0, "respStatus"    # I

    .prologue
    .line 29
    const/16 v1, 0x2537

    if-eq p0, v1, :cond_0

    const/16 v1, 0x84

    if-eq p0, v1, :cond_0

    const/16 v1, 0x87

    if-eq p0, v1, :cond_0

    const/16 v1, 0x82

    if-ne p0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    .line 36
    .local v0, "ret":Z
    :goto_0
    return v0

    .line 29
    .end local v0    # "ret":Z
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static getSlotIdBySubId(I)I
    .locals 1
    .param p0, "subId"    # I

    .prologue
    .line 16
    invoke-static {p0}, Landroid/telephony/SubscriptionManager;->getSlotId(I)I

    move-result v0

    return v0
.end method

.method public static getSubscriberIdBySubId(I)Ljava/lang/String;
    .locals 1
    .param p0, "subId"    # I

    .prologue
    .line 20
    invoke-static {}, Landroid/telephony/TelephonyManager;->getDefault()Landroid/telephony/TelephonyManager;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/telephony/TelephonyManager;->getSubscriberId(I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static isCTVersion()Z
    .locals 1

    .prologue
    .line 40
    sget-object v0, Landroid/os/BuildExt;->CUSTOMIZE_CHINATELECOM:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method
