package jhuffman.def;

// representa la tabla
// hacer una clase qe representa los campos de la tabla (analogo a un estrutura)
// hacer un array de clases
public class ITable
{
	public class Table
	{
		long n=0;
		ICode cod;
	}
	
	Table arr[] = new Table[256];
	public void addCount(int c)
	{	
		arr[c].n++;
	}
	
	public long getCount(int c)
	{
		return arr[c].n;
	}
	
	// a partir de los datos del array se crea la lista enlazada con la funcion add
	public IList createSortedList()
	{
		return null;
	}
	
	// se lee el arbol hoja por hoja y se cargan los codigos en la tabla
	public void loadHuffmanCodes(ITree tree)
	{
		ICode codHuffman = null;
		Node hoja = tree.next(codHuffman);
		while (hoja != null)
		{
			int c = hoja.getC();
			arr[c].cod = codHuffman;
		}
	}
	
	
	public ICode getCode(int c)
	{
		return arr[c].cod;
	}
}
