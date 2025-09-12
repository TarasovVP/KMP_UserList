package presentation

import com.tarasovvp.kmpuserlist.domain.model.User
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement
import utils.Constants
import utils.div
import utils.h2
import utils.span
import utils.strong

class UserRenderer {

    fun renderUsers(users: List<User>, containerId: String = Constants.ID_USERS_CONTAINER) {
        val container = document.getElementById(containerId) as? HTMLElement
        container?.let {
            it.innerHTML = Constants.STRING_EMPTY
            users.forEach { user ->
                it.appendChild(createUserCard(user))
            }
        }
    }

    private fun createUserCard(user: User): HTMLElement {
        return document.div(Constants.CSS_USER_CARD) {
            appendChild(createUserImage(user))
            appendChild(createUserInfo(user))
        }
    }

    private fun createUserImage(user: User): HTMLElement {
        val img = document.createElement(Constants.TAG_IMG) as HTMLImageElement
        img.className = Constants.CSS_USER_IMAGE
        img.src = user.image
        img.alt = "${user.firstName} ${user.lastName}"
        img.setAttribute(Constants.ATTR_ONERROR, "this.${Constants.ATTR_SRC}='${Constants.PLACEHOLDER_IMAGE}'")
        return img
    }

    private fun createUserInfo(user: User): HTMLElement {
        return document.div(Constants.CSS_USER_INFO) {
            appendChild(document.h2(Constants.CSS_USER_NAME) {
                textContent = "${user.firstName} ${user.lastName}"
            })
            appendChild(createUserDetails(user))
        }
    }

    private fun createUserDetails(user: User): HTMLElement {
        return document.div(Constants.CSS_USER_DETAILS) {
            appendChild(createDetail(Constants.TEXT_BIRTH, "${user.birthDate} (${user.age} ${Constants.TEXT_YEARS_OLD})"))
            appendChild(createGenderDetail(user.gender))
            appendChild(createDetail(Constants.TEXT_EMAIL, user.email, Constants.CSS_USER_CONTACT))
            appendChild(createDetail(Constants.TEXT_PHONE, user.phone, Constants.CSS_USER_CONTACT))
        }
    }

    private fun createDetail(label: String, value: String, spanClass: String? = null): HTMLElement {
        return document.div(Constants.CSS_USER_DETAIL) {
            appendChild(document.strong {
                textContent = label
            })
            appendChild(document.span(spanClass) {
                textContent = value
            })
        }
    }

    private fun createGenderDetail(gender: String): HTMLElement {
        return document.div(Constants.CSS_USER_DETAIL) {
            appendChild(document.strong {
                textContent = Constants.TEXT_GENDER
            })
            appendChild(document.span("${Constants.CSS_GENDER_PREFIX}$gender") {
                textContent = gender
            })
        }
    }
}