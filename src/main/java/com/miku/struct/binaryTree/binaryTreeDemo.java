package com.miku.struct.binaryTree;

public class binaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"cc");
        HeroNode node4 = new HeroNode(2,"dd");
        HeroNode node3 = new HeroNode(3,"vv");
        HeroNode node2 = new HeroNode(4,"xx");
        HeroNode node5 = new HeroNode(5,"zzz");
        binaryTree.setRootNode(root);
        root.setLeftNode(node4);
        root.setRightNode(node3);
        node3.setLeftNode(node5);
        node3.setRightNode(node2);
//        binaryTree.preOrder();

//        binaryTree.midOrder();

//            binaryTree.postOrder();


//        HeroNode heroNode = binaryTree.preSearch(5);
//        if (heroNode != null){
//            System.out.println(heroNode);
//        }else {
//            System.out.println("没找到");
//        }
        //删除前 前需遍历
        System.out.println("前");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        //删除后 前序遍历
        System.out.println("后");
        binaryTree.preOrder();
    }



    public static class BinaryTree{
        private HeroNode rootNode;

        public HeroNode getRootNode() {
            return rootNode;
        }

        public void setRootNode(HeroNode rootNode) {
            this.rootNode = rootNode;
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
