package com.sadikahmetozdemir.sadik_fodamy.ui.add_recipe

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.domain.entities.NumberOfPerson
import com.sadikahmetozdemir.domain.entities.TimeOfRecipe
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentPostRecipeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class PostRecipeFragment :
    BaseFragment<FragmentPostRecipeBinding, PostRecipeViewModel>(R.layout.fragment_post_recipe) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        registerLauncher()
        binding.apply {
            actvCategory.setOnItemClickListener { parent, _, position, _ ->
                val item =
                    parent.getItemAtPosition(position) as Category
                viewModel.categoryID = item.id
            }
            actvNumberOfPeople.setOnItemClickListener { parent, _, position, _ ->
                val item =
                    parent.getItemAtPosition(position) as NumberOfPerson
                viewModel.numberOfPersonID = item.id
            }
            actvTimeOfRecipe.setOnItemClickListener { parent, _, position, _ ->
                val item =
                    parent.getItemAtPosition(position) as TimeOfRecipe
                viewModel.timeOfRecipeNumber = item.id
            }
        }
        binding.ivFoodImage.setOnClickListener {
            selectImage()
        }
    }

    var selectedBitmap: Bitmap? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    fun initObservers() {
        viewModel.numberOfRecipes.observe(viewLifecycleOwner) { itNumber ->
            binding.actvNumberOfPeople.setAdapter(
                ArrayAdapter(
                    requireContext(), android.R.layout.simple_dropdown_item_1line,
                    itNumber
                )
            )
        }
        viewModel.timeOfRecipes.observe(viewLifecycleOwner) { itTime ->
            binding.actvTimeOfRecipe.setAdapter(
                ArrayAdapter(
                    requireContext(), android.R.layout.simple_spinner_dropdown_item, itTime
                )
            )
        }
        viewModel.category.observe(viewLifecycleOwner) { itCategory ->
            binding.actvCategory.setAdapter(
                ArrayAdapter(
                    requireContext(), android.R.layout.simple_spinner_dropdown_item, itCategory
                )
            )
        }
    }

    fun selectImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                view?.let {
                    Snackbar.make(it, getString(R.string.permission), Snackbar.LENGTH_INDEFINITE)
                        .setAction(
                            getString(R.string.give_permission),
                            {
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        ).show()
                }
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }

    private fun registerLauncher() {

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intentFromResult = result.data
                    if (intentFromResult != null) {
                        val imageData = intentFromResult.data
                        viewModel.image = imageData
                        try {
                            if (Build.VERSION.SDK_INT >= 28) {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    imageData!!
                                )

                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.ivFoodImage.setImageBitmap(selectedBitmap)
                            } else {
                                selectedBitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    imageData
                                )
                                binding.ivFoodImage.setImageBitmap(selectedBitmap)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    // permission granted
                    val intentToGallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)
                } else {
                    // permission denied
                    // Toast
                }
            }
    }
}
