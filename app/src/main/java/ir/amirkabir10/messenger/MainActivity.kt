package ir.amirkabir10.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.amirkabir10.messenger.ui.page.SignupPage
import ir.amirkabir10.messenger.ui.page.WelcomePage
import ir.amirkabir10.messenger.ui.theme.AKMessengerTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			AKMessengerTheme {
				MainComponent()
			}
		}
	}
}

@Composable
fun MainComponent() {
	val navController = rememberNavController()
	
	NavHost(navController = navController, startDestination = "welcome") {
		composable(
			"welcome",
			enterTransition = { slideInHorizontally { -it } },
			exitTransition = { slideOutHorizontally { -it } }
		) { WelcomePage(navController) }
		
		composable(
			"signup",
			enterTransition = { slideInHorizontally { it } },
			exitTransition = { slideOutHorizontally { it } }
		) { SignupPage() }
	}
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
	AKMessengerTheme(darkTheme = false) {
		MainComponent()
	}
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
	AKMessengerTheme(darkTheme = true) {
		MainComponent()
	}
}
