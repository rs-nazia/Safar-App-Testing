package com.safar.mobile.patterns

import com.safar.mobile.model.Destination

/**
 * 8. ITERATOR DESIGN PATTERN
 * Provides custom iterator behavior over Destination collections.
 */
interface CustomIterator<T> {
    fun hasNext(): Boolean
    fun next(): T
}

interface AggregateCollection<T> {
    fun createIterator(): CustomIterator<T>
}

class DestinationCollection(private val items: List<Destination>) : AggregateCollection<Destination> {
    
    override fun createIterator(): CustomIterator<Destination> {
        return DestinationSequentialIterator(items)
    }

    fun createPriceFilteredIterator(maxPrice: Double): CustomIterator<Destination> {
        val filtered = items.filter { dest ->
            val numericPrice = dest.price?.replace(Regex("[^0-9.]"), "")?.toDoubleOrNull() ?: 0.0
            numericPrice <= maxPrice
        }
        return DestinationSequentialIterator(filtered)
    }
}

class DestinationSequentialIterator(private val list: List<Destination>) : CustomIterator<Destination> {
    private var index = 0

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): Destination {
        if (!hasNext()) {
            throw NoSuchElementException("No more destinations available in iterator")
        }
        return list[index++]
    }
}
