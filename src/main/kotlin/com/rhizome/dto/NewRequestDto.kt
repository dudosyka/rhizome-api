package com.rhizome.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewRequestDto (
    val name: String,
    val phone: String,
    val email: String,
    val type: String,
    val context: String
)