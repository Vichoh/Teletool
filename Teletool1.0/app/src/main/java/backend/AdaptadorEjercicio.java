package backend;

/**
 * Created by Adrian on 07/11/2016.
 */

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrian.teletool10.R;

import java.util.ArrayList;

/**
 * Created by Adrian on 03/11/2016.
 */

public class AdaptadorEjercicio extends RecyclerView.Adapter<AdaptadorEjercicio.EjercicioViewHolder> implements View.OnClickListener {

    private ArrayList<Ejercicio> datosProyecto;
    private View.OnClickListener listener;



    //-------------------------------------------------------------------------------

    public static class EjercicioViewHolder extends RecyclerView.ViewHolder{
        TextView descripcionCarview;

        ImageView imagenCarView;

        public EjercicioViewHolder(View itemView) {


            super(itemView);

            descripcionCarview = (TextView)itemView.findViewById(R.id.descripcion_carview);

            imagenCarView = (ImageView)itemView.findViewById(R.id.imagen_carview);

        }

        public void bindProyecto(backend.Ejercicio e){
            descripcionCarview.setText(e.getDescripcion());
            imagenCarView.setImageDrawable(Drawable.createFromPath(e.getImagen()));

        }
    }



    //--------------------------------------------------------------


    public AdaptadorEjercicio(ArrayList<Ejercicio> datosProyecto) {
        this.datosProyecto = datosProyecto;
    }



    @Override
    public EjercicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent,false);

        itemView.setOnClickListener(this);

        EjercicioViewHolder pvh = new EjercicioViewHolder(itemView);
        return  pvh;
    }

    @Override
    public void onBindViewHolder(EjercicioViewHolder holder, int position) {
        Ejercicio item = datosProyecto.get(position);
        holder.bindProyecto(item);
    }



    @Override
    public int getItemCount() {
        return datosProyecto.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}
