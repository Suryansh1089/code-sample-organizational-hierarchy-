
import java.util.Vector;
public class SearchTree {
	public Node root;
	
	public Node getRoot() { return root; }
	public void setRoot(Node root) { this.root = root; }
	
	
	public SearchTree() { this.root = new Node(); }
	int t_size;
	public int find(int id) {
	Node traversal = root;
		
		int c_index;

		do {
			
			if((traversal.findItem(id)) != -1) 
			{
				c_index = traversal.findItem(id);
				return c_index;
			}
			else if(traversal.isLeaf()) return -1;		
			
			else traversal = getNextChild(traversal, id);
		}while(true);
	}
	
	
	
	public Node getNextChild(Node p, int id) {
		 
		int i=0;
		while(i < p.getNumItems())
			{
			if(id < p.getItem(i).getId()) return p.getChild(i);
			i++;}
		
		return p.getChild(i);
	}
	
	
	
	
	public Hnode search(int id)
	{
Node traversal = root;
		
		int c_index;

		do {
			
			if((traversal.findItem(id)) != -1) 
			{
				c_index = traversal.findItem(id);
				return traversal.iArray[c_index];
			}
			else if(traversal.isLeaf()) return null;		
			
			else traversal = getNextChild(traversal, id);
		}while(true);

		
		
		
		
		
		
		
	}
	
	
	
	

	public Node rqNode(int id)
	{
Node traversal = root;
		
		

		do {
			
			if((traversal.findItem(id)) != -1) 
			{
				
				return null;
			}
			else if(traversal.isLeaf()) return traversal;		
			
			else traversal = getNextChild(traversal, id);
		}while(true);

		
		}
		
		
		
		
	
	
	
	
	public void insert(Hnode employee) {
	int id=employee.getId();
	
	Node traverse=getRoot();
	  while(true)
	  {
	if(traverse.isFull())
	{
		split(traverse);
		traverse=getNextChild(traverse.getParent(),id);
	}
	else if(traverse.isLeaf())
	{
		traverse.insertItem(employee);
		t_size++;
		break;
	}
	else
	{
		traverse=getNextChild(traverse,id);
	}
	  }
	}
		

	
	public void split(Node oldNodeForSplit) {
		
	Hnode bItem;
	Hnode cItem;
	
	Node parent;
	//Assuming the node is full, two pointers at the two rightmost children are
	//prepared
	Node child2;
	Node child3;
	//Index of items used later on
	int itemIndex;
	//Regardless whether or not root is being split, item 'C' and 'B' have to be
	//disconnected
	cItem = oldNodeForSplit.removeItem();
	bItem = oldNodeForSplit.removeItem();
	//Regardless whether or not root is being split, the two rightmost children
	//have to be disconnected
	child2 = oldNodeForSplit.dLinkChild(2);
	child3 = oldNodeForSplit.dLinkChild(3);
	//Create a new node that will be used by the split. Item 'C' will move here
	//regardless whether or not root is being split
	Node newNodeForSplit = new Node();

	//If this is the root, create a new node, promote and connect it
	if(oldNodeForSplit == getRoot()) {
		//Create a new node
		setRoot(new Node());
		//The new root becomes the parent of the current node
		parent = getRoot();
		//Connect the new root to the old root
		root.linkChild(0, oldNodeForSplit);
	}
	//This is not the root hence grab the parent of this node
	else parent = oldNodeForSplit.getParent();
	
	//Always move 'B' up (root/non-root) 
	itemIndex = parent.insertItem(bItem);
	
	//Make some space for the new children
	//Move parent's connections one child to the right
	for(int itemN = parent.getNumItems() - 1; itemN > itemIndex; itemN--) {
		Node temp = parent.dLinkChild(itemN);
		parent.linkChild(itemN + 1, temp);
	}
	//Connect newNodeForSplit to parent
	parent.linkChild(itemIndex + 1, newNodeForSplit);
	//Always insert 'C' into the new node (root/non-root split)
	newNodeForSplit.insertItem(cItem);
	//Connect child 2
	newNodeForSplit.linkChild(0, child2);
	//Connect child 3
	newNodeForSplit.linkChild(1, child3);
}
	
	
	public SearchReturn search2(int id)
	{
		Node traversal = getRoot();
		
		int c_index;

		do {
			// Return the child number...
			if((traversal.findItem(id)) != -1)
			{	c_index=traversal.findItem(id);
				return new SearchReturn(traversal,traversal.iArray[c_index]);
			}
			else if(traversal.isLeaf()) 
				{
				return new SearchReturn(traversal,null);		
				}
			else traversal = getNextChild(traversal, id);
		
			}while(true);
		
		
	}
	
