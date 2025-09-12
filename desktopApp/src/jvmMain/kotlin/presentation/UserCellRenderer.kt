package presentation

import com.tarasovvp.kmpuserlist.domain.model.User
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import java.awt.Image
import java.awt.RenderingHints
import java.awt.geom.Ellipse2D
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.ListCellRenderer
import javax.swing.border.EmptyBorder

class UserCellRenderer : JPanel(), ListCellRenderer<User> {
    private val avatarLabel = JLabel()
    private val nameLabel = JLabel().apply {
        font = font.deriveFont(Font.BOLD, 15f)
        alignmentX = LEFT_ALIGNMENT
    }
    private val birthLabel = JLabel().apply {
        foreground = Color(0x6B, 0x72, 0x80)
        alignmentX = LEFT_ALIGNMENT
    }
    private val emailLabel = JLabel().apply { alignmentX = LEFT_ALIGNMENT }
    private val phoneLabel = JLabel().apply { alignmentX = LEFT_ALIGNMENT }

    init {
        layout = BorderLayout(12, 0)
        border = EmptyBorder(6, 0, 6, 0)

        val left = JPanel(BorderLayout()).apply {
            isOpaque = false
            preferredSize = Dimension(56, 56)
            add(avatarLabel, BorderLayout.CENTER)
        }

        val right = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = LEFT_ALIGNMENT
            add(nameLabel)
            add(Box.createVerticalStrut(4))
            add(birthLabel)
            add(Box.createVerticalStrut(6))
            add(row("✉︎", emailLabel))
            add(row("☎︎", phoneLabel))
        }

        add(left, BorderLayout.WEST)
        add(right, BorderLayout.CENTER)
    }

    override fun getListCellRendererComponent(
        list: JList<out User>,
        value: User?,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        if (value == null) return this

        background = if (isSelected) list.selectionBackground else list.background
        isOpaque = true

        nameLabel.text = "${value.firstName} ${value.lastName}".trim()
        birthLabel.text = "Birth: ${value.birthDate} (${value.age} years old)"
        emailLabel.text = value.email
        phoneLabel.text = value.phone

        avatarLabel.icon = loadCircleAvatar(value.image, 56)
        return this
    }

    private fun row(iconText: String, value: JLabel): JPanel {
        val icon = JLabel(iconText).apply {
            foreground = Color(0x6B, 0x72, 0x80)
            font = font.deriveFont(12f)
        }
        value.font = value.font.deriveFont(14f)
        return JPanel(FlowLayout(FlowLayout.LEFT, 8, 0)).apply {
            isOpaque = false
            alignmentX = LEFT_ALIGNMENT
            add(icon); add(value)
        }
    }

    companion object {
        private val cache = mutableMapOf<String, Icon>()

        fun loadCircleAvatar(url: String?, size: Int): Icon? {
            if (url.isNullOrBlank()) return null
            cache[url]?.let { return it }
            return try {
                val img = ImageIO.read(URL(url)) ?: return null
                val scaled = img.getScaledInstance(size, size, Image.SCALE_SMOOTH)
                val bi = BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)
                val g2 = bi.createGraphics()
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                )
                val clip = Ellipse2D.Float(0f, 0f, size.toFloat(), size.toFloat())
                g2.clip = clip
                g2.drawImage(scaled, 0, 0, null)
                g2.dispose()
                return ImageIcon(bi).also { cache[url] = it }
            } catch (_: Exception) {
                null
            }
        }
    }
}