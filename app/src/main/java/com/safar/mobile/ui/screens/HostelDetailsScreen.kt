package com.safar.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.safar.mobile.data.MockData
import com.safar.mobile.ui.theme.SafarPrimary
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HostelDetailsScreen(navController: NavController, hostelId: String) {
    val hostel = MockData.hotels.find { it.id == hostelId }

    if (hostel == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Hostel not found")
        }
        return
    }

    Scaffold(
        bottomBar = {
            Button(
                onClick = { navController.navigate(com.safar.mobile.navigation.Screen.PaymentMethods.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SafarPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Book Now - ${hostel.pricePerNight}", fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                AsyncImage(
                    model = hostel.imageUrl,
                    contentDescription = hostel.name,
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(16.dp).background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(50))
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = hostel.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = hostel.location, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(24.dp))
                
                Text(text = "Facilities", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    hostel.facilities.forEach { facility ->
                        AssistChip(
                            onClick = {},
                            label = { Text(facility) },
                            leadingIcon = {
                                val icon = when {
                                    facility.contains("WiFi", true) -> Icons.Default.Wifi
                                    facility.contains("AC", true) -> Icons.Default.Star
                                    facility.contains("Security", true) -> Icons.Default.Lock
                                    facility.contains("Breakfast", true) -> Icons.Default.Restaurant
                                    else -> Icons.Default.Check
                                }
                                Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Description", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "A comfortable stay with modern amenities. Perfect for travelers exploring the city.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
