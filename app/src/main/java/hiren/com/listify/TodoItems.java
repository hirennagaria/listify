package hiren.com.listify;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by vieva on 15-Dec-16.
 */

@Table(database = TodosDatabase.class)
public class TodoItems extends BaseModel {

    @Column
    @PrimaryKey
    private int itemID;

    @Column
    String itemName;

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
