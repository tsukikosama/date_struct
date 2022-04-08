package com.miku.struct.hastTab;

/**
 * 自己实现一个hashtab
 */
public class MyHashTab {
    public static void main(String[] args) {
        MyHashTabArray list = new MyHashTabArray(7);
        list.add(new Emp(1,"wuhu"));
        list.add(new Emp(2,"wuhu2"));
        list.printAll();
        list.findById(2);
    }

    /**
     * 散列数组 用于管理链表的
     */
    public static class MyHashTabArray{
        private  EmpList[] empLists ;
        private  int size ;



        /**
         * 构造这个散列链表
         * @param size
         */
       public MyHashTabArray(int size){
             this.size = size;
             empLists = new EmpList[size];
             //为每个数组中的链表赋初始值
            for (int i = 0 ; i < size ; i++){
                empLists[i] = new EmpList();
            }

         }

        /**
         * 通过id找emp
         * @param id
         * @return
         */
         public Emp findById(int id){
            int no = Hashfun(id);
            Emp emp = empLists[no].findEmpt(id);
            if (emp==null){
                System.out.println("没找到");
            }else {
                System.out.println(emp.id+emp.name);
            }

             return emp;
         }
         //散列方法
        public int Hashfun(int value){
            return value % size;
        }

        /**
         * 添加emp方法
         * @param emp
         */
         public void add(Emp emp){
            int empNo = Hashfun(emp.id);

            //添加进对应的链表中
             empLists[empNo].add(emp);
         }
        /**
         * 输出全部
         */
        public void printAll(){
            for (int i = 0 ; i<size;i++){
                empLists[i].findAll();
            }
        }
    }

    public static class EmpList{
        //默认这个头为空
        public Emp head;

        public Emp findEmpt(int id){
            Emp curEmp = head;

            while (true){
                //找到了
                if (curEmp.id == id){
                    break;
                }
                //到末尾了 都没找到
                if (curEmp.next == null){
                    curEmp = null;
                    break;
                }
                //没找到 向下移动一个
                curEmp = curEmp.next;

            }
            return curEmp;
        }
        /**
         * 添加e'm'p
         * @param emp
         */
        //添加
        public void add(Emp emp){
            if (head == null){
                head = emp;
                return;
            }
            //临时的emp用于f辅助遍历  规定 链表默认是从小到大 插入的
            Emp curEmp = head;
            while (true){
                //这个没有最后一个就是遍历到了最后一个
                if (curEmp.next == null){
                    break;
                }
                curEmp = curEmp.next;
            }
            curEmp.next = emp;
        }
        //遍历全部
        public void findAll(){
            if (head == null){
                System.out.println("链表为空");
                return;
            }
            Emp curEmp = head;
            while (true){
                System.out.println("当前员工为:"+curEmp.id+"name:"+curEmp.name);
                if (curEmp.next == null){
                    break;
                }
                curEmp = curEmp.next;
            }
        }
    }
    /**
     * 一个雇用类
     */
    public static class Emp{
        public int id;
        public String name;
        public Emp next;
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

        public Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
