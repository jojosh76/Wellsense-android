// Data class
data class Student(
    val name: String,
    val score: Double?
)

// Fonction pour calculer la lettre de grade
fun calculateGrade(score: Double?): String {

    // Elvis operator : si score est null → 0.0
    val validScore = score ?: return "No grade (score is null)"

    return when {
        validScore >= 90 -> "A"
        validScore >= 80 -> "B"
        validScore >= 70 -> "C"
        validScore >= 60 -> "D"
        validScore >= 0  -> "F"
        else -> "Invalid score"
    }
}

fun main() {

    // Liste avec cas normaux et cas null
    val students = listOf(
        Student("Alice", 95.0),
        Student("Bob", 82.5),
        Student("Charlie", 67.0),
        Student("David", null)  // cas null
    )

    for (student in students) {

        // Safe call ?. pour éviter NullPointerException
        val grade = calculateGrade(student.score)

        println("${student.name} -> Score: ${student.score ?: "Not provided"} -> Grade: $grade")
    }
}