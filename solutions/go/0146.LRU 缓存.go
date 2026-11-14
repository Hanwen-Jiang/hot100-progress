/*
 * @lc app=leetcode.cn id=146 lang=golang
 *
 * [146] LRU 缓存
 */

// @lc code=start
import "container/list"

type item struct {k, v int}

type LRUCache struct {
    cap int
	ll *list.List
	cache map[int]*list.Element
}


func Constructor(capacity int) LRUCache {
    return LRUCache{capacity, list.New(), map[int]*list.Element{}}
}


func (this *LRUCache) Get(key int) int {
    if e, ok := this.cache[key]; ok {
		this.ll.moveToFront(e)
		return e.Value.(*item).v
	}
	return -1
}


func (this *LRUCache) Put(key int, value int)  {
    if e, ok := this.cache[key]; ok {
		e.Value.(*item).v = value
		this.ll.moveToFront(e)
		return
	}
	val := this.ll.pushToFront(&item{key, value})
	this.cache[key] = val
	if this.capacity > this.ll.Len() {
		old := this.ll.Back()
		this.ll.Remove(old)
		delete(this.cache, old.Value.(*item).k)
	}
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
// @lc code=end

