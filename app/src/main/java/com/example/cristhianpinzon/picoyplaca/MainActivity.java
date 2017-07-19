package com.example.cristhianpinzon.picoyplaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cristhianpinzon.picoyplaca.Logic.User;
import com.example.cristhianpinzon.picoyplaca.Persistence.DatabaseAccess;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG="MAINACTIVITY";

    private List<User> users;

    private DatabaseAccess databaseAccess;
    private RadioGroup _GroupRb;
    private RadioButton _rbParticular;
    private RadioButton _rbTaxi;
    private Button _btnTerminar;
    private EditText _txtPlaca1,_txtPlaca2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginComponents();
        databaseAccess = new DatabaseAccess(this);
        loadUser();
        for (User user: users) {
            Log.d(TAG,user.toString());
        }
        if (!users.isEmpty()){
            Log.d(TAG,"YA EXISTE USUARIO");
            //ir a la siguiente activity
        }else {
            Log.d(TAG,"NO EXISTE USUARIO");
        }
    }

    private void beginComponents() {
        _rbParticular = (RadioButton) findViewById(R.id.rbParticular);
        _rbTaxi= (RadioButton) findViewById(R.id.rbTaxi);
        _GroupRb = (RadioGroup) findViewById(R.id.groupRb);
        _txtPlaca1 = (EditText) findViewById(R.id.placa1);
        _txtPlaca2 = (EditText) findViewById(R.id.placa2);
        _btnTerminar = (Button) findViewById(R.id.btnTerminar);


        _btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terminar();
            }
        });
    }

    private void terminar() {
        String tipoV = "";
        if (_GroupRb.getCheckedRadioButtonId() == -1)
            Toast.makeText(getApplication(),"Seleccione tipo de auto",Toast.LENGTH_SHORT).show();
        else {
            if (_rbParticular.isChecked())
                tipoV = "P";
            else
                tipoV = "T";

            //Log.d(TAG,"Tipov: " + tipoV);

            String placa = _txtPlaca1.getText().toString() + _txtPlaca2.getText().toString();
            if (placa.length() != 6){
                Toast.makeText(getApplication(),"Ingrese bien los datos de la placa",Toast.LENGTH_SHORT).show();
            }else   {
                newUser(placa,tipoV);
            }



        }
    }

    private void newUser(String placa, String tipoV) {
        //Log.d(TAG,"nuevousuario: placa -> " + placa  +  "tipoV" +tipoV);

        int numplaca = Integer.parseInt(placa.substring(5,6));
        databaseAccess.open();
        databaseAccess.addUser(new User(numplaca,tipoV));
        databaseAccess.close();

        //ir a la siguiente activity

    }


    private void deleteUser() {
        databaseAccess.open();
        databaseAccess.deleteUser();
        databaseAccess.close();
    }

    private void loadUser() {
        databaseAccess.open();
        users = databaseAccess.getUsers();
        databaseAccess.close();
    }
}
