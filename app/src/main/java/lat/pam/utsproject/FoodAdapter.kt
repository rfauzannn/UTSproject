package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val foodList: List<Food>, private val context: Context) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
        val orderCartIcon: ImageView = itemView.findViewById(R.id.orderCartIcon) // Menyimpan referensi ke ikon keranjang
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodName.text = food.name
        holder.foodPrice.text = food.price
        holder.foodDescription.text = food.description
        holder.foodImage.setImageResource(food.imageResourceId)

        // Listener untuk membuka OrderActivity dengan nama makanan saat item diklik
        holder.itemView.setOnClickListener {
            val intent = Intent(context, OrderActivity::class.java)
            intent.putExtra("FOOD_NAME", food.name) // Kirim nama makanan
            context.startActivity(intent)
        }

        // Listener khusus untuk ikon keranjang (orderCartIcon)
        holder.orderCartIcon.setOnClickListener {
            // Hanya navigasi ke OrderActivity tanpa pesan
            val intent = Intent(context, OrderActivity::class.java)
            intent.putExtra("FOOD_NAME", food.name) // Kirim nama makanan (atau data lain yang relevan)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = foodList.size
}
