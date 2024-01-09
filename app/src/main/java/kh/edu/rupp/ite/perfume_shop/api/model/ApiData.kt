package kh.edu.rupp.ite.perfume_shop.api.model

data class ApiData<T>(
    val status: Status,
    val data: CategoryResponse?
    )

enum class Status {
        PROCESSING, SUCCESS, ERROR }

