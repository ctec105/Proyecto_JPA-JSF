package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbSolicitud;

public class SolicitudDataModel extends ListDataModel<TbSolicitud> implements SelectableDataModel<TbSolicitud> {  

    public SolicitudDataModel() {
    }

    public SolicitudDataModel(List<TbSolicitud> data) {
        super(data);
    }
    
    @Override
    public TbSolicitud getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbSolicitud> cars = (List<TbSolicitud>) getWrappedData();
        
        for(TbSolicitud car : cars) {
            if(Integer.toString(car.getIdSolicitud()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbSolicitud car) {
        return car.getIdSolicitud();
    }
}
                    