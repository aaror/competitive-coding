import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class GenericTree {

private static  class Node{
	int data;
	ArrayList<Node> children=new ArrayList<>();


public Node(int data) {
	this.data = data;
	children = new ArrayList<Node>();
}
}
public static Node construct(ArrayList<Integer> data_list) {
	Node root = null;
	ArrayList<Node> nList = new ArrayList<Node>();

	for (Integer data : data_list) {
		Node nn = new Node(data);

		if (data == -1) {
			nList.remove(nList.size() - 1);
		} else {
			if (nList.size() == 0) {
				root = nn;
			} else {
				Node lastn = nList.get(nList.size() - 1);
				lastn.children.add(nn);
			}

			nList.add(nn);
		}
	}

	return root;
}
public static void display(Node node) {
	if (node == null) {
		return;
	} else {
		String output = node.data + "=>";

		for (Node child : node.children) {
			output += child.data + ",";
		}
		output += ".";
		System.out.println(output);
		for (Node child : node.children) {
			display(child);
		}
	}

}
public static int size(Node root) {
	int rv = 1;

	for (Node child : root.children) {
		rv += size(child);
	}
	return rv;
}
public static boolean find(Node node, int data) {
	if (node.data == data) {
		return true;
	}
	boolean rv = false;
	for (Node child : node.children) {
		rv |= find(child, data);
	}

	return rv;

}
public static int max(Node node) {
	int rv = node.data;

	for (Node child : node.children) {
		rv = Math.max(rv, max(child));
	}

	return rv;
}
public static int depth(Node node) {

	int rv = 0;

	for (Node child : node.children) {
		rv = Math.max(rv, depth(child));
	}

	return rv + 1;

}
public static ArrayList<Integer> node2rootPath(Node root, int dtf) {
	if (root.data == dtf) {
		ArrayList<Integer> rv = new ArrayList<Integer>();
		rv.add(dtf);
		return rv;
	}

	ArrayList<Integer> rv = new ArrayList<Integer>();
	for (Node child : root.children) {
		rv = node2rootPath(child, dtf);
		if (rv.size() > 0) {
			rv.add(root.data);
			return rv;
		}
	}
	return rv;

}
//	lca
public static int leastCommonAncestor(Node root, int n1, int n2) {
	ArrayList<Integer> n1List = node2rootPath(root, n1);
	ArrayList<Integer> n2List = node2rootPath(root, n2);

	int ancestor = -1;
	while (n1List.size() > 0 && n2List.size() > 0) {
		if (n1List.get(n1List.size() - 1) != n2List.get(n2List.size() - 1)) {
			break;
		}
		ancestor = n1List.remove(n1List.size() - 1);
		n2List.remove(n2List.size() - 1);

	}

	return ancestor;
}

//distance
public static int FindDistance(Node root, int n1, int n2) {
	ArrayList<Integer> n1List = node2rootPath(root, n1);
	ArrayList<Integer> n2List = node2rootPath(root, n2);
	int i, j, count = 0;

	for (i = n1List.size() - 1, j = n2List.size() - 1; i >= 0 && j >= 0; --i, --j) {
		if (n1List.get(i) != n2List.get(j)) {
			break;
		}
		++count;

	}

	return n1List.size() + n2List.size() - (2 * count) + 1;
}

public static distance_helper distance(Node root, int n1, int n2) {
	distance_helper mp = new distance_helper();
	if (root.data == n1) {
		mp.node1_dist = 0;
	}

	if (root.data == n2) {
		mp.node2_dist = 0;
	}

	for (Node child : root.children) {
		distance_helper rv = distance(child, n1, n2);
		if (rv.node1_to_node2 < mp.node1_to_node2) {
			return rv;
		}
		mp.node1_dist = Math.min(mp.node1_dist, rv.node1_dist + 1);
		mp.node2_dist = Math.min(mp.node2_dist, rv.node2_dist + 1);
		mp.node1_to_node2 = Math.min(mp.node1_dist + mp.node2_dist + 1, rv.node1_to_node2);

	}

	return mp;

}

public static class distance_helper {
	int node1_dist, node2_dist, node1_to_node2;

	public distance_helper() {
		node1_dist = 100000;
		node2_dist = 100000;
		node1_to_node2 = 100000;
	}
}

public static void mirror(Node root) {
	Collections.reverse(root.children);

	for (Node child : root.children) {
		mirror(child);
	}
}

public static void remove_leave(Node node) {
//
//	for (Node child : node.children) {
//		if (child.children != null && child.children.size() == 0) {
//			node.children.remove(child);
//		}
//	}
//	for (Node child : node.children) {
//		remove_leave(child);
//	}
	for (int i = node.children.size() - 1; i >= 0; i--) {
		Node child = node.children.get(i);
		if (child.children.size() == 0) {
			node.children.remove(i);
		} else {
			remove_leave(child);
		}
	}

}

public static void linearise(Node node) {
	for (int i = node.children.size() - 1; i >= 1; --i) {
		Node n = node.children.remove(i);
		node.children.get(i - 1).children.add(n);
	}
	
	for (Node child : node.children) {
		linearise(child);
	}

	
}
public static Node linearise2(Node root) {
	if(root.children.size()==0) {
		return root;
	}
	Node ol=root.children.get(root.children.size()-1);
	Node olkitail=linearise2(ol);
	while(root.children.size()!=1) {
		Node sl=root.children.get(root.children.size()-2);
		Node slkitail=linearise2(sl);
		Node last =root.children.remove(root.children.size()-1);
		slkitail.children.add(last);
	}
	return olkitail;
	
}
public static boolean areSimilar(Node root1, Node root2) {
	if(root1.children.size()==root2.children.size()) {
		for(int i=0;i<root1.children.size();i++) {
			boolean r=areSimilar(root1.children.get(i), root2.children.get(i));
			if(r==false) {
				return false ;
			}
		
		}
	}else {
		return false;
	}
		return true;
}
public static boolean isMirrorStructure(Node root1,Node root2) {
	if(root1.children.size()!=root2.children.size()) {
		return false;
	}
	if(root1.children.size()==root2.children.size()) {
	for(int i=0;i<root1.children.size();i++) {
		boolean r=isMirrorStructure(root1.children.get(i), root2.children.get(root2.children.size()-i-1));
		if(r==false) {
			return false;
		}
	}
	}
	return true;
}
public static boolean isSyemmtric(Node root) {
	for(int i=0;i<root.children.size()/2;i++) {
		Node left =root.children.get(i);
		Node right=root.children.get(root.children.size()-i-1);
		if(left.children.size()!=right.children.size()) {
			return false;
		} 
		boolean r=isSyemmtric(root.children.get(i));
		if(r==false) {
			return false;
		}
	}
	return true;
}


	
private static class heapmover{
	int sz;
	int ht;
	int mn;
	int mx;
	int c;
	int f;
	int p;
	int s;
	int status=0;
	int curr=0;
	int pre=0;
	int r=Integer.MIN_VALUE;
	int k = Integer.MAX_VALUE;
	public heapmover() {
		
	}
	public heapmover(int size,int height,int min,int max,int ceil,int floor) {
		this.sz=size;
		this.ht=height;
		this.mn=min;
		this.mx=max;
		this.c=ceil;
		this.f=floor;
		
		
//	this.p=pre;
//	this.s=suc;
	}
}
public static void multisolver(Node root,int depth,heapmover m,int data) {
	
 m.sz++;
 m.ht=depth>m.ht? depth:m.ht;
 m.mn=root.data<m.mn?root.data:m.mn;
 m.mx=root.data>m.mx?root.data:m.mx;
 
 if(root.data>data&&root.data<m.c) {
	 m.c=root.data;
	 
 }else  if(root.data<data&&root.data>m.f) {
	 m.f=root.data;
	 
	 
 }
  for(int i=0;i<root.children.size();i++) {
		 multisolver(root.children.get(i), depth+1, m,data);
	 
 }
	
}

public static int secondlargest(Node root,heapmover m) {
	if(root==null) {
		return 0;
		
	}
	for(int i=0;i<=2;i++) {
		floor(root,m.k,m);
		m.k=m.r;
		m.r=Integer.MIN_VALUE;
	}
	return m.k;
	
}
public static void predsucc(Node root,int data,heapmover m) {
//	if(m.status==0) {
//		if(root.data!=data) {
//			m.p=root.data;
//		}else {
//			m.status=1;
//		}
//	}else if(m.status==1) {
//		m.s=root.data;
//		m.status++;
//	}
	m.pre=m.curr;
	m.curr=root.data;
	if(m.curr==data) {
		m.p=m.pre;
	}else if(m.pre==data) {
		
		m.s=m.curr;
	}
	for(int i=0;i<root.children.size();i++) {
		predsucc(root.children.get(i), data, m);
	}
}
public static void  floor(Node root,int data, heapmover m) {
	m.r=root.data<data&&root.data>m.r? root.data:m.r;
	
	for(int i=0;i<root.children.size();i++) {
		floor(root.children.get(i),data,m);
	}
}
public static int kthlargest(Node root,int k,heapmover m) {
	
	
	for(int i=0;i<=k;i++) {
		floor(root, m.k, m);
		m.k=m.r;
		m.r=Integer.MIN_VALUE;
	}
	return m.k;
	
	
}
public static void levelo(Node root) {
	LinkedList<Node> queue=new LinkedList<Node>();
	queue.add(root);
	
	while(queue.size()>0) {
		Node f=queue.getFirst();
		queue.removeFirst();
		System.out.print(f.data+" ");
	
	for(int i=0;i<f.children.size();i++) {
		queue.addLast(f.children.get(i));
	}
	}
}
public static void levelowise(Node root) {
	LinkedList<Node>queue= new LinkedList<Node>();
	LinkedList<Node>next=new LinkedList<Node>();
//	if(root==null) {
//		return;
//	}
	queue.add(root);

		 {
		while(queue.size()>0) {
				Node f=queue.getFirst();
				queue.removeFirst();
				System.out.print(f.data+" ");
			
		
	
		
		
		for(int i=0;i<f.children.size();i++) {
			next.add(f.children.get(i));
		
		}
			
		if(queue.size()==0){
			queue=next;
			next=new LinkedList<Node>();
		System.out.println();
	}
		}
}}
	public static void zigzaglevelorder(Node root) {
		LinkedList<Node> queue=new LinkedList<Node>();
		LinkedList<Node> stack=new LinkedList<Node>();
		queue.add(root);
		boolean lefttoright=true;
		while(queue.size()>0) {
			Node f=queue.getFirst();
			queue.removeFirst();
			System.out.print(f.data+" ");
			
			if(lefttoright) {
			for(int i=0;i<f.children.size();i++) {
			stack.addFirst(f.children.get(i));
		}
			}
		else {
			for(int i=f.children.size()-1;i>=0;i--) {
				stack.addFirst(f.children.get(i));
		}
		}
		if(queue.size()==0){
			queue=stack;
			stack=new LinkedList<Node>();
		System.out.println();
		lefttoright=!lefttoright;
	}
		
	
		}
	}
	public static  class ehelper{
		Node root;
		int state;
		public ehelper(Node root, int state ) {
			this.root=root;
			this.state=state;
		}
	}
	public static void euler(Node root,ehelper e) {
	LinkedList<ehelper> stack=new LinkedList<ehelper>();
	ehelper rh=new ehelper(root, 0);
	stack.addFirst(rh);
	while(stack.size()>0) {
		ehelper th=stack.getFirst();
		if(th.state==0) {
			//pre
			System.out.println(th.root.data+"pre");
			th.state++;
		}
		else if(th.state>=1 && th.state<=th.root.children.size()) {
			ehelper ch= new ehelper(th.root.children.get(th.state-1), 0);
			stack.addFirst(ch);
			th.state++;
			if(th.state>=2) {
				System.out.println(th.root.data+"In");
				
			}
		}
			else if(th.state==th.root.children.size()+1) {
				System.out.println(th.root.data+"post");
				th.state++;
			}
			else if(th.state==th.root.children.size()+2) {
				stack.removeFirst();
		}
	}
	
	
		
		
		
	
		}
	
	
	

public static void main(String[] args) {

	ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
			-1, 120, -1, -1, 90, -1, -1, 40, 100, -1, 150, -1,-1));

	Node root = construct(list);
