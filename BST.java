import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BST {
	private static class Node {
		int data;
		Node left;
		Node right;

		public Node(int d) {
			this.data = d;
		}
	}
	public static void display(Node root) {
		if (root.left != null)
			System.out.print(root.left.data);
		else
			System.out.print(".");

		System.out.print("->" + root.data + "<-");
		// System.out.print(root.data);
		// System.out.print("<-");

		if (root.right != null)
			System.out.print(root.right.data);
		else
			System.out.print(".");

		System.out.println();
		if (root.left != null)
			display(root.left);
		if (root.right != null)
			display(root.right);

	}
public static int min(Node root) {
	if(root==null) {
		return 0;
	}
	if(root.left!=null) {
		return min(root.left);
	}else {
		return root.data;
	}
}
public static int max(Node root) {
	if(root==null) {
		return 0;
	}
	if(root.right!=null) {
		return max(root.right);
	}else {
		return root.data;
	}
}
public static boolean find(Node root,int data) {
	if(root==null) {
		return false;
	}
	
	if(data>root.data) {
		return find(root.right, data);
	}else if(data<root.data) {
		return find(root.left, data);
	}else {
		return true;
	}
	
	
}
public static Node construct(ArrayList<Integer> data,int lo,int hi) {
	if(lo>hi) {
		return null;
	}
	int mid=(lo+hi)/2;
	Node root=new Node(data.get(mid));
	root.left=construct(data, lo, mid-1);
	root.right=construct(data, mid+1, hi);
	return root;
}
public static class sum{
	int sum=0;
}
public static void replacenodewithsum(Node root,sum s) {
	if(root==null) {
		return ;
		
	}

	replacenodewithsum(root.right,s);
//int ord=root.data;
//root.data=s.sum;
//s.sum=root.data;
//s.sum+=ord;
	s.sum+=root.data;
	root.data=s.sum-root.data;
replacenodewithsum(root.left,s);

	
}
public static void pir(Node root, int lo,int hi) {
	if(root==null) {
		return ;
		
	}
	if(root.data<lo) {
		pir(root.right, lo, hi);
	}else if(root.data>hi) {
		pir(root.left, lo, hi);
	}
	else {
		pir(root.left,lo,hi);
		System.out.print(root.data);
		pir(root.right, lo, hi);
	}
}
//approach 1 for n in bst,nlogn for bt
public static void pairsumtarget(Node or,Node root,int tar) {
	if(root==null) {
		return;
	}
	int comp=tar-root.data;
	if(root.data<comp) {
		if(find(or, comp)==true) {
			{
				System.out.println(root.data+" "+comp);
			}
		}
	}
	pairsumtarget(or, root.left, tar);
	pairsumtarget(or, root.right, tar);
}
// approach 2 for bst in o(n)
public static void pairsumtarget1(Node root,int tar) {
	ArrayList<Integer> list = new ArrayList<>();
	filler(root, list);
	int li=0,ri=list.size()-1;
	while(li!=ri) {
		if(list.get(li)+list.get(ri)<tar) {
			li++;
		}else if(list.get(li)+list.get(ri)>tar) {
			ri--;
		}else {
			System.out.println(list.get(li)+" "+ list.get(ri));
			li++;
			ri--;
		}
	}
}
public static void filler(Node root,ArrayList<Integer> list) {
	if(root==null) {
		return;
	}
	filler(root.left, list);
	list.add(root.data);
	filler(root.right, list);
}
// aproach 2 in bt for nlogn
public static void pairsumtarget2(Node root,int tar) {
	ArrayList<Integer> list = new ArrayList<>();
	filler(root, list);
	Collections.sort(list);
	int li=0,ri=list.size()-1;
	while(li!=ri) {
		if(list.get(li)+list.get(ri)<tar) {
			li++;
		}else if(list.get(li)+list.get(ri)>tar) {
			ri--;
		}else {
			System.out.println(list.get(li)+" "+ list.get(ri));
			li++;
			ri--;
		}
	}
}

public static void main(String[] args) {
//ArrayList<Integer> list= new ArrayList<Integer>(Arrays.asList(12,25,37,50,62,75,87));
	ArrayList<Integer> list= new ArrayList<Integer>(Arrays.asList(80,70,60,50,30,20,10));
	
	Node root=construct(list, 0,list.size()-1);
//display(root);
//System.out.println(find(root, 40));
//display(root);
//	sum s=new sum();
//replacenodewithsum(root,s);
//display(root);
	//pir(root, 10, 60);
//pairsumtarget(root, root, 100);

	//pairsumtarget1(root, 100);
	pairsumtarget1(root, 100);
	//pairsumtarget2(root, 100);
	
}

}
