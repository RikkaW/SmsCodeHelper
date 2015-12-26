.class Lcom/android/mms/service/MmsService$1;
.super Lcom/android/internal/telephony/IMms$Stub;
.source "MmsService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/MmsService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsService;


# direct methods
.method constructor <init>(Lcom/android/mms/service/MmsService;)V
    .locals 0

    .prologue
    .line 198
    iput-object p1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-direct {p0}, Lcom/android/internal/telephony/IMms$Stub;-><init>()V

    return-void
.end method


# virtual methods
.method public addMultimediaMessageDraft(Ljava/lang/String;Landroid/net/Uri;)Landroid/net/Uri;
    .locals 2
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "contentUri"    # Landroid/net/Uri;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 356
    const-string v0, "MmsService"

    const-string v1, "addMultimediaMessageDraft"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 357
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v0}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 358
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->addMmsDraft(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    invoke-static {v0, p2, p1}, Lcom/android/mms/service/MmsService;->access$1400(Lcom/android/mms/service/MmsService;Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public addTextMessageDraft(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;
    .locals 2
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "address"    # Ljava/lang/String;
    .param p3, "text"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 348
    const-string v0, "MmsService"

    const-string v1, "addTextMessageDraft"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 349
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v0}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 350
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->addSmsDraft(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;
    invoke-static {v0, p2, p3, p1}, Lcom/android/mms/service/MmsService;->access$1300(Lcom/android/mms/service/MmsService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public archiveStoredConversation(Ljava/lang/String;JZ)Z
    .locals 4
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "conversationId"    # J
    .param p4, "archived"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 337
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "archiveStoredConversation "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p4}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 338
    const-wide/16 v0, -0x1

    cmp-long v0, p2, v0

    if-nez v0, :cond_0

    .line 339
    const-string v0, "MmsService"

    const-string v1, "archiveStoredConversation: invalid thread id"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 340
    const/4 v0, 0x0

    .line 342
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->archiveConversation(JZ)Z
    invoke-static {v0, p2, p3, p4}, Lcom/android/mms/service/MmsService;->access$1200(Lcom/android/mms/service/MmsService;JZ)Z

    move-result v0

    goto :goto_0
.end method

.method public deleteStoredConversation(Ljava/lang/String;J)Z
    .locals 10
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "conversationId"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    const/4 v5, 0x1

    const/4 v4, 0x0

    .line 301
    const-string v6, "MmsService"

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "deleteStoredConversation "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 302
    iget-object v6, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v6}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 303
    const-wide/16 v6, -0x1

    cmp-long v6, p2, v6

    if-nez v6, :cond_0

    .line 304
    const-string v5, "MmsService"

    const-string v6, "deleteStoredConversation: invalid thread id"

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 323
    :goto_0
    return v4

    .line 307
    :cond_0
    sget-object v6, Landroid/provider/Telephony$Threads;->CONTENT_URI:Landroid/net/Uri;

    invoke-static {v6, p2, p3}, Landroid/content/ContentUris;->withAppendedId(Landroid/net/Uri;J)Landroid/net/Uri;

    move-result-object v1

    .line 312
    .local v1, "uri":Landroid/net/Uri;
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v2

    .line 314
    .local v2, "identity":J
    :try_start_0
    iget-object v6, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v6}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v6

    const/4 v7, 0x0

    const/4 v8, 0x0

    invoke-virtual {v6, v1, v7, v8}, Landroid/content/ContentResolver;->delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v6

    if-eq v6, v5, :cond_1

    .line 315
    const-string v6, "MmsService"

    const-string v7, "deleteStoredConversation: failed to delete"

    invoke-static {v6, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 321
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    :cond_1
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    :goto_1
    move v4, v5

    .line 323
    goto :goto_0

    .line 318
    :catch_0
    move-exception v0

    .line 319
    .local v0, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v4, "MmsService"

    const-string v6, "deleteStoredConversation: failed to delete"

    invoke-static {v4, v6, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 321
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .end local v0    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v4

    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v4
.end method

.method public deleteStoredMessage(Ljava/lang/String;Landroid/net/Uri;)Z
    .locals 8
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "messageUri"    # Landroid/net/Uri;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    const/4 v4, 0x1

    const/4 v1, 0x0

    .line 274
    const-string v5, "MmsService"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "deleteStoredMessage "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 275
    iget-object v5, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v5}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 276
    # invokes: Lcom/android/mms/service/MmsService;->isSmsMmsContentUri(Landroid/net/Uri;)Z
    invoke-static {p2}, Lcom/android/mms/service/MmsService;->access$1000(Landroid/net/Uri;)Z

    move-result v5

    if-nez v5, :cond_0

    .line 277
    const-string v4, "MmsService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "deleteStoredMessage: invalid message URI: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {p2}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 295
    :goto_0
    return v1

    .line 283
    :cond_0
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v2

    .line 285
    .local v2, "identity":J
    :try_start_0
    iget-object v5, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v5}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v5

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-virtual {v5, p2, v6, v7}, Landroid/content/ContentResolver;->delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v5

    if-eq v5, v4, :cond_1

    .line 287
    const-string v5, "MmsService"

    const-string v6, "deleteStoredMessage: failed to delete"

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 293
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    :cond_1
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    :goto_1
    move v1, v4

    .line 295
    goto :goto_0

    .line 290
    :catch_0
    move-exception v0

    .line 291
    .local v0, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v1, "MmsService"

    const-string v5, "deleteStoredMessage: failed to delete"

    invoke-static {v1, v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 293
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .end local v0    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v1

    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v1
.end method

.method public downloadMessage(ILjava/lang/String;Ljava/lang/String;Landroid/net/Uri;Landroid/os/Bundle;Landroid/app/PendingIntent;)V
    .locals 9
    .param p1, "subId"    # I
    .param p2, "callingPkg"    # Ljava/lang/String;
    .param p3, "locationUrl"    # Ljava/lang/String;
    .param p4, "contentUri"    # Landroid/net/Uri;
    .param p5, "configOverrides"    # Landroid/os/Bundle;
    .param p6, "downloadedIntent"    # Landroid/app/PendingIntent;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 227
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "downloadMessage: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ", subId = "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, ", contentUri = "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 228
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v1}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 230
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->checkSubId(I)I
    invoke-static {v1, p1}, Lcom/android/mms/service/MmsService;->access$600(Lcom/android/mms/service/MmsService;I)I

    move-result p1

    .line 232
    new-instance v0, Lcom/android/mms/service/DownloadRequest;

    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    move v2, p1

    move-object v3, p3

    move-object v4, p4

    move-object v5, p6

    move-object v6, p2

    move-object v7, p5

    invoke-direct/range {v0 .. v7}, Lcom/android/mms/service/DownloadRequest;-><init>(Lcom/android/mms/service/MmsRequest$RequestManager;ILjava/lang/String;Landroid/net/Uri;Landroid/app/PendingIntent;Ljava/lang/String;Landroid/os/Bundle;)V

    .line 234
    .local v0, "request":Lcom/android/mms/service/DownloadRequest;
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->getCarrierMessagingServicePackageIfExists()Ljava/lang/String;
    invoke-static {v1}, Lcom/android/mms/service/MmsService;->access$700(Lcom/android/mms/service/MmsService;)Ljava/lang/String;

    move-result-object v8

    .line 236
    .local v8, "carrierMessagingServicePackage":Ljava/lang/String;
    if-eqz v8, :cond_0

    .line 237
    const-string v1, "MmsService"

    const-string v2, "downloading message by carrier app"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 238
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v0, v1, v8}, Lcom/android/mms/service/DownloadRequest;->tryDownloadingByCarrierApp(Landroid/content/Context;Ljava/lang/String;)V

    .line 242
    :goto_0
    return-void

    .line 240
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v1, v0}, Lcom/android/mms/service/MmsService;->addSimRequest(Lcom/android/mms/service/MmsRequest;)V

    goto :goto_0
