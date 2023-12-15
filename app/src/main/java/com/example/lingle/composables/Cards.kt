package com.example.lingle.composables

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.lingle.R
import com.example.lingle.ui.theme.LingleTheme

@Composable
fun ItemCard(name: String, image: String, modifier: Modifier = Modifier) {
//    var isClicked by remember { mutableStateOf(false) }
    OutlinedCard(
//        onClick = {isClicked = !isClicked},
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        border = BorderStroke(4.dp, Color.Black),
        modifier = modifier
//            .clickable { isClicked = !isClicked }
            .fillMaxWidth()
            .height(500.dp)
            .size(width = 240.dp, height = 100.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$name",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(10.dp)
            )
            AsyncImage(
                model = image,
                contentDescription = name,
            )
            val soundImage = painterResource(R.drawable.voice)
            Image(
                painter = soundImage,
                contentDescription = "Volume",
                modifier = Modifier
                    .size(50.dp)
            )
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
fun HomePageCards (
    text: String,
    color: Color,
    picture: Painter,
    navController: NavHostController,
    modifier: Modifier = Modifier) {

    Card(onClick = { navController.navigate("item/${text}") },
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

// @Preview(showBackground = true)
// @Composable
// fun CardsPreview() {
//     LingleTheme {
//         ItemCard(name = "Apple")
//     }
// }


 @Preview(showBackground = true)
 @Composable
 fun HomePageCardsPreview() {
     val navController = rememberNavController()
     LingleTheme {
         HomePageCards("HELLO ANDROID!", color = Color.Red, picture = painterResource(id = R.drawable.fruits), navController = navController) }
 }

