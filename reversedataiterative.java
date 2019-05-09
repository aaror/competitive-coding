package linkedlistmar16;


public class reversedataiterative {

		private class Node {
			public int data;
			public Node next;
		}
		private Node head;
		private Node tail;
		private Node rdrleft;
		private int size;
	
	private Node getnodeat(int idx) {
		Node temp=head;
		for(int i=0;i<idx;i++) {
			temp=temp.next;
		}
		return temp;
		
	}
	public void addlast(int data) {
		if (size == 0) 
			handleaddwheresizeis0(data);
			//return;
		 else {
			Node nn = new Node();
			nn.data = data;
			nn.next = null;
			tail.next = nn;
			tail = nn;
			size++;
		}
	}
	private void handleaddwheresizeis0(int data) {
		Node nn = new Node();
		nn.data = data;
		nn.next = null;
		head = tail = nn;

		size = 1;

	}
	public void reverse() {
	
		for(int i=0;i<size/2;i++) {
			Node temp1=getnodeat(i);
			Node temp2=getnodeat(size-i-1);
			int temp=temp1.data;
			temp1.data=temp2.data;
			temp2.data=temp;
		}
	display();
	}
	
	public void display() {
		Node temp = head;
		for (int i = 0; i < size; i++) {
			System.out.print(temp.data + "-> ");
			temp = temp.next;
		}
		System.out.println();
	}

	public void pointeriterative() {

		Node previous= null;
		Node current=head;
		Node Next = head.next;
		
		while(current!=null) {
			current.next=previous;
			previous=current;
		current=Next;
		if(Next!=null) {
		Next=Next.next;}
			Node temp=head;
			head=tail;
			tail=temp;
		}
		
		
	}
	
	private void displayreverse(Node node) {
		if(node==null) {
			return;
		}
		displayreverse(node.next);
			System.out.print(node.data + "->");
	}
private void pointerreverse(Node node) {
	if(node==tail) {
		return;}
	pointerreverse(node.next);
	node.next.next=node;
	}

	public void  pointerreverse() {
	pointerreverse(head);
	Node temp=head;
	head=tail;
	tail=temp;
	tail.next=null;
	}
	
	private void rdr(Node right, int floor) {
		if(right==null) {
			return;
		}
		
			rdr(right.next, floor+1);
			if(floor>=size/2) {
			int temp=rdrleft.data;
			rdrleft.data=right.data;
			right.data=temp;
		}
			rdrleft=rdrleft.next;
			}
		public void rdr() {
			rdrleft=head;
			Node right=head;
			rdr(right, 0);
		}
		
	private boolean isPallindrome(Node right, int floor) {
		if(right==null) {return true;}
		boolean v=isPallindrome(right.next, floor+1);
	if(floor>=size/2) {
		if(v) {
			if(rdrleft.data==right.data) {
				rdrleft=rdrleft.next;
				return true;
			}
			else return false;
		
		}
		else return v;
		
		
		
	}
		return v;
	}
	public boolean ispal() {
		rdrleft=head;
		boolean val=isPallindrome(head, 0);
		return val;
	}
private void fold(Node right,int floor) {
	if(right==null) {
		
		return;
	}
	fold(right.next, floor+1);

	
	if(floor>size/2) {
		right.next=rdrleft.next;
		rdrleft.next=right;
		rdrleft=right.next;
	}else if(floor==size/2) {
		tail=right;
		right.next=null;
	}
	
}
public void fold() {
	rdrleft=head;
	fold(head,0);
}
private void unfold(Node temp1) {
	if(temp1.next==null) {
		head=tail=temp1;
		return;
	}else if(temp1.next.next==null) {
		head=temp1;
		tail=temp1.next;
		return;
	}
	Node temp2=temp1.next;
	unfold(temp1.next.next);
	//Node temp2=temp1.next;
	temp1.next=head;
	head=temp1;
	tail.next=temp2;
	tail=temp2;
	tail.next=null;

}
public void unfold() {
	rdrleft=head;
	unfold(head);
}


	public static void main(String[] args) {
		reversedataiterative ll = new reversedataiterative();
		ll.addlast(10);
		ll.addlast(70);
		ll.addlast(20);
		ll.addlast(60);
		
		ll.addlast(30);
		ll.addlast(50);
		ll.addlast(40);
		//ll.addlast(60);
		//ll.display();
		//ll.reverse();
		//ll.pointeriterative();
		//ll.displayreverse(ll.head);
		//ll.pointerreverse();
		//ll.display();
		//ll.rdr();
		//ll.display();
		//System.out.println(ll.ispal());
		//ll.fold();
		//ll.addlast(10);
		ll.display();
		//ll.addlast(70);
		ll.unfold();
		ll.display();
	}

}
