package com.example.house_rental_app.theme.screens.property

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.house_rental_app.R
import com.example.house_rental_app.entity.HouseEntity

@Composable
fun ContactLandlord(navController: NavController, houseEntity: HouseEntity) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Contact Landlord", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "House : " + houseEntity.address, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        val email = "sunnam@gmail"
        val phoneNumber = "9023381292"
        // Email
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                val email = "sunnamm@gmail.com"
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                context.startActivity(intent)
            }
        ) {
            Icon(Icons.Default.Email, "Email", Modifier.size(30.dp))
            Spacer(Modifier.width(8.dp))
            Text(email, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phone
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                val phoneNumber = "9023391292" // Ensure phoneNumber is from propertyDetails
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                context.startActivity(dialIntent)
            }
        ) {
            Icon(Icons.Default.Phone, contentDescription = "Phone", modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(phoneNumber, style = MaterialTheme.typography.bodyLarge)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ContactLandLordPreview() {
    val navController = rememberNavController()
    ContactLandlord(navController,  HouseEntity( address = "111 Main St", price = 234, ownerId = 23, lease = "April 1st 2022", images = "img_2", description = "A 2 bedroom" ),)
}
