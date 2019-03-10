package jorge.gonzalez.solstice.networkdatasource.data.api

import io.reactivex.Maybe
import io.reactivex.Single
import jorge.gonzalez.solstice.networkdatasource.data.cache.CacheProvider
import jorge.gonzalez.solstice.networkdatasource.model.ServerAddress
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkApi(private val serverAddress: ServerAddress,
                 private val cacheProvider: CacheProvider) {

    private fun getRetrofitBuilder() = Retrofit.Builder()
            .baseUrl(serverAddress.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

    private fun <DATA : Any, T> makeApiCallForResponse(
            apiClass: Class<T>,
            apiCall: ((api: T) -> Single<DATA>)
    ): Single<DATA> =
            apiCall(getRetrofitBuilder().create(apiClass))

    fun <DATA : Any, T, CACHE_DATA :Any> makeCacheableApiCallForResponse(
            apiClass: Class<T>,
            cacheDataClass: Class<CACHE_DATA>,
            index: String,
            transformToCache :((data :DATA) -> CACHE_DATA),
            transformFromCache :((cacheData :CACHE_DATA?) -> DATA?),
            apiCall: ((api: T) -> Single<DATA>)
    ): Single<DATA> {

        return Maybe.just(true).flatMap {
            return@flatMap cacheProvider.provide(cacheDataClass, index)?.let { Maybe.just(transformFromCache(it)) } ?: Maybe.empty<DATA>()

        }.switchIfEmpty(makeApiCallForResponse(apiClass, apiCall).toMaybe())
                .toSingle()
                .map {
                    cacheProvider.store(transformToCache(it), index)
                    it
                }
    }
}