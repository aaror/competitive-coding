package ll23mar;



public class floyd {
	private Node head;
	public static class Node {
		public int data;
		public Node next;
	
		Node(int d) {
			  d=data;
			  next=null;
		  }
	}

	public void display() {
		Node temp = null;
		while(temp!=null) {
			System.out.print(temp.data + "-> ");
			temp = temp.next;
		}
		System.out.println();
	}
public static boolean detectloop(Node node) {
	Node slow=node;
	Node fast=node;
	
	while(slow!=null && fast!=null&&fast.next!=null) {
		slow=slow.next;
		fast=fast.next.next;
		if(fast==slow) {
			removal(slow,fast);
			return true;
		}
		
	}
	return false;
}
public static void removal(Node slow,Node fast) {
	Node prev=fast;
	while(slow!=fast) {
		slow=slow.next;
		prev=fast;
		fast=fast.next;
				
	}
	System.out.println();
	
}

	public static void main(String[] args) {
		floyd f=new floyd();
		Node n1=new Node(10);
		Node n2=new Node(20);
		Node n3=new Node(30);
		Node n4=new Node(40);
		Node n5=new Node(50);
		Node n6=new Node(60);
		Node n7=new Node(70);
		Node n8=new Node(80);
		Node n9=new Node(90);
		Node n10=new Node(100);
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		n5.next=n6;
		n6.next=n7;
		n7.next=n8;
		n8.next=n9;
		n9.next=n10;
	n10.next=null;
		n10.next=n5;	
		//n10.next=null;
		boolean temp=detectloop(n1);
		System.out.println(temp);
		

		
		
		
		
		
		
		
		
		
	}

}
