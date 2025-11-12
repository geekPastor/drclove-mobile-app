package dev.geekpastor.drclove.ui.screens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

    }
}