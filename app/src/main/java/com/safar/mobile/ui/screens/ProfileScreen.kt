package com.safar.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.safar.mobile.model.Destination
import com.safar.mobile.model.DestinationType
import com.safar.mobile.ui.theme.SafarPrimary
import com.safar.mobile.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var isAdmin by remember { mutableStateOf(false) } // Toggle for demo
    var showAddTourDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("Milad Hossain") }
    var userEmail by remember { mutableStateOf("milad@example.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Header
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(SafarPrimary.copy(alpha = 0.1f), RoundedCornerShape(50)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(60.dp), tint = SafarPrimary)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (isEditing) {
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { isEditing = false },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Changes")
            }
        } else {
            Text(text = userName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(text = userEmail, color = Color.Gray)
            TextButton(onClick = { isEditing = true }) {
                Text("Edit Profile", color = SafarPrimary)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Admin Toggle (For demonstration)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Admin Mode", fontWeight = FontWeight.SemiBold)
            Switch(checked = isAdmin, onCheckedChange = { isAdmin = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isAdmin) {
            Button(
                onClick = { showAddTourDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add New Tour")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu Items
        ProfileMenuItem(icon = Icons.Default.Map, label = "My Trips")
        ProfileMenuItem(icon = Icons.Default.CreditCard, label = "Payment Methods", onClick = { navController.navigate(com.safar.mobile.navigation.Screen.PaymentMethods.route) })
        ProfileMenuItem(icon = Icons.Default.Settings, label = "Settings")
        ProfileMenuItem(icon = Icons.Default.Help, label = "Help & Support", onClick = { navController.navigate("help") })
        
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = { navController.navigate("welcome") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text("Logout")
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // Bottom Nav space
    }

    if (showAddTourDialog) {
        AddTourDialog(
            onDismiss = { showAddTourDialog = false },
            onConfirm = { newTour ->
                viewModel.addAdminTour(newTour)
                showAddTourDialog = false
            }
        )
    }
}

@Composable
fun ProfileMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTourDialog(onDismiss: () -> Unit, onConfirm: (Destination) -> Unit) {
    var title by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Tour") },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Tour Title") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = price, onValueChange = { price = it }, label = { Text("Price (e.g. ৳25,000)") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
            }
        },
        confirmButton = {
            Button(onClick = {
                val newTour = Destination(
                    id = (300..1000).random(),
                    title = title,
                    location = location,
                    description = description,
                    price = price,
                    imageUrl = "https://images.unsplash.com/photo-1476514525535-07fb3b4ae5f1?q=80&w=2070", // Default
                    type = DestinationType.TRENDING
                )
                onConfirm(newTour)
            }) { Text("Add") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
