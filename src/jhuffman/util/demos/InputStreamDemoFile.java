package jhuffman.util.demos;

import java.io.FileInputStream;

public class InputStreamDemoFile
{
	public static void main(String[] args)
	{
		FileInputStream fis = null;
		try
		{
			String nombreArchivo = args[0];
			
			// abro el archivo
			fis = new FileInputStream(nombreArchivo);

			int cont=0;
			
			// leo el primer byte
			int c = fis.read();

			// itero mientras no llegue el EOF representado por -1
			while( c!=-1 )
			{	
				cont++;
				
				// leo el siguiente byte
				c = fis.read();

			}
			
			System.out.println(nombreArchivo+" tiene "+cont+" bytes");
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
				if( fis!=null ) fis.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
}
