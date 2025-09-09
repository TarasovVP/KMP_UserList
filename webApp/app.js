import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName: String,
    val lastName: String,
    val maidenName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val birthDate: String,
    val image: String
)

class GetUserListUseCase {
    suspend fun execute(): List<User> {

        kotlinx.coroutines.delay(1000)

        return listOf(
            User(
                firstName = "Emily",
                lastName = "Johnson",
                maidenName = "Smith",
                age = 28,
                gender = "female",
                email = "emily.johnson@x.dummyjson.com",
                phone = "+81 965-431-3024",
                birthDate = "1996-5-30",
                image = "https://dummyjson.com/icon/emilys/128"
            ),
            User(
                firstName = "John",
                lastName = "Doe",
                maidenName = "",
                age = 32,
                gender = "male",
                email = "john.doe@x.dummyjson.com",
                phone = "+1 555-0123",
                birthDate = "1992-3-15",
                image = "https://dummyjson.com/icon/johns/128"
            )
        )
    }
}

fun main() {
    window.onload = {
        loadUsers()
    }
}

fun showLoading(show: Boolean) {
    document.getElementById("loading")?.style?.display = if (show) "block" else "none"
}

fun showError(message: String) {
    val errorElement = document.getElementById("error")
    errorElement?.style?.display = "block"
    errorElement?.textContent = message
}

fun hideError() {
    document.getElementById("error")?.style?.display = "none"
}

fun renderUsers(users: List<User>) {
    val usersContainer = document.getElementById("users-container")
    usersContainer?.innerHTML = users.joinToString("") { user ->
        """
        <div class="user-card">
            <img src="${user.image}" alt="${user.firstName} ${user.lastName}" class="user-image"
                 onerror="this.src='https://via.placeholder.com/128?text=No+Image'">

            <div class="user-info">
                <h2 class="user-name">${user.firstName} ${user.lastName}</h2>

                <div class="user-details">
                    <div class="user-detail">
                        <strong>Birth:</strong>
                        ${user.birthDate} (${user.age} years old)
                    </div>

                    <div class="user-detail">
                        <strong>Gender:</strong>
                        <span class="gender-${user.gender}">${user.gender}</span>
                    </div>

                    <div class="user-detail">
                        <strong>Email:</strong>
                        <span class="user-contact">${user.email}</span>
                    </div>

                    <div class="user-detail">
                        <strong>Phone:</strong>
                        <span class="user-contact">${user.phone}</span>
                    </div>
                </div>
            </div>
        </div>
        """.trimIndent()
    }
}

@JsName("loadUsers")
fun loadUsers() {
    showLoading(true)
    hideError()

    val scope = MainScope()
    scope.launch {
        try {
            val useCase = GetUserListUseCase()
            val users = useCase.execute()
            renderUsers(users)
        } catch (e: Exception) {
            showError("Error loading users: ${e.message}")
        } finally {
            showLoading(false)
        }
    }
}