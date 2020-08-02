package munik.androidprojects.tradefab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends
        RecyclerView.Adapter<NoteAdapter.ListItemHolder> {
    private List<Person> mNoteList;
    private SellerPage mSellerPage;
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
        return new ListItemHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ListItemHolder holder, int position) {
        Person note = mNoteList.get(position);
        holder.item.setText(note.getmItem());
        holder.quantiy.setText(note.getmQuantity());
        holder.color.setText(note.getmColor());
        holder.cost.setText(note.getmCost());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
    public class ListItemHolder extends
            RecyclerView.ViewHolder{
        TextView item;
        TextView quantiy;
        TextView color;
        TextView cost;
        public ListItemHolder(View view) {
            super(view);
            item = (TextView)
                    view.findViewById(R.id.textView);
            quantiy = (TextView)
                    view.findViewById(R.id.textView6);
            color = (TextView)
                    view.findViewById(R.id.textView9);
            cost = (TextView)
                    view.findViewById(R.id.textView8);
        }
    }
}
