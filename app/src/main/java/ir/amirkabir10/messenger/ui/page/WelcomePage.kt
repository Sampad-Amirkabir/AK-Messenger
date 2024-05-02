package ir.amirkabir10.messenger.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.amirkabir10.messenger.R
import ir.amirkabir10.messenger.ui.theme.AKMessengerTheme
import ir.amirkabir10.messenger.ui.theme.Typography

@Composable
fun WelcomePage(navController: NavController) {
	Scaffold(
		modifier = Modifier.fillMaxSize()
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
				.padding(12.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceBetween
		) {
			Text(
				text = "Welcome to AK-Messenger",
				style = Typography.displayMedium,
				textAlign = TextAlign.Center
			)
			
			Icon(
				painterResource(R.drawable.messenger),
				"",
				modifier = Modifier.size(200.dp),
			)
			
			Button(
				modifier = Modifier.fillMaxWidth(),
				onClick = { navController.navigate("signup") }
			) {
				Text(
					text = "Next",
					style = Typography.titleLarge
				)
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
		WelcomePage(rememberNavController())
	}
}

@Preview
@Composable
private fun DarkPreview() {
	AKMessengerTheme(darkTheme = true) {
		WelcomePage(rememberNavController())
	}
}