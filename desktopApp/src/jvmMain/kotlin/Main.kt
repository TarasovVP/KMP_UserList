import com.tarasovvp.kmpuserlist.di.initKoin
import presentation.App
import javax.swing.SwingUtilities

fun main() = SwingUtilities.invokeLater {
    initKoin()
    App()
}