package dev.geekpastor.drclove.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.geekpastor.drclove.R
import dev.geekpastor.drclove.ui.components.SocialButton
import dev.geekpastor.drclove.ui.theme.DrcLoveTheme

@Composable
fun RegisterRoute(
    navigateToSetUpProfile: () -> Unit = {},
    navigateToLogin: () -> Unit = {}
){
    RegisterScreen(
        navigateToHome = navigateToSetUpProfile,
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun RegisterScreen(
    navigateToHome: () -> Unit = {},
    navigateToLogin: () -> Unit = {}
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(56.dp))

        // ---------- LOGO / TITRE ----------

        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier.width(100.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Inscrivez-vous",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C2C2C)
            )
        )

        Spacer(modifier = Modifier.height(40.dp))


        Spacer(modifier = Modifier.height(16.dp))

        // ---------- CHAMP EMAIL ----------
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text("Taper votre adresse email")
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0xFFFF4B8B)
                )
            },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFFFF4B8B)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- CHAMP MOT DE PASSE ----------
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text("*************")
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFFFF4B8B)
                )
            },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFFFF4B8B)
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        // ---------- CHAMP MOT DE PASSE ----------
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text("*************")
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFFFF4B8B)
                )
            },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFFFF4B8B)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- BOUTON CONTINUER (GRADIENT) ----------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
                .clickable {
                    navigateToHome()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Continuer",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- SÉPARATEUR "OU" ----------
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFDDDDDD)
            )
            Text(
                text = "  OU  ",
                color = Color(0xFF999999),
                fontSize = 12.sp
            )
            Divider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFDDDDDD)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- BOUTON GOOGLE ----------
        SocialButton(
            iconRes = R.drawable.ic_google, // mets ton icône Google ici
            text = "Se connecter avec google",
            onClick = {
                navigateToHome()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ---------- BOUTON FACEBOOK ----------
        SocialButton(
            iconRes = R.drawable.ic_facebook, // mets ton icône Facebook ici
            text = "Se connecter avec facebook",
            onClick = {
                navigateToHome()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        // ---------- TEXTE BAS DE PAGE ----------
        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Vous avez deja un compte ? ",
                fontSize = 13.sp,
                color = Color(0xFF444444)
            )
            Text(
                text = "se connecter",
                fontSize = 13.sp,
                color = Color(0xFFFF1F75),
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navigateToLogin()
                }
            )
        }
    }
}


@Composable
fun RegisterPreview(){
    DrcLoveTheme {

    }
}