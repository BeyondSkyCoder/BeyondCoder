__author__ = 'BeyondSky'

# circular linked list + dictionary

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.hm = {}
        self.CAPACITY = capacity
        self.head = Node(-1, -1)    # dummy head
        self.head.next = self.head
        self.head.prev = self.head
        self.size = 0

    def get(self, key):
        """
        :rtype: int
        """
        node = self.hm.get(key)

        if node is None:
            return -1
        else:
            self.detach_entry(node)
            self.add_first(node)
            return node.val

    def set(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: nothing
        """
        node = self.hm.get(key)
        if node is not None:
            self.detach_entry(node)
            node.val = value
            self.add_first(node)
        else:
            if self.size == self.CAPACITY:
                self.remove_last()
                self.size -= 1

            node = Node(key, value)
            self.add_first(node)
            self.size += 1
            self.hm[key] = node

    def add_first(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    def remove_last(self):
        temp = self.head.prev
        if temp is None:
            return
        temp.prev.next = self.head
        self.head.prev = temp.prev
        temp.prev = None
        temp.next = None
        del self.hm[temp.key]

    def detach_entry(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev


class Node:
    key = None
    val = None
    prev = None
    next = None
    def __init__(self, key, val):
        self.key = key
        self.val = val


if __name__ == "__main__":
    outer = LRUCache(1)
    outer.set(2, 1)
    print(outer.get(2))
    outer.set(3, 2)
    print(outer.get(2))
    print(outer.get(3))


