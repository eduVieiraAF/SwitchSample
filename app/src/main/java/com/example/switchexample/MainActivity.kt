package com.example.switchexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.switchexample.ui.theme.SwitchExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitchExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SwitchSample()
                }
            }
        }
    }
}

@Composable
fun SwitchSample() {
    val isChecked = remember { mutableStateOf(true) }
    val labelText = remember { mutableStateOf("Image is showing") }
    val isVisible = remember { mutableStateOf(1F) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(50.dp))

        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.Black,
                checkedTrackColor = Color.White,
                uncheckedTrackColor = Color.LightGray,
                checkedBorderColor = MaterialTheme.colorScheme.primary,
                uncheckedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.size(30.dp))

        if (isChecked.value) {
            labelText.value = "Image is showing"
            isVisible.value = 1F
        } else {
            labelText.value = "Image is not showing"
            isVisible.value = 0F
        }

        Image(
            painter = painterResource(id = R.drawable.alien_pet),
            contentDescription = "Alien Pet",
            modifier = Modifier.size(300.dp).alpha(isVisible.value),
            contentScale = androidx.compose.ui.layout.ContentScale.Fit,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(45.dp))

        Text(
            text = labelText.value,
            color = if (isChecked.value) MaterialTheme.colorScheme.primary else Color.Red,
            fontSize = 20.sp,
            fontWeight = if (isChecked.value) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwitchExampleTheme {
        SwitchSample()
    }
}