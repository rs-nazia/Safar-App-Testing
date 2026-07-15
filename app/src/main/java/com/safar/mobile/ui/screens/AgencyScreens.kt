package com.safar.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyListingScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val agencies = MockData.sampleAgencies

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verified Agencies", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = { Text("Search by country, city or service") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(agencies) { agency ->
                    AgencyCard(agency = agency, onClick = {
                        navController.navigate("agency_profile/${agency["id"]}")
                    })
                }
            }
        }
    }
}

@Composable
fun AgencyCard(agency: Map<String, String>, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = agency["featureImage"],
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(140.dp),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = agency["logo"],
                    contentDescription = null,
                    modifier = Modifier.size(50.dp).clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = agency["name"] ?: "", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        if (agency["verified"] == "true") {
                            Icon(Icons.Default.Verified, contentDescription = "Verified", tint = Color(0xFF4CAF50), modifier = Modifier.size(14.dp))
                        }
                    }
                    Text(text = "📍 ${agency["location"]}", color = Color.Gray, fontSize = 12.sp)
                    Text(text = "⭐ ${agency["rating"]}", fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = SafarPrimary)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Starts from", fontSize = 10.sp, color = Color.Gray)
                    Text(text = agency["price"] ?: "", fontWeight = FontWeight.Bold, color = SafarPrimary, fontSize = 14.sp)
                    Text(text = "Details", color = SafarPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyProfileScreen(navController: NavController, agencyId: String) {
    val agency = MockData.sampleAgencies.find { it["id"] == agencyId } ?: return

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.height(250.dp).fillMaxWidth()) {
                AsyncImage(
                    model = agency["featureImage"],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp).background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(50))) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                }
            }
            
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = agency["logo"],
                        contentDescription = null,
                        modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = agency["name"] ?: "", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text(text = "Reg No: ${agency["regId"]}", color = Color.Gray, fontSize = 12.sp)
                        Text(text = "${agency["experience"]} Experience", fontWeight = FontWeight.SemiBold, color = SafarPrimary)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                InfoItem(icon = Icons.Default.LocationOn, label = "Address", value = agency["address"] ?: "")
                InfoItem(icon = Icons.Default.Phone, label = "Phone", value = agency["phone"] ?: "")
                InfoItem(icon = Icons.Default.Email, label = "Email", value = agency["email"] ?: "")

                Spacer(modifier = Modifier.height(24.dp))
                Text("Services", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(text = agency["services"] ?: "", color = Color.DarkGray)

                Spacer(modifier = Modifier.height(16.dp))
                Text("Countries Covered", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(text = agency["countries"] ?: "", color = Color.DarkGray)

                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {}, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(12.dp)) {
                    Text("Chat with Agency")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyRegistrationScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var tradeLicense by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agency Registration") }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, contentDescription = null) }
            })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(24.dp).verticalScroll(rememberScrollState())) {
            Text("Become a Partner", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text("Fill in the details to list your agency on Safar.", color = Color.Gray)
            
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Agency Name") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = tradeLicense, onValueChange = { tradeLicense = it }, label = { Text("Trade License / Reg ID") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email Address") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Office Address") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
            
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { /* Submit logic */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Submit Application")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyDashboardScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Agency Dashboard") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Welcome, Sylhet Travelers Ltd", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DashboardStatCard("Bookings", "12", Icons.Default.ConfirmationNumber, Modifier.weight(1f))
                DashboardStatCard("Rating", "4.6", Icons.Default.Star, Modifier.weight(1f))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Text("Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            
            DashboardActionItem(icon = Icons.Default.Add, label = "Add New Package")
            DashboardActionItem(icon = Icons.Default.List, label = "Manage Bookings")
            DashboardActionItem(icon = Icons.Default.RateReview, label = "Respond to Reviews")
            DashboardActionItem(icon = Icons.Default.MonetizationOn, label = "Earnings & Payouts")
        }
    }
}

@Composable
fun DashboardStatCard(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = SafarPrimary.copy(alpha = 0.1f))) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = SafarPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(text = label, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun DashboardActionItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), onClick = {}) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color.Gray)
            Text(value, fontWeight = FontWeight.Medium)
        }
    }
}
