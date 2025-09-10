package presentation

import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import utils.Constants

class UiStateManager {

    fun showLoading(show: Boolean, elementId: String = Constants.ID_LOADING) {
        val element = document.getElementById(elementId) as? HTMLElement
        element?.style?.display = if (show) Constants.DISPLAY_BLOCK else Constants.DISPLAY_NONE
    }

    fun showError(message: String, elementId: String = Constants.ID_ERROR) {
        val element = document.getElementById(elementId) as? HTMLElement
        element?.let {
            it.style.display = Constants.DISPLAY_BLOCK
            it.textContent = message
        }
    }

    fun hideError(elementId: String = Constants.ID_ERROR) {
        val element = document.getElementById(elementId) as? HTMLElement
        element?.style?.display = Constants.DISPLAY_NONE
    }
}