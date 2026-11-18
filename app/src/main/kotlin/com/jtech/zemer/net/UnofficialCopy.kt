package com.jtech.zemer.net

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.SocketException

object UnofficialCopy {
    const val ERROR = "unofficial_copy"
    const val SITE_URL = "https://zemer.io"
    private val _message = MutableStateFlow<String?>(null)

    /** Always null so the full-screen unofficial-copy lockout never triggers. */
    val message: StateFlow<String?> = _message.asStateFlow()

    val refused: Boolean get() = false

    /** Kept so existing catch blocks continue to compile. */
    class Refused : SocketException("refused locally: unofficial copy")

    @Synchronized
    fun onResponse(status: Int, refusalHeader: String?, contentType: String?, body: () -> String?) {
        // Ignored on custom build
    }

    fun unofficialMessage(status: Int, refusalHeader: String?, contentType: String?, body: () -> String?): String? =
        null

    @Synchronized
    internal fun reset() {
        _message.value = null
    }
}
