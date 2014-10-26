package jhuffman.app;

import jhuffman.def.IFileCode;
import jhuffman.def.IFileCompressed;
import jhuffman.def.IFileInput;
import jhuffman.def.IList;
import jhuffman.def.ITable;
import jhuffman.def.ITree;

public class Zip
{
	public static void main(String[] args)
	{
		// abro el archivo
		IFileInput fi = new IFileInput();
		fi.setFilename(args[0]);

		// obtengo la tabla de ocurrencias
		ITable table = fi.createTable();

		// obtengo la lista enlazada
		IList list = table.createSortedList();

		// convierto la lista en arbol
		ITree tree = list.toTree();
		
		// asigno los codigos en la tabla
		table.loadHuffmanCodes(tree);

		// abro el archivo de codigo
		IFileCode codeFile = new IFileCode();
		codeFile.setFilename(args[0] + ".cod");

		// grabo el archivo tomando los codigos del arbol
		codeFile.save(table);

		// abro el archivo comprimido
		IFileCompressed compressFile = new IFileCompressed();
		compressFile.setFilename(args[0] + ".dat");

		// grabo el archivo comprimido
		compressFile.save(fi,table);
	}
}
