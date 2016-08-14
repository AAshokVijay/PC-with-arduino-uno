import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
import controlP5.*; 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Computer_Aided_Lathe extends PApplet {








 int i=0,j=0,k=0,l=0;
ControlP5 cp5;
int[] IMP; int seri=0;

Textlabel Label1,Label2,Label3,Label4,Label5,Label6,Label7,Label8,info;
Textarea details;

Serial myPort;  // Create object from Serial class
int val;      // Data received from the serial port

String inputString = "",ttemp="";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

public void setup() 
{
  
  background(color(50));
  cp5 = new ControlP5(this);
  //  noStroke();
  //labels
        Label1 = cp5.addTextlabel("label1")
                    .setText("WELCOME TO OUR CAL MACHINE")
                    .setPosition(400,10)
                    .setColorValue(0xff4CDAE5)
                    .setFont(createFont("arial",30))
                    ;
      Label2 = cp5.addTextlabel("label2")
                    .setText("CONTROLS")
                    .setPosition(100,100)
                    .setColorValue(0xff0618CE)
                    .setFont(createFont("Georgia",30))
                    ;
      Label3 = cp5.addTextlabel("label3")
                    .setText("DETAILS")
                    .setPosition(550,100)
                    .setColorValue(0xff0618CE)
                    .setFont(createFont("Georgia",30))
                    ;
      Label4 = cp5.addTextlabel("label4")
                    .setText("INFORMATION FROM CAL")
                    .setPosition(900,100)
                    .setColorValue(0xff0618CE)
                    .setFont(createFont("Georgia",30))
                    ;
      Label5 = cp5.addTextlabel("label5")
                    .setText("GUIDED BY: Dr.N.Baskar")
                    .setPosition(950,480)
                    .setColorValue(0xffF5FCFC)
                    .setFont(createFont("Georgia",30))
                    ;
      Label6 = cp5.addTextlabel("label6")
                    .setText("by"+"\nRaja Samuel.A\nVineesh.R\nVenkatesh.R\nVijayaram.A")
                    .setPosition(950,510)
                    .setColorValue(0xffBBCDCE)
                    .setFont(createFont("Georgia",30))
                    ;
     Label7 = cp5.addTextlabel("label8")
                    .setText("MECHANICAL")
                    .setPosition(530,35)
                    .setColorValue(0xffB3BCBC)
                    .setFont(createFont("Georgia",30))
                    ;
    Label8 = cp5.addTextlabel("label9")
                    .setText("SARANATHAN COLLEGE OF ENGINEERING,TRICHY")
                    .setPosition(300,65)
                    .setColorValue(0xffFCFEFF)
                    .setFont(createFont("Georgia",30))
                    ;
    
    //text areas
     details = cp5.addTextarea("txt1")
                  .setPosition(400,150)
                  .setSize(400,300)
                  .setFont(createFont("arial",30))
                  .setLineHeight(30)
                  .setColor(0xff237102)
                  .setColorBackground(0xffB8C4BC)
                  .setColorForeground(color(255,100))
                  ;          
     info = cp5.addTextlabel("txt2")
                  .setPosition(900,150)
                  .setSize(400,300)
                  .setFont(createFont("arial",12))
                  .setLineHeight(12)
                  .setColor(0xffFFFFFF)
                  .setColorBackground(0xffB8C4BC)
                  .setColorForeground(color(255,100))
                  ;          
    
    //buttons
  cp5.addButton("LOAD_DESIGN_DATA")
     .setValue(40)
     .setPosition(100,200)
     .setSize(200,19)
     ;
  
  cp5.addButton("VIEW_DETAILS")
     .setValue(100)
     .setPosition(100,300)
     .setSize(200,19)
     ;
  cp5.addButton("SEND_DATA")
     .setPosition(100,400)
     .setSize(200,19)
     .setValue(0)
     ;
  cp5.addButton("MAKE_PRODUCT")
     .setPosition(100,500)
     .setSize(200,19)
     .setValue(0)
     ;
     //photos
    cp5.addButton("logo")
     .setPosition(200,5)
     .setSize(100,100)
     .setImages(loadImage("logo.png"), loadImage("logo.png"), loadImage("logo.png"))
     .updateSize();
   
    cp5.addButton("cnc")
     .setPosition(350,460)
     .setSize(500,200)
     .setImages(loadImage("cnc.png"), loadImage("cnc.png"), loadImage("cnc.png"))
     .updateSize();
                    
  String portName = Serial.list()[0];
  myPort = new Serial(this, portName, 9600);
}
public void LOAD_DESIGN_DATA(int value)
{
  if(i==0)
  {i=10;}
  else
  {
   try
        {
           IMP=LoadDesign();
           details.setText("design data loaded successfully");
        }
        catch(Exception e)
        {
          details.setText("can't load");
        }
  }
}
public void VIEW_DETAILS(int value)
{
  if(j==0)
  {j=10;}
  else
  {
  StringBuffer temp_t=new StringBuffer("The dimensions are...... ");
            for(int f=0;f<(3+(IMP[1]*3)+(IMP[2]*4));f++)
            {
            temp_t.append(IMP[f]+",");
            }
            details.setText(temp_t.toString()); 
  }
}
public void SEND_DATA(int value)
{
  if(k==0)
  {k=10;}
  else
  {
    try{
  details.setText("The data has been sent successfully");
  delay(1);
   myPort.write("1000"+"\n");
   delay(1);
   for(int f=0;f<(3+(IMP[1]*3)+(IMP[2]*4));f++)
            {
            myPort.write(IMP[f]+"\n");
            delay(1);
            }
     myPort.write("5000"+"\n");
     delay(1);
    }
    catch(Exception e)
    {
      details.setText("can't sent");
    }
  }
}
public void MAKE_PRODUCT(int value)
{
  if(l==0)
  {l=10;}
  else
  {
  details.setText("The Production is going on");
  delay(1);
  myPort.write("7000"+"\n");
//  seri=1;
  }
}

public void serialEvent() {
  while (myPort.available()>0) {
    // get the new byte:
    
    char inChar = (char)myPort.read();
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\n') {
      stringComplete = true;
    }
  }
}

