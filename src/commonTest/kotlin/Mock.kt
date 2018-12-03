import containers.IStack
import containers.AriadneStack

internal data class MockScreen(val name: String)

internal enum class MockTab : Tab { First, Second }


internal class MockNavigator(
    val app: MockApp,
    tabs: List<Tab> = listOf(TabStub())
) : Navigator<MockScreen>() {
    private val tabStacks: Map<Tab, IStack<MockScreen>> =
        tabs.map { it to AriadneStack<MockScreen>() }.toMap()

    private var activeTab: Tab? = tabStacks.keys.first()

    private val activeTabStack: IStack<MockScreen>
        get() = tabStacks[activeTab]
            ?: throw Navigator.IllegalTabException("No such tab = [$activeTab] registered in Router.")

    private val activeScreen: MockScreen?
        get() = if (activeTabStack.isNotEmpty()) activeTabStack.peek() else null

    override fun apply(command: NavigationCommand<MockScreen>) {
        when (command) {
            is ForwardCommand -> activeTabStack.push(command.destination as MockScreen)
            is BackCommand -> activeTabStack.pop()
            is ChangeTabCommand -> activeTab = command.tab
            else -> throw Navigator.UnsupportedCommandException("No such command")
        }
        app.currentScreen = activeScreen
    }
}


internal class MockApp(isMultipleTabs: Boolean = false) {
    val router: Router<MockScreen> = Router()

    init {
        val navigator = if (isMultipleTabs)
            MockNavigator(app = this, tabs = MockTab.values().toList())
        else
            MockNavigator(app = this)
        router.navigator = navigator

    }

    var currentScreen: MockScreen? = null
}