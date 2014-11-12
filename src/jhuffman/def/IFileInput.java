
package jhuffman.def;

import java.io.File;
import java.io.FileInputStream;
import jhuffman.util.UFile;

public class IFileInput
{
	File file = null;
	FileInputStream fis = null;
	
	public void setFilename(String filename)
	{	
		file = new File(filename);
	}
	
	public String getFilename()
	{		
		return file.getPath();
	}
	
	public long getLength()
	{
		return file.length();
	}
	
	public ITable createTable()
	{
		ITable iT = new ITable();
			
		try
		{
			// abro el archivo para leer los bits
			fis = new FileInputStream(file);			
			UFile uFile = new UFile(fis);
						
			int bit = uFile.readBit();
			while( bit>=0 )
			{
				iT.addCount(bit);
				bit = uFile.readBit();
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
		return iT;
	}
}
