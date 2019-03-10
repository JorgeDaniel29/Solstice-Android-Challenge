package jorge.gonzalez.solstice.networkdatasource.data.cache

interface CacheProvider {
    fun <T : Any> provide(classProvide: Class<T>, index: String): T?
    fun <T : Any> store(it: T, index: String)
    fun <T : Any> clear(classProvide: Class<T>, index: String)
    fun invalidate()
}