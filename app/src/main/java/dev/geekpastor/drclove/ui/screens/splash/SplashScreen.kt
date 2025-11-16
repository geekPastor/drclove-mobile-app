package dev.geekpastor.drclove.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.geekpastor.drclove.R
import dev.geekpastor.drclove.ui.navigation.Destination
import dev.geekpastor.drclove.ui.theme.DrcLoveTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashRoute(navController: NavHostController) {
    SplashScreen(navController = navController)
}

@Composable
fun SplashScreen(navController: NavHostController) {
    Splash(navController = navController)
}

@Composable
fun Splash(navController: NavHostController) {
    // --- Animations ---
    val heartOffsetY = remember { Animatable(-200f) }
    val heartAlpha = remember { Animatable(0f) }

    val logoScale = remember { Animatable(0f) }
    val logoAlpha = remember { Animatable(0f) }

    val gradientOffsetY = remember { Animatable(300f) }
    val gradientAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Lancer plusieurs animations en parallèle
        launch {
            heartOffsetY.animateTo(0f, tween(1000))
        }
        launch {
            heartAlpha.animateTo(1f, tween(1200))
        }

        // logo après les cœurs
        delay(400)
        launch {
            logoScale.animateTo(1f, tween(1000))
        }
        launch {
            logoAlpha.animateTo(1f, tween(1000))
        }

        // dégradé après le logo
        delay(500)
        launch {
            gradientOffsetY.animateTo(0f, tween(1000))
        }
        launch {
            gradientAlpha.animateTo(1f, tween(1000))
        }

        // attendre avant navigation
        delay(1000)
        val destination = Destination.WelcomeScreen.route.name
        navController.navigate(destination) {
            popUpTo(Destination.SplashScreen.route.name) { inclusive = true }
        }
    }

    // --- Interface ---
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // --- Cœurs animés ---
        Image(
            painter = painterResource(id = R.drawable.ic_hearts),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 30.dp, y = (-30).dp + heartOffsetY.value.dp)
                .size(220.dp)
                .alpha(heartAlpha.value), // fade-in
            contentScale = ContentScale.Fit,
            alpha = 0.15f
        )

        // --- Dégradé animé ---
        Image(
            painter = painterResource(id = R.drawable.brush),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomStart)
                .offset(y = gradientOffsetY.value.dp)
                .alpha(gradientAlpha.value), // fade-in
            contentScale = ContentScale.Crop
        )

        // --- Logo + texte ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(200.dp)
                    .scale(logoScale.value)
                    .alpha(logoAlpha.value) // fade-in
            )
        }
    }
}

@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun SplashScreenPreview() {
    DrcLoveTheme {
        SplashScreen(navController = NavHostController(LocalContext.current))
    }
}
