
# Basic Utilities

## Ordering
`Ordering` 是 Guava 的 `Comparator` 类，可用于创建复杂的 `Comparator`。

### 创建



#Caches
Caches 用于缓存一些昂贵的对象。Cache 类似于 `ConcurrentMap`，但不完全一样。`ConcurremtMap` 会一直保存对象，而 `Cache` 则会自动删除对象，以节省内存开支。当然，即使 `LoadingCache` 不自动删除对象，在许多场合依然很有用。

`Guava` 缓存保存在本地 RAM，如果这个无法满足你，可以使用 [Memcached](http://memcached.org/)