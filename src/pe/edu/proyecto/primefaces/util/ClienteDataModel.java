package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbCliente;

public class ClienteDataModel extends ListDataModel<TbCliente> implements SelectableDataModel<TbCliente> {  

    public ClienteDataModel() {
    }

    public ClienteDataModel(List<TbCliente> data) {
        super(data);
    }
    
    @Override
    public TbCliente getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbCliente> cars = (List<TbCliente>) getWrappedData();
        
        for(TbCliente car : cars) {
            if(car.getIdCliente().equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbCliente car) {
        return car.getIdCliente();
    }
}
                    