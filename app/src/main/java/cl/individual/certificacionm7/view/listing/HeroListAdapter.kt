package cl.individual.certificacionm7.view.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.individual.certificacionm7.data.local.SuperheroEntity
import cl.individual.certificacionm7.databinding.ItemLayoutBinding
import coil.load

class HeroListAdapter : RecyclerView.Adapter<HeroListAdapter.HeroViewHolder> (){
    private lateinit var binding: ItemLayoutBinding
    private val heroesList = mutableListOf<SuperheroEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroesList[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = heroesList.size

    fun setData(hero: List<SuperheroEntity>) {
        this.heroesList.clear()
        this.heroesList.addAll(hero)
        notifyDataSetChanged()
    }

    class HeroViewHolder(val binding:ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: SuperheroEntity) {
            binding.imgHero.load(hero.imagenLink)
            binding.txtHeroName.text = hero.nombre
            binding.txtCreationDate.text = hero.fechaCreacion.toString()

            binding.cardHeroItem.setOnClickListener{
                val selectedHeroId= Bundle()
                selectedHeroId.putInt("id", hero.id)
                // pendiente navegar a otro fragment
            }
        }

    }


}