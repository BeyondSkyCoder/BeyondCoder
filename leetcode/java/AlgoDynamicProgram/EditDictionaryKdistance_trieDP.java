package AlgoDynamicProgram;


public class EditDictionaryKdistance_trieDP {
    /*
     * EditDistance is K with dictionary,
     *
     * standard spelling correction algorithm: http://blog.faroo.com/2012/06/07/improved-edit-distance-based-s
     *
     * TrieTree should be solution of preparation of word list for efficiency
     * I have a feeling that when the question is about a set of things and a target, the set should be
     * prepared for repeated calls.
     */
    // Algorithm: build the TrieTree Dictionary
    
    public TrieNodeHM constructTire(String[] dict) {

        TrieNodeHM root = new TrieNodeHM(null, ' ');
        for (String aDict : dict) {
            TrieNodeHM ptr = root;

            for (int j = 0; j < aDict.length(); j++) {
                // traverse the tree to add all chars in dict[i]
                if (ptr.getChildFromChar(aDict.charAt(j)) == null) {
                    ptr.addChar(aDict.charAt(j));
                }
                ptr = ptr.getChildFromChar(aDict.charAt(j));
            }

            // set word directly and save the word
            ptr.isWord = true;
            ptr.word = aDict;
        }
        return root;
    }

    // TBD
    public void EditDictionaryKDistance_find_print(String target, TrieNodeHM ptr, int k) {

        int m = target.length() + 1;            // num of char in target + 1
        int n = TrieNodeHM.counter;    // number of char in dictionary + 1

        // edit distance matrix
        int[][] matrix = new int[m][n];
        int i, j;
        for (i = 0; i < m; i++) matrix[i][0] = i;
        for (j = 0; j < n; j++) matrix[0][j] = TrieNodeHM.findNode(j).depth;


        for (j = 1; j < n; j++) {
            for (i = 1; i < m; i++) {

                TrieNodeHM node = TrieNodeHM.findNode(j);

                if (target.charAt(i - 1) == node.c) {

                    matrix[i][j] = matrix[i - 1][node.p.no];

                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j] + 1, matrix[i][node.p.no] + 1);

                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][node.p.no] + 1);
                }

                if (i == m - 1 && node.isWord && matrix[i][j] == k) {
                    System.out.println(node.word);
                }
            }
        }
    }

    public static void main(String[] args) {
        EditDictionaryKdistance_trieDP outerKD = new EditDictionaryKdistance_trieDP();

        String[] input = new String[]{"baa", "aaa", "zz", "abcd", "bad", "adbcd"};
        String target = "a";

        TrieNodeHM root = outerKD.constructTire(input);

        outerKD.EditDictionaryKDistance_find_print(target, root, 3);
    }
}

