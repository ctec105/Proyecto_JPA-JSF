package pe.edu.proyecto.jsf.managed;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.ClienteService;
import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.primefaces.util.ClienteDataModel;
import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name="formCliente")
@SessionScoped
public class ClienteBean {
	
	// Businnes Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static ClienteService clienteService;
	
	// Instances
	private TbCliente cliente = new TbCliente();
	private List<TbCliente> clienteList;
	private ClienteDataModel mediumClientesModel; // PrimeFaces listado
	private boolean flag = false; // flag para ocultar elementos

	// Getters and Setters
	public TbCliente getCliente() {
		return cliente;
	}
	public void setCliente(TbCliente cliente) {
		this.cliente = cliente;
	}
	public List<TbCliente> getClienteList() {
		return clienteList;
	}
	public void setClienteList(List<TbCliente> clienteList) {
		this.clienteList = clienteList;
	}
	public ClienteDataModel getMediumClientesModel() {
		return mediumClientesModel;
	}
	public void setMediumClientesModel(ClienteDataModel mediumClientesModel) {
		this.mediumClientesModel = mediumClientesModel;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	// Actions Methods
	public String listarClientes() throws Exception{
		System.out.println("Estoy dentro del método listarClientes() - INI");
		clienteService = abd.getClienteService();
		clienteList = clienteService.listadoCliente();
		mediumClientesModel = new ClienteDataModel(clienteList); // PrimeFaces listado
		System.out.println("Estoy dentro del método listarClientes() - FIN");
		return "cliente";
	}
	
	public String edit() throws Exception{
		System.out.println("Estoy dentro del método edit() - INI");
		
		cliente = new TbCliente();
		flag = false;
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		System.out.println("*** Cliente: " + codigo);
		
		if (codigo != null && codigo.length() > 0) {
			System.out.println("Entramos a editar");
			flag = true;
			clienteService = abd.getClienteService();
			cliente.setIdCliente(codigo);
			cliente = clienteService.obtenerCliente(cliente);
		}

		System.out.println("Estoy dentro del método edit() - FIN");
		return "clienteForm";
	}
	
	public String registrarOrActualizar()throws Exception{
		System.out.println("Estoy dentro del método registrarOrActualizar() - INI");
		
		clienteService = abd.getClienteService();
		
		if (cliente.getIdCliente().length() == 0){
			Mensajes.info("¡Ingrese ID del ciente!", null);
			return null;
		}
		
		if (cliente.getRazonsocial().length() == 0){
			Mensajes.info("¡Ingrese la razón social!", null);
			return null;
		}
		
		if (cliente.getDireccion().length() == 0){
			Mensajes.info("¡Ingrese una dirección!", null);
			return null;
		}
		
		if (cliente.getTelefono().length() == 0){
			Mensajes.info("¡Ingrese un teléfono!", null);
			return null;
		}
		
		if (flag == false) {
			System.out.println("Insertando cliente");
			clienteService.registrarCliente(cliente);
			Mensajes.info("Exitoso", "¡Cliente Registrado!");
		} else {
			System.out.println("Actualizando cliente");
			clienteService.actualizarCliente(cliente);
			Mensajes.info("Exitoso", "¡Cliente Actualizado!");
		}
		
		System.out.println("Estoy dentro del método registrarOrActualizar() - FIN");
		return listarClientes();
	}
	
	public String eliminar() throws Exception{
		System.out.println("Estoy dentro del método eliminar() - INI");
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		clienteService = abd.getClienteService();
		cliente = new TbCliente();
		cliente.setIdCliente(codigo);
		clienteService.eliminarCliente(cliente);
		Mensajes.info("Exitoso", "¡Cliente eliminado!");
		System.out.println("Estoy dentro del método eliminar() - FIN");
		return listarClientes();
	}
	
	public void onRowSelect(SelectEvent event) {
        Mensajes.info("Cliente ", ((TbCliente) event.getObject()).getRazonsocial());
    }

    public void onRowUnselect(UnselectEvent event) {
        Mensajes.info("Cliente ", ((TbCliente) event.getObject()).getRazonsocial());
    }
    
}
