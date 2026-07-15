package com.safar.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safar.mobile.model.Flight
import com.safar.mobile.ui.theme.SafarPrimary

@Composable
fun FlightSearchCard(
    onSearchClick: (String, String) -> Unit
) {
    var fromText by remember { mutableStateOf("New York (JFK)") }
    var toText by remember { mutableStateOf("London (LHR)") }
    var depDate by remember { mutableStateOf("Oct 20, 2024") }
    var retDate by remember { mutableStateOf("Oct 27, 2024") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // From / To Row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // Simplified Inputs for Demo
                OutlinedTextField(
                    value = fromText,
                    onValueChange = { fromText = it },
                    label = { Text("From", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f).padding(end = 4.dp),
                    trailingIcon = { Icon(Icons.Default.FlightTakeoff, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
                )
                OutlinedTextField(
                    value = toText,
                    onValueChange = { toText = it },
                    label = { Text("To", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f).padding(start = 4.dp),
                    trailingIcon = { Icon(Icons.Default.FlightLand, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            // Dates Row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedTextField(
                    value = depDate,
                    onValueChange = { depDate = it },
                    label = { Text("Departure", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f).padding(end = 4.dp),
                    trailingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
                )
                OutlinedTextField(
                    value = retDate,
                    onValueChange = { retDate = it },
                    label = { Text("Return", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f).padding(start = 4.dp),
                    trailingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(16.dp)) },
                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onSearchClick(fromText, toText) },
                colors = ButtonDefaults.buttonColors(containerColor = SafarPrimary),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text("Search Flights", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FlightItemCard(flight: Flight) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left: Airline Logo placeholder & Time
            Column(modifier = Modifier.weight(1f)) {
                // Placeholder Logo
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Airlines, // Fallback icon
                        contentDescription = flight.airline,
                        tint = SafarPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(flight.airline, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${flight.departureTime} - ${flight.arrivalTime}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = flight.duration,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // Right: Price & Button
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${flight.price.toInt()}",
                    color = SafarPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO: Book Logic */ },
                    colors = ButtonDefaults.buttonColors(containerColor = SafarPrimary),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Book", fontSize = 12.sp)
                }
            }
        }
    }
}
