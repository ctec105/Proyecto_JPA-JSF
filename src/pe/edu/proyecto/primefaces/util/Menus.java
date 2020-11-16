package pe.edu.proyecto.primefaces.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name = "menu")
@SessionScoped
public class Menus {

	public void menuIni() {
		Mensajes.info("Opción", "Menu Inicio");
	}

	public void listSol() {
		Mensajes.info("Opción", "Solicitud de Servicio");
	}

	public void listOrd() {
		Mensajes.info("Opción", "Orden de Trabajo");
	}

	public void listLiq() {
		Mensajes.info("Opción", "Orden de Liquidacion");
	}

	public void listPre() {
		Mensajes.info("Opción", "Generar Pre-Factura");
	}

	public void listUsu() {
		Mensajes.info("Opción", "Gestionar Usuarios");
	}

	public void listCli() {
		Mensajes.info("Opción", "Gestionar Clientes");
	}

	public void listPer() {
		Mensajes.info("Opción", "Gestionar Permisos");
	}

	public void listPaq() {
		Mensajes.info("Opción", "Gestionar Paquetes de Servicio");
	}

	public void cambioC() {
		Mensajes.info("Opción", "Cambiar Contraseña");
	}

	public void reporte() {
		Mensajes.info("Opción", "Reportes");
	}
	
	public void cerrarS() {
		Mensajes.info("¡Su sesión ha terminado!", null);
	}

}