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
		return file.getPath();
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
			
			for(long l=0; l<longitudFile; l++)
			{
				int bit = fis.read();
				for(int i=0; i<table.arr[bit].cod.getLength(); i++)
				{
					uFileOut.writeBit(table.arr[bit].cod.getBitAt(i));	
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
	
	public void restore(IFileInput fi, ITree tree)
	{
	}
}