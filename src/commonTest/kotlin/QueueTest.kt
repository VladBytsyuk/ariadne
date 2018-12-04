import containers.AriadneQueue
import containers.Queue

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class QueueTest {
    @Test
    fun empty() {
        val queue: Queue<Int> = AriadneQueue()

        assertEquals(expected = 0, actual = queue.size)
        assertTrue { queue.isEmpty() }
        assertFalse { queue.isNotEmpty() }
    }


    @Test
    fun addOne() {
        val queue: Queue<Int> = AriadneQueue()
        val value = 42
        queue.add(value)

        assertEquals(expected = 1, actual = queue.size )
        assertFalse { queue.isEmpty() }
        assertTrue { queue.isNotEmpty() }
    }

    @Test
    fun pollOne() {
        val queue: Queue<Int> = AriadneQueue()
        val value = 42
        queue.add(value)
        val valueFromQueue = queue.poll()

        assertEquals(expected = 0, actual = queue.size)
        assertTrue { queue.isEmpty() }
        assertFalse { queue.isNotEmpty() }
        assertEquals(expected = value, actual = valueFromQueue)
    }


    @Test
    fun addTwo() {
        val queue: Queue<Int> = AriadneQueue()
        val valueOne = 42
        val valueTwo = 23
        queue.add(valueOne)
        queue.add(valueTwo)

        assertEquals(expected = 2, actual = queue.size )
        assertFalse { queue.isEmpty() }
        assertTrue { queue.isNotEmpty() }
    }


    @Test
    fun pollTwo() {
        val queue: Queue<Int> = AriadneQueue()
        val valueOne = 42
        val valueTwo = 23
        queue.add(valueOne)
        queue.add(valueTwo)
        val valueOneFromQueue = queue.poll()
        val valueTwoFromQueue = queue.poll()

        assertEquals(expected = 0, actual = queue.size)
        assertTrue { queue.isEmpty() }
        assertFalse { queue.isNotEmpty() }
        assertEquals(expected = valueOne, actual = valueOneFromQueue)
        assertEquals(expected = valueTwo, actual = valueTwoFromQueue)
    }


    @Test
    fun addPoll() {
        val queue: Queue<Int> = AriadneQueue()
        val value = 42
        queue.add(value)

        assertEquals(expected = 1, actual = queue.size )
        assertFalse { queue.isEmpty() }
        assertTrue { queue.isNotEmpty() }

        val valueFromQueue = queue.poll()

        assertEquals(expected = 0, actual = queue.size)
        assertTrue { queue.isEmpty() }
        assertFalse { queue.isNotEmpty() }
        assertEquals(expected = value, actual = valueFromQueue)
    }
}