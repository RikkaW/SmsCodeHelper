.class public Lcom/android/mms/service/DownloadRequest;
.super Lcom/android/mms/service/MmsRequest;
.source "DownloadRequest.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/mms/service/DownloadRequest$1;,
        Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;,
        Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;
    }
.end annotation


# instance fields
.field private final mContentUri:Landroid/net/Uri;

.field private final mDownloadedIntent:Landroid/app/PendingIntent;

.field private final mLocationUrl:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/android/mms/service/MmsRequest$RequestManager;ILjava/lang/String;Landroid/net/Uri;Landroid/app/PendingIntent;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 6
    .param p1, "manager"    # Lcom/android/mms/service/MmsRequest$RequestManager;
    .param p2, "subId"    # I
    .param p3, "locationUrl"    # Ljava/lang/String;
    .param p4, "contentUri"    # Landroid/net/Uri;
    .param p5, "downloadedIntent"    # Landroid/app/PendingIntent;
    .param p6, "creator"    # Ljava/lang/String;
    .param p7, "configOverrides"    # Landroid/os/Bundle;

    .prologue
    .line 80
    const/4 v2, 0x0

    move-object v0, p0

    move-object v1, p1

    move v3, p2

    move-object v4, p6

    move-object v5, p7

    invoke-direct/range {v0 .. v5}, Lcom/android/mms/service/MmsRequest;-><init>(Lcom/android/mms/service/MmsRequest$RequestManager;Landroid/net/Uri;ILjava/lang/String;Landroid/os/Bundle;)V

    .line 81
    iput-object p3, p0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    .line 82
    iput-object p5, p0, Lcom/android/mms/service/DownloadRequest;->mDownloadedIntent:Landroid/app/PendingIntent;

    .line 83
    iput-object p4, p0, Lcom/android/mms/service/DownloadRequest;->mContentUri:Landroid/net/Uri;

    .line 84
    return-void
.end method

.method static synthetic access$100(Lcom/android/mms/service/DownloadRequest;)Landroid/net/Uri;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/DownloadRequest;

    .prologue
    .line 69
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mContentUri:Landroid/net/Uri;

    return-object v0
.end method

