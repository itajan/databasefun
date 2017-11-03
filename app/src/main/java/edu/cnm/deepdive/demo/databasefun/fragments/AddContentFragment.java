package edu.cnm.deepdive.demo.databasefun.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.TextView;
import edu.cnm.deepdive.demo.databasefun.DatabaseActivity;
import edu.cnm.deepdive.demo.databasefun.R;

public class AddContentFragment extends DialogFragment {

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();

    builder.setView(inflater.inflate(R.layout.dialog_add_content, null))

        .setPositiveButton(R.string.ok, new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            SQLiteDatabase db = ((DatabaseActivity)getActivity()).dbHelper.getWritableDatabase();

            String content = ((TextView) ((DatabaseActivity) getActivity()).findViewById(R.id.new_content)).getText().toString();

            String color = ((TextView) ((DatabaseActivity) getActivity()).findViewById(R.id.new_color)).getText().toString();
            ((DatabaseActivity) getActivity()).dbHelper.insert(db, content, color);


          }
        })
        .setNegativeButton(R.string.cancel, new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            AddContentFragment.this.getDialog().cancel();
          }
        });
    return builder.create();
  }
}
