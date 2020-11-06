package com.nhapps.doggz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhapps.doggz.R
import com.nhapps.doggz.view.adapters.BreedImagesRVAdapter
import com.nhapps.doggz.viewmodel.BreedImagesViewModel
import kotlinx.android.synthetic.main.activity_breed_images.*
import kotlinx.android.synthetic.main.rv_breed_images_item.*
import org.koin.android.viewmodel.ext.android.viewModel

class BreedImagesActivity : AppCompatActivity() {

    private val viewModel: BreedImagesViewModel by viewModel()
    private lateinit var selectedBreed: String
    private lateinit var myAdapter: BreedImagesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_images)
        initComponents()
        setObservers()
    }

    private fun initComponents() {
        selectedBreed = intent.getStringExtra("selectedBreed")!!
        tvSelectedBreed.text = "\uD83D\uDC15 $selectedBreed"
        viewModel.getBreedImages(selectedBreed)
        setupRecyclerView()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        btnVolver.setOnClickListener {
            finish()
        }
        btnReintentarImages.setOnClickListener {
            viewModel.getBreedImages(selectedBreed)
        }

    }

    private fun setupRecyclerView(){
        myAdapter = BreedImagesRVAdapter()
        rvBreedImages.adapter = myAdapter
        rvBreedImages.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        viewModel.imagesList.observe(this, Observer{ breedImages ->
            myAdapter.setData(breedImages)
        })
        viewModel.loading.observe(this, Observer{ visibility ->
            pbImages.visibility = visibility
        })
        viewModel.reintentarButton.observe(this, Observer { visibility ->
            btnReintentarImages.visibility = visibility
        })
        viewModel.errorImage.observe(this, Observer { visibility ->
            ivErrorImages.visibility = visibility
        })
    }
}