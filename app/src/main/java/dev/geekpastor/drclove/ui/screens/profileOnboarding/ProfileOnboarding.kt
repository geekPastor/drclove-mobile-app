package dev.geekpastor.drclove.ui.screens.profileOnboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.ExperimentalLayoutApi as FlowExperimentalLayoutApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.geekpastor.drclove.R
import dev.geekpastor.drclove.ui.theme.DrcLoveTheme
import kotlinx.coroutines.launch

// -----------------------------------------------------------------------------
//  DATA & STEP
// -----------------------------------------------------------------------------

enum class OnboardingStep {
    PHONE,
    PROFILE_BASIC,
    PROFILE_DETAILS,
    GENDER,
    PREFERENCES
}

data class ProfileOnboardingData(
    val phoneCode: String = "+243",
    val phoneNumber: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val city: String = "",
    val birthDate: String = "",
    val gender: String? = null,            // "Homme" / "Femme" etc.
    val preferences: Set<String> = emptySet()
)

// -----------------------------------------------------------------------------
//  ROUTE
// -----------------------------------------------------------------------------

@Composable
fun ProfileOnboardingRoute(
    onFinished: (ProfileOnboardingData) -> Unit = {}
) {
    ProfileOnboardingScreen(onFinished = onFinished)
}

