package utils

import org.w3c.dom.HTMLElement

fun org.w3c.dom.Document.create(
    tagName: String,
    className: String? = null,
    block: HTMLElement.() -> Unit = {}
): HTMLElement {
    val element = createElement(tagName) as HTMLElement
    className?.let { element.className = it }
    element.block()
    return element
}

fun org.w3c.dom.Document.div(className: String? = null, block: HTMLElement.() -> Unit = {}): HTMLElement {
    return create(Constants.TAG_DIV, className, block)
}

fun org.w3c.dom.Document.span(className: String? = null, block: HTMLElement.() -> Unit = {}): HTMLElement {
    return create(Constants.TAG_SPAN, className, block)
}

fun org.w3c.dom.Document.strong(block: HTMLElement.() -> Unit = {}): HTMLElement {
    return create(Constants.TAG_STRONG, block = block)
}

fun org.w3c.dom.Document.h2(className: String? = null, block: HTMLElement.() -> Unit = {}): HTMLElement {
    return create(Constants.TAG_H2, className, block)
}