
public interface SetIF<E> extends ContainerIF<E>{
	public void union (SetIF<E> s);
	
	public void intersection (SetIF<E> s);
	
	public void difference (SetIF<E> s);
	
	public boolean isSubset (SetIF<E> s);

}
