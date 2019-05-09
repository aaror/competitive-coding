
import java.util.*;

public class BinaryTree {
	private static class Node {
		int data;
		Node left;
		Node right;

		public Node(int d) {
			this.data = d;
		}
	}

	public static Node construct(int[] data) {
		Node root = new Node(data[0]);
		ArrayList<Node> stack = new ArrayList<Node>();

		for (int i = 0; i < data.length; i++) {
			if (data[i] == -1) {
				stack.remove(stack.size() - 1);
			} else {
				Node node = new Node(data[i]);
				if (stack.size() == 0) {
					root = node;

				} else {
					Node top = stack.get(stack.size() - 1);

					if (top.left == null) {
						top.left = node;
					} else {
						top.right = node;
					}
					// stack.add(node);
				}
				stack.add(node);

			}
		}
		return root;

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

	public static int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		int ls = max(node.left);
		int rs = max(node.right);
		return Math.max(node.data, Math.max(ls, rs));
	}

	public static int sum(Node node) {
		if (node == null) {
			return 0;
		}
		int ls = sum(node.left);
		int rs = sum(node.right);
		return ls + rs + node.data;
	}

	public static int size(Node node) {
		int ls = 0, rs = 0;
		if (node.left != null) {
			ls = size(node.left);
		}
		if (node.right != null) {
			rs = size(node.right);
		}
		return ls + rs + 1;
	}

	public static int height(Node root) {
		if (root == null) {
			return -1;
		}
		int ls = height(root.left);
		int rs = height(root.right);
		return Math.max(ls, rs) + 1;
	}

