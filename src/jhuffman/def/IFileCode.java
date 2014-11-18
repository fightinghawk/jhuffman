package jhuffman.def;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import jhuffman.util.UFile;


public class IFileCode
{
	private File file = null;
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	
	public void setFilename(String f)
	{		
		file = new File(f);
	}
	
	public void save(ITable table)
	{
		try
		{
			fos = new FileOutputStream(file);
			UFile uFile = new UFile(fos);
			
			for(int i=0; i<256; i++)
			{
				if(table.arr[i].n>0)
				{
					int nCod = table.arr[i].cod.getLength(); 
					//int m = nCod/8 + (nCod%8 !=0 ? 1:0);
					fos.write(i); //Grabo el caracter.					
					fos.write(nCod); // Grabo la longitud del codigo.
					for(int j=0; j<nCod; j++)
					{
						//Grabo el codigo bit a bit.
						uFile.writeBit(table.arr[i].cod.getBitAt(j));						
					}	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				if(fos!=null) fos.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}

	public ITree load()
	{
		ITree tree = null;
		ITable table = new ITable();
		try
		{
			fis = new FileInputStream(file);			
			UFile uFile = new UFile(fis);
			
			//Cargo la tabla:	
			int c, nCod, bit; 
			c = fis.read(); 		//Leo que caracter es.
			
			while (c>=0)
			{			
				nCod = fis.read(); 	//Leo la longitud del codigo.
				table.arr[c].cod.len = nCod;
				//int m = nCod/8 + (nCod%8 !=0 ? 1:0);
				//int v = Integer.parseInt("10101",2);
				
				for(int j=0; j<nCod; j++)
				{						
					//Leo el codigo bit a bit.
					bit = uFile.readBit();			
					table.arr[c].cod.arr[j] = bit;
				}							
				c = fis.read();								
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				if(fis!=null) fis.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
		
		//Creo lista:
		IList list = new IList();
		list = table.createSortedList();
		
		//Armo arbol:
		tree = list.toTree();
		
		return tree;
	}
}
