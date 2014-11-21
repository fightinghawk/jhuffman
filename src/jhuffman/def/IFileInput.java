
package jhuffman.def;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class IFileInput
{
	File file = null;
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	
	public void setFilename(String filename)
	{	
		file = new File(filename);
	}
	
	public String getFilename()
	{		
		return file.getName();
	}
	
	public long getLength()
	{
		return file.length();
	}
	
	public ITable createTable()
	{
		ITable table = new ITable();
			
		try
		{
			// abro el archivo para leer los bits
			//fis = new FileInputStream(file);
			bis = new BufferedInputStream(new FileInputStream(file));
						
			int c = bis.read();
			while( c>=0 )
			{
				table.addCount(c);
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
		return table;
	}
}
