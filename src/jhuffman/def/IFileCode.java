package jhuffman.def;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import jhuffman.util.UBuffFile;


public class IFileCode
{
	private File file = null;
	//private FileOutputStream fos = null;
	//private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private BufferedOutputStream bos = null;
	
	public void setFilename(String f)
	{		
		file = new File(f);
	}
	
	public void save(ITable table)
	{
		try
		{
			//fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(new FileOutputStream(file));
			UBuffFile uFile = new UBuffFile(bos);
			
			for(int i=0; i<256; i++)
			{
				if(table.getCount(i)>0)
				{
					bos.write(i); 		//Grabo el caracter.	
					int nCod = table.getCode(i).getLength(); 							
					bos.write(nCod); 	// Grabo la longitud del codigo.
					uFile.writeLength(table.getCount(i));		//Grabo las ocurrencias del caracter.
					
					for(int j=0; j<nCod; j++)
					{
						uFile.writeBit(table.getCode(i).getBitAt(j)); 	//Grabo el codigo bit a bit.
					}	
					uFile.flush(); //Completo el byte con ceros.
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
				if(bos!=null) bos.close();
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
		ITree tree = new ITree();
		ITable table = new ITable();
		try
		{
			//fis = new FileInputStream(file);
			bis = new BufferedInputStream(new FileInputStream(file));
			UBuffFile uFile = new UBuffFile(bis);
			
			//Cargo la tabla:	
			int c, nCod, bit; 
			c = bis.read(); 		//Leo que caracter es.
			
			while (c>=0)
			{			
				nCod = bis.read(); 	//Leo la longitud del codigo.
				table.getCode(c).len = nCod;
				long ocurrencias = uFile.readLength();	//Leo ocurrencias.
				table.setCount(c,ocurrencias);
										
				for(int j=0; j<nCod; j++)
				{						
					//Leo el codigo bit a bit.
					bit = uFile.readBit();			
					table.getCode(c).arr[j] = bit;
				}	
				if (nCod%8 != 0)
				{
					for(int j=nCod;j<(1+nCod/8)*8;j++) //Completo la lectura del byte antes de leer el otro caracter.
					{
						bit = uFile.readBit();
					}
				}
				c = bis.read();								
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
				if(bis!=null) bis.close();
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
