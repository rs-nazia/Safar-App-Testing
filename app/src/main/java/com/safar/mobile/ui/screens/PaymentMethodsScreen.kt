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
import com.safar.mobile.data.MockData
import com.safar.mobile.ui.theme.SafarPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodsScreen(navController: NavController) {
    var showAddDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = SafarPrimary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Payment Method")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8F9FA))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Saved Methods",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            MockData.samplePaymentMethods.forEach { method ->
                PaymentMethodCard(
                    type = method["type"] ?: "",
                    number = method["number"] ?: "",
                    expiry = method["expiry"] ?: "",
                    onClick = { showSuccessDialog = true }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Add new for next trip",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Payment Method") },
            text = {
                Column {
                    Text("This is a demo UI. In a real app, you would integrate with a payment gateway.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(value = "", onValueChange = {}, label = { Text("Card Number") })
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        OutlinedTextField(value = "", onValueChange = {}, label = { Text("MM/YY") }, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(value = "", onValueChange = {}, label = { Text("CVV") }, modifier = Modifier.weight(1f))
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showAddDialog = false }) {
                    Text("Save (Demo)")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Booking Successful! 🎉") },
            text = { Text("Your hotel and tour have been booked successfully. Check 'My Trips' for details.") },
            confirmButton = {
                Button(onClick = { 
                    showSuccessDialog = false
                    navController.popBackStack(com.safar.mobile.navigation.Screen.Home.route, false)
                }) {
                    Text("Go Home")
                }
            }
        )
    }
}

@Composable
fun PaymentMethodCard(type: String, number: String, expiry: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (type == "bKash") Icons.Default.AccountBalanceWallet else Icons.Default.CreditCard,
                contentDescription = null,
                tint = SafarPrimary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = type, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = number, color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = expiry, color = Color.Gray, fontSize = 12.sp)
        }
    }
}
