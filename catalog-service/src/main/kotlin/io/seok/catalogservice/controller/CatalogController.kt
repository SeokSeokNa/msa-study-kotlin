package io.seok.catalogservice.controller

import io.seok.catalogservice.service.CatalogService
import io.seok.catalogservice.vo.ResponseCatalog
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    private val env: Environment,
    private val catalogService: CatalogService
) {

    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in Catalog Service on PORT ${env.getProperty("local.server.port")}" //"local.server.port"는 랜덤포트로 할당된 값을 가져올수있게 하는것
    }

    @GetMapping("/catalogs")
    fun getUsers(): ResponseEntity<List<ResponseCatalog>> {
        val catalogs = catalogService.getAllCatalogs()
        return ResponseEntity.status(HttpStatus.OK).body(catalogs)
    }
}