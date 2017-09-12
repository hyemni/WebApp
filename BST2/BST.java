import java.util.*;

class TreeNode {
	private int data;
	private TreeNode left;
	private TreeNode right;    
	
	public TreeNode(int data){
		this.setData(data);
		setLeft(null);
		setRight(null);
	}
 
	public int getData() {return data;}
	public TreeNode getLeft() {return left;}
	public TreeNode getRight() {return right;}
	public void setData(int data) {this.data = data;}
	public void setLeft(TreeNode left) {this.left = left;}
	public void setRight(TreeNode right) {this.right = right;}
}

public class BST {
	public TreeNode root;
	public BST(){this.root = null;}

	//탐색 연산
	public int searchBST(int x){
		TreeNode current = root;
		while(current!=null){
			if(current.getData()==x){return current.getData();}//현재 노드와 찾는 값이 같으면
			else if(current.getData()>x){current = current.getLeft();}//찾는 값이 현재 노드보다 작으면
			else{current = current.getRight();}//찾는 값이 현재 노드보다 크면
		}
		return current.getData();
	}	
	
  //삽입 연산
	public void insert(int x){
		TreeNode newNode = new TreeNode(x);
		if(root==null){
			root = newNode;
			return;
		}
		TreeNode current = root;
		TreeNode parent = null;
		boolean go=true;
		while(go){
			parent = current;
			if(x < current.getData()){                
				current = current.getLeft();
				if(current==null){
					parent.setLeft(newNode);
					go=false;
				}
			}
			else{
				current = current.getRight();
				if(current==null){
					parent.setRight(newNode);
					go=false;
				}
			}
		}
	}
	
  //삭제 연산
	public boolean delete(int x){
		TreeNode parent = root;
		TreeNode current = root;
		boolean isLeftChild = false;//왼쪽자식과 값이 일치하는지 확인시켜주는 역할
		while(current.getData()!=x){
			parent = current;
			if(current.getData()>x){
				isLeftChild = true;
				current = current.getLeft();
			}
			else{
				isLeftChild = false;
				current = current.getRight();
			}
			if(current==null){return false;}
		}
		//Case 1: 자식노드가 없는 경우
		if(current.getLeft()==null && current.getRight()==null){
			if(current==root){root = null;}
			if(isLeftChild==true){parent.setLeft(null);}
			else{parent.setRight(null);}
		}
		
		//Case 2 : 하나의 자식을 갖는 경우
		else if(current.getRight()==null){
			if(current==root){root = current.getLeft();}
			else if(isLeftChild){parent.setLeft(current.getLeft());}
			else{parent.setRight(current.getLeft());}
		} 
		else if(current.getLeft()==null){
			if(current==root){root = current.getRight();}
			else if(isLeftChild){parent.setLeft(current.getRight());}
			else{parent.setRight(current.getRight());}
		}
		
		//Case 3 : 두개의 자식을 갖는 경우
		else if(current.getLeft()!=null && current.getRight()!=null){
			// 오른쪽 서브트리의 최소값을 찾음
			TreeNode successor = getSuccessor(current);
			if(current==root){root = successor;}
			else if(isLeftChild){parent.setLeft(successor);}
			else{parent.setRight(successor);}            
			successor.setLeft(current.getLeft());
		}        
		return true;        
	}
 
