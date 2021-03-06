package edu.cnm.deepdive.demo.databasefun;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import edu.cnm.deepdive.demo.databasefun.fragments.AddContentFragment;

public class DatabaseActivity extends AppCompatActivity {

  public DatabaseHelper dbHelper;
  private ListView databaseListView;
  private SQLiteDatabase sqLiteDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_database);

    databaseListView = (ListView) findViewById(R.id.database_list);

    dbHelper = new DatabaseHelper(this);

    sqLiteDatabase =dbHelper.getReadableDatabase();

    refresh();


  }

  public void refresh() {
    Cursor cursor = sqLiteDatabase.query(DatabaseHelper.DATABASE_TABLE,
        new String[] {DatabaseHelper.ID_COLUMN, DatabaseHelper.CONTENT_COLUMN, DatabaseHelper.COLOR_COLUMN},
        null, null, null, null, null);

    ColorCursorAdapter cursorAdapter = new ColorCursorAdapter(this, cursor, true);

    databaseListView.setAdapter(cursorAdapter);
  }

  public void showAddDialog(View view) {
    AddContentFragment dialog = new AddContentFragment();
    dialog.show(getSupportFragmentManager(), "AddContentFragment");
  }

  @Override
  protected void onDestroy() {
    dbHelper.close(); // so that the db gets closed as the app gets destroyed
    super.onDestroy();
  }
}
