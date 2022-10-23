package com.eceozbay.s.global_jeoit_ondilasyonu;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    TextView textView;

    double fi;
    double lamda;

    double undu = 0.0;
    double Zeta2N = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);




    }

    public void hesap(View view) {

        if (editText1.getText().toString().matches("") || editText2.getText().toString().matches("")) {
        } else {


            fi = Math.toRadians(Double.parseDouble(editText1.getText().toString()));
            lamda = Math.toRadians(Double.parseDouble(editText2.getText().toString()));

            double c = 6399593.6259;
            double e12 = 0.006739496775;
            double e2 = 0.006694380023;
            double N = c / Math.sqrt(1 + e12 * Math.pow(Math.cos(fi), 2));
            double x = (N) * Math.cos(fi) * Math.cos(lamda);
            double y = (N) * Math.cos(fi) * Math.sin(lamda);
            double z = ((1 - e2) * N) * Math.sin(fi);
            double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
            double L = Math.toDegrees(Math.atan(y / x));
            double V = Math.toDegrees(Math.atan(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / z));
            double RR = 6378137.0000;
            double GM = 3986004.418E8;
            double f1 = .0;
            double f2 = .0;
            double f3 = .0;
            double f4 = .0;
            double f5 = .0;
            double gamae = 9.7803267715;
            double kk = 1.931851353E-3;
            double gama0 = gamae * (1.0 + kk * Math.sin(fi) * Math.sin(fi)) / Math.sqrt(1.0 - e2 * Math.sin(fi) * Math.sin(fi));
            double CN[] = new double[70000];
            double zeta;

/////////////////////////////////////////////////////////////////////////////////////
//zonal coefficients of the normal gravity C20, C40, C60 ... (GRS80)
            CN[2 * 3 / 2] = -4.84166854896e-04;
            CN[4 * 5 / 2] = 7.90304072886e-07;
            CN[6 * 7 / 2] = -1.68725117568e-09;
            CN[8 * 9 / 2] = 3.46053239804e-12;
            CN[10 * 11 / 2] = -2.65006217824e-15;
            CN[12 * 13 / 2] = -4.10788001539e-17;
            CN[14 * 15 / 2] = 4.47176179027e-19;
            CN[16 * 17 / 2] = -3.46361902624e-21;
            CN[18 * 19 / 2] = 2.41145224800e-23;
            CN[20 * 21 / 2] = -1.60243073602e-25;

            //    File file = new File("C:\\Users\\Vatan\\workspace\\bitirme\\egm360.txt"); // buraya okuyacaÄŸÄ±nÄ±z metin dosyasÄ±nÄ±n konumunu girin.

            ArrayList<String> textParts = new ArrayList<>(2);
            ArrayList<Double> cValues = new ArrayList<>();
            ArrayList<Double> sValues = new ArrayList<>();

            try {

                Resources re = getResources();
                InputStream is = re.openRawResource(R.raw.egm1);

                Scanner sc = new Scanner(is);

                String text;

                int counter = 0;
                int a;


                while (sc.hasNextLine()) {

                    text = sc.nextLine();

                    for (a = 0; a < text.length(); a++) {
                        if (text.charAt(a) != ' ')
                            counter++;
                        else {
                            if (counter > 0) {
                                textParts.add(text.substring(a - counter, a));
                                counter = 0;
                                //System.out.println("Textparts="+textParts);
                            }
                        }
                    }

                    if (counter > 0) {
                        textParts.add(text.substring(a - counter, a));
                        counter = 0;
                    }

                    if (!textParts.isEmpty()) {

                        cValues.add(Double.parseDouble(textParts.get(0))); // 3.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'cValues' adlÄ± ArrayList'e atÄ±yoruz.
                        sValues.add(Double.parseDouble(textParts.get(1))); // 4.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'sValues' adlÄ± ArrayList'e atÄ±yoruz.

                    }

                    textParts.clear();
                }

                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                Resources re = getResources();
                InputStream is = re.openRawResource(R.raw.egm2);

                Scanner sc = new Scanner(is);

                String text;

                int counter = 0;
                int a;


                while (sc.hasNextLine()) {

                    text = sc.nextLine();

                    for (a = 0; a < text.length(); a++) {
                        if (text.charAt(a) != ' ')
                            counter++;
                        else {
                            if (counter > 0) {
                                textParts.add(text.substring(a - counter, a));
                                counter = 0;
                                //System.out.println("Textparts="+textParts);
                            }
                        }
                    }

                    if (counter > 0) {
                        textParts.add(text.substring(a - counter, a));
                        counter = 0;
                    }

                    if (!textParts.isEmpty()) {

                        cValues.add(Double.parseDouble(textParts.get(0))); // 3.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'cValues' adlÄ± ArrayList'e atÄ±yoruz.
                        sValues.add(Double.parseDouble(textParts.get(1))); // 4.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'sValues' adlÄ± ArrayList'e atÄ±yoruz.

                    }

                    textParts.clear();
                }

                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // starting legendre functions...

            double t = Math.cos(Math.toRadians(V));
            double tt = Math.sin(Math.toRadians(V));
            double[] P = new double[cValues.size()];

            P[0] = 1.0;
            P[1] = Math.sqrt(3.0) * t;
            P[2] = Math.sqrt(3.0) * tt;

            int n = 0;
            int i = 0;
            int j = 0;
            int imax = 0;
            double Ynm = 0.0;

            for (n = 2; n <= 360; n++) {
                i = n * (n + 1) / 2 + n; // index for Pn,n
                j = i - n - 1;       // index for Pn-1,n-1

                f1 = Math.sqrt((2.0 * n + 1) / 2 / n);
                f2 = Math.sqrt(2.0 * n + 1);
                // diagonal elements
                P[i] = f1 * tt * P[j];
                // subdiagonal elements
                P[i - 1] = f2 * t * P[j];
            }
            n = 2;
            int m = 0;
            i = 2;
            imax = cValues.size();
            while (++i < imax - 2) {
                if (m == n - 1) {
                    m = 0;
                    n++;
                    i++;
                } else {
                    j = i - n;    // index for Pn-1,m
                    int k = i - 2 * n + 1;// index for Pn-2,m
                    f3 = Math.sqrt((2.0 * n + 1) / (n - m) / (n + m));
                    f4 = Math.sqrt(2.0 * n - 1);
                    f5 = Math.sqrt((n - m - 1.0) * (n + m - 1.0) / (2.0 * n - 3));
                    // remaining elements
                    P[i] = f3 * (f4 * t * P[j] - f5 * P[k]);
                    m++;
                }

            }

            // finish legendre functions ...

            //   File file1 = new File("C:\\Users\\Vatan\\workspace\\bitirme\\zeta2n.txt");

            ArrayList<String> textParts1 = new ArrayList<>(2);
            ArrayList<Double> HC = new ArrayList<>();
            ArrayList<Double> HS = new ArrayList<>();

            try {
                Resources res = getResources();
                InputStream iss = res.openRawResource(R.raw.zeta1);

                Scanner scan1 = new Scanner(iss);

                String text;

                int counter = 0;
                int a;


                while (scan1.hasNextLine()) {
                    text = scan1.nextLine();

                    for (a = 0; a < text.length(); a++) {
                        if (text.charAt(a) != ' ')
                            counter++;
                        else {
                            if (counter > 0) {
                                textParts1.add(text.substring(a - counter, a));
                                counter = 0;

                            }
                        }
                    }

                    if (counter > 0) {
                        textParts1.add(text.substring(a - counter, a));
                        counter = 0;
                    }

                    if (!textParts1.isEmpty()) {

                        HC.add(Double.parseDouble(textParts1.get(0))); // 3.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'cValues' adlÄ± ArrayList'e atÄ±yoruz.
                        HS.add(Double.parseDouble(textParts1.get(1))); // 4.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'sValues' adlÄ± ArrayList'e atÄ±yoruz.

                    }

                    textParts1.clear();
                }

                scan1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Resources res = getResources();
                InputStream iss = res.openRawResource(R.raw.zeta2);

                Scanner scan1 = new Scanner(iss);

                String text;

                int counter = 0;
                int a;


                while (scan1.hasNextLine()) {
                    text = scan1.nextLine();

                    for (a = 0; a < text.length(); a++) {
                        if (text.charAt(a) != ' ')
                            counter++;
                        else {
                            if (counter > 0) {
                                textParts1.add(text.substring(a - counter, a));
                                counter = 0;

                            }
                        }
                    }

                    if (counter > 0) {
                        textParts1.add(text.substring(a - counter, a));
                        counter = 0;
                    }

                    if (!textParts1.isEmpty()) {

                        HC.add(Double.parseDouble(textParts1.get(0))); // 3.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'cValues' adlÄ± ArrayList'e atÄ±yoruz.
                        HS.add(Double.parseDouble(textParts1.get(1))); // 4.sutundaki sayÄ±larÄ± sÄ±rasÄ± ile(dÃ¶ngÃ¼nÃ¼n her bir adÄ±mÄ±nda) 'sValues' adlÄ± ArrayList'e atÄ±yoruz.

                    }

                    textParts1.clear();
                }

                scan1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // System.out.println("HC : "+HS.get(4));

            i = 3;
            n = 2;
            m = 0;
            double Rr = RR / r;
            while (i < imax) {
                Ynm = Ynm + Math.pow(Rr, n + 1) * ((cValues.get(i) - CN[i]) * Math.cos(m * lamda) + sValues.get(i) * Math.sin(m * lamda)) * P[i];
                Zeta2N = Zeta2N + (HC.get(i) * Math.cos(m * lamda) + HS.get(i) * Math.sin(m * lamda)) * P[i];
                if (m == n) {
                    m = 0;
                    n++;
                    i++;
                } else {
                    m++;
                    i++;
                }
            }

            zeta = GM / r / gama0 * Ynm;
            undu = zeta + Zeta2N;

            //   System.out.println("zeta : " + zeta);
            //   System.out.println("N : " + undu);

            textView = findViewById(R.id.textView);
            // for(int q=0;q<cValues.size();q++){
            //DecimalFormat df=new DecimalFormat("#.####");
            // String dx=df.format(undu);
            //undu=Double.valueOf(dx);

          /*  DecimalFormat df=new DecimalFormat("#.####");
            String dx=df.format(undu);
            undu=Double.valueOf(dx); */

            String sss="a"+undu;
            String aaa=sss.substring(1,8);

            textView.setText("N = " + aaa+" m");


        }
    }
}




