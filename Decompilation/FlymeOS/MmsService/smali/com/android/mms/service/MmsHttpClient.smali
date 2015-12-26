.class public Lcom/android/mms/service/MmsHttpClient;
.super Ljava/lang/Object;
.source "MmsHttpClient.java"


# static fields
.field private static final MACRO_P:Ljava/util/regex/Pattern;


# instance fields
.field private final mConnectionPool:Lcom/android/okhttp/ConnectionPool;

.field private final mContext:Landroid/content/Context;

.field private final mHostResolver:Lcom/android/okhttp/HostResolver;

.field private final mSocketFactory:Ljavax/net/SocketFactory;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 337
    const-string v0, "##(\\S+)##"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lcom/android/mms/service/MmsHttpClient;->MACRO_P:Ljava/util/regex/Pattern;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Ljavax/net/SocketFactory;Lcom/android/okhttp/HostResolver;Lcom/android/okhttp/ConnectionPool;)V
    .locals 0
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "socketFactory"    # Ljavax/net/SocketFactory;
    .param p3, "hostResolver"    # Lcom/android/okhttp/HostResolver;
    .param p4, "connectionPool"    # Lcom/android/okhttp/ConnectionPool;

    .prologue
    .line 87
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 88
    iput-object p1, p0, Lcom/android/mms/service/MmsHttpClient;->mContext:Landroid/content/Context;

    .line 89
    iput-object p2, p0, Lcom/android/mms/service/MmsHttpClient;->mSocketFactory:Ljavax/net/SocketFactory;

    .line 90
    iput-object p3, p0, Lcom/android/mms/service/MmsHttpClient;->mHostResolver:Lcom/android/okhttp/HostResolver;

    .line 91
    iput-object p4, p0, Lcom/android/mms/service/MmsHttpClient;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    .line 92
    return-void
.end method