// -----------------------------------------------------------------------------
//  SCREEN PRINCIPAL AVEC HorizontalPager
// -----------------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileOnboardingScreen(
    onFinished: (ProfileOnboardingData) -> Unit
) {
    val steps = remember {
        listOf(
            OnboardingStep.PHONE,
            OnboardingStep.PROFILE_BASIC,
            OnboardingStep.PROFILE_DETAILS,
            OnboardingStep.GENDER,
            OnboardingStep.PREFERENCES
        )
    }

    val pagerState = rememberPagerState(pageCount = { steps.size })
    val scope = rememberCoroutineScope()

    var state by remember { mutableStateOf(ProfileOnboardingData()) }

    val currentPage = pagerState.currentPage
    val currentStep = steps[currentPage]
    val isLastStep = currentPage == steps.lastIndex
    val isFirstStep = currentPage == 0

    val backgroundColor = Color(0xFFF7EFF3)
    val primaryGradient = Brush.horizontalGradient(
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- BARRE DE PROGRESSION ----------
        OnboardingProgressBar(
            totalSteps = steps.size,
            currentIndex = currentPage,
            gradient = primaryGradient
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- LOGO ----------
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier.width(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- PAGER AVEC LES ÉTAPES ----------
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { pageIndex ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val step = steps[pageIndex]
                when (step) {
                    OnboardingStep.PHONE -> PhoneStep(
                        state = state,
                        onStateChange = { state = it }
                    )
                    OnboardingStep.PROFILE_BASIC -> ProfileBasicStep(
                        state = state,
                        onStateChange = { state = it }
                    )
                    OnboardingStep.PROFILE_DETAILS -> ProfileDetailsStep(
                        state = state,
                        onStateChange = { state = it }
                    )
                    OnboardingStep.GENDER -> GenderStep(
                        state = state,
                        onStateChange = { state = it }
                    )
                    OnboardingStep.PREFERENCES -> PreferencesStep(
                        state = state,
                        onStateChange = { state = it }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- BOUTONS BAS (PRÉCÉDENT / CONTINUER / TERMINER) ----------
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Bouton gauche : Précédent (caché à la première page)
            if (!isFirstStep) {
                SecondaryButton(
                    text = "Précédent",
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    if (!isFirstStep) {
                        scope.launch {
                            pagerState.animateScrollToPage(currentPage - 1)
                        }
                    }
                }
            } else {
                // Espace réservé pour garder l'alignement
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                )
            }

            // Bouton droite : Continuer / Terminer
            PrimaryGradientButton(
                text = if (isLastStep) "Terminer" else "Continuer",
                gradient = primaryGradient,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                if (isLastStep) {
                    onFinished(state)
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(currentPage + 1)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nous n’affichons les numéros que pour les vérifications de la BCDC.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// -----------------------------------------------------------------------------
//  COMPOSANTS D’ÉTAPES
// -----------------------------------------------------------------------------

@Composable
private fun PhoneStep(
    state: ProfileOnboardingData,
    onStateChange: (ProfileOnboardingData) -> Unit
) {
    Text(
        text = "Tapez votre numéro pour\npasser la vérification.",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF2C2C2C),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(32.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = state.phoneCode,
            onValueChange = { onStateChange(state.copy(phoneCode = it)) },
            modifier = Modifier.width(120.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = Color(0xFFFF4B8B)
                )
            },
            shape = RoundedCornerShape(50.dp),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = { onStateChange(state.copy(phoneNumber = it)) },
            modifier = Modifier.weight(1f),
            singleLine = true,
            placeholder = { Text("97 000 0000") },
            shape = RoundedCornerShape(50.dp),
            colors = textFieldColors()
        )
    }
}

@Composable
private fun ProfileBasicStep(
    state: ProfileOnboardingData,
    onStateChange: (ProfileOnboardingData) -> Unit
) {
    Text(
        text = "Entrez vos informations\nde profil public.",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF2C2C2C),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(32.dp))

    OutlinedTextField(
        value = state.firstName,
        onValueChange = { onStateChange(state.copy(firstName = it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Prénom") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFFFF4B8B)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = textFieldColors()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = state.lastName,
        onValueChange = { onStateChange(state.copy(lastName = it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Nom") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFFFF4B8B)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = textFieldColors()
    )
}

@Composable
private fun ProfileDetailsStep(
    state: ProfileOnboardingData,
    onStateChange: (ProfileOnboardingData) -> Unit
) {
    Text(
        text = "Entrez vos informations\nde profil public.",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF2C2C2C),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(32.dp))

    OutlinedTextField(
        value = state.city,
        onValueChange = { onStateChange(state.copy(city = it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Ville") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFFFF4B8B)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = textFieldColors()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = state.birthDate,
        onValueChange = { onStateChange(state.copy(birthDate = it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("JJ / MM / AAAA") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Cake,
                contentDescription = null,
                tint = Color(0xFFFF4B8B)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = textFieldColors()
    )
}

@Composable
private fun GenderStep(
    state: ProfileOnboardingData,
    onStateChange: (ProfileOnboardingData) -> Unit
) {
    Text(
        text = "Je suis un (e).",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF2C2C2C),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(40.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GenderChoice(
            label = "Homme",
            selected = state.gender == "Homme",
            onClick = { onStateChange(state.copy(gender = "Homme")) }
        )
        GenderChoice(
            label = "Femme",
            selected = state.gender == "Femme",
            onClick = { onStateChange(state.copy(gender = "Femme")) }
        )
    }
}

@OptIn(FlowExperimentalLayoutApi::class)
@Composable
private fun PreferencesStep(
    state: ProfileOnboardingData,
    onStateChange: (ProfileOnboardingData) -> Unit
) {
    Text(
        text = "Mes préférences.",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF2C2C2C),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(24.dp))

    val allPrefs = listOf(
        "Photographie", "Shopping", "Karaoké", "Yoga",
        "Cuisine", "Voyage", "Marathon", "Natation",
        "Art", "Musique", "Lecture", "Jeux vidéo"
    )

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        allPrefs.forEach { pref ->
            val selected = pref in state.preferences
            PreferenceChip(
                label = pref,
                selected = selected,
            ) {
                val newSet = state.preferences.toMutableSet().apply {
                    if (selected) remove(pref) else add(pref)
                }
                onStateChange(state.copy(preferences = newSet))
            }
        }
    }
}

// -----------------------------------------------------------------------------
//  COMPOSANTS RÉUTILISABLES
// -----------------------------------------------------------------------------

@Composable
private fun OnboardingProgressBar(
    totalSteps: Int,
    currentIndex: Int,
    gradient: Brush
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(totalSteps) { index ->
            val isActive = index <= currentIndex
            val brush = if (isActive) {
                gradient
            } else {
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFE2C7D6))
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(100))
                    .background(brush = brush)
            )
        }
    }
}

@Composable
private fun PrimaryGradientButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(gradient)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun GenderChoice(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg = if (selected) Color(0xFFFF4B8B) else Color.White
    val contentColor = if (selected) Color.White else Color(0xFF333333)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(bg),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label.first().toString(),
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = contentColor,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun PreferenceChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg = if (selected) Color(0xFFFFEBF3) else Color.White
    val border = if (selected) Color(0xFFFF4B8B) else Color(0xFFE0E0E0)
    val textColor = if (selected) Color(0xFFFF4B8B) else Color(0xFF333333)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(bg)
            .border(width = 1.dp, color = border, shape = RoundedCornerShape(50.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    disabledContainerColor = Color.White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    cursorColor = Color(0xFFFF4B8B)
)

// -----------------------------------------------------------------------------
//  PREVIEW
// -----------------------------------------------------------------------------

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileOnboardingPreview() {
    DrcLoveTheme {
        ProfileOnboardingRoute()
    }
}
