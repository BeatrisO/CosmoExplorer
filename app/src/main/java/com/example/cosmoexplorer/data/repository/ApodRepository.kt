import com.example.space.data.remote.RetrofitInstance

class ApodRepository {
    suspend fun getApod(apiKey: String) =
        RetrofitInstance.api.getApod(apiKey)
}
