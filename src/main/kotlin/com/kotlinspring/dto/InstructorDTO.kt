package com.kotlinspring.dto

import com.kotlinspring.entity.Course
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.NotBlank


data class InstructorDTO (
    val id: Int?,
    @get:NotBlank(message = "InstructorDTO.name must not be blank")
    var name: String,
    @OneToMany(
        mappedBy = "instructor",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var course: List<Course> = mutableListOf()
)