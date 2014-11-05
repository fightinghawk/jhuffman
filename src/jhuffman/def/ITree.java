package jhuffman.def;

import jhuffman.util.UTree;

public class ITree
{
	Node raiz;
	UTree p;
	
	public void setRoot(Node root)
	{	
		raiz = root;
		p = new UTree(raiz);
	}
	
	public Node getRoot()
	{
		return raiz;
	}
	
	public Node next(ICode cod)
	{
		StringBuffer buff = new StringBuffer();
		Node aux = p.next(buff);
		cod.fromString(buff.toString());
		return aux;
	}
}
