package com.plcoding.multipleroomtables

import androidx.room.*
import com.plcoding.multipleroomtables.entities.Director
import com.plcoding.multipleroomtables.entities.School
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.Subject
import com.plcoding.multipleroomtables.entities.relations.*

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectGradeCrossRef(crossRef: StudentSubjectGradeCrossRef)

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirector>

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolWithStudents(schoolName: String): List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String): List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM studentsubjectgradecrossref WHERE studentName = :studentName AND subjectName = :subjectName")
    suspend fun getGradeOfStudentSubject(studentName: String, subjectName: String): List<StudentSubjectGradeCrossRef>

    @Transaction
    @Query("SELECT * FROM studentsubjectgradecrossref WHERE studentName = :studentName")
    suspend fun getGradesOfStudent(studentName: String): List<StudentSubjectGradeCrossRef>

    @Transaction
    @Query("SELECT * FROM student WHERE studentName = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String): List<StudentWithSubjects>

    @Transaction
    @Query("SELECT * FROM student WHERE semester = :semester")
    suspend fun getStudentsOfSemester(semester: Int): List<Student>

    @Transaction
    @Query("SELECT student.studentName, student.semester,  student.schoolName, subjectName FROM StudentSubjectCrossRef \n" +
            "INNER JOIN Student ON student.studentName = StudentSubjectCrossRef.studentName\n" +
            "WHERE subjectName=:subjectName")
    suspend fun getStudentSubjectSchoolOfSubject(subjectName: String): List<StudentSubjectSchool>

    @Transaction
    @Query("SELECT grade, schoolName FROM studentSubjectGradeCrossRef\n" +
            "INNER JOIN student ON studentSubjectGradeCrossRef.studentName = student.studentName\n" +
            "WHERE student.schoolName = :schoolName")
    suspend fun getGradesOfSchool(schoolName: String): List<GradeSchool>


}