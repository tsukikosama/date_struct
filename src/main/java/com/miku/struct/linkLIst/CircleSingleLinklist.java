package com.miku.struct;


/**
 * 这个是实现约瑟夫问题的 循环单链表
 */
public class CircleSingleLinklist {
    public static void main(String[] args) {
        CircleBoy list = new CircleBoy();
        list.initBoy(55);
        //list.printAll();
        list.goOut(14,12,55);
    }
}




class CircleBoy{
    Boy first = null;

    /**
     * 传教环形链表
     */
    void initBoy(int num){
        //辅助变量
        Boy curBoy = null;

        //添加一定个数的链表
        for (int i = 1 ; i<= num ;i++){
            Boy newBoy = new Boy(i);
            //代表生成第一个
            if (i == 1){
                first = newBoy;
                //添加进去一个
                first.setNext(newBoy);
                //指向自己形成环
                newBoy.setNext(first);
                //辅助指针移动到最新的一个
                curBoy = newBoy;
            }else {
                curBoy.setNext(newBoy);
                newBoy.setNext(first);
                curBoy = newBoy;
            }
        }
    }
    /**
     * 遍历环
     */
    void printAll(){
        if (first == null){
            System.out.println("为空");
            return;
        }
        //辅助遍历
        Boy curBoy = first;
        while (true){
            System.out.println(curBoy.getId());
            if (curBoy.getNext() != first){
                curBoy = curBoy.getNext();
            }else {
                System.out.println("输出完了");
                break;
            }
        }
    }

    /**
     *  约瑟夫问题出队
     * @param index  从哪个开始
     * @param count 数的个数
     * @param sum   总人数
     */
    public void goOut(int index , int count ,int sum) {
        //判断参数是否有误
        if (index < 1 || count > sum || count < 1){
            System.out.println("参数有误");
            return;
        }
        //获取一个辅助遍历 指向头的前一个
        Boy helpBoy = first;
        while (helpBoy.getNext() != first)
        {
            //判断是否是first的后一个
            helpBoy = helpBoy.getNext();
        }

        //移动到开始位置
        for (int i = 0 ; i < index-1 ; i++){
            first = first.getNext();
            helpBoy = helpBoy.getNext();
        }
        //开始出圈咯
        while (true){
            //只有一个了 自己跳出循环
            if (first.getId() == helpBoy.getId()){
                break;
            }else {
                //开始数数咯 并且移动到要出队的人
                for (int j = 0; j < count - 1; j++) {
                    first = first.getNext();
                    helpBoy = helpBoy.getNext();
                }
            }
            // 输出要出队的人 然后移动头到下一个 并且跟随helpboy变量
            System.out.println("出圈的是"+first.getId());
            first = first.getNext();
            helpBoy.setNext(first);
        }
        System.out.println("最后一个人是:"+first.getId());
    }
}

class Boy{
    private  int id;
    private Boy next;


    Boy(int id){
       this.id =id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
