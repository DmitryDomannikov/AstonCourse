package task2.HashAndList;

public class Main {
    public static void main(String[] args) {
        MyLInkedList myLInkedList = new MyLInkedList();
        myLInkedList.add(1);
        myLInkedList.add(2);
        myLInkedList.add(10);

        System.out.println(myLInkedList);
        System.out.println(myLInkedList.get(0));
        System.out.println(myLInkedList.get(1));
        System.out.println(myLInkedList.get(2));

        myLInkedList.remove(1);
        System.out.println(myLInkedList);
        MyLInkedList list = new MyLInkedList();
        list.add(18);
        list.add(22);
        myLInkedList.addAll(list);
        System.out.println(myLInkedList);
    }
}
