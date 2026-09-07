package capaLogica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import excepciones.*;
import valueObjects.*;


public interface IFachada extends Remote  
{   
	/*1*/public void RegistrarNuevoPostre(VOPostre voPostre) throws RemoteException, PostreYaExisteException; 
	/*2*/public ArrayList<VOPostre>ListadoGeneralDePostres() throws RemoteException, DiccioVacioException;
	/*3*/public VOPostre ListadoDetalladoPostre (VOPostre voPostre)throws RemoteException, PostreNoExisteException, DiccioVacioException;
	/*4*/public void RegistrarNuevaVenta(VONuevaVenta voVenta) throws RemoteException, FechaInvalidaException;
	/*5*/public void AgregarPostreVenta(VOModificarPostreVenta voModificarPostreVenta) throws RemoteException, SumaSuperaLimiteException, SeSupero40Exception, VentaNoExisteException, PostreNoExisteException, VentaYaFinalizadaException;
	/*6*/public void EliminarPostreVenta(VOModificarPostreVenta voModificarPostreVenta) throws RemoteException, PostreNoEnVentaException, VentaNoExisteException, PostreNoExisteException, VentaYaFinalizadaException;
	/*7*/public double FinalizarVentas(VOModificarEstado voModificarEstado) throws RemoteException, VentaYaFinalizadaException, VentaNoExisteException;
	/*8*/public ArrayList<VOListadoVentas>ListadoVentas(VOListadoVentas vo) throws RemoteException, NoEsTPFException;
	/*9*/public ArrayList<VOListadoPostresVentas>ListadoPostresVentas(VOListadoPostresVentas vo) throws RemoteException, VentaNoExisteException;
	/*10*/ public VOMontoTotal RecaudacionPostreFecha(VOMontoTotal mT) throws RemoteException, PostreNoExisteException;
	/*11*/public void Respaldar (VOSistema voSistema) throws RemoteException;
	/*12*/public VOSistema Recuperar() throws RemoteException;
}