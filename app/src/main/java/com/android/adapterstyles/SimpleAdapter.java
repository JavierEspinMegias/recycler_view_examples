package com.android.adapterstyles;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder>{


    private ArrayList<AppUser> users;
    public boolean isIcon = false;

    public SimpleAdapter(ArrayList<AppUser> users) {
        this.users = users;
    }

    //El constructor deberá enlazar los datos del modelos con los del controlador
    public SimpleAdapter(ArrayList<AppUser> users, boolean isAnIcon) {
        this.users = users;
        isIcon = isAnIcon;
    }

    //En un adaptador es obligatorio definir una clase que herede de RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //La clase ViewHolder hará referencia a los elementos de la vista creada para el recycler view
        public TextView name;
        public TextView id;
        private Button deleteUser;
        public ImageView imageUser;

        //Su constructor debera enlazar las variables del controlador con la vista
        public ViewHolder(final View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.user_name);
            this.id = (TextView) itemView.findViewById(R.id.user_id);
            this.deleteUser=(Button)itemView.findViewById(R.id.custom_buttom_1);
            this.imageUser = (ImageView)itemView.findViewById(R.id.image_view_adapter);

        }
    }

    @NonNull
    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Especificamos el fichero XML que se utilizará como vista
        View contactView = inflater.inflate(R.layout.adapter_users, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);

        // Comprobamos si la vista es horizontal o vertical
        if (viewType == 0) {

        } else if (viewType == 1) {

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleAdapter.ViewHolder holder, final int position) {

        //Vamos obteniendo mail por mail
        final AppUser user = this.users.get(position);
        //Enlazamos los elementos de la vista con el modelo
        holder.name.setText("name"+user.name+position);
        holder.id.setText("-id-+position");
        holder.deleteUser.setText("Remove"+position);

        holder.deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0, users.size());
            }
        });


//        int newColor = getRandomColor();
//        holder.deleteUser.setTextColor(newColor);
//
//        if (isColorDark(newColor)){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                holder.iconLetter.setTextColor(holder.itemView.getContext().getColor(R.color.colorAccent));
//            }
//
//        }else{
////            viewHolder.deleteUser.setBackgroundColor(viewHolder.deleteUser.getContext().getColor(R.color.colorPrimaryDark));
//        }
//
//        if (isIcon){
//            holder.iconLetter.setVisibility(View.VISIBLE);
//            holder.imageUser.setVisibility(View.GONE);
//            Drawable buttonDrawable = holder.iconLetter.getBackground();
//            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
//            DrawableCompat.setTint(buttonDrawable, newColor);
//            holder.iconLetter.setBackground(buttonDrawable);
//
//            holder.iconLetter.setText(""+user.name.charAt(0));
//
//            holder.layoutOrientation.setOrientation(LinearLayout.HORIZONTAL);
//
//        }else {
//            holder.imageUser.setVisibility(View.VISIBLE);
//            holder.layoutOrientation.setOrientation(LinearLayout.VERTICAL);
//            holder.iconLetter.setVisibility(View.GONE);
//        }

    }


    @Override
    public int getItemCount() {
        return users.size();
    }
    public int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
    public boolean isColorDark(int color){
        double darkness = 1-(0.299*Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
        if(darkness<0.5){
            return false;
        }else{
            return true;
        }
    }
}