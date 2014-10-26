package jhuffman.util.demos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BuffersIODemo
{
	public static void main(String[] args)
	{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try
		{
			// nombre de archivo origen 
			String desde = args[0];
			
			// nombre de archivo destio
			String hasta = args[1];
			
			bis = new BufferedInputStream(new FileInputStream(desde));
			bos = new BufferedOutputStream(new FileOutputStream(hasta));
			
			// leo el primer byte desde el buffer de entrada
			int c = bis.read();
			
			while( c!=-1 )
			{
				// escribo el byte en el buffer de salida
				bos.write(c);
				
				// leo el siguiente byte
				c = bis.read();
			}
			
			// vacio el contenido del buffer
			bos.flush();
			
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
				if( bos!=null ) bos.close();
				if( bis!=null ) bis.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
}
