package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import capaLogica.IFachada;
import valueObjects.VOPostre;
import valueObjects.VOPostreLight;
import excepciones.PostreYaExisteException;

public class CtrlRegistrarPostre 
{

    private IFachada fachada;

    public CtrlRegistrarPostre(IFachada fachada) 
    {
        this.fachada = fachada;
    }

    public void registrar(JFrame frame, String strCodigo, String nombre,
            String strPrecio, boolean esComun, String endulzante, String descripcion) 
    {
        try
        {
            if (strCodigo.isEmpty() || nombre.isEmpty() || strPrecio.isEmpty()) 
            {
                JOptionPane.showMessageDialog(frame, "Por favor complete todos los campos obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String codigo = strCodigo;
            double precio = Double.parseDouble(strPrecio.trim());

            if (precio <= 0)
            {
                JOptionPane.showMessageDialog(frame, "El precio debe ser mayor a cero.",
                    "Precio inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (esComun) 
            {
                // tipo = true => Común
                VOPostre vo = new VOPostre(codigo, nombre.trim(), precio, true);
                fachada.RegistrarNuevoPostre(vo);
            } else 
            	{
	                // Light: tipo = false
	                if (endulzante.isEmpty() || descripcion.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Complete los campos de postre Light.",
	                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
	                    return;
                }
                VOPostreLight vo = new VOPostreLight(codigo, nombre.trim(), precio, false,
                    endulzante.trim(), descripcion.trim());
                fachada.RegistrarNuevoPostre(vo);
            }

            JOptionPane.showMessageDialog(frame, "Postre registrado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(frame, "Código y precio deben ser numéricos.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } 
        catch (PostreYaExisteException ex) 
        {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(),
                "Postre ya existe", JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}