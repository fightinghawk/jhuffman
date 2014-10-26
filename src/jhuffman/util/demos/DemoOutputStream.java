package jhuffman.util.demos;

import java.io.FileOutputStream;

public class DemoOutputStream
{
	public static void main(String[] args)
	{
		FileOutputStream fos = null;
		try
		{
			String nombreArchivo = args[0];
			String textAGrabar = args[1];
			
			// abro el archivo
			fos = new FileOutputStream(nombreArchivo);


			// itero mientras no llegue el EOF representado por -1
			for( int i=0; i<textAGrabar.length(); i++ )
			{	
				int c = textAGrabar.charAt(i);
				
				// leo el siguiente byte
				fos.write(c);
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
				if( fos!=null ) fos.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
}
