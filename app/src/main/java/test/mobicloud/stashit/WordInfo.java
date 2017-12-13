package test.mobicloud.stashit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class WordInfo extends AppCompatActivity {
    String word1;
    String[] positive =  {"good","nice","greate","super","well","make"};
    String[] negative = {"bad","notyet","worst","not","you"};
    String mainString = "People think that you have to do something huge good, like go to" +
            " Africa and build a school, but you can make a small change " +
            "in a day. If you change Wednesday, then you change Thursday. Pretty soon it's a week," +
            " then a month, then a year. It's bite-size, as opposed to" +
            " feeling like you have to turn your life inside out to make changes ,Good nice things.";
    ArrayList<String> mylist;
    SpannableString ss3,ss1,ss2;
    // checking github sample information.....
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_info);

        TextView textView = (TextView) findViewById(R.id.textinfo);
        TextView textView1 = (TextView) findViewById(R.id.textinfo1);
        String s="Hello World";

        mylist = new ArrayList<String>();


//        String subString = "to";
//
//        if(mainString.contains(subString)) {
//            int startIndex = mainString.indexOf(subString);
//            int endIndex = startIndex + subString.length();
//            SpannableString spannableString = new SpannableString(mainString);
//            spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#ff0000")), startIndex, endIndex,
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            textView.setText(spannableString);
//        }

//        String str = "font roboto regular this is kishore";
//
//        String a = "";
//
//
//
        for (String word : mainString.split(" ")){
            System.out.println(word);
            mylist.add(word);
//             ss=  new SpannableString(word);
//            ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, 0);
////            String kkkk = ""+ss;
//            Log.i("dd",""+ss);
////           textView.append( ss );

            }
//        String str = TextUtils.join("\t", mylist);
//        textView.setText(str);
        Log.i("size",""+mylist.size());
        int d = mylist.size();
            for (int i=0; i<d;i++){
                String kdd= mylist.get(i).toString();

                for (String pos : positive){
                    String dd = pos;
                    if (kdd.equals(dd)){
                        int kkn = dd.length();
                        ss2=  new SpannableString(kdd);
                        ss2.setSpan(new ForegroundColorSpan(Color.parseColor("#0A26F3")), 0,kkn, 0);
                        textView.append(ss2);
                    }
                }

                for (String pos1 : negative){
                    String dd1 = pos1;
                    if (kdd.equals(dd1)){
                        int kkn1 = dd1.length();
                        ss2=  new SpannableString(kdd);
                        ss2.setSpan(new ForegroundColorSpan(Color.RED), 0,kkn1, 0);
                        textView.append(ss2);
                    }
                }

                textView.append(" "+kdd+" ");


                if (kdd.equals("you")){
                    String le = "you";
                    int len = le.length();

                    ss1=  new SpannableString(kdd);
                    ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, len, 0);
                    textView1.append(ss1);
                }else if (kdd.equals("make")) {
                    String le1 = "make";
                    int len1 = le1.length();
                    ss1=  new SpannableString(kdd);
                    ss1.setSpan(new ForegroundColorSpan(Color.parseColor("#0A26F3")), 0,len1, 0);
                    textView1.append(ss1);
                }else
                 {
                    textView1.append(" "+kdd+" ");
                }


                //checking from arraylist


            }

        }

//        textView.setText(word1);
//        String[] strArray = str.split(" ");
//        StringBuilder builder = new StringBuilder();
//        for (String s : strArray) {
//            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
//            builder.append(cap + " ");
//            Log.i("kkk",cap+"\n"+builder.append(cap+""));
//        }


}
