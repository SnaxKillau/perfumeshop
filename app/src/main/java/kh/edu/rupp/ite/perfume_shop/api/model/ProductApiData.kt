package kh.edu.rupp.ite.perfume_shop.api.model

data class ProductApiData<T>(
    val status: Status,
    val data: ProductResponse?
)

