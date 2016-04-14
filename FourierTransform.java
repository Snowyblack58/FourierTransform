import java.io.*;
import javax.sound.sampled.*;


public class FourierTransform {
   private static double PI2 = Math.PI*2;
   private static double PIo8 = Math.PI/8;
   private static double invPI = 1/Math.PI;
   private static double[] Y, KC, KS, FC, FS;
   private static byte[] buffer;

   public static void main(String[] args){
      try {
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SummerShort.wav"));
         System.out.println(audioInputStream.getFormat());
         AudioFormat format = audioInputStream.getFormat();
         System.out.println(audioInputStream.available());
         buffer = new byte[audioInputStream.available()];
         audioInputStream.read(buffer);
         System.out.println("blarg");
      } catch(Exception e){
         
      }
      
    //   byte[] bufferhalf = new byte[buffer.length / 2];
//       for(int cnt = 0; cnt < buffer.length; cnt += 2){
//          bufferhalf[cnt / 2] = buffer[cnt];
//       }

      double[] buffer2 = new double[buffer.length / 2];
      for(int cnt = 0; cnt < buffer.length; cnt += 2){
         buffer2[cnt / 2] = buffer[cnt];
      }
      
      double[] averages = new double[buffer2.length / 128];
      for(int cnt = 0; cnt < averages.length * 128; cnt += 128){
         int total = 0;
         for(int bcnt = cnt; bcnt < cnt + 128; bcnt++){
            total += buffer2[bcnt];
         }
         averages[cnt / 128] = total / 128.0;
      }
      
      averages = new double[]{0, 1.68294, 1.818594, 0.28224, -1.513, -1.917, -0.5588, 1.313};
      
      //dft
      int SAMPLES = averages.length;
      Y = averages;
      FC = new double[16];
      FS = new double[16];
      for(int h1 = 0; h1 < 16; h1++){
         for(int h2 = 0; h2 < SAMPLES; h2++){
            FC[h1] += Y[h2]*Math.cos(h1*h2*Math.PI*2/SAMPLES);
            FS[h1] += Y[h2]*Math.sin(h1*h2*Math.PI*2/SAMPLES);
         }
         FC[h1] /= SAMPLES;
         FS[h1] /= SAMPLES;
      }
      
      System.out.println("---");
      
      System.out.println(" h |   Fcos  |   Fsin  ");
      System.out.println("-----------------------");
      for(int cnt = 0; cnt < 16; cnt++){
         System.out.println(
            String.format("%02d", cnt) +
            " | " +
            String.format("%+.4f", FC[cnt]) +
            " | " +
            String.format("%+.4f", FS[cnt]));
      }
      
      String equation = "";
      equation += FC[0] + " + ";
      for(int cnt = 1; cnt < 16; cnt++){
         double coef = 0.0;
         if(cnt == 8){
            coef = Math.round(FC[cnt]/cnt/cnt*1000)/1000.0;
         } else {
            coef = Math.round(2*FC[cnt]/cnt/cnt*1000)/1000.0;
         }
         equation += coef + "*cos(" + cnt + "*x) + ";
         equation += coef + "*sin(" + cnt + "*x) + ";
//          if(Math.round(2*FC[cnt]*1000)/1000.0 != 0){
//             equation += Math.round(2*FC[cnt]/cnt/cnt*1000)/1000.0 + "*cos(" + cnt + "*x) + ";
//          }
//          if(Math.round(2*FS[cnt]*1000)/1000.0 != 0){
//             equation += Math.round(2*FS[cnt]/cnt/cnt*1000)/1000.0 + "*sin(" + cnt + "*x) + ";
//          }
      }
      System.out.println(equation);
      System.out.println("im smart, totally... q.q");
   }
   
   private static double evaluateEquationAt(double x){
      double evaluate = FC[0];
      for(int cnt = 1; cnt < 16; cnt++){
         evaluate += 2*FC[cnt] * Math.cos(cnt*x);
         evaluate += 2*FS[cnt] * Math.sin(cnt*x);
      }
      return evaluate;
   }
   
   private static double findMaximumValue(){
      return 0.0;
   }
}