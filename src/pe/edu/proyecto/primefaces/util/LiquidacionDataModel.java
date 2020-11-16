package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;


public class LiquidacionDataModel extends ListDataModel<TbOrdenliquidacion> implements SelectableDataModel<TbOrdenliquidacion> {  

    public LiquidacionDataModel() {
    }

    public LiquidacionDataModel(List<TbOrdenliquidacion> data) {
        super(data);
    }
    
    @Override
    public TbOrdenliquidacion getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbOrdenliquidacion> cars = (List<TbOrdenliquidacion>) getWrappedData();
        
        for(TbOrdenliquidacion car : cars) {
            if(Integer.toString(car.getIdLiquidacion()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbOrdenliquidacion car) {
        return car.getIdLiquidacion();
    }
}
                    