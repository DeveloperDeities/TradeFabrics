package munik.androidprojects.tradefab;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends
        RecyclerView.Adapter<NoteAdapter.ListItemHolder> {
    private List<Person> mNoteList;
    private List<String> mNoteList1;
    private SellerPage mSellerPage;
    ShowDialogForMerchantsPuttingData showDialogForMerchantsPuttingData=new ShowDialogForMerchantsPuttingData();
    public NoteAdapter(SellerPage sellerPage,
                       List<Person> noteList) {
        mSellerPage=sellerPage;
        mNoteList = noteList;

    }

    @NonNull
    @Override
    public NoteAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.for_putting_items_in_sellers_account, parent, false);
        return new ListItemHolder(itemView,mSellerPage);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ListItemHolder holder, int position) {
        Person note = mNoteList.get(position);
        holder.item.setText(note.getmItem());
        holder.quantiy.setText(note.getmQuantity());
        holder.color.setText(note.getmColor());
        holder.cost.setText(note.getmCost());
        if(!mSellerPage.isContextualModeUnable){
            holder.forDeleting.setVisibility(View.GONE);
        }
        else{
            holder.forDeleting.setVisibility(View.VISIBLE);
            holder.forDeleting.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
    public class ListItemHolder extends
            RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item;
        TextView quantiy;
        TextView color;
        TextView cost;
        CheckBox forDeleting;
        View itemView;
        public ListItemHolder(View view,SellerPage sellerPage) {
            super(view);
            item = (TextView)
                    view.findViewById(R.id.textView);
            quantiy = (TextView)
                    view.findViewById(R.id.textView6);
            color = (TextView)
                    view.findViewById(R.id.textView9);
            cost = (TextView)
                    view.findViewById(R.id.textView8);
            forDeleting=(CheckBox)view.findViewById(R.id.forDeleting);
            itemView=view;
            itemView.setOnLongClickListener(sellerPage);
            forDeleting.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSellerPage.MAkeSelectItem(v,getAdapterPosition());
        }
    }
    public void  removeItem(List<Person> mNoteList1){
        for(int i=0;i<mNoteList1.size();i++){
            mNoteList.remove(mNoteList1.get(i));
            notifyDataSetChanged();
        }
    }
    public String removal(){

        mNoteList1=new ArrayList<>();
        int count=0;
        int count1=0;
        for(int i=0;i<mNoteList.size();i++){
           String h= showDialogForMerchantsPuttingData.add_to_list_the_sellers_details(mNoteList.get(i).getmItem(),mNoteList.get(i).getmQuantity(),mNoteList.get(i).getmColor(),mNoteList.get(i).getmCost());
          for(int j=0;j<h.length();j++){
              if(h.charAt(j)!='['){
                  count=j;
                  break;
              }
          }
          for(int k=h.length()-1;k>0;k--){
              if(h.charAt(k)!=']')
              {
                  count1=k;
                  break;
              }
          }
          String l="";
          for(int u=count-1;u<=count1+1;u++){
              l=l+h.charAt(u);
          }
           Log.i("info","result="+l);
           mNoteList1.add(l);
        }

        return mNoteList1.toString();
    }
}