.method private addExtraHeaders(Ljava/net/HttpURLConnection;Lcom/android/mms/service/MmsConfig$Overridden;)V
    .locals 12
    .param p1, "connection"    # Ljava/net/HttpURLConnection;
    .param p2, "mmsConfig"    # Lcom/android/mms/service/MmsConfig$Overridden;

    .prologue
    const/4 v11, 0x2

    .line 385
    invoke-virtual {p2}, Lcom/android/mms/service/MmsConfig$Overridden;->getHttpParams()Ljava/lang/String;

    move-result-object v1

    .line 386
    .local v1, "extraHttpParams":Ljava/lang/String;
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v9

    if-nez v9, :cond_1

    .line 388
    const-string v9, "\\|"

    invoke-virtual {v1, v9}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v5

    .line 389
    .local v5, "paramList":[Ljava/lang/String;
    move-object v0, v5

    .local v0, "arr$":[Ljava/lang/String;
    array-length v3, v0

    .local v3, "len$":I
    const/4 v2, 0x0

    .local v2, "i$":I
    :goto_0
    if-ge v2, v3, :cond_1

    aget-object v6, v0, v2

    .line 390
    .local v6, "paramPair":Ljava/lang/String;
    const-string v9, ":"

    invoke-virtual {v6, v9, v11}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v7

    .line 391
    .local v7, "splitPair":[Ljava/lang/String;
    array-length v9, v7

    if-ne v9, v11, :cond_0

    .line 392
    const/4 v9, 0x0

    aget-object v9, v7, v9

    invoke-virtual {v9}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v4

    .line 393
    .local v4, "name":Ljava/lang/String;
    iget-object v9, p0, Lcom/android/mms/service/MmsHttpClient;->mContext:Landroid/content/Context;

    const/4 v10, 0x1

    aget-object v10, v7, v10

    invoke-virtual {v10}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10, p2}, Lcom/android/mms/service/MmsHttpClient;->resolveMacro(Landroid/content/Context;Ljava/lang/String;Lcom/android/mms/service/MmsConfig$Overridden;)Ljava/lang/String;

    move-result-object v8

    .line 394
    .local v8, "value":Ljava/lang/String;
    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v9

    if-nez v9, :cond_0

    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v9

    if-nez v9, :cond_0

    .line 396
    invoke-virtual {p1, v4, v8}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 389
    .end local v4    # "name":Ljava/lang/String;
    .end local v8    # "value":Ljava/lang/String;
    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 401
    .end local v0    # "arr$":[Ljava/lang/String;
    .end local v2    # "i$":I
    .end local v3    # "len$":I
    .end local v5    # "paramList":[Ljava/lang/String;
    .end local v6    # "paramPair":Ljava/lang/String;
    .end local v7    # "splitPair":[Ljava/lang/String;
    :cond_1
    return-void
.end method

.method private static addLocaleToHttpAcceptLanguage(Ljava/lang/StringBuilder;Ljava/util/Locale;)V
    .locals 3
    .param p0, "builder"    # Ljava/lang/StringBuilder;
    .param p1, "locale"    # Ljava/util/Locale;

    .prologue
    .line 326
    invoke-virtual {p1}, Ljava/util/Locale;->getLanguage()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/android/mms/service/MmsHttpClient;->convertObsoleteLanguageCodeToNew(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 327
    .local v1, "language":Ljava/lang/String;
    if-eqz v1, :cond_0

    .line 328
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 329
    invoke-virtual {p1}, Ljava/util/Locale;->getCountry()Ljava/lang/String;

    move-result-object v0

    .line 330
    .local v0, "country":Ljava/lang/String;
    if-eqz v0, :cond_0

    .line 331
    const-string v2, "-"

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 332
    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 335
    .end local v0    # "country":Ljava/lang/String;
    :cond_0
    return-void
.end method

.method private static checkMethod(Ljava/lang/String;)V
    .locals 4
    .param p0, "method"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/android/mms/service/exception/MmsHttpException;
        }
    .end annotation

    .prologue
    .line 276
    const-string v0, "GET"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "POST"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 277
    new-instance v0, Lcom/android/mms/service/exception/MmsHttpException;

    const/4 v1, 0x0

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Invalid method "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;)V

    throw v0

    .line 279
    :cond_0
    return-void
.end method

.method private static convertObsoleteLanguageCodeToNew(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p0, "langCode"    # Ljava/lang/String;

    .prologue
    .line 309
    if-nez p0, :cond_1

    .line 310
    const/4 p0, 0x0

    .line 322
    .end local p0    # "langCode":Ljava/lang/String;
    :cond_0
    :goto_0
    return-object p0

    .line 312
    .restart local p0    # "langCode":Ljava/lang/String;
    :cond_1
    const-string v0, "iw"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 314
    const-string p0, "he"

    goto :goto_0

    .line 315
    :cond_2
    const-string v0, "in"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 317
    const-string p0, "id"

    goto :goto_0

    .line 318
    :cond_3
    const-string v0, "ji"

    invoke-virtual {v0, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 320
    const-string p0, "yi"

    goto :goto_0
.end method

.method public static getCurrentAcceptLanguage(Ljava/util/Locale;)Ljava/lang/String;
    .locals 2
    .param p0, "locale"    # Ljava/util/Locale;

    .prologue
    .line 291
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 292
    .local v0, "buffer":Ljava/lang/StringBuilder;
    invoke-static {v0, p0}, Lcom/android/mms/service/MmsHttpClient;->addLocaleToHttpAcceptLanguage(Ljava/lang/StringBuilder;Ljava/util/Locale;)V

    .line 294
    sget-object v1, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v1, p0}, Ljava/util/Locale;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 295
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->length()I

    move-result v1

    if-lez v1, :cond_0

    .line 296
    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 298
    :cond_0
    const-string v1, "en-US"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 301
    :cond_1
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method private static logHttpHeaders(Ljava/util/Map;)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/util/List",
            "<",
            "Ljava/lang/String;",
            ">;>;)V"
        }
    .end annotation

    .prologue
    .line 260
    .local p0, "headers":Ljava/util/Map;, "Ljava/util/Map<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;"
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    .line 261
    .local v4, "sb":Ljava/lang/StringBuilder;
    if-eqz p0, :cond_2

    .line 262
    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v7

    invoke-interface {v7}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 263
    .local v0, "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;"
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 264
    .local v3, "key":Ljava/lang/String;
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/List;

    .line 265
    .local v6, "values":Ljava/util/List;, "Ljava/util/List<Ljava/lang/String;>;"
    if-eqz v6, :cond_0

    .line 266
    invoke-interface {v6}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    .local v2, "i$":Ljava/util/Iterator;
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    .line 267
    .local v5, "value":Ljava/lang/String;
    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const/16 v8, 0x3d

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const/16 v8, 0xa

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 271
    .end local v0    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;"
    .end local v2    # "i$":Ljava/util/Iterator;
    .end local v3    # "key":Ljava/lang/String;
    .end local v5    # "value":Ljava/lang/String;
    .end local v6    # "values":Ljava/util/List;, "Ljava/util/List<Ljava/lang/String;>;"
    :cond_1
    const-string v7, "MmsService"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "HTTP: headers\n"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 273
    :cond_2
    return-void
.end method

.method private openConnection(Ljava/net/URL;Ljava/net/Proxy;)Ljava/net/HttpURLConnection;
    .locals 5
    .param p1, "url"    # Ljava/net/URL;
    .param p2, "proxy"    # Ljava/net/Proxy;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/net/MalformedURLException;
        }
    .end annotation

    .prologue
    .line 244
    invoke-virtual {p1}, Ljava/net/URL;->getProtocol()Ljava/lang/String;

    move-result-object v1

    .line 246
    .local v1, "protocol":Ljava/lang/String;
    const-string v2, "http"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 247
    invoke-static {p2}, Lcom/android/okhttp/HttpHandler;->createHttpOkHttpClient(Ljava/net/Proxy;)Lcom/android/okhttp/OkHttpClient;

    move-result-object v0

    .line 253
    .local v0, "okHttpClient":Lcom/android/okhttp/OkHttpClient;
    :goto_0
    iget-object v2, p0, Lcom/android/mms/service/MmsHttpClient;->mSocketFactory:Ljavax/net/SocketFactory;

    invoke-virtual {v0, v2}, Lcom/android/okhttp/OkHttpClient;->setSocketFactory(Ljavax/net/SocketFactory;)Lcom/android/okhttp/OkHttpClient;

    move-result-object v2

    iget-object v3, p0, Lcom/android/mms/service/MmsHttpClient;->mHostResolver:Lcom/android/okhttp/HostResolver;

    invoke-virtual {v2, v3}, Lcom/android/okhttp/OkHttpClient;->setHostResolver(Lcom/android/okhttp/HostResolver;)Lcom/android/okhttp/OkHttpClient;

    move-result-object v2

    iget-object v3, p0, Lcom/android/mms/service/MmsHttpClient;->mConnectionPool:Lcom/android/okhttp/ConnectionPool;

    invoke-virtual {v2, v3}, Lcom/android/okhttp/OkHttpClient;->setConnectionPool(Lcom/android/okhttp/ConnectionPool;)Lcom/android/okhttp/OkHttpClient;

    move-result-object v2

    invoke-virtual {v2, p1}, Lcom/android/okhttp/OkHttpClient;->open(Ljava/net/URL;)Ljava/net/HttpURLConnection;

    move-result-object v2

    return-object v2

    .line 248
    .end local v0    # "okHttpClient":Lcom/android/okhttp/OkHttpClient;
    :cond_0
    const-string v2, "https"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 249
    invoke-static {p2}, Lcom/android/okhttp/HttpsHandler;->createHttpsOkHttpClient(Ljava/net/Proxy;)Lcom/android/okhttp/OkHttpClient;

    move-result-object v0

    .restart local v0    # "okHttpClient":Lcom/android/okhttp/OkHttpClient;
    goto :goto_0

    .line 251
    .end local v0    # "okHttpClient":Lcom/android/okhttp/OkHttpClient;
    :cond_1
    new-instance v2, Ljava/net/MalformedURLException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Invalid URL or unrecognized protocol "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/net/MalformedURLException;-><init>(Ljava/lang/String;)V

    throw v2
.end method

.method private static resolveMacro(Landroid/content/Context;Ljava/lang/String;Lcom/android/mms/service/MmsConfig$Overridden;)Ljava/lang/String;
    .locals 9
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "value"    # Ljava/lang/String;
    .param p2, "mmsConfig"    # Lcom/android/mms/service/MmsConfig$Overridden;

    .prologue
    .line 347
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_1

    .line 373
    .end local p1    # "value":Ljava/lang/String;
    :cond_0
    :goto_0
    return-object p1

    .line 350
    .restart local p1    # "value":Ljava/lang/String;
    :cond_1
    sget-object v6, Lcom/android/mms/service/MmsHttpClient;->MACRO_P:Ljava/util/regex/Pattern;

    invoke-virtual {v6, p1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v3

    .line 351
    .local v3, "matcher":Ljava/util/regex/Matcher;
    const/4 v4, 0x0

    .line 352
    .local v4, "nextStart":I
    const/4 v5, 0x0

    .line 353
    .local v5, "replaced":Ljava/lang/StringBuilder;
    :goto_1
    invoke-virtual {v3}, Ljava/util/regex/Matcher;->find()Z

    move-result v6

    if-eqz v6, :cond_5

    .line 354
    if-nez v5, :cond_2

    .line 355
    new-instance v5, Ljava/lang/StringBuilder;

    .end local v5    # "replaced":Ljava/lang/StringBuilder;
    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    .line 357
    .restart local v5    # "replaced":Ljava/lang/StringBuilder;
    :cond_2
    invoke-virtual {v3}, Ljava/util/regex/Matcher;->start()I

    move-result v2

    .line 358
    .local v2, "matchedStart":I
    if-le v2, v4, :cond_3

    .line 359
    invoke-virtual {p1, v4, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 361
    :cond_3
    const/4 v6, 0x1

    invoke-virtual {v3, v6}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v0

    .line 362
    .local v0, "macro":Ljava/lang/String;
    invoke-virtual {p2, p0, v0}, Lcom/android/mms/service/MmsConfig$Overridden;->getHttpParamMacro(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 363
    .local v1, "macroValue":Ljava/lang/String;
    if-eqz v1, :cond_4

    .line 364
    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 368
    :goto_2
    invoke-virtual {v3}, Ljava/util/regex/Matcher;->end()I

    move-result v4

    .line 369
    goto :goto_1

    .line 366
    :cond_4
    const-string v6, "MmsService"

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "HTTP: invalid macro "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v7}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_2

    .line 370
    .end local v0    # "macro":Ljava/lang/String;
    .end local v1    # "macroValue":Ljava/lang/String;
    .end local v2    # "matchedStart":I
    :cond_5
    if-eqz v5, :cond_6

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v6

    if-ge v4, v6, :cond_6

    .line 371
    invoke-virtual {p1, v4}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 373
    :cond_6
    if-eqz v5, :cond_0

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_0
.end method


# virtual methods
.method public execute(Ljava/lang/String;[BLjava/lang/String;ZLjava/lang/String;ILcom/android/mms/service/MmsConfig$Overridden;)[B
    .locals 25
    .param p1, "urlString"    # Ljava/lang/String;
    .param p2, "pdu"    # [B
    .param p3, "method"    # Ljava/lang/String;
    .param p4, "isProxySet"    # Z
    .param p5, "proxyHost"    # Ljava/lang/String;
    .param p6, "proxyPort"    # I
    .param p7, "mmsConfig"    # Lcom/android/mms/service/MmsConfig$Overridden;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/android/mms/service/exception/MmsHttpException;
        }
    .end annotation

    .prologue
    .line 111
    const-string v22, "MmsService"

    new-instance v21, Ljava/lang/StringBuilder;

    invoke-direct/range {v21 .. v21}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: "

    move-object/from16 v0, v21

    move-object/from16 v1, v23

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    move-object/from16 v0, v21

    move-object/from16 v1, p3

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    const-string v23, " "

    move-object/from16 v0, v21

    move-object/from16 v1, v23

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    move-object/from16 v0, v21

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    if-eqz p4, :cond_4

    new-instance v21, Ljava/lang/StringBuilder;

    invoke-direct/range {v21 .. v21}, Ljava/lang/StringBuilder;-><init>()V

    const-string v24, ", proxy="

    move-object/from16 v0, v21

    move-object/from16 v1, v24

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    move-object/from16 v0, v21

    move-object/from16 v1, p5

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    const-string v24, ":"

    move-object/from16 v0, v21

    move-object/from16 v1, v24

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    move-object/from16 v0, v21

    move/from16 v1, p6

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v21

    invoke-virtual/range {v21 .. v21}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v21

    :goto_0
    move-object/from16 v0, v23

    move-object/from16 v1, v21

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v21

    const-string v23, ", PDU size="

    move-object/from16 v0, v21

    move-object/from16 v1, v23

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    if-eqz p2, :cond_5

    move-object/from16 v0, p2

    array-length v0, v0

    move/from16 v21, v0

    :goto_1
    move-object/from16 v0, v23

    move/from16 v1, v21

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v21

    invoke-virtual/range {v21 .. v21}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v21

    move-object/from16 v0, v22

    move-object/from16 v1, v21

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 114
    invoke-static/range {p3 .. p3}, Lcom/android/mms/service/MmsHttpClient;->checkMethod(Ljava/lang/String;)V

    .line 115
    const/4 v5, 0x0

    .line 117
    .local v5, "connection":Ljava/net/HttpURLConnection;
    const/4 v12, 0x0

    .line 118
    .local v12, "proxy":Ljava/net/Proxy;
    if-eqz p4, :cond_0

    .line 119
    :try_start_0
    new-instance v13, Ljava/net/Proxy;

    sget-object v21, Ljava/net/Proxy$Type;->HTTP:Ljava/net/Proxy$Type;

    new-instance v22, Ljava/net/InetSocketAddress;

    move-object/from16 v0, v22

    move-object/from16 v1, p5

    move/from16 v2, p6

    invoke-direct {v0, v1, v2}, Ljava/net/InetSocketAddress;-><init>(Ljava/lang/String;I)V

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-direct {v13, v0, v1}, Ljava/net/Proxy;-><init>(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V

    .end local v12    # "proxy":Ljava/net/Proxy;
    .local v13, "proxy":Ljava/net/Proxy;
    move-object v12, v13

    .line 121
    .end local v13    # "proxy":Ljava/net/Proxy;
    .restart local v12    # "proxy":Ljava/net/Proxy;
    :cond_0
    new-instance v19, Ljava/net/URL;

    move-object/from16 v0, v19

    move-object/from16 v1, p1

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 123
    .local v19, "url":Ljava/net/URL;
    move-object/from16 v0, p0

    move-object/from16 v1, v19

    invoke-direct {v0, v1, v12}, Lcom/android/mms/service/MmsHttpClient;->openConnection(Ljava/net/URL;Ljava/net/Proxy;)Ljava/net/HttpURLConnection;

    move-result-object v5

    .line 124
    const/16 v21, 0x1

    move/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setDoInput(Z)V

    .line 125
    invoke-virtual/range {p7 .. p7}, Lcom/android/mms/service/MmsConfig$Overridden;->getHttpSocketTimeout()I

    move-result v21

    move/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 126
    const v21, 0x15f90

    move/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V
    :try_end_0
    .catch Ljava/net/MalformedURLException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/net/ProtocolException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_4
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 128
    const/4 v10, 0x0

    .line 129
    .local v10, "objectMethod":Ljava/lang/reflect/Method;
    :try_start_1
    const-string v21, "java.net.URLConnection"

    invoke-static/range {v21 .. v21}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v9

    .line 130
    .local v9, "objectClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    const-string v21, "setWriteTimeout"

    const/16 v22, 0x1

    move/from16 v0, v22

    new-array v0, v0, [Ljava/lang/Class;

    move-object/from16 v22, v0

    const/16 v23, 0x0

    sget-object v24, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    aput-object v24, v22, v23

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-virtual {v9, v0, v1}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v10

    .line 131
    const/16 v21, 0x1

    move/from16 v0, v21

    new-array v0, v0, [Ljava/lang/Object;

    move-object/from16 v21, v0

    const/16 v22, 0x0

    const/16 v23, 0x7530

    invoke-static/range {v23 .. v23}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v23

    aput-object v23, v21, v22

    move-object/from16 v0, v21

    invoke-virtual {v10, v5, v0}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_1
    .catch Ljava/lang/NoSuchMethodException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_1 .. :try_end_1} :catch_3
    .catch Ljava/lang/IllegalAccessException; {:try_start_1 .. :try_end_1} :catch_5
    .catch Ljava/lang/ClassNotFoundException; {:try_start_1 .. :try_end_1} :catch_6
    .catch Ljava/net/MalformedURLException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Ljava/net/ProtocolException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_4
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 144
    .end local v9    # "objectClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    :goto_2
    :try_start_2
    const-string v21, "Accept"

    const-string v22, "*/*, application/vnd.wap.mms-message, application/vnd.wap.sic"

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-virtual {v5, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 146
    const-string v21, "Accept-Language"

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v22

    invoke-static/range {v22 .. v22}, Lcom/android/mms/service/MmsHttpClient;->getCurrentAcceptLanguage(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v22

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-virtual {v5, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 149
    invoke-virtual/range {p7 .. p7}, Lcom/android/mms/service/MmsConfig$Overridden;->getUserAgent()Ljava/lang/String;

    move-result-object v20

    .line 150
    .local v20, "userAgent":Ljava/lang/String;
    const-string v21, "MmsService"

    new-instance v22, Ljava/lang/StringBuilder;

    invoke-direct/range {v22 .. v22}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: User-Agent="

    invoke-virtual/range {v22 .. v23}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    move-object/from16 v0, v22

    move-object/from16 v1, v20

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    invoke-virtual/range {v22 .. v22}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v22

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 151
    const-string v21, "User-Agent"

    move-object/from16 v0, v21

    move-object/from16 v1, v20

    invoke-virtual {v5, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 153
    invoke-virtual/range {p7 .. p7}, Lcom/android/mms/service/MmsConfig$Overridden;->getUaProfTagName()Ljava/lang/String;

    move-result-object v18

    .line 154
    .local v18, "uaProfUrlTagName":Ljava/lang/String;
    invoke-virtual/range {p7 .. p7}, Lcom/android/mms/service/MmsConfig$Overridden;->getUaProfUrl()Ljava/lang/String;

    move-result-object v17

    .line 155
    .local v17, "uaProfUrl":Ljava/lang/String;
    if-eqz v17, :cond_1

    .line 156
    const-string v21, "MmsService"

    new-instance v22, Ljava/lang/StringBuilder;

    invoke-direct/range {v22 .. v22}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: UaProfUrl="

    invoke-virtual/range {v22 .. v23}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    move-object/from16 v0, v22

    move-object/from16 v1, v17

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    invoke-virtual/range {v22 .. v22}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v22

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 157
    move-object/from16 v0, v18

    move-object/from16 v1, v17

    invoke-virtual {v5, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 160
    :cond_1
    move-object/from16 v0, p0

    move-object/from16 v1, p7

    invoke-direct {v0, v5, v1}, Lcom/android/mms/service/MmsHttpClient;->addExtraHeaders(Ljava/net/HttpURLConnection;Lcom/android/mms/service/MmsConfig$Overridden;)V

    .line 162
    const-string v21, "POST"

    move-object/from16 v0, v21

    move-object/from16 v1, p3

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v21

    if-eqz v21, :cond_b

    .line 163
    if-eqz p2, :cond_2

    move-object/from16 v0, p2

    array-length v0, v0

    move/from16 v21, v0

    const/16 v22, 0x1

    move/from16 v0, v21

    move/from16 v1, v22

    if-ge v0, v1, :cond_6

    .line 164
    :cond_2
    const-string v21, "MmsService"

    const-string v22, "HTTP: empty pdu"

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 165
    new-instance v21, Lcom/android/mms/service/exception/MmsHttpException;

    const/16 v22, 0x0

    const-string v23, "Sending empty PDU"

    invoke-direct/range {v21 .. v23}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;)V

    throw v21
    :try_end_2
    .catch Ljava/net/MalformedURLException; {:try_start_2 .. :try_end_2} :catch_0
    .catch Ljava/net/ProtocolException; {:try_start_2 .. :try_end_2} :catch_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_4
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 214
    .end local v10    # "objectMethod":Ljava/lang/reflect/Method;
    .end local v17    # "uaProfUrl":Ljava/lang/String;
    .end local v18    # "uaProfUrlTagName":Ljava/lang/String;
    .end local v19    # "url":Ljava/net/URL;
    .end local v20    # "userAgent":Ljava/lang/String;
    :catch_0
    move-exception v7

    .line 215
    .local v7, "e":Ljava/net/MalformedURLException;
    :try_start_3
    const-string v21, "MmsService"

    new-instance v22, Ljava/lang/StringBuilder;

    invoke-direct/range {v22 .. v22}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: invalid URL "

    invoke-virtual/range {v22 .. v23}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    move-object/from16 v0, v22

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    invoke-virtual/range {v22 .. v22}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v22

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-static {v0, v1, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 216
    new-instance v21, Lcom/android/mms/service/exception/MmsHttpException;

    const/16 v22, 0x0

    new-instance v23, Ljava/lang/StringBuilder;

    invoke-direct/range {v23 .. v23}, Ljava/lang/StringBuilder;-><init>()V

    const-string v24, "Invalid URL "

    invoke-virtual/range {v23 .. v24}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    move-object/from16 v0, v23

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    invoke-virtual/range {v23 .. v23}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v23

    move-object/from16 v0, v21

    move/from16 v1, v22

    move-object/from16 v2, v23

    invoke-direct {v0, v1, v2, v7}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;Ljava/lang/Throwable;)V

    throw v21
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 224
    .end local v7    # "e":Ljava/net/MalformedURLException;
    :catchall_0
    move-exception v21

    if-eqz v5, :cond_3

    .line 225
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->disconnect()V

    :cond_3
    throw v21

    .line 111
    .end local v5    # "connection":Ljava/net/HttpURLConnection;
    .end local v12    # "proxy":Ljava/net/Proxy;
    :cond_4
    const-string v21, ""

    goto/16 :goto_0

    :cond_5
    const/16 v21, 0x0

    goto/16 :goto_1

    .line 132
    .restart local v5    # "connection":Ljava/net/HttpURLConnection;
    .restart local v10    # "objectMethod":Ljava/lang/reflect/Method;
    .restart local v12    # "proxy":Ljava/net/Proxy;
    .restart local v19    # "url":Ljava/net/URL;
    :catch_1
    move-exception v7

    .line 133
    .local v7, "e":Ljava/lang/NoSuchMethodException;
    :try_start_4
    invoke-virtual {v7}, Ljava/lang/NoSuchMethodException;->printStackTrace()V
    :try_end_4
    .catch Ljava/net/MalformedURLException; {:try_start_4 .. :try_end_4} :catch_0
    .catch Ljava/net/ProtocolException; {:try_start_4 .. :try_end_4} :catch_2
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    goto/16 :goto_2

    .line 217
    .end local v7    # "e":Ljava/lang/NoSuchMethodException;
    .end local v10    # "objectMethod":Ljava/lang/reflect/Method;
    .end local v19    # "url":Ljava/net/URL;
    :catch_2
    move-exception v7

    .line 218
    .local v7, "e":Ljava/net/ProtocolException;
    :try_start_5
    const-string v21, "MmsService"

    new-instance v22, Ljava/lang/StringBuilder;

    invoke-direct/range {v22 .. v22}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: invalid URL protocol "

    invoke-virtual/range {v22 .. v23}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    move-object/from16 v0, v22

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    invoke-virtual/range {v22 .. v22}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v22

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-static {v0, v1, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 219
    new-instance v21, Lcom/android/mms/service/exception/MmsHttpException;

    const/16 v22, 0x0

    new-instance v23, Ljava/lang/StringBuilder;

    invoke-direct/range {v23 .. v23}, Ljava/lang/StringBuilder;-><init>()V

    const-string v24, "Invalid URL protocol "

    invoke-virtual/range {v23 .. v24}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    move-object/from16 v0, v23

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    invoke-virtual/range {v23 .. v23}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v23

    move-object/from16 v0, v21

    move/from16 v1, v22

    move-object/from16 v2, v23

    invoke-direct {v0, v1, v2, v7}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;Ljava/lang/Throwable;)V

    throw v21
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 134
    .end local v7    # "e":Ljava/net/ProtocolException;
    .restart local v10    # "objectMethod":Ljava/lang/reflect/Method;
    .restart local v19    # "url":Ljava/net/URL;
    :catch_3
    move-exception v7

    .line 135
    .local v7, "e":Ljava/lang/reflect/InvocationTargetException;
    :try_start_6
    invoke-virtual {v7}, Ljava/lang/reflect/InvocationTargetException;->printStackTrace()V
    :try_end_6
    .catch Ljava/net/MalformedURLException; {:try_start_6 .. :try_end_6} :catch_0
    .catch Ljava/net/ProtocolException; {:try_start_6 .. :try_end_6} :catch_2
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_4
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    goto/16 :goto_2

    .line 220
    .end local v7    # "e":Ljava/lang/reflect/InvocationTargetException;
    .end local v10    # "objectMethod":Ljava/lang/reflect/Method;
    .end local v19    # "url":Ljava/net/URL;
    :catch_4
    move-exception v7

    .line 221
    .local v7, "e":Ljava/io/IOException;
    :try_start_7
    const-string v21, "MmsService"

    const-string v22, "HTTP: IO failure"

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-static {v0, v1, v7}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 222
    new-instance v21, Lcom/android/mms/service/exception/MmsHttpException;

    const/16 v22, 0x0

    move-object/from16 v0, v21

    move/from16 v1, v22

    invoke-direct {v0, v1, v7}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/Throwable;)V

    throw v21
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    .line 136
    .end local v7    # "e":Ljava/io/IOException;
    .restart local v10    # "objectMethod":Ljava/lang/reflect/Method;
    .restart local v19    # "url":Ljava/net/URL;
    :catch_5
    move-exception v7

    .line 137
    .local v7, "e":Ljava/lang/IllegalAccessException;
    :try_start_8
    invoke-virtual {v7}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto/16 :goto_2

    .line 138
    .end local v7    # "e":Ljava/lang/IllegalAccessException;
    :catch_6
    move-exception v7

    .line 139
    .local v7, "e":Ljava/lang/ClassNotFoundException;
    invoke-virtual {v7}, Ljava/lang/ClassNotFoundException;->printStackTrace()V

    goto/16 :goto_2

    .line 167
    .end local v7    # "e":Ljava/lang/ClassNotFoundException;
    .restart local v17    # "uaProfUrl":Ljava/lang/String;
    .restart local v18    # "uaProfUrlTagName":Ljava/lang/String;
    .restart local v20    # "userAgent":Ljava/lang/String;
    :cond_6
    const/16 v21, 0x1

    move/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 168
    const-string v21, "POST"

    move-object/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 169
    invoke-virtual/range {p7 .. p7}, Lcom/android/mms/service/MmsConfig$Overridden;->getSupportHttpCharsetHeader()Z

    move-result v21

    if-eqz v21, :cond_a

    .line 170
    const-string v21, "Content-Type"

    const-string v22, "application/vnd.wap.mms-message; charset=utf-8"

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-virtual {v5, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 176
    :goto_3
    const-string v21, "MmsService"

    const/16 v22, 0x2

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v21

    if-eqz v21, :cond_7

    .line 177
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getRequestProperties()Ljava/util/Map;

    move-result-object v21

    invoke-static/range {v21 .. v21}, Lcom/android/mms/service/MmsHttpClient;->logHttpHeaders(Ljava/util/Map;)V

    .line 179
    :cond_7
    move-object/from16 v0, p2

    array-length v0, v0

    move/from16 v21, v0

    move/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setFixedLengthStreamingMode(I)V

    .line 181
    new-instance v11, Ljava/io/BufferedOutputStream;

    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v21

    move-object/from16 v0, v21

    invoke-direct {v11, v0}, Ljava/io/BufferedOutputStream;-><init>(Ljava/io/OutputStream;)V

    .line 183
    .local v11, "out":Ljava/io/OutputStream;
    move-object/from16 v0, p2

    invoke-virtual {v11, v0}, Ljava/io/OutputStream;->write([B)V

    .line 184
    invoke-virtual {v11}, Ljava/io/OutputStream;->flush()V

    .line 185
    invoke-virtual {v11}, Ljava/io/OutputStream;->close()V

    .line 193
    .end local v11    # "out":Ljava/io/OutputStream;
    :cond_8
    :goto_4
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v15

    .line 194
    .local v15, "responseCode":I
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getResponseMessage()Ljava/lang/String;

    move-result-object v16

    .line 195
    .local v16, "responseMessage":Ljava/lang/String;
    const-string v21, "MmsService"

    new-instance v22, Ljava/lang/StringBuilder;

    invoke-direct/range {v22 .. v22}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: "

    invoke-virtual/range {v22 .. v23}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    move-object/from16 v0, v22

    invoke-virtual {v0, v15}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v22

    const-string v23, " "

    invoke-virtual/range {v22 .. v23}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    move-object/from16 v0, v22

    move-object/from16 v1, v16

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v22

    invoke-virtual/range {v22 .. v22}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v22

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 196
    const-string v21, "MmsService"

    const/16 v22, 0x2

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v21

    if-eqz v21, :cond_9

    .line 197
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getHeaderFields()Ljava/util/Map;

    move-result-object v21

    invoke-static/range {v21 .. v21}, Lcom/android/mms/service/MmsHttpClient;->logHttpHeaders(Ljava/util/Map;)V

    .line 199
    :cond_9
    div-int/lit8 v21, v15, 0x64

    const/16 v22, 0x2

    move/from16 v0, v21

    move/from16 v1, v22

    if-eq v0, v1, :cond_d

    .line 200
    new-instance v21, Lcom/android/mms/service/exception/MmsHttpException;

    move-object/from16 v0, v21

    move-object/from16 v1, v16

    invoke-direct {v0, v15, v1}, Lcom/android/mms/service/exception/MmsHttpException;-><init>(ILjava/lang/String;)V

    throw v21

    .line 173
    .end local v15    # "responseCode":I
    .end local v16    # "responseMessage":Ljava/lang/String;
    :cond_a
    const-string v21, "Content-Type"

    const-string v22, "application/vnd.wap.mms-message"

    move-object/from16 v0, v21

    move-object/from16 v1, v22

    invoke-virtual {v5, v0, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    goto/16 :goto_3

    .line 186
    :cond_b
    const-string v21, "GET"

    move-object/from16 v0, v21

    move-object/from16 v1, p3

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v21

    if-eqz v21, :cond_8

    .line 187
    const-string v21, "MmsService"

    const/16 v22, 0x2

    invoke-static/range {v21 .. v22}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v21

    if-eqz v21, :cond_c

    .line 188
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getRequestProperties()Ljava/util/Map;

    move-result-object v21

    invoke-static/range {v21 .. v21}, Lcom/android/mms/service/MmsHttpClient;->logHttpHeaders(Ljava/util/Map;)V

    .line 190
    :cond_c
    const-string v21, "GET"

    move-object/from16 v0, v21

    invoke-virtual {v5, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    goto/16 :goto_4

    .line 202
    .restart local v15    # "responseCode":I
    .restart local v16    # "responseMessage":Ljava/lang/String;
    :cond_d
    new-instance v8, Ljava/io/BufferedInputStream;

    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v21

    move-object/from16 v0, v21

    invoke-direct {v8, v0}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    .line 203
    .local v8, "in":Ljava/io/InputStream;
    new-instance v4, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v4}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 204
    .local v4, "byteOut":Ljava/io/ByteArrayOutputStream;
    const/16 v21, 0x1000

    move/from16 v0, v21

    new-array v3, v0, [B

    .line 205
    .local v3, "buf":[B
    const/4 v6, 0x0

    .line 206
    .local v6, "count":I
    :goto_5
    invoke-virtual {v8, v3}, Ljava/io/InputStream;->read([B)I

    move-result v6

    if-lez v6, :cond_e

    .line 207
    const/16 v21, 0x0

    move/from16 v0, v21

    invoke-virtual {v4, v3, v0, v6}, Ljava/io/ByteArrayOutputStream;->write([BII)V

    goto :goto_5

    .line 209
    :cond_e
    invoke-virtual {v8}, Ljava/io/InputStream;->close()V

    .line 210
    invoke-virtual {v4}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v14

    .line 211
    .local v14, "responseBody":[B
    const-string v22, "MmsService"

    new-instance v21, Ljava/lang/StringBuilder;

    invoke-direct/range {v21 .. v21}, Ljava/lang/StringBuilder;-><init>()V

    const-string v23, "HTTP: response size="

    move-object/from16 v0, v21

    move-object/from16 v1, v23

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v23

    if-eqz v14, :cond_10

    array-length v0, v14

    move/from16 v21, v0

    :goto_6
    move-object/from16 v0, v23

    move/from16 v1, v21

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v21

    invoke-virtual/range {v21 .. v21}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v21

    move-object/from16 v0, v22

    move-object/from16 v1, v21

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_8
    .catch Ljava/net/MalformedURLException; {:try_start_8 .. :try_end_8} :catch_0
    .catch Ljava/net/ProtocolException; {:try_start_8 .. :try_end_8} :catch_2
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_4
    .catchall {:try_start_8 .. :try_end_8} :catchall_0

    .line 224
    if-eqz v5, :cond_f

    .line 225
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->disconnect()V

    :cond_f
    return-object v14

    .line 211
    :cond_10
    const/16 v21, 0x0

    goto :goto_6
.end method
