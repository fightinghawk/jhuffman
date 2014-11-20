package jhuffman.def;

// representa la tabla
// hacer una clase qe representa los campos de la tabla (analogo a un estrutura)
// hacer un array de clases
public class ITable
{
	public class campos
	{
		long n = 0;
		ICode cod = new ICode();
	}
	
	public class Table
	{
		private campos arr[] = new campos[256];
	
		public Table()
		{
			for (int i=0;i<256;i++)
			{
				arr[i] = new campos();
			}
		}
	}
	
	Table tabla = new Table();
	
	public void addCount(int c)
	{
		tabla.arr[c].n++;
	}
	
	public long getCount(int c)
	{
		return tabla.arr[c].n;
	}
	
	public void setCount(int c, long o) //Agregado para resolver tema de ocurrencias.
	{
		tabla.arr[c].n = o;
	}
	
	// a partir de los datos del array se crea la lista enlazada con la funcion add
	public IList createSortedList()
	{
		IList lista = new IList();
		Node nodo = new Node();
		for (int i=0;i<256;i++)
		{
			if (getCount(i)>0)
			{
				nodo.setC(i);
				nodo.setN(getCount(i));
				lista.addNode(nodo);
			}
		}
		return lista;
	}
	
	// se lee el arbol hoja por hoja y se cargan los codigos en la tabla
	public void loadHuffmanCodes(ITree tree)
	{
		ICode codHuffman = new ICode();
		Node hoja = tree.next(codHuffman);
		while (hoja != null)
		{
			int c = hoja.getC();
			//tabla.arr[c].cod = codHuffman;
			for(int i=0;i<codHuffman.len;i++)
			{
				tabla.arr[c].cod.arr[i] = codHuffman.getBitAt(i);
			}
			tabla.arr[c].cod.len = codHuffman.getLength();
			hoja = tree.next(codHuffman);
		}
	}
	
	public ICode getCode(int c)
	{
		return tabla.arr[c].cod;
	}
}