/*
class dummy {


1: 直接遍历完整计算edit distance. 285 clocks.
2: 直接遍历，计算edit distance到 >k 就返回. 149 clocks.
3: TrieTree+shortcut edit distance. Build trie: 606 clocks, process: 6 clocks.
http://stevehanov.ca/blog/index.php?id=114
4: Generate delete k transformation. Build dict: 17033 clocks. process: 0
clocks.
但这里不仅需要生成delete k的pattern, 还需要生成所有delete 1..k-1的pattern,
否则不能handle如(chrome brome)的case
http://blog.faroo.com/2012/06/07/improved-edit-distance-based-s


    int EditDistanceC(string s1, string s2) {
        vector<int> v (s1.size() + 1);
        iota(v.begin(), v.end(), 0);
        for (int len = 1; len <= s2.size(); ++len) {
            int topLeft = v[0];
            ++v[0];
            for (int len1 = 1; len1 <= s1.size(); ++len1) {
                int t = v[len1];
                v[len1] = min(topLeft, min(v[len1], v[len1 - 1])) + 1;
                if (s1[len1 - 1] == s2[len - 1]) {
                    v[len1] = min(topLeft, v[len1]);
                }
                topLeft = t;
            }
        }
        return v.back();
    }

    int EditDistanceWithThreshold(string s1, string s2, int threshold) {
        vector<int> v (s1.size() + 1);
        iota(v.begin(), v.end(), 0);
        for (int len = 1; len <= s2.size(); ++len) {
            int topLeft = v[0];
            ++v[0];
            bool underThreshold = v[0] <= threshold;
            for (int len1 = 1; len1 <= s1.size(); ++len1) {
                int t = v[len1];
                v[len1] = min(topLeft, min(v[len1], v[len1 - 1])) + 1;
                if (s1[len1 - 1] == s2[len - 1]) {
                    v[len1] = min(topLeft, v[len1]);
                }
                if (v[len1] <= threshold) {
                    underThreshold = true;
                }
                topLeft = t;
            }
            if (!underThreshold) {
                return threshold + 1;
            }
        }
        return v.back();
    }

    vector<string> correct1(string s, int d, vector<string>&dict) {
        vector<string> results;
        for (auto word : dict) {
            if (EditDistance(s, word) <= d) {
                results.push_back(word);
            }
        }
        return results;
    }

    vector<string> correct2(string s, int d, vector<string>&dict) {
        vector<string> results;
        for (auto word : dict) {
            if (EditDistanceWithThreshold(s, word, d) <= d) {
                results.push_back(word);
            }
        }
        return results;
    }


    void DeleteM(string s, int start, int m, int initM, vector<string>&results) {
        if (m != initM) {
            results.push_back(s);
        }
        if (m == 0) return;
        for (int i = start; i < s.size(); ++i) {
            string s1 = s;
            s1.erase(s1.begin() + i);
            DeleteM(s1, i, m - 1, initM, results);
        }
    }

    // Need to generate -1 to -m string, not only -m, otherwise
// for m = 2, chrome and brome can't be treated as match
    vector<string> DeleteM(string s, int m) {
        vector<string> results;
        DeleteM(s, 0, m, m, results);
        return results;
    }

    int main() {
        int threshold = 2;
        const char*dictFile = "british\brit-a-z.txt";
        ifstream input (dictFile);
        vector<string> wordList;
        string source = "chrome";
        {
            Clock clock ("Load word list");
            for (string line; getline(input, line); ) {
                wordList.push_back(line);
            }
        }
        {
            Clock clock ("iterate without shortcut");
            auto results = correct1(source, threshold, wordList);
            DumpStringVector(results);
            printf("n");
        }

        {
            Clock clock ("iterate with shortcut");
            auto results = correct2(source, threshold, wordList);
            DumpStringVector(results);
            printf("n");
        }

        TrieTree trie;
        {
            Clock clock ("build trie");
            for (string s : wordList) {
                trie.AddString(s.c_str());
            }
            printf("n");
        }
        {
            Clock clock ("TrieTree with shortcut");
            auto results = trie.FindAllEditDistanceUnderThreshold(source,
                    threshold);
            DumpStringVector(results);
            printf("n");
        }
        unordered_set<string> origDict;
        multimap<string, string> deleteMDict;
        {
            Clock clock ("Build Delete Deict");
            for (auto s : wordList) {
                origDict.insert(s);
                for (auto del : DeleteM(s, threshold)) {
                    deleteMDict.insert(make_pair(del, s));
                }
            }
            printf("n");
        }

        {
            Clock clock ("Delte dict");
            auto deleteSource = DeleteM(source, threshold);
            unordered_set<string> results;
            if (origDict.find(source) != origDict.end()) {
                results.insert(source);
            }

            {
                auto range = deleteMDict.equal_range(source);
                auto start = range.first;
                auto end = range.second;
                while (start != end) {
                    results.insert(start -> second);
                    ++start;
                }
            }
            for (string s : deleteSource) {
                if (origDict.find(s) != origDict.end()) {
                    results.insert(s);
                }
                auto range = deleteMDict.equal_range(s);
                auto start = range.first;
                auto end = range.second;
                while (start != end) {
                    if (EditDistanceWithThreshold(source, start -> second,
                            threshold) <= threshold) {
                        results.insert(start -> second);
                    }
                    ++start;
                }
            }
            DumpStringRange(results.begin(), results.end());
            printf("n");
        }

        return 0;

    }
}
*/
