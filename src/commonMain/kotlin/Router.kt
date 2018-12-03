/**
 * Marker interface for entities that represents navigation tabs.
 */
public interface Tab

public class TabStub : Tab


/**
 * [Router] is an adapter between user calls and the platform’s navigation system,
 * implemented in [Navigator]
 */
public open class Router<Screen> {
    private val commandBuffer: CommandBuffer<Screen> = CommandBuffer()


    /**
     * Platform-based implementation of [Navigator] interface
     */
    public var navigator: Navigator<Screen>?
        public get() = commandBuffer.navigator
        public set(value) {
            commandBuffer.navigator = value
        }


    public fun navigateTo(
        destination: Screen,
        data: String? = null
    ) = ForwardCommand(destination, data).execute()

    public fun back() = BackCommand<Screen>().execute()

    public fun changeTab(tab: Tab) = ChangeTabCommand<Screen>(tab).execute()


    protected fun NavigationCommand<Screen>.execute() = commandBuffer.execute(this)
}
