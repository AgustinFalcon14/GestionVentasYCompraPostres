package sistema;
import java.io.File;
import java.io.*;

//import capaLogica.Fachada;
import valueObjects.VOFachada;
import excepciones.PersistenciaException;

public class persistencia
{
	public void respaldar (String nomArch, VOFachada vofachada )throws IOException, PersistenciaException 
	{		
		try
		{ 
			File archivo = new File(nomArch);
	        File carpeta = archivo.getParentFile();
	        if (carpeta != null && !carpeta.exists())
	        {
	            carpeta.mkdirs(); 		// crea config/ automáticamente
	        }
	
	        FileOutputStream f = new FileOutputStream(archivo);
	        ObjectOutputStream o = new ObjectOutputStream(f);
	        o.writeObject(vofachada);
	        o.close();
	        f.close();
		}
		catch (IOException e)
		{ e.printStackTrace();
			throw new PersistenciaException("error respaldar");
		}
	
			
	}
	
	public VOFachada recuperar (String nomArch)throws PersistenciaException
	{
		try
		{
			FileInputStream f = new FileInputStream(nomArch);  // Abro el archivo y creo un flujo de comunicación hacia él
			ObjectInputStream o = new ObjectInputStream(f); 	// Leo el arreglo de vehículos desde el archivo a través del flujo
		
			VOFachada vofachada = (VOFachada)o.readObject();
			o.close();
			f.close();
			return vofachada;
		}
		catch (FileNotFoundException e) // ← archivo no existe, es normal la primera vez
	    {
	        return null; // la Fachada arranca vacía
	    }
		catch (IOException e)
		{ 
			e.printStackTrace();
			throw new PersistenciaException("error recuperar");
		}
		catch (ClassNotFoundException e)
		{
			 throw new PersistenciaException("Error al recuperar los datos");
		}        
    }
}






//anterior (ultima vez que andaba el programa)
/*

			public class persistencia
			{
				public void respaldar (String nomArch, VOFachada p) 
				{
			        try
			        {
			            FileOutputStream f = new FileOutputStream (nomArch);
			            ObjectOutputStream o = new ObjectOutputStream (f);
			            o.writeObject(p);
			            o.close();
			            f.close();
			        } catch (IOException e)
			        {}
			    }
			        
			        public VOFachada recuperar (String nomArch)
			        {
			            try {
			            	FileInputStream f = new FileInputStream(nomArch);
			            	ObjectInputStream o = new ObjectInputStream(f);  // no (o)
			                VOFachada p = (VOFachada) o.readObject();
			                o.close();
			                f.close();
			                return p;
			            }
			            catch (IOException |  ClassNotFoundException e) {
			            return null;
			        }
			        
			    }
			}
			
		public VOFachada recuperar (String nomArch)
        {
            try {
            	FileInputStream f = new FileInputStream(nomArch);
            	ObjectInputStream o = new ObjectInputStream(f);  // no (o)
                VOFachada p = (VOFachada) o.readObject();
                o.close();
                f.close();
                return p;
            }
            catch (IOException |  ClassNotFoundException e) {
            return null;
        }
			
			
			
 * */