.end method

.method public getAutoPersisting()Z
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 407
    const-string v0, "MmsService"

    const-string v1, "getAutoPersisting"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 408
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v0}, Lcom/android/mms/service/MmsService;->getAutoPersistingPref()Z

    move-result v0

    return v0
.end method

.method public getCarrierConfigValues(I)Landroid/os/Bundle;
    .locals 3
    .param p1, "subId"    # I

    .prologue
    .line 245
    const-string v1, "MmsService"

    const-string v2, "getCarrierConfigValues"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 247
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->checkSubId(I)I
    invoke-static {v1, p1}, Lcom/android/mms/service/MmsService;->access$600(Lcom/android/mms/service/MmsService;I)I

    move-result p1

    .line 248
    invoke-static {}, Lcom/android/mms/service/MmsConfigManager;->getInstance()Lcom/android/mms/service/MmsConfigManager;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/android/mms/service/MmsConfigManager;->getMmsConfigBySubId(I)Lcom/android/mms/service/MmsConfig;

    move-result-object v0

    .line 249
    .local v0, "mmsConfig":Lcom/android/mms/service/MmsConfig;
    if-nez v0, :cond_0

    .line 250
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 252
    :goto_0
    return-object v1

    :cond_0
    invoke-virtual {v0}, Lcom/android/mms/service/MmsConfig;->getCarrierConfigValues()Landroid/os/Bundle;

    move-result-object v1

    goto :goto_0
