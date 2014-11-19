package jhuffman.def;

import java.util.Comparator;

public class IList 
{
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
		// Falta aplicar criterio de ordenamiento si los 2 son iguales
		Node nuevo= new Node();
		nuevo.setC(n.getC());
		nuevo.setSig(NULL); 
		Node ant=NULL;
		Node aux=p;
		while( aux!=NULL && aux.getC()<=n.getC() )
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
		// averiguar bien como se crea el arbol.
		Node der= new Node();
		Node izq= new Node();
		der= removeFirstNode();
		izq= removeFirstNode();
		
		
		return null;
	}
	
	// averiguar bien que hace esto.
	public Comparator<Node> getComparator()
	{
		return null;
	}
}
