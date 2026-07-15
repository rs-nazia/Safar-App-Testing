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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.safar.mobile.data.MockData
import com.safar.mobile.ui.theme.SafarPrimary
import com.safar.mobile.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationDetailsScreen(
    navController: NavController,
    destinationId: Int,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val destination = MockData.sylhetTourPackages.find { it.id == destinationId }
        ?: MockData.internationalTourPackages.find { it.id == destinationId }
        ?: MockData.trendingDestinations.find { it.id == destinationId }

    if (destination == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Destination not found")
        }
        return
    }

    Scaffold(
        bottomBar = {
            Button(
                onClick = { navController.navigate(com.safar.mobile.navigation.Screen.PaymentMethods.route) },
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SafarPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Book Now - ${destination.price ?: "Inquire"}", fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().verticalScroll(rememberScrollState())) {
            Box {
                AsyncImage(
                    model = destination.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp).background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(50))) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                }
                IconButton(
                    onClick = {
                        viewModel.deleteTour(destination.id)
                        navController.popBackStack()
                    },
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp).background(Color.Red.copy(alpha = 0.6f), RoundedCornerShape(50))
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
                }
            }

            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = destination.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = destination.duration ?: "", color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = destination.description, style = MaterialTheme.typography.bodyLarge, color = Color.DarkGray)
            }
        }
    }
}
