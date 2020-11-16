package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;


public class OrdenDataModel extends ListDataModel<TbOrdentrabajo> implements SelectableDataModel<TbOrdentrabajo> {  

    public OrdenDataModel() {
    }

    public OrdenDataModel(List<TbOrdentrabajo> data) {
        super(data);
    }
    
    @Override
    public TbOrdentrabajo getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbOrdentrabajo> cars = (List<TbOrdentrabajo>) getWrappedData();
        
        for(TbOrdentrabajo car : cars) {
            if(Integer.toString(car.getIdOtrabajo()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbOrdentrabajo car) {
        return car.getIdOtrabajo();
    }
}
                    