.method static synthetic access$200(Lcom/android/mms/service/DownloadRequest;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/DownloadRequest;

    .prologue
    .line 69
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    return-object v0
.end method

.method private notifyOfDownload(Landroid/content/Context;)V
    .locals 14
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v5, 0x0

    .line 229
    new-instance v1, Landroid/content/Intent;

    const-string v0, "android.provider.Telephony.MMS_DOWNLOADED"

    invoke-direct {v1, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 230
    .local v1, "intent":Landroid/content/Intent;
    const/high16 v0, 0x8000000

    invoke-virtual {v1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 233
    const/4 v13, 0x0

    .line 235
    .local v13, "users":[I
    :try_start_0
    invoke-static {}, Landroid/app/ActivityManagerNative;->getDefault()Landroid/app/IActivityManager;

    move-result-object v0

    invoke-interface {v0}, Landroid/app/IActivityManager;->getRunningUserIds()[I
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v13

    .line 238
    :goto_0
    if-nez v13, :cond_0

    .line 239
    const/4 v0, 0x1

    new-array v13, v0, [I

    .end local v13    # "users":[I
    const/4 v0, 0x0

    sget-object v3, Landroid/os/UserHandle;->ALL:Landroid/os/UserHandle;

    invoke-virtual {v3}, Landroid/os/UserHandle;->getIdentifier()I

    move-result v3

    aput v3, v13, v0

    .line 241
    .restart local v13    # "users":[I
    :cond_0
    const-string v0, "user"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v12

    check-cast v12, Landroid/os/UserManager;

    .line 246
    .local v12, "userManager":Landroid/os/UserManager;
    array-length v0, v13

    add-int/lit8 v10, v0, -0x1

    .local v10, "i":I
    :goto_1
    if-ltz v10, :cond_4

    .line 247
    new-instance v2, Landroid/os/UserHandle;

    aget v0, v13, v10

    invoke-direct {v2, v0}, Landroid/os/UserHandle;-><init>(I)V

    .line 248
    .local v2, "targetUser":Landroid/os/UserHandle;
    aget v0, v13, v10

    if-eqz v0, :cond_3

    .line 250
    const-string v0, "no_sms"

    invoke-virtual {v12, v0, v2}, Landroid/os/UserManager;->hasUserRestriction(Ljava/lang/String;Landroid/os/UserHandle;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 246
    :cond_1
    :goto_2
    add-int/lit8 v10, v10, -0x1

    goto :goto_1

    .line 254
    :cond_2
    aget v0, v13, v10

    invoke-virtual {v12, v0}, Landroid/os/UserManager;->getUserInfo(I)Landroid/content/pm/UserInfo;

    move-result-object v11

    .line 255
    .local v11, "info":Landroid/content/pm/UserInfo;
    if-eqz v11, :cond_1

    invoke-virtual {v11}, Landroid/content/pm/UserInfo;->isManagedProfile()Z

    move-result v0

    if-nez v0, :cond_1

    .line 259
    .end local v11    # "info":Landroid/content/pm/UserInfo;
    :cond_3
    const-string v3, "android.permission.RECEIVE_MMS"

    const/16 v4, 0x12

    const/4 v7, -0x1

    move-object v0, p1

    move-object v6, v5

    move-object v8, v5

    move-object v9, v5

    invoke-virtual/range {v0 .. v9}, Landroid/content/Context;->sendOrderedBroadcastAsUser(Landroid/content/Intent;Landroid/os/UserHandle;Ljava/lang/String;ILandroid/content/BroadcastReceiver;Landroid/os/Handler;ILjava/lang/String;Landroid/os/Bundle;)V

    goto :goto_2

    .line 265
    .end local v2    # "targetUser":Landroid/os/UserHandle;
    :cond_4
    return-void

    .line 236
    .end local v10    # "i":I
    .end local v12    # "userManager":Landroid/os/UserManager;
    :catch_0
    move-exception v0

    goto :goto_0
.end method


# virtual methods
.method protected checkResponse([B)I
    .locals 1
    .param p1, "response"    # [B

    .prologue
    .line 371
    const/4 v0, -0x1

    return v0
.end method

.method protected doHttp(Landroid/content/Context;Lcom/android/mms/service/MmsNetworkManager;Lcom/android/mms/service/ApnSettings;)[B
    .locals 8
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "netMgr"    # Lcom/android/mms/service/MmsNetworkManager;
    .param p3, "apn"    # Lcom/android/mms/service/ApnSettings;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/android/mms/service/exception/MmsHttpException;
        }
    .end annotation

    .prologue
    .line 89
    invoke-virtual {p2}, Lcom/android/mms/service/MmsNetworkManager;->getOrCreateHttpClient()Lcom/android/mms/service/MmsHttpClient;

    move-result-object v0

    .line 90
    .local v0, "mmsHttpClient":Lcom/android/mms/service/MmsHttpClient;
    if-nez v0, :cond_0

    .line 91
    const-string v1, "MmsService"

    const-string v2, "MMS network is not ready!"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 92
    new-instance v1, Lcom/android/mms/service/exception/MmsHttpException;

    const/4 v2, 0x0

    const-string v3, "MMS network is not ready"

    invoke-direct {v1, v2, v3}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;)V

    throw v1

    .line 94
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    const/4 v2, 0x0

    const-string v3, "GET"

    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->isProxySet()Z

    move-result v4

    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->getProxyAddress()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->getProxyPort()I

    move-result v6

    iget-object v7, p0, Lcom/android/mms/service/DownloadRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    invoke-virtual/range {v0 .. v7}, Lcom/android/mms/service/MmsHttpClient;->execute(Ljava/lang/String;[BLjava/lang/String;ZLjava/lang/String;ILcom/android/mms/service/MmsConfig$Overridden;)[B

    move-result-object v1

    return-object v1
.end method

.method protected getPendingIntent()Landroid/app/PendingIntent;
    .locals 1

    .prologue
    .line 106
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mDownloadedIntent:Landroid/app/PendingIntent;

    return-object v0
.end method

.method protected persistIfRequired(Landroid/content/Context;I[B)Landroid/net/Uri;
    .locals 16
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "result"    # I
    .param p3, "response"    # [B

    .prologue
    .line 119
    invoke-direct/range {p0 .. p1}, Lcom/android/mms/service/DownloadRequest;->notifyOfDownload(Landroid/content/Context;)V

    .line 120
    move-object/from16 v0, p0

    iget-object v4, v0, Lcom/android/mms/service/DownloadRequest;->mRequestManager:Lcom/android/mms/service/MmsRequest$RequestManager;

    invoke-interface {v4}, Lcom/android/mms/service/MmsRequest$RequestManager;->getAutoPersistingPref()Z

    move-result v4

    if-nez v4, :cond_0

    const-string v4, "com.android.mms"

    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/android/mms/service/DownloadRequest;->mCreator:Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_0

    .line 121
    const/4 v6, 0x0

    .line 225
    :goto_0
    return-object v6

    .line 123
    :cond_0
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired"

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 124
    if-eqz p3, :cond_1

    move-object/from16 v0, p3

    array-length v4, v0

    const/4 v5, 0x1

    if-ge v4, v5, :cond_2

    .line 125
    :cond_1
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: empty response"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 126
    const/4 v6, 0x0

    goto :goto_0

    .line 128
    :cond_2
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v12

    .line 134
    .local v12, "identity":J
    :try_start_0
    new-instance v4, Lcom/meizu/android/mms/pdu/MzPduParser;

    move-object/from16 v0, p3

    invoke-direct {v4, v0}, Lcom/meizu/android/mms/pdu/MzPduParser;-><init>([B)V

    invoke-virtual {v4}, Lcom/meizu/android/mms/pdu/MzPduParser;->parse()Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v3

    .line 135
    .local v3, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    if-eqz v3, :cond_3

    instance-of v4, v3, Lcom/meizu/android/mms/pdu/MzRetrieveConf;

    if-nez v4, :cond_4

    .line 136
    :cond_3
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: invalid parsed PDU"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 137
    const/4 v6, 0x0

    .line 223
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 162
    :cond_4
    :try_start_1
    invoke-virtual/range {p1 .. p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v4

    sget-object v5, Landroid/provider/Telephony$Mms;->CONTENT_URI:Landroid/net/Uri;

    const-string v8, "m_type=? AND ct_l =?"

    const/4 v9, 0x2

    new-array v9, v9, [Ljava/lang/String;

    const/4 v11, 0x0

    const/16 v14, 0x82

    invoke-static {v14}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v14

    aput-object v14, v9, v11

    const/4 v11, 0x1

    move-object/from16 v0, p0

    iget-object v14, v0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    aput-object v14, v9, v11

    move-object/from16 v0, p1

    invoke-static {v0, v4, v5, v8, v9}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->delete(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I

    .line 175
    invoke-static/range {p1 .. p1}, Lcom/meizu/android/mms/pdu/MzPduPersister;->getPduPersister(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;

    move-result-object v2

    .line 176
    .local v2, "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    sget-object v4, Landroid/provider/Telephony$Mms$Inbox;->CONTENT_URI:Landroid/net/Uri;

    const/4 v5, 0x1

    const/4 v6, 0x1

    const/4 v7, 0x0

    invoke-virtual/range {v2 .. v7}, Lcom/meizu/android/mms/pdu/MzPduPersister;->persist(Lcom/meizu/android/mms/pdu/MzGenericPdu;Landroid/net/Uri;ZZLjava/util/HashMap;)Landroid/net/Uri;

    move-result-object v6

    .line 182
    .local v6, "messageUri":Landroid/net/Uri;
    if-nez v6, :cond_5

    .line 183
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: can not persist message"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Lcom/google/android/mms/MmsException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_2
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 184
    const/4 v6, 0x0

    .line 223
    .end local v6    # "messageUri":Landroid/net/Uri;
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 187
    .restart local v6    # "messageUri":Landroid/net/Uri;
    :cond_5
    :try_start_2
    new-instance v7, Landroid/content/ContentValues;

    invoke-direct {v7}, Landroid/content/ContentValues;-><init>()V

    .line 188
    .local v7, "values":Landroid/content/ContentValues;
    const-string v4, "date"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    const-wide/16 v14, 0x3e8

    div-long/2addr v8, v14

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 189
    const-string v4, "read"

    const/4 v5, 0x0

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 190
    const-string v4, "seen"

    const/4 v5, 0x0

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 191
    move-object/from16 v0, p0

    iget-object v4, v0, Lcom/android/mms/service/DownloadRequest;->mCreator:Ljava/lang/String;

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_6

    .line 192
    const-string v4, "creator"

    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/android/mms/service/DownloadRequest;->mCreator:Ljava/lang/String;

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 194
    :cond_6
    const-string v4, "sub_id"

    move-object/from16 v0, p0

    iget v5, v0, Lcom/android/mms/service/DownloadRequest;->mSubId:I

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 195
    const-string v4, "ct_l"

    move-object/from16 v0, p0

    iget-object v5, v0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 197
    const-string v4, "association_id"

    new-instance v5, Ljava/lang/Long;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    invoke-direct {v5, v8, v9}, Ljava/lang/Long;-><init>(J)V

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 200
    const-string v4, "imsi"

    move-object/from16 v0, p0

    iget v5, v0, Lcom/android/mms/service/DownloadRequest;->mSubId:I

    invoke-static {v5}, Lcom/meizu/mms/utils/MzMmsServiceUtils;->getSubscriberIdBySubId(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 203
    const-string v4, "sim_id"

    move-object/from16 v0, p0

    iget v5, v0, Lcom/android/mms/service/DownloadRequest;->mSubId:I

    invoke-static {v5}, Lcom/meizu/mms/utils/MzMmsServiceUtils;->getSlotIdBySubId(I)I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v7, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 205
    invoke-virtual/range {p1 .. p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v5

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object/from16 v4, p1

    invoke-static/range {v4 .. v9}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v4

    const/4 v5, 0x1

    if-eq v4, v5, :cond_7

    .line 212
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: can not update message"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_2
    .catch Lcom/google/android/mms/MmsException; {:try_start_2 .. :try_end_2} :catch_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 223
    :cond_7
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .line 216
    .end local v2    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    .end local v3    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .end local v6    # "messageUri":Landroid/net/Uri;
    .end local v7    # "values":Landroid/content/ContentValues;
    :catch_0
    move-exception v10

    .line 217
    .local v10, "e":Lcom/google/android/mms/MmsException;
    :try_start_3
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: can not persist message"

    invoke-static {v4, v5, v10}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 223
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 225
    .end local v10    # "e":Lcom/google/android/mms/MmsException;
    :goto_1
    const/4 v6, 0x0

    goto/16 :goto_0

    .line 218
    :catch_1
    move-exception v10

    .line 219
    .local v10, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_4
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: can not update message"

    invoke-static {v4, v5, v10}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 223
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .line 220
    .end local v10    # "e":Landroid/database/sqlite/SQLiteException;
    :catch_2
    move-exception v10

    .line 221
    .local v10, "e":Ljava/lang/RuntimeException;
    :try_start_5
    const-string v4, "MmsService"

    const-string v5, "DownloadRequest.persistIfRequired: can not parse response"

    invoke-static {v4, v5, v10}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 223
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .end local v10    # "e":Ljava/lang/RuntimeException;
    :catchall_0
    move-exception v4

    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v4
.end method

.method protected prepareForHttpRequest()Z
    .locals 1

    .prologue
    .line 280
    const/4 v0, 0x1

    return v0
.end method

.method protected putOriginalUrl(Landroid/content/Intent;)V
    .locals 2
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 376
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 377
    const-string v0, "oriuri"

    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 381
    :goto_0
    return-void

    .line 379
    :cond_0
    const-string v0, "oriuri"

    const-string v1, ""

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    goto :goto_0
.end method

.method protected revokeUriPermission(Landroid/content/Context;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 299
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mContentUri:Landroid/net/Uri;

    if-eqz v0, :cond_0

    .line 300
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mContentUri:Landroid/net/Uri;

    const/4 v1, 0x2

    invoke-virtual {p1, v0, v1}, Landroid/content/Context;->revokeUriPermission(Landroid/net/Uri;I)V

    .line 302
    :cond_0
    return-void
.end method

.method protected transferResponse(Landroid/content/Intent;[B)Z
    .locals 2
    .param p1, "fillIn"    # Landroid/content/Intent;
    .param p2, "response"    # [B

    .prologue
    .line 275
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest;->mRequestManager:Lcom/android/mms/service/MmsRequest$RequestManager;

    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest;->mContentUri:Landroid/net/Uri;

    invoke-interface {v0, v1, p2}, Lcom/android/mms/service/MmsRequest$RequestManager;->writePduToContentUri(Landroid/net/Uri;[B)Z

    move-result v0

    return v0
.end method

.method public tryDownloadingByCarrierApp(Landroid/content/Context;Ljava/lang/String;)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "carrierMessagingServicePackage"    # Ljava/lang/String;

    .prologue
    .line 290
    new-instance v0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;

    const/4 v2, 0x0

    invoke-direct {v0, p0, v2}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;-><init>(Lcom/android/mms/service/DownloadRequest;Lcom/android/mms/service/DownloadRequest$1;)V

    .line 291
    .local v0, "carrierDownloadManger":Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;
    new-instance v1, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;

    invoke-direct {v1, p0, p1, v0}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;-><init>(Lcom/android/mms/service/DownloadRequest;Landroid/content/Context;Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;)V

    .line 293
    .local v1, "downloadCallback":Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;
    invoke-virtual {v0, p1, p2, v1}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->downloadMms(Landroid/content/Context;Ljava/lang/String;Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;)V

    .line 295
    return-void
.end method
