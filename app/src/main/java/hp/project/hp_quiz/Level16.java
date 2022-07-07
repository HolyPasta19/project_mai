package hp.project.hp_quiz;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level16 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level3);



        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);


        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);


//Диалоговое окно
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //preview img
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewamg);
        previewimg.setImageResource(R.drawable.previewimgthree);
        //больше не превью имг

        //текст
        TextView textdescription = dialog.findViewById(R.id.text_description);
        textdescription.setText(R.string.levelthree);
        //текст

        TextView btnclose = (TextView)dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level16.this,GameLevels.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                }
                dialog.dismiss();
            }
        });
        Button btncontinue = (Button)dialog.findViewById(R.id.button_continue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //__________________________________________________
        //Диалоговое окно finish


        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.text_description_end);
        textdescriptionEnd.setText(R.string.levelthree_end);

        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.button_close);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level16.this,GameLevels.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.button_continue);
        //buttoncontinue
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =new Intent(Level16.this, Level16.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e){
                }

                dialog.dismiss();
            }
        });


        //Диалоговое окно finish запривачено
//_______________________________________________


        Button btn_back =(Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level16.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
//Диалоговое окно запривачено
        //точечки
        final int[] progress ={
                R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,
                R.id.point6,R.id.point7,R.id.point8,R.id.point9,R.id.point10,
                R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20
        };
        //точечки закончились


        final Animation a = AnimationUtils.loadAnimation(Level16.this,R.anim.alpha);




        //картинки с текстом
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images3[numLeft]);
        text_left.setText(array.texts3[numLeft]);

        numRight = random.nextInt(10);
        while (numLeft==numRight){
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images3[numRight]);
        text_right.setText(array.texts3[numRight]);
        //больше не картинки с текстом



        //левая картинка
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }
                    else {
                        img_left.setImageResource(R.drawable.img_false);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP){

                    if(numLeft>numRight){
                        if(count<20){
                            count ++;
                        }
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    else{
                        if(count>0){
                            if(count==1){
                                count = 0;
                            }
                            else if(count==0){
                                count = 0;
                            }
                            else {
                                count = count - 2;
                            }
                        }
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }


                    }
                    if(count==20){
                        //EXIT
                        //прогресс
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level1",1);
                        if(level > 16){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",17);
                            editor.commit();
                        }
                        //прогресс end
                        dialogEnd.show();
                    }
                    else {
                        //картинки с текстом
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images3[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);

                        numRight = random.nextInt(10);
                        while (numLeft==numRight){
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images3[numRight]);
                        img_right.setAnimation(a);
                        text_right.setText(array.texts3[numRight]);
                        //больше не картинки с текстом

                        img_right.setEnabled(true);


                    }

                }

                return true;
            }
        });
        //больше не  левая картинка


        //правая картинка
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if (numLeft>numRight){
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    else {
                        img_right.setImageResource(R.drawable.img_true);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP){

                    if(numLeft<numRight){
                        if(count<20){
                            count ++;
                        }
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    else{
                        if(count>0){
                            if(count==1){
                                count = 0;
                            }
                            else if(count==0){
                                count = 0;
                            }
                            else {
                                count = count - 2;
                            }
                        }
                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }


                    }
                    if(count==20){
                        //EXIT
                        //прогресс
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level1",1);
                        if(level > 16){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",17);
                            editor.commit();
                        }
                        //прогресс end
                        dialogEnd.show();
                    }
                    else {
                        //картинки с текстом
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images3[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);

                        numRight = random.nextInt(10);
                        while (numLeft==numRight){
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images3[numRight]);
                        img_right.setAnimation(a);
                        text_right.setText(array.texts3[numRight]);
                        //больше не картинки с текстом

                        img_left.setEnabled(true);
                    }

                }

                return true;
            }
        });
        //больше не правая картинка




//системная кнопка
    }
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level16.this,GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }

    }
    //системная кнопка сдохла
}
