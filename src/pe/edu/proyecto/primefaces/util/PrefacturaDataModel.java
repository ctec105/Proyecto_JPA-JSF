package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbPrefactura;


public class PrefacturaDataModel extends ListDataModel<TbPrefactura> implements SelectableDataModel<TbPrefactura> {  

    public PrefacturaDataModel() {
    }

    public PrefacturaDataModel(List<TbPrefactura> data) {
        super(data);
    }
    
    @Override
    public TbPrefactura getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbPrefactura> cars = (List<TbPrefactura>) getWrappedData();
        
        for(TbPrefactura car : cars) {
            if(Integer.toString(car.getIdPrefactura()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbPrefactura car) {
        return car.getIdPrefactura();
    }
}
                    