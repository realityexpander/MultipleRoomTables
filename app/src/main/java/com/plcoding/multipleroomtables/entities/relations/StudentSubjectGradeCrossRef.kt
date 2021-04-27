package com.plcoding.multipleroomtables.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["studentName", "subjectName"])
data class StudentSubjectGradeCrossRef(
    val studentName: String,
    val subjectName: String,
    val grade: String
)