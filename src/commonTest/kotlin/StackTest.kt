import containers.AriadneStack
import containers.Stack

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StackTest {
    @Test
    fun empty() {
        val stack: Stack<Int> = AriadneStack()

        assertEquals(expected = 0, actual = stack.size)
        assertTrue { stack.isEmpty() }
        assertFalse { stack.isNotEmpty() }
    }


    @Test
    fun pushOne() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)

        assertEquals(expected = 1, actual = stack.size )
        assertFalse { stack.isEmpty() }
        assertTrue { stack.isNotEmpty() }
    }

    @Test
    fun popOne() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)
        val valueFromStack = stack.pop()

        assertEquals(expected = 0, actual = stack.size)
        assertTrue { stack.isEmpty() }
        assertFalse { stack.isNotEmpty() }
        assertEquals(expected = value, actual = valueFromStack)
    }

    @Test
    fun peekOne() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)
        val valueFromStack = stack.peek()

        assertEquals(expected = 1, actual = stack.size)
        assertFalse { stack.isEmpty() }
        assertTrue { stack.isNotEmpty() }
        assertEquals(expected = value, actual = valueFromStack)
    }


    @Test
    fun pushTwo() {
        val stack: Stack<Int> = AriadneStack()
        val valueOne = 42
        val valueTwo = 23
        stack.push(valueOne)
        stack.push(valueTwo)

        assertEquals(expected = 2, actual = stack.size )
        assertFalse { stack.isEmpty() }
        assertTrue { stack.isNotEmpty() }
    }


    @Test
    fun popTwo() {
        val stack: Stack<Int> = AriadneStack()
        val valueOne = 42
        val valueTwo = 23
        stack.push(valueOne)
        stack.push(valueTwo)
        val valueTwoFromStack = stack.pop()
        val valueOneFromStack = stack.pop()

        assertEquals(expected = 0, actual = stack.size)
        assertTrue { stack.isEmpty() }
        assertFalse { stack.isNotEmpty() }
        assertEquals(expected = valueTwo, actual = valueTwoFromStack)
        assertEquals(expected = valueOne, actual = valueOneFromStack)
    }


    @Test
    fun pushPeekPop() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)

        assertEquals(expected = 1, actual = stack.size )
        assertFalse { stack.isEmpty() }
        assertTrue { stack.isNotEmpty() }

        val peekedValueFromStack = stack.peek()

        assertEquals(expected = 1, actual = stack.size)
        assertFalse { stack.isEmpty() }
        assertTrue { stack.isNotEmpty() }
        assertEquals(expected = value, actual = peekedValueFromStack)

        val poppedValueFromStack = stack.pop()

        assertEquals(expected = 0, actual = stack.size)
        assertTrue { stack.isEmpty() }
        assertFalse { stack.isNotEmpty() }
        assertEquals(expected = value, actual = poppedValueFromStack)
    }
}