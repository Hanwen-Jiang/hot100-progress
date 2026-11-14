import java.util.*;

/**
<p>以数组 <code>intervals</code> 表示若干个区间的集合，其中单个区间为 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 。请你合并所有重叠的区间，并返回&nbsp;<em>一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>输出：</strong>[[1,6],[8,10],[15,18]]
<strong>解释：</strong>区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[4,5]]
<strong>输出：</strong>[[1,5]]
<strong>解释：</strong>区间 [1,4] 和 [4,5] 可被视为重叠区间。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>intervals = [[4,7],[1,4]]
<b>输出：</b>[[1,7]]
<b>解释：</b>区间 [1,4] 和 [4,7] 可被视为重叠区间。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>intervals[i].length == 2</code></li> 
 <li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><details><summary>Related Topics</summary><div><li>数组</li><li>排序</li></div></details></div>
<div><li>👍 2727</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        List<int[]> res = new ArrayList<>();
        int[] last = intervals[0];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0] > last[1]){
                res.add(last);
                last = intervals[i];
            } else {
                last[1] = Math.max(last[1],intervals[i][1]);
            }
            System.out.println();
        }
        res.add(last);
        return res.toArray(new int[res.size()][]);
    }

    static void main() {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.merge(new int[][]{{4, 7}, {1, 4}})));
        System.out.println(Arrays.deepToString(solution.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(solution.merge(new int[][]{{1, 4}, {4, 5}})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
