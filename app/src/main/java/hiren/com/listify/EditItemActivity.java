package hiren.com.listify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    EditText etItemItem;
    Button btnSaveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String itemName = getIntent().getStringExtra("Item");
        final int itemPos = getIntent().getIntExtra("ItemPosition", 0);

        etItemItem = (EditText) findViewById(R.id.etEditItem);
        btnSaveItem = (Button) findViewById(R.id.btnSaveItem);

        etItemItem.setText(itemName);
        etItemItem.setSelection(itemName.length());

        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("Item", etItemItem.getText().toString());
                intent.putExtra("ItemPosition", itemPos);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        super.onDestroy();
    }
}
