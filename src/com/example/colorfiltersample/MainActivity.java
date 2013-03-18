package com.example.colorfiltersample;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.BitmapFactory.Options;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView imageView = (ImageView)findViewById(R.id.imageView1);
        imageView.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                draw();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void draw(){
        ColorMatrixColorFilter colorMatrix = new ColorMatrixColorFilter(generateMatrix());
        
        String target_path = "/sdcard/Download/mont_saint_michel.jpg";
        
        // API Level 11 or higher
        // Options options = new Options();
        // options.inMutable = true;
        // Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lenna , options);
        
        Bitmap origin = BitmapFactory.decodeResource(getResources(), R.drawable.lenna);
        Bitmap bitmap = origin.copy(Config.ARGB_8888, true);
        
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        
        Canvas canvas = new Canvas(bitmap);
        
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(colorMatrix);
        canvas.drawBitmap(bitmap,0,0, paint);
        ImageView imageView = (ImageView)findViewById(R.id.imageView1);
        imageView.setImageBitmap(bitmap);
    }
    
    public float[] generateMatrix(){
        float[] fa = new float[]{
                f(R.id.editText1) , f(R.id.editText2) , f(R.id.editText3) , f(R.id.editText4) , f(R.id.editText5),
                f(R.id.editText6) , f(R.id.editText7) , f(R.id.editText8) , f(R.id.editText9) , f(R.id.editText10),
                f(R.id.editText11) , f(R.id.editText12) , f(R.id.editText13) , f(R.id.editText14) , f(R.id.editText15),
                f(R.id.editText16) , f(R.id.editText17) , f(R.id.editText18) , f(R.id.editText19) , f(R.id.editText20)
        };
        return fa;
    }
    
    private float f(int id){
        return getMatrixElement(id);
    }
    
    public float getMatrixElement(int id){
        EditText text = (EditText)findViewById(id);
        String f = text.getText().toString();
        float fl = 0f;
        if(f != null && !f.trim().equals("")){
            fl = new Float(f).floatValue();
        }
        return fl;
    }

}