	public Hnode delete(Node n,int id)
	{
		if(n.isLeaf())
		{
			if(n.getNumItems()>1)
			{
				Hnode a=n.removeItem(id);
				t_size--;
				return a;
			}
			else if(n.isLast())
			{
				
					
					if(n==getRoot())
					{
						setRoot(new Node());
						Hnode a=n.removeItem();
						n=null;
						t_size=0;
						return a;
					}
					else
					{
						Node sibling=n.getsibiling(id);
					
						if(!sibling.isLast())
						{
						Hnode a=sibling.removeItem();
						Node p=n.getParent();
						Hnode pItemL=p.removeItem();
						p.insertItem(a);
						n.insertItem(pItemL);
						Hnode x=n.removeItem();
						t_size--;
						return x;
						}
						else
						{	Hnode a=n.getItem(0);
							merge(sibling,n);
							return a;
							
							
							
						}
					
					}
				
				
				
			}
			
		}
		else if(!n.isLeaf())
		{
			int i=n.findItem(id);
			Node curr=n.getChild(i);
			while(!curr.isLeaf())
			{
				curr=curr.getChild(curr.getNumItems());
			}
			Hnode replace=curr.iArray[curr.getNumItems()-1];
			Hnode to_be_del=n.iArray[i];
			n.iArray[i]=replace;
			curr.setItem(curr.getNumItems()-1,to_be_del);
					delete(curr,to_be_del.getId());
					
			
			return to_be_del;
		}
		else
		{
			return null;
			
			
		}
		return null;
		
		
	}//node a and b should have same parent
	
	
	public Node merge(Node sib,Node del)
	{
		int sibindex=sib.getCIndex();
		
		Node p=del.getParent();
		if(!p.isLast())
		{	
			
			if(del.isLeaf())
			{del.removeItem();
			t_size--;}
			
			
			
		int phnode_index;
		if(sib_check(sib,del)==0)
		{
			phnode_index=sibindex;
		}
		else
		{	phnode_index=sibindex-1;
		}
		borrw_p(p,p.getItem(phnode_index),sibindex);
		
		balance(p,sib,del);
		
		return sib;//balancing required
		}
		else
		{
			if(p==root)
			{	if(del.isLeaf())
				{del.removeItem();
				t_size--;}
				
				int phnode_index;
				if(sib_check(sib,del)==0)
				{
					phnode_index=sibindex;
				}
				else
					phnode_index=sibindex-1;
				p=borrw_p(p,p.getItem(phnode_index),sibindex);
				
				p.dLinkChild(del.getCIndex());
				root=sib;
				p=null;
				return sib;
			}
			else
			{	
				Node sib_parent=p.getsibiling(p.iArray[0].getId());
				if(del.isLeaf())
				{del.removeItem();
				t_size--;}
				
				int phnode_index;
				if(sib_check(sib,del)==0)
				{
					phnode_index=sibindex;
				}
				else
				{	phnode_index=sibindex-1;
				}
				borrw_p(p,p.getItem(phnode_index),sibindex);
				
				balance(p,sib,del);
				
				
				merge(sib_parent,p);
				return sib;	
				
				
			
			
			}
			
			
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	public Node borrw_p(Node a,Hnode b,int sibindex)
	{//a is the parent node
		//b is the parent hnode
		int index=a.findItem(b.getId());
		if(index!=-1)
		{a.cArray[sibindex].insertItem(b);
			a.removeItem(b.getId());
		
		return a.cArray[sibindex];}
		else
		{System.out.println("error borrw");
			return null;
		}
		
		
	}
	
	public int sib_check(Node bor,Node del)
	{
		if(del==root || bor==root)
		{
			return -1;
		}
		else
		{
			if(del.getCIndex()>bor.getCIndex())
				return 0;//left sibling 
			else
				return 1;//right sibling
			
		}
		
		
	}
	public Hnode pred(Node a,int id)
	{int index=a.findItem(id);
		if(!a.isLeaf())
		{
			Node curr=a.getChild(index);
			if(curr!=null)
			{	while(!curr.isLeaf())
				{
				curr=curr.getChild(0);
				}
			Hnode replace=curr.removeItem();
			return replace;
			}
			else
			{int i=index-1;
				while(curr==null && i>=0)
				{
					curr=a.getChild(i);
					i--;
				}
				Hnode replace=curr.removeItem();
				return replace;
			}
			}
		else
		{
			return null;
			
		}
		
		
		
	}
	public void balance(Node parent,Node sib,Node del)
	{
		
		for(int i=0;i<=parent.getNumItems()+1;i++)
		{
			if(parent.cArray[i].getNumItems()==0)
			{	if(i+1<=parent.getNumItems()+1)
				{if(parent.cArray[i+1].getNumItems()!=0)
				parent.cArray[i]=parent.cArray[i+1];
				else
					break;
				}
			else 
				break;
			}
			
		}
		if(!del.isLeaf())
		sib.cArray[2]=del.cArray[0];
		
		
	}
	public void displayTree(int id)
	{
		Node a=search2(id).getA();
		Vector<Node> eshow=new Vector<>();
		eshow.add(a);
		while(!eshow.isEmpty())
		{
			Node curr=eshow.remove(0);
			print(curr);
			System.out.println();
			for(int i=0;i<=curr.getNumItems();i++)
			{
				eshow.addElement(curr.cArray[i]);
				
				
			}
		}
		
		
	}
	public void print(Node a)
	{		System.out.print(a.parent.getNumItems()+"---");
		for(int i=0;i<a.getNumItems();i++)
		{
			System.out.print(a.iArray[i].getId()+" ||  ");
			
		}
		
		
	}
	
	
	
}
