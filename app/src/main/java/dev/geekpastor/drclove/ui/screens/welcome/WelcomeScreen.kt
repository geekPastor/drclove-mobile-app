package dev.geekpastor.drclove.ui.screens.welcome

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.geekpastor.drclove.R
import dev.geekpastor.drclove.ui.theme.DrcLoveTheme

@Composable
fun WelcomeRoute(
    navigateToLogin: () -> Unit,
){
    Welcome(
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun Welcome(
    navigateToLogin: () -> Unit = {}
) {

    // --- ANIMATION STATES ---
    val logoScale = remember { Animatable(0f) }
    val textScale = remember { Animatable(0f) }
    val circleScale = remember { Animatable(0f) }
    val bottomOffset = remember { Animatable(200f) } // 200dp vers le bas

    // --- LAUNCH ALL ANIMATIONS ---
    LaunchedEffect(Unit) {
        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
        )

        textScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
        )

        circleScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
        )

        bottomOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // BACKGROUND IMAGE
        Image(
            painter = painterResource(id = R.drawable.bg_welcome),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // GRADIENT OVERLAY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFA1292).copy(0.6f),
                            Color(0xFF53093B),
                        )
                    )
                )
        )

        // --- MAIN CONTENT ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(40.dp))

            // LOGO ANIMÉ
            Image(
                painter = painterResource(id = R.drawable.ic_drc_love_logo_white),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(200.dp)
                    .scale(logoScale.value)
            )

            Spacer(Modifier.height(16.dp))

            // TEXTE ANIMÉ
            Text(
                text = "Rencontrer votre âme soeur\nConstruisez ensemble",
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .scale(textScale.value),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(Modifier.height(150.dp))

            // --- PROFILE CIRCLE WITH RINGS (ANIMÉ) ---
            Box(
                modifier = Modifier
                    .size(260.dp)
                    .scale(circleScale.value),
                contentAlignment = Alignment.Center
            ) {

                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.White.copy(alpha = 0.10f),
                        radius = size.minDimension / 2
                    )
                    drawCircle(
                        color = Color.White.copy(alpha = 0.20f),
                        radius = size.minDimension / 2.5f
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.central_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(10.dp, Color.White, CircleShape)
                )

                SmallAvatar(painterResource(R.drawable.avatar_1), Modifier.align(Alignment.TopCenter))
                SmallAvatar(painterResource(R.drawable.avatar_2), Modifier.align(Alignment.CenterStart))
                SmallAvatar(painterResource(R.drawable.avatar_3), Modifier.align(Alignment.CenterEnd))
                SmallAvatar(painterResource(R.drawable.avatar_4), Modifier.align(Alignment.BottomStart))
                SmallAvatar(painterResource(R.drawable.avatar_5), Modifier.align(Alignment.BottomEnd))
            }

            Spacer(Modifier.height(100.dp))

            // --- BUTTON ANIMÉ (SLIDE UP) ---
            Column(
                modifier = Modifier.offset(y = bottomOffset.value.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = navigateToLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF00204A)
                    )
                ) {
                    Text(
                        text = "Commencer Maintenant",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Découvrez l’amour comme jamais auparavant",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun SmallAvatar(image: Painter, modifier: Modifier) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
    )
}

@Preview
@Composable
fun WelcomePreview(){
    DrcLoveTheme {
        Welcome()
    }
}