//	ArrayList<Integer> list1=new ArrayList<Integer>(Arrays.asList(10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
//			-1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1)) ;
//	Node root1=construct(list);
//	display(root);
//	System.out.println(size(root));
//	System.out.println(find(root, 90));
//	System.out.println(find(root, 5));
//	System.out.println(max(root));
//	System.out.println(depth(root));

//	ArrayList<Integer> rv = node2rootPath(root, 90);
//	for (int x : rv) {
//		System.out.print(x + "->");
//	}

//	System.out.println();

//	System.out.println(distance(root, 70, 120).node1_to_node2);

//	System.out.println(FindDistance(root, 50, 120));

//	System.out.println(leastCommonAncestor(root, 50, 20));

//	display(root);
//	remove_leave(root);
//	System.out.println("=>>>>>>>>>>>>>>>>>>>>>>...................");
//	display(root);
	
	//display(root);
	//linearise(root);
	//linearise2(root);
 //System.out.println(areSimilar(root, root1));
	//mirror(root);
	//System.out.println(isMirrorStructure(root, root1));
	//System.out.println(isSyemmtric(root));
heapmover m=new heapmover();
//multisolver(root, 0, m, 68);
//System.out.println(m.sz+" "+m.ht+" "+m.mn+" "+m.mx+" "+m.c+" "+ m.f);

//predsucc(root, 70,m);
//System.out.println(m.p+" "+m.s+" ");
//System.out.println(kthlargest(root, 4,m));
//System.out.println(secondlargest(root, m));
//levelo(root);
//levelowise(root);
//zigzaglevelorder(root);
ehelper e= new ehelper(root, 0);
euler(root, e);


System.out.println("=>>>>>>>>>>>>>>>>>>>>>>...................");

	//display(root);

}

}








