.class public abstract Lcom/android/mms/service/MmsRequest;
.super Ljava/lang/Object;
.source "MmsRequest.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/mms/service/MmsRequest$CarrierMmsActionCallback;,
        Lcom/android/mms/service/MmsRequest$RequestManager;
    }
.end annotation


# instance fields
.field protected mCreator:Ljava/lang/String;

.field protected mMessageUri:Landroid/net/Uri;

.field protected mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

.field protected mMmsConfigOverrides:Landroid/os/Bundle;

.field protected mRequestManager:Lcom/android/mms/service/MmsRequest$RequestManager;

.field protected mSubId:I


# direct methods
.method public constructor <init>(Lcom/android/mms/service/MmsRequest$RequestManager;Landroid/net/Uri;ILjava/lang/String;Landroid/os/Bundle;)V
    .locals 1
    .param p1, "requestManager"    # Lcom/android/mms/service/MmsRequest$RequestManager;
    .param p2, "messageUri"    # Landroid/net/Uri;
    .param p3, "subId"    # I
    .param p4, "creator"    # Ljava/lang/String;
    .param p5, "configOverrides"    # Landroid/os/Bundle;

    .prologue
    .line 98
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 99
    iput-object p1, p0, Lcom/android/mms/service/MmsRequest;->mRequestManager:Lcom/android/mms/service/MmsRequest$RequestManager;

    .line 100
    iput-object p2, p0, Lcom/android/mms/service/MmsRequest;->mMessageUri:Landroid/net/Uri;

    .line 101
    iput p3, p0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    .line 102
    iput-object p4, p0, Lcom/android/mms/service/MmsRequest;->mCreator:Ljava/lang/String;

    .line 103
    iput-object p5, p0, Lcom/android/mms/service/MmsRequest;->mMmsConfigOverrides:Landroid/os/Bundle;

    .line 104
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/mms/service/MmsRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    .line 105
    return-void
.end method

.method private ensureMmsConfigLoaded()Z
    .locals 3

    .prologue
    .line 112
    iget-object v1, p0, Lcom/android/mms/service/MmsRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    if-nez v1, :cond_0

    .line 114
    invoke-static {}, Lcom/android/mms/service/MmsConfigManager;->getInstance()Lcom/android/mms/service/MmsConfigManager;

    move-result-object v1

    iget v2, p0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    invoke-virtual {v1, v2}, Lcom/android/mms/service/MmsConfigManager;->getMmsConfigBySubId(I)Lcom/android/mms/service/MmsConfig;

    move-result-object v0

    .line 115
    .local v0, "config":Lcom/android/mms/service/MmsConfig;
    if-eqz v0, :cond_0

    .line 116
    new-instance v1, Lcom/android/mms/service/MmsConfig$Overridden;

    iget-object v2, p0, Lcom/android/mms/service/MmsRequest;->mMmsConfigOverrides:Landroid/os/Bundle;

    invoke-direct {v1, v0, v2}, Lcom/android/mms/service/MmsConfig$Overridden;-><init>(Lcom/android/mms/service/MmsConfig;Landroid/os/Bundle;)V

    iput-object v1, p0, Lcom/android/mms/service/MmsRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    .line 119
    .end local v0    # "config":Lcom/android/mms/service/MmsConfig;
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/MmsRequest;->mMmsConfig:Lcom/android/mms/service/MmsConfig$Overridden;

    if-eqz v1, :cond_1

    const/4 v1, 0x1

    :goto_0
    return v1

    :cond_1
    const/4 v1, 0x0

    goto :goto_0
.end method

.method protected static toSmsManagerResult(I)I
    .locals 1
    .param p0, "carrierMessagingAppResult"    # I

    .prologue
    .line 314
    packed-switch p0, :pswitch_data_0

    .line 320
    const/4 v0, 0x1

    :goto_0
    return v0

    .line 316
    :pswitch_0
    const/4 v0, -0x1

    goto :goto_0

    .line 318
    :pswitch_1
    const/4 v0, 0x6

    goto :goto_0

    .line 314
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method


