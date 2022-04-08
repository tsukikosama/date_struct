package com.miku.struct.avltree;



/**
 * 平衡二叉树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10,11,7,6,8,9};
        AVLTree tree = new AVLTree();
        for (int k : arr){
            tree.addNode(new Node(k));
        }
        tree.midORder();
        //获取树的高度
        System.out.println("树的高度"+tree.root.treeHeight());
        System.out.println("左子树的高度"+tree.root.leftHeight());
        System.out.println("右子树的高度"+tree.root.rightHeight());
        System.out.println("左子树的左子树"+tree.root.left.left);
    }
    static class AVLTree{
        private Node root;

        public Node getRoot() {
            return root;
        }

        public void setRoot(Node root) {
            this.root = root;
        }

        /**
         * 删除节点
         */
        public void delNode(int value){
            //判断节点是否为空 不用删除了直接返回
            if (root == null){
                // System.out.println("节点为空");
                return;
            }else {
                //寻找到要删除的节点
                Node temp = searchNode(value);
                //没有找到就返回空
                if (temp == null) {

                    return;
                    //找到了 要判断这个节点是不是叶子节点
                }
                //判断这个节点是不是根节点
                if (root.left == null && root.right == null) {
                    this.root = null;
                    return;
                }

                //这个时候就是判断他是叶子节点  去获取他的父节点
               Node parentNode = parentNode(value);
                if (temp.left == null && temp.right == null) {
                    //判断他是父节点的左子树 还是右子树
                    if (parentNode.left != null && parentNode.left.value == value) {
                        //满足这个情况 要删除的节点就是父节点的左子树
                        parentNode.left = null;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        //满足这个条件 要删除的节点就是父节点的右子树
                        parentNode.right = null;
                    }
                    //要删除的节点 两边都不为空
                }else if (temp.left != null && temp.right != null){
                    //删除要删除的节点 并把最小的值 赋值给删除的位置 保持二叉排序树
                    int val = delNodes(temp);
                    temp.value = val;
                    //两边有一边为空去情况
                }else {
                    //判断是否有父节点 没有父节点就把根节点直接移动到要删除的元素的
                    if (parentNode!= null) {
                        //左子树 有 节点
                        if (parentNode.left != null) {
                            //要删除的左子树有节点
                            if (temp.left != null) {
                                parentNode.left = temp.left;
                            } else {
                                parentNode.left = temp.right;
                            }
                            //右子树是要删除的
                        }
                    }else {
                        root = temp.left;
                    }
                    //判断是否有父节点
                    if (parentNode!= null) {
                        //要删除的右子树有节点
                        if (temp.right != null) {
                            parentNode.right = temp.right;
                        } else {
                            parentNode.right = temp.left;
                        }
                    }else {
                        root = temp.left;
                    }

                    //两边都有节点的情况
                }
            }

        }

        /**
         * 删除节点并返回值
         * @param node
         * @return
         */
        public int delNodes(Node node){
            Node temp = node;
            //一直找到最小的
            while (temp.left!=null){
                //移动到最小的位置
                temp = temp.left;
            }
            //把要删除的值彻底过去
            delNode(temp.value);
            //把删除的值返回回去
            return temp.value;
        }

        /**
         * 通过值寻找id
         * @param value
         * @return
         */
        public Node searchNode(int value){
            //判断树是否为空 如果为空就直接返回空
            if (root == null){
                return null;
            }
            return root.search(value);
        }

        /**
         * 寻找父节点
         * @param value
         */
        public Node parentNode(int value){
            if (root == null){
                return null;
            }
            else {
                return root.searchParent(value);
            }

        }
        //添加方法
        public void addNode(Node node){

            //判断添加的节点是否为空
            if (root == null){
                this.root = node;

            }else {
                this.root.add(node);
            }
        }

        //中序输出树
        public void midORder(){
            if (root == null){
                System.out.println("树为空");
            }else {
                root.midOrder(root);
            }
        }
    }
    static class Node{
        private int value;
        private Node left;
        private Node right;

        //左旋转
        public void leftRotate(){
            //1.创建一个新的接单
            Node newNode = new Node(value);
            //2.把新节点的左子树设置为当前节点的左子树
            newNode.left = left;
            //3.把新节点的左子树 设子为当前节点的右子树的左子树
            newNode.right = right.left;
            //4.把当前节点的值替换为 右节点的值
            value = right.value;
            //5.把当前节点的右子树 设置为右子树的右子树
            right = right.right;
            //6.把当前结点的左子树设子为新的节点
            left= newNode;
        }

        /**
         * 右旋转
         */
        public void rightRotate(){
            //1.添加一个新的节点
            Node newNode = new Node(value);
            //新节点的右子树为当前的右子树
            newNode.right = right;
            //新节点的左节点为 当前的左子树的右节点
            newNode.left = left.right;
            //当前节点的值为左节点的值
            value = left.value;
            //当前节点的左节点为左节点的左节点
            left=left.left;
            //6.把当前节点的右子树设置为新的节点
            right = newNode;
        }
        //返回左子树的高度
        public int leftHeight(){
            if (left == null){
                return 0;
            }else {
                return left.treeHeight();
            }
        }
        //返回左子树的高度
        public int rightHeight(){
            if (right == null){
                return 0;
            }else {
                return right.treeHeight();
            }
        }
        /**
         * 以当前节点为根节点获取树的高度
         */
        public int treeHeight(){
            return Math.max(this.left==null?0:left.treeHeight(),this.right==null?0:right.treeHeight())+1;
        }

        /**
         * 查找节点
         * @param value
         * @return
         */
        public Node search(int value){
            //判断这个节点是否为空
            if (this.value == value){
                return this;
            }
            //如果不为空就开始寻找  当前的节点值 比要找的节点值大
            else if (this.value > value){
                //进入左子树 继续寻找
                //判断左子树是否为空
                if (this.left == null){
                    return null;
                }
                return this.left.search(value);
            }else {
                //判断右子树 是是否为空
                if (this.right == null){
                    return null;
                }
                return this.right.search(value);
            }

        }

        /**
         * 寻找要找的节点的父节点
         * @param value
         * @return
         */
        public Node searchParent(int value){

            //判断当前节点左右子树是否和需要找的节点值 相等  如果相等直接返回 不相等就判断值的大小 往左右子树递归
            if (( this.left != null && this.left.value == value) ||
                    (this.right != null &&this.right.value == value)){
                return this;
            }else {
                if (value < this.value &&this.left != null) {
                    //要找的值 小于 当前值
                    return this.left.searchParent(value);
                } else if (value >= this.value && this.right != null) {
                    //要找的值 大于或者等于 当前值
                    return this.right.searchParent(value);
                } else {
                    //到最后了 还是没有找到对应的值
                    return null;
                }
            }
        }


        //添加方法
        public void add(Node node){
            //判断这个node节点是否为空 如果为空就不添加
            if (node == null){
                System.out.println("节点为空添加失败");
            }
            //如果这个添加的节点不为空 就去判断添加到左节点还是右节点  左节点小 右节点大
            if (this.value > node.value){
                //添加进总结点 判断左节点死否为空  如果为空就直接添加
                if (this.left == null){
                    this.left = node;
                }else {
                    //不为空 左节点队规添加这个节点
                    this.left.add(node);
                }
            }else {
                //添加的节点大于或者等于当前节点
                //如果右节点为空就直接添加进右节点
                if (this.right == null){
                    this.right = node;
                }else {
                    this.right.add(node);
                }
            }

            //添加之后判断两边树的差 是否大于1
            if ((rightHeight() - leftHeight()) > 1){
                //右子树的左子树高度大于右子树的右子子树高度
                if (right!=null && right.leftHeight() > right.rightHeight()){
                    right.rightRotate();

                    leftRotate();
                }else {
                    leftRotate();
                }
                return;
            }

            if (leftHeight() - rightHeight() > 1 ){
                //左子树的右子树高度大于左子树的左子树高度
                if (left != null && left.rightHeight() > left.leftHeight()){
                    //先对这个节点的左节点进行左旋转
                    left.leftRotate();
                    //然后进行右选择
                    rightRotate();
                }else {
                    rightRotate();
                }
                return;
            }
        }

        //中序遍历
        public void midOrder(Node node){
            //先判断节点是否为空 如果为空就提示
            if (node == null){
                System.out.println("当前树为空");
            }
            //不为空去判断是否有左子树
            if (this.left != null){
                this.left.midOrder(node);
            }
            //输出中间节点
            System.out.println(this.value);

            //开始递归判断右节点
            if (this.right != null){
                this.right.midOrder(node);
            }

        }


        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
