package com.safar.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safar.mobile.ui.theme.SafarPrimary

@Composable
fun ServiceButtonGrid(onFlightClick: () -> Unit, onHotelClick: () -> Unit, onTourClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ServiceButton(icon = Icons.Default.Map, label = "Tours", onClick = onTourClick)
        ServiceButton(icon = Icons.Default.Hotel, label = "Hotels", onClick = onHotelClick)
        ServiceButton(icon = Icons.Default.Flight, label = "Flights", onClick = onFlightClick)
    }
}

@Composable
fun ServiceButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = SafarPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.size(100.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White, // Changed tint to White for contrast with SafarPrimary background
                modifier = Modifier
                    .size(36.dp)
                    .background(SafarPrimary, CircleShape)
                    .padding(8.dp) // Added padding to the icon itself
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}
