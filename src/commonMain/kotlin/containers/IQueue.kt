package containers

interface IQueue<T> {
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean

    fun add(item: T): Boolean
    fun poll(): T
}

internal class Queue<T> : IQueue<T> {
    private val list: MutableList<T> = mutableListOf()


    override fun isEmpty() = list.isEmpty()

    override fun isNotEmpty() = list.isNotEmpty()


    override fun add(item: T) = list.add(item)

    override fun poll(): T  = if (!list.isEmpty())
        list.first().apply { list.remove(this) }
    else throw EmptyQueueException("Can't poll on empty queue.")


    class EmptyQueueException(message: String) : Throwable(message)
}