package ir.amirkabir10.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComponent() {
	var n by remember { mutableIntStateOf(0) }
	var showDialog by remember { mutableStateOf(false) }
	
	if (showDialog) {
		Dialog(onDismissRequest = { showDialog = false }) {
			Surface(
				modifier = Modifier.clip(RoundedCornerShape(16.dp))
			) {
				Column(
					verticalArrangement = Arrangement.spacedBy(15.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.padding(16.dp)
				) {
					Text(text = "Are you sure to reset counter?")
					Row(
						horizontalArrangement = Arrangement.spacedBy(20.dp)
					) {
						Button(onClick = {
							n = 0
							showDialog = false
						}) {
							Text(text = "Yes", fontSize = 20.sp, fontWeight = FontWeight.Black)
						}
						Button(onClick = {
							showDialog = false
						}) {
							Text(text = "No", fontSize = 20.sp, fontWeight = FontWeight.Black)
						}
					}
				}
			}
		}
	}
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(text = "AK Messenger") },
				actions = {
					IconButton(onClick = { /*TODO*/ }) {
						Icon(Icons.TwoTone.Menu, "")
					}
				}
			)
		}
	) { contentPadding ->
		Surface(
			modifier = Modifier
				.fillMaxSize()
				.padding(contentPadding)
		) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				Text(text = "$n times clicked")
				
				Row(
					horizontalArrangement = Arrangement.spacedBy(16.dp)
				) {
					Button(onClick = { n++ }) {
						Text(text = "Add")
					}
					
					Button(onClick = {
						showDialog = true
					}) {
						Text(text = "reset")
					}
				}
			}
		}
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
