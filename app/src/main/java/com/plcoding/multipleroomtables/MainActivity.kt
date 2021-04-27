package com.plcoding.multipleroomtables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.plcoding.multipleroomtables.entities.Director
import com.plcoding.multipleroomtables.entities.School
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.StudentSubjectCrossRef
import com.plcoding.multipleroomtables.entities.relations.StudentSubjectGradeCrossRef
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = SchoolDatabase.getInstance(this).schoolDao

        val directors = listOf(
            Director("Mike Litoris", "Jake Wharton School"),
            Director("Jack Goff", "Kotlin School"),
            Director("Chris P. Chicken", "JetBrains School")
        )
        val schools = listOf(
            School("Jake Wharton School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
        val subjects = listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Beff Jezos", 2, "Kotlin School"),
            Student("Mark Suckerberg", 5, "Jake Wharton School"),
            Student("Gill Bates", 8, "Kotlin School"),
            Student("Donny Jepp", 1, "Kotlin School"),
            Student("Hom Tanks", 2, "JetBrains School")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
            StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
            StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
            StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
            StudentSubjectCrossRef("Gill Bates", "How to use Google"),
            StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
            StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
            StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
        )

        val studentSubjectGradeRelations = listOf(
            StudentSubjectGradeCrossRef("Beff Jezos", "Dating for programmers", "A"),
            StudentSubjectGradeCrossRef("Beff Jezos", "Avoiding depression", "B"),
            StudentSubjectGradeCrossRef("Beff Jezos", "Bug Fix Meditation", "C"),
            StudentSubjectGradeCrossRef("Beff Jezos", "Logcat for Newbies", "A"),
            StudentSubjectGradeCrossRef("Mark Suckerberg", "Dating for programmers", "B"),
            StudentSubjectGradeCrossRef("Gill Bates", "How to use Google", "C"),
            StudentSubjectGradeCrossRef("Donny Jepp", "Logcat for Newbies", "A"),
            StudentSubjectGradeCrossRef("Hom Tanks", "Avoiding depression", "B"),
            StudentSubjectGradeCrossRef("Hom Tanks", "Dating for programmers", "C")
        )

        lifecycleScope.launch {
            directors.forEach { dao.insertDirector(it) }
            schools.forEach { dao.insertSchool(it) }
            subjects.forEach { dao.insertSubject(it) }
            students.forEach { dao.insertStudent(it) }
            studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }
            studentSubjectGradeRelations.forEach { dao.insertStudentSubjectGradeCrossRef(it) }

            val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")
            val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
            val studentsOfSemester = dao.getStudentsOfSemester(2)
            val studentsOfSubject = dao.getStudentsOfSubject("Dating for programmers")
            val subjectsOfStudent = dao.getSubjectsOfStudent("Beff Jezos")
            val studentSubjectSchoolOfSubject2 = dao.getStudentSubjectSchoolOfSubject("Avoiding depression")
            val studentSubjectGrade = dao.getGradeOfStudentSubject("Beff Jezos", "Avoiding depression")
            val studentGrades = dao.getGradesOfStudent("Hom Tanks")
            val gradesOfSchool = dao.getGradesOfSchool("Kotlin School")


            println(schoolWithDirector)
            println(schoolWithStudents)
            println(studentsOfSemester)
            println(studentsOfSubject)
//            println(studentSubjectSchoolOfSubject)
            println(studentSubjectSchoolOfSubject2)

            println(studentSubjectGrade)
            println(studentGrades)
            println(subjectsOfStudent)
            println(gradesOfSchool)

        }
    }
}