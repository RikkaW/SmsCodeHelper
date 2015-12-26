.class Lcom/android/mms/service/MmsConfig$1;
.super Ljava/lang/Object;
.source "MmsConfig.java"

# interfaces
.implements Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/mms/service/MmsConfig;->loadFromResources(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsConfig;


# direct methods
.method constructor <init>(Lcom/android/mms/service/MmsConfig;)V
    .locals 0

    .prologue
    .line 324
    iput-object p1, p0, Lcom/android/mms/service/MmsConfig$1;->this$0:Lcom/android/mms/service/MmsConfig;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public process(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .param p1, "key"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
    .param p3, "type"    # Ljava/lang/String;

    .prologue
    .line 327
    iget-object v0, p0, Lcom/android/mms/service/MmsConfig$1;->this$0:Lcom/android/mms/service/MmsConfig;

    # invokes: Lcom/android/mms/service/MmsConfig;->update(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    invoke-static {v0, p1, p2, p3}, Lcom/android/mms/service/MmsConfig;->access$000(Lcom/android/mms/service/MmsConfig;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 328
    return-void
.end method
