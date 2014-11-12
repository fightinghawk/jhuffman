package jhuffman.def;

import java.util.Comparator;

public class IList 
{
	///Hay que usar las funciones que tiene asociada node para asignar
	// el siguiente para los nodos de la lista
	
	
	private static final Node NULL = null;
	Node p;
	
	//desenlazar y devolver puntero, no liberarlo
	public Node removeFirstNode()
	{
		Node aux=p;
		p=p.getSig();
		return aux;
	}
	//inserta ordenado
	public void addNode(Node n)
	{
		Node nuevo= new Node();
		nuevo.setC(n.getC());
		nuevo.setSig(NULL);
		Node ant=NULL;
		Node aux=p;
		while( aux!=NULL && aux.getC()<=n.getC())
		{
		ant = aux;
		aux = aux.getSig();
		}
		if( ant==NULL )
		{
		p = nuevo;
		}
		else
		{
		ant.setSig(nuevo);
		}
		nuevo.setSig(aux);
	}
	
	//sacar 2 nodos a la lista y se crea un "pequeño arbol bnario" con las 2 hojas
	
	public ITree toTree()
	{
		return null;
	}
	
	/*la interfaz es una definicion de una clase, la funcion comparator es una
	 * interfaz
	 * 
	 */
	public Comparator<Node> getComparator()
	{
		return null;
	}
}
