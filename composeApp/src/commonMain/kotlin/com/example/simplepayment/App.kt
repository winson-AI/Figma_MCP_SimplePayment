package com.example.simplepayment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import simplepayment.composeapp.generated.resources.Res
import simplepayment.composeapp.generated.resources.ic_arrow_left
import simplepayment.composeapp.generated.resources.ic_check
import simplepayment.composeapp.generated.resources.image_credit_card
import simplepayment.composeapp.generated.resources.image_debit_card
import simplepayment.composeapp.generated.resources.ic_search

@Composable
@Preview
fun App() {
    MaterialTheme {
        PaymentScreen()
    }
}

@Composable
fun PaymentScreen() {
    var selectedPaymentMethod by remember { mutableStateOf("credit") }
    var saveCardDetails by remember { mutableStateOf(true) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .clip(RoundedCornerShape(35.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 22.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_arrow_left),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { /* Handle back navigation */ }
                )
                
                Image(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Handle search */ }
                )
            }
            
            Spacer(modifier = Modifier.height(31.dp))
            
            // Order Summary Section
            Text(
                text = "Order summary",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3C2F2F),
                modifier = Modifier.padding(start = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Order Details
            OrderDetailsSection()
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Payment Methods Section
            Text(
                text = "Payment methods",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3C2F2F),
                modifier = Modifier.padding(start = 7.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Payment Method Cards
            PaymentMethodCard(
                title = "Credit card",
                cardNumber = "5105 **** **** 0505",
                isSelected = selectedPaymentMethod == "credit",
                onClick = { selectedPaymentMethod = "credit" },
                backgroundColor = if (selectedPaymentMethod == "credit") Color(0xFF3C2F2F) else Color(0xFFF3F4F6),
                textColor = if (selectedPaymentMethod == "credit") Color.White else Color(0xFF3C2F2F),
                cardNumberColor = Color(0xFF808080),
                cardIcon = painterResource(Res.drawable.image_credit_card),
                imageSize = DpSize(70.dp, 42.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            PaymentMethodCard(
                title = "Debit card",
                cardNumber = "3566 **** **** 0505",
                isSelected = selectedPaymentMethod == "debit",
                onClick = { selectedPaymentMethod = "debit" },
                backgroundColor = if (selectedPaymentMethod == "debit") Color(0xFF3C2F2F) else Color(0xFFF3F4F6),
                textColor = if (selectedPaymentMethod == "debit") Color.White else Color(0xFF3C2F2F),
                cardNumberColor = Color(0xFF808080),
                cardIcon = painterResource(Res.drawable.image_debit_card),
                imageSize = DpSize(83.dp, 32.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Save Card Details Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { saveCardDetails = !saveCardDetails }
                    .padding(start = 7.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .background(
                            color = if (saveCardDetails) Color(0xFFEF2A39) else Color.Transparent,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = if (saveCardDetails) 0.dp else 1.dp,
                            color = Color(0xFFEF2A39),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (saveCardDetails) {
                        Image(
                            painter = painterResource(Res.drawable.ic_check),
                            contentDescription = "Checked",
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = "Save card details for future payments",
                    fontSize = 16.sp,
                    color = Color(0xFF808080)
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Bottom Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.padding(start = 7.dp)
                ) {
                    Text(
                        text = "Total price",
                        fontSize = 16.sp,
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$18.19",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                
                Button(
                    onClick = { /* Handle payment */ },
                    modifier = Modifier
                        .width(209.dp)
                        .height(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3C2F2F)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Pay Now",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun OrderDetailsSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OrderDetailRow("Order", "$16.48")
        Spacer(modifier = Modifier.height(37.dp))
        OrderDetailRow("Taxes", "$0.3")
        Spacer(modifier = Modifier.height(37.dp))
        OrderDetailRow("Delivery fees", "$1.5")
        
        Spacer(modifier = Modifier.height(44.dp))
        
        // Total row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3C2F2F),
                modifier = Modifier.padding(start = 24.dp)
            )
            Text(
                text = "$18.19",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3C2F2F),
                modifier = Modifier.padding(end = 24.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(47.dp))
        
        // Delivery time
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Estimated delivery time:",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3C2F2F),
                modifier = Modifier.padding(start = 24.dp)
            )
            Text(
                text = "15 - 30mins",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3C2F2F),
                modifier = Modifier.padding(end = 24.dp)
            )
        }
    }
}

@Composable
fun OrderDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color(0xFF7D7D7D),
            modifier = Modifier.padding(start = 24.dp)
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = Color(0xFF7D7D7D),
            modifier = Modifier.padding(end = 24.dp)
        )
    }
}

@Composable
fun PaymentMethodCard(
    title: String,
    cardNumber: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color,
    cardNumberColor: Color,
    cardIcon: androidx.compose.ui.graphics.painter.Painter,
    imageSize: DpSize
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 19.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = cardIcon,
                contentDescription = "Card icon",
                modifier = Modifier.size(imageSize)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = cardNumber,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = cardNumberColor
                )
            }
        }
        
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}