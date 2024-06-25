package pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2223.g22005813_22004525.R
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.FilmeRepository
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.RowCinemaListBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Cinema


class CinemaListAdapter(val listaCinemas: List<Cinema>):
RecyclerView.Adapter<CinemaListAdapter.CinemaViewHolder>(){
    private val model = FilmeRepository.getInstance()

    class CinemaViewHolder(val binding: RowCinemaListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CinemaViewHolder {
        return CinemaViewHolder(RowCinemaListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }


    override fun getItemCount(): Int {
        return listaCinemas.size
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val cinema = listaCinemas[position]

        val paresDeRatings = cinema.ratings
        val median = paresDeRatings.sumOf { it.second }.toFloat() / paresDeRatings.size

        holder.binding.tvNomeCinema.text = cinema.name
        holder.binding.tvLocation.text = cinema.address
        holder.binding.tvRating.text = "$median/10"
        holder.binding.tvHorario.text = model.buildHorario(cinema)
        if(model.isOpen(cinema)) {
            holder.binding.ivCircle.setImageResource(R.drawable.ic_circle_green)
        } else {
            holder.binding.ivCircle.setImageResource(R.drawable.ic_circle_red)
        }
    }
}