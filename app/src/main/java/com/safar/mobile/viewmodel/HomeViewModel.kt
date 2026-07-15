package com.safar.mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safar.mobile.data.MockData
import com.safar.mobile.data.network.RetrofitClient
import com.safar.mobile.model.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _trendingDestinations = MutableStateFlow<List<Destination>>(emptyList())
    val trendingDestinations: StateFlow<List<Destination>> = _trendingDestinations.asStateFlow()

    private val _internationalTours = MutableStateFlow<List<Destination>>(emptyList())
    val internationalTours: StateFlow<List<Destination>> = _internationalTours.asStateFlow()

    private val _adminTours = MutableStateFlow<List<Destination>>(emptyList())
    val adminTours: StateFlow<List<Destination>> = _adminTours.asStateFlow()

    private val _sylhetDestinations = MutableStateFlow<List<Destination>>(emptyList())
    val sylhetDestinations: StateFlow<List<Destination>> = _sylhetDestinations.asStateFlow()

    init {
        fetchData()
    }

    fun addAdminTour(destination: Destination) {
        _adminTours.value = _adminTours.value + destination
    }

    fun deleteTour(id: Any) {
        _adminTours.value = _adminTours.value.filter { it.id != id }
        _trendingDestinations.value = _trendingDestinations.value.filter { it.id != id }
        _sylhetDestinations.value = _sylhetDestinations.value.filter { it.id != id }
        _internationalTours.value = _internationalTours.value.filter { it.id != id }
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                // Attempt to fetch from Backend (Keeping existing bridge for future)
//                val trending = RetrofitClient.apiService.getTrendingDestinations()
//                _trendingDestinations.value = trending
            } catch (e: Exception) {
                // Silently fails for now, using mock data
            }
            // Always load MockData for stability during refinement
            _trendingDestinations.value = MockData.trendingDestinations
            _internationalTours.value = MockData.internationalTourPackages
            _sylhetDestinations.value = MockData.sylhetTourPackages
        }
    }
}
