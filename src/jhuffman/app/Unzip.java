package jhuffman.app;

import jhuffman.def.IFileCode;
import jhuffman.def.IFileCompressed;
import jhuffman.def.IFileInput;
import jhuffman.def.ITree;

public class Unzip
{
	public static void main(String[] args)
	{
		// abro el archivo de codigos
		IFileCode codeFile = new IFileCode();
		codeFile.setFilename(args[0] + ".cod");

		// leo el archivo y genero la lista
		ITree tree = codeFile.load();

		// abro el archivo comprimido
		IFileCompressed compressFile = new IFileCompressed();
		compressFile.setFilename(args[0] + ".dat");
		
		// abro el archivo
		IFileInput fi = new IFileInput();
		fi.setFilename(args[0]);

		// recupera el archivo original
		compressFile.restore(fi,tree);
	}
}
