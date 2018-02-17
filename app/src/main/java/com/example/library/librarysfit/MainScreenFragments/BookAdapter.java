package com.example.library.librarysfit.MainScreenFragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.librarysfit.R;

import java.util.List;

/**
 * Created by Bhatnagar Rishabh on 2/12/2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;


    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.individual_card_layout, null);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        Book book = bookList.get(position);
        holder.textView.setText(book.getTitle());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(book.getImageView()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public BookViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
