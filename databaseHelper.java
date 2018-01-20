package tk.snap52.snap_it;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 1/20/2018.
 */

public class databaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PASS="pass";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts(id integer primary key not null ,"+"name text not null , email text not null , pass text not null);";

    public databaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    db.execSQL(TABLE_CREATE);
    this.db=db;
    }
    public void insertcontact(Contact c){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String query ="select * from contacts";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();


        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASS,c.getPass());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public String searchpass(String email){
        db=this.getReadableDatabase();
    String query ="select email, pass from"+TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);
        String a,b;
        b="not found";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                if(a.equals(email)){
                    b=cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    String query ="DROP TABLE IF EXISTS"+TABLE_NAME;
    db.execSQL(query);
    this.onCreate(db);

    }
}
