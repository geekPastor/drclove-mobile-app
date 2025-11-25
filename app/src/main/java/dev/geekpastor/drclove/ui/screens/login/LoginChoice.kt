package dev.geekpastor.drclove.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
fun LoginChoiceRoute(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
){
    LoginChoiceScreen(
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick
    )
}

@Composable
fun LoginChoiceScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        // ---------- LOGO ----------
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier.width(100.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // ---------- WHITE CARD ----------
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            border = BorderStroke(1.dp, Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {

                // ---------- HEART + PHOTOS ----------
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    // Stroke Heart Icon
                    Image(
                        painter = painterResource(id = R.drawable.ic_hearts),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ---------- TITLE ----------
                Text(
                    text = "Ton voyage vers le bonheur commence ici.",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ---------- SUBTEXT ----------
                Text(
                    text = "Rejoins-nous pour découvrir ton partenaire idéal " +
                            "et débuter ensemble votre chemin vers la romance.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )

                Spacer(modifier = Modifier.height(28.dp))
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // ---------- LOGIN BUTTON ----------
        Box(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .height(52.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(40.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE01890),
                            Color(0xFFE07CC4),
                            Color(0xFFB30FA8)
                        )
                    )
                )
                .clickable { onLoginClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Se connecter",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ---------- REGISTER LINK ----------
        Row(
            modifier = Modifier.clickable { onRegisterClick() }
        ) {
            Text(
                text = "Vous n’avez pas de compte ? ",
                fontSize = 14.sp,
            )
            Text(
                text = "S’inscrire",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE01890),
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginChoicePreview(){
    DrcLoveTheme {
        LoginChoiceScreen()
    }
}