package kh.edu.rupp.ite.perfume_shop.api.model

data class Profile (
    val name : String,
    val email: String
)
data class ProfileResponse(
    val data: Profile
)
