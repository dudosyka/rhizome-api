package com.rhizome.dto

import kotlinx.serialization.Serializable

@Serializable
data class SuccessOutputDto (
    val success: String = "success"
)