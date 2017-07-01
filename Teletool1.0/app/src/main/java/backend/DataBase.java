package backend;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;



public class DataBase extends SQLiteAssetHelper {

    Context context;

    public DataBase(Context context ){
        super(context,"TeletoolDB",null, 1);
        this.context=context;
    }

}