.end method

.method public importMultimediaMessage(Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;JZZ)Landroid/net/Uri;
    .locals 10
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "contentUri"    # Landroid/net/Uri;
    .param p3, "messageId"    # Ljava/lang/String;
    .param p4, "timestampSecs"    # J
    .param p6, "seen"    # Z
    .param p7, "read"    # Z

    .prologue
    .line 266
    const-string v0, "MmsService"

    const-string v1, "importMultimediaMessage"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 267
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v0}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 268
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    move-object v2, p2

    move-object v3, p3

    move-wide v4, p4

    move/from16 v6, p6

    move/from16 v7, p7

    move-object v8, p1

    # invokes: Lcom/android/mms/service/MmsService;->importMms(Landroid/net/Uri;Ljava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;
    invoke-static/range {v1 .. v8}, Lcom/android/mms/service/MmsService;->access$900(Lcom/android/mms/service/MmsService;Landroid/net/Uri;Ljava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public importTextMessage(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;JZZ)Landroid/net/Uri;
    .locals 9
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "address"    # Ljava/lang/String;
    .param p3, "type"    # I
    .param p4, "text"    # Ljava/lang/String;
    .param p5, "timestampMillis"    # J
    .param p7, "seen"    # Z
    .param p8, "read"    # Z

    .prologue
    .line 258
    const-string v0, "MmsService"

    const-string v1, "importTextMessage"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 259
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v0}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 260
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    move-object v1, p2

    move v2, p3

    move-object v3, p4

    move-wide v4, p5

    move/from16 v6, p7

    move/from16 v7, p8

    move-object v8, p1

    # invokes: Lcom/android/mms/service/MmsService;->importSms(Ljava/lang/String;ILjava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;
    invoke-static/range {v0 .. v8}, Lcom/android/mms/service/MmsService;->access$800(Lcom/android/mms/service/MmsService;Ljava/lang/String;ILjava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method public sendMessage(ILjava/lang/String;Landroid/net/Uri;Ljava/lang/String;Landroid/os/Bundle;Landroid/app/PendingIntent;)V
    .locals 10
    .param p1, "subId"    # I
    .param p2, "callingPkg"    # Ljava/lang/String;
    .param p3, "contentUri"    # Landroid/net/Uri;
    .param p4, "locationUrl"    # Ljava/lang/String;
    .param p5, "configOverrides"    # Landroid/os/Bundle;
    .param p6, "sentIntent"    # Landroid/app/PendingIntent;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 203
    const-string v1, "MmsService"

    const-string v2, "sendMessage"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 204
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v1}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 206
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->checkSubId(I)I
    invoke-static {v1, p1}, Lcom/android/mms/service/MmsService;->access$600(Lcom/android/mms/service/MmsService;I)I

    move-result p1

    .line 207
    new-instance v0, Lcom/android/mms/service/SendRequest;

    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    const/4 v4, 0x0

    move v2, p1

    move-object v3, p3

    move-object v5, p4

    move-object/from16 v6, p6

    move-object v7, p2

    move-object v8, p5

    invoke-direct/range {v0 .. v8}, Lcom/android/mms/service/SendRequest;-><init>(Lcom/android/mms/service/MmsRequest$RequestManager;ILandroid/net/Uri;Landroid/net/Uri;Ljava/lang/String;Landroid/app/PendingIntent;Ljava/lang/String;Landroid/os/Bundle;)V

    .line 209
    .local v0, "request":Lcom/android/mms/service/SendRequest;
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-static {p2, v1}, Lcom/android/internal/telephony/SmsApplication;->shouldWriteMessageForPackage(Ljava/lang/String;Landroid/content/Context;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 211
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v0, v1}, Lcom/android/mms/service/SendRequest;->storeInOutbox(Landroid/content/Context;)V

    .line 213
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->getCarrierMessagingServicePackageIfExists()Ljava/lang/String;
    invoke-static {v1}, Lcom/android/mms/service/MmsService;->access$700(Lcom/android/mms/service/MmsService;)Ljava/lang/String;

    move-result-object v9

    .line 215
    .local v9, "carrierMessagingServicePackage":Ljava/lang/String;
    if-eqz v9, :cond_1

    .line 216
    const-string v1, "MmsService"

    const-string v2, "sending message by carrier app"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 217
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v0, v1, v9}, Lcom/android/mms/service/SendRequest;->trySendingByCarrierApp(Landroid/content/Context;Ljava/lang/String;)V

    .line 221
    :goto_0
    return-void

    .line 219
    :cond_1
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v1, v0}, Lcom/android/mms/service/MmsService;->addSimRequest(Lcom/android/mms/service/MmsRequest;)V

    goto :goto_0
