package com.example.john.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button btn1;
    private String Carrera, facultad, NomGit, NomFace, Correo, Telefono;
    private CircleImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.share);
        btn1 = findViewById(R.id.shareimage);
        image_view = findViewById(R.id.image_1);

        Carrera = getResources().getString(R.string.ing_john_vladimir_linares_de_paz);
        facultad = getResources().getString(R.string.ingenieria_en_sistemas);
        NomGit = getResources().getString(R.string.john_vladimir);
        NomFace = getResources().getString(R.string.john_linares);
        Correo = getResources().getString(R.string._00005016_uca_edu_sv);
        Telefono = getResources().getString(R.string._75809621);

//boton para compartir texto
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Nombre: " + Carrera + "\n" + "Facultad: " + facultad + "\n" + "Cuenta de GitHub: " + NomGit + "\n" + "Cuenta de Facebook: " +
                        NomFace + "\n" + "Correo: " + Correo + "\n" + "Telefono: " + Telefono);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
//boton para compartir imagen
        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetWorldReadable")
            @Override
            public void onClick(View view) {
                image_view.buildDrawingCache();
                Bitmap bit_image = image_view.getDrawingCache();
                try {
                    File file = new File(image_view.getContext().getCacheDir(), bit_image + ".png");
                    FileOutputStream fOut = null;
                    fOut = new FileOutputStream(file);
                    bit_image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent sendIntent1 = new Intent();
                    sendIntent1.setAction(Intent.ACTION_SEND);
                    sendIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sendIntent1.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    sendIntent1.setType("image/png");
                    startActivity(sendIntent1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}