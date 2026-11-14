package code76;

/**
<p>给定两个字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>，长度分别是&nbsp;<code>m</code> 和&nbsp;<code>n</code>，返回 s 中的&nbsp;<strong>最短窗口 <span data-keyword="substring-nonempty">子串</span></strong>，使得该子串包含 <code>t</code> 中的每一个字符（<strong>包括重复字符</strong>）。如果没有这样的子串，返回空字符串<em>&nbsp;</em><code>""</code>。</p>

<p>测试用例保证答案唯一。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
<strong>输出：</strong>"BANC"
<strong>解释：</strong>最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", t = "a"
<strong>输出：</strong>"a"
<strong>解释：</strong>整个字符串 s 是最小覆盖子串。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", t = "aa"
<strong>输出:</strong> ""
<strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>m == s.length</code></li> 
 <li><code>n == t.length</code></li> 
 <li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li> 
 <li><code>s</code> 和 <code>t</code> 由英文字母组成</li> 
</ul>

<p>&nbsp;</p> 
<strong>进阶：</strong>你能设计一个在 
<code>O(m + n)</code> 时间内解决此问题的算法吗？

<div><details><summary>Related Topics</summary><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></details></div>
<div><li>👍 3508</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[58], window = new int[58];
        int left = 0, right, required = 0, formed = 0, bestLen = Integer.MAX_VALUE, bestStart = 0;
        for (int i = 0; i < t.length(); i++) {
            int cur = t.charAt(i) - 'A';
            if (need[cur] == 0) required++;
            need[cur]++;
        }
        for (right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'A';
            window[c]++;
            if (need[c] > 0 && window[c] == need[c]) formed++;
            while (formed == required) {
                if (right - left + 1 < bestLen) {
                    bestStart = left;
                    bestLen = right - left + 1;
                }
                int cur = s.charAt(left) - 'A';
                window[cur]--;
                if (window[cur] < need[cur]) formed--;
                left++;
            }
        }
        return bestLen==Integer.MAX_VALUE?"":s.substring(bestStart,bestStart+bestLen);
    }

    static void main() {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("coobdafceeaxab", "abcz"));
        System.out.println(solution.minWindow("cabwefgewcwaefgcf", "cae"));
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(solution.minWindow("a", "a"));
        System.out.println(solution.minWindow("a", "aa"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
