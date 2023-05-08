package io.seok.catalogservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import io.seok.catalogservice.entity.CatalogEntity
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL) //null 필드는 반환하지 않음
data class ResponseCatalog(
    val productId: String,
    val productName: String,
    val unitPrice: Int,
    val stock: Int,

    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    companion object {

        fun createResponseCatalog(catalogEntity: CatalogEntity): ResponseCatalog {
            return ResponseCatalog(
                productId = catalogEntity.productId,
                productName = catalogEntity.productName,
                unitPrice = catalogEntity.unitPrice,
                stock = catalogEntity.stock
            )
        }
    }
}
