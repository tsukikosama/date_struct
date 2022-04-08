package com.miku.struct.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        MyBinarySortTree tree = new MyBinarySortTree();
        for (int k : arr){
            tree.addNode(new Node(k));
        }
        tree.midORder();

        tree.delNode(2);
        tree.midORder();
        tree.delNode(5);
        tree.delNode(5);
        tree.delNode(9);
        tree.delNode(12);
        tree.delNode(1);
        tree.delNode(7);
        System.out.println("++"+tree.getRoot());
        tree.delNode(3);
        tree.delNode(10);
        tree.midORder();
    }


   static class MyBinarySortTree{
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