public void draw()
{
  if(seri==0)
  {
  serialEvent();
   if (stringComplete) {
     //   info.setText(inputString);
     info.setText(inputString);
   }
  }
   
}

 public float findStartingPoint(int xoy,int x1, int x2, int k1, int k2, float[][] POINTS) {
       if(POINTS[xoy][k1]<POINTS[xoy][k2])
                     {
                         if(POINTS[xoy][k1]<POINTS[xoy][x1])
                         {
                             if(POINTS[xoy][k1]<POINTS[xoy][x2])
                             {
    //                             System.out.println("starting of boxline is "+POINTS[xoy][k1]); 
                                 return POINTS[xoy][k1];
                             }
                             else
                             {
   //                             System.out.println("starting of boxline is "+POINTS[xoy][x2]);  
                                return POINTS[xoy][x2];
                             }
                         }else
                         {
                             if(POINTS[xoy][x1]<POINTS[xoy][x2])
                             {
  //                               System.out.println("starting of boxline is "+POINTS[xoy][x1]); 
                                  return POINTS[xoy][x1];
                             }
                             else
                             {
 //                               System.out.println("starting of boxline is "+POINTS[xoy][x2]); 
                                 return POINTS[xoy][x2];
                             } 
                         }
                        
                     }
                     else
                     {
                          if(POINTS[xoy][k2]<POINTS[xoy][x1])
                         {
                             if(POINTS[xoy][k2]<POINTS[xoy][x2])
                             {
 //                                System.out.println("starting of boxline is "+POINTS[xoy][k2]); 
                                  return POINTS[xoy][k2];
                             }
                             else
                             {
 //                               System.out.println("starting of boxline is "+POINTS[xoy][x2]);  
                                 return POINTS[xoy][x2];
                             }
                         }else
                         {
                             if(POINTS[xoy][x1]<POINTS[xoy][x2])
                             {
//                                 System.out.println("starting of boxline is "+POINTS[xoy][x1]); 
                                  return POINTS[xoy][x1];
                             }
                             else
                             {
//                                System.out.println("starting of boxline is "+POINTS[xoy][x2]);
                                 return POINTS[xoy][x2];
                             } 
                         }
                     }
}

