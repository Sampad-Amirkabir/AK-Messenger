package ir.amirkabir10.messenger.ui.page

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import ir.amirkabir10.messenger.ui.theme.AKMessengerTheme
import ir.amirkabir10.messenger.ui.theme.Typography

@Composable
fun SignupPage(navController: NavController) {
	var dialogShown by remember { mutableStateOf(false) }
	var username by remember { mutableStateOf("") }
	var avatar by remember { mutableStateOf(null as Uri?) }
	var avatarBitmap by remember { mutableStateOf(null as Bitmap?) }
	val avatarSelectorLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
		avatar = it
		avatarBitmap = null
	}
	val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
		avatarBitmap = it
		avatar = null
	}
	
	if (dialogShown) {
		Dialog(onDismissRequest = { dialogShown = false }) {
			Surface(
				modifier = Modifier.clip(RoundedCornerShape(16.dp))
			) {
				Column(
					modifier = Modifier.padding(10.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text("Choose Avatar", style = Typography.titleLarge)
					
					Row {
						TextButton(onClick = {
							avatarSelectorLauncher.launch("image/*")
							dialogShown = false
						}) {
							Text("From Gallery", fontSize = 18.sp)
						}
						
						TextButton(onClick = {
							cameraLauncher.launch(null)
							dialogShown = false
						}) {
							Text("Take Picture", fontSize = 18.sp)
						}
					}
				}
			}
		}
	}
	
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
			
			var nameHasError by remember { mutableStateOf(false) }
			OutlinedTextField(
				value = username,
				onValueChange = {
					username = it
					if (nameHasError && it.isNotBlank())
						nameHasError = false
				},
				placeholder = { Text(text = "Nick Name") },
				label = { Text(text = "Nick Name") },
				isError = nameHasError,
				supportingText = {
					AnimatedVisibility(
						visible = nameHasError,
						enter = slideInVertically { -it },
						exit = slideOutVertically { -it }
					) {
						Text("Nick Name must not be empty")
					}
				}
			)
			
			Spacer(modifier = Modifier.weight(1f))
			
			Text(
				text = "Choose your avatar:",
				style = Typography.titleLarge
			)
			
			Spacer(modifier = Modifier.height(16.dp))
			
			when {
				avatar != null -> {
					Box {
						Image(
							rememberAsyncImagePainter(avatar),
							"",
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.size(100.dp)
								.border(3.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
								.clip(CircleShape)
						)
						Icon(
							Icons.Rounded.Edit,
							"",
							modifier = Modifier
								.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f), CircleShape)
								.padding(4.dp)
								.align(Alignment.BottomEnd)
								.clickable(
									interactionSource = remember { MutableInteractionSource() },
									indication = rememberRipple(false),
									onClick = { dialogShown = true }
								)
						)
					}
				}
				
				avatarBitmap != null -> {
					Box {
						Image(
							avatarBitmap!!.asImageBitmap(),
							"",
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.size(100.dp)
								.border(3.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
								.clip(CircleShape)
						)
						Icon(
							Icons.Rounded.Edit,
							"",
							modifier = Modifier
								.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f), CircleShape)
								.padding(4.dp)
								.align(Alignment.BottomEnd)
								.clickable(
									interactionSource = remember { MutableInteractionSource() },
									indication = rememberRipple(false),
									onClick = { dialogShown = true }
								)
						)
					}
				}
				
				else -> {
					Icon(
						Icons.Rounded.AddPhotoAlternate,
						"",
						modifier = Modifier
							.size(100.dp)
							.border(3.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
							.padding(16.dp)
							.clickable(
								interactionSource = remember { MutableInteractionSource() },
								indication = rememberRipple(false),
								onClick = { dialogShown = true }
							)
					)
				}
			}
			
			Spacer(modifier = Modifier.weight(1f))
			
			Button(
				modifier = Modifier.fillMaxWidth(),
				onClick = {
					if (username.isBlank()) {
						nameHasError = true
						return@Button
					}
					
					navController.navigate("chat")
				}
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
		SignupPage(rememberNavController())
	}
}

@Preview
@Composable
private fun DarkPreview() {
	AKMessengerTheme(darkTheme = true) {
		SignupPage(rememberNavController())
	}
}
