.class public Lcom/android/mms/service/MmsNetworkManager;
.super Ljava/lang/Object;
.source "MmsNetworkManager.java"

# interfaces
.implements Lcom/android/okhttp/HostResolver;


# static fields
.field private static final EMPTY_ADDRESS_ARRAY:[Ljava/net/InetAddress;

.field private static final httpKeepAlive:Z

.field private static final httpKeepAliveDurationMs:J

.field private static final httpMaxConnections:I


# instance fields
.field private mConnectionPool:Lcom/android/okhttp/ConnectionPool;

.field private volatile mConnectivityManager:Landroid/net/ConnectivityManager;

.field private final mContext:Landroid/content/Context;

.field private mIsNetworkLost:Z

.field private mIsNetworkReleased:Z

.field private mMmsHttpClient:Lcom/android/mms/service/MmsHttpClient;

.field private mMmsRequestCount:I

.field private mNetwork:Landroid/net/Network;

.field private mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

.field private final mNetworkRequest:Landroid/net/NetworkRequest;

.field private mPendingMmsRequestCount:I

.field private final mSubId:I


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 53
    const-string v0, "http.keepAlive"

    const-string v2, "true"

    invoke-static {v0, v2}, Ljava/lang/System;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v0

    sput-boolean v0, Lcom/android/mms/service/MmsNetworkManager;->httpKeepAlive:Z

    .line 55
    sget-boolean v0, Lcom/android/mms/service/MmsNetworkManager;->httpKeepAlive:Z

    if-eqz v0, :cond_0

    const-string v0, "http.maxConnections"

    const-string v2, "5"

    invoke-static {v0, v2}, Ljava/lang/System;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    :goto_0
    sput v0, Lcom/android/mms/service/MmsNetworkManager;->httpMaxConnections:I

    .line 57
    const-string v0, "http.keepAliveDuration"

    const-string v2, "300000"

    invoke-static {v0, v2}, Ljava/lang/System;->getProperty(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v2

    sput-wide v2, Lcom/android/mms/service/MmsNetworkManager;->httpKeepAliveDurationMs:J

    .line 253
    new-array v0, v1, [Ljava/net/InetAddress;

    sput-object v0, Lcom/android/mms/service/MmsNetworkManager;->EMPTY_ADDRESS_ARRAY:[Ljava/net/InetAddress;

    return-void

    :cond_0
    move v0, v1

    .line 55
    goto :goto_0
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 2
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "subId"    # I

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x0

    .line 88
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 89
    iput-object p1, p0, Lcom/android/mms/service/MmsNetworkManager;->mContext:Landroid/content/Context;

    .line 90
    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    .line 91
    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    .line 92
    iput v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    .line 93
    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectivityManager:Landroid/net/ConnectivityManager;

    .line 94
    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    .line 95
    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsHttpClient:Lcom/android/mms/service/MmsHttpClient;

    .line 96
    iput p2, p0, Lcom/android/mms/service/MmsNetworkManager;->mSubId:I

    .line 97
    iput-boolean v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkLost:Z

    .line 98
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkReleased:Z

    .line 99
    new-instance v0, Landroid/net/NetworkRequest$Builder;

    invoke-direct {v0}, Landroid/net/NetworkRequest$Builder;-><init>()V

    invoke-virtual {v0, v1}, Landroid/net/NetworkRequest$Builder;->addTransportType(I)Landroid/net/NetworkRequest$Builder;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/net/NetworkRequest$Builder;->addCapability(I)Landroid/net/NetworkRequest$Builder;

    move-result-object v0

    iget v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mSubId:I

    invoke-static {v1}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/net/NetworkRequest$Builder;->setNetworkSpecifier(Ljava/lang/String;)Landroid/net/NetworkRequest$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/NetworkRequest$Builder;->build()Landroid/net/NetworkRequest;

    move-result-object v0

    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkRequest:Landroid/net/NetworkRequest;

    .line 104
    return-void
.end method

.method static synthetic access$002(Lcom/android/mms/service/MmsNetworkManager;Landroid/net/Network;)Landroid/net/Network;
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsNetworkManager;
    .param p1, "x1"    # Landroid/net/Network;

    .prologue
    .line 44
    iput-object p1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    return-object p1
.end method

.method static synthetic access$102(Lcom/android/mms/service/MmsNetworkManager;Z)Z
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsNetworkManager;
    .param p1, "x1"    # Z

    .prologue
    .line 44
    iput-boolean p1, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkLost:Z

    return p1
.end method

.method static synthetic access$200(Lcom/android/mms/service/MmsNetworkManager;Landroid/net/ConnectivityManager$NetworkCallback;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsNetworkManager;
    .param p1, "x1"    # Landroid/net/ConnectivityManager$NetworkCallback;

    .prologue
    .line 44
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsNetworkManager;->releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V

    return-void
.end method

.method private getConnectivityManager()Landroid/net/ConnectivityManager;
    .locals 2

    .prologue
    .line 267
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectivityManager:Landroid/net/ConnectivityManager;

    if-nez v0, :cond_0

    .line 268
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mContext:Landroid/content/Context;

    const-string v1, "connectivity"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/ConnectivityManager;

    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectivityManager:Landroid/net/ConnectivityManager;

    .line 271
    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectivityManager:Landroid/net/ConnectivityManager;

    return-object v0
.end method

.method private getOrCreateConnectionPoolLocked()Lcom/android/okhttp/ConnectionPool;
    .locals 4

    .prologue
    .line 275
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    if-nez v0, :cond_0

    .line 276
    new-instance v0, Lcom/android/okhttp/ConnectionPool;

    sget v1, Lcom/android/mms/service/MmsNetworkManager;->httpMaxConnections:I

    sget-wide v2, Lcom/android/mms/service/MmsNetworkManager;->httpKeepAliveDurationMs:J

    invoke-direct {v0, v1, v2, v3}, Lcom/android/okhttp/ConnectionPool;-><init>(IJ)V

    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    .line 278
    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    return-object v0
.end method

.method private newRequest()V
    .locals 4

    .prologue
    .line 184
    invoke-direct {p0}, Lcom/android/mms/service/MmsNetworkManager;->getConnectivityManager()Landroid/net/ConnectivityManager;

    move-result-object v0

    .line 185
    .local v0, "connectivityManager":Landroid/net/ConnectivityManager;
    new-instance v1, Lcom/android/mms/service/MmsNetworkManager$1;

    invoke-direct {v1, p0}, Lcom/android/mms/service/MmsNetworkManager$1;-><init>(Lcom/android/mms/service/MmsNetworkManager;)V

    iput-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    .line 218
    const-string v1, "MmsService"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "newRequest subid = "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget v3, p0, Lcom/android/mms/service/MmsNetworkManager;->mSubId:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 219
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkRequest:Landroid/net/NetworkRequest;

    iget-object v2, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    const v3, 0xea60

    invoke-virtual {v0, v1, v2, v3}, Landroid/net/ConnectivityManager;->requestNetwork(Landroid/net/NetworkRequest;Landroid/net/ConnectivityManager$NetworkCallback;I)V

    .line 221
    return-void
.end method

.method private releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V
    .locals 3
    .param p1, "callback"    # Landroid/net/ConnectivityManager$NetworkCallback;

    .prologue
    .line 229
    if-eqz p1, :cond_0

    .line 230
    const-string v1, "MmsService"

    const-string v2, "MmsNetworkManager: releaseRequestLocked"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 231
    invoke-direct {p0}, Lcom/android/mms/service/MmsNetworkManager;->getConnectivityManager()Landroid/net/ConnectivityManager;

    move-result-object v0

    .line 232
    .local v0, "connectivityManager":Landroid/net/ConnectivityManager;
    invoke-virtual {v0, p1}, Landroid/net/ConnectivityManager;->unregisterNetworkCallback(Landroid/net/ConnectivityManager$NetworkCallback;)V

    .line 234
    .end local v0    # "connectivityManager":Landroid/net/ConnectivityManager;
    :cond_0
    invoke-direct {p0}, Lcom/android/mms/service/MmsNetworkManager;->resetLocked()V

    .line 235
    return-void
.end method

.method private resetLocked()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 241
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkReleased:Z

    .line 242
    iput-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    .line 243
    iput-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    .line 244
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    .line 249
    iput-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    .line 250
    iput-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsHttpClient:Lcom/android/mms/service/MmsHttpClient;

    .line 251
    return-void
.end method


# virtual methods
.method public acquireNetwork()V
    .locals 10
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/android/mms/service/exception/MmsNetworkException;
        }
    .end annotation

    .prologue
    .line 112
    const-string v1, "MmsService"

    const-string v6, "MmsNetworkManager: acquireNetwork start"

    invoke-static {v1, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 113
    monitor-enter p0

    .line 114
    :try_start_0
    iget v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    add-int/lit8 v1, v1, 0x1

    iput v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    .line 115
    const/4 v1, 0x0

    iput-boolean v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkLost:Z

    .line 116
    const/4 v1, 0x0

    iput-boolean v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkReleased:Z

    .line 117
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    if-eqz v1, :cond_0

    .line 119
    const-string v1, "MmsService"

    const-string v6, "MmsNetworkManager: already available"

    invoke-static {v1, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 120
    monitor-exit p0

    .line 136
    :goto_0
    return-void

    .line 122
    :cond_0
    const-string v1, "MmsService"

    const-string v6, "MmsNetworkManager: start new network request"

    invoke-static {v1, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 124
    invoke-direct {p0}, Lcom/android/mms/service/MmsNetworkManager;->newRequest()V

    .line 125
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result-wide v6

    const-wide/32 v8, 0xfde8

    add-long v2, v6, v8

    .line 126
    .local v2, "shouldEnd":J
    const-wide/32 v4, 0xfde8

    .line 128
    .local v4, "waitTime":J
    :goto_1
    const-wide/16 v6, 0x0

    cmp-long v1, v4, v6

    if-lez v1, :cond_2

    .line 130
    :try_start_1
    invoke-virtual {p0, v4, v5}, Ljava/lang/Object;->wait(J)V
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 134
    :goto_2
    :try_start_2
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    if-eqz v1, :cond_1

    .line 136
    monitor-exit p0

    goto :goto_0

    .line 151
    .end local v2    # "shouldEnd":J
    .end local v4    # "waitTime":J
    :catchall_0
    move-exception v1

    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v1

    .line 131
    .restart local v2    # "shouldEnd":J
    .restart local v4    # "waitTime":J
    :catch_0
    move-exception v0

    .line 132
    .local v0, "e":Ljava/lang/InterruptedException;
    :try_start_3
    const-string v1, "MmsService"

    const-string v6, "MmsNetworkManager: acquire network wait interrupted"

    invoke-static {v1, v6}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_2

    .line 139
    .end local v0    # "e":Ljava/lang/InterruptedException;
    :cond_1
    iget-boolean v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkLost:Z

    if-eqz v1, :cond_3

    .line 140
    const-string v1, "MmsService"

    const-string v6, "MmsNetworkManager: network already lost!"

    invoke-static {v1, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 148
    :cond_2
    const-string v1, "MmsService"

    const-string v6, "MmsNetworkManager: timed out"

    invoke-static {v1, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 149
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    invoke-direct {p0, v1}, Lcom/android/mms/service/MmsNetworkManager;->releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V

    .line 150
    new-instance v1, Lcom/android/mms/service/exception/MmsNetworkException;

    const-string v6, "Acquiring network timed out"

    invoke-direct {v1, v6}, Lcom/android/mms/service/exception/MmsNetworkException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 144
    :cond_3
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-result-wide v6

    sub-long v4, v2, v6

    goto :goto_1
.end method

.method public getAllByName(Ljava/lang/String;)[Ljava/net/InetAddress;
    .locals 2
    .param p1, "host"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/net/UnknownHostException;
        }
    .end annotation

    .prologue
    .line 256
    const/4 v0, 0x0

    .line 257
    .local v0, "network":Landroid/net/Network;
    monitor-enter p0

    .line 258
    :try_start_0
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    if-nez v1, :cond_0

    .line 259
    sget-object v1, Lcom/android/mms/service/MmsNetworkManager;->EMPTY_ADDRESS_ARRAY:[Ljava/net/InetAddress;

    monitor-exit p0

    .line 263
    :goto_0
    return-object v1

    .line 261
    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    .line 262
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 263
    invoke-virtual {v0, p1}, Landroid/net/Network;->getAllByName(Ljava/lang/String;)[Ljava/net/InetAddress;

    move-result-object v1

    goto :goto_0

    .line 262
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method public getApnName()Ljava/lang/String;
    .locals 7

    .prologue
    .line 308
    const/4 v3, 0x0

    .line 309
    .local v3, "network":Landroid/net/Network;
    monitor-enter p0

    .line 310
    :try_start_0
    iget-object v4, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    if-nez v4, :cond_0

    .line 311
    const-string v4, "MmsService"

    const-string v5, "MmsNetworkManager: getApnName: network not available"

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 312
    const/4 v0, 0x0

    monitor-exit p0

    .line 323
    :goto_0
    return-object v0

    .line 314
    :cond_0
    iget-object v3, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    .line 315
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 316
    const/4 v0, 0x0

    .line 317
    .local v0, "apnName":Ljava/lang/String;
    invoke-direct {p0}, Lcom/android/mms/service/MmsNetworkManager;->getConnectivityManager()Landroid/net/ConnectivityManager;

    move-result-object v1

    .line 318
    .local v1, "connectivityManager":Landroid/net/ConnectivityManager;
    invoke-virtual {v1, v3}, Landroid/net/ConnectivityManager;->getNetworkInfo(Landroid/net/Network;)Landroid/net/NetworkInfo;

    move-result-object v2

    .line 319
    .local v2, "mmsNetworkInfo":Landroid/net/NetworkInfo;
    if-eqz v2, :cond_1

    .line 320
    invoke-virtual {v2}, Landroid/net/NetworkInfo;->getExtraInfo()Ljava/lang/String;

    move-result-object v0

    .line 322
    :cond_1
    const-string v4, "MmsService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "MmsNetworkManager: getApnName: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 315
    .end local v0    # "apnName":Ljava/lang/String;
    .end local v1    # "connectivityManager":Landroid/net/ConnectivityManager;
    .end local v2    # "mmsNetworkInfo":Landroid/net/NetworkInfo;
    :catchall_0
    move-exception v4

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v4
.end method

.method public getOrCreateHttpClient()Lcom/android/mms/service/MmsHttpClient;
    .locals 4

    .prologue
    .line 287
    monitor-enter p0

    .line 288
    :try_start_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsHttpClient:Lcom/android/mms/service/MmsHttpClient;

    if-nez v0, :cond_0

    .line 289
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    if-eqz v0, :cond_0

    .line 291
    new-instance v0, Lcom/android/mms/service/MmsHttpClient;

    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager;->mContext:Landroid/content/Context;

    iget-object v2, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;

    invoke-virtual {v2}, Landroid/net/Network;->getSocketFactory()Ljavax/net/SocketFactory;

    move-result-object v2

    invoke-direct {p0}, Lcom/android/mms/service/MmsNetworkManager;->getOrCreateConnectionPoolLocked()Lcom/android/okhttp/ConnectionPool;

    move-result-object v3

    invoke-direct {v0, v1, v2, p0, v3}, Lcom/android/mms/service/MmsHttpClient;-><init>(Landroid/content/Context;Ljavax/net/SocketFactory;Lcom/android/okhttp/HostResolver;Lcom/android/okhttp/ConnectionPool;)V

    iput-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsHttpClient:Lcom/android/mms/service/MmsHttpClient;

    .line 298
    :cond_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsHttpClient:Lcom/android/mms/service/MmsHttpClient;

    monitor-exit p0

    return-object v0

    .line 299
    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public isNetworkReleased()Z
    .locals 1

    .prologue
    .line 336
    iget-boolean v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkReleased:Z

    return v0
.end method

.method public releaseNetwork()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 158
    monitor-enter p0

    .line 159
    :try_start_0
    iget v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    if-lez v0, :cond_0

    .line 160
    iget v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    add-int/lit8 v0, v0, -0x1

    iput v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    .line 161
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "MmsNetworkManager: release, count="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", mPendingMmsRequestCount = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lcom/android/mms/service/MmsNetworkManager;->mPendingMmsRequestCount:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 163
    iget v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mMmsRequestCount:I

    if-ge v0, v3, :cond_0

    iget v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mPendingMmsRequestCount:I

    if-ge v0, v3, :cond_0

    .line 164
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsNetworkManager;->releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V

    .line 167
    :cond_0
    monitor-exit p0

    .line 168
    return-void

    .line 167
    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public releaseNetworkImmediately()V
    .locals 2

    .prologue
    .line 174
    monitor-enter p0

    .line 175
    :try_start_0
    const-string v0, "MmsService"

    const-string v1, "MmsNetworkManager: release network immdiately"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 176
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager;->mNetworkCallback:Landroid/net/ConnectivityManager$NetworkCallback;

    invoke-direct {p0, v0}, Lcom/android/mms/service/MmsNetworkManager;->releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V

    .line 177
    monitor-exit p0

    .line 178
    return-void

    .line 177
    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public updatePendingMmsRequestCount(I)V
    .locals 3
    .param p1, "count"    # I

    .prologue
    .line 329
    monitor-enter p0

    .line 330
    :try_start_0
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "MmsNetworkManager: update pending count: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 331
    iput p1, p0, Lcom/android/mms/service/MmsNetworkManager;->mPendingMmsRequestCount:I

    .line 332
    monitor-exit p0

    .line 333
    return-void

    .line 332
    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method
