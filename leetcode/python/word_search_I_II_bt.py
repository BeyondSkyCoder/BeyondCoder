__author__ = 'BeyondSky'

from trie import Trie
from trie import TrieNode

class WordsearchI(object):

    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        row = len(board)
        col = len(board[0])
        if row == 0 or col == 0:
            return False
        if row * col < len(word):
            return False

        visited = [ [0]*col for y in range(row) ]
        for i in range(row):
            for j in range(col):
                ret = self.word_search_helper(board, word, visited, i, j, 0)
                if ret is True:
                    return True;

        return False;


    def word_search_helper(self, board, w, visited, i, j, pos):
        if board[i][j] != w[pos]:
            return False

        if pos == len(w) - 1:
            return True

        row = len(board)
        col = len(board[0])

        pos += 1
        visited[i][j] = 1

        if i > 0 and visited[i-1][j] == 0 and self.word_search_helper(board, w, visited, i-1, j, pos):
            return True
        if i < row-1 and visited[i+1][j] == 0 and self.word_search_helper(board, w, visited, i+1, j, pos):
            return True
        if j > 0 and visited[i][j-1] == 0 and self.word_search_helper(board, w, visited, i, j-1, pos):
            return True
        if j < col-1 and visited[i][j+1] == 0 and self.word_search_helper(board, w, visited, i, j+1, pos):
            return True


        visited[i][j] = 0
        return False

class WordsearchII?:
    hs = set()

    def findWords(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """
        res = []
        row = len(board)
        col = len(board[0])
        if row == 0 and col == 0:
            return res

        visited = [[0] * col for x in range(row)]

        tr = Trie()
        for s in words:
            tr.add_word(s)

        root = tr.root

        for i in range(row):
            for j in range(col):
                # prune search earlier if word is not in trie
                idx = board[i][j]
                if root.children.get(idx) is None:
                    continue

                visited[i][j] = 1
                self.word_search_helper2_dfs(board, res, root.children[idx], visited, i, j)
                visited[i][j] = 0

        return sorted(res)

    def word_search_helper2_dfs(self, board, res, trie_node, visited, i, j):
        if trie_node.isWord is True:
            s = trie_node.get_word()
            if s not in self.hs:    # avoid dup to the res list
                self.hs.add(s)
                res.append(s)

        row = len(board)
        col = len(board[0])

        # UP
        if i > 0 and visited[i-1][j] == 0:
            idx = board[i-1][j]
            if trie_node.children.get(idx) is not None:
                visited[i-1][j] = 1
                self.word_search_helper2_dfs(board, res, trie_node.children[idx], visited, i-1, j)
                visited[i-1][j] = 0

        # DOWN
        if i < row-1 and visited[i+1][j] == 0:
            idx = board[i+1][j]
            if trie_node.children.get(idx) is not None:
                visited[i+1][j] = 1
                self.word_search_helper2_dfs(board, res, trie_node.children[idx], visited, i+1, j)
                visited[i+1][j] = 0

        # LEFT
        if j > 0 and visited[i][j-1] == 0:
            idx = board[i][j-1]
            if trie_node.children.get(idx) is not None:
                visited[i][j-1] = 1
                self.word_search_helper2_dfs(board, res, trie_node.children[idx], visited, i, j-1)
                visited[i][j-1] = 0

        # RIGHT
        if j < col-1 and visited[i][j+1] == 0:
            idx = board[i][j+1]
            if trie_node.children.get(idx) is not None:
                visited[i][j+1] = 1
                self.word_search_helper2_dfs(board, res, trie_node.children[idx], visited, i, j+1)
                visited[i][j+1] = 0


if __name__ == "__main__":
    outer = WordsearchII()
    matrix1 = [ ['a', 'b', 'd'], ['e', 'c', 'f']]
    matrix2 = ["a", "a"]
    print(outer.findWords(matrix2, ['a']))