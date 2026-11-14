package code6;

/**
<p>将一个给定字符串 <code>s</code> 根据给定的行数 <code>numRows</code> ，以从上往下、从左到右进行&nbsp;Z 字形排列。</p>

<p>比如输入字符串为 <code>"PAYPALISHIRING"</code>&nbsp;行数为 <code>3</code> 时，排列如下：</p>

<pre>
P   A   H   N
A P L S I I G
Y   I   R</pre>

<p>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：<code>"PAHNAPLSIIGYIR"</code>。</p>

<p>请你实现这个将字符串进行指定行数变换的函数：</p>

<pre>
string convert(string s, int numRows);</pre>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "PAYPALISHIRING", numRows = 3
<strong>输出：</strong>"PAHNAPLSIIGYIR"
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong>s = "PAYPALISHIRING", numRows = 4
<strong>输出：</strong>"PINALSIGYAHRPI"
<strong>解释：</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "A", numRows = 1
<strong>输出：</strong>"A"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
 <li><code>s</code> 由英文字母（小写和大写）、<code>','</code> 和 <code>'.'</code> 组成</li> 
 <li><code>1 &lt;= numRows &lt;= 1000</code></li> 
</ul>

<div><details><summary>Related Topics</summary><div><li>字符串</li></div></details></div>
<div><li>👍 2619</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public String convert(String s, int numRows) {
//        if (numRows == 1) return s;
//        int gapNum = Math.max(0, numRows - 2), znNum = gapNum + numRows;
//        int len = s.length();
//        StringBuilder result = new StringBuilder();
//        StringBuilder[] results = new StringBuilder[numRows];
//        for (int i = 0; i < numRows; i++) results[i] = new StringBuilder();
//        for (int i = 0; i < len; i++) {
//            int zi = i % znNum;
//            int xi = zi < numRows ? zi : numRows - 2 - zi + numRows;
//            results[xi].append(s.charAt(i));
//        }
//        for (StringBuilder sb : results) result.append(sb);
//        return result.toString();
//    }

    public String convert(String s, int numRows) {
        if (numRows <= 1 || s.length() <= numRows) return s;

        int n = s.length();
        char[] res = new char[n];
        int index = 0;
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                res[index++] = s.charAt(j + i);
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) res[index++] = s.charAt(j + cycleLen - i);
            }
        }
        return new String(res);
    }

    static void main() {
        code6.Solution solution = new code6.Solution();
        System.out.println(solution.convert("PAYPALISHIRING", 4));
//        System.out.println(solution.convert("A", 1));
//        System.out.println(solution.convert("AB", 1));
        System.out.println(solution.convert("ABAB", 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
