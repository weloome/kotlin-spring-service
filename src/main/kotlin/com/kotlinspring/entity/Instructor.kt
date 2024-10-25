package com.kotlinspring.entity

import jakarta.persistence.*

@Entity
@Table(name = "INSTRUCTOR")
data class Instructor (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String
)