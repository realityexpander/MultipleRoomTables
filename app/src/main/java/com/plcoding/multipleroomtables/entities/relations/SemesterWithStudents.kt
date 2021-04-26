package com.plcoding.multipleroomtables.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject

data class SemesterWithStudents(
    val semester: Int,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "studentName",
    )
    val students: List<Student>
)