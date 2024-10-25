package com.kotlinspring.entity

import jakarta.persistence.*

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Int?,
    var name : String,
    var category : String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTOR_ID", nullable = false)
    val instructor: Instructor? = null
) {
    // 스택 오버플로우 방지
    override fun toString(): String {
        return "Course(id=$id, name=$name, category=$category, instructor=${instructor!!.id}"
    }
}