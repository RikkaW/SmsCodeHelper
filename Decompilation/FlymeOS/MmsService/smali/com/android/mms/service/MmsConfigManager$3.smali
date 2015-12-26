.class Lcom/android/mms/service/MmsConfigManager$3;
.super Ljava/lang/Thread;
.source "MmsConfigManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/mms/service/MmsConfigManager;->loadInBackground()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsConfigManager;


# direct methods
.method constructor <init>(Lcom/android/mms/service/MmsConfigManager;)V
    .locals 0

    .prologue
    .line 110
    iput-object p1, p0, Lcom/android/mms/service/MmsConfigManager$3;->this$0:Lcom/android/mms/service/MmsConfigManager;

    invoke-direct {p0}, Ljava/lang/Thread;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .prologue
    .line 113
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigManager$3;->this$0:Lcom/android/mms/service/MmsConfigManager;

    # getter for: Lcom/android/mms/service/MmsConfigManager;->mContext:Landroid/content/Context;
    invoke-static {v1}, Lcom/android/mms/service/MmsConfigManager;->access$100(Lcom/android/mms/service/MmsConfigManager;)Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    .line 116
    .local v0, "configuration":Landroid/content/res/Configuration;
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "MmsConfigManager.loadInBackground(): mcc/mnc: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v3, v0, Landroid/content/res/Configuration;->mcc:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "/"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v3, v0, Landroid/content/res/Configuration;->mnc:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 118
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigManager$3;->this$0:Lcom/android/mms/service/MmsConfigManager;

    iget-object v2, p0, Lcom/android/mms/service/MmsConfigManager$3;->this$0:Lcom/android/mms/service/MmsConfigManager;

    # getter for: Lcom/android/mms/service/MmsConfigManager;->mContext:Landroid/content/Context;
    invoke-static {v2}, Lcom/android/mms/service/MmsConfigManager;->access$100(Lcom/android/mms/service/MmsConfigManager;)Landroid/content/Context;

    move-result-object v2

    # invokes: Lcom/android/mms/service/MmsConfigManager;->load(Landroid/content/Context;)V
    invoke-static {v1, v2}, Lcom/android/mms/service/MmsConfigManager;->access$200(Lcom/android/mms/service/MmsConfigManager;Landroid/content/Context;)V

    .line 119
    return-void
.end method
