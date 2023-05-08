package io.seok.catalogservice.repository

import io.seok.catalogservice.entity.CatalogEntity
import org.springframework.data.repository.CrudRepository

interface CatalogRepository : CrudRepository<CatalogEntity , Long> {

    fun findByProductId(productId: String): CatalogEntity
}