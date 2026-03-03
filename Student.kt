import java.util.Scanner
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.io.File

// ✅ 1. Data Class
data class Student(
    val name: String,
    val course: String,
    val score: Double?
)

// ✅ 2. Higher Order Function
fun processStudent(
    student: Student,
    gradeCalculator: (Double) -> String
): String {

    val score = student.score

    return if (score == null || score !in 0.0..100.0) {
        "Invalid"
    } else {
        gradeCalculator(score)
    }
}

// ✅ 3. Fonction Export Excel
fun exportToExcel(results: List<Triple<String, String, String>>) {

    val workbook = XSSFWorkbook()
    val sheet = workbook.createSheet("Results")

    // Header
    val header = sheet.createRow(0)
    header.createCell(0).setCellValue("STUDENT")
    header.createCell(1).setCellValue("COURSE")
    header.createCell(2).setCellValue("GRADE")

    // Data
    results.forEachIndexed { index, (name, course, grade) ->
        val row = sheet.createRow(index + 1)
        row.createCell(0).setCellValue(name)
        row.createCell(1).setCellValue(course)
        row.createCell(2).setCellValue(grade)
    }

    val file = File("students_results.xlsx")
    FileOutputStream(file).use { workbook.write(it) }

    workbook.close()

    println("\n✅ Excel file generated at: ${file.absolutePath}")
}

// ✅ 4. Main Function
fun main() {

    val reader = Scanner(System.`in`)
    val students = mutableListOf<Student>()

    print("How many students? ")
    val number = reader.nextLine().toIntOrNull() ?: 0

    // ✅ Lambda Grade Calculator
    val calculateGrade: (Double) -> String = { s ->
        when {
            s >= 80 -> "A"
            s >= 70 -> "B"
            s >= 60 -> "C+"
            s >= 50 -> "C"
            s >= 40 -> "D"
            else -> "F"
        }
    }

    // ✅ Input Loop
    repeat(number) { i ->

        println("\n--- Student ${i + 1} ---")

        print("Name: ")
        val name = reader.nextLine().ifBlank { "Unknown" }

        print("Course: ")
        val course = reader.nextLine().ifBlank { "N/A" }

        print("Score: ")
        val scoreInput = reader.nextLine().replace(',', '.')
        val score = scoreInput.toDoubleOrNull()

        students.add(Student(name, course, score))
    }

    // ✅ Process Results
    val resultList = students.map { st ->
        val grade = processStudent(st, calculateGrade)
        Triple(st.name, st.course, grade)
    }

    // ✅ Console Table
    println("\n========== RESULT TABLE ==========")
    println(String.format("%-15s %-15s %-10s", "STUDENT", "COURSE", "GRADE"))
    println("---------------------------------------------")

    resultList.forEach { (name, course, grade) ->
        println(String.format("%-15s %-15s %-10s", name, course, grade))
    }

    // ✅ Export Excel
    exportToExcel(resultList)
}