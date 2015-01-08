package test;

/**
 * Created by qingcunz on 12/6/14.
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// http://stackoverflow.com/questions/24793567/algorithm-sum-of-distances-between-every-two-nodes-of-a-binary-search-tree-in-o
public class SumDistances {
    private final int[] left;
    private final int[] right;
    private final int[] cnt;
    private final int[] ret;
    private final int[] dis;
    private final int nNode;

    public SumDistances(int n) {// n is the number of node
        left = new int[n];
        right = new int[n];
        cnt = new int[n];
        ret = new int[n];
        dis = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        nNode = n;
    }

    void add(int a, int b) {
        if (left[b] == -1) {
            left[b] = a;
        } else {
            right[b] = a;
        }
    }

    int cal() {
        _cal(0);//assume root's idx is 0
        return ret[0];
    }

    void _cal(int idx){
        if (left[idx] == -1 && right[idx] == -1) {
            // leaf node
            cnt[idx] = 1;
            dis[idx] = 0;
            ret[idx] = 0;
        } else if (left[idx] != -1  && right[idx] == -1){
            // right child is null
            _cal(left[idx]);
            cnt[idx] = cnt[left[idx]] + 1;
            dis[idx] = dis[left[idx]] + cnt[left[idx]];
            ret[idx] = ret[left[idx]] + dis[idx];
            //left[idx] == -1 and right[idx] != -1 is impossible, guaranteed by add(int,int)
        } else {
            _cal(left[idx]);
            _cal(right[idx]);
            cnt[idx] = cnt[left[idx]] + 1 + cnt[right[idx]];
            dis[idx] = dis[left[idx]] + dis[right[idx]] + cnt[left[idx]] + cnt[right[idx]];
            ret[idx] = dis[idx] + ret[left[idx]] + ret[right[idx]] + 2*cnt[left[idx]]*cnt[right[idx]] + dis[left[idx]]*cnt[right[idx]] + dis[right[idx]]*cnt[left[idx]];
        }
    }

    public static void main(String[] args) {
        SumDistances bst1 = new SumDistances(3);
        bst1.add(1, 0);
        bst1.add(2, 0);
        //   (0)
        //  /   \
        //(1)   (2)
        System.out.println(bst1.cal());

        SumDistances bst2 = new SumDistances(5);
        bst2.add(1, 0);
        bst2.add(2, 0);
        bst2.add(3, 1);
        bst2.add(4, 1);
        //       (0)
        //      /   \
        //    (1)   (2)
        //   /   \
        // (3)   (4)
        //0 -> 1:1
        //0 -> 2:1
        //0 -> 3:2
        //0 -> 4:2
        //1 -> 2:2
        //1 -> 3:1
        //1 -> 4:1
        //2 -> 3:3
        //2 -> 4:3
        //3 -> 4:2
        //2*4+3*2+1*4=18
        System.out.println(bst2.cal());

        System.out.println(sumDistance(init()));
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();

        root.left.left = new TreeNode();
        root.left.right = new TreeNode();
        return root;
    }

    public static int sumDistance(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();

        int n = count(root, map);

        int res = 0;
        for(TreeNode node : map.keySet()) {
            if(node.left != null) {
                int left = map.get(node.left);
                int right = map.get(node.right);
                res += left * (n-left);
                res += right * (n-right);
            }
        }
        return res;
    }

    private static int count(TreeNode root, Map<TreeNode, Integer> map) {
        if(root == null)
            return 0;

        int left = count(root.left, map);
        int right = count(root.right, map);

        map.put(root, left+right+1);
        return left+right+1;
    }

    private static class TreeNode {
        TreeNode left, right;
    }
}