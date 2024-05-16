package ir.amirkabir10.messenger.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowLeft
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material.icons.rounded.Attachment
import androidx.compose.material.icons.rounded.DensityMedium
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.SentimentSatisfied
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.amirkabir10.messenger.R
import ir.amirkabir10.messenger.ui.theme.AKMessengerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPage() {
	var message by remember { mutableStateOf("") }
	val messageList = remember { mutableStateListOf<String>("Hello World Some Text\nLine 2 with more text") }
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Row(
						verticalAlignment = Alignment.CenterVertically
					) {
						Image(
							painterResource(R.drawable.profile),
							"",
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.size(52.dp)
								.clip(CircleShape)
						)
						Spacer(modifier = Modifier.width(16.dp))
						Text("Amir Ali")
					}
				},
				navigationIcon = {
					IconButton(onClick = {}) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, "")
					}
				},
				actions = {
					IconButton(onClick = { /*TODO*/ }) {
						Icon(Icons.Rounded.MoreVert, "")
					}
				},
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = Color(0xFF006064)
				)
			)
		}
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.imePadding()
		) {
			LazyColumn(
				modifier = Modifier
					.fillMaxSize()
					.weight(1f)
					.padding(10.dp),
				reverseLayout = true,
				verticalArrangement = Arrangement.spacedBy(10.dp)
			) {
				items(messageList.asReversed()) {
					Text(
						text = it,
						modifier = Modifier
							.clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp))
							.background(
								Brush.linearGradient(
									0f to Color(0xFF0D47A1),
									1f to Color(0xFF1A237E)
								)
							)
							.padding(10.dp)
					)
				}
			}
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
					.background(Color(0xFF252525))
			) {
				IconButton(onClick = { /*TODO*/ }) {
					Icon(Icons.Rounded.SentimentSatisfied, "")
				}
				OutlinedTextField(
					value = message,
					onValueChange = { message = it },
					modifier = Modifier.weight(1f),
					colors = OutlinedTextFieldDefaults.colors(
						unfocusedBorderColor = Color.Transparent,
						focusedBorderColor = Color.Transparent
					)
				)
				IconButton(onClick = { /*TODO*/ }) {
					Icon(Icons.Rounded.Attachment, "")
				}
				IconButton(onClick = {
					if (message.isBlank())
						return@IconButton
					messageList.add(message)
					message = ""
				}) {
					Icon(Icons.AutoMirrored.Rounded.Send, "")
				}
			}
		}
	}
}

@Preview
@Composable
private fun LightTheme() {
	AKMessengerTheme(darkTheme = false) {
		ChatPage()
	}
}

@Preview
@Composable
private fun DarkTheme() {
	AKMessengerTheme(darkTheme = true) {
		ChatPage()
	}
}
