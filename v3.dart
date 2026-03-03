import 'dart:io';
class Student {
  final String name;
  final double? score;
  Student(this.name, this.score);
}
String calculateGrade(double score) {
  if (score >= 80 && score <= 100) return "A";
  if (score >= 70) return "B";
  if (score >= 60) return "C +";
  if (score >= 50) return "C";
  if (score >= 40) return "D";
if (score >= 0) return "F";
  return "Invalid Score";
}
void main() {
  stdout.write("Enter student name: ");
  String? name = stdin.readLineSync()?.trim();
  name = name?.isNotEmpty == true ? name! : "Unknown";
  stdout.write("Enter student score (0 - 100): ");
  String? input = stdin.readLineSync()?.trim();
  double? score = double.tryParse(input ?? "");
  Student student = Student(name, score);
  String finalResult;
  if (score == null) {
    finalResult = "Invalid input! Please enter a valid number.";
  } else if (score < 0 || score > 100) {
    finalResult = "Score must be between 0 and 100.";
  } else {
    String grade = calculateGrade(score);
    finalResult = "Student: ${student.name}\n"
        "Score: $score\n"
        "Grade: $grade";
  }
  print("\n----- RESULT -----");
  print(finalResult);
}