package com.example.sisturinfo_pb.ActivitysSecond;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sisturinfo_pb.Adapter.AtrativosAdapter;
import com.example.sisturinfo_pb.R;
import com.example.sisturinfo_pb.Sqlite.HelperSQLAtrativos;

public class ActivityInfAt extends AppCompatActivity {

    private HelperSQLAtrativos helperSQLAtrativos;
    private static ListView l1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_at);


        helperSQLAtrativos = new HelperSQLAtrativos(this);
        helperSQLAtrativos.limparArray();
        helperSQLAtrativos.recoverDataSQLAtrativos();


        Toast.makeText(this, "Quantidade de Atrativos Turisticos: " + helperSQLAtrativos.getReturnList().size(), Toast.LENGTH_SHORT).show();

        l1 = new ListView(ActivityInfAt.this);
        AtrativosAdapter atrativosAdapter = new AtrativosAdapter(this, helperSQLAtrativos.getReturnList());
        if (l1 != null) {
            l1.setAdapter(atrativosAdapter);
            setContentView(l1);

            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final int i = position;
                    /*
                    captura o evento e joga ele em uma Activity do municipio correspondente
                    ;
                     */
                    new AlertDialog.Builder(ActivityInfAt.this).setTitle("Informações Detalhadas").setMessage("Deseja visualizar as informações do atrativos turisticos?").setPositiveButton("sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            listenEvent(i);

                        }
                    }).setNegativeButton("não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ActivityInfAt.this, "Cancelado!", Toast.LENGTH_SHORT).show();

                        }
                    }).show();
                }
            });
        }
    }



    public void listenEvent(int position){
        /*
                            caso sim ! entra uma new intenção com os dados
                            do municipio correspondente;
        */

        if(position==0){
            Intent at = new Intent(ActivityInfAt.this, InfDetailsAt.class);
            at.putExtra("position","ok");
            Log.d("POSITION_At","POSITION_at"+position);
            startActivity(at);
            finish();
        }else{
            Log.d("POSITION","POSITION"+position);
            Intent et = new Intent(ActivityInfAt.this, InfDetailsAt.class);
            et.putExtra("position","nao_ok");
            et.putExtra("detailAtrativo", String.valueOf(position));

            startActivity(et);
            finish();

        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();


    }

    @Override
    protected void onPause() {
        Log.d("Verific At","VERIFIC AT");

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.v("ZUERA POW", "kapsapsk");

        super.onDestroy();
    }
}



