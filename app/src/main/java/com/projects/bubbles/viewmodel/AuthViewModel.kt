import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.projects.bubbles.dto.LoginRequest
import com.projects.bubbles.dto.LoginResponse
import com.projects.bubbles.dto.RegisterRequest
import com.projects.bubbles.dto.RegisterResponse
import com.projects.bubbles.services.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

sealed class AuthResult {
    data class Success(val message: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthViewModel : ViewModel() {

    val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()
    val registerResult: MutableLiveData<RegisterResponse> = MutableLiveData()
    val erro: MutableLiveData<String> = MutableLiveData()

    private val authService = Service.AuthService()

    fun login(email: String, password: String, navController: NavHostController): Boolean {
        var success = false
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("AuthViewModel", "Attempting login...")
                val response = authService.login(LoginRequest(email, password))
                Log.d("AuthViewModel", "Login response received: ${response.raw()}")
                success = response.isSuccessful
                if (success) {
                    handleLoginResponse(response)

                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error during login: ${e.message}")
                erro.postValue("Erro ao efetuar login: ${e.message}")
            }
        }
        return success
    }

    fun register(data: RegisterRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("AuthViewModel", "Attempting registration...")
                val response = authService.register(data)
                Log.d("AuthViewModel", "Registration response received: ${response.raw()}")
                handleRegisterResponse(response)
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error during registration: ${e.message}")
                erro.postValue("Erro ao registrar: ${e.message}")
            }
        }
    }

    private fun handleLoginResponse(response: Response<LoginResponse>) {
        if (response.isSuccessful) {
            loginResult.postValue(response.body())
            erro.postValue("")
            Log.d("AuthViewModel", "Login successful")
        } else {
            Log.e("AuthViewModel", "Error during login: ${response.message()}")
            erro.postValue("Erro ao efetuar login: ${response.message()}")
        }
    }

    private fun handleRegisterResponse(response: Response<RegisterResponse>) {
        if (response.isSuccessful) {
            registerResult.postValue(response.body())
            Log.d("AuthViewModel", "Registration successful")
        } else {
            Log.e("AuthViewModel", "Error during registration: ${response.message()}")
            erro.postValue("Erro ao registrar: ${response.message()}")
        }
    }
}
