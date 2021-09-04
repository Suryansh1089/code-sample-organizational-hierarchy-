import java.util.Iterator;
import java.util.Vector;

public class Hnode implements Comparable<Hnode>
{
	public int id; 
	public int level;
	public Hnode imdt_boss;
	public Vector<Hnode> clist;
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int a) {
		this.level = a;
	}
	public Hnode getImdt_boss() {
		return imdt_boss;
	}
	public void setImdt_boss(Hnode imdt_boss) {
		this.imdt_boss = imdt_boss;
	}
	public Vector<Hnode> getClist() {
		return clist;
	}
	public void setClist(Vector<Hnode> clist) {
		this.clist = clist;
	}
	public Hnode findC(int key)
	{
		Iterator<Hnode> it=clist.iterator();
		while(it.hasNext())
		{Hnode h=it.next();
			if(h.getId()==key)
				return h;
			
		}
		return null;
	}
	public Hnode(int id)
	{
		this.id=id;
		this.clist=new Vector<>();
		
		
	}
	
	public String displayItem() { return ("[" + getId() + "]"); }
	@Override
	public int compareTo(Hnode o)
	{
		return this.getId().compareTo(o.getId());
		
		
	}
}
