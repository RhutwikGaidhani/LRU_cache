import java.util.*;

public class cache <T> {
    private final int capacity;
    private int size;

    private final Map<String, Node> hashmap;
    private final DoublyLinkedList internalQueue;

    cache(final int capacity){
        this.capacity=capacity;
        this.hashmap=new HashMap<>();
        this.internalQueue=new DoublyLinkedList();
        this.size=0;
    }

    // get function
    public T get(final String key){
        // feting data from hashmap using key passed by user
        Node node=hashmap.get(key);
        if(node!=null){
            // since getting accessed or used moving value to front
            internalQueue.moveNodeToFront(node);
            return hashmap.get(key).value;
            // return node.value;
        }
        return null;
    }
    // put finction
    public void put(final String key, final T value ){

        // check already exist if yes replace value
        Node currentNode=hashmap.get(key);
        if(currentNode!=null){
            currentNode.value=value;
            // since accessed move to front
            internalQueue.moveNodeToFront(currentNode);
        }

        // not present already but sized reached capacity
        if(size==capacity){
            String rearNodeKey= internalQueue.getRearKey();
            internalQueue.removeNodeFromRear();
            hashmap.remove(rearNodeKey);
            size--;
        }
        Node node=new Node(key,value);
        internalQueue.addNodeToFront(node);
        hashmap.put(key,node);
        size++;
    }

    // creating nodes for stroing value
    private class Node{
        String key;
        T value;
        Node next,prev;
        public Node(final String key, final T value){
            this.key=key;
            this.value=value;
            this.next=null;
            this.prev=null;
        }
    }

    // creating doubly linked list to access data from both from both front and back so that delete direct from back and access fromfront too
    private class DoublyLinkedList{
        private Node front,rear;
        public DoublyLinkedList(){
            front=rear=null;
        }

    private void addNodeToFront(final Node node){
        if(rear==null){
            front=rear=node;
            return;
        }
        node.next=front;
        front.prev=node;
        front=node;
    }
    public void moveNodeToFront(final Node node){
        // detaching first from present location.......
        if(front==node){
            return;
        }
        // what if node passed is rear so no prev
        if(node==rear){
            rear=rear.prev;
            rear.next=null;
        }else{
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
        // add node now to front.......
        node.prev=null;
        node.next=front;
        front.prev=node;
        front=node;
    }
    public void removeNodeFromRear(){
        if(rear==null){
            return;
        }
        System.out.println("removing key: "+ rear.key);
        if(front==rear){
            front=rear=null;
        }else{
            rear=rear.prev;
            rear.next=null;
        }
    }
    private String getRearKey(){
        return rear.key;
    }
    }
}
