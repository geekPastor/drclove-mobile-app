package dev.geekpastor.drclove.ui.screens.onboarding

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.geekpastor.drclove.model.data.OnBoardingPage
import dev.geekpastor.drclove.R
import kotlinx.coroutines.launch


@Composable
fun OnBoardingRoute(
    navigateToLogin: () -> Unit = {},
){
    OnBoardingScreen(
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun OnBoardingScreen(
    navigateToLogin: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val pages = remember {
        listOf(
            OnBoardingPage(
                logo = R.drawable.splash_logo,
                "Swiper à gauche ou à droite pour aimer ou sauter un profil",
                listOf(R.drawable.ob1_a, R.drawable.ob1_b, R.drawable.ob2_a)
            ),
            OnBoardingPage(
                logo = R.drawable.splash_logo,
                "Swiper à gauche ou à droite pour aimer ou sauter un profil",
                listOf(R.drawable.ob1_b, R.drawable.ob2_a, R.drawable.ob3_b)
            ),
            OnBoardingPage(
                logo = R.drawable.splash_logo,
                description = "Quand il y a match, vous pouvez\nCommencer la discussion",
                images = listOf(
                    R.drawable.ob2_a,
                    R.drawable.ob3_b,
                    R.drawable.ic_hearts,     // icône superposée
                    R.drawable.ic_message   // icône superposée
                ),
                isLast = true
            )
        )
    }

    val pagerState = rememberPagerState(pageCount = { pages.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.White, Color(0xFFF8D9EC))
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            // ------ TEXTS ------
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(200.dp) // fade-in
            )

            Text(
                text = pages[pagerState.currentPage].description,
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF222222),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ------ IMAGES ------
            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->

                val p = pages[page]

                if (!p.isLast){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        // ---- IMAGE GAUCHE (dépassant à moitié) ----
                        Image(
                            painter = painterResource(id = p.images[0]),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(x = (-30).dp) // dépassement
                                .width(100.dp)
                                .height(250.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .align(Alignment.CenterStart),
                            contentScale = ContentScale.Crop
                        )

                        // ---- IMAGE PRINCIPALE ----
                        Image(
                            painter = painterResource(id = p.images[1]),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .height(300.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop
                        )

                        // ---- IMAGE DROITE (dépassant à moitié) ----
                        Image(
                            painter = painterResource(id = p.images[2]),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(x = (30).dp) // dépassement
                                .width(100.dp)
                                .height(250.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .align(Alignment.CenterEnd),
                            contentScale = ContentScale.Crop
                        )
                    }
                }else{
                    LastOnBoardingPage(p)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ------ DOT INDICATOR ------
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (pagerState.currentPage == index) 10.dp else 8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (pagerState.currentPage == index)
                                    Color(0xFFEB1589)
                                else
                                    Color(0x55EB1589)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ------ BUTTONS RETOUR / SUIVANT ------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Retour
                Button(
                    onClick = {
                        if (pagerState.currentPage > 0) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text("Retour", color = Color.Black)
                }

                // Suivant / Terminer
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFE01890),
                                    Color(0xFFE07CC4),
                                    Color(0xFFB30FA8)
                                )
                            ),
                            shape = RoundedCornerShape(40.dp)
                        )
                        .clickable {
                            if (pagerState.currentPage < pages.size - 1) {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            } else {
                                navigateToLogin()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.lastIndex) "Terminer" else "Suivant",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LastOnBoardingPage(page: OnBoardingPage) {

    val OvershootPop = Easing { fraction ->
        // Effet overshoot style "pop"
        val tension = 2.2f
        val t = fraction - 1f
        t * t * ((tension + 1) * t + tension) + 1f
    }

    // --- Animation trigger ---
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    // --- Animations images ---
    val leftRotation by animateFloatAsState(
        targetValue = if (startAnimation) -12f else 0f,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    val rightRotation by animateFloatAsState(
        targetValue = if (startAnimation) 12f else 0f,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    val leftOffsetX by animateDpAsState(
        targetValue = if (startAnimation) (-60).dp else 0.dp,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    val rightOffsetX by animateDpAsState(
        targetValue = if (startAnimation) (60).dp else 0.dp,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    val rightOffsetY by animateDpAsState(
        targetValue = if (startAnimation) 80.dp else 0.dp,
        animationSpec = tween(900, easing = FastOutSlowInEasing)
    )

    // --- Animations icônes ---
    val loveScale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(600, delayMillis = 600, easing = OvershootPop)
    )

    val messageScale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(600, delayMillis = 700, easing = OvershootPop)
    )


    // ==== UI ====
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                // --- IMAGE DROITE ---
                Image(
                    painter = painterResource(id = page.images[1]),
                    contentDescription = null,
                    modifier = Modifier
                        .height(260.dp)
                        .width(160.dp)
                        .offset(x = rightOffsetX, y = rightOffsetY)
                        .graphicsLayer {
                            rotationZ = rightRotation
                        }
                        .clip(RoundedCornerShape(26.dp))
                        .border(3.dp, Color.White, RoundedCornerShape(26.dp)),
                    contentScale = ContentScale.Crop
                )

                // --- IMAGE GAUCHE ---
                Image(
                    painter = painterResource(id = page.images[0]),
                    contentDescription = null,
                    modifier = Modifier
                        .height(260.dp)
                        .width(160.dp)
                        .offset(x = leftOffsetX)
                        .graphicsLayer {
                            rotationZ = leftRotation
                        }
                        .clip(RoundedCornerShape(26.dp))
                        .border(3.dp, Color.White, RoundedCornerShape(26.dp)),
                    contentScale = ContentScale.Crop
                )

                // --- Icône LOVE (pop) ---
                Image(
                    painter = painterResource(id = page.images[2]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(55.dp * loveScale)   // effet POP
                        .align(Alignment.Center)
                        .offset(x = 15.dp, y = (-60).dp)
                )

                // --- Icône MESSAGE (pop) ---
                Image(
                    painter = painterResource(id = page.images[3]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp * messageScale)
                        .align(Alignment.Center)
                        .offset(x = (-50).dp, y = 150.dp)
                )
            }
        }
    }
}
