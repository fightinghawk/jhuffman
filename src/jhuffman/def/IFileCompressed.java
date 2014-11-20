package jhuffman.def;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

import jhuffman.util.UFile;

public class IFileCompressed
{
	File file = null;
	FileOutputStream fos = null;
	FileInputStream fis = null;
	
	public void setFilename(String filename)
	{		
		file = new File(filename);
	}

	public String getFilename()
	{
		return file.getName();
	}
	
	public void save(IFileInput fi, ITable table)
	{
		try
		{
			fis = new FileInputStream(fi.getFilename());
			fos = new FileOutputStream(file);
			UFile uFileOut = new UFile(fos);
			
			//Grabo la longitud del archivo original:
			long longitudFile = fi.getLength();
			uFileOut.writeLength(longitudFile);
			
			int c = fis.read();
			while (c>=0)
			{			
				for(int i=0; i<table.getCode(c).getLength(); i++)
				{
					uFileOut.writeBit(table.getCode(c).getBitAt(i));	
				}			
				
				c = fis.read();
			}
			uFileOut.flush();
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
	
	public void restore(IFileInput fi, ITree tree)
	{
		try
		{
			fis = new FileInputStream(file);
			fos = new FileOutputStream(fi.getFilename());
			UFile uFileIn = new UFile(fis);
			
			long longitudFile = uFileIn.readLength();
			Node nodo = tree.getRoot();
			
			for (long l=0; l<longitudFile; l++)
			{
				while (nodo.getIzq()!=null || nodo.getDer()!=null)
				{
					int bit = uFileIn.readBit();
					if (bit==0)			
					{
						nodo = nodo.getIzq();						
					}
					else
					{
						nodo = nodo.getDer();
					}
				}
				fos.write(nodo.getC());	
				nodo = tree.getRoot();
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
}