package cl.individual.certificacionm7.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.individual.certificacionm7.R
import cl.individual.certificacionm7.databinding.FragmentDetailsBinding
import cl.individual.certificacionm7.view.SuperheroViewModel
import coil.load

private const val ARG_PARAM1 = "id"

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val heroViewModel: SuperheroViewModel by activityViewModels()
    private var selectedHeroId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedHeroId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        heroViewModel.getSuperheroDetails(selectedHeroId)
        initDetailsView()
        initSendEmailButton()
        return binding.root
    }


    private fun initDetailsView() {

        heroViewModel.superheroesDetailsLiveData(selectedHeroId).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.txtHeroNameDt.text = it.nombre
                binding.txtFecha.text = getString(R.string.creation_date_dt, it.fechaCreacion)
                binding.txtColor.text = getString(R.string.color_dt, it.color)
                binding.txtDescriptionDt.text =
                    getString(R.string.description_title, it.origen, it.poder)
                binding.imgHeroDt.load(it.imagenLink)

                val translationValue: String =
                    if (it.traduccion == getString(R.string.translation_value)) {
                        getString(R.string.translation_true)
                    } else {
                        getString(R.string.translation_false)
                    }

                binding.txtTraduccion.text = translationValue
            }
        }
    }

    private fun initSendEmailButton() {
        binding.fabSendEmail.setOnClickListener {
            heroViewModel.superheroesDetailsLiveData(selectedHeroId).observe(viewLifecycleOwner) {
                heroViewModel.sendEmail(requireContext(), it.nombre)
            }
        }
    }

}