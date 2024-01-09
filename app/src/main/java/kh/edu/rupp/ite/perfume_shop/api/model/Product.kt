package kh.edu.rupp.ite.perfume_shop.api.model

data class Product(
    val id:Number,
    val name:String,
    val image:List<Image>,
    val brand:String,
    val availableUnit:Number,
    val decription:String,
    val price:Number,
    val type : String,
    val count: Number
)
data class ProductResponse(
    val data: List<Product>
)
data class ProductDetailResponse(
    val data: Product
)
