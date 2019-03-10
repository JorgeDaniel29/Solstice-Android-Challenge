package jorge.gonzalez.solstice.base.data

interface DataMapper<ENTITY, DOMAIN_MODEL> {
    fun mapFromEntity(entity: ENTITY): DOMAIN_MODEL
    fun mapToEntity(domainModel: DOMAIN_MODEL): ENTITY
}