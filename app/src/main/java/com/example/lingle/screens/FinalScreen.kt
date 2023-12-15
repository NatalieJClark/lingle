package com.example.lingle.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingle.Item
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lingle.R
import com.example.lingle.composables.FinalCard
import com.example.lingle.composables.NewGameButton
import com.example.lingle.composables.SubHeadingText
import com.example.lingle.ui.theme.DarkOrange
import com.example.lingle.ui.theme.LightOrange


// Layout of the final screen of the game
@Composable
fun FinalScreenLayout(startColour: Color, endColour: Color, navController: NavHostController, modifier: Modifier = Modifier) {

//    val fakeItemsList = listOf("Apple", "Banana", "Orange", "Pear", "Kiwi")

    val items = arrayListOf(
        Item(
            "Apple",
            "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/1_kjyk3h.png"
        ),
        Item(
            "Pear",
            "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/5_qbaizz.png"
        ),
        Item(
            "Orange",
            "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/2_ymvg5d.png"
        ),
        Item(
            "Strawberry",
            "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/3_kiv5du.png"
        ),
        Item(
            "Banana",
            "https://res.cloudinary.com/dqgeypwaa/image/upload/v1702393272/4_w6aicq.png"
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        startColour,
                        endColour
                    )
                )
            )
            .padding(top = 80.dp)
            .fillMaxSize()
    ) {
        SubHeadingText(
            text = stringResource(id = R.string.final_message),
            modifier = modifier.weight(1f)
        )
        FinalCard(
            itemList = items,
            modifier = modifier.weight(4f)
        )
        Spacer(modifier = modifier
            .weight(0.5f)
        )
        NewGameButton(modifier = modifier.weight(0.6f))
        Spacer(modifier = modifier
            .weight(0.5f)
        )
    }

}


// Fake navbar as a placeholder
@Composable
fun FakeNavBar(modifier: Modifier = Modifier) {

    Row {
        Text(
            text = "Lingle",
            fontSize = 40.sp,
            modifier = modifier.weight(1f)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.weight(1f)
        ) {

        }
    }
}



@Preview(showBackground = true)
@Composable
fun FinalScreenPreview() {
    val navController = rememberNavController()
    FinalScreenLayout(
        navController = navController,
        startColour = LightOrange,
        endColour = DarkOrange
    )
}