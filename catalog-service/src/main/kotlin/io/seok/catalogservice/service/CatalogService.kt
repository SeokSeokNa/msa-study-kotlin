package io.seok.catalogservice.service

import io.seok.catalogservice.entity.CatalogEntity
import io.seok.catalogservice.vo.ResponseCatalog

interface CatalogService {
    fun getAllCatalogs(): List<ResponseCatalog>
}