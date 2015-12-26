.class final Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;
.super Landroid/telephony/CarrierMessagingServiceManager;
.source "DownloadRequest.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/DownloadRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "CarrierDownloadManager"
.end annotation


# instance fields
.field private volatile mCarrierDownloadCallback:Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;

.field final synthetic this$0:Lcom/android/mms/service/DownloadRequest;


# direct methods
.method private constructor <init>(Lcom/android/mms/service/DownloadRequest;)V
    .locals 0

    .prologue
    .line 307
    iput-object p1, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->this$0:Lcom/android/mms/service/DownloadRequest;

    invoke-direct {p0}, Landroid/telephony/CarrierMessagingServiceManager;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/android/mms/service/DownloadRequest;Lcom/android/mms/service/DownloadRequest$1;)V
    .locals 0
    .param p1, "x0"    # Lcom/android/mms/service/DownloadRequest;
    .param p2, "x1"    # Lcom/android/mms/service/DownloadRequest$1;

    .prologue
    .line 307
    invoke-direct {p0, p1}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;-><init>(Lcom/android/mms/service/DownloadRequest;)V

    return-void
.end method


# virtual methods
.method downloadMms(Landroid/content/Context;Ljava/lang/String;Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "carrierMessagingServicePackage"    # Ljava/lang/String;
    .param p3, "carrierDownloadCallback"    # Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;

    .prologue
    .line 313
    iput-object p3, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->mCarrierDownloadCallback:Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;

    .line 314
    invoke-virtual {p0, p1, p2}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->bindToCarrierMessagingService(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 315
    const-string v0, "MmsService"

    const-string v1, "bindService() for carrier messaging service succeeded"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 321
    :goto_0
    return-void

    .line 317
    :cond_0
    const-string v0, "MmsService"

    const-string v1, "bindService() for carrier messaging service failed"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 318
    const/4 v0, 0x1

    invoke-virtual {p3, v0}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->onDownloadMmsComplete(I)V

    goto :goto_0
.end method

.method protected onServiceReady(Landroid/service/carrier/ICarrierMessagingService;)V
    .locals 5
    .param p1, "carrierMessagingService"    # Landroid/service/carrier/ICarrierMessagingService;

    .prologue
    .line 326
    :try_start_0
    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->this$0:Lcom/android/mms/service/DownloadRequest;

    # getter for: Lcom/android/mms/service/DownloadRequest;->mContentUri:Landroid/net/Uri;
    invoke-static {v1}, Lcom/android/mms/service/DownloadRequest;->access$100(Lcom/android/mms/service/DownloadRequest;)Landroid/net/Uri;

    move-result-object v1

    iget-object v2, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->this$0:Lcom/android/mms/service/DownloadRequest;

    iget v2, v2, Lcom/android/mms/service/DownloadRequest;->mSubId:I

    iget-object v3, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->this$0:Lcom/android/mms/service/DownloadRequest;

    # getter for: Lcom/android/mms/service/DownloadRequest;->mLocationUrl:Ljava/lang/String;
    invoke-static {v3}, Lcom/android/mms/service/DownloadRequest;->access$200(Lcom/android/mms/service/DownloadRequest;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->mCarrierDownloadCallback:Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;

    invoke-interface {p1, v1, v2, v3, v4}, Landroid/service/carrier/ICarrierMessagingService;->downloadMms(Landroid/net/Uri;ILandroid/net/Uri;Landroid/service/carrier/ICarrierMessagingCallback;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    .line 334
    :goto_0
    return-void

    .line 328
    :catch_0
    move-exception v0

    .line 329
    .local v0, "e":Landroid/os/RemoteException;
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Exception downloading MMS using the carrier messaging service: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 331
    iget-object v1, p0, Lcom/android/mms/service/DownloadRequest$CarrierDownloadManager;->mCarrierDownloadCallback:Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Lcom/android/mms/service/DownloadRequest$CarrierDownloadCompleteCallback;->onDownloadMmsComplete(I)V

    goto :goto_0
.end method
