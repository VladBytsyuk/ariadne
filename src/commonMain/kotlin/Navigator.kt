import containers.Stack
import containers.AriadneStack


abstract class Navigator<Screen>(
    tabs: List<Tab> = listOf(TabStub())
) {
    /* =================================================================================================================
     * Tab fields
     * =================================================================================================================
     */

    internal class TabStub : Tab

    private val tabStacks: Map<Tab, Stack<Screen>> = tabs.map { it to AriadneStack<Screen>() }.toMap()

    private var activeTab: Tab? = tabStacks.keys.first()

    private val activeTabStack: Stack<Screen>
        get() = tabStacks[activeTab] ?: throw IllegalTabException("No such tab = [$activeTab] registered in Router.")

    val activeScreen: Screen? get() = if (activeTabStack.isNotEmpty()) activeTabStack.peek() else null


    /* =================================================================================================================
     * Abstract methods
     * =================================================================================================================
     */

    abstract fun apply(command: NavigationCommand<Screen>)


    /* =================================================================================================================
     * Exceptions
     * =================================================================================================================
     */

    class UnsupportedCommandException(message: String) : UnsupportedOperationException(message)

    class IllegalTabException(message: String) : IllegalArgumentException(message)
}

