public class Node {
	public int nItems;
	
	public static final int U_CHILD = 4;
	public static final int U_VALUE= 3;
	
	public Node parent;
	
	public Node cArray [] = new Node[4];
	
	public Hnode iArray [] = new Hnode[3];
	
	public Node getChild(int c_index) { return cArray[c_index]; }
	public void setChild(int c_index, Node child) { cArray[c_index] = child; }
	
	//Useful methods for dealing with iArray
	public Hnode getItem(int index) { return iArray[index]; }
	public void setItem(int index, Hnode item) { iArray[index] = item; }
	public boolean isItemNull(int index) { return (iArray[index] == null) ? true : false; }
	

	
	public Node getParent() { return parent; }
	
	
	public boolean isLeaf() { return (getChild(0) == null); }
	
	public boolean isLast() {return (getNumItems()<1);}
	public boolean isFull() { return (getNumItems() == 3); }
	//Connect child to this Node
	
	
	public void linkChild(int c_index, Node child) {
		
		cArray[c_index]=child;
		if(child != null) child.parent = this;
	}
	public Node dLinkChild(int c_index) {
		
		Node rq = getChild(c_index);
		
		cArray[c_index]=null;
		
		return rq;
	}

	 
	
	public int insertItem(Hnode newItem) {
		int a=getNumItems();
		if(!isFull())
		{
			
			for(int i=a-1;i>=0;i--)
			{
				if(iArray[i].getId()<newItem.getId())
				{
					setItem(i+1,newItem);
					nItems++;
					return i+1;
					
				}
				else if(iArray[i].getId()>newItem.getId())
				{
					setItem(i+1,iArray[i]);
				}
				else
				{
					return -1;
				}
				
				
			}
			setItem(0,newItem);
			nItems++;
			return 0;
		}
		else
		{
			System.err.println("Node is full");
			return -1;
			
		}
		
		
		
		
	}

	//Remove the largest item
	public Hnode removeItem() {
		//Assuming the Node is empty, save the item
		Hnode temp = getItem(getNumItems() - 1);
		//Disconnect the item
		setItem(getNumItems() - 1, null);
		//Decrease the counter
		decreaseItemNum();
		
		return temp;
	}
	
	public int findItem(int key) {
		for(int i = 0; i < 3; i++) {
			
			if(iArray[i]==null) 
			{
				break;
			}
			 
			else if(getItem(i).getId() == key)
			{
			return i;
			}
		}
		
		return -1;
	}  

	
	
	public Hnode removeItem(int id)
	{
		int a=findItem(id);
		if(a!=-1 && a<getNumItems())
		{Hnode ret=iArray[a];
			for(int i=a;i<getNumItems();i++)
			{	if(i+1<getNumItems())
				iArray[i]=iArray[i+1];
			else
				{
				iArray[i]=null;
				
				}
			}
			decreaseItemNum();
			return ret;
			
		}
		else if(a==-1)
		{
			System.out.println("KEY NOT FOUND");
			return null;
		}
		else
		{
			
			return null;
		}
		
	}
		
	
	public String displayNode() {
		String x = new String("");
		for(int Node = 0; Node < getNumItems(); Node++) x = x + getItem(Node).displayItem();
		
		return x;
	}
	public Node getsibiling(int theValue) {
		
		Node x = null;
		Node p = getParent();
		if (nItems != 0) {
			if(getCIndex()==0)
			{
				if(p.getNumItems()==0)
				{System.out.println("it is a root");
					return null;
				}
				else
				{
					return p.cArray[1];
					
				}
			}
				
			
			else
			{
			for (int i = 0; i <= p.nItems; i++) {
				if (p.cArray[i].iArray[0].getId() < theValue) {
					
					x = p.cArray[i];
				}}
			}
		} else if (nItems == 0) {
			for (int i = 0; i <= p.nItems; i++) {
				if (p.cArray[i].iArray[0] == null) {
					
					if (i != 0) {
						x = p.cArray[i - 1];
					}
					else
					{	if(i+1<=p.nItems)
						x=p.cArray[i+1];
					}
				}
			}
		}
		
		return x;
		
	}
	public void insertatfront(Hnode newItem) {

		int newKey = newItem.getId(); // key of new item
		nItems++;
		for (int j = nItems - 1; j > 0; j--) {
			iArray[j] = iArray[j - 1];
			linkChild(j + 1, dLinkChild(j));
		}
		linkChild(1, dLinkChild(0));
		iArray[0] = newItem;
		linkChild(0, null);

		return;
	}
	//CREATED TO CHANGE NUM VARIABLE
		public int getNumItems() { return nItems; }
		public void setNumItems(int a) {this.nItems=a;}
		public void increaseItemNum() { nItems++; }
		public void decreaseItemNum() { nItems--; }
		
		public int getCIndex()
		{
			if(getParent()==null)
				return -1;
			else
			{int i;
				for(i=0;i<4;i++)
				{
					if(getParent().cArray[i]==this)
						return i;
				}
				System.out.println("no such CIndex");
				return i-1;
			}
			
		}

}
