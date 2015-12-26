.class Lcom/android/mms/service/MmsConfigManager$2;
.super Landroid/telephony/SubscriptionManager$OnSubscriptionsChangedListener;
.source "MmsConfigManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/MmsConfigManager;
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
    .line 80
    iput-object p1, p0, Lcom/android/mms/service/MmsConfigManager$2;->this$0:Lcom/android/mms/service/MmsConfigManager;

    invoke-direct {p0}, Landroid/telephony/SubscriptionManager$OnSubscriptionsChangedListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onSubscriptionsChanged()V
    .locals 1

    .prologue
    .line 83
    iget-object v0, p0, Lcom/android/mms/service/MmsConfigManager$2;->this$0:Lcom/android/mms/service/MmsConfigManager;

    # invokes: Lcom/android/mms/service/MmsConfigManager;->loadInBackground()V
    invoke-static {v0}, Lcom/android/mms/service/MmsConfigManager;->access$000(Lcom/android/mms/service/MmsConfigManager;)V

    .line 84
    return-void
.end method
