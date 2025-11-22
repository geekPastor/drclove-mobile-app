package dev.geekpastor.drclove.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.geekpastor.drclove.ui.screens.login.LoginChoiceRoute
import dev.geekpastor.drclove.ui.screens.login.LoginRoute
import dev.geekpastor.drclove.ui.screens.onboarding.OnBoardingRoute
import dev.geekpastor.drclove.ui.screens.profileOnboarding.ProfileOnboardingRoute
import dev.geekpastor.drclove.ui.screens.register.RegisterRoute
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
            LoginChoiceRoute(
                onLoginClick = {
                    navController.navigate(Destination.LoginScreen.route.name)
                },
                onRegisterClick = {
                    navController.navigate(Destination.RegisterScreen.route.name)
                }
            )
        }

        composable(
            route = Destination.LoginScreen.route.name
        ) {
            LoginRoute(
                navigateToRegister = {
                    navController.navigate(Destination.RegisterScreen.route.name)
                },
                navigateToSetUpProfile = {
                    navController.navigate(Destination.ProfileOnboardingScreen.route.name)
                }
            )
        }

        composable(
            route = Destination.RegisterScreen.route.name
        ) {
            RegisterRoute(
                navigateToLogin = {
                    navController.navigate(Destination.LoginScreen.route.name)
                },
                navigateToSetUpProfile = {
                    navController.navigate(Destination.ProfileOnboardingScreen.route.name)
                }
            )
        }

        composable(
            route = Destination.ProfileOnboardingScreen.route.name
        ) {
            ProfileOnboardingRoute {

            }
        }
    }
}