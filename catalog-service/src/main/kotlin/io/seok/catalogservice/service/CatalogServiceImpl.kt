package io.seok.catalogservice.service

import io.seok.catalogservice.repository.CatalogRepository
import io.seok.catalogservice.vo.ResponseCatalog
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    private val catalogRepository: CatalogRepository
) : CatalogService {

    val logger: Logger = LoggerFactory.getLogger(CatalogServiceImpl::class.java)


    override fun getAllCatalogs(): List<ResponseCatalog> {
        return catalogRepository.findAll()
            .map { catalogEntity -> ResponseCatalog.createResponseCatalog(catalogEntity) }
    }
}