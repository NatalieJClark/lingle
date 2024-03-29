package com.example.lingle.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lingle.R
import com.example.lingle.composables.FinalCard
import com.example.lingle.composables.ItemCard
import com.example.lingle.composables.NewGameButton
import com.example.lingle.composables.NextButton
import com.example.lingle.composables.SubHeadingText
import com.example.lingle.ui.theme.DarkOrange
import com.example.lingle.ui.theme.DarkTurquoise
import com.example.lingle.ui.theme.LightOrange
import com.example.lingle.ui.theme.LightTurquoise
import com.example.lingle.utils.Item

@Composable
fun ItemScreen(
    category: String?,
    randomItems: ArrayList<Item>?,
    startColour: Color,
    endColour: Color,
    navController: NavHostController,
    modifier: Modifier = Modifier) {

//    Variable with mutable state to be remembered and tracked between screens
    var isFlipped by remember { mutableStateOf(false) }
    var currentItemIndex by remember { mutableIntStateOf(0) }
    var progress by remember { mutableFloatStateOf(0.2f) } // hold progress status to update progress bar

    Column(
        // Show screen background colour, according to selected category
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        startColour,
                        endColour
                    )
                )
            )
            .padding(20.dp)
    ) {
        randomItems?.let {
            if (it.isNotEmpty()) {
//                Show all item cards
                if (currentItemIndex < randomItems.size) { // Checks if there are cards left in the series
//                    if they get past the final item in series, then the else block is invoked
                    Spacer(modifier = modifier
                        .weight(0.5f)
                    )
                    if (category != null) {
                        SubHeadingText(
                            text = category,
                            modifier = modifier
                                .weight(1f)
                        )
                    }
                    // progress bar updates with every new item
                    LinearProgressIndicator(
                        progress = progress,
                        color = DarkTurquoise,
                        trackColor = LightTurquoise,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = modifier
                        .weight(0.5f)
                    )
                    //  Show item card for each item in game
                    ItemCard(
                        name = it[currentItemIndex].name,
                        image = it[currentItemIndex].imgUrl,
                        modifier = modifier.weight(8f),
                        isFlipped = isFlipped,
                        onCardClick = {
                        isFlipped = !isFlipped// Can flip back and forth
                        }
                    )
                    Spacer(
                        modifier = modifier
                            .weight(0.5f)
                    )
                    if (isFlipped) {
//                        When card is flipped, NextButton is displayed,
//                        Click NextButton to move to next item in game
                        NextButton(
                            onButtonClick = {
                                progress += 0.2f // update progress value => updates progress bar on next item page
                                currentItemIndex += 1 // update currentItemIndex by 1
                                isFlipped = false // Reset back to false
                            }
                        )
                    } else { // Once all cards have been viewed, show final card with summary
                        Text(
                            text = stringResource(id = R.string.tap_card),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                    Spacer(
                        modifier = modifier
                            .weight(0.5f)
                    )
                }
                //  At the end of the game, show the final card and new game button
                else {
                    Spacer(modifier = modifier
                        .weight(0.5f)
                    )
                    SubHeadingText(
                        text = stringResource(id = R.string.final_message),
                        modifier = modifier.weight(1f)
                    )
                    Spacer(modifier = modifier
                        .weight(0.5f)
                    )
                    FinalCard(
                        itemList = randomItems,
                        modifier = modifier.weight(8f)
                    )
                    Spacer(modifier = modifier
                        .weight(0.5f)
                    )
//                    Regenerates a new game with same category
                    NewGameButton(onClick = { navController.navigate("item/${category}") }, modifier = modifier.weight(1f))
                    Spacer(modifier = modifier
                        .weight(0.5f)
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Item Screen"
)
@Composable
fun ItemScreenPreview() {
    val navController = rememberNavController() 
    val items = arrayListOf(
        Item("Apple", "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/1_kjyk3h.png"),
        Item("Pear", "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/5_qbaizz.png"),
        Item("Orange", "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/2_ymvg5d.png"),
        Item("Strawberry", "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/3_kiv5du.png"),
        Item("Banana", "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/4_w6aicq.png"),
    )
    ItemScreen(
        category = "Fruits",
        randomItems = items,
        startColour = LightOrange,
        endColour = DarkOrange,
        navController = navController,
        modifier = Modifier
    )
}
