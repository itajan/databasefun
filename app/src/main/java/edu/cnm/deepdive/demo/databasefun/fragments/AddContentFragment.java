package edu.cnm.deepdive.demo.databasefun.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.cnm.deepdive.demo.databasefun.DatabaseActivity;
import edu.cnm.deepdive.demo.databasefun.R;

public class AddContentFragment extends DialogFragment {

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();

    View inflatedView = inflater.inflate(R.layout.dialog_add_content, null);

    final EditText contentView = inflatedView.findViewById(R.id.new_content); // On a variable inside a method, "final" makes it immutable
    final EditText colorView = inflatedView.findViewById(R.id.new_color);

    builder.setView(inflatedView)

        .setPositiveButton(R.string.ok, new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            SQLiteDatabase db = ((DatabaseActivity)getActivity()).dbHelper.getWritableDatabase();

            String content = contentView.getText().toString();
            String color = colorView.getText().toString();
            try {
              Color.parseColor(color);
              ((DatabaseActivity) getActivity()).dbHelper.insert(db, content, color);
              ((DatabaseActivity) getActivity()).refresh();
            }catch (IllegalArgumentException e) {
              Toast toast = Toast.makeText(getContext(), R.string.invalid_color, Toast.LENGTH_LONG);
              toast.show();
              return;
            }
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