	public TreeNode getSuccessor(TreeNode deleleNode){
		TreeNode successsor =null;
		TreeNode successsorParent =null;
		TreeNode current = deleleNode.getRight();
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.getLeft();
		}
		if(successsor!=deleleNode.getRight()){
			successsorParent.setLeft(successsor.getRight());
			successsor.setRight(deleleNode.getRight());
		}
		return successsor;
	}

	public void inorder(TreeNode root){
		if(root!=null){
			inorder(root.getLeft());
			System.out.print(root.getData() + " ");
			inorder(root.getRight());
		}
	}
	public void preorder(TreeNode root){
        if(root!=null){
            System.out.print(root.getData() + " ");
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }
    
    public void postorder(TreeNode root){
        if(root!=null){
            postorder(root.getLeft());
            postorder(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }

	public void printBST(){
		inorder(root);
		System.out.println();
	}   

	public boolean isEmpty(){
		if(root==null){return true;}
		else{return false;}
	} 

	public int nodenum(TreeNode root) { 
		int num=0; 
		if (root==null){num = 0;} 
		else{num = nodenum(root.getLeft()) + nodenum(root.getRight()) + 1;} 
		return num; 
	} 

	public void firstEntry(){
		int first=0;
		TreeNode current = root;
		while(current!=null){
			first = current.getData();
			current = current.getLeft();
		}
		System.out.println("first : "+ first);
	}

	public void lastEntry(){
		int last=0;
		TreeNode current = root;
		while (current!=null){
			last = current.getData();
			current = current.getRight();
		}   
		System.out.println("last : "+ last);
	}

	// public void floorEntry(TreeNode root, int[] floor, int i){
	//     if(root!=null){
	//         floorEntry(root.getLeft(), floor, i);
	//         System.out.print("i:"+i+" "+floor[i]+", ");
	//         floor[i] = root.getData();
	//         floorEntry(root.getRight(), floor, i);
	//     }
	// }

	// public void floorEntry(int x){
	//     int[] floor = new int[nodenum];
	//     int num=0;
	//     floorEntry(root, floor, 0);
	//     for(int i=0; i<floor.length; i++){
	//          if(x>=floor[i]){num=floor[i];}
	//     }   
	//     System.out.println("floor : " + num);
	// }

	// public void ceilingEntry(int x){
	//     int ceiling=0;
	//     TreeNode r = root;
		
	// }

	public int depth(TreeNode root,int x){
		TreeNode current = root;
		if(x==current.getData()){return 0;}
		else if(x>current.getData()){return 1+depth(current.getRight(), x);}
		else{return 1+depth(current.getLeft(), x);}
	}

	public int height(TreeNode root) { 
		int height=0, heightLeft=0, heightRight=0; 
		if(root == null){height=0;} 
		else{ 
			heightLeft = height(root.getLeft()); 
			heightRight = height(root.getRight()); 
			if(heightLeft >= heightRight) {height = heightLeft + 1;}
			else{height = heightRight + 1;}
		} 
		return height; 
	}  


	void start(){
		Scanner s = new Scanner(System.in);
		int num=0;
		int input=0;
		boolean go=true;

		while(go){
			System.out.println("1)insert 2)delete 3)treeprint 4)height 5)first 6)last 7)floor 8)ceiling 9)isEmpty 10)nodenum 11)depth 12)search 13)end");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			System.out.print("num : ");
			num = s.nextInt();
			switch (num) {
				case 1:
					System.out.print("insert : ");
					input = s.nextInt();
					insert(input);
					break;
				case 2:
					System.out.print("delete : ");
					input = s.nextInt();
					delete(input);
					break;
				case 3:
					printBST();
					break;
				case 4:
					TreeNode r = root;
					System.out.println("height : " + (height(r)-1));
					break;
				case 5:
					firstEntry();
					break;
				case 6:
					lastEntry();
					break;
				// case 7:
				//     System.out.print("floor : ");
				//     input = s.nextInt();
				//     floorEntry(input);
				//     break;
				// case 8:
				//     ceilingEntry
				case 9:
					System.out.println(isEmpty());
					break;
				case 10:
					TreeNode c = root;
					System.out.println("nodenum : " + nodenum(c));
					break;  
				case 11:
					System.out.print("depth : ");
					input = s.nextInt();
					System.out.println("depth : " + depth(root, input)); 
					break; 
				case 12:
					System.out.print("search : ");
					input = s.nextInt();
					System.out.println("search : " + searchBST(input));
					break;
				case 13:
					System.out.println("BYE :)");
					go=false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		BST bst = new BST();
		bst.start();
	}
}
