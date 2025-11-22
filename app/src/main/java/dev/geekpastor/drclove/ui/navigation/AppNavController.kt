package dev.geekpastor.drclove.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.geekpastor.drclove.ui.screens.login.LoginChoiceRoute
import dev.geekpastor.drclove.ui.screens.onboarding.OnBoardingRoute
import dev.geekpastor.drclove.ui.screens.splash.SplashRoute
import dev.geekpastor.drclove.ui.screens.welcome.WelcomeRoute

@Composable
fun AppNavController(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.SplashScreen.route.name
    ){

        composable(
            route = Destination.SplashScreen.route.name
        ){
            SplashRoute(navController = navController)
        }


        composable(
            route = Destination.WelcomeScreen.route.name
        ){
            WelcomeRoute {
                navController.navigate(Destination.OnBoardingScreen.route.name)
            }
        }

        composable(
            route = Destination.OnBoardingScreen.route.name
        ){
            OnBoardingRoute(
                navigateToLoginChoice = {
                    navController.navigate(Destination.LoginChoiceScreen.route.name)
                }
            )
        }

        composable(
            route = Destination.LoginChoiceScreen.route.name
        ) {
            LoginChoiceRoute()
        }
    }
}