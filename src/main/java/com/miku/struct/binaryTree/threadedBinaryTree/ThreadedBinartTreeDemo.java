package com.miku.struct.binaryTree.threadedBinaryTree;

/**
 * 线索二叉树 比普通的二叉树 多了空的左右指针可以指向前驱和后继 前驱上一个节点 后继是父节点
 */
public class ThreadedBinartTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode node1 = new HeroNode(1,"cc");
        HeroNode node2 = new HeroNode(3,"tt");
        HeroNode node3 = new HeroNode(6,"xx");
        HeroNode node4 = new HeroNode(8,"dd");
        HeroNode node5 = new HeroNode(10,"yy");
        HeroNode node6 = new HeroNode(14,"miku");
        node1.setLeftNode(node2);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node1.setRightNode(node3);
        node3.setLeftNode(node6);
        binaryTree.setRootNode(node1);
        binaryTree.ThreadedBinaryTree();
        HeroNode rightNode = node5.getRightNode();

        HeroNode leftNode = node5.getLeftNode();
//        System.out.println("5号节点的前驱是"+leftNode);
//        System.out.println("5号节点的后继是"+rightNode);
        binaryTree.printAll();
    }
    public static class BinaryTree{
        private HeroNode rootNode;
        //这个是指向当前节点的前一个节点
        private HeroNode pre = null;
        public HeroNode getRootNode() {
            return rootNode;
        }

        public void setRootNode(HeroNode rootNode) {
            this.rootNode = rootNode;
        }

        public void printAll(){
            HeroNode temp = rootNode;
            while (temp != null){
                //去找到开头那个 leftType = 1 的点
                while (temp.leftNodeType != 1){
                    temp = temp.getLeftNode();
                }
                //输出当前节点 就是左子树
                System.out.println(temp);
                //一直递归去寻找右指针的类型是1 的 就代表右下一个 一直输出
                while (temp.rightNodeType == 1){
                    temp = temp.rightNode;
                    System.out.println(temp);
                }
                //如果没有右指针类型为1的 就是输出完了 跳转到右指数继续循环
                temp = temp.rightNode;
            }
        }

        public void ThreadedBinaryTree(){
            this.ThreadedBinaryTree(rootNode);
        }

        public void ThreadedBinaryTree(HeroNode node){
            //判断这个节点是否为空
            if (node == null){
                System.out.println("树为空");
                return;
            }
            //左边递归
            ThreadedBinaryTree(node.getLeftNode());
            //线索化
            //1.1左边
            if (node.getLeftNode() == null){
                //让当前的左指针 指向 前驱节点
                node.leftNode = pre;
                //修改指向的类型
                node.leftNodeType = 1;
            }
            //1.2右边  这个是通过下一次循环 然后连接上之前的线索
            if (pre != null && pre.getRightNode() == null){
                //让前驱的右指针 指向当前节点
                pre.setRightNode(node);
                pre.rightNodeType = 1 ;
            }
            //移动pre
            pre = node ;
            //右边递归
            ThreadedBinaryTree(node.getRightNode());
        }

        //删除节点
        public void delNode(int id){
            //先判断这个节点是不是为空
            if (rootNode == null){
                System.out.println("树为空");
            }else {
                //判断头节点是不是要删除的 不判断之后就没有机会了  如果是要删除的就置空
                if (rootNode.id == id){
                    rootNode = null;
                }else {
                    rootNode.delNode(id);
                }
            }
        }

        /**
         * 先去查找
         * @return
         */
        public HeroNode preSearch(int id){
           HeroNode heroNode = rootNode.preSearch(id);

            return heroNode;
        }
        /**
         *  中序查找
         * @return
         */
        public HeroNode midSearch(int id){
            HeroNode heroNode = rootNode.midSearch(id);

            return heroNode;
        }
        /**
         *  后续查找
         * @return
         */
        public HeroNode postSearch(int id){
            HeroNode heroNode = rootNode.postSearch(id);

            return heroNode;
        }
        //先序遍历
        public void preOrder(){
            if (rootNode!=null){
                this.rootNode.preOrder();
            }else {
                System.out.println("树为空");
            }
        }
        //中序遍历
        public void midOrder(){
            if (rootNode!=null){
                this.rootNode.midOrder();
            }else {
                System.out.println("树为空");
            }
        }
        //后序遍历
        public void postOrder(){
            if (rootNode!=null){
                this.rootNode.postOrder();
            }else {
                System.out.println("树为空");
            }
        }
    }

    /**
     * 二叉树的节点
     */
    public static class HeroNode{
        private int id ;
        private String name;
        private HeroNode leftNode;
        private HeroNode rightNode;
        //表明左右指针是指向前驱还是父节点  默认 0 是指向父节点 1 是指向 前驱或者后继节点的
        private int leftNodeType = 0;
        private int rightNodeType = 0;


        /**
         * 先序遍历删除节点 如果是子节点直接删除 如果不是子节点 把那一部分都删除
         */
        public void delNode(int id){
            //先判断左节点是不是要删除的
            //一个防止控制在 一个是判断条件
            if (this.leftNode != null &&this.leftNode.id == id){
                this.leftNode = null;
                return;
            }
            //判断右节点是不是要删除的
            if (this.rightNode != null && this.rightNode.id == id){
                this.rightNode = null;
                return;
            }
            //两个节点都不是 就进入到下一个节点继续判断之后的是不是
            //左边不为空进入左递归
            if (this.leftNode != null){
                this.leftNode.delNode(id);
            }
            //右边不为空进入又递归
            if (this.rightNode != null){
                this.rightNode.delNode(id);
            }


        }

        /**
         * 先序查找
         */
        public HeroNode preSearch(int id){
            System.out.println("进入查找i");
            //当前节点就是 直接返回
            if (this.id == id){
                return this;
            }
            //定义一个中间遍历判断是否找到了
            HeroNode node = null;
            //判断左节点是否为空
            if (this.leftNode != null){
                node = this.leftNode.preSearch(id);
            }
            if (node != null){
                return node;
            }
            //还说没有找到 就开始右节点遍历
            if (this.rightNode != null){
                node = this.rightNode.preSearch(id);
            }
            //不管有没有找到都需要返回
            return node;
        }
        /**
         * 中序查找
         * @return
         */
        public HeroNode midSearch(int id){
            //判断是否找到的节点
            HeroNode node = null;
            //判断左子树是否为空
            if (this.leftNode != null){
                node = this.leftNode.midSearch(id);
            }
            //如果找到了就直接返回
            System.out.println("进入查找");
            if (node != null){
                return node;
            }
            //左边没找到 判断中间这个是不是
            if (this.id == id){
                return  this;
            }
            //还是没找到就开始寻找右边节点的
            if (this.rightNode != null){
                node = this.rightNode.midSearch(id);
            }
            return node;

        }

        /**
         * 后续查找
         */
        public HeroNode postSearch(int id){
            //判断是否找到的节点
            HeroNode node = null;
            //判断左子树是否为空
            if (this.leftNode != null){
                node = this.leftNode.postSearch(id);
            }
            //如果找到了就直接返回
            if (node != null){
                return node;
            }
            //还是没找到就开始寻找右边节点的
            if (this.rightNode != null){
                node = this.rightNode.postSearch(id);
            }

            if (node != null){
                return node;
            }
            System.out.println("进入查找");
            return this.id == id?this:node;
        }
        /**
         * 先序遍历 中左右
         */
        public void preOrder(){
            System.out.println(this);
            if (this.leftNode != null){
                this.leftNode.preOrder();
            }
            if (this.rightNode != null){
                this.rightNode.preOrder();
            }
        }
        /**
         * 中序遍历 左中右
         */
        public void midOrder(){
            if (this.leftNode != null){
                this.leftNode.midOrder();
            }
            System.out.println(this);
            if (this.rightNode != null){
                this.rightNode.midOrder();
            }
        }
        /**
         * 后序遍历  左右中
         */
        public void postOrder(){
            if (this.leftNode != null){
                this.leftNode.postOrder();
            }
            if (this.rightNode != null){
                this.rightNode.postOrder();
            }
            System.out.println(this);
        }

        /**
         * 默认左右是为空的
         * @param id
         * @param name
         */
        public HeroNode(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HeroNode getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(HeroNode leftNode) {
            this.leftNode = leftNode;
        }

        public HeroNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(HeroNode rightNode) {
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
