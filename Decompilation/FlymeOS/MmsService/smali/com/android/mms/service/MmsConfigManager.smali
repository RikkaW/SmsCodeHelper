.class public Lcom/android/mms/service/MmsConfigManager;
.super Ljava/lang/Object;
.source "MmsConfigManager.java"


# static fields
.field private static volatile sInstance:Lcom/android/mms/service/MmsConfigManager;


# instance fields
.field private mContext:Landroid/content/Context;

.field private final mOnSubscriptionsChangedListener:Landroid/telephony/SubscriptionManager$OnSubscriptionsChangedListener;

.field private final mReceiver:Landroid/content/BroadcastReceiver;

.field private final mSubIdConfigMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map",
            "<",
            "Ljava/lang/Integer;",
            "Lcom/android/mms/service/MmsConfig;",
            ">;"
        }
    .end annotation
.end field

.field private mSubscriptionManager:Landroid/telephony/SubscriptionManager;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 51
    new-instance v0, Lcom/android/mms/service/MmsConfigManager;

    invoke-direct {v0}, Lcom/android/mms/service/MmsConfigManager;-><init>()V

    sput-object v0, Lcom/android/mms/service/MmsConfigManager;->sInstance:Lcom/android/mms/service/MmsConfigManager;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 48
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 58
    new-instance v0, Landroid/util/ArrayMap;

    invoke-direct {v0}, Landroid/util/ArrayMap;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsConfigManager;->mSubIdConfigMap:Ljava/util/Map;

    .line 69
    new-instance v0, Lcom/android/mms/service/MmsConfigManager$1;

    invoke-direct {v0, p0}, Lcom/android/mms/service/MmsConfigManager$1;-><init>(Lcom/android/mms/service/MmsConfigManager;)V

    iput-object v0, p0, Lcom/android/mms/service/MmsConfigManager;->mReceiver:Landroid/content/BroadcastReceiver;

    .line 79
    new-instance v0, Lcom/android/mms/service/MmsConfigManager$2;

    invoke-direct {v0, p0}, Lcom/android/mms/service/MmsConfigManager$2;-><init>(Lcom/android/mms/service/MmsConfigManager;)V

    iput-object v0, p0, Lcom/android/mms/service/MmsConfigManager;->mOnSubscriptionsChangedListener:Landroid/telephony/SubscriptionManager$OnSubscriptionsChangedListener;

    return-void
.end method

