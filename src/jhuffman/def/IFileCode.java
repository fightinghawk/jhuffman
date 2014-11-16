package jhuffman.def;

import java.io.File;
import java.io.FileOutputStream;
import jhuffman.util.UFile;

public class IFileCode
{
	File file = null;
	FileOutputStream fos = null;
	
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
				for( int j=0; i<table.arr[i].cod.len; j++)
				{
					int bit = table.arr[i].cod.getBitAt(j);			
					uFile.writeBit(bit);
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
		return null;
	}
}
