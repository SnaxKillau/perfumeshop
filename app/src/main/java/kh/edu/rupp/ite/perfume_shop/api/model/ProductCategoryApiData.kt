package kh.edu.rupp.ite.perfume_shop.api.model

data class ProductCategoryApiData<T>(
    val status: Status,
    val data: ProductCategoryResponse?
)

