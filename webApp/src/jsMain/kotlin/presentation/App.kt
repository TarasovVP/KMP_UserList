package presentation

import com.tarasovvp.kmpuserlist.di.getUserListUseCase
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLElement
import utils.Constants

class App(
    private val userRenderer: UserRenderer = UserRenderer(),
    private val uiStateManager: UiStateManager = UiStateManager()
) {

    init {
        setupEventListeners()
        loadUsers()
    }

    private fun setupEventListeners() {
        val refreshButton = document.getElementById(Constants.ID_REFRESH_BUTTON) as? HTMLElement
        refreshButton?.addEventListener(Constants.CLICK, { loadUsers() })
    }

    fun loadUsers() {
        uiStateManager.showLoading(true)
        uiStateManager.hideError()

        val scope = MainScope()
        scope.launch {
            try {
                val userListUseCase = getUserListUseCase()
                val users = userListUseCase.execute()
                userRenderer.renderUsers(users)
            } catch (e: Exception) {
                uiStateManager.showError("${Constants.ERROR_LOADING_USERS}${e.message ?: Constants.ERROR_UNKNOWN}")
            } finally {
                uiStateManager.showLoading(false)
            }
        }
    }
}