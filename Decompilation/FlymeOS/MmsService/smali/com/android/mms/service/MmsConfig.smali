.class public Lcom/android/mms/service/MmsConfig;
.super Ljava/lang/Object;
.source "MmsConfig.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/mms/service/MmsConfig$Overridden;
    }
.end annotation


# static fields
.field private static final DEFAULTS:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final mKeyValues:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private final mSubId:I

.field private mUaProfUrl:Ljava/lang/String;

.field private mUserAgent:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    .prologue
    const/4 v5, -0x1

    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 146
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    .line 148
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enabledMMS"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 149
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enabledTransID"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 150
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enabledNotifyWapMMSC"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 151
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "aliasEnabled"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 152
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "allowAttachAudio"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 153
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enableMultipartSMS"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 154
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enableSMSDeliveryReports"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 155
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enableGroupMms"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 156
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "supportMmsContentDisposition"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 157
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "config_cellBroadcastAppLinks"

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 158
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "sendMultipartSmsAsSeparateMessages"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 159
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enableMMSReadReports"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 160
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "enableMMSDeliveryReports"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 161
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "supportHttpCharsetHeader"

    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 162
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "maxMessageSize"

    const v2, 0x4b000

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 163
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "maxImageHeight"

    const/16 v2, 0x1e0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 164
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "maxImageWidth"

    const/16 v2, 0x280

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 165
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "recipientLimit"

    const v2, 0x7fffffff

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 166
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "httpSocketTimeout"

    const v2, 0xea60

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 167
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "aliasMinChars"

    const/4 v2, 0x2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 168
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "aliasMaxChars"

    const/16 v2, 0x30

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 169
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "smsToMmsTextThreshold"

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 170
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "smsToMmsTextLengthThreshold"

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 171
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "maxMessageTextSize"

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 172
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "maxSubjectLength"

    const/16 v2, 0x28

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 173
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "uaProfTagName"

    const-string v2, "x-wap-profile"

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 174
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "userAgent"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 175
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "uaProfUrl"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 176
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "httpParams"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 177
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "emailGatewayNumber"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 178
    sget-object v0, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    const-string v1, "naiSuffix"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 179
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "subId"    # I

    .prologue
    const/4 v0, 0x0

    .line 191
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 251
    iput-object v0, p0, Lcom/android/mms/service/MmsConfig;->mUserAgent:Ljava/lang/String;

    .line 252
    iput-object v0, p0, Lcom/android/mms/service/MmsConfig;->mUaProfUrl:Ljava/lang/String;

    .line 255
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    .line 192
    iput p2, p0, Lcom/android/mms/service/MmsConfig;->mSubId:I

    .line 194
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 195
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    sget-object v1, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    invoke-interface {v0, v1}, Ljava/util/Map;->putAll(Ljava/util/Map;)V

    .line 197
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsConfig;->loadDeviceUaSettings(Landroid/content/Context;)V

    .line 198
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "MmsConfig: mUserAgent="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/android/mms/service/MmsConfig;->mUserAgent:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", mUaProfUrl="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/android/mms/service/MmsConfig;->mUaProfUrl:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 200
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsConfig;->loadFromResources(Landroid/content/Context;)V

    .line 201
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "MmsConfig: all settings -- "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 202
    return-void
.end method

