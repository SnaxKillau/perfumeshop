package kh.edu.rupp.ite.perfume_shop.api.model

data class ProductCategory (
    val name : String,
    val products : List<Product>
)
data class ProductCategoryResponse(
    val data: List<ProductCategory>
)


