package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.rickandmorty.databinding.ActivityCharactersBinding
import com.example.rickandmorty.model.CharacterView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_characters.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    private lateinit var charactersListAdapter: CharactersListAdapter
    private val vm: CharactersViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        srlFragmentHome.setOnRefreshListener { charactersListAdapter.refresh() }
    }

    private fun setupRecyclerView() {
        charactersListAdapter = CharactersListAdapter { adapterOnClick(it) }
        rv_characters_activityCharacter.adapter = charactersListAdapter
        rv_characters_activityCharacter.adapter = charactersListAdapter.withLoadStateFooter(
            footer = CharacterLoadStateAdapter(charactersListAdapter)
        )
        lifecycleScope.launchWhenCreated {
            charactersListAdapter.loadStateFlow.collect { loadStates ->
                srlFragmentHome.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.myList.collectLatest {
                charactersListAdapter.submitData(it)
            }
        }

        rv_characters_activityCharacter.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun adapterOnClick(characterView: CharacterView) {
    }
}