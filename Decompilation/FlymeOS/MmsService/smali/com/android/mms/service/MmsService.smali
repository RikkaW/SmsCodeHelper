.class public Lcom/android/mms/service/MmsService;
.super Landroid/app/Service;
.source "MmsService.java"

# interfaces
.implements Lcom/android/mms/service/MmsRequest$RequestManager;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/mms/service/MmsService$RequestQueue;
    }
.end annotation


# instance fields
.field private mCurrentSubId:I

.field private final mExecutor:Ljava/util/concurrent/ExecutorService;

.field private final mNetworkManagerCache:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray",
            "<",
            "Lcom/android/mms/service/MmsNetworkManager;",
            ">;"
        }
    .end annotation
.end field

.field private final mPendingSimRequestQueue:Ljava/util/Queue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Queue",
            "<",
            "Lcom/android/mms/service/MmsRequest;",
            ">;"
        }
    .end annotation
.end field

.field private final mPendingSimRequestQueues:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray",
            "<",
            "Ljava/util/Queue",
            "<",
            "Lcom/android/mms/service/MmsRequest;",
            ">;>;"
        }
    .end annotation
.end field

.field private mRunningRequestCount:I

.field private final mRunningRequestQueues:[Lcom/android/mms/service/MmsService$RequestQueue;

.field private final mRunningRequestQueuesMap:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray",
            "<",
            "Lcom/android/mms/service/MmsService$RequestQueue;",
            ">;"
        }
    .end annotation
.end field

