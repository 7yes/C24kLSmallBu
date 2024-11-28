package com.jesse.c24lksmall.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jesse.c24lksmall.domain.model.SampleModel
import com.jesse.c24lksmall.presentation.uistate.UIState
import com.jesse.c24lksmall.ui.theme.C24LkSmallTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            C24LkSmallTheme {
                InitialScreen()
            }
        }
    }
}

@Composable
fun InitialScreen() {
    val navController = rememberNavController()
    lateinit var itemSelected: SampleModel
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(){
                navController.navigate("detail")
                itemSelected = it
            }
        }
        composable("detail") {
            DetailScreen(itemSelected)
        }
    }
}

@Composable
fun HomeScreen(viewModel: SampleViewModel = hiltViewModel(), onClickListener: (SampleModel) -> Unit) {
    val state = viewModel.uiState.collectAsState()
    when (state.value) {
        is UIState.Error -> {
            ErrorScreen(error = (state.value as UIState.Error).error)
        }

        UIState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    strokeWidth = 10.dp,
                    color = Color.Blue
                )
            }
        }

        is UIState.Success -> {
            val myList = (state.value as UIState.Success).mySuccessList
            LazyColumn(Modifier.fillMaxSize()) {
                items(myList.size) {
                    ItemView(myList[it], onClickListener)
                }
            }
        }
    }
}

@Composable
fun ItemView(item: SampleModel, onClickListener: (SampleModel) -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .padding(24.dp)
            .clip(RoundedCornerShape(24))
            .border(2.dp, Color.Green, shape = RoundedCornerShape(0, 24, 0, 24))
            .fillMaxWidth()
            .clickable { onClickListener(item) }
            .height(250.dp)
    ) {
        AsyncImage(
            model = item.image, modifier = Modifier.fillMaxWidth(),
            contentDescription = "Image", alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        MyText(item.name.toString())
    }
}

@Composable
fun MyText(
    text: String = "",
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Black
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor.copy(alpha = 0.5f)),
        color = textColor, textAlign = TextAlign.Center, maxLines = 1,
        text = text, fontSize = 26.sp
    )
}

@Composable
fun ErrorScreen(error: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = error)
    }
}

@Composable
fun DetailScreen(item: SampleModel) {
    Column(Modifier.fillMaxSize().padding(24.dp)){
        AsyncImage(
            model = item.image, modifier = Modifier.fillMaxWidth().height(350.dp),
            contentDescription = "Image", alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        MyText("Name: ${item.name}", textColor = Color.Black, backgroundColor = Color.Yellow)
        MyText("First Episode: ${item.firstEpisode}",textColor = Color.Black, backgroundColor = Color.Yellow)
    }

}
