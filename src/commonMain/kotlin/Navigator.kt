import containers.Stack
import containers.StackImpl


public abstract class Navigator<in Screen>(
    tabs: List<Tab> = listOf(TabStub())
) {
    /* =================================================================================================================
     * Private tab fields
     * =================================================================================================================
     */
    private val tabStacks: Map<Tab, Stack<Screen>> = tabs.map { it to StackImpl<Screen>() }.toMap()

    private var activeTab: Tab? = tabStacks.keys.first()

    private val activeTabStack: Stack<Screen>
        get() = tabStacks[activeTab] ?: throw IllegalTabException("No such tab = [$activeTab] registered in Router.")

    private val activeScreen: Screen? get() = if (activeTabStack.isNotEmpty()) activeTabStack.peek() else null



    /* =================================================================================================================
     * Abstract methods
     * =================================================================================================================
     */
    public abstract fun apply(command: NavigationCommand<Screen>)


    /* =================================================================================================================
     * Exception
     * =================================================================================================================
     */
    class UnsupportedCommandException(message: String) : Throwable(message)

    class IllegalTabException(message: String) : Throwable(message)
    class WrongUsageException(message: String) : Throwable(message)
}

