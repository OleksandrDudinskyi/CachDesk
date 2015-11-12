package com.example.duo.cashdesk.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

import com.example.duo.cashdesk.R;

/**
 * @author o.dudinskyi(dudinskyj@gmail.com)
 */
public class WithdrawDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText withdrawAmount;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        withdrawAmount = new EditText(getActivity());
        withdrawAmount.setInputType(InputType.TYPE_CLASS_NUMBER);

        return new AlertDialog.Builder(getActivity()).setTitle(R.string.withdraw_title).setMessage(R.string.withdraw_text)
                .setPositiveButton(R.string.ok_btn, WithdrawDialog.this).setNegativeButton(R.string.cancel_btn, null).setView(withdrawAmount).create();

    }

    @Override
    public void onClick(DialogInterface dialog, int position) {

        String value = withdrawAmount.getText().toString();
        MainActivity callingActivity = (MainActivity) getActivity();
        callingActivity.onUserSelectValue(Integer.parseInt(value));
        dialog.dismiss();
    }
}