.field private mStub:Lcom/android/internal/telephony/IMms$Stub;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 89
    invoke-direct {p0}, Landroid/app/Service;-><init>()V

    .line 113
    new-instance v0, Ljava/util/ArrayDeque;

    invoke-direct {v0}, Ljava/util/ArrayDeque;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueue:Ljava/util/Queue;

    .line 115
    invoke-static {}, Ljava/util/concurrent/Executors;->newCachedThreadPool()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mExecutor:Ljava/util/concurrent/ExecutorService;

    .line 118
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mNetworkManagerCache:Landroid/util/SparseArray;

    .line 198
    new-instance v0, Lcom/android/mms/service/MmsService$1;

    invoke-direct {v0, p0}, Lcom/android/mms/service/MmsService$1;-><init>(Lcom/android/mms/service/MmsService;)V

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mStub:Lcom/android/internal/telephony/IMms$Stub;

    .line 415
    const/4 v0, 0x2

    new-array v0, v0, [Lcom/android/mms/service/MmsService$RequestQueue;

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mRunningRequestQueues:[Lcom/android/mms/service/MmsService$RequestQueue;

    .line 1073
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    .line 1074
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsService;->mRunningRequestQueuesMap:Landroid/util/SparseArray;

    return-void
.end method

.method static synthetic access$000(Lcom/android/mms/service/MmsService;I)Lcom/android/mms/service/MmsNetworkManager;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # I

    .prologue
    .line 89
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->getNetworkManager(I)Lcom/android/mms/service/MmsNetworkManager;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$100(Lcom/android/mms/service/MmsService;)I
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;

    .prologue
    .line 89
    iget v0, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    return v0
.end method

.method static synthetic access$1000(Landroid/net/Uri;)Z
    .locals 1
    .param p0, "x0"    # Landroid/net/Uri;

    .prologue
    .line 89
    invoke-static {p0}, Lcom/android/mms/service/MmsService;->isSmsMmsContentUri(Landroid/net/Uri;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$110(Lcom/android/mms/service/MmsService;)I
    .locals 2
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;

    .prologue
    .line 89
    iget v0, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    add-int/lit8 v1, v0, -0x1

    iput v1, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    return v0
.end method

.method static synthetic access$1100(Lcom/android/mms/service/MmsService;Landroid/net/Uri;Landroid/content/ContentValues;)Z
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Landroid/net/Uri;
    .param p2, "x2"    # Landroid/content/ContentValues;

    .prologue
    .line 89
    invoke-direct {p0, p1, p2}, Lcom/android/mms/service/MmsService;->updateMessageStatus(Landroid/net/Uri;Landroid/content/ContentValues;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$1200(Lcom/android/mms/service/MmsService;JZ)Z
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # J
    .param p3, "x2"    # Z

    .prologue
    .line 89
    invoke-direct {p0, p1, p2, p3}, Lcom/android/mms/service/MmsService;->archiveConversation(JZ)Z

    move-result v0

    return v0
.end method

.method static synthetic access$1300(Lcom/android/mms/service/MmsService;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Ljava/lang/String;
    .param p2, "x2"    # Ljava/lang/String;
    .param p3, "x3"    # Ljava/lang/String;

    .prologue
    .line 89
    invoke-direct {p0, p1, p2, p3}, Lcom/android/mms/service/MmsService;->addSmsDraft(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$1400(Lcom/android/mms/service/MmsService;Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Landroid/net/Uri;
    .param p2, "x2"    # Ljava/lang/String;

    .prologue
    .line 89
    invoke-direct {p0, p1, p2}, Lcom/android/mms/service/MmsService;->addMmsDraft(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$1500(Lcom/android/mms/service/MmsService;Landroid/net/Uri;)Z
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Landroid/net/Uri;

    .prologue
    .line 89
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->isFailedOrDraft(Landroid/net/Uri;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$1600(Lcom/android/mms/service/MmsService;Landroid/app/PendingIntent;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Landroid/app/PendingIntent;

    .prologue
    .line 89
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->returnUnspecifiedFailure(Landroid/app/PendingIntent;)V

    return-void
.end method

.method static synthetic access$1700(Lcom/android/mms/service/MmsService;Landroid/net/Uri;)[B
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Landroid/net/Uri;

    .prologue
    .line 89
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->loadPdu(Landroid/net/Uri;)[B

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$200(Lcom/android/mms/service/MmsService;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;

    .prologue
    .line 89
    invoke-direct {p0}, Lcom/android/mms/service/MmsService;->movePendingSimRequestsToRunningSynchronized()V

    return-void
.end method

.method static synthetic access$300(Lcom/android/mms/service/MmsService;)Landroid/util/SparseArray;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;

    .prologue
    .line 89
    iget-object v0, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    return-object v0
.end method

.method static synthetic access$400(Lcom/android/mms/service/MmsService;I)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # I

    .prologue
    .line 89
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->updatePendingMmsRequestCountForSubId(I)V

    return-void
.end method

.method static synthetic access$500(Lcom/android/mms/service/MmsService;)V
    .locals 0
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;

    .prologue
    .line 89
    invoke-direct {p0}, Lcom/android/mms/service/MmsService;->enforceSystemUid()V

    return-void
.end method

.method static synthetic access$600(Lcom/android/mms/service/MmsService;I)I
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # I

    .prologue
    .line 89
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->checkSubId(I)I

    move-result v0

    return v0
.end method

.method static synthetic access$700(Lcom/android/mms/service/MmsService;)Ljava/lang/String;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;

    .prologue
    .line 89
    invoke-direct {p0}, Lcom/android/mms/service/MmsService;->getCarrierMessagingServicePackageIfExists()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$800(Lcom/android/mms/service/MmsService;Ljava/lang/String;ILjava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;
    .locals 2
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Ljava/lang/String;
    .param p2, "x2"    # I
    .param p3, "x3"    # Ljava/lang/String;
    .param p4, "x4"    # J
    .param p6, "x5"    # Z
    .param p7, "x6"    # Z
    .param p8, "x7"    # Ljava/lang/String;

    .prologue
    .line 89
    invoke-direct/range {p0 .. p8}, Lcom/android/mms/service/MmsService;->importSms(Ljava/lang/String;ILjava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$900(Lcom/android/mms/service/MmsService;Landroid/net/Uri;Ljava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;
    .locals 1
    .param p0, "x0"    # Lcom/android/mms/service/MmsService;
    .param p1, "x1"    # Landroid/net/Uri;
    .param p2, "x2"    # Ljava/lang/String;
    .param p3, "x3"    # J
    .param p5, "x4"    # Z
    .param p6, "x5"    # Z
    .param p7, "x6"    # Ljava/lang/String;

    .prologue
    .line 89
    invoke-direct/range {p0 .. p7}, Lcom/android/mms/service/MmsService;->importMms(Landroid/net/Uri;Ljava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method private addMmsDraft(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    .locals 12
    .param p1, "contentUri"    # Landroid/net/Uri;
    .param p2, "creator"    # Ljava/lang/String;

    .prologue
    .line 809
    const/high16 v2, 0x800000

    invoke-virtual {p0, p1, v2}, Lcom/android/mms/service/MmsService;->readPduFromContentUri(Landroid/net/Uri;I)[B

    move-result-object v9

    .line 810
    .local v9, "pduData":[B
    if-eqz v9, :cond_0

    array-length v2, v9

    const/4 v3, 0x1

    if-ge v2, v3, :cond_1

    .line 811
    :cond_0
    const-string v2, "MmsService"

    const-string v3, "addMmsDraft: empty PDU"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 812
    const/4 v4, 0x0

    .line 858
    :goto_0
    return-object v4

    .line 817
    :cond_1
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v10

    .line 819
    .local v10, "identity":J
    :try_start_0
    invoke-static {v9}, Lcom/android/mms/service/MmsService;->parsePduForAnyCarrier([B)Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v1

    .line 821
    .local v1, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    if-nez v1, :cond_2

    .line 822
    const-string v2, "MmsService"

    const-string v3, "addMmsDraft: can\'t parse input PDU"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 823
    const/4 v4, 0x0

    .line 856
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 825
    :cond_2
    :try_start_1
    instance-of v2, v1, Lcom/meizu/android/mms/pdu/MzSendReq;

    if-nez v2, :cond_3

    .line 826
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "addMmsDraft; invalid MMS type: "

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/Class;->getCanonicalName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 827
    const/4 v4, 0x0

    .line 856
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 829
    :cond_3
    :try_start_2
    invoke-static {p0}, Lcom/meizu/android/mms/pdu/MzPduPersister;->getPduPersister(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;

    move-result-object v0

    .line 830
    .local v0, "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    sget-object v2, Landroid/provider/Telephony$Mms$Draft;->CONTENT_URI:Landroid/net/Uri;

    const/4 v3, 0x1

    const/4 v4, 0x1

    const/4 v5, 0x0

    invoke-virtual/range {v0 .. v5}, Lcom/meizu/android/mms/pdu/MzPduPersister;->persist(Lcom/meizu/android/mms/pdu/MzGenericPdu;Landroid/net/Uri;ZZLjava/util/HashMap;)Landroid/net/Uri;

    move-result-object v4

    .line 836
    .local v4, "uri":Landroid/net/Uri;
    if-nez v4, :cond_4

    .line 837
    const-string v2, "MmsService"

    const-string v3, "addMmsDraft: failed to persist message"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_2
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 838
    const/4 v4, 0x0

    .line 856
    .end local v4    # "uri":Landroid/net/Uri;
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 840
    .restart local v4    # "uri":Landroid/net/Uri;
    :cond_4
    :try_start_3
    new-instance v5, Landroid/content/ContentValues;

    const/4 v2, 0x3

    invoke-direct {v5, v2}, Landroid/content/ContentValues;-><init>(I)V

    .line 841
    .local v5, "values":Landroid/content/ContentValues;
    const-string v2, "read"

    const/4 v3, 0x1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 842
    const-string v2, "seen"

    const/4 v3, 0x1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v5, v2, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 843
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_5

    .line 844
    const-string v2, "creator"

    invoke-virtual {v5, v2, p2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 846
    :cond_5
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v2, p0

    invoke-static/range {v2 .. v7}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v2

    const/4 v3, 0x1

    if-eq v2, v3, :cond_6

    .line 848
    const-string v2, "MmsService"

    const-string v3, "addMmsDraft: failed to update message"

    invoke-static {v2, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_3
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 856
    :cond_6
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .line 851
    .end local v0    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    .end local v1    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .end local v4    # "uri":Landroid/net/Uri;
    .end local v5    # "values":Landroid/content/ContentValues;
    :catch_0
    move-exception v8

    .line 852
    .local v8, "e":Ljava/lang/RuntimeException;
    :try_start_4
    const-string v2, "MmsService"

    const-string v3, "addMmsDraft: failed to parse input PDU"

    invoke-static {v2, v3, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 856
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 858
    .end local v8    # "e":Ljava/lang/RuntimeException;
    :goto_1
    const/4 v4, 0x0

    goto/16 :goto_0

    .line 853
    :catch_1
    move-exception v8

    .line 854
    .local v8, "e":Lcom/google/android/mms/MmsException;
    :try_start_5
    const-string v2, "MmsService"

    const-string v3, "addMmsDraft: failed to persist message"

    invoke-static {v2, v3, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 856
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .end local v8    # "e":Lcom/google/android/mms/MmsException;
    :catchall_0
    move-exception v2

    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v2
.end method

.method private addSmsDraft(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;
    .locals 7
    .param p1, "address"    # Ljava/lang/String;
    .param p2, "text"    # Ljava/lang/String;
    .param p3, "creator"    # Ljava/lang/String;

    .prologue
    const/4 v6, 0x1

    .line 786
    new-instance v1, Landroid/content/ContentValues;

    const/4 v4, 0x5

    invoke-direct {v1, v4}, Landroid/content/ContentValues;-><init>(I)V

    .line 787
    .local v1, "values":Landroid/content/ContentValues;
    const-string v4, "address"

    invoke-virtual {v1, v4, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 788
    const-string v4, "body"

    invoke-virtual {v1, v4, p2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 789
    const-string v4, "read"

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v1, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 790
    const-string v4, "seen"

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v1, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 791
    invoke-static {p3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_0

    .line 792
    const-string v4, "creator"

    invoke-virtual {v1, v4, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 797
    :cond_0
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v2

    .line 799
    .local v2, "identity":J
    :try_start_0
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v4

    sget-object v5, Landroid/provider/Telephony$Sms$Draft;->CONTENT_URI:Landroid/net/Uri;

    invoke-virtual {v4, v5, v1}, Landroid/content/ContentResolver;->insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result-object v4

    .line 803
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 805
    :goto_0
    return-object v4

    .line 800
    :catch_0
    move-exception v0

    .line 801
    .local v0, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v4, "MmsService"

    const-string v5, "addSmsDraft: failed to store draft message"

    invoke-static {v4, v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 803
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 805
    const/4 v4, 0x0

    goto :goto_0

    .line 803
    .end local v0    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v4

    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v4
.end method

.method private addToRunningRequestQueueSynchronized(Lcom/android/mms/service/MmsRequest;)V
    .locals 5
    .param p1, "request"    # Lcom/android/mms/service/MmsRequest;

    .prologue
    .line 495
    const-string v2, "MmsService"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Add request to running queue for subId "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {p1}, Lcom/android/mms/service/MmsRequest;->getSubId()I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 497
    invoke-virtual {p1}, Lcom/android/mms/service/MmsRequest;->getSubId()I

    move-result v2

    iput v2, p0, Lcom/android/mms/service/MmsService;->mCurrentSubId:I

    .line 498
    iget v2, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    add-int/lit8 v2, v2, 0x1

    iput v2, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    .line 508
    iget v2, p0, Lcom/android/mms/service/MmsService;->mCurrentSubId:I

    invoke-direct {p0, v2}, Lcom/android/mms/service/MmsService;->getRequestQueue(I)Lcom/android/mms/service/MmsService$RequestQueue;

    move-result-object v1

    .line 509
    .local v1, "requestQueue":Lcom/android/mms/service/MmsService$RequestQueue;
    if-nez v1, :cond_0

    .line 515
    :goto_0
    return-void

    .line 512
    :cond_0
    invoke-static {}, Landroid/os/Message;->obtain()Landroid/os/Message;

    move-result-object v0

    .line 513
    .local v0, "message":Landroid/os/Message;
    iput-object p1, v0, Landroid/os/Message;->obj:Ljava/lang/Object;

    .line 514
    invoke-virtual {v1, v0}, Lcom/android/mms/service/MmsService$RequestQueue;->sendMessage(Landroid/os/Message;)Z

    goto :goto_0
.end method

.method private archiveConversation(JZ)Z
    .locals 13
    .param p1, "conversationId"    # J
    .param p3, "archived"    # Z

    .prologue
    const/4 v6, 0x0

    const/4 v5, 0x1

    .line 761
    new-instance v1, Landroid/content/ContentValues;

    invoke-direct {v1, v5}, Landroid/content/ContentValues;-><init>(I)V

    .line 762
    .local v1, "values":Landroid/content/ContentValues;
    const-string v7, "archived"

    if-eqz p3, :cond_0

    move v4, v5

    :goto_0
    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v1, v7, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 766
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v2

    .line 768
    .local v2, "identity":J
    :try_start_0
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v4

    sget-object v7, Landroid/provider/Telephony$Threads;->CONTENT_URI:Landroid/net/Uri;

    const-string v8, "_id=?"

    const/4 v9, 0x1

    new-array v9, v9, [Ljava/lang/String;

    const/4 v10, 0x0

    invoke-static {p1, p2}, Ljava/lang/Long;->toString(J)Ljava/lang/String;

    move-result-object v11

    aput-object v11, v9, v10

    invoke-virtual {v4, v7, v1, v8, v9}, Landroid/content/ContentResolver;->update(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v4

    if-eq v4, v5, :cond_1

    .line 773
    const-string v4, "MmsService"

    const-string v5, "archiveConversation: failed to update database"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 780
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 782
    :goto_1
    return v6

    .end local v2    # "identity":J
    :cond_0
    move v4, v6

    .line 762
    goto :goto_0

    .line 780
    .restart local v2    # "identity":J
    :cond_1
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    move v6, v5

    goto :goto_1

    .line 777
    :catch_0
    move-exception v0

    .line 778
    .local v0, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v4, "MmsService"

    const-string v5, "archiveConversation: failed to update database"

    invoke-static {v4, v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 780
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .end local v0    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v4

    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v4
.end method

.method private checkSubId(I)I
    .locals 3
    .param p1, "subId"    # I

    .prologue
    .line 175
    invoke-static {p1}, Landroid/telephony/SubscriptionManager;->isValidSubscriptionId(I)Z

    move-result v0

    if-nez v0, :cond_0

    .line 176
    new-instance v0, Ljava/lang/RuntimeException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Invalid subId "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 178
    :cond_0
    const v0, 0x7fffffff

    if-ne p1, v0, :cond_1

    .line 179
    invoke-static {}, Landroid/telephony/SubscriptionManager;->getDefaultSmsSubId()I

    move-result p1

    .line 181
    .end local p1    # "subId":I
    :cond_1
    return p1
.end method

.method private enforceSystemUid()V
    .locals 2

    .prologue
    .line 169
    invoke-static {}, Landroid/os/Binder;->getCallingUid()I

    move-result v0

    const/16 v1, 0x3e8

    if-eq v0, v1, :cond_0

    .line 170
    new-instance v0, Ljava/lang/SecurityException;

    const-string v1, "Only system can call this service"

    invoke-direct {v0, v1}, Ljava/lang/SecurityException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 172
    :cond_0
    return-void
.end method

.method private getCarrierMessagingServicePackageIfExists()Ljava/lang/String;
    .locals 5

    .prologue
    .line 186
    new-instance v1, Landroid/content/Intent;

    const-string v3, "android.service.carrier.CarrierMessagingService"

    invoke-direct {v1, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 187
    .local v1, "intent":Landroid/content/Intent;
    const-string v3, "phone"

    invoke-virtual {p0, v3}, Lcom/android/mms/service/MmsService;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/telephony/TelephonyManager;

    .line 189
    .local v2, "telephonyManager":Landroid/telephony/TelephonyManager;
    invoke-virtual {v2, v1}, Landroid/telephony/TelephonyManager;->getCarrierPackageNamesForIntent(Landroid/content/Intent;)Ljava/util/List;

    move-result-object v0

    .line 191
    .local v0, "carrierPackages":Ljava/util/List;, "Ljava/util/List<Ljava/lang/String;>;"
    if-eqz v0, :cond_0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v3

    const/4 v4, 0x1

    if-eq v3, v4, :cond_1

    .line 192
    :cond_0
    const/4 v3, 0x0

    .line 194
    :goto_0
    return-object v3

    :cond_1
    const/4 v3, 0x0

    invoke-interface {v0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    goto :goto_0
.end method

.method private getNetworkManager(I)Lcom/android/mms/service/MmsNetworkManager;
    .locals 3
    .param p1, "subId"    # I

    .prologue
    .line 158
    iget-object v2, p0, Lcom/android/mms/service/MmsService;->mNetworkManagerCache:Landroid/util/SparseArray;

    monitor-enter v2

    .line 159
    :try_start_0
    iget-object v1, p0, Lcom/android/mms/service/MmsService;->mNetworkManagerCache:Landroid/util/SparseArray;

    invoke-virtual {v1, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/android/mms/service/MmsNetworkManager;

    .line 160
    .local v0, "manager":Lcom/android/mms/service/MmsNetworkManager;
    if-nez v0, :cond_0

    .line 161
    new-instance v0, Lcom/android/mms/service/MmsNetworkManager;

    .end local v0    # "manager":Lcom/android/mms/service/MmsNetworkManager;
    invoke-direct {v0, p0, p1}, Lcom/android/mms/service/MmsNetworkManager;-><init>(Landroid/content/Context;I)V

    .line 162
    .restart local v0    # "manager":Lcom/android/mms/service/MmsNetworkManager;
    iget-object v1, p0, Lcom/android/mms/service/MmsService;->mNetworkManagerCache:Landroid/util/SparseArray;

    invoke-virtual {v1, p1, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 164
    :cond_0
    monitor-exit v2

    return-object v0

    .line 165
    .end local v0    # "manager":Lcom/android/mms/service/MmsNetworkManager;
    :catchall_0
    move-exception v1

    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private getPendingSimRequestQueue(I)Ljava/util/Queue;
    .locals 3
    .param p1, "subId"    # I
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)",
            "Ljava/util/Queue",
            "<",
            "Lcom/android/mms/service/MmsRequest;",
            ">;"
        }
    .end annotation

    .prologue
    .line 1076
    invoke-static {p1}, Landroid/telephony/SubscriptionManager;->isValidSubscriptionId(I)Z

    move-result v1

    if-nez v1, :cond_0

    .line 1077
    const/4 v0, 0x0

    .line 1085
    :goto_0
    return-object v0

    .line 1079
    :cond_0
    iget-object v2, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    monitor-enter v2

    .line 1080
    :try_start_0
    iget-object v1, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    invoke-virtual {v1, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Queue;

    .line 1081
    .local v0, "requestQueque":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    if-nez v0, :cond_1

    .line 1082
    new-instance v0, Ljava/util/ArrayDeque;

    .end local v0    # "requestQueque":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    invoke-direct {v0}, Ljava/util/ArrayDeque;-><init>()V

    .line 1083
    .restart local v0    # "requestQueque":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    iget-object v1, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    invoke-virtual {v1, p1, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 1085
    :cond_1
    monitor-exit v2

    goto :goto_0

    .line 1086
    .end local v0    # "requestQueque":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    :catchall_0
    move-exception v1

    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private getRequestQueue(I)Lcom/android/mms/service/MmsService$RequestQueue;
    .locals 5
    .param p1, "subId"    # I

    .prologue
    .line 1089
    invoke-static {p1}, Landroid/telephony/SubscriptionManager;->isValidSubscriptionId(I)Z

    move-result v2

    if-nez v2, :cond_0

    .line 1090
    const/4 v0, 0x0

    .line 1101
    :goto_0
    return-object v0

    .line 1092
    :cond_0
    iget-object v3, p0, Lcom/android/mms/service/MmsService;->mRunningRequestQueuesMap:Landroid/util/SparseArray;

    monitor-enter v3

    .line 1093
    :try_start_0
    iget-object v2, p0, Lcom/android/mms/service/MmsService;->mRunningRequestQueuesMap:Landroid/util/SparseArray;

    invoke-virtual {v2, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/android/mms/service/MmsService$RequestQueue;

    .line 1094
    .local v0, "requestQueque":Lcom/android/mms/service/MmsService$RequestQueue;
    if-nez v0, :cond_1

    .line 1095
    new-instance v1, Landroid/os/HandlerThread;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "MmsService RequestQueueEx "

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    .line 1097
    .local v1, "thread":Landroid/os/HandlerThread;
    invoke-virtual {v1}, Landroid/os/HandlerThread;->start()V

    .line 1098
    new-instance v0, Lcom/android/mms/service/MmsService$RequestQueue;

    .end local v0    # "requestQueque":Lcom/android/mms/service/MmsService$RequestQueue;
    invoke-virtual {v1}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v2

    invoke-direct {v0, p0, v2}, Lcom/android/mms/service/MmsService$RequestQueue;-><init>(Lcom/android/mms/service/MmsService;Landroid/os/Looper;)V

    .line 1099
    .restart local v0    # "requestQueque":Lcom/android/mms/service/MmsService$RequestQueue;
    iget-object v2, p0, Lcom/android/mms/service/MmsService;->mRunningRequestQueuesMap:Landroid/util/SparseArray;

    invoke-virtual {v2, p1, v0}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 1101
    .end local v1    # "thread":Landroid/os/HandlerThread;
    :cond_1
    monitor-exit v3

    goto :goto_0

    .line 1102
    .end local v0    # "requestQueque":Lcom/android/mms/service/MmsService$RequestQueue;
    :catchall_0
    move-exception v2

    monitor-exit v3
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v2
.end method

.method private importMms(Landroid/net/Uri;Ljava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;
    .locals 19
    .param p1, "contentUri"    # Landroid/net/Uri;
    .param p2, "messageId"    # Ljava/lang/String;
    .param p3, "timestampSecs"    # J
    .param p5, "seen"    # Z
    .param p6, "read"    # Z
    .param p7, "creator"    # Ljava/lang/String;

    .prologue
    .line 635
    const/high16 v5, 0x800000

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    invoke-virtual {v0, v1, v5}, Lcom/android/mms/service/MmsService;->readPduFromContentUri(Landroid/net/Uri;I)[B

    move-result-object v14

    .line 636
    .local v14, "pduData":[B
    if-eqz v14, :cond_0

    array-length v5, v14

    const/4 v6, 0x1

    if-ge v5, v6, :cond_1

    .line 637
    :cond_0
    const-string v5, "MmsService"

    const-string v6, "importMessage: empty PDU"

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 638
    const/4 v7, 0x0

    .line 699
    :goto_0
    return-object v7

    .line 643
    :cond_1
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v12

    .line 645
    .local v12, "identity":J
    :try_start_0
    invoke-static {v14}, Lcom/android/mms/service/MmsService;->parsePduForAnyCarrier([B)Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v3

    .line 647
    .local v3, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    if-nez v3, :cond_2

    .line 648
    const-string v5, "MmsService"

    const-string v6, "importMessage: can\'t parse input PDU"

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 649
    const/4 v7, 0x0

    .line 697
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 651
    :cond_2
    const/4 v4, 0x0

    .line 652
    .local v4, "insertUri":Landroid/net/Uri;
    :try_start_1
    instance-of v5, v3, Lcom/meizu/android/mms/pdu/MzSendReq;

    if-eqz v5, :cond_4

    .line 653
    sget-object v4, Landroid/provider/Telephony$Mms$Sent;->CONTENT_URI:Landroid/net/Uri;

    .line 660
    :cond_3
    :goto_1
    if-nez v4, :cond_6

    .line 661
    const-string v5, "MmsService"

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "importMessage; invalid MMS type: "

    invoke-virtual {v6, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/Class;->getCanonicalName()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v6, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 662
    const/4 v7, 0x0

    .line 697
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 654
    :cond_4
    :try_start_2
    instance-of v5, v3, Lcom/meizu/android/mms/pdu/MzRetrieveConf;

    if-nez v5, :cond_5

    instance-of v5, v3, Lcom/meizu/android/mms/pdu/MzNotificationInd;

    if-nez v5, :cond_5

    instance-of v5, v3, Lcom/meizu/android/mms/pdu/MzDeliveryInd;

    if-nez v5, :cond_5

    instance-of v5, v3, Lcom/meizu/android/mms/pdu/MzReadOrigInd;

    if-eqz v5, :cond_3

    .line 658
    :cond_5
    sget-object v4, Landroid/provider/Telephony$Mms$Inbox;->CONTENT_URI:Landroid/net/Uri;

    goto :goto_1

    .line 664
    :cond_6
    invoke-static/range {p0 .. p0}, Lcom/meizu/android/mms/pdu/MzPduPersister;->getPduPersister(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;

    move-result-object v2

    .line 665
    .local v2, "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    const/4 v5, 0x1

    const/4 v6, 0x1

    const/4 v7, 0x0

    invoke-virtual/range {v2 .. v7}, Lcom/meizu/android/mms/pdu/MzPduPersister;->persist(Lcom/meizu/android/mms/pdu/MzGenericPdu;Landroid/net/Uri;ZZLjava/util/HashMap;)Landroid/net/Uri;

    move-result-object v7

    .line 671
    .local v7, "uri":Landroid/net/Uri;
    if-nez v7, :cond_7

    .line 672
    const-string v5, "MmsService"

    const-string v6, "importMessage: failed to persist message"

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_2
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 673
    const/4 v7, 0x0

    .line 697
    .end local v7    # "uri":Landroid/net/Uri;
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 675
    .restart local v7    # "uri":Landroid/net/Uri;
    :cond_7
    :try_start_3
    new-instance v8, Landroid/content/ContentValues;

    const/4 v5, 0x5

    invoke-direct {v8, v5}, Landroid/content/ContentValues;-><init>(I)V

    .line 676
    .local v8, "values":Landroid/content/ContentValues;
    invoke-static/range {p2 .. p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_8

    .line 677
    const-string v5, "m_id"

    move-object/from16 v0, p2

    invoke-virtual {v8, v5, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 679
    :cond_8
    const-wide/16 v16, -0x1

    cmp-long v5, p3, v16

    if-eqz v5, :cond_9

    .line 680
    const-string v5, "date"

    invoke-static/range {p3 .. p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-virtual {v8, v5, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 682
    :cond_9
    const-string v6, "read"

    if-eqz p5, :cond_c

    const/4 v5, 0x1

    :goto_2
    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v8, v6, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 683
    const-string v6, "seen"

    if-eqz p6, :cond_d

    const/4 v5, 0x1

    :goto_3
    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v8, v6, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 684
    invoke-static/range {p7 .. p7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_a

    .line 685
    const-string v5, "creator"

    move-object/from16 v0, p7

    invoke-virtual {v8, v5, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 687
    :cond_a
    invoke-virtual/range {p0 .. p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v6

    const/4 v9, 0x0

    const/4 v10, 0x0

    move-object/from16 v5, p0

    invoke-static/range {v5 .. v10}, Lcom/meizu/android/mms/util/MzSqliteWrapper;->update(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v5

    const/4 v6, 0x1

    if-eq v5, v6, :cond_b

    .line 689
    const-string v5, "MmsService"

    const-string v6, "importMessage: failed to update message"

    invoke-static {v5, v6}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_3
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 697
    :cond_b
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto/16 :goto_0

    .line 682
    :cond_c
    const/4 v5, 0x0

    goto :goto_2

    .line 683
    :cond_d
    const/4 v5, 0x0

    goto :goto_3

    .line 692
    .end local v2    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    .end local v3    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .end local v4    # "insertUri":Landroid/net/Uri;
    .end local v7    # "uri":Landroid/net/Uri;
    .end local v8    # "values":Landroid/content/ContentValues;
    :catch_0
    move-exception v11

    .line 693
    .local v11, "e":Ljava/lang/RuntimeException;
    :try_start_4
    const-string v5, "MmsService"

    const-string v6, "importMessage: failed to parse input PDU"

    invoke-static {v5, v6, v11}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 697
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 699
    .end local v11    # "e":Ljava/lang/RuntimeException;
    :goto_4
    const/4 v7, 0x0

    goto/16 :goto_0

    .line 694
    :catch_1
    move-exception v11

    .line 695
    .local v11, "e":Lcom/google/android/mms/MmsException;
    :try_start_5
    const-string v5, "MmsService"

    const-string v6, "importMessage: failed to persist message"

    invoke-static {v5, v6, v11}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 697
    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_4

    .end local v11    # "e":Lcom/google/android/mms/MmsException;
    :catchall_0
    move-exception v5

    invoke-static {v12, v13}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v5
.end method

.method private importSms(Ljava/lang/String;ILjava/lang/String;JZZLjava/lang/String;)Landroid/net/Uri;
    .locals 10
    .param p1, "address"    # Ljava/lang/String;
    .param p2, "type"    # I
    .param p3, "text"    # Ljava/lang/String;
    .param p4, "timestampMillis"    # J
    .param p6, "seen"    # Z
    .param p7, "read"    # Z
    .param p8, "creator"    # Ljava/lang/String;

    .prologue
    .line 596
    const/4 v3, 0x0

    .line 597
    .local v3, "insertUri":Landroid/net/Uri;
    packed-switch p2, :pswitch_data_0

    .line 606
    :goto_0
    if-nez v3, :cond_0

    .line 607
    const-string v7, "MmsService"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "importTextMessage: invalid message type for importing: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 608
    const/4 v7, 0x0

    .line 630
    :goto_1
    return-object v7

    .line 599
    :pswitch_0
    sget-object v3, Landroid/provider/Telephony$Sms$Inbox;->CONTENT_URI:Landroid/net/Uri;

    .line 601
    goto :goto_0

    .line 603
    :pswitch_1
    sget-object v3, Landroid/provider/Telephony$Sms$Sent;->CONTENT_URI:Landroid/net/Uri;

    goto :goto_0

    .line 610
    :cond_0
    new-instance v6, Landroid/content/ContentValues;

    const/4 v7, 0x6

    invoke-direct {v6, v7}, Landroid/content/ContentValues;-><init>(I)V

    .line 611
    .local v6, "values":Landroid/content/ContentValues;
    const-string v7, "address"

    invoke-virtual {v6, v7, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 612
    const-string v7, "date"

    invoke-static {p4, p5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v6, v7, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 613
    const-string v8, "seen"

    if-eqz p6, :cond_2

    const/4 v7, 0x1

    :goto_2
    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v6, v8, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 614
    const-string v8, "read"

    if-eqz p7, :cond_3

    const/4 v7, 0x1

    :goto_3
    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v6, v8, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 615
    const-string v7, "body"

    invoke-virtual {v6, v7, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 616
    invoke-static/range {p8 .. p8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_1

    .line 617
    const-string v7, "creator"

    move-object/from16 v0, p8

    invoke-virtual {v6, v7, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 622
    :cond_1
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v4

    .line 624
    .local v4, "identity":J
    :try_start_0
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v7

    invoke-virtual {v7, v3, v6}, Landroid/content/ContentResolver;->insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result-object v7

    .line 628
    invoke-static {v4, v5}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_1

    .line 613
    .end local v4    # "identity":J
    :cond_2
    const/4 v7, 0x0

    goto :goto_2

    .line 614
    :cond_3
    const/4 v7, 0x0

    goto :goto_3

    .line 625
    .restart local v4    # "identity":J
    :catch_0
    move-exception v2

    .line 626
    .local v2, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v7, "MmsService"

    const-string v8, "importTextMessage: failed to persist imported text message"

    invoke-static {v7, v8, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 628
    invoke-static {v4, v5}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 630
    const/4 v7, 0x0

    goto :goto_1

    .line 628
    .end local v2    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v7

    invoke-static {v4, v5}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v7

    .line 597
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method private isFailedOrDraft(Landroid/net/Uri;)Z
    .locals 13
    .param p1, "messageUri"    # Landroid/net/Uri;

    .prologue
    const/4 v12, 0x1

    const/4 v9, 0x0

    .line 1002
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v10

    .line 1003
    .local v10, "identity":J
    const/4 v7, 0x0

    .line 1005
    .local v7, "cursor":Landroid/database/Cursor;
    :try_start_0
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const/4 v1, 0x1

    new-array v2, v1, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v3, "msg_box"

    aput-object v3, v2, v1

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v7

    .line 1011
    if-eqz v7, :cond_3

    invoke-interface {v7}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 1012
    const/4 v0, 0x0

    invoke-interface {v7, v0}, Landroid/database/Cursor;->getInt(I)I
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-result v6

    .line 1013
    .local v6, "box":I
    const/4 v0, 0x3

    if-eq v6, v0, :cond_0

    const/4 v0, 0x5

    if-eq v6, v0, :cond_0

    const/4 v0, 0x4

    if-ne v6, v0, :cond_2

    :cond_0
    move v0, v12

    .line 1020
    :goto_0
    if-eqz v7, :cond_1

    .line 1021
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    .line 1023
    :cond_1
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 1025
    .end local v6    # "box":I
    :goto_1
    return v0

    .restart local v6    # "box":I
    :cond_2
    move v0, v9

    .line 1013
    goto :goto_0

    .line 1020
    .end local v6    # "box":I
    :cond_3
    if-eqz v7, :cond_4

    .line 1021
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    .line 1023
    :cond_4
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    :goto_2
    move v0, v9

    .line 1025
    goto :goto_1

    .line 1017
    :catch_0
    move-exception v8

    .line 1018
    .local v8, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v0, "MmsService"

    const-string v1, "isFailedOrDraft: query message type failed"

    invoke-static {v0, v1, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1020
    if-eqz v7, :cond_5

    .line 1021
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    .line 1023
    :cond_5
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_2

    .line 1020
    .end local v8    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v0

    if-eqz v7, :cond_6

    .line 1021
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    .line 1023
    :cond_6
    invoke-static {v10, v11}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v0
.end method

.method private static isSmsMmsContentUri(Landroid/net/Uri;)Z
    .locals 6
    .param p0, "uri"    # Landroid/net/Uri;

    .prologue
    const/4 v1, 0x0

    .line 703
    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    .line 704
    .local v0, "uriString":Ljava/lang/String;
    const-string v2, "content://sms/"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_1

    const-string v2, "content://mms/"

    invoke-virtual {v0, v2}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_1

    .line 710
    :cond_0
    :goto_0
    return v1

    .line 707
    :cond_1
    invoke-static {p0}, Landroid/content/ContentUris;->parseId(Landroid/net/Uri;)J

    move-result-wide v2

    const-wide/16 v4, -0x1

    cmp-long v2, v2, v4

    if-eqz v2, :cond_0

    .line 710
    const/4 v1, 0x1

    goto :goto_0
.end method

.method private loadPdu(Landroid/net/Uri;)[B
    .locals 10
    .param p1, "messageUri"    # Landroid/net/Uri;

    .prologue
    const/4 v6, 0x0

    .line 1032
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v2

    .line 1034
    .local v2, "identity":J
    :try_start_0
    invoke-static {p0}, Lcom/meizu/android/mms/pdu/MzPduPersister;->getPduPersister(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;

    move-result-object v5

    .line 1035
    .local v5, "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    invoke-virtual {v5, p1}, Lcom/meizu/android/mms/pdu/MzPduPersister;->load(Landroid/net/Uri;)Lcom/meizu/android/mms/pdu/MzGenericPdu;

    move-result-object v4

    .line 1036
    .local v4, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    if-nez v4, :cond_0

    .line 1037
    const-string v7, "MmsService"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "loadPdu: failed to load PDU from "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {p1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Lcom/google/android/mms/MmsException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1047
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 1049
    .end local v4    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .end local v5    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    :goto_0
    return-object v6

    .line 1040
    .restart local v4    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .restart local v5    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    :cond_0
    :try_start_1
    new-instance v0, Lcom/meizu/android/mms/pdu/MzPduComposer;

    invoke-direct {v0, p0, v4}, Lcom/meizu/android/mms/pdu/MzPduComposer;-><init>(Landroid/content/Context;Lcom/meizu/android/mms/pdu/MzGenericPdu;)V

    .line 1041
    .local v0, "composer":Lcom/meizu/android/mms/pdu/MzPduComposer;
    invoke-virtual {v0}, Lcom/meizu/android/mms/pdu/MzPduComposer;->make()[B
    :try_end_1
    .catch Lcom/google/android/mms/MmsException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    move-result-object v6

    .line 1047
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 1042
    .end local v0    # "composer":Lcom/meizu/android/mms/pdu/MzPduComposer;
    .end local v4    # "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .end local v5    # "persister":Lcom/meizu/android/mms/pdu/MzPduPersister;
    :catch_0
    move-exception v1

    .line 1043
    .local v1, "e":Lcom/google/android/mms/MmsException;
    :try_start_2
    const-string v7, "MmsService"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "loadPdu: failed to load PDU from "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {p1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 1047
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .line 1044
    .end local v1    # "e":Lcom/google/android/mms/MmsException;
    :catch_1
    move-exception v1

    .line 1045
    .local v1, "e":Ljava/lang/RuntimeException;
    :try_start_3
    const-string v7, "MmsService"

    const-string v8, "loadPdu: failed to serialize PDU"

    invoke-static {v7, v8, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 1047
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .end local v1    # "e":Ljava/lang/RuntimeException;
    :catchall_0
    move-exception v6

    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v6
.end method

.method private movePendingSimRequestsToRunningSynchronized()V
    .locals 6

    .prologue
    .line 518
    const-string v4, "MmsService"

    const-string v5, "Schedule requests pending on SIM"

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 538
    iget v4, p0, Lcom/android/mms/service/MmsService;->mCurrentSubId:I

    invoke-direct {p0, v4}, Lcom/android/mms/service/MmsService;->getPendingSimRequestQueue(I)Ljava/util/Queue;

    move-result-object v1

    .line 539
    .local v1, "pendingRequestQueue":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    if-eqz v1, :cond_2

    invoke-interface {v1}, Ljava/util/Queue;->size()I

    move-result v4

    if-lez v4, :cond_2

    .line 540
    :goto_0
    invoke-interface {v1}, Ljava/util/Queue;->size()I

    move-result v4

    if-lez v4, :cond_0

    .line 541
    invoke-interface {v1}, Ljava/util/Queue;->peek()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/android/mms/service/MmsRequest;

    .line 542
    .local v3, "request":Lcom/android/mms/service/MmsRequest;
    if-eqz v3, :cond_1

    .line 543
    invoke-interface {v1}, Ljava/util/Queue;->remove()Ljava/lang/Object;

    .line 544
    invoke-direct {p0, v3}, Lcom/android/mms/service/MmsService;->addToRunningRequestQueueSynchronized(Lcom/android/mms/service/MmsRequest;)V

    .line 570
    .end local v3    # "request":Lcom/android/mms/service/MmsRequest;
    :cond_0
    :goto_1
    return-void

    .line 547
    .restart local v3    # "request":Lcom/android/mms/service/MmsRequest;
    :cond_1
    const-string v4, "MmsService"

    const-string v5, "Schedule pending: found empty request"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 548
    invoke-interface {v1}, Ljava/util/Queue;->remove()Ljava/lang/Object;

    goto :goto_0

    .line 552
    .end local v3    # "request":Lcom/android/mms/service/MmsRequest;
    :cond_2
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_2
    iget-object v4, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    invoke-virtual {v4}, Landroid/util/SparseArray;->size()I

    move-result v4

    if-ge v0, v4, :cond_0

    .line 553
    iget-object v4, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    invoke-virtual {v4, v0}, Landroid/util/SparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Queue;

    .line 554
    .local v2, "pendingRequestQueueEx":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    if-eqz v2, :cond_4

    invoke-interface {v2}, Ljava/util/Queue;->size()I

    move-result v4

    if-lez v4, :cond_4

    .line 555
    :goto_3
    invoke-interface {v2}, Ljava/util/Queue;->size()I

    move-result v4

    if-lez v4, :cond_4

    .line 556
    invoke-interface {v2}, Ljava/util/Queue;->peek()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/android/mms/service/MmsRequest;

    .line 557
    .restart local v3    # "request":Lcom/android/mms/service/MmsRequest;
    if-eqz v3, :cond_3

    .line 558
    invoke-interface {v2}, Ljava/util/Queue;->remove()Ljava/lang/Object;

    .line 559
    invoke-direct {p0, v3}, Lcom/android/mms/service/MmsService;->addToRunningRequestQueueSynchronized(Lcom/android/mms/service/MmsRequest;)V

    goto :goto_1

    .line 562
    :cond_3
    const-string v4, "MmsService"

    const-string v5, "Schedule pending: found empty request"

    invoke-static {v4, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 563
    invoke-interface {v2}, Ljava/util/Queue;->remove()Ljava/lang/Object;

    goto :goto_3

    .line 552
    .end local v3    # "request":Lcom/android/mms/service/MmsRequest;
    :cond_4
    add-int/lit8 v0, v0, 0x1

    goto :goto_2
.end method

.method private static parsePduForAnyCarrier([B)Lcom/meizu/android/mms/pdu/MzGenericPdu;
    .locals 4
    .param p0, "data"    # [B

    .prologue
    .line 869
    const/4 v1, 0x0

    .line 873
    .local v1, "pdu":Lcom/meizu/android/mms/pdu/MzGenericPdu;
    :try_start_0
    new-instance v2, Lcom/meizu/android/mms/pdu/MzPduParser;

    invoke-direct {v2, p0}, Lcom/meizu/android/mms/pdu/MzPduParser;-><init>([B)V

    invoke-virtual {v2}, Lcom/meizu/android/mms/pdu/MzPduParser;->parse()Lcom/meizu/android/mms/pdu/MzGenericPdu;
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v1

    .line 895
    :goto_0
    return-object v1

    .line 884
    :catch_0
    move-exception v0

    .line 885
    .local v0, "e":Ljava/lang/RuntimeException;
    const-string v2, "MmsService"

    const-string v3, "parsePduForAnyCarrier: Failed to parse PDU with content disposition"

    invoke-static {v2, v3, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method private returnUnspecifiedFailure(Landroid/app/PendingIntent;)V
    .locals 1
    .param p1, "pi"    # Landroid/app/PendingIntent;

    .prologue
    .line 1053
    if-eqz p1, :cond_0

    .line 1055
    const/4 v0, 0x1

    :try_start_0
    invoke-virtual {p1, v0}, Landroid/app/PendingIntent;->send(I)V
    :try_end_0
    .catch Landroid/app/PendingIntent$CanceledException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1060
    :cond_0
    :goto_0
    return-void

    .line 1056
    :catch_0
    move-exception v0

    goto :goto_0
.end method

.method private updateMessageStatus(Landroid/net/Uri;Landroid/content/ContentValues;)Z
    .locals 10
    .param p1, "messageUri"    # Landroid/net/Uri;
    .param p2, "statusValues"    # Landroid/content/ContentValues;

    .prologue
    const/4 v6, 0x1

    const/4 v5, 0x0

    .line 714
    invoke-static {p1}, Lcom/android/mms/service/MmsService;->isSmsMmsContentUri(Landroid/net/Uri;)Z

    move-result v7

    if-nez v7, :cond_0

    .line 715
    const-string v6, "MmsService"

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "updateMessageStatus: invalid messageUri: "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {p1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 756
    :goto_0
    return v5

    .line 718
    :cond_0
    if-nez p2, :cond_1

    .line 719
    const-string v6, "MmsService"

    const-string v7, "updateMessageStatus: empty values to update"

    invoke-static {v6, v7}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 722
    :cond_1
    new-instance v4, Landroid/content/ContentValues;

    invoke-direct {v4}, Landroid/content/ContentValues;-><init>()V

    .line 723
    .local v4, "values":Landroid/content/ContentValues;
    const-string v7, "read"

    invoke-virtual {p2, v7}, Landroid/content/ContentValues;->containsKey(Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_3

    .line 724
    const-string v7, "read"

    invoke-virtual {p2, v7}, Landroid/content/ContentValues;->getAsInteger(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v1

    .line 725
    .local v1, "val":Ljava/lang/Integer;
    if-eqz v1, :cond_2

    .line 727
    const-string v7, "read"

    invoke-virtual {v4, v7, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 736
    .end local v1    # "val":Ljava/lang/Integer;
    :cond_2
    :goto_1
    invoke-virtual {v4}, Landroid/content/ContentValues;->size()I

    move-result v7

    if-ge v7, v6, :cond_4

    .line 737
    const-string v6, "MmsService"

    const-string v7, "updateMessageStatus: no value to update"

    invoke-static {v6, v7}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 729
    :cond_3
    const-string v7, "seen"

    invoke-virtual {p2, v7}, Landroid/content/ContentValues;->containsKey(Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_2

    .line 730
    const-string v7, "seen"

    invoke-virtual {p2, v7}, Landroid/content/ContentValues;->getAsInteger(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v1

    .line 731
    .restart local v1    # "val":Ljava/lang/Integer;
    if-eqz v1, :cond_2

    .line 733
    const-string v7, "seen"

    invoke-virtual {v4, v7, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    goto :goto_1

    .line 743
    .end local v1    # "val":Ljava/lang/Integer;
    :cond_4
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v2

    .line 745
    .local v2, "identity":J
    :try_start_0
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v7

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-virtual {v7, p1, v4, v8, v9}, Landroid/content/ContentResolver;->update(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v7

    if-eq v7, v6, :cond_5

    .line 747
    const-string v6, "MmsService"

    const-string v7, "updateMessageStatus: failed to update database"

    invoke-static {v6, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/database/sqlite/SQLiteException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 754
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    :cond_5
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    move v5, v6

    goto :goto_0

    .line 751
    :catch_0
    move-exception v0

    .line 752
    .local v0, "e":Landroid/database/sqlite/SQLiteException;
    :try_start_1
    const-string v6, "MmsService"

    const-string v7, "updateMessageStatus: failed to update database"

    invoke-static {v6, v7, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 754
    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    goto :goto_0

    .end local v0    # "e":Landroid/database/sqlite/SQLiteException;
    :catchall_0
    move-exception v5

    invoke-static {v2, v3}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    throw v5
.end method

.method private updatePendingMmsRequestCountForSubId(I)V
    .locals 4
    .param p1, "subId"    # I

    .prologue
    .line 1064
    const/4 v0, 0x0

    .line 1065
    .local v0, "count":I
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->getPendingSimRequestQueue(I)Ljava/util/Queue;

    move-result-object v2

    .line 1066
    .local v2, "pendingSimRequestQueue":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    if-nez v2, :cond_0

    .line 1071
    :goto_0
    return-void

    .line 1069
    :cond_0
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->getNetworkManager(I)Lcom/android/mms/service/MmsNetworkManager;

    move-result-object v1

    .line 1070
    .local v1, "manager":Lcom/android/mms/service/MmsNetworkManager;
    invoke-interface {v2}, Ljava/util/Queue;->size()I

    move-result v3

    invoke-virtual {v1, v3}, Lcom/android/mms/service/MmsNetworkManager;->updatePendingMmsRequestCount(I)V

    goto :goto_0
.end method


# virtual methods
.method public addSimRequest(Lcom/android/mms/service/MmsRequest;)V
    .locals 6
    .param p1, "request"    # Lcom/android/mms/service/MmsRequest;

    .prologue
    .line 440
    if-nez p1, :cond_0

    .line 441
    const-string v3, "MmsService"

    const-string v4, "Add running or pending: empty request"

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 492
    :goto_0
    return-void

    .line 466
    :cond_0
    monitor-enter p0

    .line 467
    :try_start_0
    invoke-virtual {p1}, Lcom/android/mms/service/MmsRequest;->getSubId()I

    move-result v3

    invoke-direct {p0, v3}, Lcom/android/mms/service/MmsService;->getPendingSimRequestQueue(I)Ljava/util/Queue;

    move-result-object v1

    .line 468
    .local v1, "pendingSimRequestQueue":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    if-nez v1, :cond_1

    .line 469
    monitor-exit p0

    goto :goto_0

    .line 490
    .end local v1    # "pendingSimRequestQueue":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    :catchall_0
    move-exception v3

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v3

    .line 471
    .restart local v1    # "pendingSimRequestQueue":Ljava/util/Queue;, "Ljava/util/Queue<Lcom/android/mms/service/MmsRequest;>;"
    :cond_1
    :try_start_1
    const-string v3, "MmsService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Current running="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget v5, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "current subId="

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget v5, p0, Lcom/android/mms/service/MmsService;->mCurrentSubId:I

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ", "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "pending request count"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-interface {v1}, Ljava/util/Queue;->size()I

    move-result v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 474
    invoke-interface {v1}, Ljava/util/Queue;->size()I

    move-result v3

    if-gtz v3, :cond_2

    iget v3, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    if-lez v3, :cond_4

    .line 475
    :cond_2
    invoke-interface {v1, p1}, Ljava/util/Queue;->add(Ljava/lang/Object;)Z

    .line 477
    invoke-virtual {p1}, Lcom/android/mms/service/MmsRequest;->getSubId()I

    move-result v3

    invoke-direct {p0, v3}, Lcom/android/mms/service/MmsService;->updatePendingMmsRequestCountForSubId(I)V

    .line 490
    :cond_3
    :goto_1
    monitor-exit p0

    goto :goto_0

    .line 479
    :cond_4
    invoke-interface {v1}, Ljava/util/Queue;->size()I

    move-result v3

    if-gtz v3, :cond_5

    iget v3, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    if-gtz v3, :cond_5

    invoke-virtual {p1}, Lcom/android/mms/service/MmsRequest;->getSubId()I

    move-result v3

    iget v4, p0, Lcom/android/mms/service/MmsService;->mCurrentSubId:I

    if-ne v3, v4, :cond_5

    .line 481
    invoke-direct {p0, p1}, Lcom/android/mms/service/MmsService;->addToRunningRequestQueueSynchronized(Lcom/android/mms/service/MmsRequest;)V

    goto :goto_1

    .line 483
    :cond_5
    invoke-interface {v1, p1}, Ljava/util/Queue;->add(Ljava/lang/Object;)Z

    .line 484
    invoke-direct {p0}, Lcom/android/mms/service/MmsService;->movePendingSimRequestsToRunningSynchronized()V

    .line 485
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_2
    iget-object v3, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    invoke-virtual {v3}, Landroid/util/SparseArray;->size()I

    move-result v3

    if-ge v0, v3, :cond_3

    .line 486
    iget-object v3, p0, Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;

    invoke-virtual {v3, v0}, Landroid/util/SparseArray;->keyAt(I)I

    move-result v2

    .line 487
    .local v2, "subId":I
    invoke-direct {p0, v2}, Lcom/android/mms/service/MmsService;->updatePendingMmsRequestCountForSubId(I)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 485
    add-int/lit8 v0, v0, 0x1

    goto :goto_2
.end method

.method public getAutoPersistingPref()Z
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 900
    const-string v1, "mmspref"

    invoke-virtual {p0, v1, v2}, Lcom/android/mms/service/MmsService;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 902
    .local v0, "preferences":Landroid/content/SharedPreferences;
    const-string v1, "autopersisting"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    return v1
.end method

.method public onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 1
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 574
    iget-object v0, p0, Lcom/android/mms/service/MmsService;->mStub:Lcom/android/internal/telephony/IMms$Stub;

    return-object v0
.end method

.method public onCreate()V
    .locals 2

    .prologue
    .line 583
    invoke-super {p0}, Landroid/app/Service;->onCreate()V

    .line 584
    const-string v0, "MmsService"

    const-string v1, "onCreate"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 586
    invoke-static {}, Lcom/android/mms/service/MmsConfigManager;->getInstance()Lcom/android/mms/service/MmsConfigManager;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/android/mms/service/MmsConfigManager;->init(Landroid/content/Context;)V

    .line 588
    monitor-enter p0

    .line 589
    const/4 v0, -0x1

    :try_start_0
    iput v0, p0, Lcom/android/mms/service/MmsService;->mCurrentSubId:I

    .line 590
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/mms/service/MmsService;->mRunningRequestCount:I

    .line 591
    monitor-exit p0

    .line 592
    return-void

    .line 591
    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public readPduFromContentUri(Landroid/net/Uri;I)[B
    .locals 7
    .param p1, "contentUri"    # Landroid/net/Uri;
    .param p2, "maxSize"    # I

    .prologue
    .line 912
    const-string v4, "MmsService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "readPduFromContentUri(), uri = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, ", maxSize = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 913
    new-instance v0, Lcom/android/mms/service/MmsService$2;

    invoke-direct {v0, p0, p1, p2}, Lcom/android/mms/service/MmsService$2;-><init>(Lcom/android/mms/service/MmsService;Landroid/net/Uri;I)V

    .line 947
    .local v0, "copyPduToArray":Ljava/util/concurrent/Callable;, "Ljava/util/concurrent/Callable<[B>;"
    iget-object v4, p0, Lcom/android/mms/service/MmsService;->mExecutor:Ljava/util/concurrent/ExecutorService;

    invoke-interface {v4, v0}, Ljava/util/concurrent/ExecutorService;->submit(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;

    move-result-object v3

    .line 949
    .local v3, "pendingResult":Ljava/util/concurrent/Future;, "Ljava/util/concurrent/Future<[B>;"
    const-wide/16 v4, 0x7530

    :try_start_0
    sget-object v6, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-interface {v3, v4, v5, v6}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [B
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 955
    :goto_0
    return-object v2

    .line 951
    :catch_0
    move-exception v1

    .line 953
    .local v1, "e":Ljava/lang/Exception;
    const/4 v4, 0x1

    invoke-interface {v3, v4}, Ljava/util/concurrent/Future;->cancel(Z)Z

    .line 955
    const/4 v2, 0x0

    goto :goto_0
.end method

.method public writePduToContentUri(Landroid/net/Uri;[B)Z
    .locals 9
    .param p1, "contentUri"    # Landroid/net/Uri;
    .param p2, "pdu"    # [B

    .prologue
    const/4 v4, 0x1

    const/4 v5, 0x0

    .line 965
    new-instance v0, Lcom/android/mms/service/MmsService$3;

    invoke-direct {v0, p0, p1, p2}, Lcom/android/mms/service/MmsService$3;-><init>(Lcom/android/mms/service/MmsService;Landroid/net/Uri;[B)V

    .line 987
    .local v0, "copyDownloadedPduToOutput":Ljava/util/concurrent/Callable;, "Ljava/util/concurrent/Callable<Ljava/lang/Boolean;>;"
    iget-object v6, p0, Lcom/android/mms/service/MmsService;->mExecutor:Ljava/util/concurrent/ExecutorService;

    invoke-interface {v6, v0}, Ljava/util/concurrent/ExecutorService;->submit(Ljava/util/concurrent/Callable;)Ljava/util/concurrent/Future;

    move-result-object v2

    .line 989
    .local v2, "pendingResult":Ljava/util/concurrent/Future;, "Ljava/util/concurrent/Future<Ljava/lang/Boolean;>;"
    const-wide/16 v6, 0x7530

    :try_start_0
    sget-object v8, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-interface {v2, v6, v7, v8}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Boolean;

    .line 990
    .local v3, "succeeded":Ljava/lang/Boolean;
    sget-object v6, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    if-ne v3, v6, :cond_0

    .line 995
    .end local v3    # "succeeded":Ljava/lang/Boolean;
    :goto_0
    return v4

    .restart local v3    # "succeeded":Ljava/lang/Boolean;
    :cond_0
    move v4, v5

    .line 990
    goto :goto_0

    .line 991
    .end local v3    # "succeeded":Ljava/lang/Boolean;
    :catch_0
    move-exception v1

    .line 993
    .local v1, "e":Ljava/lang/Exception;
    invoke-interface {v2, v4}, Ljava/util/concurrent/Future;->cancel(Z)Z

    move v4, v5

    .line 995
    goto :goto_0
.end method
