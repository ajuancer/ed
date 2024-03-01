
public interface MultiSetIF<E>  extends ContainerIF<E>{
	public void addMultiple(E e, int n);
	
	public void removeMultiple(E e, int n);
	
	public int multiplicity (E e);
	
	public void union (MultiSetIF<E> s);
	
	public void intersection (MultiSetIF<E> s);
	
	public void difference (MultiSetIF<E> s);
	
	public boolean isSubMultiSet(MultiSetIF<E> s);
}