public float findEndingPoint(int xoy, int x1, int x2, int k1, int k2, float[][] POINTS) {
        if(POINTS[xoy][k1]>POINTS[xoy][k2])
                     {
                         if(POINTS[xoy][k1]>POINTS[xoy][x1])
                         {
                             if(POINTS[xoy][k1]>POINTS[xoy][x2])
                             {
 //                                System.out.println("ending of boxline is "+POINTS[xoy][k1]); 
                                 return POINTS[xoy][k1];
                             }
                             else
                             {
 //                               System.out.println("ending of boxline is "+POINTS[xoy][x2]);  
                                return POINTS[xoy][x2];
                             }
                         }else
                         {
                             if(POINTS[xoy][x1]>POINTS[xoy][x2])
                             {
//                                 System.out.println("ending of boxline is "+POINTS[xoy][x1]); 
                                  return POINTS[xoy][x1];
                             }
                             else
                             {
//                                System.out.println("ending of boxline is "+POINTS[xoy][x2]); 
                                 return POINTS[xoy][x2];
                             } 
                         }
                        
                     }
                     else
                     {
                          if(POINTS[xoy][k2]>POINTS[xoy][x1])
                         {
                             if(POINTS[xoy][k2]>POINTS[xoy][x2])
                             {
//                                  System.out.println("ending of boxline is "+POINTS[xoy][k2]); 
                                  return POINTS[xoy][k2];
                             }
                             else
                             {
//                                 System.out.println("ending of boxline is "+POINTS[xoy][x2]);  
                                 return POINTS[xoy][x2];
                             }
                         }else
                         {
                             if(POINTS[xoy][x1]>POINTS[xoy][x2])
                             {
//                                  System.out.println("ending of boxline is "+POINTS[xoy][x1]); 
                                  return POINTS[xoy][x1];
                             }
                             else
                             {
//                                 System.out.println("ending of boxline is "+POINTS[xoy][x2]);
                                 return POINTS[xoy][x2];
                             } 
                         }
                     }
}

public void generateCodeForBoxlines(int NSX, int[] SLX, float[][] POINTS,StringBuffer code,int[] IMP) {
        int oo=0;
        for(int e=0;e<NSX;e++)
         {
         for(int m=0;m<NSX;m++)
         {   
             int line_one=SLX[e];
             int k1=(line_one*2)-1;
             int k2=(line_one*2)-2;
             int line_two=SLX[m];
             int x1=(line_two*2)-1;
             int x2=(line_two*2)-2;
             int xx=0,yy=1;
             if(line_one==line_two)
             {
                 
             }
             else
             if(POINTS[0][k1]==POINTS[0][x1]&&POINTS[0][k2]==POINTS[0][x2])
             {
           //     System.out.println(line_one+" and "+line_two+" are boxlines");
              
             }
             else
                 if(POINTS[0][k1]==POINTS[0][x2]&&POINTS[0][k2]==POINTS[0][x1])
                 {
                     if(line_one<line_two)
                     {
                   //   System.out.println(line_one+" and "+line_two+" are boxlines"); 
                     //starting point of the boxline
                      float sp=findStartingPoint(xx,x1,x2,k1,k2,POINTS);
                      System.out.println("sp value is "+sp);  
                     
            IMP[oo+3]=(int)sp;
               
                      code.append("sp value is "+sp);
                      code.append(System.lineSeparator());
                      
                      //finding ending point
                       float ep=findEndingPoint(xx,x1,x2,k1,k2,POINTS);
                      System.out.println("ep value is "+ep);
                      
                      IMP[oo+4]=(int)ep;
                      
                      code.append("ep value is "+ep);
                      code.append(System.lineSeparator());
                      
                      //finding out diameter
                       float minip=findStartingPoint(yy,x1,x2,k1,k2,POINTS);
                       float maxp=findEndingPoint(yy,x1,x2,k1,k2,POINTS);
                       float dia=maxp-minip;
                       System.out.println("the diameter is "+dia);
                       
                       IMP[oo+5]=(int)dia;
                       oo=oo+3;
                       
                        code.append("the diameter is "+dia);
                        code.append(System.lineSeparator());
                     }
                 }
             else
                 {
                //   System.out.println(line_one+" and "+line_two+" are not boxlines"); 
                 }
         }
         }
}

