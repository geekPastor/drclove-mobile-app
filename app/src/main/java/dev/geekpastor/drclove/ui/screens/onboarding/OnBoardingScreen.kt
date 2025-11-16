package dev.geekpastor.drclove.ui.screens.onboarding

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

                // IMAGE DROITE (décalée vers la droite + descendue)
                Image(
                    painter = painterResource(id = page.images[1]),
                    contentDescription = null,
                    modifier = Modifier
                        .height(260.dp)
                        .width(160.dp)
                        .offset(
                            x = 60.dp,   // ➜ DÉCALAGE À DROITE
                            y = 80.dp    // ➜ DÉCALAGE EN BAS
                        )
                        .graphicsLayer {
                            rotationZ = 12f
                        }
                        .clip(RoundedCornerShape(26.dp))
                        .border(3.dp, Color.White, RoundedCornerShape(26.dp)),
                    contentScale = ContentScale.Crop
                )

                // IMAGE GAUCHE (décalée vers la gauche)
                Image(
                    painter = painterResource(id = page.images[0]),
                    contentDescription = null,
                    modifier = Modifier
                        .height(260.dp)
                        .width(160.dp)
                        .offset(x = (-60).dp)   // ➜ DÉCALAGE À GAUCHE
                        .graphicsLayer {
                            rotationZ = -12f
                        }
                        .clip(RoundedCornerShape(26.dp))
                        .border(3.dp, Color.White, RoundedCornerShape(26.dp)),
                    contentScale = ContentScale.Crop
                )

                // Icône "love"
                Image(
                    painter = painterResource(id = page.images[2]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(55.dp)
                        .align(Alignment.Center)
                        .offset(x = (15).dp, y = (-60).dp)
                )

                // Icône "message"
                Image(
                    painter = painterResource(id = page.images[3]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                        .offset(x = (-50).dp, y = (150).dp)
                )
            }
        }
    }
}
