/**
 * Project Milestone 3: Object-Oriented Domain Model
 * Application: Fitness Tracker
 */

// 1. Interface définissant un comportement commun [cite: 165, 243]
interface Loggable {
    fun getSummary(): String
}

// 2. Classe Abstraite servant de base pour la hiérarchie [cite: 145, 146]
// On utilise 'abstract' car on ne veut pas instancier un "Workout" générique [cite: 147]
abstract class Workout(val name: String, val durationMinutes: Int) : Loggable {
    // Méthode abstraite que chaque sous-classe doit implémenter [cite: 149]
    abstract fun calculateCalories(): Int
}

// 3. Data Class pour représenter l'entité utilisateur [cite: 174, 243]
// Génère automatiquement toString(), copy(), etc. [cite: 175]
data class UserProfile(val username: String, val weightKg: Double)

// 4. Sous-classe concrète pour la course à pied [cite: 120, 242]
class Running(duration: Int, val distanceKm: Double) : Workout("Running", duration) {
    override fun calculateCalories(): Int = (distanceKm * 60).toInt()

    override fun getSummary(): String = "$name: $distanceKm km en $durationMinutes min"
}

// 5. Sous-classe concrète pour le Yoga [cite: 120, 242]
class Yoga(duration: Int, val intensity: String) : Workout("Yoga", duration) {
    override fun calculateCalories(): Int = durationMinutes * 4

    // Requirement: Redéfinition manuelle de toString() [cite: 244]
    override fun toString(): String = "Session de Yoga ($intensity) - $durationMinutes min"

    override fun getSummary(): String = "$name: flux $intensity de $durationMinutes min"
}

// Fonction principale pour démontrer le modèle [cite: 246]
fun main() {
    // Instanciation de la data class [cite: 39]
    val user = UserProfile("AlexFit", 75.0)
    println("Profil Utilisateur : $user") // Utilise le toString() auto-généré [cite: 175]

    // Démonstration du polymorphisme
    // On stocke différentes sous-classes dans une liste de type 'Workout'
    val weeklyWorkouts: List<Workout> = listOf(
        Running(duration = 30, distanceKm = 5.2),
        Yoga(duration = 45, intensity = "Haute")
    )

    println("\n--- Journal d'activités hebdomadaire ---")
    for (workout in weeklyWorkouts) {
        // Appels de méthodes polymorphes
        println("Activité : ${workout.getSummary()}")
        println("Calories estimées : ${workout.calculateCalories()} kcal")

        // Affiche le détail via toString()
        println("Détails : $workout")
        println("----------------------------------------")
    }
}