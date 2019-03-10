package jorge.gonzalez.solstice.networkdatasource.data.cache

import android.content.Context
import wiki.depasquale.mcache.Cache

class SourceCacheProvider(context: Context): CacheProvider {

    init {
        Cache.with(context)
        invalidate()
    }

    override fun <T : Any> provide(classProvide: Class<T>, index: String): T? =
            Cache.obtain(classProvide)
                    .ofIndex(index)
                    .build()
                    .getNow()

    override fun <T : Any> store(it: T, index: String) {
        Cache.give(it)
                .ofIndex(index)
                .build()
                .getNow()
    }

    override fun <T : Any> clear(classProvide: Class<T>, index: String) {
        Cache.obtain(classProvide)
                .ofIndex(index)
                .build()
                .delete()
    }

    override fun invalidate() {
        Cache.obtain(Cache::class.java).build().delete()
    }
}