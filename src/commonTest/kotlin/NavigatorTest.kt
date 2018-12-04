import kotlin.test.Test
import kotlin.test.assertEquals

class NavigatorTest {
    @Test
    fun testCommandsWithOneScreen() {
        val app = MockApp()
        val screenA = MockScreen("A")

        app.router.navigateTo(screenA)
        assertEquals(actual = app.currentScreen, expected = screenA)
        
        app.router.back()
        assertEquals(actual = app.currentScreen, expected = null)
    }

    @Test
    fun testCommandsWithTwoScreens() {
        val app = MockApp()
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.back()
        assertEquals(actual = app.currentScreen, expected = screenA)
        
        app.router.back()
        assertEquals(actual = app.currentScreen, expected = null)
    }

    @Test
    fun testCommandsWithThreeScreens() {
        val app = MockApp()
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("B")

        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.back()
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigateTo(screenC)
        assertEquals(actual = app.currentScreen, expected = screenC)

        app.router.back()
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.back()
        assertEquals(actual = app.currentScreen, expected = null)
    }


    @Test
    fun testRemoveAddNavigatorBeforeNavigationWithOneFragment() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")

        app.router.navigator = null
        app.router.navigateTo(screenA)
        assertEquals(actual = app.currentScreen, expected = null)

        app.router.navigator = navigator
        assertEquals(actual = app.currentScreen, expected = screenA)
    }

    @Test
    fun testRemoveAddNavigatorBeforeNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigator = null
        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = null)

        app.router.navigator = navigator
        assertEquals(actual = app.currentScreen, expected = screenB)
    }

    @Test
    fun testRemoveAddNavigatorDuringNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigator = null
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigator = navigator
        assertEquals(actual = app.currentScreen, expected = screenB)
    }

    @Test
    fun testRemoveAddNavigatorDuringNavigationWithThreeFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("C")

        app.router.navigateTo(screenA)
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigator = null
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigator = navigator
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.back()
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigator = null
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigator = navigator
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigateTo(screenC)
        assertEquals(actual = app.currentScreen, expected = screenC)
    }

    @Test
    fun testRemoveAddNavigatorAfterNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.navigator = null
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.back()
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.navigator = navigator
        assertEquals(actual = app.currentScreen, expected = screenA)
    }


    @Test
    fun testChangeTab() {
        val app = MockApp(isMultipleTabs = true)
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("C")

        app.router.navigateTo(screenA)
        assertEquals(actual = app.currentScreen, expected = screenA)

        app.router.navigateTo(screenB)
        assertEquals(actual = app.currentScreen, expected = screenB)

        app.router.changeTab(MockTab.Second)
        app.router.navigateTo(screenC)
        assertEquals(actual = app.currentScreen, expected = screenC)

        app.router.changeTab(MockTab.First)
        assertEquals(actual = app.currentScreen, expected = screenB)
    }
}