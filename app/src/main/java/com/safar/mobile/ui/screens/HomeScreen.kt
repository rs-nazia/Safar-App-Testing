package com.safar.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.safar.mobile.ui.components.*
import com.safar.mobile.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onTourClick: () -> Unit = {},
    onHotelClick: () -> Unit = {},
    onFlightClick: () -> Unit = {},
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val trendingDestinations by viewModel.trendingDestinations.collectAsState()
    val sylhetTours by viewModel.sylhetDestinations.collectAsState()
    val internationalTours by viewModel.internationalTours.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HeroSection(onPlanTripClick = onFlightClick)
        Spacer(modifier = Modifier.height(16.dp))
        
        ServiceButtonGrid(
            onFlightClick = onFlightClick,
            onHotelClick = onHotelClick,
            onTourClick = onTourClick
        )

        Spacer(modifier = Modifier.height(24.dp))
        SectionHeader("Trending Destinations")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(trendingDestinations) { destination ->
                TrendingCard(
                    destination = destination,
                    onClick = { navController.navigate(com.safar.mobile.navigation.Screen.DestinationDetails.createRoute(destination.id)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        SectionHeader("Sylhet Tours")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sylhetTours) { destination ->
                TrendingCard(
                    destination = destination,
                    onClick = { navController.navigate(com.safar.mobile.navigation.Screen.DestinationDetails.createRoute(destination.id)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        SectionHeader("International Tours")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(internationalTours) { destination ->
                TrendingCard(
                    destination = destination,
                    onClick = { navController.navigate(com.safar.mobile.navigation.Screen.DestinationDetails.createRoute(destination.id)) }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )
}
