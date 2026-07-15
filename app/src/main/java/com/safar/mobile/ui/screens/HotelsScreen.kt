package com.safar.mobile.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.safar.mobile.data.MockData
import com.safar.mobile.ui.theme.SafarPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelsScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(MockData.hotels) { hotel ->
            HotelCard(hotel, onDetailsClick = {
                navController.navigate("hostel_details/${hotel.id}")
            })
        }
    }
}

@Composable
fun HotelCard(hotel: com.safar.mobile.model.Hotel, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            AsyncImage(
                model = hotel.imageUrl,
                contentDescription = hotel.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = hotel.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD700), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = hotel.rating.toString(), fontWeight = FontWeight.Bold)
                    }
                }
                
                Text(
                    text = hotel.location,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Amenities chips
                Row(modifier = Modifier.horizontalScroll(androidx.compose.foundation.rememberScrollState())) {
                    hotel.facilities.forEach { facility ->
                        AssistChip(
                            onClick = {},
                            label = { Text(facility) },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Start from", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        Text(
                            text = hotel.pricePerNight + "/night",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SafarPrimary
                        )
                    }
                    Button(
                        onClick = onDetailsClick,
                        colors = ButtonDefaults.buttonColors(containerColor = SafarPrimary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("View Details")
                    }
                }
            }
        }
    }
}
