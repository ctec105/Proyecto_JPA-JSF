package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbUsuario;

public class UsuarioDataModel extends ListDataModel<TbUsuario> implements SelectableDataModel<TbUsuario> {  

    public UsuarioDataModel() {
    }

    public UsuarioDataModel(List<TbUsuario> data) {
        super(data);
    }
    
    @Override
    public TbUsuario getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbUsuario> cars = (List<TbUsuario>) getWrappedData();
        
        for(TbUsuario car : cars) {
            if(Integer.toString(car.getIdUsuario()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbUsuario car) {
        return car.getIdUsuario();
    }
}
                    