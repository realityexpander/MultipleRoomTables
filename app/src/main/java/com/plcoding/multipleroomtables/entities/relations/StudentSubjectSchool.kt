package com.plcoding.multipleroomtables.entities.relations

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.plcoding.multipleroomtables.entities.School
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject

//data class StudentSubjectSchool(
//    val studentName: String?,
//    val subjectName: String?,
//    val schoolName: String?
//)


data class StudentSubjectSchool(
    @Embedded val student: Student,
    @Embedded val subject: Subject
)