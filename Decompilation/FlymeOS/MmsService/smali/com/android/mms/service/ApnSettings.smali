.class public Lcom/android/mms/service/ApnSettings;
.super Ljava/lang/Object;
.source "ApnSettings.java"


# static fields
.field private static final APN_PROJECTION:[Ljava/lang/String;


# instance fields
.field private final mDebugText:Ljava/lang/String;

.field private final mProxyAddress:Ljava/lang/String;

.field private final mProxyPort:I

.field private final mServiceCenter:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 49
    const/16 v0, 0x11

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v2, "type"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string v2, "mmsc"

    aput-object v2, v0, v1

    const/4 v1, 0x2

    const-string v2, "mmsproxy"

    aput-object v2, v0, v1

    const/4 v1, 0x3

    const-string v2, "mmsport"

    aput-object v2, v0, v1

    const/4 v1, 0x4

    const-string v2, "name"

    aput-object v2, v0, v1

    const/4 v1, 0x5

    const-string v2, "apn"

    aput-object v2, v0, v1

    const/4 v1, 0x6

    const-string v2, "bearer"

    aput-object v2, v0, v1

    const/4 v1, 0x7

    const-string v2, "protocol"

    aput-object v2, v0, v1

    const/16 v1, 0x8

    const-string v2, "roaming_protocol"

    aput-object v2, v0, v1

    const/16 v1, 0x9

    const-string v2, "authtype"

    aput-object v2, v0, v1

    const/16 v1, 0xa

    const-string v2, "mvno_type"

    aput-object v2, v0, v1

    const/16 v1, 0xb

    const-string v2, "mvno_match_data"

    aput-object v2, v0, v1

    const/16 v1, 0xc

    const-string v2, "proxy"

    aput-object v2, v0, v1

    const/16 v1, 0xd

    const-string v2, "port"

    aput-object v2, v0, v1

    const/16 v1, 0xe

    const-string v2, "server"

    aput-object v2, v0, v1

    const/16 v1, 0xf

    const-string v2, "user"

    aput-object v2, v0, v1

    const/16 v1, 0x10

    const-string v2, "password"

    aput-object v2, v0, v1

    sput-object v0, Lcom/android/mms/service/ApnSettings;->APN_PROJECTION:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    .locals 0
    .param p1, "mmscUrl"    # Ljava/lang/String;
    .param p2, "proxyAddr"    # Ljava/lang/String;
    .param p3, "proxyPort"    # I
    .param p4, "debugText"    # Ljava/lang/String;

    .prologue
    .line 186
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 187
    iput-object p1, p0, Lcom/android/mms/service/ApnSettings;->mServiceCenter:Ljava/lang/String;

    .line 188
    iput-object p2, p0, Lcom/android/mms/service/ApnSettings;->mProxyAddress:Ljava/lang/String;

    .line 189
    iput p3, p0, Lcom/android/mms/service/ApnSettings;->mProxyPort:I

    .line 190
    iput-object p4, p0, Lcom/android/mms/service/ApnSettings;->mDebugText:Ljava/lang/String;

    .line 191
    return-void
.end method

.method private static getDebugText(Landroid/database/Cursor;)Ljava/lang/String;
    .locals 6
    .param p0, "cursor"    # Landroid/database/Cursor;

    .prologue
    .line 165
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    .line 166
    .local v2, "sb":Ljava/lang/StringBuilder;
    const-string v4, "APN ["

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 167
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_0
    invoke-interface {p0}, Landroid/database/Cursor;->getColumnCount()I

    move-result v4

    if-ge v0, v4, :cond_2

    .line 168
    invoke-interface {p0, v0}, Landroid/database/Cursor;->getColumnName(I)Ljava/lang/String;

    move-result-object v1

    .line 169
    .local v1, "name":Ljava/lang/String;
    invoke-interface {p0, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 170
    .local v3, "value":Ljava/lang/String;
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_0

    .line 167
    :goto_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 173
    :cond_0
    if-lez v0, :cond_1

    .line 174
    const/16 v4, 0x20

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 176
    :cond_1
    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const/16 v5, 0x3d

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 178
    .end local v1    # "name":Ljava/lang/String;
    .end local v3    # "value":Ljava/lang/String;
    :cond_2
    const-string v4, "]"

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 179
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    return-object v4
.end method

.method private static isValidApnType(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 6
    .param p0, "types"    # Ljava/lang/String;
    .param p1, "requestType"    # Ljava/lang/String;

    .prologue
    const/4 v4, 0x1

    .line 211
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_1

    .line 220
    :cond_0
    :goto_0
    return v4

    .line 214
    :cond_1
    const-string v5, ","

    invoke-virtual {p0, v5}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .local v0, "arr$":[Ljava/lang/String;
    array-length v2, v0

    .local v2, "len$":I
    const/4 v1, 0x0

    .local v1, "i$":I
    :goto_1
    if-ge v1, v2, :cond_2

    aget-object v3, v0, v1

    .line 215
    .local v3, "type":Ljava/lang/String;
    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    .line 216
    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_0

    const-string v5, "*"

    invoke-virtual {v3, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_0

    .line 214
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 220
    .end local v3    # "type":Ljava/lang/String;
    :cond_2
    const/4 v4, 0x0

    goto :goto_0
.end method

.method public static load(Landroid/content/Context;Ljava/lang/String;I)Lcom/android/mms/service/ApnSettings;
    .locals 13
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "apnName"    # Ljava/lang/String;
    .param p2, "subId"    # I
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/android/mms/service/exception/ApnException;
        }
    .end annotation

    .prologue
    .line 95
    const-string v0, "MmsService"

    const/4 v1, 0x2

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 96
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "ApnSettings: apnName "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 100
    :cond_0
    const/4 v4, 0x0

    .line 101
    .local v4, "selection":Ljava/lang/String;
    const/4 v5, 0x0

    .line 102
    .local v5, "selectionArgs":[Ljava/lang/String;
    if-eqz p1, :cond_5

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    .line 103
    :goto_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 105
    const-string v4, "apn=?"

    .line 106
    const/4 v0, 0x1

    new-array v5, v0, [Ljava/lang/String;

    .end local v5    # "selectionArgs":[Ljava/lang/String;
    const/4 v0, 0x0

    aput-object p1, v5, v0

    .line 108
    .restart local v5    # "selectionArgs":[Ljava/lang/String;
    :cond_1
    const/4 v7, 0x0

    .line 110
    .local v7, "cursor":Landroid/database/Cursor;
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    sget-object v0, Landroid/provider/Telephony$Carriers;->CONTENT_URI:Landroid/net/Uri;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "/subId/"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v0, v2}, Landroid/net/Uri;->withAppendedPath(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    sget-object v3, Lcom/android/mms/service/ApnSettings;->APN_PROJECTION:[Ljava/lang/String;

    const/4 v6, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v6}, Landroid/database/sqlite/SqliteWrapper;->query(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v7

    .line 118
    if-eqz v7, :cond_7

    .line 119
    const/4 v9, 0x0

    .line 120
    .local v9, "mmscUrl":Ljava/lang/String;
    const/4 v11, 0x0

    .line 121
    .local v11, "proxyAddress":Ljava/lang/String;
    const/4 v12, -0x1

    .line 122
    .local v12, "proxyPort":I
    :cond_2
    invoke-interface {v7}, Landroid/database/Cursor;->moveToNext()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 124
    const/4 v0, 0x0

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "mms"

    invoke-static {v0, v1}, Lcom/android/mms/service/ApnSettings;->isValidApnType(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 126
    const/4 v0, 0x1

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/android/mms/service/ApnSettings;->trimWithNullCheck(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 127
    invoke-static {v9}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 130
    invoke-static {v9}, Landroid/net/NetworkUtils;->trimV4AddrZeros(Ljava/lang/String;)Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result-object v9

    .line 132
    :try_start_1
    new-instance v0, Ljava/net/URI;

    invoke-direct {v0, v9}, Ljava/net/URI;-><init>(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/net/URISyntaxException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 136
    const/4 v0, 0x2

    :try_start_2
    invoke-interface {v7, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/android/mms/service/ApnSettings;->trimWithNullCheck(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 137
    invoke-static {v11}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 138
    invoke-static {v11}, Landroid/net/NetworkUtils;->trimV4AddrZeros(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 139
    const/4 v0, 0x3

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/android/mms/service/ApnSettings;->trimWithNullCheck(Ljava/lang/String;)Ljava/lang/String;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    move-result-object v10

    .line 141
    .local v10, "portString":Ljava/lang/String;
    if-eqz v10, :cond_3

    .line 143
    :try_start_3
    invoke-static {v10}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I
    :try_end_3
    .catch Ljava/lang/NumberFormatException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-result v12

    .line 150
    .end local v10    # "portString":Ljava/lang/String;
    :cond_3
    :try_start_4
    new-instance v0, Lcom/android/mms/service/ApnSettings;

    invoke-static {v7}, Lcom/android/mms/service/ApnSettings;->getDebugText(Landroid/database/Cursor;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v9, v11, v12, v1}, Lcom/android/mms/service/ApnSettings;-><init>(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 157
    if-eqz v7, :cond_4

    .line 158
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    :cond_4
    return-object v0

    .line 102
    .end local v7    # "cursor":Landroid/database/Cursor;
    .end local v9    # "mmscUrl":Ljava/lang/String;
    .end local v11    # "proxyAddress":Ljava/lang/String;
    .end local v12    # "proxyPort":I
    :cond_5
    const/4 p1, 0x0

    goto/16 :goto_0

    .line 133
    .restart local v7    # "cursor":Landroid/database/Cursor;
    .restart local v9    # "mmscUrl":Ljava/lang/String;
    .restart local v11    # "proxyAddress":Ljava/lang/String;
    .restart local v12    # "proxyPort":I
    :catch_0
    move-exception v8

    .line 134
    .local v8, "e":Ljava/net/URISyntaxException;
    :try_start_5
    new-instance v0, Lcom/android/mms/service/exception/ApnException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid MMSC url "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/android/mms/service/exception/ApnException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 157
    .end local v8    # "e":Ljava/net/URISyntaxException;
    .end local v9    # "mmscUrl":Ljava/lang/String;
    .end local v11    # "proxyAddress":Ljava/lang/String;
    .end local v12    # "proxyPort":I
    :catchall_0
    move-exception v0

    if-eqz v7, :cond_6

    .line 158
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    :cond_6
    throw v0

    .line 144
    .restart local v9    # "mmscUrl":Ljava/lang/String;
    .restart local v10    # "portString":Ljava/lang/String;
    .restart local v11    # "proxyAddress":Ljava/lang/String;
    .restart local v12    # "proxyPort":I
    :catch_1
    move-exception v8

    .line 145
    .local v8, "e":Ljava/lang/NumberFormatException;
    :try_start_6
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid port "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 146
    new-instance v0, Lcom/android/mms/service/exception/ApnException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid port "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/android/mms/service/exception/ApnException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 157
    .end local v8    # "e":Ljava/lang/NumberFormatException;
    .end local v9    # "mmscUrl":Ljava/lang/String;
    .end local v10    # "portString":Ljava/lang/String;
    .end local v11    # "proxyAddress":Ljava/lang/String;
    .end local v12    # "proxyPort":I
    :cond_7
    if-eqz v7, :cond_8

    .line 158
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    .line 161
    :cond_8
    new-instance v0, Lcom/android/mms/service/exception/ApnException;

    const-string v1, "Can not find valid APN"

    invoke-direct {v0, v1}, Lcom/android/mms/service/exception/ApnException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static trimWithNullCheck(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p0, "value"    # Ljava/lang/String;

    .prologue
    .line 183
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public getMmscUrl()Ljava/lang/String;
    .locals 1

    .prologue
    .line 194
    iget-object v0, p0, Lcom/android/mms/service/ApnSettings;->mServiceCenter:Ljava/lang/String;

    return-object v0
.end method

.method public getProxyAddress()Ljava/lang/String;
    .locals 1

    .prologue
    .line 198
    iget-object v0, p0, Lcom/android/mms/service/ApnSettings;->mProxyAddress:Ljava/lang/String;

    return-object v0
.end method

.method public getProxyPort()I
    .locals 1

    .prologue
    .line 202
    iget v0, p0, Lcom/android/mms/service/ApnSettings;->mProxyPort:I

    return v0
.end method

.method public isProxySet()Z
    .locals 1

    .prologue
    .line 206
    iget-object v0, p0, Lcom/android/mms/service/ApnSettings;->mProxyAddress:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public toString()Ljava/lang/String;
    .locals 1

    .prologue
    .line 224
    iget-object v0, p0, Lcom/android/mms/service/ApnSettings;->mDebugText:Ljava/lang/String;

    return-object v0
.end method
