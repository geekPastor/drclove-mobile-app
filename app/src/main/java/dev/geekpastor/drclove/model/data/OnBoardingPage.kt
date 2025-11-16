package dev.geekpastor.drclove.model.data

data class OnBoardingPage(
    val logo: Int,            // image du logo
    val description: String,  // texte sous le logo
    val images: List<Int>,    // images de la mise en page
    val isLast: Boolean = false
)