.method static synthetic access$000(Lcom/android/mms/service/MmsConfigManager;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfigManager;

    .prologue
    .line 48
    invoke-direct {p0}, Lcom/android/mms/service/MmsConfigManager;->loadInBackground()V

    return-void
.end method

.method static synthetic access$100(Lcom/android/mms/service/MmsConfigManager;)Landroid/content/Context;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfigManager;

    .prologue
    .line 48
    iget-object v0, p0, Lcom/android/mms/service/MmsConfigManager;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method static synthetic access$200(Lcom/android/mms/service/MmsConfigManager;Landroid/content/Context;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsConfigManager;
    .param p1, "x1"    # Landroid/content/Context;

    .prologue
    .line 48
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsConfigManager;->load(Landroid/content/Context;)V

    return-void
.end method

.method public static getInstance()Lcom/android/mms/service/MmsConfigManager;
    .locals 1

    .prologue
    .line 54
    sget-object v0, Lcom/android/mms/service/MmsConfigManager;->sInstance:Lcom/android/mms/service/MmsConfigManager;

    return-object v0
.end method

.method private load(Landroid/content/Context;)V
    .locals 12
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 148
    iget-object v9, p0, Lcom/android/mms/service/MmsConfigManager;->mSubscriptionManager:Landroid/telephony/SubscriptionManager;

    invoke-virtual {v9}, Landroid/telephony/SubscriptionManager;->getActiveSubscriptionInfoList()Ljava/util/List;

    move-result-object v8

    .line 149
    .local v8, "subs":Ljava/util/List;, "Ljava/util/List<Landroid/telephony/SubscriptionInfo;>;"
    if-eqz v8, :cond_0

    invoke-interface {v8}, Ljava/util/List;->size()I

    move-result v9

    const/4 v10, 0x1

    if-ge v9, v10, :cond_1

    .line 150
    :cond_0
    const-string v9, "MmsService"

    const-string v10, "MmsConfigManager.load -- empty getActiveSubInfoList"

    invoke-static {v9, v10}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 188
    :goto_0
    return-void

    .line 153
    :cond_1
    const-string v9, "MmsService"

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "MmsConfigManager.load -- subs.size()"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-interface {v8}, Ljava/util/List;->size()I

    move-result v11

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 156
    new-instance v4, Landroid/util/ArrayMap;

    invoke-direct {v4}, Landroid/util/ArrayMap;-><init>()V

    .line 157
    .local v4, "newConfigMap":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/Integer;Lcom/android/mms/service/MmsConfig;>;"
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 158
    .local v3, "localSubs":Ljava/util/List;, "Ljava/util/List<Landroid/telephony/SubscriptionInfo;>;"
    monitor-enter v8

    .line 159
    :try_start_0
    invoke-interface {v3, v8}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 160
    monitor-exit v8
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 161
    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .local v2, "i$":Ljava/util/Iterator;
    :cond_2
    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_4

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroid/telephony/SubscriptionInfo;

    .line 162
    .local v5, "sub":Landroid/telephony/SubscriptionInfo;
    if-eqz v5, :cond_2

    .line 165
    new-instance v1, Landroid/content/res/Configuration;

    invoke-direct {v1}, Landroid/content/res/Configuration;-><init>()V

    .line 166
    .local v1, "configuration":Landroid/content/res/Configuration;
    invoke-virtual {v5}, Landroid/telephony/SubscriptionInfo;->getMcc()I

    move-result v9

    if-nez v9, :cond_3

    invoke-virtual {v5}, Landroid/telephony/SubscriptionInfo;->getMnc()I

    move-result v9

    if-nez v9, :cond_3

    .line 167
    iget-object v9, p0, Lcom/android/mms/service/MmsConfigManager;->mContext:Landroid/content/Context;

    invoke-virtual {v9}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v9

    invoke-virtual {v9}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    .line 168
    .local v0, "config":Landroid/content/res/Configuration;
    iget v9, v0, Landroid/content/res/Configuration;->mcc:I

    iput v9, v1, Landroid/content/res/Configuration;->mcc:I

    .line 169
    iget v9, v0, Landroid/content/res/Configuration;->mnc:I

    iput v9, v1, Landroid/content/res/Configuration;->mnc:I

    .line 170
    const-string v9, "MmsService"

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "MmsConfigManager.load -- no mcc/mnc for sub: "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, " using mcc/mnc from main context: "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    iget v11, v1, Landroid/content/res/Configuration;->mcc:I

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, "/"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    iget v11, v1, Landroid/content/res/Configuration;->mnc:I

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 179
    .end local v0    # "config":Landroid/content/res/Configuration;
    :goto_2
    invoke-virtual {p1, v1}, Landroid/content/Context;->createConfigurationContext(Landroid/content/res/Configuration;)Landroid/content/Context;

    move-result-object v6

    .line 181
    .local v6, "subContext":Landroid/content/Context;
    invoke-virtual {v5}, Landroid/telephony/SubscriptionInfo;->getSubscriptionId()I

    move-result v7

    .line 182
    .local v7, "subId":I
    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v9

    new-instance v10, Lcom/android/mms/service/MmsConfig;

    invoke-direct {v10, v6, v7}, Lcom/android/mms/service/MmsConfig;-><init>(Landroid/content/Context;I)V

    invoke-interface {v4, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    .line 160
    .end local v1    # "configuration":Landroid/content/res/Configuration;
    .end local v2    # "i$":Ljava/util/Iterator;
    .end local v5    # "sub":Landroid/telephony/SubscriptionInfo;
    .end local v6    # "subContext":Landroid/content/Context;
    .end local v7    # "subId":I
    :catchall_0
    move-exception v9

    :try_start_1
    monitor-exit v8
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v9

    .line 174
    .restart local v1    # "configuration":Landroid/content/res/Configuration;
    .restart local v2    # "i$":Ljava/util/Iterator;
    .restart local v5    # "sub":Landroid/telephony/SubscriptionInfo;
    :cond_3
    const-string v9, "MmsService"

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "MmsConfigManager.load -- mcc/mnc for sub: "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 176
    invoke-virtual {v5}, Landroid/telephony/SubscriptionInfo;->getMcc()I

    move-result v9

    iput v9, v1, Landroid/content/res/Configuration;->mcc:I

    .line 177
    invoke-virtual {v5}, Landroid/telephony/SubscriptionInfo;->getMnc()I

    move-result v9

    iput v9, v1, Landroid/content/res/Configuration;->mnc:I

    goto :goto_2

    .line 184
    .end local v1    # "configuration":Landroid/content/res/Configuration;
    .end local v5    # "sub":Landroid/telephony/SubscriptionInfo;
    :cond_4
    iget-object v10, p0, Lcom/android/mms/service/MmsConfigManager;->mSubIdConfigMap:Ljava/util/Map;

    monitor-enter v10

    .line 185
    :try_start_2
    iget-object v9, p0, Lcom/android/mms/service/MmsConfigManager;->mSubIdConfigMap:Ljava/util/Map;

    invoke-interface {v9}, Ljava/util/Map;->clear()V

    .line 186
    iget-object v9, p0, Lcom/android/mms/service/MmsConfigManager;->mSubIdConfigMap:Ljava/util/Map;

    invoke-interface {v9, v4}, Ljava/util/Map;->putAll(Ljava/util/Map;)V

    .line 187
    monitor-exit v10

    goto/16 :goto_0

    :catchall_1
    move-exception v9

    monitor-exit v10
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw v9
.end method

.method private loadInBackground()V
    .locals 1

    .prologue
    .line 110
    new-instance v0, Lcom/android/mms/service/MmsConfigManager$3;

    invoke-direct {v0, p0}, Lcom/android/mms/service/MmsConfigManager$3;-><init>(Lcom/android/mms/service/MmsConfigManager;)V

    invoke-virtual {v0}, Lcom/android/mms/service/MmsConfigManager$3;->start()V

    .line 121
    return-void
.end method


# virtual methods
.method public getMmsConfigBySubId(I)Lcom/android/mms/service/MmsConfig;
    .locals 4
    .param p1, "subId"    # I

    .prologue
    .line 134
    iget-object v2, p0, Lcom/android/mms/service/MmsConfigManager;->mSubIdConfigMap:Ljava/util/Map;

    monitor-enter v2

    .line 135
    :try_start_0
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigManager;->mSubIdConfigMap:Ljava/util/Map;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-interface {v1, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/android/mms/service/MmsConfig;

    .line 136
    .local v0, "mmsConfig":Lcom/android/mms/service/MmsConfig;
    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 137
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "getMmsConfigBySubId -- for sub: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " mmsConfig: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 138
    return-object v0

    .line 136
    .end local v0    # "mmsConfig":Lcom/android/mms/service/MmsConfig;
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method public init(Landroid/content/Context;)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 89
    iput-object p1, p0, Lcom/android/mms/service/MmsConfigManager;->mContext:Landroid/content/Context;

    .line 90
    invoke-static {p1}, Landroid/telephony/SubscriptionManager;->from(Landroid/content/Context;)Landroid/telephony/SubscriptionManager;

    move-result-object v1

    iput-object v1, p0, Lcom/android/mms/service/MmsConfigManager;->mSubscriptionManager:Landroid/telephony/SubscriptionManager;

    .line 93
    new-instance v0, Landroid/content/IntentFilter;

    const-string v1, "LOADED"

    invoke-direct {v0, v1}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    .line 95
    .local v0, "intentFilterLoaded":Landroid/content/IntentFilter;
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigManager;->mReceiver:Landroid/content/BroadcastReceiver;

    invoke-virtual {p1, v1, v0}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 104
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigManager;->mContext:Landroid/content/Context;

    invoke-static {v1}, Landroid/telephony/SubscriptionManager;->from(Landroid/content/Context;)Landroid/telephony/SubscriptionManager;

    move-result-object v1

    iget-object v2, p0, Lcom/android/mms/service/MmsConfigManager;->mOnSubscriptionsChangedListener:Landroid/telephony/SubscriptionManager$OnSubscriptionsChangedListener;

    invoke-virtual {v1, v2}, Landroid/telephony/SubscriptionManager;->addOnSubscriptionsChangedListener(Landroid/telephony/SubscriptionManager$OnSubscriptionsChangedListener;)V

    .line 106
    return-void
.end method
