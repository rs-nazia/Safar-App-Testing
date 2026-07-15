package com.safar.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.safar.mobile.data.MockData
import com.safar.mobile.ui.components.FlightItemCard
import com.safar.mobile.ui.components.FlightSearchCard

@Composable
fun FlightsScreen(navController: NavController) {
    var filteredFlights by androidx.compose.runtime.remember { 
        androidx.compose.runtime.mutableStateOf(MockData.sampleFlights) 
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Search Section
        FlightSearchCard(
            onSearchClick = { from, to ->
                filteredFlights = MockData.sampleFlights.filter { 
                    it.from.contains(from, ignoreCase = true) && 
                    it.to.contains(to, ignoreCase = true)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Results Section
        Text(
            text = if (filteredFlights.isEmpty()) "No Flights Found" else "Search Results",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(filteredFlights) { flight ->
                FlightItemCard(flight = flight)
            }
        }
    }
}
