package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            PreviewMessageCard()
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
                    PreviewConversation()
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.icons8_person_80),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 3.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }) {
                Text(
                    message.body,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }


}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Surface {
//            MessageCard(
//                message = Message(
//                    "Mozes", "Look! I'm learning Compose \n" +
//                            "While working on a design for Lokolingo \n" +
//                            "Isn't this exciting!!!!\n" +
//                            "I find it exciting!"
//                )
//            )
            Conversation(messages = SampleData.conversationSample)
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages.size) { message ->
            MessageCard(message = messages[message])
        }
    }
}


data class Message(val author: String, val body: String)

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposeTutorialTheme {
//        Greeting("Android")
//    }
//}