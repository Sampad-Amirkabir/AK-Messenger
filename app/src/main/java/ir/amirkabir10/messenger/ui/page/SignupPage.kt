package ir.amirkabir10.messenger.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.amirkabir10.messenger.ui.theme.AKMessengerTheme
import ir.amirkabir10.messenger.ui.theme.Typography

@Composable
fun SignupPage() {
	var username by remember { mutableStateOf("") }
	
	Scaffold(
		modifier = Modifier.fillMaxSize()
	) { contentPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(contentPadding)
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Spacer(modifier = Modifier.weight(1f))
			
			Text(
				text = "Enter your name:",
				style = Typography.titleLarge
			)
			
			OutlinedTextField(
				value = username,
				onValueChange = { username = it },
				placeholder = { Text(text = "Nick Name") },
				label = { Text(text = "Nick Name") }
			)
			
			Spacer(modifier = Modifier.weight(1f))
			
			Button(
				modifier = Modifier.fillMaxWidth(),
				onClick = { /*TODO*/ }
			) {
				Text(text = "Start", style = Typography.titleLarge)
				Spacer(modifier = Modifier.width(8.dp))
				Icon(Icons.AutoMirrored.Rounded.ArrowForwardIos, "")
			}
		}
	}
}

@Preview
@Composable
private fun LightPreview() {
	AKMessengerTheme(darkTheme = false) {
		SignupPage()
	}
}

@Preview
@Composable
private fun DarkPreview() {
	AKMessengerTheme(darkTheme = true) {
		SignupPage()
	}
}