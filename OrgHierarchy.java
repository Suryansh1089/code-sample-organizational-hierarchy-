import java.io.*; 
import java.util.*; 

// Tree node


public class OrgHierarchy implements OrgHierarchyInterface{

//root node
Hnode root;
int size;
public SearchTree s_tree=new SearchTree();
int ownerid;

public boolean isEmpty(){
	return(size==0);	
} 

public int size(){
	return size;
}

public int level(int id) throws IllegalIDException, EmptyTreeException{
	if(isEmpty())
		throw new EmptyTreeException("Organization is empty");
	else
	{Hnode index=s_tree.search(id);
		if(index==null)
			throw new IllegalIDException("no employee of such id");
		else
		{
			return index.getLevel();
			
		}
	}
	 
} 

public void hireOwner(int id) throws NotEmptyException{
	if(!isEmpty())
		throw new NotEmptyException("organization has a owner already");
	else
	{
		
		root=new Hnode(id);
		root.setLevel(1);
		s_tree.insert(root);
		size++;
		ownerid=id;
	}
}

public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	if(isEmpty())
	{
		throw new EmptyTreeException("Organization has no boss");
	}
	else 
	{	
		Hnode boss_index=s_tree.search(bossid);
		if(s_tree.search(id)==null && boss_index!=null)
		{	Hnode emp_index=new Hnode(id);
			boss_index.clist.add(emp_index);
			
			emp_index.setLevel(boss_index.getLevel()+1);
			emp_index.setImdt_boss(boss_index);
			size++;
			s_tree.insert(emp_index);
			
		}
		else
		{
			throw new IllegalIDException("either the boss or employee id is invalid");
		}
	}
} 

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	if(isEmpty())
		throw new EmptyTreeException("organization empty");
	else
	{
		SearchReturn sr=s_tree.search2(id);
		Node a=sr.getA();
		Hnode b=sr.getB();
		if(b==root)
		{
			throw new IllegalIDException("owner cannot be fired");
		}
		else if(!b.getClist().isEmpty())
		{
			throw new IllegalIDException("this is not proper function to fire any type of boss");
		}
		else	
		{s_tree.delete(a,id);
		Hnode imb=b.getImdt_boss();
		imb.getClist().remove(b);
		b.setImdt_boss(null);
		size--;
		}
		
		
		
	}
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	if(isEmpty())
		throw new EmptyTreeException("organization already empty");
	else
	{
		SearchReturn sr1=s_tree.search2(id);
		Hnode b=s_tree.search(manageid);
		
		Node a1=sr1.getA(); 
		Hnode b1=sr1.getB();
		if(b1==root || b==root)
		{
			throw new IllegalIDException("owner tried to be removed or given manage position");
		}

		else if(b1.getClist().isEmpty())
		{
			fireEmployee(id);
		}
		else
		{
			
			
			Vector<Hnode> clist=b1.getClist();
			Hnode imb=b1.getImdt_boss();
			
			s_tree.delete(a1,id);
			
			imb.getClist().remove(b1);
		
			size--;
			for(int i=0;i<clist.size();i++)
			{	Hnode emp_O_b1=clist.elementAt(i);
				emp_O_b1.setImdt_boss(b);
				b.getClist().add(emp_O_b1);
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	if(isEmpty())
		throw new EmptyTreeException("organization is empty");
	else
	{
		
		Hnode emp_index=s_tree.search(id);
		if(emp_index==null)
			throw new IllegalIDException("employee id is invalid");
		else
		{
			Hnode boss_index=emp_index.getImdt_boss();
			if(boss_index==null)
				return -1;
			else
				return boss_index.getId();
			
		}
	}
	
	
	
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	if(isEmpty())
		throw new EmptyTreeException("organization is empty");
	else
	{
		if(id1==ownerid || id2==ownerid)
			return -1;
		else
		{
			Hnode h1=s_tree.search(id1);
			Hnode h2=s_tree.search(id2);
			if(h1==null  || h2==null)
			{
				throw new IllegalIDException("one or both the ids are invalid");
				
			}
			Hnode curr1=h1.getImdt_boss();
			Hnode curr2=h2.getImdt_boss();
			while(curr1.getId()!=ownerid && curr2.getId()!=ownerid)
			{
				if(curr1.getId()==curr2.getId())
					return curr1.getId();
				
				curr1=curr1.getImdt_boss();
				curr2=curr2.getImdt_boss();
			}
			return ownerid;
			
		}
		
		
		
		
	}
	
	
	
	
}


	
	
	
	

	public String toString(int id)throws IllegalIDException,EmptyTreeException 
	{
		if(isEmpty())
		{
			throw new EmptyTreeException("tree is already empty");
			
		}
		else
		{
			Hnode u_root=s_tree.search(id);
			if(u_root==null)
			{
				throw new IllegalIDException("no such id");
				
			}
			
			
			Vector<Hnode> show=new Vector<>();
			show.add(u_root);
			String s="";
			while(!show.isEmpty())
			{
				Hnode curr=show.remove(0);
				
				
				
				Iterator<Hnode> it=curr.getClist().iterator();
				while(it.hasNext())
				{
					show.add(it.next());
					
				}
				if(!show.isEmpty())
				{
					if(curr.getLevel()==show.elementAt(0).getLevel())
				{
					s=s+curr.getId()+" ";
					
				}
				else if(curr.getLevel()<show.elementAt(0).getLevel())
				{   
					s=s+curr.getId()+",";
					Collections.sort(show);
				}
					}
				else
				{
					s=s+curr.getId();
					
				}
				
			}return s;
			
			
			
			
		}



}
}