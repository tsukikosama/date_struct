package com.miku.struct.binaryTree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree();
        tree.setTree(arr);
        tree.preOrder();
    }
}
 class ArrBinaryTree{
    private int[] tree;

     public int[] getTree() {
         return tree;
     }

     public void setTree(int[] tree) {
         this.tree = tree;
     }

     public void preOrder(){
        this.preOrder(0);
    }
     /**
      * 用先序遍历的方法输出数组
      * @param index
      */
    public void preOrder(int index){
        if (tree == null || tree.length == 0){
            System.out.println("树为空");
        }
        System.out.println(tree[index]);
        if (tree.length > index*2+1){
            preOrder(index*2+1);
        }
        if (tree.length > index*2+2){
            preOrder(index*2+2);
        }
    }
}