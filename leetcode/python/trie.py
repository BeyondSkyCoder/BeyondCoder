__author__ = 'BeyondSky'


class Trie:
    def __init__(self):
        self.root = TrieNode('z')

    def add_word(self, word):
        node = self.root

        for c in word:
            child = node.children.get(c)
            if child is None:
                child = TrieNode(c)
                child.father = node
                node.children[c] = child
            node = child
        node.isWord = True

    def delete(self, word):
        node = self.root
        queue = []
        for c in word:
            queue.append((c, node))
            child = node.children.get(c)
            if child is None:
                return False
            node = child
        if node.isWord is False:
            return False
        if len(node.children):
            node.isWord = False
        else:
            for letter, node in reversed(queue):
                del node.children[c]
                if len(node.children) or node.isWord:
                    break

        return True



class TrieNode:

    def __init__(self, value):
        self.val = value
        self.isWord = False
        self.children = dict()  # use dict to save Trie Tree structure
        self.father = None

    def get_word(self):
        cur = self
        s = ''
        while cur.father is not None:
            s += str(cur.val)
            cur = cur.father
        return s[::-1]    # reverse by slice trick