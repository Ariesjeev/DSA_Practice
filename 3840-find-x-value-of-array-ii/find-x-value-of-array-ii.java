class Solution {

    static class Node {
        int product;     // Product of the whole segment % k
        int[] count;     // count[r] = number of prefixes with product % k == r

        Node(int k) {
            product = 1;       // Identity for multiplication
            count = new int[k];
        }
    }

    private int k;
    private Node[] tree;

    // Merge two adjacent segments:
    //
    // left | right
    //
    // Prefixes of the combined segment are:
    // 1. Prefixes completely inside left
    // 2. Whole left + a prefix of right
    private Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of the complete segment
        res.product = (left.product * right.product) % k;

        // 1. Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.count[r] += left.count[r];
        }

        // 2. Prefixes that include all of left
        //    and some prefix of right
        for (int r = 0; r < k; r++) {
            int newRemainder = (left.product * r) % k;
            res.count[newRemainder] += right.count[r];
        }

        return res;
    }

    private void build(int node, int l, int r, int[] nums) {

        if (l == r) {
            int value = nums[l] % k;

            tree[node].product = value;

            // The only prefix of a single-element segment
            // is the element itself.
            tree[node].count[value] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            value %= k;

            tree[node] = new Node(k);
            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {

        // Completely outside
        if (r < ql || l > qr) {
            return new Node(k);
        }

        // Completely inside
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node(k);
        }

        // Only remainder modulo k matters.
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // We need:
            //
            // nums[start..start]
            // nums[start..start+1]
            // ...
            // nums[start..n-1]
            //
            // These are exactly the prefixes of [start, n-1].
            Node res = query(1, 0, n - 1, start, n - 1);

            result[i] = res.count[x];
        }

        return result;
    }
}