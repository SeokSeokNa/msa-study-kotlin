package io.seok.catalogservice.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime

@Entity
@Table(name = "catalog")
class CatalogEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 120, unique = true)
    val productId: String,
    @Column(nullable = false)
    val productName: String,
    @Column(nullable = false)
    var stock: Int,
    @Column(nullable = false)
    val unitPrice: Int,

    @Column(nullable = false, updatable = false, insertable = true , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private var createdAt: LocalDateTime



) {
}