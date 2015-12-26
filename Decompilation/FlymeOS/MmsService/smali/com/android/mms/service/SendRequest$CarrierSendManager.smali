.class final Lcom/android/mms/service/SendRequest$CarrierSendManager;
.super Landroid/telephony/CarrierMessagingServiceManager;
.source "SendRequest.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/SendRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "CarrierSendManager"
.end annotation


# instance fields
.field private volatile mCarrierSendCompleteCallback:Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;

.field final synthetic this$0:Lcom/android/mms/service/SendRequest;


# direct methods
.method private constructor <init>(Lcom/android/mms/service/SendRequest;)V
    .locals 0

    .prologue
    .line 405
    iput-object p1, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->this$0:Lcom/android/mms/service/SendRequest;

    invoke-direct {p0}, Landroid/telephony/CarrierMessagingServiceManager;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/android/mms/service/SendRequest;Lcom/android/mms/service/SendRequest$1;)V
    .locals 0
    .param p1, "x0"    # Lcom/android/mms/service/SendRequest;
    .param p2, "x1"    # Lcom/android/mms/service/SendRequest$1;

    .prologue
    .line 405
    invoke-direct {p0, p1}, Lcom/android/mms/service/SendRequest$CarrierSendManager;-><init>(Lcom/android/mms/service/SendRequest;)V

    return-void
.end method


# virtual methods
.method protected onServiceReady(Landroid/service/carrier/ICarrierMessagingService;)V
    .locals 5
    .param p1, "carrierMessagingService"    # Landroid/service/carrier/ICarrierMessagingService;

    .prologue
    .line 425
    const/4 v1, 0x0

    .line 426
    .local v1, "locationUri":Landroid/net/Uri;
    :try_start_0
    iget-object v2, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->this$0:Lcom/android/mms/service/SendRequest;

    # getter for: Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;
    invoke-static {v2}, Lcom/android/mms/service/SendRequest;->access$100(Lcom/android/mms/service/SendRequest;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 427
    iget-object v2, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->this$0:Lcom/android/mms/service/SendRequest;

    # getter for: Lcom/android/mms/service/SendRequest;->mLocationUrl:Ljava/lang/String;
    invoke-static {v2}, Lcom/android/mms/service/SendRequest;->access$100(Lcom/android/mms/service/SendRequest;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    .line 429
    :cond_0
    iget-object v2, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->this$0:Lcom/android/mms/service/SendRequest;

    # getter for: Lcom/android/mms/service/SendRequest;->mPduUri:Landroid/net/Uri;
    invoke-static {v2}, Lcom/android/mms/service/SendRequest;->access$200(Lcom/android/mms/service/SendRequest;)Landroid/net/Uri;

    move-result-object v2

    iget-object v3, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->this$0:Lcom/android/mms/service/SendRequest;

    iget v3, v3, Lcom/android/mms/service/SendRequest;->mSubId:I

    iget-object v4, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->mCarrierSendCompleteCallback:Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;

    invoke-interface {p1, v2, v3, v1, v4}, Landroid/service/carrier/ICarrierMessagingService;->sendMms(Landroid/net/Uri;ILandroid/net/Uri;Landroid/service/carrier/ICarrierMessagingCallback;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    .line 438
    :goto_0
    return-void

    .line 431
    :catch_0
    move-exception v0

    .line 432
    .local v0, "e":Landroid/os/RemoteException;
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Exception sending MMS using the carrier messaging service: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 434
    iget-object v2, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->mCarrierSendCompleteCallback:Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;

    const/4 v3, 0x1

    const/4 v4, 0x0

    invoke-virtual {v2, v3, v4}, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->onSendMmsComplete(I[B)V

    goto :goto_0
.end method

.method sendMms(Landroid/content/Context;Ljava/lang/String;Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "carrierMessagingServicePackage"    # Ljava/lang/String;
    .param p3, "carrierSendCompleteCallback"    # Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;

    .prologue
    .line 411
    iput-object p3, p0, Lcom/android/mms/service/SendRequest$CarrierSendManager;->mCarrierSendCompleteCallback:Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;

    .line 412
    invoke-virtual {p0, p1, p2}, Lcom/android/mms/service/SendRequest$CarrierSendManager;->bindToCarrierMessagingService(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 413
    const-string v0, "MmsService"

    const-string v1, "bindService() for carrier messaging service succeeded"

    invoke-static {v0, v1}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 420
    :goto_0
    return-void

    .line 415
    :cond_0
    const-string v0, "MmsService"

    const-string v1, "bindService() for carrier messaging service failed"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 416
    const/4 v0, 0x1

    const/4 v1, 0x0

    invoke-virtual {p3, v0, v1}, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->onSendMmsComplete(I[B)V

    goto :goto_0
.end method
