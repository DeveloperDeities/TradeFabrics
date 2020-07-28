package munik.androidprojects.tradefab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

public class ShowingDialogForChoice extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =
                getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.show_dialog, null);
        final ImageView merchant_image=(ImageView)dialogView.findViewById(R.id.merchantSelection);
        final ImageView user_image=(ImageView)dialogView.findViewById(R.id.userSelection);
        builder.setView(dialogView);
        merchant_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*LoginPage callingActivity = (LoginPage) getActivity();
                callingActivity.take_to_Merchant_Login();*/
                Intent intent=new Intent(getActivity(),SignupForMerchant.class);
                startActivity(intent);
            }
        });
        user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*LoginPage callingActivity = (LoginPage) getActivity();
                callingActivity.take_to_buyer_Login();*/
                Intent intent=new Intent(getActivity(),SignupForByer.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        return builder.create();

    }
}
