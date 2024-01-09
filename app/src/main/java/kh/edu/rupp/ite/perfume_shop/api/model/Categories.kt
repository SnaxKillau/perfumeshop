package kh.edu.rupp.ite.perfume_shop.api.model

data class Categories(
    val id:Number,
    val name:String,
)
data class CategoryResponse(
    val data: List<Categories>
)