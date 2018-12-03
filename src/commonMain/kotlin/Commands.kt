public open class NavigationCommand<out Screen>(
    val tab: Tab? = null,
    val destination: Screen? = null,
    val data: String? = null
)


/**
 * Direct transition to [destination] screen with [data]
 */
public final class ForwardCommand<out Screen>(
    destination: Screen,
    data: String? = null
) : NavigationCommand<Screen>(
    destination = destination,
    data = data
)


/**
 * Return to previous screen
 */
public final class BackCommand<out Screen> : NavigationCommand<Screen>()


/**
 * Change tab with screens to [tab]
 */
public final class ChangeTabCommand<out Screen>(
    tab: Tab
) : NavigationCommand<Screen>(tab = tab)