# virtual methods
.method protected abstract checkResponse([B)I
.end method

.method protected abstract doHttp(Landroid/content/Context;Lcom/android/mms/service/MmsNetworkManager;Lcom/android/mms/service/ApnSettings;)[B
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/android/mms/service/exception/MmsHttpException;
        }
    .end annotation
.end method

.method public execute(Landroid/content/Context;Lcom/android/mms/service/MmsNetworkManager;)V
    .locals 18
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "networkManager"    # Lcom/android/mms/service/MmsNetworkManager;

    .prologue
    .line 144
    const/4 v11, 0x1

    .line 145
    .local v11, "result":I
    const/4 v7, 0x0

    .line 146
    .local v7, "httpStatusCode":I
    const/4 v10, 0x0

    .line 147
    .local v10, "response":[B
    const/4 v9, 0x0

    .line 148
    .local v9, "isRequestSuccessed":Z
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: execute"

    invoke-static {v14, v15}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 149
    invoke-direct/range {p0 .. p0}, Lcom/android/mms/service/MmsRequest;->ensureMmsConfigLoaded()Z

    move-result v14

    if-nez v14, :cond_2

    .line 150
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: mms config is not loaded yet"

    invoke-static {v14, v15}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 151
    const/4 v11, 0x7

    .line 218
    :cond_0
    :goto_0
    const/4 v14, -0x1

    if-eq v11, v14, :cond_1

    .line 219
    const-string v14, "MmsService"

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    const-string v16, "pdp connect FAIL. result = "

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v15

    invoke-static {v14, v15}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 221
    :cond_1
    move-object/from16 v0, p0

    move-object/from16 v1, p1

    invoke-virtual {v0, v1, v11, v10, v7}, Lcom/android/mms/service/MmsRequest;->processResult(Landroid/content/Context;I[BI)V

    .line 222
    return-void

    .line 152
    :cond_2
    invoke-virtual/range {p0 .. p0}, Lcom/android/mms/service/MmsRequest;->prepareForHttpRequest()Z

    move-result v14

    if-nez v14, :cond_3

    .line 153
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: failed to prepare for request"

    invoke-static {v14, v15}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 154
    const/4 v11, 0x5

    goto :goto_0

    .line 156
    :cond_3
    const-wide/16 v12, 0x2

    .line 158
    .local v12, "retryDelaySecs":J
    const/4 v8, 0x0

    .local v8, "i":I
    :goto_1
    const/4 v14, 0x3

    if-ge v8, v14, :cond_4

    .line 160
    :try_start_0
    invoke-virtual/range {p2 .. p2}, Lcom/android/mms/service/MmsNetworkManager;->acquireNetwork()V

    .line 161
    invoke-virtual/range {p2 .. p2}, Lcom/android/mms/service/MmsNetworkManager;->getApnName()Ljava/lang/String;
    :try_end_0
    .catch Lcom/android/mms/service/exception/ApnException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Lcom/android/mms/service/exception/MmsNetworkException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Lcom/android/mms/service/exception/MmsHttpException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_5

    move-result-object v5

    .line 163
    .local v5, "apnName":Ljava/lang/String;
    const/4 v4, 0x0

    .line 165
    .local v4, "apn":Lcom/android/mms/service/ApnSettings;
    :try_start_1
    move-object/from16 v0, p0

    iget v14, v0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    move-object/from16 v0, p1

    invoke-static {v0, v5, v14}, Lcom/android/mms/service/ApnSettings;->load(Landroid/content/Context;Ljava/lang/String;I)Lcom/android/mms/service/ApnSettings;
    :try_end_1
    .catch Lcom/android/mms/service/exception/ApnException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    move-result-object v4

    .line 176
    :goto_2
    :try_start_2
    const-string v14, "MmsService"

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    const-string v16, "MmsRequest: doHttp start : apn = "

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v4}, Lcom/android/mms/service/ApnSettings;->toString()Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    const-string v16, ", mSubId = "

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    move-object/from16 v0, p0

    iget v0, v0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    move/from16 v16, v0

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v15

    invoke-static {v14, v15}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 177
    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    invoke-virtual {v0, v1, v2, v4}, Lcom/android/mms/service/MmsRequest;->doHttp(Landroid/content/Context;Lcom/android/mms/service/MmsNetworkManager;Lcom/android/mms/service/ApnSettings;)[B

    move-result-object v10

    .line 178
    move-object/from16 v0, p0

    invoke-virtual {v0, v10}, Lcom/android/mms/service/MmsRequest;->checkResponse([B)I

    move-result v11

    .line 179
    const-string v14, "MmsService"

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    const-string v16, "MmsRequest: doHttp end ! And result = "

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v15

    const-string v16, ", mSubId = "

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    move-object/from16 v0, p0

    iget v0, v0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    move/from16 v16, v0

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v15

    invoke-static {v14, v15}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 181
    const/4 v9, 0x1

    .line 184
    :try_start_3
    invoke-virtual/range {p2 .. p2}, Lcom/android/mms/service/MmsNetworkManager;->releaseNetwork()V
    :try_end_3
    .catch Lcom/android/mms/service/exception/ApnException; {:try_start_3 .. :try_end_3} :catch_2
    .catch Lcom/android/mms/service/exception/MmsNetworkException; {:try_start_3 .. :try_end_3} :catch_3
    .catch Lcom/android/mms/service/exception/MmsHttpException; {:try_start_3 .. :try_end_3} :catch_4
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_5

    .line 211
    .end local v4    # "apn":Lcom/android/mms/service/ApnSettings;
    .end local v5    # "apnName":Ljava/lang/String;
    :cond_4
    :goto_3
    if-eqz v9, :cond_0

    invoke-virtual/range {p2 .. p2}, Lcom/android/mms/service/MmsNetworkManager;->isNetworkReleased()Z

    move-result v14

    if-eqz v14, :cond_0

    .line 213
    :try_start_4
    const-string v14, "MmsService"

    const-string v15, "success sleep 2s"

    invoke-static {v14, v15}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 214
    const-wide/16 v14, 0x7d0

    const/16 v16, 0x0

    invoke-static/range {v14 .. v16}, Ljava/lang/Thread;->sleep(JI)V
    :try_end_4
    .catch Ljava/lang/InterruptedException; {:try_start_4 .. :try_end_4} :catch_0

    goto/16 :goto_0

    .line 215
    :catch_0
    move-exception v14

    goto/16 :goto_0

    .line 166
    .restart local v4    # "apn":Lcom/android/mms/service/ApnSettings;
    .restart local v5    # "apnName":Ljava/lang/String;
    :catch_1
    move-exception v6

    .line 168
    .local v6, "e":Lcom/android/mms/service/exception/ApnException;
    if-nez v5, :cond_5

    .line 170
    :try_start_5
    throw v6
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 184
    .end local v6    # "e":Lcom/android/mms/service/exception/ApnException;
    :catchall_0
    move-exception v14

    :try_start_6
    invoke-virtual/range {p2 .. p2}, Lcom/android/mms/service/MmsNetworkManager;->releaseNetwork()V

    throw v14
    :try_end_6
    .catch Lcom/android/mms/service/exception/ApnException; {:try_start_6 .. :try_end_6} :catch_2
    .catch Lcom/android/mms/service/exception/MmsNetworkException; {:try_start_6 .. :try_end_6} :catch_3
    .catch Lcom/android/mms/service/exception/MmsHttpException; {:try_start_6 .. :try_end_6} :catch_4
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_5

    .line 186
    .end local v4    # "apn":Lcom/android/mms/service/ApnSettings;
    .end local v5    # "apnName":Ljava/lang/String;
    :catch_2
    move-exception v6

    .line 187
    .restart local v6    # "e":Lcom/android/mms/service/exception/ApnException;
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: APN failure"

    invoke-static {v14, v15, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 188
    const/4 v11, 0x2

    .line 189
    goto :goto_3

    .line 172
    .restart local v4    # "apn":Lcom/android/mms/service/ApnSettings;
    .restart local v5    # "apnName":Ljava/lang/String;
    :cond_5
    :try_start_7
    const-string v14, "MmsService"

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    const-string v16, "MmsRequest: No match with APN name:"

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    const-string v16, ", try with no name"

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v15

    invoke-static {v14, v15}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 174
    const/4 v14, 0x0

    move-object/from16 v0, p0

    iget v15, v0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    move-object/from16 v0, p1

    invoke-static {v0, v14, v15}, Lcom/android/mms/service/ApnSettings;->load(Landroid/content/Context;Ljava/lang/String;I)Lcom/android/mms/service/ApnSettings;
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    move-result-object v4

    goto/16 :goto_2

    .line 190
    .end local v4    # "apn":Lcom/android/mms/service/ApnSettings;
    .end local v5    # "apnName":Ljava/lang/String;
    .end local v6    # "e":Lcom/android/mms/service/exception/ApnException;
    :catch_3
    move-exception v6

    .line 191
    .local v6, "e":Lcom/android/mms/service/exception/MmsNetworkException;
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: MMS network acquiring failure"

    invoke-static {v14, v15, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 192
    const/4 v11, 0x3

    .line 206
    .end local v6    # "e":Lcom/android/mms/service/exception/MmsNetworkException;
    :goto_4
    :try_start_8
    const-string v14, "MmsService"

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    const-string v16, "sleep: "

    invoke-virtual/range {v15 .. v16}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v15

    const-wide/16 v16, 0x3e8

    mul-long v16, v16, v12

    invoke-virtual/range {v15 .. v17}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v15

    invoke-static {v14, v15}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 207
    const-wide/16 v14, 0x3e8

    mul-long/2addr v14, v12

    const/16 v16, 0x0

    invoke-static/range {v14 .. v16}, Ljava/lang/Thread;->sleep(JI)V
    :try_end_8
    .catch Ljava/lang/InterruptedException; {:try_start_8 .. :try_end_8} :catch_6

    .line 209
    :goto_5
    const/4 v14, 0x1

    shl-long/2addr v12, v14

    .line 158
    add-int/lit8 v8, v8, 0x1

    goto/16 :goto_1

    .line 194
    :catch_4
    move-exception v6

    .line 195
    .local v6, "e":Lcom/android/mms/service/exception/MmsHttpException;
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: HTTP or network I/O failure"

    invoke-static {v14, v15, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 196
    invoke-virtual/range {p2 .. p2}, Lcom/android/mms/service/MmsNetworkManager;->releaseNetworkImmediately()V

    .line 197
    const/4 v11, 0x4

    .line 198
    invoke-virtual {v6}, Lcom/android/mms/service/exception/MmsHttpException;->getStatusCode()I

    move-result v7

    .line 204
    goto :goto_4

    .line 200
    .end local v6    # "e":Lcom/android/mms/service/exception/MmsHttpException;
    :catch_5
    move-exception v6

    .line 201
    .local v6, "e":Ljava/lang/Exception;
    const-string v14, "MmsService"

    const-string v15, "MmsRequest: unexpected failure"

    invoke-static {v14, v15, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 202
    const/4 v11, 0x1

    .line 203
    goto/16 :goto_3

    .line 208
    .end local v6    # "e":Ljava/lang/Exception;
    :catch_6
    move-exception v14

    goto :goto_5
.end method

.method protected abstract getPendingIntent()Landroid/app/PendingIntent;
.end method

.method public getSubId()I
    .locals 1

    .prologue
    .line 108
    iget v0, p0, Lcom/android/mms/service/MmsRequest;->mSubId:I

    return v0
.end method

.method protected maybeFallbackToRegularDelivery(I)Z
    .locals 3
    .param p1, "carrierMessagingAppResult"    # I

    .prologue
    const/4 v0, 0x1

    .line 298
    if-eq p1, v0, :cond_0

    if-ne p1, v0, :cond_1

    .line 302
    :cond_0
    const-string v1, "MmsService"

    const-string v2, "Sending/downloading MMS by IP failed."

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 303
    iget-object v1, p0, Lcom/android/mms/service/MmsRequest;->mRequestManager:Lcom/android/mms/service/MmsRequest$RequestManager;

    invoke-interface {v1, p0}, Lcom/android/mms/service/MmsRequest$RequestManager;->addSimRequest(Lcom/android/mms/service/MmsRequest;)V

    .line 306
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected abstract persistIfRequired(Landroid/content/Context;I[B)Landroid/net/Uri;
.end method

.method protected abstract prepareForHttpRequest()Z
.end method

.method public processResult(Landroid/content/Context;I[BI)V
    .locals 17
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "result"    # I
    .param p3, "response"    # [B
    .param p4, "httpStatusCode"    # I

    .prologue
    .line 233
    invoke-virtual/range {p0 .. p0}, Lcom/android/mms/service/MmsRequest;->getPendingIntent()Landroid/app/PendingIntent;

    move-result-object v14

    .line 235
    .local v14, "pendingIntent":Landroid/app/PendingIntent;
    if-eqz v14, :cond_3

    .line 236
    invoke-virtual {v14}, Landroid/app/PendingIntent;->getIntent()Landroid/content/Intent;

    move-result-object v12

    .line 237
    .local v12, "intent":Landroid/content/Intent;
    const-string v2, "bundle_uri"

    invoke-virtual {v12, v2}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    .line 238
    .local v16, "uri_string":Ljava/lang/String;
    invoke-static/range {v16 .. v16}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v4

    .line 239
    .local v4, "uri":Landroid/net/Uri;
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "processResult: mMessageUri = "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v4}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 240
    const/4 v9, 0x0

    .line 242
    .local v9, "cursor":Landroid/database/Cursor;
    :try_start_0
    invoke-virtual/range {p1 .. p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    const/4 v2, 0x3

    new-array v5, v2, [Ljava/lang/String;

    const/4 v2, 0x0

    const-string v6, "_id"

    aput-object v6, v5, v2

    const/4 v2, 0x1

    const-string v6, "thread_id"

    aput-object v6, v5, v2

    const/4 v2, 0x2

    const-string v6, "m_size"

    aput-object v6, v5, v2

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    move-object/from16 v2, p1

    invoke-static/range {v2 .. v8}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->query(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v9

    .line 248
    if-eqz v9, :cond_0

    invoke-interface {v9}, Landroid/database/Cursor;->getCount()I

    move-result v2

    if-nez v2, :cond_2

    .line 249
    :cond_0
    const-string v2, "MmsService"

    const-string v3, "Wap push has been deleted !"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 255
    if-eqz v9, :cond_1

    .line 256
    invoke-interface {v9}, Landroid/database/Cursor;->close()V

    .line 291
    .end local v4    # "uri":Landroid/net/Uri;
    .end local v9    # "cursor":Landroid/database/Cursor;
    .end local v12    # "intent":Landroid/content/Intent;
    .end local v16    # "uri_string":Ljava/lang/String;
    :cond_1
    :goto_0
    return-void

    .line 255
    .restart local v4    # "uri":Landroid/net/Uri;
    .restart local v9    # "cursor":Landroid/database/Cursor;
    .restart local v12    # "intent":Landroid/content/Intent;
    .restart local v16    # "uri_string":Ljava/lang/String;
    :cond_2
    if-eqz v9, :cond_3

    .line 256
    invoke-interface {v9}, Landroid/database/Cursor;->close()V

    .line 261
    .end local v4    # "uri":Landroid/net/Uri;
    .end local v9    # "cursor":Landroid/database/Cursor;
    .end local v12    # "intent":Landroid/content/Intent;
    .end local v16    # "uri_string":Ljava/lang/String;
    :cond_3
    :goto_1
    invoke-virtual/range {p0 .. p3}, Lcom/android/mms/service/MmsRequest;->persistIfRequired(Landroid/content/Context;I[B)Landroid/net/Uri;

    move-result-object v13

    .line 262
    .local v13, "messageUri":Landroid/net/Uri;
    const-string v2, "MmsService"

    const-string v3, "processResult: prepare"

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 264
    if-eqz v14, :cond_8

    .line 265
    const/4 v15, 0x1

    .line 267
    .local v15, "succeeded":Z
    new-instance v11, Landroid/content/Intent;

    invoke-direct {v11}, Landroid/content/Intent;-><init>()V

    .line 268
    .local v11, "fillIn":Landroid/content/Intent;
    if-eqz p3, :cond_4

    .line 269
    move-object/from16 v0, p0

    move-object/from16 v1, p3

    invoke-virtual {v0, v11, v1}, Lcom/android/mms/service/MmsRequest;->transferResponse(Landroid/content/Intent;[B)Z

    move-result v15

    .line 271
    :cond_4
    if-eqz v13, :cond_5

    .line 272
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "processResult: messageUri = "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 273
    const-string v2, "uri"

    invoke-virtual {v13}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v11, v2, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 275
    :cond_5
    move-object/from16 v0, p0

    invoke-virtual {v0, v11}, Lcom/android/mms/service/MmsRequest;->putOriginalUrl(Landroid/content/Intent;)V

    .line 276
    const/4 v2, 0x4

    move/from16 v0, p2

    if-ne v0, v2, :cond_6

    if-eqz p4, :cond_6

    .line 277
    const-string v2, "android.telephony.extra.MMS_HTTP_STATUS"

    move/from16 v0, p4

    invoke-virtual {v11, v2, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    .line 280
    :cond_6
    if-nez v15, :cond_7

    .line 281
    const/16 p2, 0x5

    .line 283
    :cond_7
    :try_start_1
    const-string v2, "MmsService"

    const-string v3, "processResult: pendingIntent will be Send."

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 284
    move-object/from16 v0, p1

    move/from16 v1, p2

    invoke-virtual {v14, v0, v1, v11}, Landroid/app/PendingIntent;->send(Landroid/content/Context;ILandroid/content/Intent;)V
    :try_end_1
    .catch Landroid/app/PendingIntent$CanceledException; {:try_start_1 .. :try_end_1} :catch_1

    .line 290
    .end local v11    # "fillIn":Landroid/content/Intent;
    .end local v15    # "succeeded":Z
    :cond_8
    :goto_2
    invoke-virtual/range {p0 .. p1}, Lcom/android/mms/service/MmsRequest;->revokeUriPermission(Landroid/content/Context;)V

    goto :goto_0

    .line 252
    .end local v13    # "messageUri":Landroid/net/Uri;
    .restart local v4    # "uri":Landroid/net/Uri;
    .restart local v9    # "cursor":Landroid/database/Cursor;
    .restart local v12    # "intent":Landroid/content/Intent;
    .restart local v16    # "uri_string":Ljava/lang/String;
    :catch_0
    move-exception v10

    .line 253
    .local v10, "e":Ljava/lang/Exception;
    :try_start_2
    const-string v2, "MmsService"

    const-string v3, "MmsRequest: processResult query fail !"

    invoke-static {v2, v3, v10}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 255
    if-eqz v9, :cond_3

    .line 256
    invoke-interface {v9}, Landroid/database/Cursor;->close()V

    goto :goto_1

    .line 255
    .end local v10    # "e":Ljava/lang/Exception;
    :catchall_0
    move-exception v2

    if-eqz v9, :cond_9

    .line 256
    invoke-interface {v9}, Landroid/database/Cursor;->close()V

    :cond_9
    throw v2

    .line 285
    .end local v4    # "uri":Landroid/net/Uri;
    .end local v9    # "cursor":Landroid/database/Cursor;
    .end local v12    # "intent":Landroid/content/Intent;
    .end local v16    # "uri_string":Ljava/lang/String;
    .restart local v11    # "fillIn":Landroid/content/Intent;
    .restart local v13    # "messageUri":Landroid/net/Uri;
    .restart local v15    # "succeeded":Z
    :catch_1
    move-exception v10

    .line 286
    .local v10, "e":Landroid/app/PendingIntent$CanceledException;
    const-string v2, "MmsService"

    const-string v3, "MmsRequest: sending pending intent canceled"

    invoke-static {v2, v3, v10}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_2
.end method

.method protected abstract putOriginalUrl(Landroid/content/Intent;)V
.end method

.method protected abstract revokeUriPermission(Landroid/content/Context;)V
.end method

.method protected abstract transferResponse(Landroid/content/Intent;[B)Z
.end method
