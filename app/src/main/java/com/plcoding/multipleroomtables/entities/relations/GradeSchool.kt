package com.plcoding.multipleroomtables.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.plcoding.multipleroomtables.entities.School

data class GradeSchool(
    val grade: String,
    @Embedded val school: School
)
