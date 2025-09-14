package presentation

import com.tarasovvp.kmpuserlist.di.getUserListUseCase
import com.tarasovvp.kmpuserlist.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.border.EmptyBorder

class App {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Swing)

    private val frame = JFrame("Users")
    private val listModel = DefaultListModel<User>()
    private val list = JList(listModel).apply {
        cellRenderer = UserCellRenderer()
        fixedCellHeight = - 1
    }
    private val status = JLabel("")

    init {
        initialize()
    }

    fun initialize() {
        val refresh = JButton("Refresh").apply { addActionListener { refresh() } }

        val top = JPanel(BorderLayout()).apply {
            border = EmptyBorder(8, 8, 8, 8)
            add(refresh, BorderLayout.WEST)
            add(status, BorderLayout.EAST)
        }
        val content = JPanel(BorderLayout()).apply {
            border = EmptyBorder(12, 12, 12, 12)
            add(top, BorderLayout.NORTH)
            add(JScrollPane(list), BorderLayout.CENTER)
        }

        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = content
        frame.setSize(460, 720)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true

        refresh()
    }

    private fun refresh() {
        status.foreground = Color.GRAY
        status.text = "Loading…"
        scope.launch {
            val useCase = getUserListUseCase()
            val result = withContext(Dispatchers.IO) { runCatching {
                useCase.execute()
            } }
            result.onSuccess { users ->
                listModel.clear()
                users.forEach { listModel.addElement(it) }
                status.text = ""
            }.onFailure { e ->
                listModel.clear()
                status.foreground = Color(0xCC0000)
                status.text = e.message ?: "Unknown error"
            }
        }
    }
}