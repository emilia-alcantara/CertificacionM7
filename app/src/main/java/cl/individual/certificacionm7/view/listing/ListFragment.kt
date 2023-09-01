package cl.individual.certificacionm7.view.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.individual.certificacionm7.R
import cl.individual.certificacionm7.databinding.FragmentListBinding
import cl.individual.certificacionm7.view.SuperheroViewModel


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val heroViewModel: SuperheroViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        heroViewModel.getAllSuperheroes()
        initRecycler()

        return binding.root
    }

    private fun initRecycler() {
        val heroListAdapter = HeroListAdapter()

        binding.recSuperheroList.adapter = heroListAdapter

        heroViewModel.superheroesLiveData().observe(viewLifecycleOwner) {
            heroListAdapter.setData(it)
        }
    }


}