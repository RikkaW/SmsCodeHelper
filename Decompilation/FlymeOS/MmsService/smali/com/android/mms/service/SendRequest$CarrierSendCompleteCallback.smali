.class final Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;
.super Lcom/android/mms/service/MmsRequest$CarrierMmsActionCallback;
.source "SendRequest.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/SendRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x12
    name = "CarrierSendCompleteCallback"
.end annotation


# instance fields
.field private final mCarrierSendManager:Lcom/android/mms/service/SendRequest$CarrierSendManager;

.field private final mContext:Landroid/content/Context;

.field final synthetic this$0:Lcom/android/mms/service/SendRequest;


# direct methods
.method public constructor <init>(Lcom/android/mms/service/SendRequest;Landroid/content/Context;Lcom/android/mms/service/SendRequest$CarrierSendManager;)V
    .locals 0
    .param p2, "context"    # Landroid/content/Context;
    .param p3, "carrierSendManager"    # Lcom/android/mms/service/SendRequest$CarrierSendManager;

    .prologue
    .line 450
    iput-object p1, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->this$0:Lcom/android/mms/service/SendRequest;

    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsRequest$CarrierMmsActionCallback;-><init>(Lcom/android/mms/service/MmsRequest;)V

    .line 451
    iput-object p2, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->mContext:Landroid/content/Context;

    .line 452
    iput-object p3, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->mCarrierSendManager:Lcom/android/mms/service/SendRequest$CarrierSendManager;

    .line 453
    return-void
.end method


# virtual methods
.method public onDownloadMmsComplete(I)V
    .locals 3
    .param p1, "result"    # I

    .prologue
    .line 469
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Unexpected onDownloadMmsComplete call with result: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 470
    return-void
.end method

.method public onSendMmsComplete(I[B)V
    .locals 4
    .param p1, "result"    # I
    .param p2, "sendConfPdu"    # [B

    .prologue
    .line 457
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Carrier app result for send: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 458
    iget-object v0, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->mCarrierSendManager:Lcom/android/mms/service/SendRequest$CarrierSendManager;

    iget-object v1, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->mContext:Landroid/content/Context;

    invoke-virtual {v0, v1}, Lcom/android/mms/service/SendRequest$CarrierSendManager;->disposeConnection(Landroid/content/Context;)V

    .line 460
    iget-object v0, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->this$0:Lcom/android/mms/service/SendRequest;

    invoke-virtual {v0, p1}, Lcom/android/mms/service/SendRequest;->maybeFallbackToRegularDelivery(I)Z

    move-result v0

    if-nez v0, :cond_0

    .line 461
    iget-object v0, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->this$0:Lcom/android/mms/service/SendRequest;

    iget-object v1, p0, Lcom/android/mms/service/SendRequest$CarrierSendCompleteCallback;->mContext:Landroid/content/Context;

    invoke-static {p1}, Lcom/android/mms/service/MmsRequest;->toSmsManagerResult(I)I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, p2, v3}, Lcom/android/mms/service/SendRequest;->processResult(Landroid/content/Context;I[BI)V

    .line 465
    :cond_0
    return-void
.end method
