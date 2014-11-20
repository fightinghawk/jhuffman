package jhuffman.def;

public class IList 
{
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
		nuevo.setN(n.getN());
		nuevo.setDer(n.getDer());
		nuevo.setIzq(n.getIzq());
		nuevo.setSig(null); 
		Node ant=null;
		Node aux=p;
		while( aux!=null && compareNode(aux, n)<=0)
		{
			ant = aux;
			aux = aux.getSig();
		}
		if( ant==null )
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
		
		Node izq= removeFirstNode();
		int i=257;
		while(p!=null)
		{

			Node der= removeFirstNode();
			Node aux = new Node();
			aux.setDer(izq);
			aux.setIzq(der);
			aux.setN(izq.getN()+der.getN());
			aux.setC(i);
			addNode(aux);
			izq= removeFirstNode();
			i++;
		}
		ITree arbol = new ITree();
		arbol.setRoot(izq);
		return arbol;
	}
	
	// averiguar bien que hace esto.
	public long compareNode(Node n1, Node n2)
	{
		long N1, N2;
		N1=n1.getN();
		N2=n2.getN();
		return N1<N2?-1:N1>N2?1:n1.getC()<n2.getC()?0:1;
	}
}
