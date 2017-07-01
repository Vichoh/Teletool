package backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adrian.teletool10.Principal;
import com.example.adrian.teletool10.R;

/**
 * Created by camil on 16-11-2016.
 */

public class AdaptadorPrincipal extends BaseAdapter {

    private Context context;

    public AdaptadorPrincipal(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return PrincipalNews.Items.length;
    }

    @Override
    public PrincipalNews getItem(int i) {
        return PrincipalNews.Items[i];
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen_institucion);
        TextView nombreCoche = (TextView) view.findViewById(R.id.nombre_institucion);

        final PrincipalNews item = getItem(i);
        Glide.with(imagenCoche.getContext())
                .load(item.getIdDrawablePri())
                .into(imagenCoche);

        nombreCoche.setText(item.getNombre());

        return view;
    }
}
