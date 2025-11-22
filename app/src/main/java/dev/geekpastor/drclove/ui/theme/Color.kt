package dev.geekpastor.drclove.ui.theme

import androidx.compose.ui.graphics.Color

object DRCLoveColor {

    object Light : ColorSchemeType {
        // --- Couleur principale (Rose magenta) ---
        override val Primary = Color(0xFFB30FA8)          // Magenta
        override val OnPrimary = Color(0xFFFFFFFF)        // Blanc
        override val PrimaryContainer = Color(0xFFF5CCF1) // Magenta clair
        override val OnPrimaryContainer = Color(0xFF4A0045) // Magenta foncé

        // --- Couleur secondaire (Rose chaud) ---
        override val Secondary = Color(0xFFE01890)        // Rose
        override val OnSecondary = Color(0xFFFFFFFF)      // Blanc
        override val SecondaryContainer = Color(0xFFFFC1E4) // Rose clair
        override val OnSecondaryContainer = Color(0xFF4B0030) // Rose foncé

        // --- Accent (Or doux) ---
        override val Tertiary = Color(0xFFE8A838)         // Doré
        override val OnTertiary = Color(0xFF12020C)       // Noir profond
        override val TertiaryContainer = Color(0xFFFFE7B2) // Jaune clair
        override val OnTertiaryContainer = Color(0xFF3D2A00) // Doré foncé

        // --- Background & Surface ---
        override val Background = Color(0xFFF3EFEF)       // Blanc
        override val OnBackground = Color(0xFF12020C)     // Noir profond

        override val Surface = Color(0xFFFFFFFF)
        override val OnSurface = Color(0xFF12020C)
        override val SurfaceVariant = Color(0xFFF5E8F3)   // Rose clair
        override val OnSurfaceVariant = Color(0xFF4A0045)

        override val SurfaceTint = Primary

        // --- États d'erreur ---
        override val Error = Color(0xFFBA1A1A)
        override val OnError = Color(0xFFFFFFFF)
        override val ErrorContainer = Color(0xFFFFDAD6)
        override val OnErrorContainer = Color(0xFF410002)

        // --- Bordures / outlines ---
        override val Outline = Color(0xFFB0A4A8)
        override val OutlineVariant = Color(0xFFD6C6D0)
        override val scrim = Color(0xFF000000)

        // --- Inverses ---
        override val InverseSurface = Color(0xFF12020C)
        override val InverseOnSurface = Color(0xFFFFFFFF)
        override val InversePrimary = Color(0xFFE01890)

        // --- Surfaces dynamiques ---
        override val SurfaceDim = Color(0xFFECE0EB)
        override val SurfaceBright = Color(0xFFFFFFFF)
        override val SurfaceContainerLowest = Color(0xFFFFFFFF)
        override val SurfaceContainerLow = Color(0xFFFDF2FA)
        override val SurfaceContainer = Color(0xFFF7E8F5)
        override val SurfaceContainerHigh = Color(0xFFF0DAF0)
        override val SurfaceContainerHighest = Color(0xFFE5CCE4)
    }

    object Dark : ColorSchemeType {
        // --- Couleur principale (Magenta clair) ---
        override val Primary = Color(0xFFF17EE6)
        override val OnPrimary = Color(0xFF4A0045)
        override val PrimaryContainer = Color(0xFF7B0071)
        override val OnPrimaryContainer = Color(0xFFFFCCF8)

        // --- Couleur secondaire (Rose chaud clair) ---
        override val Secondary = Color(0xFFFF7DC3)
        override val OnSecondary = Color(0xFF4B0030)
        override val SecondaryContainer = Color(0xFF74004C)
        override val OnSecondaryContainer = Color(0xFFFFC1E4)

        // --- Accent (Or chaud) ---
        override val Tertiary = Color(0xFFFFC65C)
        override val OnTertiary = Color(0xFF3D2A00)
        override val TertiaryContainer = Color(0xFF5E4100)
        override val OnTertiaryContainer = Color(0xFFFFE7B2)

        // --- Background & Surface ---
        override val Background = Color(0xFF12020C)       // Noir profond
        override val OnBackground = Color(0xFFEDE0EA)

        override val Surface = Color(0xFF1E1018)
        override val OnSurface = Color(0xFFEDE0EA)
        override val SurfaceVariant = Color(0xFF4A0045)
        override val OnSurfaceVariant = Color(0xFFF5CFEF)

        override val SurfaceTint = Primary

        // --- États d'erreur ---
        override val Error = Color(0xFFFFB4AB)
        override val OnError = Color(0xFF690005)
        override val ErrorContainer = Color(0xFF93000A)
        override val OnErrorContainer = Color(0xFFFFDAD6)

        // --- Bordures / outlines ---
        override val Outline = Color(0xFF8D7A8A)
        override val OutlineVariant = Color(0xFF5F4457)
        override val scrim = Color(0xFF000000)

        // --- Inverses ---
        override val InverseSurface = Color(0xFFEDE0EA)
        override val InverseOnSurface = Color(0xFF12020C)
        override val InversePrimary = Color(0xFFE01890)

        // --- Surfaces dynamiques ---
        override val SurfaceDim = Color(0xFF12020C)
        override val SurfaceBright = Color(0xFF291A25)
        override val SurfaceContainerLowest = Color(0xFF0A0008)
        override val SurfaceContainerLow = Color(0xFF1E1018)
        override val SurfaceContainer = Color(0xFF2D1E29)
        override val SurfaceContainerHigh = Color(0xFF3B2A37)
        override val SurfaceContainerHighest = Color(0xFF4A3846)
    }
}

val Color.Companion.Light: DRCLoveColor.Light
    get() = DRCLoveColor.Light

val Color.Companion.Dark: DRCLoveColor.Dark
    get() = DRCLoveColor.Dark
