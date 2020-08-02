package munik.androidprojects.tradefab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ShowDialogForMerchantsPuttingData extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =
                getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.cardview_layout_for_putting_items_by_merchant, null);
        Button register_for_merchant=dialogView.findViewById(R.id.register1);
        final EditText item=dialogView
                .findViewById(R.id.item);
        final EditText quantity=dialogView
                .findViewById(R.id.quantity);
        final EditText color=dialogView
                .findViewById(R.id.color);
        final EditText rate=dialogView
                .findViewById(R.id.rate);
        builder.setView(dialogView);
        register_for_merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String itemA=item.getText().toString().trim();
                final String quantityA=quantity.getText().toString().trim();
                final String colorA=color.getText().toString().trim();
                final String rateA=rate.getText().toString().trim();

                if((TextUtils.isEmpty(itemA))||(TextUtils.equals(itemA,"Name"))) {
                    item.setError("name is required");
                    return;
                }
                if((TextUtils.isEmpty(quantityA)||(TextUtils.equals(quantityA,"Name")))) {
                    quantity.setError("name is required");
                    return;
                }
                if((TextUtils.isEmpty(colorA))||(TextUtils.equals(colorA,"Name"))) {
                    color.setError("name is required");
                    return;
                }
                if((TextUtils.isEmpty(rateA))||(TextUtils.equals(rateA,"Name"))) {
                    rate.setError("name is required");
                    return;
                }
                SellerPage callingActivity = (SellerPage) getActivity();
                String temp1=callingActivity.return_list();
                List<String> actual_list=new ArrayList<>();
                //adding item to the list of merchant
                if(temp1.length()==2){
                    String a=item.getText().toString();
                    String b=quantity.getText().toString();
                    String c=color.getText().toString();
                    String d=rate.getText().toString();
                    actual_list.add(add_to_list_the_sellers_details(a,b,c,d));
                    callingActivity.addition(actual_list.toString());
                }
                else{
                    String a=item.getText().toString();
                    String b=quantity.getText().toString();
                    String c=color.getText().toString();
                    String d=rate.getText().toString();
                    String k=temp1.substring(0,temp1.length()-1);
                    k=k+","+add_to_list_the_sellers_details(a,b,c,d);
                    String h="";
                    for(int i=1;i<k.length();i++){
                        h=h+k.charAt(i);
                    }
                    actual_list.add(h);
                    callingActivity.addition(actual_list.toString());
                }
                dismiss();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        }).setInverseBackgroundForced(true);
        return builder.create();

    }
    public String add_to_list_the_sellers_details(String item,String quantity,String color,String rate){
        List<String>  addtolistthesellersdetails=new ArrayList<>();
        addtolistthesellersdetails.add(item);
        addtolistthesellersdetails.add(quantity);
        addtolistthesellersdetails.add(color);
        addtolistthesellersdetails.add(rate);
        return addtolistthesellersdetails.toString();
    }

}
