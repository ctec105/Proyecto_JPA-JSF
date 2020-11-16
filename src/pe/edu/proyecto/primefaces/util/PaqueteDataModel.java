package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbPaquete;

public class PaqueteDataModel extends ListDataModel<TbPaquete> implements SelectableDataModel<TbPaquete> {  

    public PaqueteDataModel() {
    }

    public PaqueteDataModel(List<TbPaquete> data) {
        super(data);
    }
    
    @Override
    public TbPaquete getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbPaquete> cars = (List<TbPaquete>) getWrappedData();
        
        for(TbPaquete car : cars) {
            if(Integer.toString(car.getIdPaquetes()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbPaquete car) {
        return car.getIdPaquetes();
    }
}
                    