	// public static Node find(Node node,int data) {
	// if(node==null) {
	// return null;
	// }
	// if(node.data==data) {
	// return node;
	// }
	// Node lv=find(node.left, data);
	// if(lv!=null) {
	// return lv;
	// }
	// return find(node.right, data);
	// }
	public static boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}

		if (node.data == data) {
			return true;
		}
		boolean fleft = find(node.left, data);
		if (fleft) {
			return true;
		}
		boolean fright = find(node.right, data);
		if (fright) {
			return true;

		}
		return false;
	}

	public static ArrayList<Integer> root2node(Node node, int data) {
		if (node == null) {
			return new ArrayList<>();
		}
		if (node.data == data) {
			ArrayList<Integer> path = new ArrayList<>();
			path.add(node.data);
			return path;
		}
		ArrayList<Integer> pathleft = root2node(node.left, data);
		if (pathleft.size() > 0) {
			pathleft.add(node.data);
			return pathleft;
		}
		ArrayList<Integer> pathright = root2node(node.right, data);
		if (pathright.size() > 0) {
			pathright.add(node.data);
			return pathright;
		}
		return new ArrayList<>();
	}

	public static ArrayList<Node> node2root(Node root, int data) {
		if (root == null) {
			return null;
		}
		if (root.data == data) {
			ArrayList<Node> lst = new ArrayList<>();
			lst.add(root);
			return lst;
		}

		ArrayList<Node> pathL = node2root(root.left, data);
		if (pathL != null) {
			pathL.add(root);
			return pathL;
		}

		ArrayList<Node> pathR = node2root(root.right, data);
		if (pathR != null) {
			pathR.add(root);
			return pathR;
		}

		return null;
	}

	public static void PrintSingleChild(Node root, Node parent) {
		if (root == null) {
			return;
		}
		if (parent == null) {
			return;
		}
		if ((parent.left == root && parent.right == null) || (parent.right == root && parent.left == null)) {
			System.out.print(root.data + " ");

		}
		PrintSingleChild(root.left, root);
		PrintSingleChild(root.right, root);

	}

	public static Node remove_leave(Node root) {

		if (root == null) {
			return null;
		}

		if (root.left == null && root.right == null) {
			return null;
		}

		root.left = remove_leave(root.left);
		root.right = remove_leave(root.right);

		return root;

	}

	public static void toDuplicate(Node root) {
		if (root == null)
			return;

		Node n = new Node(root.data);

		n.left = root.left;
		root.left = n;

		toDuplicate(root.left.left);
		toDuplicate(root.right);

	}

	public static void display1(Node root) {
		if (root == null) {
			return;
		}
		display1(root.left);
		System.out.print(root.data + " ");
		display1(root.right);
	}

	public static void fromDuplicate(Node root) {
		if (root == null)
			return;

		root.left = root.left.left;

		fromDuplicate(root.left);
		fromDuplicate(root.right);
	}

	public static void printKfar(Node root, int data, int k) {
		ArrayList<Node> lst = node2root(root, data);
		// node myNode=find(root, data);
		printKDown(lst.get(0), k);

		for (int i = 1; i <= k && i < lst.size(); ++i) {
			Node prev = lst.get(i - 1);
			Node curr = lst.get(i);
			if (curr.left == prev)
				printKDown(curr.right, k - i - 1);
			else
				printKDown(curr.left, k - i - 1);
		}

	}

	public static void printKDown(Node root, int k) {
		if (root == null) {
			return;
		}
		if (k == 0) {
			System.out.print(root.data + " ");
		}

		printKDown(root.left, k - 1);
		printKDown(root.right, k - 1);
	}
	// private static void printKaway(node root, int data, int k) {
	// ArrayList<Integer> n2d = n2r(root, data);

	// for (int i = 0; i < n2d.size(); ++i) {
	// printKdown(root, data, k - i, false);
	// }
	// }
	public static Node construct1(int[] pre, int[] in, int prestart, int preend, int instart, int inend) {
		if (prestart > preend || instart > inend) {
			return null;
		}
		Node root = new Node(pre[prestart]);

		// int k=-1;
		// for(int i=instart;i<inend;i++) {
		// if(pre[prestart]==in[i]) {
		// k=1;
		// break;
		// }
		// }
		int lhs = 0;
		while (in[instart + lhs] != pre[prestart]) {
			lhs++;
		}
		root.left = construct1(pre, in, prestart + 1, prestart + lhs, instart, instart + lhs - 1);
		root.right = construct1(pre, in, prestart + lhs + 1, preend, instart + lhs + 1, inend);

		return root;
	}

	public static Node construct2(int[] post, int[] in, int poststart, int postend, int instart, int inend) {
		if (poststart > postend || instart > inend) {
			return null;
		}
		Node root = new Node(post[postend]);

		// int k=-1;
		// for(int i=instart;i<inend;i++) {
		// if(pre[prestart]==in[i]) {
		// k=1;
		// break;
		// }
		// }
		int lhs = 0;
		while (in[instart + lhs] != post[postend]) {
			lhs++;
		}
		root.left = construct2(post, in, poststart, poststart + lhs - 1, instart, instart + lhs - 1);
		root.right = construct2(post, in, poststart + lhs + 1, postend - 1, instart + lhs + 1, inend);

		return root;
	}

	public static Node construct3(int[] da, int[] pr) {

		Node root = null;
		// ArrayList<Node> narr= new ArrayList<Node>(101);
		Node[] narr = new Node[101];

		for (int i = 0; i < da.length; i++) {
			narr[da[i]] = new Node(da[i]);
		}
		for (int i = 0; i < da.length; i++) {
			int data = da[i];
			int pdata = pr[i];
			if (pdata == -1) {
				Node node = narr[data];
				root = node;
			} else {
				Node node = narr[data];
				Node parent = narr[pdata];
				if (parent.left == null) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}
		return root;
	}

	// public static Node construct4(int[] pre, int[] post, int prestart,int
	// preend,int poststart ,int postend) {
	//
	// Node root=new Node(pre[prestart]);
	// prestart++;
	//
	// }

	private static class height {
		int h = 0;
		int dia = 0;

	}

	public static int diameter(Node root) {
		if (root == null) {

			return 0;
		}

		int lheight = height(root.left);
		int rheight = height(root.right);
		int ldiameter = diameter(root.left);
		int rdiameter = diameter(root.right);
		return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));

	}

	public static height diameter2(Node root) {

		if (root == null) {
			height ht = new height();
			ht.h = 0;
			ht.dia = 0;
			return ht;
		}

		height lheight = diameter2(root.left);
		height rheight = diameter2(root.right);
		height mp = new height();

		mp.h = Math.max(lheight.h, rheight.h) + 1;
		mp.dia = Math.max(lheight.h + rheight.h + 1, Math.max(lheight.dia, rheight.dia));
		return mp;

	}

	public static void rootToLEafPathInRange(Node root, int lo, int hi, int sum, String parh) {
		if (root == null) {
			return;
		}

		if (sum > hi) {
			return;
		}

		if (root.left == null && root.right == null && sum + root.data <= hi && sum + root.data >= lo) {
			System.out.println(parh + " " + root.data);
			return;
		}

		rootToLEafPathInRange(root.left, lo, hi, sum + root.data, parh + " " + root.data);
		rootToLEafPathInRange(root.right, lo, hi, sum + root.data, parh + " " + root.data);
	}

	private static class height1 {
		int h = 0;
	}

	public static boolean isbalanced(Node node, height1 ht) {
		if (node == null) {
			ht.h = 0;
			return true;
		}
		height1 lh = new height1();
		height1 rh = new height1();
		boolean l = isbalanced(node.left, lh);
		boolean r = isbalanced(node.right, rh);
		int lh1 = lh.h;
		int rh1 = rh.h;
		ht.h = (lh1 > rh1 ? lh1 : rh1) + 1;
		if ((lh1 - rh1 >= 2) || (rh1 - lh1 >= 2)) {
			return false;
		} else
			return l && r;

	}

	private static class BSTp {
		int min;
		int max;
		boolean isBST;
		int lbstroot;
		int lbstsize;
	}

	public static BSTp isBst(Node node) {
		if (node == null) {
			BSTp hp = new BSTp();
			hp.isBST = true;
			hp.min = Integer.MAX_VALUE;
			hp.max = Integer.MIN_VALUE;
			hp.lbstroot = -1;
			hp.lbstsize = 0;
			return hp;
		}
		BSTp lp = isBst(node.left);
		BSTp rp = isBst(node.right);
		BSTp mp = new BSTp();
		mp.min = Math.min(node.data, Math.max(lp.min, rp.min));
		mp.max = Math.max(node.data, Math.max(lp.max, rp.max));
		mp.isBST = lp.isBST && rp.isBST && node.data > lp.max && node.data < rp.min;
		if (mp.isBST !=false) {
			mp.lbstroot = node.data;
			mp.lbstsize = lp.lbstsize + rp.lbstsize + 1;

		} else {
			if (lp.lbstsize > rp.lbstsize) {
				mp.lbstroot = lp.lbstroot;
				mp.lbstsize = lp.lbstsize;
			} else {
				mp.lbstroot = rp.lbstroot;
				mp.lbstsize = rp.lbstsize;
			}
		}
		return mp;
	}

	public static void main(String[] args) {
		// int[] data = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1,
		// 70, -1, -1, 87, -1, -1, -1 };
		// int[] data1 = { 50, 25, 12,20, -1,-1, 37, 30, -1, -1, -1, 75, 62, 60, -1, 70,
		// -1, -1, 87, -1, -1, -1 };
		// int[] data= {50,25,12,-1,30,37,-1,-1,-1,75,62,-1,87,-1,-1,-1};
		// int[] data= {12,25,50,75,37,87,40,80};
		// int[] parent= {25,50,-1,50,25,75,37,87};
		// int[]data=
		// {50,25,12,-1,37,30,28,-1,-1,40,-1,-1,-1,75,62,60,-1,70,-1,-1,87,-1,-1,-1};
		int[] data = { 50, 25, 12, -1, 37, 30, -1, 51, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87,  -1, -1,-1 };
		Node root = construct(data);
		// Node root1=construct(parent);
		display(root);
		// BinaryTree tree=new BinaryTree();
		// Node root = construct(data);
		// Node root1=construct(data1);
		// System.out.println(size(root));
		// display(root);
		// System.out.println(max(root));
		// System.out.println(sum(root));
		// System.out.println(height(root));
		// System.out.println(find(root,62 ).data);
		// System.out.println(find(root,30));
		// System.out.println(root2node(root, 87));
		// ArrayList<Node> list=node2root(root, 60);
		//
		// for(int i=0;i<list.size();i++) {
		// System.out.print(list.get(i).data+",");
		// }

		// PrintSingleChild(root1,null);
		// System.out.println(remove_leave(root).data);
		// Node root2= construct1(data, data, 0, data.length-1, 0, data.length-1);
		// Node root2=construct2(data, data, 0, data.length-1, 0, data.length-1);
		// display1(root2);
		// Node root2=construct3(data, parent);
		// display(root2);

		// height h=diameter2(root);
		// System.out.print(h.dia);
		// display(root);
		// height1 ht=new height1();
		// System.out.print(isbalanced(root, ht));
		System.out.println(isBst(root).isBST);
		System.out.println(isBst(root).lbstroot);
		System.out.print(isBst(root).lbstsize);
		//

	}
}
