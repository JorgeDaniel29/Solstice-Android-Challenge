package jorge.gonzalez.solstice.networkdatasource.module

import jorge.gonzalez.solstice.networkdatasource.BuildConfig
import jorge.gonzalez.solstice.networkdatasource.data.api.NetworkApi
import jorge.gonzalez.solstice.networkdatasource.data.cache.CacheProvider
import jorge.gonzalez.solstice.networkdatasource.data.cache.SourceCacheProvider
import jorge.gonzalez.solstice.networkdatasource.model.ServerAddress
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val network = module {
    factory { ServerAddress(BuildConfig.SERVER_BASE_URL) }
    single<CacheProvider> { SourceCacheProvider(androidApplication())}
    factory { NetworkApi(get(), get()) }
}