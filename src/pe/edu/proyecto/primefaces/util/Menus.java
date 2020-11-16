package pe.edu.proyecto.primefaces.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name = "menu")
@SessionScoped
public class Menus {

	public void menuIni() {
		Mensajes.info("Opci�n", "Menu Inicio");
	}

	public void listSol() {
		Mensajes.info("Opci�n", "Solicitud de Servicio");
	}

	public void listOrd() {
		Mensajes.info("Opci�n", "Orden de Trabajo");
	}

	public void listLiq() {
		Mensajes.info("Opci�n", "Orden de Liquidacion");
	}

	public void listPre() {
		Mensajes.info("Opci�n", "Generar Pre-Factura");
	}

	public void listUsu() {
		Mensajes.info("Opci�n", "Gestionar Usuarios");
	}

	public void listCli() {
		Mensajes.info("Opci�n", "Gestionar Clientes");
	}

	public void listPer() {
		Mensajes.info("Opci�n", "Gestionar Permisos");
	}

	public void listPaq() {
		Mensajes.info("Opci�n", "Gestionar Paquetes de Servicio");
	}

	public void cambioC() {
		Mensajes.info("Opci�n", "Cambiar Contrase�a");
	}

	public void reporte() {
		Mensajes.info("Opci�n", "Reportes");
	}
	
	public void cerrarS() {
		Mensajes.info("�Su sesi�n ha terminado!", null);
	}

}