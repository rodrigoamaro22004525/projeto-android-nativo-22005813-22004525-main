package pt.ulusofona.deisi.cm2223.g22005813_22004525.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2223.g22005813_22004525.databinding.RowFilmeListBinding
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.Filme
import pt.ulusofona.deisi.cm2223.g22005813_22004525.models.FilmeAvaliacao
import java.text.SimpleDateFormat
import java.util.Locale

class FilmeListAdapter(val listaFilmes: List<FilmeAvaliacao>, val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<FilmeListAdapter.FilmeViewHolder>() {
    class FilmeViewHolder(val binding: RowFilmeListBinding) : RecyclerView.ViewHolder(binding.root)

    // informação de cada elemento da recycler view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmeViewHolder { // quando a recycler view precisa de dar update
        return FilmeViewHolder(RowFilmeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun getItemCount(): Int {
        return listaFilmes.size
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {

        val filme = listaFilmes[position]
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        // o que vai ser printed no display
        holder.binding.tvFilme.text = filme.info.nome
        holder.binding.tvData.text = sdf.format(filme.dataVisto)
        holder.binding.tvAvaliacao.text = "${filme.avaliacaoUtilizador}/10"

        if(holder.binding.tvDescricao != null) {
            holder.binding.tvDescricao.text = filme.observacoes
        }

        holder.itemView.setOnClickListener {
            onClickListener(filme.uuid)
        }
    }
}