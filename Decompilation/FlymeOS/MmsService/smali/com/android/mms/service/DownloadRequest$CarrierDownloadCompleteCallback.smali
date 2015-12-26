.class final Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;
.super Lcom/android/mms/service/MmsRequest$CarrierMmsActionCallback;
.source "DownloadRequest.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/DownloadRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "CarrierDownloadCompleteCallback"
.end annotation


# instance fields
.field private final mCarrierDownloadManager:Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;

.field private final mContext:Landroid/content/Context;

.field final synthetic this$0:Lcom/android/mms/service/DownloadRequest;


# direct methods
.method public constructor <init>(Lcom/android/mms/service/DownloadRequest;Landroid/content/Context;Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;)V
    .locals 0
    .param p2, "context"    # Landroid/content/Context;
    .param p3, "carrierDownloadManager"    # Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;

    .prologue
    .line 347
    iput-object p1, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->this$0:Lcom/android/mms/service/DownloadRequest;

    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsRequest$CarrierMmsActionCallback;-><init>(Lcom/android/mms/service/MmsRequest;)V

    .line 348
    iput-object p2, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->mContext:Landroid/content/Context;

    .line 349
    iput-object p3, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->mCarrierDownloadManager:Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;

    .line 350
    return-void
.end method


# virtual methods
.method public onDownloadMmsComplete(I)V
    .locals 5
    .param p1, "result"    # I

    .prologue
    .line 359
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Carrier app result for download: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 360
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->mCarrierDownloadManager:Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;

    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->disposeConnection(Landroid/content/Context;)V

    .line 362
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->this$0:Lcom/android/mms/service/DownloadRequest;

    invoke-virtual {v0, p1}, Lcom/android/mms/service/DownloadRequest;->maybeFallbackToRegularDelivery(I)Z

    move-result v0

    if-nez v0, :cond_0

    .line 363
    iget-object v0, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->this$0:Lcom/android/mms/service/DownloadRequest;

    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->mContext:Landroid/content/Context;

    invoke-static {p1}, Lcom/android/mms/service/MmsRequest;->toSmsManagerResult(I)I

    move-result v2

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-virtual {v0, v1, v2, v3, v4}, Lcom/android/mms/service/DownloadRequest;->processResult(Landroid/content/Context;I[BI)V

    .line 366
    :cond_0
    return-void
.end method

.method public onSendMmsComplete(I[B)V
    .locals 3
    .param p1, "result"    # I
    .param p2, "sendConfPdu"    # [B

    .prologue
    .line 354
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unexpected onSendMmsComplete call with result: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 355
    return-void
.end method
