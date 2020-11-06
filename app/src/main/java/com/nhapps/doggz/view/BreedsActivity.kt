package com.nhapps.doggz.view

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhapps.doggz.R
import com.nhapps.doggz.view.adapters.BreedsRVAdapter
import com.nhapps.doggz.viewmodel.BreedsViewModel
import kotlinx.android.synthetic.main.activity_breeds.*
import org.koin.android.viewmodel.ext.android.viewModel

class BreedsActivity : AppCompatActivity() {

    private lateinit var myAdapter: BreedsRVAdapter
    private val viewModel: BreedsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breeds)
        initComponents()
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        btnReintentarBreeds.setOnClickListener {
            viewModel.getBreedList()

        }
        svBreeds.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                myAdapter.filter(query!!)
                svBreeds.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                 myAdapter.filter(newText!!)
                return true
            }
        })
    }

    private fun initComponents() {
        viewModel.getBreedList()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        myAdapter = BreedsRVAdapter { currentItem ->
            val intent = Intent(this, BreedImagesActivity::class.java)
            intent.putExtra("selectedBreed", currentItem)
            startActivity(intent)
        }
        rvBreeds.adapter = myAdapter
        rvBreeds.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        viewModel.breedList.observe(this, Observer { breedList ->
            myAdapter.setData(breedList)
        })
        viewModel.loading.observe(this, Observer { visibility ->
            pbBreeds.visibility = visibility
        })
        viewModel.reintentarButton.observe(this, Observer { visibility ->
            btnReintentarBreeds.visibility = visibility
        })
        viewModel.errorImage.observe(this, Observer { visibility ->
            ivErrorBreeds.visibility = visibility
        })
        viewModel.breedSearch.observe(this, Observer { visibility ->
            svBreeds.visibility = visibility
        })
    }

}


