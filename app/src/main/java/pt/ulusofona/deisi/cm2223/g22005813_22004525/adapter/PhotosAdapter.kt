package pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.PhotoBinding

class PhotosAdapter(var items: List<String> = listOf()) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    private val model = FilmeRepository.getInstance()

    class PhotosViewHolder(val binding: PhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosViewHolder {
        return PhotosViewHolder(
            PhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val base64Image = items[position]
        val imageBitmap = model.decodeBase64ToImageView(base64Image)
        Log.i("APP", base64Image)

        holder.binding.ivPhoto.setImageBitmap(imageBitmap)
    }

    override fun getItemCount() = items.size

    fun updateItems(items: MutableList<String>?) {
        if(items == null)
            return

        this.items = items
        notifyDataSetChanged()
    }
}