package pe.edu.proyecto.primefaces.util;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;

public class PermisoDataModel extends ListDataModel<TbAreaHasTbMenu> implements SelectableDataModel<TbAreaHasTbMenu> {  

    public PermisoDataModel() {
    }

    public PermisoDataModel(List<TbAreaHasTbMenu> data) {
        super(data);
    }
    
    @Override
    public TbAreaHasTbMenu getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<TbAreaHasTbMenu> cars = (List<TbAreaHasTbMenu>) getWrappedData();
        
        for(TbAreaHasTbMenu car : cars) {
            if(Integer.toString(car.getTbMenu().getIdMenu()).equals(rowKey))
                return car;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(TbAreaHasTbMenu car) {
        return car.getTbMenu().getIdMenu();
    }
}
                    