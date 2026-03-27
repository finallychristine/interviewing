import io.github.oshai.kotlinlogging.KotlinLogging

// NOTE: prediction is enabled
// Note: breakpoints on uncaught exception enabled

private val logger = KotlinLogging.logger { }

fun example() {
    error("oh no")
}

fun main() {
    logger.info { "hello world" }
    example()
}