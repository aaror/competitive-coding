package linkedlistmar16;


public class LinkedList {
	private class Node {
		public int data;
		public Node next;
	}

	private Node head;
	private Node tail;
	private int size;

	// public LinkedList() {
	// head = tail = null;
	// size = 0;
	// }
	private Node getnodeat(int idx) {
		Node temp = head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}
		return temp;

	}

	public void display() {
		Node temp = head;
		for (int i = 0; i < size; i++) {
			System.out.print(temp.data + "-> ");
			temp = temp.next;
		}
		System.out.println();
	}

	private void handleaddwheresizeis0(int data) {
		Node nn = new Node();
		nn.data = data;
		nn.next = null;
		head = tail = nn;

		size = 1;

	}

	public void addlast(int data) {
		if (size == 0)
			handleaddwheresizeis0(data);
		// return;
		else {
			Node nn = new Node();
			nn.data = data;
			nn.next = null;
			tail.next = nn;
			tail = nn;
			size++;
		}
	}

	public int getsize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getfirst() {
		if (size == 0) {
			return -1;
		} else {
			return head.data;
		}
	}

	public int getlast() {
		if (size == 0) {
			return -1;
		} else {
			return tail.data;
		}
	}

	public int getaddat(int idx) {
		if (idx < 0 || idx >= size) {
			return -1;
		} else {
			Node temp = getnodeat(idx);
			return temp.data;
		}
	}

	public void addfirst(int data) {
		if (size == 0)
			handleaddwheresizeis0(data);
		// return;
		else {
			Node nn = new Node();
			nn.data = data;
			// nn.next = null;
			nn.next = head;
			head = nn;
			size++;
		}
	}

	public void addat(int data, int idx) {
		if (idx < 0 || idx >= size) {
			return;
		} else if (idx == 0) {
			addfirst(data);
		} else if (idx == size) {
			addlast(data);
		} else {
			Node nm1 = getnodeat(idx - 1);
			Node np1 = nm1.next;
			Node n = new Node();
			n.data = data;
			n.next = np1;
			nm1.next = n;
			size++;
		}
	}

	private int handlewhensizeis1() {

		int temp = head.data;
		head = tail = null;

		size = 0;

		return temp;
	}

	public int removefirst() {

		if (size == 0) {
			return -1;
		}
		if (size == 1) {

			return handlewhensizeis1();
		} else {
			Node oh = head;

			head = head.next;

			size--;
			int temp = oh.data;
			return temp;
		}
	}

	public int removelast() {
		if (size == 1) {
			return handlewhensizeis1();
		} else {
			Node s1 = getnodeat(size - 2);
			s1.next = null;
			int temp = tail.data;
			tail = s1;
			size--;
			return temp;

		}

	}

	public int removeat(int idx) {
		if (idx < 0 || idx >= size) {
			return -1;
		} else {
			if (idx == 0) {
				return removefirst();

			} else if (idx == size - 1) {
				return removelast();
			} else {
				Node nm1 = getnodeat(idx - 1);
				Node n = nm1.next;
				Node np1 = n.next;
				int temp = n.data;
				size--;
				nm1.next = np1;
				return temp;
			}

		}
	}

	// private void clear(LinkedList L) {
	// L.head=null;
	// L.tail=null;
	// L.size=0;
	// }
	public void removedup() {

		LinkedList n1 = new LinkedList();
		while (this.size >= 0) {
			int temp = this.removefirst();
			if (n1.size == 0 || n1.tail.data != temp) {
				n1.addlast(temp);

			}
		}
		this.head = n1.head;
		this.tail = n1.tail;
		this.size = n1.size;
		// clear(n1);
	}

	public void oddeven() {
		LinkedList even = new LinkedList();
		LinkedList odd = new LinkedList();
		while (this.size > 0) {
			int temp = this.removefirst();
			if (temp % 2 == 0) {
				even.addlast(temp);
			} else {
				odd.addlast(temp);
			}
		}
		if (odd.size == 0) {
			odd.tail.next = even.head;

			this.head = even.head;
			this.tail = even.tail;
			this.size = odd.size + even.size;
		} else if (even.size == 0) {
			odd.tail.next = even.head;

			this.head = even.head;
			this.tail = even.tail;
			this.size = odd.size + even.size;
		}
		odd.tail.next = even.head;
		this.head = odd.head;
		this.tail = even.tail;
		this.size = odd.size + even.size;
	}

	public void Kthreverse(int k) {
		// LinkedList temp=new LinkedList();
		// LinkedList acc= new LinkedList();
		LinkedList acc = null;
		while (this.size != 0) {
			LinkedList temp = new LinkedList();
			for (int i = 0; i < k && this.size > 0; i++) {
				temp.addfirst(this.removefirst());
			}

			if (acc == null) {
				acc = temp;
			} else {
				acc.tail.next = temp.head;
				acc.tail = temp.tail;
				acc.size = temp.size + acc.size;
			}

		}

		this.head = acc.head;
		this.tail = acc.tail;
		this.size = acc.size;
		tail.next = null;
	}

	private Node midNode(Node sp, Node fp) {
		Node slow = sp;
		Node fast = sp;
		while (fast != fp && fast.next != fp) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public int midelement() {
		Node mid = midNode(this.head, this.tail);
		return mid.data;

	}

	public int kthfromlast(int k) {
		Node slow = head;
		Node fast = head;
		for (int i = 0; i < k; i++) {
			fast = fast.next;
		}
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow.data;
	}

	public static LinkedList mergetwosortedlist(LinkedList l1, LinkedList l2) {
		LinkedList res = new LinkedList();
		Node temp1 = l1.head;
		Node temp2 = l2.head;
		while (temp1 != null && temp2 != null) {
			if (temp1.data < temp2.data) {
				res.addlast(temp1.data);
				temp1 = temp1.next;
			} else {
				res.addlast(temp2.data);
				temp2 = temp2.next;
			}
		}
		while (temp1 != null) {
			res.addlast(temp1.data);
			temp1 = temp1.next;
		}
		while (temp2 != null) {
			res.addlast(temp2.data);
			temp2 = temp2.next;
		}
		return res;
	}

private LinkedList mergesort(Node cp ,Node sp) {
	if(cp == sp)
	{
		LinkedList base = new LinkedList();
		base.addfirst(cp.data);
		return base;
	}
 Node mid= midNode(cp, sp);
 LinkedList left=mergesort(cp,mid);
 LinkedList right = mergesort(mid.next, sp);
	LinkedList res=new LinkedList();
			res = mergetwosortedlist(left, right);
	return res;
	
}

	public LinkedList mergesort() {
		LinkedList res = mergesort(this.head, this.tail);
		return res;
	}

	private int  add(Node n1, Node n2, int size1,int size2,LinkedList l1) {
		int carry=0;
		l1=null;
		Node cur=new Node();
		if(n1==null) {
		 Node res=n2;
		 return 0;
		}
		if(n2==null) {
			Node res= n1;
			return 0;
		}
		
		if(size1==size2) {
			int sum=n1.data+n2.data+carry;
			carry=sum/10;
			 sum=sum%10;}
		else {
			if(size1<size2) {
		Node temp=n1;
		n1=n2;
		n2=temp;}
			int diff=Math .abs(size1-size2);
			Node temp=n1;
			while(diff-->=0) {
				cur=temp;
				temp=temp.next;
			}[]
//	if(size1==size2) {
//	sum=sum+n1.data+n2.data+carry;
//	l1.addlast(sum);
//	if(sum>=10) {
//		sum=sum+carry+n2.next.data+n1.next.data;
//		l1.addlast(sum);
//	}
//	size1--;
//	size2--;
//	}
//	
		 }
		 
	}
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		LinkedList ll1 = new LinkedList();

		ll.addlast(30);
		ll.addlast(20);
		ll.addlast(10);
		ll.addlast(50);
		//// System.out.println(ll.getsize());
		//// System.out.println(ll.getfirst());
		//// System.out.println(ll.getlast());
		//
		// System.out.println(ll.getaddat(2));
		// ll.addat(5,1);
		// ll.display();
		// ll.removeat(2);
		// ll.display();
		// ll.removefirst();
		// ll.display();
		// ll.addfirst(40);
		// ll.display();
		ll.addlast(40);
		// ll1.addlast(5);
		// ll1.addlast(7);
		// ll.display();
		// ll1.addlast(21);
		// ll1.addlast(23);
		// ll.display();
		// ll.addat(100, 3);
		// ll1.addlast(25);
		// ll.addlast(6);
		// ll.addlast(7);
		// ll.addlast(7);
		// ll.addlast(30);
		// ll.addlast(30);
		ll.display();
		// ll1.display();
		// ll.removedup();
		// ll.oddeven();
		// ll.Kthreverse(3);

		// System.out.println(ll.midelement());
		// ll.display();

		// System.out.println(ll.kthfromlast(3));
		// ll.display();
		// LinkedList l3= new LinkedList();
		// l3=mergetwosortedlist(ll, ll1);
		// l3.display();
		ll.mergesort().display();
		ll.display();
	}

}
