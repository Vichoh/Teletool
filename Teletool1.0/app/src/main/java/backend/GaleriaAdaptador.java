package backend;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.adrian.teletool10.R;

import java.util.ArrayList;

/**
 * Created by Adrian on 09/11/2016.
 */
public class GaleriaAdaptador extends BaseAdapter {

    private Context mContext;
    public ArrayList<Drawable> drawables;

    public GaleriaAdaptador(Context context,ArrayList<Drawable> drawables){
        mContext = context;
        this.drawables = drawables;

    }

    @Override
    public int getCount() {
        return drawables.size();
    }

    @Override
    public Object getItem(int posicion) {
        return posicion;
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    public View getView(int index, View view, ViewGroup viewGroup) {

        ImageView i =new ImageView(mContext);

        //i.setImageResource(imagenIds[index]);
        i.setImageDrawable(drawables.get(index));
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        return i;
    }

}
