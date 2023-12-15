package com.example.lingle.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingle.R
import com.example.lingle.ui.theme.LingleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(name: String, modifier: Modifier = Modifier, onCardFlipped: () -> Boolean ) {

    var isFlipped by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density


    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        ), label = "Card Flip Animation"
    )
    OutlinedCard(
        onClick = {
            isFlipped = !isFlipped
            onCardFlipped
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        border = BorderStroke(4.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .size(width = 240.dp, height = 100.dp)
            .graphicsLayer(
                rotationY = rotationY,
                cameraDistance = 8 * density
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isFlipped) {
                Text(
                    text = "$name",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                        .graphicsLayer(
                            rotationY = 180f
                        )
                )
                val appleImage = painterResource(R.drawable.apple)
                Image(
                    painter = appleImage,
                    contentDescription = "Apple",
                    modifier = modifier
                        .graphicsLayer(
                            rotationY = 180f
                        )
                )

                val soundImage = painterResource(R.drawable.voice)
                Image(
                    painter = soundImage,
                    contentDescription = "Volume",
                    modifier = Modifier
                        .size(50.dp)
                        .graphicsLayer(
                            rotationY = 180f
                        )
                )

            } else {
                Text(
                    text = "Guess what this is?",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)

                )
                val appleImage = painterResource(R.drawable.apple)
                Image(
                    painter = appleImage,
                    contentDescription = "Apple"
                )
            }

        }
    }
}

// Card to list all items viewed in current game
@Composable
fun FinalCard(itemList: List<String>, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(3.dp, Color.Black),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        itemList.forEach {
            Text(
                text = it,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .weight(1f)            
            )
          }    
      }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageCards (text: String, color: Color, picture: Painter, modifier: Modifier = Modifier) {

    Card(onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(color),
        border = BorderStroke(3.dp, Color.White),
        elevation = CardDefaults.cardElevation(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)

    ){
        Column (
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = text,
                modifier = modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Image(
                painter = picture,
                contentDescription = "quiz logo",
                modifier = modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun CardPreview() {
//    FinalCard(listOf("Apple", "Banana", "Orange", "Pear", "Kiwi"))
//}

 @Preview(showBackground = true)
 @Composable
 fun CardsPreview() {
     LingleTheme {
         ItemCard(name = "Apple", onCardFlipped = {false})
     }
 }


// @Preview(showBackground = true)
// @Composable
// fun HomePageCardsPreview() {
//     LingleTheme {
//         HomePageCards("HELLO ANDROID!", color = Color.Red, picture = painterResource(id = R.drawable.fruits)) }
// }