public void generateCodeForBoxlinesToTaper(int NTX, int[] TLC, float[][] POINTS,StringBuffer code,int[] IMP,int NSX) {
               int mm=0;
         for(int e=0;e<NTX;e++)
         {
         for(int m=0;m<NTX;m++)
         {   
             int line_one=TLC[e];
             int k1=(line_one*2)-1;
             int k2=(line_one*2)-2;
             int line_two=TLC[m];
             int x1=(line_two*2)-1;
             int x2=(line_two*2)-2;
             int xx=0,yy=1;
             if(line_one==line_two)
             {
                 
             }
             else
             if(POINTS[0][k1]==POINTS[0][x1]&&POINTS[0][k2]==POINTS[0][x2])
             {
           //     System.out.println(line_one+" and "+line_two+" are boxlines");
              
             }
             else
                 if(POINTS[0][k1]==POINTS[0][x2]&&POINTS[0][k2]==POINTS[0][x1])
                 {
                     if(line_one<line_two)
                     {
                   //   System.out.println(line_one+" and "+line_two+" are boxlines"); 
                     //starting point of the boxline
                      float sp=findStartingPoint(xx,x1,x2,k1,k2,POINTS);
                      System.out.println("sp value is "+sp);       
                      
                        code.append("sp value is "+sp);
                        code.append(System.lineSeparator());
                      
                      //finding ending point
                       float ep=findEndingPoint(xx,x1,x2,k1,k2,POINTS);
                      System.out.println("ep value is "+ep);
                      
                        code.append("ep value is "+ep);
                        code.append(System.lineSeparator());
                     
                      float Dia1= findMiniDiainY(sp,x1,x2,k1,k2,POINTS);
                       System.out.println("the diameter d1 is "+Dia1);
                       
                       code.append("the diameter d1 is "+Dia1);
                       code.append(System.lineSeparator());
                       
                      float Dia2= findMiniDiainY(ep,x1,x2,k1,k2,POINTS);
                       System.out.println("the diameter d2 is "+Dia2);
                       
                       code.append("the diameter d2 is "+Dia2);
                       code.append(System.lineSeparator());
                       
                       IMP[(NSX/2)*3+3+mm]=(int)sp;
                       IMP[(NSX/2)*3+4+mm]=(int)ep;
                       IMP[(NSX/2)*3+5+mm]=(int)Dia1;
                       IMP[(NSX/2)*3+6+mm]=(int)Dia2;
                       
                       mm=mm+4;
                     }
                 }
             else
                 {
                //   System.out.println(line_one+" and "+line_two+" are not boxlines"); 
                 }
         }
         }
}
public int[] LoadDesign() throws IOException {
  // TODO Auto-generated method stub
   int LINE_COUNT=0;
       int POINT_COUNT=0;
       int PP_COUNT=0;
       int PPY_COUNT=0;
       int NSX=0;
       int NTX=0;
       int IMP[]=new int[40];
       float[][] POINTS=new float[2][60];
      String[] LD=new String[30];
      String[] PD=new String[60];
      String[] PP=new String[60];
      String[] PPY=new String[60];
    String input="";
    String[] data=loadStrings("my_part.arc");
     StringBuffer in=new StringBuffer("");
     int hq=0;
     while(hq<data.length)
     {
       in.append(data[hq]);
       hq++;
     }
    StringBuffer code = new StringBuffer("");
    
    // details.setText(in.toString());
      
    //line operation
    for(int i=0;i<in.length();i++)
    {
        if(in.charAt(i)=='<'&&in.charAt(i+1)=='l'&&in.charAt(i+2)=='i'&&in.charAt(i+3)=='n'&&in.charAt(i+4)=='e')
        {
            LINE_COUNT++;
           // System.out.println("line found: "+LINE_COUNT);
            for(int j=i;j<in.length();j++)
            {
            if(in.charAt(j)=='>'&&in.charAt(j-1)=='e'&&in.charAt(j-2)=='n'&&in.charAt(j-3)=='i'&&in.charAt(j-4)=='l'&&in.charAt(j-5)=='/')
            {
                StringBuffer temp=new StringBuffer("");
                for(int k=i;k<=j;k++)
                {
                    temp.append(in.charAt(k));
                }
   
                 LD[LINE_COUNT]=temp.toString();
             //   System.out.println("Details of line: "+temp.toString());
                break;
            }
            }
        }
    }
    
    //point operation
    for(int g=0;g<LINE_COUNT;g++)
    {
    for(int i=0;i<LD[g+1].length();i++)
    {
        StringBuffer ld=new StringBuffer(LD[g+1]);
        if(ld.charAt(i)=='<'&&ld.charAt(i+1)=='p'&&ld.charAt(i+2)=='o'&&ld.charAt(i+3)=='i'&&ld.charAt(i+4)=='n'&&ld.charAt(i+5)=='t')
        {
            POINT_COUNT++;
         //   System.out.println("point found: "+POINT_COUNT);
            for(int j=i;j<LD[g+1].length();j++)
            {
            if(ld.charAt(j)=='>'&&ld.charAt(j-1)=='/')
            {
                StringBuffer temp=new StringBuffer("");
                for(int k=i;k<=j;k++)
                {
                    temp.append(ld.charAt(k));
                }
    
                 PD[POINT_COUNT]=temp.toString();
            //    System.out.println("Details of point: "+temp.toString());
                break;
            }
            }
        }
    }
    }
    //saving point to the array
        for(int g=0;g<POINT_COUNT;g++)
    {
        for(int i=0;i<PD[g+1].length();i++)
        {
          StringBuffer pd=new StringBuffer(PD[g+1]);
          
          //for x value
          if(pd.charAt(i)=='x'&&pd.charAt(i+1)=='='&&pd.charAt(i+2)=='"')
          {
              PP_COUNT++;
              for(int j=i+3;j<PD[g+1].length();j++)
              {
                  if(pd.charAt(j)=='"')
                  {
                      StringBuffer temp=new StringBuffer("");
                      for(int k=i+3;k<j;k++)
                     {
                       temp.append(pd.charAt(k));
                     }
                      PP[PP_COUNT]=temp.toString();
                    //  System.out.println("points: X= "+temp.toString()+",");
                      break;
                  }
              }
          }
          
          //for y value
          if(pd.charAt(i)=='y'&&pd.charAt(i+1)=='='&&pd.charAt(i+2)=='"')
          {
               PPY_COUNT++;
              for(int j=i+3;j<PD[g+1].length();j++)
              {
                  if(pd.charAt(j)=='"')
                  {
                       StringBuffer temp=new StringBuffer("");
                      for(int k=i+3;k<j;k++)
                     {
                       temp.append(pd.charAt(k));
                     }
                      PPY[PPY_COUNT]=temp.toString();
                    //  System.out.println("points: Y= "+temp.toString());
                      break;
                  }
              }
          }
                 
        }
    }
                  
                   //for x value
                     for(int y=0;y<PP_COUNT;y++)
                     {
                        POINTS[0][y]=Float.parseFloat(PP[y+1]);
                        POINTS[0][y]=Math.round(POINTS[0][y]);
                     }
                    //for y value
                     for(int y=0;y<PP_COUNT;y++)
                     {
                         POINTS[1][y]=Float.parseFloat(PPY[y+1]);
                          POINTS[1][y]=Math.round(POINTS[1][y]);
                     }
                    
                        for(int x=0;x<PPY_COUNT;x++)
                        {
                      //      System.out.println(POINTS[0][x]+","+POINTS[1][x]);
                        }
                     
                     
                     //finding minimum value
                        //x value
                        float mini=POINTS[0][0];
                        for(int k=1;k<PP_COUNT;k++)
                        {
                            if(POINTS[0][k]<mini)
                            {
                                mini=POINTS[0][k];
                            }
                        }
                      //finding maximum value
                        //x value
                        float max=POINTS[0][0];
                        for(int k=1;k>PP_COUNT;k++)
                        {
                            if(POINTS[0][k]>max)
                            {
                                max=POINTS[0][k];
                            }
                        }
                        
                       mini=Math.round(mini);
                       max=Math.round(max);
                        IMP[0]=(int)max-(int)mini;
                        
                 //     System.out.println("minimum value in x is "+mini); 
                      //y value
                       float miniy=POINTS[0][0];
                        for(int k=1;k<PPY_COUNT;k++)
                        {
                            if(POINTS[1][k]<miniy)
                            {
                                miniy=POINTS[1][k];
                            }
                        }
                 //     System.out.println("minimum value in y is "+miniy);
                      
                      //getting currect axis to points
                      
                      for(int h=0;h<POINT_COUNT;h++)
                      {
                          POINTS[0][h]=POINTS[0][h]-mini;
                          POINTS[1][h]=POINTS[1][h]-miniy;
                      }
                      
                       //points to line Type
                        int[] SLX =new int[30],SLY=new int[30],TLC=new int[30];
                        int sss=0;int kkk=0;int yyy=0;
                        for(int i=0;i<POINT_COUNT;i=i+2)
                        {
                            if(POINTS[1][i]==POINTS[1][i+1])
                            {
                                SLX[sss]=(i/2)+1;
                           //     System.out.println("line "+SLX[sss]+" is straight line in x direction");
                                sss++;NSX++;
                            }
                            else 
                                if(POINTS[0][i]==POINTS[0][i+1])
                            {
                                SLY[kkk]=(i/2)+1;
                           //     System.out.println("line "+SLY[kkk]+" is straight line in y direction");
                                kkk++;
                            }
                            else
                            {
                                TLC[yyy]=(i/2)+1;
                           //     System.out.println("line "+TLC[yyy]+" is a Taper line");
                                yyy++;NTX++;
                            }
                        }
                        
                     //finding box line
                       //for straight line in x
                        generateCodeForBoxlines(NSX,SLX,POINTS,code,IMP);
                      //for  taper line in x
                        generateCodeForBoxlinesToTaper(NTX,TLC,POINTS,code,IMP,NSX);
        
                
                     //writing external file
                    //  StringBuffer result=new StringBuffer("");
                    //  for(int x=0;x<PPY_COUNT;x++)
                    //    {
                    //      result.append(POINTS[0][x]+","+POINTS[1][x]);
                    //      result.append(System.lineSeparator());
                    //    }
                      // System.out.println(result.toString());
                    //   FilesUtil.writeToTextFile("CODE.txt",code.toString());
                       IMP[1]=NSX/2;
                       IMP[2]=NTX/2;
                       
                       //finding length
                       int leng=0;
                       for(int p=0;p<IMP[1]*3;p=p+3)
                       {
                         leng=(IMP[p+4]-IMP[p+3])+leng;
                       }
                       for(int u=0;u<IMP[2]*4;u=u+4)
                       {
                         leng=(IMP[u+(IMP[1]*3)+4]-IMP[u+(IMP[1]*3)+3])+leng;
                       }
                       IMP[0]=leng;
                       
                       
                       for(int r=0;r<(3+(IMP[1]*3)+(IMP[2]*4));r++)
                       {
                         System.out.println(IMP[r]);
                       }
                       
  return IMP;
}    
public float findMiniDiainY(float sp, int x1, int x2, int k1, int k2, float[][] POINTS) {
if(POINTS[0][x1]==sp&&POINTS[0][x2]==sp)
{
if(POINTS[1][x1]>POINTS[1][x2])
{
return POINTS[1][x1]-POINTS[1][x2];
}
else
{
  return POINTS[1][x2]-POINTS[1][x1];
}
}
else
if(POINTS[0][x1]==sp&&POINTS[0][k1]==sp)
{
if(POINTS[1][x1]>POINTS[1][k1])
{
return POINTS[1][x1]-POINTS[1][k1];
}
else
{
  return POINTS[1][k1]-POINTS[1][x1];
}
}
else
if(POINTS[0][x1]==sp&&POINTS[0][k2]==sp)
{
if(POINTS[1][x1]>POINTS[1][k2])
{
return POINTS[1][x1]-POINTS[1][k2];
}
else
{
  return POINTS[1][k2]-POINTS[1][x1];
}
}
else
if(POINTS[0][x2]==sp&&POINTS[0][k1]==sp)
{
if(POINTS[1][x2]>POINTS[1][k1])
{
return POINTS[1][x2]-POINTS[1][k1];
}
else
{
  return POINTS[1][k1]-POINTS[1][x2];
}
}
else
if(POINTS[0][x2]==sp&&POINTS[0][k2]==sp)
{
if(POINTS[1][x2]>POINTS[1][k2])
{
return POINTS[1][x2]-POINTS[1][k2];
}
else
{
  return POINTS[1][k2]-POINTS[1][x2];
}
}
else
if(POINTS[0][k1]==sp&&POINTS[0][k2]==sp)
{
if(POINTS[1][k1]>POINTS[1][k2])
{
return POINTS[1][k1]-POINTS[1][k2];
}
else
{
  return POINTS[1][k2]-POINTS[1][k1];
}
}
return 0;
}
  public void settings() {  size(1350,700,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Computer_Aided_Lathe" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
