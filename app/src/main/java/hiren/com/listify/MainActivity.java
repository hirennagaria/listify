package hiren.com.listify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        //items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();
    }

    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems(itemsAdapter.getCount() - 1, itemText);
    }

    public void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                removeItems(i);
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("Item", items.get(i));
                intent.putExtra("ItemPosition", i);
                startActivityForResult(intent, 121);
            }
        });
    }

    private void removeItems(int itemPos) {
        TodoItems todoItems = SQLite.select().from(TodoItems.class).where(TodoItems_Table.itemID.eq(itemPos)).querySingle();
        todoItems.delete();
        //SQLite.select().from(TodoItems.class).where(TodoItems)
    }

    private void readItems(){
        items = new ArrayList<String>();
        List<TodoItems> todoItemsList = SQLite.select().from(TodoItems.class).queryList();
        for (int i = 0; i < todoItemsList.size(); i++) {
            items.add(i, todoItemsList.get(i).getItemName());
        }

        /*File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try{
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }*/
    }

    private void writeItems(int itemPos, String itemName){
        TodoItems todoItems = new TodoItems();
        todoItems.setItemID(itemPos);
        todoItems.setItemName(itemName);
        todoItems.save();
        /*File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 121) {
            if (resultCode == RESULT_OK) {
                String itemName = data.getStringExtra("Item");
                int itemPos = data.getIntExtra("ItemPosition", -1);
                if(itemPos != -1){
                    items.set(itemPos, itemName);
                    itemsAdapter.notifyDataSetChanged();
                    writeItems(itemPos, itemName);
                }
            }
        }
    }
}