.end method

.method public sendStoredMessage(ILjava/lang/String;Landroid/net/Uri;Landroid/os/Bundle;Landroid/app/PendingIntent;)V
    .locals 10
    .param p1, "subId"    # I
    .param p2, "callingPkg"    # Ljava/lang/String;
    .param p3, "messageUri"    # Landroid/net/Uri;
    .param p4, "configOverrides"    # Landroid/os/Bundle;
    .param p5, "sentIntent"    # Landroid/app/PendingIntent;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 364
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "sendStoredMessage: messageUri = "

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v4, ", subId = "

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 365
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v1}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 367
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->checkSubId(I)I
    invoke-static {v1, p1}, Lcom/android/mms/service/MmsService;->access$600(Lcom/android/mms/service/MmsService;I)I

    move-result p1

    .line 369
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->isFailedOrDraft(Landroid/net/Uri;)Z
    invoke-static {v1, p3}, Lcom/android/mms/service/MmsService;->access$1500(Lcom/android/mms/service/MmsService;Landroid/net/Uri;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 370
    const-string v1, "MmsService"

    const-string v2, "sendStoredMessage: not FAILED or DRAFT message"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 371
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->returnUnspecifiedFailure(Landroid/app/PendingIntent;)V
    invoke-static {v1, p5}, Lcom/android/mms/service/MmsService;->access$1600(Lcom/android/mms/service/MmsService;Landroid/app/PendingIntent;)V

    .line 392
    :goto_0
    return-void

    .line 375
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->loadPdu(Landroid/net/Uri;)[B
    invoke-static {v1, p3}, Lcom/android/mms/service/MmsService;->access$1700(Lcom/android/mms/service/MmsService;Landroid/net/Uri;)[B

    move-result-object v3

    .line 376
    .local v3, "pduData":[B
    if-eqz v3, :cond_1

    array-length v1, v3

    const/4 v2, 0x1

    if-ge v1, v2, :cond_2

    .line 377
    :cond_1
    const-string v1, "MmsService"

    const-string v2, "sendStoredMessage: failed to load PDU data"

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 378
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->returnUnspecifiedFailure(Landroid/app/PendingIntent;)V
    invoke-static {v1, p5}, Lcom/android/mms/service/MmsService;->access$1600(Lcom/android/mms/service/MmsService;Landroid/app/PendingIntent;)V

    goto :goto_0

    .line 381
    :cond_2
    new-instance v0, Lcom/android/mms/service/SendRequest;

    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    const/4 v5, 0x0

    move v2, p1

    move-object v4, p3

    move-object v6, p5

    move-object v7, p2

    move-object v8, p4

    invoke-direct/range {v0 .. v8}, Lcom/android/mms/service/SendRequest;-><init>(Lcom/android/mms/service/MmsRequest$RequestManager;I[BLandroid/net/Uri;Ljava/lang/String;Landroid/app/PendingIntent;Ljava/lang/String;Landroid/os/Bundle;)V

    .line 383
    .local v0, "request":Lcom/android/mms/service/SendRequest;
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v0, v1}, Lcom/android/mms/service/SendRequest;->storeInOutbox(Landroid/content/Context;)V

    .line 384
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->getCarrierMessagingServicePackageIfExists()Ljava/lang/String;
    invoke-static {v1}, Lcom/android/mms/service/MmsService;->access$700(Lcom/android/mms/service/MmsService;)Ljava/lang/String;

    move-result-object v9

    .line 386
    .local v9, "carrierMessagingServicePackage":Ljava/lang/String;
    if-eqz v9, :cond_3

    .line 387
    const-string v1, "MmsService"

    const-string v2, "sending message by carrier app"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 388
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v0, v1, v9}, Lcom/android/mms/service/SendRequest;->trySendingByCarrierApp(Landroid/content/Context;Ljava/lang/String;)V

    goto :goto_0

    .line 390
    :cond_3
    iget-object v1, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v1, v0}, Lcom/android/mms/service/MmsService;->addSimRequest(Lcom/android/mms/service/MmsRequest;)V

    goto :goto_0
.end method

.method public setAutoPersisting(Ljava/lang/String;Z)V
    .locals 5
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "enabled"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 396
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "setAutoPersisting "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 397
    iget-object v2, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v2}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 398
    iget-object v2, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    const-string v3, "mmspref"

    const/4 v4, 0x0

    invoke-virtual {v2, v3, v4}, Lcom/android/mms/service/MmsService;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v1

    .line 400
    .local v1, "preferences":Landroid/content/SharedPreferences;
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 401
    .local v0, "editor":Landroid/content/SharedPreferences$Editor;
    const-string v2, "autopersisting"

    invoke-interface {v0, v2, p2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 402
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 403
    return-void
.end method

.method public updateStoredMessageStatus(Ljava/lang/String;Landroid/net/Uri;Landroid/content/ContentValues;)Z
    .locals 3
    .param p1, "callingPkg"    # Ljava/lang/String;
    .param p2, "messageUri"    # Landroid/net/Uri;
    .param p3, "statusValues"    # Landroid/content/ContentValues;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    .prologue
    .line 329
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "updateStoredMessageStatus "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 330
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->enforceSystemUid()V
    invoke-static {v0}, Lcom/android/mms/service/MmsService;->access$500(Lcom/android/mms/service/MmsService;)V

    .line 331
    iget-object v0, p0, Lcom/android/mms/service/MmsService$1;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->updateMessageStatus(Landroid/net/Uri;Landroid/content/ContentValues;)Z
    invoke-static {v0, p2, p3}, Lcom/android/mms/service/MmsService;->access$1100(Lcom/android/mms/service/MmsService;Landroid/net/Uri;Landroid/content/ContentValues;)Z

    move-result v0

    return v0
.end method
