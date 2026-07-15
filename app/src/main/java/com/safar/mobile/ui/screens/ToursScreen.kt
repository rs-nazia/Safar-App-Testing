package com.safar.mobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.safar.mobile.data.MockData
import com.safar.mobile.navigation.Screen
import com.safar.mobile.ui.components.TrendingCard
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToursScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(MockData.sylhetTourPackages + MockData.internationalTourPackages) { destination ->
            TourListItem(destination = destination) {
                navController.navigate(Screen.DestinationDetails.createRoute(destination.id))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourListItem(destination: com.safar.mobile.model.Destination, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        Column {
            coil.compose.AsyncImage(
                model = destination.imageUrl,
                contentDescription = destination.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = destination.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = destination.price ?: "",
                        color = com.safar.mobile.ui.theme.SafarPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Icon(
                        androidx.compose.material.icons.Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = destination.location, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = destination.duration ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}
