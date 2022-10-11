package uz.isystem.weatherapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.weatherapp.core.moduls.BoardData
import uz.isystem.weatherapp.databinding.ItemBoardBinding

class OnBoardAdapter : RecyclerView.Adapter<OnBoardAdapter.ViewHolder>() {

    var skipButtonPress: (() -> Unit)? = null

    var data = ArrayList<BoardData>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(d: BoardData) {

            binding.imageBoard.setImageResource(d.image)
            binding.titleBoard.text = d.title
            binding.descriptionBoard.text = d.description

            binding.rootView.setBackgroundColor(d.backgroundColor)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindData(data[position])

    override fun getItemCount(): Int = data.size


}

