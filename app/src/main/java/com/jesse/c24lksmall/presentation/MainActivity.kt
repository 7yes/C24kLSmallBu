package com.jesse.c24lksmall.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jesse.c24lksmall.domain.model.SampleModel
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

    @Composable
    fun InitialScreen() {
        val navController = rememberNavController()
        val context = LocalContext.current
        var lastItem: SampleModel? = null
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen() {
                    lastItem = it
                    navController.navigate("details")
                }
            }
            composable("details") { DetailsScreen(lastItem!!) }
        }
    }

    @Composable
    fun HomeScreen(
        viewmodel: SampleViewModel = hiltViewModel(),
        onClickListener: (SampleModel) -> Unit
    ) {
        viewmodel.getData()
        val data = viewmodel.myData.observeAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(data.value.size) {
                ItemView(data.value[it], onClickListener)
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
                .clickable {
                    onClickListener(item)
                }
                .height(250.dp)
        ) {
            AsyncImage(
                model = item.image,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Image",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds
            )
            MyText(item.name.toString())
        }
    }

    @Composable
    fun MyText(text: String = "") {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f)),
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            text = text,
            fontSize = 26.sp
        )
    }

    // adicional el composable de Detail
    @Composable
    fun DetailsScreen(item: SampleModel) {
        Box(
            modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = item.image,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                MyText(item.name.toString())
            }
        }
    }
}