.method static synthetic access$000(Lcom/android/mms/service/MmsConfig;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfig;
    .param p1, "x1"    # Ljava/lang/String;
    .param p2, "x2"    # Ljava/lang/String;
    .param p3, "x3"    # Ljava/lang/String;

    .prologue
    .line 43
    invoke-direct {p0, p1, p2, p3}, Lcom/android/mms/service/MmsConfig;->update(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$100(Lcom/android/mms/service/MmsConfig;)Ljava/util/Map;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfig;

    .prologue
    .line 43
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    return-object v0
.end method

.method static synthetic access$200(Lcom/android/mms/service/MmsConfig;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfig;
    .param p1, "x1"    # Ljava/lang/String;

    .prologue
    .line 43
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsConfig;->getNullableStringValue(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$300(Lcom/android/mms/service/MmsConfig;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfig;

    .prologue
    .line 43
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig;->mUserAgent:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$400(Lcom/android/mms/service/MmsConfig;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfig;

    .prologue
    .line 43
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig;->mUaProfUrl:Ljava/lang/String;

    return-object v0
.end method

.method private getNullableStringValue(Ljava/lang/String;)Ljava/lang/String;
    .locals 2
    .param p1, "key"    # Ljava/lang/String;

    .prologue
    .line 291
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 292
    .local v0, "value":Ljava/lang/Object;
    if-eqz v0, :cond_0

    .line 293
    check-cast v0, Ljava/lang/String;

    .line 295
    .end local v0    # "value":Ljava/lang/Object;
    :goto_0
    return-object v0

    .restart local v0    # "value":Ljava/lang/Object;
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static isValidKey(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 5
    .param p0, "key"    # Ljava/lang/String;
    .param p1, "type"    # Ljava/lang/String;

    .prologue
    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 221
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_5

    sget-object v4, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    invoke-interface {v4, p0}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_5

    .line 222
    sget-object v4, Lcom/android/mms/service/MmsConfig;->DEFAULTS:Ljava/util/Map;

    invoke-interface {v4, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 223
    .local v0, "defVal":Ljava/lang/Object;
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 224
    .local v1, "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :goto_0
    const-string v4, "int"

    invoke-virtual {v4, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 225
    const-class v4, Ljava/lang/Integer;

    if-ne v1, v4, :cond_2

    .line 232
    .end local v0    # "defVal":Ljava/lang/Object;
    .end local v1    # "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :cond_0
    :goto_1
    return v2

    .line 223
    .restart local v0    # "defVal":Ljava/lang/Object;
    :cond_1
    const-class v1, Ljava/lang/String;

    goto :goto_0

    .restart local v1    # "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :cond_2
    move v2, v3

    .line 225
    goto :goto_1

    .line 226
    :cond_3
    const-string v4, "bool"

    invoke-virtual {v4, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_4

    .line 227
    const-class v4, Ljava/lang/Boolean;

    if-eq v1, v4, :cond_0

    move v2, v3

    goto :goto_1

    .line 228
    :cond_4
    const-string v4, "string"

    invoke-virtual {v4, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_5

    .line 229
    const-class v4, Ljava/lang/String;

    if-eq v1, v4, :cond_0

    move v2, v3

    goto :goto_1

    .end local v0    # "defVal":Ljava/lang/Object;
    .end local v1    # "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :cond_5
    move v2, v3

    .line 232
    goto :goto_1
.end method

.method private loadDeviceUaSettings(Landroid/content/Context;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 314
    const-string v1, "phone"

    invoke-virtual {p1, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    .line 316
    .local v0, "telephonyManager":Landroid/telephony/TelephonyManager;
    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getMmsUserAgent()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/android/mms/service/MmsConfig;->mUserAgent:Ljava/lang/String;

    .line 317
    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getMmsUAProfUrl()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/android/mms/service/MmsConfig;->mUaProfUrl:Ljava/lang/String;

    .line 318
    return-void
.end method

.method private loadFromResources(Landroid/content/Context;)V
    .locals 4
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 321
    const-string v2, "MmsService"

    const-string v3, "MmsConfig.loadFromResources"

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 322
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const/high16 v3, 0x7f020000

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getXml(I)Landroid/content/res/XmlResourceParser;

    move-result-object v0

    .line 323
    .local v0, "parser":Landroid/content/res/XmlResourceParser;
    invoke-static {v0}, Lcom/android/mms/service/MmsConfigXmlProcessor;->get(Lorg/xmlpull/v1/XmlPullParser;)Lcom/android/mms/service/MmsConfigXmlProcessor;

    move-result-object v1

    .line 324
    .local v1, "processor":Lcom/android/mms/service/MmsConfigXmlProcessor;
    new-instance v2, Lcom/android/mms/service/MmsConfig$1;

    invoke-direct {v2, p0}, Lcom/android/mms/service/MmsConfig$1;-><init>(Lcom/android/mms/service/MmsConfig;)V

    invoke-virtual {v1, v2}, Lcom/android/mms/service/MmsConfigXmlProcessor;->setMmsConfigHandler(Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;)Lcom/android/mms/service/MmsConfigXmlProcessor;

    .line 331
    :try_start_0
    invoke-virtual {v1}, Lcom/android/mms/service/MmsConfigXmlProcessor;->process()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 333
    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    .line 335
    return-void

    .line 333
    :catchall_0
    move-exception v2

    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    throw v2
.end method

.method private update(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 4
    .param p1, "key"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
    .param p3, "type"    # Ljava/lang/String;

    .prologue
    .line 300
    :try_start_0
    const-string v1, "int"

    invoke-virtual {v1, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 301
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-static {p2}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1, p1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 310
    :cond_0
    :goto_0
    return-void

    .line 302
    :cond_1
    const-string v1, "bool"

    invoke-virtual {v1, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 303
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-static {p2}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v2

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-interface {v1, p1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 307
    :catch_0
    move-exception v0

    .line 308
    .local v0, "e":Ljava/lang/NumberFormatException;
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "MmsConfig.update: invalid "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ","

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ","

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 304
    .end local v0    # "e":Ljava/lang/NumberFormatException;
    :cond_2
    :try_start_1
    const-string v1, "string"

    invoke-virtual {v1, p3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 305
    iget-object v1, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-interface {v1, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_1
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0
.end method


# virtual methods
.method public getCarrierConfigValues()Landroid/os/Bundle;
    .locals 7

    .prologue
    .line 272
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    .line 273
    .local v0, "bundle":Landroid/os/Bundle;
    iget-object v6, p0, Lcom/android/mms/service/MmsConfig;->mKeyValues:Ljava/util/Map;

    invoke-interface {v6}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v6

    invoke-interface {v6}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .line 274
    .local v2, "iter":Ljava/util/Iterator;, "Ljava/util/Iterator<Ljava/util/Map$Entry<Ljava/lang/String;Ljava/lang/Object;>;>;"
    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_4

    .line 275
    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 276
    .local v1, "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/lang/Object;>;"
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 277
    .local v3, "key":Ljava/lang/String;
    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    .line 278
    .local v4, "val":Ljava/lang/Object;
    if-eqz v4, :cond_1

    invoke-virtual {v4}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v5

    .line 279
    .local v5, "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :goto_1
    const-class v6, Ljava/lang/Integer;

    if-ne v5, v6, :cond_2

    .line 280
    check-cast v4, Ljava/lang/Integer;

    .end local v4    # "val":Ljava/lang/Object;
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v6

    invoke-virtual {v0, v3, v6}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    goto :goto_0

    .line 278
    .end local v5    # "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    .restart local v4    # "val":Ljava/lang/Object;
    :cond_1
    const-class v5, Ljava/lang/String;

    goto :goto_1

    .line 281
    .restart local v5    # "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :cond_2
    const-class v6, Ljava/lang/Boolean;

    if-ne v5, v6, :cond_3

    .line 282
    check-cast v4, Ljava/lang/Boolean;

    .end local v4    # "val":Ljava/lang/Object;
    invoke-virtual {v4}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v6

    invoke-virtual {v0, v3, v6}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    goto :goto_0

    .line 283
    .restart local v4    # "val":Ljava/lang/Object;
    :cond_3
    const-class v6, Ljava/lang/String;

    if-ne v5, v6, :cond_0

    .line 284
    check-cast v4, Ljava/lang/String;

    .end local v4    # "val":Ljava/lang/Object;
    invoke-virtual {v0, v3, v4}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_0

    .line 287
    .end local v1    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/lang/Object;>;"
    .end local v3    # "key":Ljava/lang/String;
    .end local v5    # "valueType":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :cond_4
    return-object v0
.end method

.method public getSubId()I
    .locals 1

    .prologue
    .line 210
    iget v0, p0, Lcom/android/mms/service/MmsConfig;->mSubId:I

    return v0
.end method
