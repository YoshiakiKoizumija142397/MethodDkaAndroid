package kiyoko.koizumi.MethodDka;

 

import android.app.Activity;

import android.app.AlertDialog;

import android.content.Context;

import android.graphics.Color;

 

import android.os.Bundle;

import android.text.Editable;

import android.text.InputType;

import android.view.Window;

import android.widget.LinearLayout;

import android.widget.EditText;

import android.widget.Button;

 

import android.widget.TextView;

import android.view.View;

import java.util.*;

 

public class MethodDka extends Activity

    implements View.OnClickListener{

    private final static intWC=LinearLayout.LayoutParams.WRAP_CONTENT;

    private final static intFP=LinearLayout.LayoutParams.FILL_PARENT;

    private EditText[] editText = new EditText[1002];

    EditText editText1;

    // 収束したかどうかのフラグ

    boolean bSyuusoku=false;

    private static int n;

    private Button button0,button1;

    double[] sa= new double[1001];

    double[] sr = new double[100000];

    double[] si = new double[100000];

    double sq;

    LinearLayout layout = null;

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);

 

        layout = (LinearLayout)findViewById(R.id.linearLayout1);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);

 

        // EX.3-1:HIGH DEGREE ALGEBRATIC EQUQTIONS

 

        // PRINT "修正 DKA 法"

        //レイアウトの生成（１）

/*

        LinearLayout layout=new LinearLayout(this);

        layout.setBackgroundColor(Color.rgb(255,255,255));

        layout.setOrientation(LinearLayout.VERTICAL);

        setContentView(layout);

*/

 

        //エディットテキストの生成

        editText1=new EditText(this);

        editText1.setText(R.string.maxDegree,EditText.BufferType.NORMAL);

        editText1.setLayoutParams(newLinearLayout.LayoutParams(FP,WC));

 

        editText1.setInputType(InputType.TYPE_CLASS_NUMBER );

        layout.addView(editText1);

 

        //ボタンの生成

        button0=new Button(this);

        button0.setText(R.string.DegreeDecision);

        button0.setTag("0");

        button0.setLayoutParams(newLinearLayout.LayoutParams(WC,WC));

        button0.setOnClickListener(this);

        layout.addView(button0);

 

        button1=new Button(this);

        button1.setText(R.string.Calculationstart);

        button1.setTag("1");

        button1.setLayoutParams(newLinearLayout.LayoutParams(WC,WC));

        button1.setOnClickListener(this);

        layout.addView(button1);

 

       // ArrayList<Double> sa = new ArrayList<Double>();

    }

 

    public void onClick(View view) {

 

      //220 REM HIGH DEGREE ALGEBRATIC EQUATIONS

        int tag=Integer.parseInt((String)view.getTag());

        if (tag==0){

            for(int i=0;i<=n;i++){

                layout.removeView(editText[i]);

            }

            Editable edit1 = editText1.getText();

            String str1= edit1.toString();

            n=Integer.parseInt(str1);

            for(int i=0;i<=n;i++){

                editText[i]=new EditText(this);

                editText[i].setText((n-i)+getString(R.string.nextcoefficient),EditText.BufferType.NORMAL);

                editText[i].setLayoutParams(newLinearLayout.LayoutParams(FP,WC));

                editText[i].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

                layout.addView(editText[i]);

                }

        }

        if(tag==1) {

            for(int i=0;i<=n;i++){

                Editable edit = editText[i].getText();

                String str = edit.toString();

                sa[i]= Double.parseDouble(str);

            }

         if( n==0 ){

                showDialog(this, "",getString(R.string.setzeroth));

            }

    subrutine();

 

    if(bSyuusoku==false){

        showDialog(this,"",getString(R.string.didnotconverge));

    }else{

    for(int i=1;i<=n;i++){

        TextView textView = new TextView(this);

        textView.setText(getString(R.string.Solution)+i+getString(R.string.realnumber)+" "+String.valueOf(sr[i])+" "+

                getString( R.string.Solution)+i+getString(R.string.imaginarynumber)+" "+String.valueOf(si[i]));

        textView.setTextSize(24.0f);

        textView.setTextColor(Color.BLACK);

        textView.setLayoutParams(newLinearLayout.LayoutParams(WC,WC));

        layout.addView(textView);

        }

    }

    }

}




   //ダイアログの表示

     private static void showDialog(Context context,

            String title,String text) {

        AlertDialog.Builder ad=newAlertDialog.Builder(context);

        ad.setTitle(title);

        ad.setMessage(text);

        ad.setPositiveButton("OK",null);

        ad.show();

     }

 

    public void subrutine(){

        double[] sx = new double[10000];

        double[] sy = new double[10000];

        double se=0.0000000000000001;

        int m=100;

        double sp=Math.PI;//3.14159268358979;

        double sw=0.0;

        double sb;

        double sc;

        double st;

        double s5;

 

        for(int i=0;i<= n;i++){

           sa[i]=sa[i]/sa[0];

        }

 

        for(int i=2;i<=n;i++){

            sq=n*Math.pow((Math.abs(sa[i])),(1/i));

            if(sw<sq){sw=sq;}

        }

 

        sb=2*sp/n;

        sc=sp/(2*n);

        for(int j=1;j<=n;j++){

            st=sb*(j-1)+sc;

            sr[j]=sw*Math.cos(st);

            si[j]=sw*Math.sin(st);

        }

 

        // kのループを続けるかどうかのフラグ

        boolean bContinue;

 

        for(int k=1;k<=m;k++){

            bContinue = false;

 

            for(int i=1;i<=n;i++){

                double s1=1.0;

                double s2=0.0;

                double s3=1.0;

                double s4=0.0;

                sb=sr[i];

                sc=si[i];

                for(int j=1;j<=n;j++){

                    s5=s1*sb-s2*sc;

                    s2=s1*sc+s2*sb;

                    s1=s5+sa[j];

                    if(j != i){

                        s5=s3*(sb-sr[j])-s4*(sc-si[j]);

                        s4=s3*(sc-si[j])+s4*(sb-sr[j]);

                        s3=s5;

                    }

/*                     else{

                           sw=s3*s3+s4*s4;

                           sx[i]=(s1*s3+s2*s4)/sw;

                           sy[i]=(s2*s3-s1*s4)/sw;

                           sr[i]=sr[i]-sx[i];

                           si[i]=si[i]-sy[i];

                       }

*/              }

                sw=s3*s3+s4*s4;

                sx[i]=(s1*s3+s2*s4)/sw;

                sy[i]=(s2*s3-s1*s4)/sw;

                sr[i]=sr[i]-sx[i];

                si[i]=si[i]-sy[i];

            }

 

            for(int i=1;i<=n;i++){

                if ((Math.abs(sx[i])>se)){

                    // 条件を満たしたら処理を続ける

                    bContinue = true;

                }

                if ((Math.abs(sy[i])>se)){

                    // 条件を満たしたら処理を続ける

                    bContinue = true;

                }

            }

 

            // フラグが立っていなかったら繰り返しを終える

            if(bContinue == false) {

                bSyuusoku=true;

                showDialog(this, "",getString(R.string.converged));

                return;

            }

        }

        //showDialog(this,"", " 収束しませんでした。");

     bSyuusoku=false;

    }

 

}

 
