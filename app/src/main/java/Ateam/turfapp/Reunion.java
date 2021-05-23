package Ateam.turfapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


/**
 * Created by Frederic on 23/05/2021.
 */
@Entity(tableName = "reunion", indices = { @Index(value = {"datetime_start"}) })
public class Reunion {
    //VAR
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "datetime_start")
    private String _datetimeStart;

    @ColumnInfo(name = "hippodrome")
    private String _hippodrome;

    @ColumnInfo(name = "numero")
    private String _numero;


    //CONSTRUCTOR
    public Reunion(int id, String datetimeStart, String hippodrome, String numero) {
        this._id = id;
        this._datetimeStart = datetimeStart;
        this._hippodrome = hippodrome;
        this._numero = numero;
    }

    //METHODE

    //SET

    public void setId(int id) {
        this._id = id;
    }

    public void setDatetimeStart(String datetimeStart) {
        this._datetimeStart = datetimeStart;
    }

    public void setHippodrome(String hippodrome) {
        this._hippodrome = hippodrome;
    }

    public void setNumero(String numero) {
        this._numero = numero;
    }

    //GET

    public int getId() {
        return _id;
    }

    public String getDatetimeStart() {
        return _datetimeStart;
    }

    public String getHippodrome() {
        return _hippodrome;
    }

    public String getNumero() {
        return _numero;
    }
}
