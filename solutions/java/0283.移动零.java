package code283;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
<p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>

<p><strong>请注意</strong>&nbsp;，必须在不复制数组的情况下原地对数组进行操作。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = <span><code>[0,1,0,3,12]</code></span>
<strong>输出:</strong> <span><code>[1,3,12,0,0]</code></span>
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = <span><code>[0]</code></span>
<strong>输出:</strong> <span><code>[0]</code></span></pre>

<p>&nbsp;</p>

<p><strong>提示</strong>:</p> 
<meta charset="UTF-8" />

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>-2<sup>31</sup>&nbsp;&lt;= nums[i] &lt;= 2<sup>31</sup>&nbsp;- 1</code></li> 
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你能尽量减少完成的操作次数吗？</p>

<div><details><summary>Related Topics</summary><div><li>数组</li><li>双指针</li></div></details></div>
<div><li>👍 2826</li><li>👎 0</li></div>
*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int len = nums.length, index = 0;
        int[] res = new int[len];
        for (int num : nums) if (num != 0) res[index++] = num;
        System.arraycopy(res, 0, nums, 0, len - 1 + 1);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0; i < nums.length; i++) {
            int k = nums[i];
            if (k > 0) return res;
            if (i>0 && nums[i-1] == nums[i]) continue;
            int left = i+1, right = nums.length-1;
            k += nums[left] + nums[right];
            while (left < right) {
                if (k == 0) {
                    res.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                }
                else if (k > 0) right--;
                else left++;
            }
            // -4 -1 -1 0 1 2
        }
        return res;
    }

    static void main() {
        Solution solution = new Solution();
        solution.moveZeroes(new int[]{0,1,0,3,12});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
