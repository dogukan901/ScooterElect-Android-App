package com.example.newpage;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newpage.objects.users;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<users> usersListe;

    public usersAdapter(Context mContext, List<users> usersListe) {
        this.mContext = mContext;
        this.usersListe = usersListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kisi_card_tasarim, parent, false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        users user = usersListe.get(position);
        holder.textViewKisiBilgi.setText(user.getUser_name() + "-" + user.getUsed_escooter().getEscooter_number());
        holder.imageView3Nokta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.imageView3Nokta);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_sil:
                                Snackbar.make(holder.imageView3Nokta,"Sil Tıklandı",Snackbar.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_guncelle:
                                alertgoster();
                                return true;
                            default:
                                return false;
                        }

                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewKisiBilgi;
        private ImageView imageView3Nokta;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewKisiBilgi = itemView.findViewById(R.id.textViewKisiBilgi);
            imageView3Nokta = itemView.findViewById(R.id.imageView3Nokta);

        }
    }

    public void alertgoster(){
        LayoutInflater layout = LayoutInflater.from(mContext);
        View tasarim = layout.inflate(R.layout.alert_tasarim, null);

        EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(mContext);

        alertdialog.setTitle("Kişi Güncelle");
        alertdialog.setView(tasarim);

        alertdialog.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String kisi_ad = editTextAd.getText().toString().trim();
                String kisi_tel = editTextTel.getText().toString().trim();

                Toast.makeText(mContext,kisi_ad+"--"+kisi_tel,Toast.LENGTH_SHORT).show();
            }
        });
        alertdialog.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertdialog.create().show();
    }
}
