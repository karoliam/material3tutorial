package com.karoliina.material3tutorial

import android.content.res.Configuration
import androidx.compose.foundation.Image
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karoliina.material3tutorial.ui.theme.Material3tutorialTheme
import com.karoliina.material3tutorial.ui.theme.md_theme_light_onPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3tutorialTheme {
//                Surface(modifier =  Modifier.fillMaxSize()) {
//
//                }
//                MessageCard(Message("Android", "Jetpack Compose"))
                Conversation(SampleData.conversationSample)
            }
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conversation(messages: List<Message>) {
    val contextForToast = LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cherry Blossoms",
                    )
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Menu, null)
                    }
                }, actions = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Favorite, null,
                        modifier = Modifier.padding(end = 8.dp))
                    }
                },

            )
        }

    ) { contentPadding ->



        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)) {
            LazyColumn {
                items(messages) { message ->
                    MessageCard(message)
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
                onClick = {
                    Toast.makeText(contextForToast, "Click", Toast.LENGTH_SHORT)
                        .show()
                }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    Material3tutorialTheme() {
        Conversation(SampleData.conversationSample)
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {

    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded by remember { mutableStateOf(false) }
    // surfaceColor will be updated gradually from one color to the other
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
    )

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ){
        // We toggle the isExpanded variable when we click on this Column
        Card(modifier = Modifier
            .fillMaxSize()
            .clickable { isExpanded = !isExpanded }
            .padding(all = 16.dp)
        ) {
//        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }
//            .weight(0.5f)) {
            Image(
                painter = painterResource(R.drawable.cherry_blossoms),
                contentDescription = "Card picture",
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.width(8.dp))

            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(R.drawable.profile_picture),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                )
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp)

            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    style = MaterialTheme.typography.bodyMedium,
                )
                Button(onClick = {
                    Log.d("click", "Button clicked")
                },
                    colors = ButtonDefaults.buttonColors(md_theme_light_onPrimary),



                ) {
                    Icon(Icons.Filled.Favorite,
                        contentDescription = "Favourite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Like",
                        color = MaterialTheme.colorScheme.primary)

                }
            }

        }

    }



}



object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Colleague",
            "Test...Test...Test..."
        ),
        Message(
            "Colleague",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n"
        ),
        Message(
            "Colleague",
            "I think Kotlin is my favorite programming language.\n" +
                    "It's so much fun!"
        ),
        Message(
            "Colleague",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "It's the Android's modern toolkit for building native UI." +
                    "It simplifies and accelerates UI development on Android." +
                    "Less code, powerful tools, and intuitive Kotlin APIs :)"
        ),
        Message(
            "Colleague",
            "It's available from API 21+ :)"
        ),
        Message(
            "Colleague",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Colleague",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Colleague",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Colleague",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Colleague",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Colleague",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Colleague",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)@Composable
fun PreviewMessageCard()  {
    Material3tutorialTheme() {
        Surface() {
            MessageCard(
                msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }

}
