package jhuffman.util.demos;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import jhuffman.util.UFile;

public class UFileDemo
{
	public static void main(String[] args)
	{
		grabarBits("DEMO.dat","1011101101110");
		leerBits("DEMO.dat");
	}

	private static void grabarBits(String nomArch, String bits)
	{
		FileOutputStream fos = null;
		
		try
		{
			// abro el archivo para grabar los bits
			fos = new FileOutputStream(nomArch);
			
			UFile uFile = new UFile(fos);
			
			// recorro la cadena de bits
			for(int i=0; i<bits.length(); i++)
			{
				// obtengo el i-esimo bit (1 o 0)
				int bit = bits.charAt(i)-'0';
				
				// lo grabo en el archivo
				uFile.writeBit(bit);
			}
			
			// si quedo un paquete a medio formar lo completo con ceros
			// a la derecha y lo grabo
			uFile.flush();
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
	
	private static void leerBits(String nomArch)
	{
		FileInputStream fis = null;
		
		try
		{
			// abro el archivo para grabar los bits
			fis = new FileInputStream(nomArch);
			
			UFile uFile = new UFile(fis);
			
			// recorro la cadena de bits
			int bit = uFile.readBit();
			while( bit>=0 )
			{
				System.out.println(bit);

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
	}
}
