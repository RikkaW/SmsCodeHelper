.class public Lcom/android/mms/service/SendRequest;
.super Lcom/android/mms/service/MmsRequest;
.source "SendRequest.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/mms/service/SendRequest$1;,
        Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;,
        Lcom/android/mms/service/SendRequest$CarrierSendManager;
    }
.end annotation


# instance fields
.field private final mLocationUrl:Ljava/lang/String;

.field private mPduData:[B

.field private final mPduUri:Landroid/net/Uri;

.field private final mSentIntent:Landroid/app/PendingIntent;


# direct methods
.method public constructor <init>(Lcom/android/mms/service/MmsRequest$RequestManager;ILandroid/net/Uri;Landroid/net/Uri;Ljava/lang/String;Landroid/app/PendingIntent;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 6
    .param p1, "manager"    # Lcom/android/mms/service/MmsRequest$RequestManager;
    .param p2, "subId"    # I
    .param p3, "contentUri"    # Landroid/net/Uri;
    .param p4, "messageUri"    # Landroid/net/Uri;
    .param p5, "locationUrl"    # Ljava/lang/String;
    .param p6, "sentIntent"    # Landroid/app/PendingIntent;
    .param p7, "creator"    # Ljava/lang/String;
    .param p8, "configOverrides"    # Landroid/os/Bundle;

    .prologue
    .line 81
    move-object v0, p0

    move-object v1, p1

    move-object v2, p4

    move v3, p2

    move-object v4, p7

    move-object v5, p8

    invoke-direct/range {v0 .. v5}, Lcom/android/mms/service/MmsRequest;-><init>(Lcom/android/mms/service/MmsRequest$RequestManager;Landroid/net/Uri;ILjava/lang/String;Landroid/os/Bundle;)V

    .line 82
    iput-object p3, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    .line 83
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    .line 84
    iput-object p5, p0, Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;

    .line 85
    iput-object p6, p0, Lcom/android/mms/service/SendRequest;->mSentIntent:Landroid/app/PendingIntent;

    .line 86
    return-void
.end method

.method public constructor <init>(Lcom/android/mms/service/MmsRequest$RequestManager;I[BLandroid/net/Uri;Ljava/lang/String;Landroid/app/PendingIntent;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 6
    .param p1, "manager"    # Lcom/android/mms/service/MmsRequest$RequestManager;
    .param p2, "subId"    # I
    .param p3, "pduData"    # [B
    .param p4, "messageUri"    # Landroid/net/Uri;
    .param p5, "locationUrl"    # Ljava/lang/String;
    .param p6, "sentIntent"    # Landroid/app/PendingIntent;
    .param p7, "creator"    # Ljava/lang/String;
    .param p8, "configOverrides"    # Landroid/os/Bundle;

    .prologue
    .line 92
    move-object v0, p0

    move-object v1, p1

    move-object v2, p4

    move v3, p2

    move-object v4, p7

    move-object v5, p8

    invoke-direct/range {v0 .. v5}, Lcom/android/mms/service/MmsRequest;-><init>(Lcom/android/mms/service/MmsRequest$RequestManager;Landroid/net/Uri;ILjava/lang/String;Landroid/os/Bundle;)V

    .line 93
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    .line 94
    iput-object p3, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    .line 95
    iput-object p5, p0, Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;

    .line 96
    iput-object p6, p0, Lcom/android/mms/service/SendRequest;->mSentIntent:Landroid/app/PendingIntent;

    .line 97
    return-void
.end method

.method static synthetic access$100(Lcom/android/mms/service/SendRequest;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/SendRequest;

    .prologue
    .line 73
    iget-object v0, p0, Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$200(Lcom/android/mms/service/SendRequest;)Landroid/net/Uri;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/SendRequest;

    .prologue
    .line 73
    iget-object v0, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    return-object v0
.end method

.method private readPduFromContentUri()Z
    .locals 5

    .prologue
    const/4 v2, 0x1

    .line 346
    const-string v3, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "readPduFromContentUri"

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    if-nez v1, :cond_0

    const/4 v1, 0x0

    :goto_0
    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v3, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 348
    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    if-eqz v1, :cond_1

    .line 353
    :goto_1
    return v2

    .line 346
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    invoke-virtual {v1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    .line 351
    :cond_1
    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    invoke-virtual {v1}, Lcom/android/mms/service/MmsConfig$Overridden;->getMaxMessageSize()I

    move-result v0

    .line 352
    .local v0, "bytesTobeRead":I
    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mRequestManager:Lcom/android/mms/service/MmsRequest$RequestManager;

    iget-object v3, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    invoke-interface {v1, v3, v0}, Lcom/android/mms/service/MmsRequest$RequestManager;->readPduFromContentUri(Landroid/net/Uri;I)[B

    move-result-object v1

    iput-object v1, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    .line 353
    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    if-eqz v1, :cond_2

    move v1, v2

    :goto_2
    move v2, v1

    goto :goto_1

    :cond_2
    const/4 v1, 0x0

    goto :goto_2
.end method


# virtual methods
.method protected checkResponse([B)I
    .locals 7
    .param p1, "response"    # [B

    .prologue
    const/4 v2, 0x1

    const/4 v3, -0x1

    .line 476
    if-eqz p1, :cond_0

    array-length v4, p1

    if-nez v4, :cond_2

    .line 477
    :cond_0
    const-string v2, "MmsService"

    const-string v4, "checkResponse get response is null, maybe is not a normal sending"

    invoke-static {v2, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    move v2, v3

    .line 495
    :cond_1
    :goto_0
    return v2

    .line 484
    :cond_2
    const-string v4, "MmsService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "SendConf length = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    array-length v6, p1

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 485
    new-instance v4, Lcom/meizu/android/mms/pdu/MzPduParser;

    invoke-direct {v4, p1}, Lcom/meizu/android/mms/pdu/MzPduParser;-><init>([B)V

    invoke-virtual {v4}, Lcom/meizu/android/mms/pdu/MzPduParser;->parse()Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v0

    check-cast v0, Lcom/meizu/android/mms/pdu/MzSendConf;

    .line 486
    .local v0, "conf":Lcom/meizu/android/mms/pdu/MzSendConf;
    if-nez v0, :cond_3

    .line 487
    const-string v3, "MmsService"

    const-string v4, "No M-Send.conf received."

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 490
    :cond_3
    invoke-virtual {v0}, Lcom/meizu/android/mms/pdu/MzSendConf;->getResponseStatus()I

    move-result v1

    .line 491
    .local v1, "respStatus":I
    const-string v4, "MmsService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "checkResponse get response  = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 492
    const/16 v4, 0x80

    if-ne v1, v4, :cond_1

    move v2, v3

    .line 493
    goto :goto_0
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
    .line 102
    invoke-virtual {p2}, Lcom/android/mms/service/MmsNetworkManager;->getOrCreateHttpClient()Lcom/android/mms/service/MmsHttpClient;

    move-result-object v0

    .line 103
    .local v0, "mmsHttpClient":Lcom/android/mms/service/MmsHttpClient;
    if-nez v0, :cond_0

    .line 104
    const-string v1, "MmsService"

    const-string v2, "MMS network is not ready!"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 105
    new-instance v1, Lcom/android/mms/service/exception/MmsHttpException;

    const/4 v2, 0x0

    const-string v3, "MMS network is not ready"

    invoke-direct {v1, v2, v3}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;)V

    throw v1

    .line 107
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;

    :goto_0
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    const-string v3, "POST"

    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->isProxySet()Z

    move-result v4

    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->getProxyAddress()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->getProxyPort()I

    move-result v6

    iget-object v7, p0, Lcom/android/mms/service/SendRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    invoke-virtual/range {v0 .. v7}, Lcom/android/mms/service/MmsHttpClient;->execute(Ljava/lang/String;[BLjava/lang/String;ZLjava/lang/String;ILcom/android/mms/service/MmsConfig$Overridden;)[B

    move-result-object v1

    return-object v1

    :cond_1
    invoke-virtual {p3}, Lcom/android/mms/service/ApnSettings;->getMmscUrl()Ljava/lang/String;

    move-result-object v1

    goto :goto_0
.end method

.method protected getPendingIntent()Landroid/app/PendingIntent;
    .locals 1

    .prologue
    .line 119
    iget-object v0, p0, Lcom/android/mms/service/SendRequest;->mSentIntent:Landroid/app/PendingIntent;

    return-object v0
.end method

.method public getRetryIndex(Landroid/content/Context;Landroid/net/Uri;)I
    .locals 13
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "uri"    # Landroid/net/Uri;

    .prologue
    const/4 v3, 0x0

    .line 523
    invoke-static {p2}, Landroid/content/ContentUris;->parseId(Landroid/net/Uri;)J

    move-result-wide v10

    .line 526
    .local v10, "msgId":J
    sget-object v0, Landroid/provider/Telephony$MmsSms$PendingMessages;->CONTENT_URI:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v12

    .line 527
    .local v12, "uriBuilder":Landroid/net/Uri$Builder;
    const-string v0, "protocol"

    const-string v1, "mms"

    invoke-virtual {v12, v0, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    .line 528
    const-string v0, "message"

    invoke-static {v10, v11}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v12, v0, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    .line 529
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    invoke-virtual {v12}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v2

    move-object v0, p1

    move-object v4, v3

    move-object v5, v3

    move-object v6, v3

    invoke-static/range {v0 .. v6}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->query(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v7

    .line 532
    .local v7, "cursor":Landroid/database/Cursor;
    const/4 v9, -0x1

    .line 533
    .local v9, "retryIndex":I
    if-eqz v7, :cond_1

    .line 535
    :try_start_0
    invoke-interface {v7}, Landroid/database/Cursor;->getCount()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    invoke-interface {v7}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 536
    const-string v0, "retry_index"

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getColumnIndexOrThrow(Ljava/lang/String;)I

    move-result v0

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getInt(I)I

    move-result v9

    .line 538
    const-string v0, "err_type"

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getColumnIndexOrThrow(Ljava/lang/String;)I

    move-result v0

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getInt(I)I

    move-result v8

    .line 540
    .local v8, "errorType":I
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "queryPendingMessageRetryIndex, retryIndex = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", errorType = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 543
    .end local v8    # "errorType":I
    :cond_0
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    .line 546
    :cond_1
    return v9

    .line 543
    :catchall_0
    move-exception v0

    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    throw v0
.end method

.method public markPendingMessageErrorType(Landroid/content/Context;Landroid/net/Uri;)V
    .locals 8
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "uri"    # Landroid/net/Uri;

    .prologue
    .line 509
    new-instance v3, Landroid/content/ContentValues;

    const/4 v0, 0x2

    invoke-direct {v3, v0}, Landroid/content/ContentValues;-><init>(I)V

    .line 510
    .local v3, "pendingvalues":Landroid/content/ContentValues;
    invoke-static {p2}, Landroid/content/ContentUris;->parseId(Landroid/net/Uri;)J

    move-result-wide v6

    .line 513
    .local v6, "msgId":J
    const-string v0, "err_type"

    const/16 v1, 0x13

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v3, v0, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 515
    const-string v0, "retry_index"

    const/4 v1, 0x4

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v3, v0, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 517
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    sget-object v2, Landroid/provider/Telephony$MmsSms$PendingMessages;->CONTENT_URI:Landroid/net/Uri;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "msg_id="

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x0

    move-object v0, p1

    invoke-static/range {v0 .. v5}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    .line 520
    return-void
.end method

.method protected persistIfRequired(Landroid/content/Context;I[B)Landroid/net/Uri;
    .locals 14
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "result"    # I
    .param p3, "response"    # [B

    .prologue
    .line 278
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    if-nez v2, :cond_0

    .line 279
    const/4 v2, 0x0

    .line 339
    :goto_0
    return-object v2

    .line 281
    :cond_0
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v10

    .line 283
    .local v10, "identity":J
    const/4 v13, 0x0

    .line 284
    .local v13, "sendConf":Lcom/meizu/android/mms/pdu/MzSendConf;
    if-eqz p3, :cond_1

    :try_start_0
    move-object/from16 v0, p3

    array-length v2, v0

    if-lez v2, :cond_1

    .line 288
    new-instance v2, Lcom/meizu/android/mms/pdu/MzPduParser;

    move-object/from16 v0, p3

    invoke-direct {v2, v0}, Lcom/meizu/android/mms/pdu/MzPduParser;-><init>([B)V

    invoke-virtual {v2}, Lcom/meizu/android/mms/pdu/MzPduParser;->parse()Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v9

    .line 289
    .local v9, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    if-eqz v9, :cond_1

    instance-of v2, v9, Lcom/meizu/android/mms/pdu/MzSendConf;

    if-eqz v2, :cond_1

    .line 290
    move-object v0, v9

    check-cast v0, Lcom/meizu/android/mms/pdu/MzSendConf;

    move-object v13, v0

    .line 294
    .end local v9    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    :cond_1
    new-instance v5, Landroid/content/ContentValues;

    invoke-direct {v5}, Landroid/content/ContentValues;-><init>()V

    .line 295
    .local v5, "values":Landroid/content/ContentValues;
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    invoke-virtual {p0, p1, v2}, Lcom/android/mms/service/SendRequest;->getRetryIndex(Landroid/content/Context;Landroid/net/Uri;)I

    move-result v12

    .line 297
    .local v12, "retryIndex":I
    if-nez v13, :cond_4

    .line 298
    const/4 v2, 0x4

    if-ge v12, v2, :cond_3

    .line 299
    const-string v2, "resp_st"

    const/16 v3, 0x86

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 321
    :goto_1
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "MzMmsServiceUtils.isCTVersion() = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-static {}, Lcom/meizu/mms/utils/MzMmsServiceUtils;->isCTVersion()Z

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ", result = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    move/from16 v0, p2

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ", retryIndex = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v12}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 322
    invoke-static {}, Lcom/meizu/mms/utils/MzMmsServiceUtils;->isCTVersion()Z

    move-result v2

    if-eqz v2, :cond_2

    const/4 v2, -0x1

    move/from16 v0, p2

    if-eq v0, v2, :cond_2

    .line 324
    const-string v2, "resp_st"

    const/16 v3, 0x2537

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 326
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    invoke-virtual {p0, p1, v2}, Lcom/android/mms/service/SendRequest;->markPendingMessageErrorType(Landroid/content/Context;Landroid/net/Uri;)V

    .line 329
    :cond_2
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v2, p1

    invoke-static/range {v2 .. v7}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    .line 331
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 337
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .line 302
    :cond_3
    :try_start_1
    const-string v2, "resp_st"

    const/16 v3, 0x2537

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 304
    const-string v2, "msg_box"

    const/4 v3, 0x5

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V
    :try_end_1
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    .line 332
    .end local v5    # "values":Landroid/content/ContentValues;
    .end local v12    # "retryIndex":I
    :catch_0
    move-exception v8

    .line 333
    .local v8, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_2
    const-string v2, "MmsService"

    const-string v3, "SendRequest.persistIfRequired: can not update message"

    invoke-static {v2, v3, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 337
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 339
    .end local v8    # "e":Landroid/database/sqlite/SQLiteException;
    :goto_2
    const/4 v2, 0x0

    goto/16 :goto_0

    .line 307
    .restart local v5    # "values":Landroid/content/ContentValues;
    .restart local v12    # "retryIndex":I
    :cond_4
    const/4 v2, -0x1

    move/from16 v0, p2

    if-ne v0, v2, :cond_5

    .line 308
    :try_start_3
    const-string v2, "msg_box"

    const/4 v3, 0x2

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 310
    :cond_5
    const-string v2, "resp_st"

    invoke-virtual {v13}, Lcom/meizu/android/mms/pdu/MzSendConf;->getResponseStatus()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 311
    invoke-virtual {v13}, Lcom/meizu/android/mms/pdu/MzSendConf;->getResponseStatus()I

    move-result v2

    invoke-static {v2}, Lcom/meizu/mms/utils/MzMmsServiceUtils;->checkIsFailedRespStatus(I)Z

    move-result v2

    if-eqz v2, :cond_6

    .line 312
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    invoke-virtual {p0, p1, v2}, Lcom/android/mms/service/SendRequest;->markPendingMessageErrorType(Landroid/content/Context;Landroid/net/Uri;)V

    .line 313
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "SendRequest.persistIfRequired: Server returned an error code: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v13}, Lcom/meizu/android/mms/pdu/MzSendConf;->getResponseStatus()I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 317
    :cond_6
    const-string v2, "m_id"

    invoke-virtual {v13}, Lcom/meizu/android/mms/pdu/MzSendConf;->getMessageId()[B

    move-result-object v3

    invoke-static {v3}, Lcom/meizu/android/mms/pdu/MzPduPersister;->toIsoString([B)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_3
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_3 .. :try_end_3} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    goto/16 :goto_1

    .line 334
    .end local v5    # "values":Landroid/content/ContentValues;
    .end local v12    # "retryIndex":I
    :catch_1
    move-exception v8

    .line 335
    .local v8, "e":Ljava/lang/RuntimeException;
    :try_start_4
    const-string v2, "MmsService"

    const-string v3, "SendRequest.persistIfRequired: can not parse response"

    invoke-static {v2, v3, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 337
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_2

    .end local v8    # "e":Ljava/lang/RuntimeException;
    :catchall_0
    move-exception v2

    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v2
.end method

.method protected prepareForHttpRequest()Z
    .locals 1

    .prologue
    .line 378
    invoke-direct {p0}, Lcom/android/mms/service/SendRequest;->readPduFromContentUri()Z

    move-result v0

    return v0
.end method

.method protected putOriginalUrl(Landroid/content/Intent;)V
    .locals 2
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 501
    iget-object v0, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    if-eqz v0, :cond_0

    .line 502
    const-string v0, "oriuri"

    iget-object v1, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    invoke-virtual {v1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 506
    :goto_0
    return-void

    .line 504
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
    .line 397
    iget-object v0, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    if-eqz v0, :cond_0

    .line 398
    iget-object v0, p0, Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;

    const/4 v1, 0x1

    invoke-virtual {p1, v0, v1}, Landroid/content/Context;->revokeUriPermission(Landroid/net/Uri;I)V

    .line 400
    :cond_0
    return-void
.end method

.method public storeInOutbox(Landroid/content/Context;)V
    .locals 14
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v9, 0x1

    .line 128
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v10

    .line 131
    .local v10, "identity":J
    :try_start_0
    invoke-direct {p0}, Lcom/android/mms/service/SendRequest;->readPduFromContentUri()Z

    move-result v2

    if-nez v2, :cond_0

    .line 132
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: empty PDU"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 191
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 193
    :goto_0
    return-void

    .line 135
    :cond_0
    :try_start_1
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    if-nez v2, :cond_6

    .line 141
    new-instance v2, Lcom/meizu/android/mms/pdu/MzPduParser;

    iget-object v3, p0, Lcom/android/mms/service/SendRequest;->mPduData:[B

    invoke-direct {v2, v3}, Lcom/meizu/android/mms/pdu/MzPduParser;-><init>([B)V

    invoke-virtual {v2}, Lcom/meizu/android/mms/pdu/MzPduParser;->parse()Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v1

    .line 142
    .local v1, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    if-nez v1, :cond_1

    .line 143
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: can\'t parse input PDU"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Lcom/google/android/mms/MmsException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 191
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 146
    :cond_1
    :try_start_2
    instance-of v2, v1, Lcom/meizu/android/mms/pdu/MzSendReq;

    if-nez v2, :cond_2

    .line 147
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: not SendReq"

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_2
    .catch Lcom/google/android/mms/MmsException; {:try_start_2 .. :try_end_2} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 191
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 150
    :cond_2
    :try_start_3
    invoke-static {p1}, Lcom/meizu/android/mms/pdu/MzPduPersister;->getPduPersister(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;

    move-result-object v0

    .line 151
    .local v0, "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    sget-object v2, Landroid/provider/Telephony$Mms$Outbox;->CONTENT_URI:Landroid/net/Uri;

    const/4 v3, 0x1

    const/4 v4, 0x1

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Lcom/meizu/android/mms/pdu/MzPduPersister;->persist(Lcom/meizu/android/mms/pdu/MzGenericPdu;Landroid/net/Uri;ZZLjava/util/HashMap;)Landroid/net/Uri;

    move-result-object v2

    iput-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    .line 157
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    if-nez v2, :cond_3

    .line 158
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: can not persist message"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_3
    .catch Lcom/google/android/mms/MmsException; {:try_start_3 .. :try_end_3} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 191
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 161
    :cond_3
    :try_start_4
    new-instance v5, Landroid/content/ContentValues;

    const/4 v2, 0x5

    invoke-direct {v5, v2}, Landroid/content/ContentValues;-><init>(I)V

    .line 162
    .local v5, "values":Landroid/content/ContentValues;
    const-string v2, "date"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    const-wide/16 v12, 0x3e8

    div-long/2addr v6, v12

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 163
    const-string v2, "read"

    const/4 v3, 0x1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 164
    const-string v2, "seen"

    const/4 v3, 0x1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 165
    iget-object v2, p0, Lcom/android/mms/service/SendRequest;->mCreator:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_4

    .line 166
    const-string v2, "creator"

    iget-object v3, p0, Lcom/android/mms/service/SendRequest;->mCreator:Ljava/lang/String;

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 168
    :cond_4
    const-string v2, "sub_id"

    iget v3, p0, Lcom/android/mms/service/SendRequest;->mSubId:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 169
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v2, p1

    invoke-static/range {v2 .. v7}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v2

    if-eq v2, v9, :cond_5

    .line 171
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: failed to update message"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_4
    .catch Lcom/google/android/mms/MmsException; {:try_start_4 .. :try_end_4} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_4 .. :try_end_4} :catch_1
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 191
    .end local v0    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    .end local v1    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    :cond_5
    :goto_1
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .line 176
    .end local v5    # "values":Landroid/content/ContentValues;
    :cond_6
    :try_start_5
    new-instance v5, Landroid/content/ContentValues;

    const/4 v2, 0x3

    invoke-direct {v5, v2}, Landroid/content/ContentValues;-><init>(I)V

    .line 178
    .restart local v5    # "values":Landroid/content/ContentValues;
    const-string v2, "date"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    const-wide/16 v12, 0x3e8

    div-long/2addr v6, v12

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 179
    const-string v2, "msg_box"

    const/4 v3, 0x4

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 180
    const-string v2, "sub_id"

    iget v3, p0, Lcom/android/mms/service/SendRequest;->mSubId:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 181
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/SendRequest;->mMessageUri:Landroid/net/Uri;

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v2, p1

    invoke-static/range {v2 .. v7}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v2

    if-eq v2, v9, :cond_5

    .line 183
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: failed to update message"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_5
    .catch Lcom/google/android/mms/MmsException; {:try_start_5 .. :try_end_5} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    goto :goto_1

    .line 186
    .end local v5    # "values":Landroid/content/ContentValues;
    :catch_0
    move-exception v8

    .line 187
    .local v8, "e":Lcom/google/android/mms/MmsException;
    :try_start_6
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: can not persist/update message"

    invoke-static {v2, v3, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 191
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .line 188
    .end local v8    # "e":Lcom/google/android/mms/MmsException;
    :catch_1
    move-exception v8

    .line 189
    .local v8, "e":Ljava/lang/RuntimeException;
    :try_start_7
    const-string v2, "MmsService"

    const-string v3, "SendRequest.storeInOutbox: unexpected parsing failure"

    invoke-static {v2, v3, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    .line 191
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .end local v8    # "e":Ljava/lang/RuntimeException;
    :catchall_0
    move-exception v2

    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v2
.end method

.method protected transferResponse(Landroid/content/Intent;[B)Z
    .locals 1
    .param p1, "fillIn"    # Landroid/content/Intent;
    .param p2, "response"    # [B

    .prologue
    .line 366
    if-eqz p2, :cond_0

    .line 367
    const-string v0, "android.telephony.extra.MMS_DATA"

    invoke-virtual {p1, v0, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;[B)Landroid/content/Intent;

    .line 369
    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method public trySendingByCarrierApp(Landroid/content/Context;Ljava/lang/String;)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "carrierMessagingServicePackage"    # Ljava/lang/String;

    .prologue
    .line 389
    new-instance v0, Lcom/android/mms/service/SendRequest$CarrierSendManager;

    const/4 v2, 0x0

    invoke-direct {v0, p0, v2}, Lcom/android/mms/service/SendRequest$CarrierSendManager;-><init>(Lcom/android/mms/service/SendRequest;Lcom/android/mms/service/SendRequest$1;)V

    .line 390
    .local v0, "carrierSendManger":Lcom/android/mms/service/SendRequest$CarrierSendManager;
    new-instance v1, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;

    invoke-direct {v1, p0, p1, v0}, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;-><init>(Lcom/android/mms/service/SendRequest;Landroid/content/Context;Lcom/android/mms/service/SendRequest$CarrierSendManager;)V

    .line 392
    .local v1, "sendCallback":Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;
    invoke-virtual {v0, p1, p2, v1}, Lcom/android/mms/service/SendRequest$CarrierSendManager;->sendMms(Landroid/content/Context;Ljava/lang/String;Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;)V

    .line 393
    return-void
.end method
