package munik.androidprojects.tradefab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter1 extends
        RecyclerView.Adapter<NoteAdapter1.ListItemHolder1> {
    private List<Person> mNoteList;
    private ByerPage mByerPager;
    public NoteAdapter1(ByerPage byerPage,
                       List<Person> noteList) {
        mByerPager=byerPage;
        mNoteList = noteList;

    }

    @NonNull
    @Override
    public NoteAdapter1.ListItemHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.for_putting_item_in_byer_account, parent, false);
        return new NoteAdapter1.ListItemHolder1(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter1.ListItemHolder1 holder, int position) {
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
    public class ListItemHolder1 extends
            RecyclerView.ViewHolder{
        TextView item;
        TextView quantiy;
        TextView color;
        TextView cost;
        public ListItemHolder1(View view) {
            super(view);
            item = (TextView)
                    view.findViewById(R.id.textView10);
            quantiy = (TextView)
                    view.findViewById(R.id.textView11);
            color = (TextView)
                    view.findViewById(R.id.textView12);
            cost = (TextView)
                    view.findViewById(R.id.textView13);
        }
    }
}
