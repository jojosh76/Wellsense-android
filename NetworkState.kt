//  Sealed Class
sealed class NetworkState {

    object Loading : NetworkState()

    data class Success(val data: String) : NetworkState()

    data class Error(val message: String) : NetworkState()
}

//  Function to handle states
fun handleState(state: NetworkState) {
    when (state) {
        is NetworkState.Loading -> {
            println("Loading... Please wait.")
        }

        is NetworkState.Success -> {
            println("Success: ${state.data}")
        }

        is NetworkState.Error -> {
            println("Error: ${state.message}")
        }
    }
}

// Example Usage
fun main() {
    val states = listOf(
        NetworkState.Loading,
        NetworkState.Success("User data loaded"),
        NetworkState.Error("Network timeout")
    )

    states.forEach { handleState(it) }
}