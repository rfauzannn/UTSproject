package lat.pam.utsproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: List<Food>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_food)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", "Rp. 5.000", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", "Rp. 20.000", R.drawable.black_salad),
            Food("Cireng", "Cireng Enak dan Murah", "Rp. 5000", R.drawable.cireng),
            Food("Cappuccino", "Kopi cappuccino asli yang dibuat dari Kopi Arabica", "Rp. 15.000", R.drawable.cappuchino),
            Food("Donat", "Donat khas bandung yang terbuat dari bahan premium", "Rp. 10.000", R.drawable.donut),
            Food("Cheese Cake", "Terbuat dari keju cheddar asli", "Rp. 25.000", R.drawable.cheesecake),
            Food("Nasi Goreng", "Nasi Goreng abang-abang asli, dijamin enak dan porsi banyak", "Rp. 15.000", R.drawable.nasigoreng),
            Food("Sparkling Tea", "Cocok untuk cuaca panas, apalagi sambil menikmati desir angin pantai", "Rp. 10.000", R.drawable.sparkling_tea)
        )

        // Inisialisasi adapter dengan context dari ListFoodActivity
        val adapter = FoodAdapter(foodList, this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    }
}
