import org.gradle.api.JavaVersion

/**
 * @author Vivien Mahe
 * @since 23/07/2022
 */

object ProjectConfiguration {

    object Chartopia {
        const val packageName = "com.tweener.chartopia"
        const val versionName = "2.0.3"
        const val namespace = "$packageName.android"
        const val compileSDK = 35
        const val minSDK = 24

        object Maven {
            const val name = "Chartopia"
            const val description = "A Kotlin/Compose Multiplatform library that offers many different types of charts"
            const val group = "io.github.tweener"
            const val packageUrl = "https://github.com/Tweener/chartopia"
            const val gitUrl = "github.com:Tweener/chartopia.git"

            object Developer {
                const val id = "Tweener"
                const val name = "Vivien Mahé"
                const val email = "vivien@tweener-labs.com"
            }
        }
    }

    object Compiler {
        val javaCompatibility = JavaVersion.VERSION_21
        val jvmTarget = javaCompatibility.toString()
    }
}
