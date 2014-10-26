package jhuffman.util.demos;

import jhuffman.util.UShowProgress;

public class UShowProgressDemo
{
	public static void main(String[] args)
	{
		UShowProgress x = UShowProgress.getProgress();

		x.setProcessName("Proceso :O) corto");
		proceso(100);
		x.endProcess();

		x.setProcessName("Proceso mas largo");
		proceso(500);
		x.endProcess();

		x.setProcessName("Proceso intermedio");
		proceso(300);
		x.endProcess();

		x.mostrarEstadisticas();
	}

	public static void proceso(int n)
	{
		try
		{
			UShowProgress x = UShowProgress.getProgress();

			x.setProcessLength(n);
			x.beginProcess();
			for(int i = 0; i < n; i++)
			{
				x.addProgress();
				Thread.sleep((int) (Math.random() * 100));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
