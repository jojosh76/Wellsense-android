// 1️⃣ Interface
interface Drawable {
    fun draw()
}

// 2️⃣ Classe Circle
class Circle(private val radius: Int) : Drawable {

    override fun draw() {
        println("Drawing Circle with radius $radius")
        println("  ***  ")
        println(" *   * ")
        println(" *   * ")
        println("  ***  ")
        println()
    }
}

// 3️⃣ Classe Square
class Square(private val side: Int) : Drawable {

    override fun draw() {
        println("Drawing Square with side $side")
        println(" ***** ")
        println(" *   * ")
        println(" *   * ")
        println(" ***** ")
        println()
    }
}

// 4️⃣ Fonction main (Bonus demandé)
fun main() {

    // Création des objets
    val circle = Circle(5)
    val square = Square(4)

    // Liste polymorphique
    val shapes: List<Drawable> = listOf(circle, square)

    // Appel polymorphique
    shapes.forEach { shape ->
        shape.draw()
    }
}