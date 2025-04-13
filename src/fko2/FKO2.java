/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fko2;
 import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
/**
 *
 * @author Alvar
 */
public class FKO2 {
  //*************Globales*************************   
   int Re_Name_Counter = 0;
   int Good_Comparation = 0;
   int  Bad_Comparation = 0;
   String Path01="E:\\Descargas\\Anime-Dowload";
   File Download = new File(Path01);
      
   String Path02="E:\\Descargas\\Anime";
   File Location = new File(Path02);
   
   String SubPath="";
   File SubLocat;
   
   String OrigenPath="",EndPath="";
   Path Origen;
   Path Destino;
   
   String TempName= "";
   
   public static FKO2 ND = new FKO2();
   
  //*************Fin Globales************************ 
   
   
   
    
    public static void main(String[] args) {
       int Status= 1;
       do{
        ND.Get_Down_Data();
        System.out.println("Incompatible: " + ND.Bad_Comparation);
        System.out.println("Compatibles: "  + ND.Good_Comparation);
        System.out.println("Renombrados: "  + ND.Re_Name_Counter);
        System.out.println("Do you Wanna Continue Press 1 if not Press 2: \n");
        Scanner teclado = new Scanner(System.in);
        Status = Integer.parseInt(teclado.nextLine());        
       }while(Status == 1);
    }
    
    
    public class MediaNames{
        String MCode;
        String MDiv;
        int Chap;
        String Exten;
        
            MediaNames(/*int MCode,String MDiv,String MChap,String Exten*/){
                this.MCode = "";// MCode;
                this.MDiv = "_";//MDiv;
                this.Chap = 00;//MChap;
                this.Exten = "";//Exten;
            }
    }
    
     public class FilesNames{
        String FDiv01;
        String FCode;
        String FDiv02;
        String FName; 
        
            FilesNames(){
            this.FDiv01 = "(";
            this.FCode = "";
            this.FDiv02 = ")";
            this.FName = "";
            }
    }
     
                    
    
     String SToF ="(" , EToF=".mp4",EToF1=".mkv",EToF2=".mp4";
         
    
     FilenameFilter filter = new FilenameFilter(){
         @Override
         public boolean accept(File file, String name){
         if(name.endsWith(EToF) ||name.endsWith(EToF1) ){
         
         return true;
         }else{
             return false;
         }
         }
     
     };
     
     
     
    FilesNames  FN = new FilesNames(/*COdeD,Divider,ChapNDW,Extencion*/);
    MediaNames  MN = new MediaNames(/*COdeD,Divider,ChapNDW,Extencion*/);
    
 
     
    //*******************Inicia generacion de datos para Download********************************** 
     
     public  void Get_Down_Data(){
         
        String[] Lista_Down =Download.list(filter);
        String tp="";
        boolean FoT=false;
        int p=0;
        File f;
       try{
           
           
            
        for (int X=0; X<Lista_Down.length;X++){
            System.out.println(Lista_Down[X]+ " : Listado de archivos a convertir");
            
            //********************VALIDACIONES INTERNAS DE TIPO FILE******************************************************
            
             f=new File(Download.getAbsolutePath(),Lista_Down[X]);//Genera un Tipo File Pra Path Pra comprobar
            
                if(!f.isDirectory()){             // Si Ruta "NO" pertenece a directorio                      
                      tp=Lista_Down[X].substring(0,1);
                        if(ND.isNumeric(tp)== true){
                         TempName = Lista_Down[X];
                      //   System.out.println("Numero de archivo: "+ p++ + " Archivo: " +Lista_Down[X]);
                        //SB.append("Numero de archivo:").append(p++).append(" Archivo: ").append(Lista_Down[X]);
                         ND.Gen_Down_Name(TempName);
                        }else{System.out.println(Lista_Down[X] +" :Archivo con formato erroneo");}
                }
        }
       
            }catch(Exception e){}    
            
            
        
    }
     
     
 //**************************************************************************** 
     
     
     
   //****************Validacion global Numeros**********************************************
    public  boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}                            
    //***************Fin Validacion Numeros***************************
    
    
    
    //*********************Inicia descompartimiento y generacion de nombre Download ******************************************************
     
     public void Gen_Down_Name(String Name){
        
         
            
        String CodeDN="",Divider="",ChapNDW="", Extencion="";
        char  split= '_',SPLT ='.';
        int Ctr = 0,CTR2=0,SB=0,SA=0;    
        
        char[] GenName = Name.toCharArray();
        
                  
        for (int x=0;x<GenName.length;x++){           
            if (GenName[x] == split){
                SB=Ctr;
                System.out.println("Comparacion  1st char: " + GenName[x]+ " Posicion del contador: " + Ctr);
                break;
            }else{
                Ctr++;
            }
        }
        
        
        
        for(int z=0;z<GenName.length;z++){
            if (GenName[z] == SPLT){
                SA=CTR2;
                System.out.println("Comparacion 2nd char: " + GenName[z]+ "Posicion del contador:  " + CTR2);
                break;
            }else{
                CTR2++;
            }
        }
               //******************************************************************************************
                            /*Utilizar un array que busque el divisor, con un contador          
                               * Marcar cuando lo encuentra, guardar en variable y  estabelecer el numero  
                               * usar numero para establecer final del substring */
                //********************************************************************************************            
                            
                            
                //CodeDN  = Name.substring(0,SB);
          MN.MCode=(CodeDN = Name.substring(0,SB));          
                //Divider = Name.substring(SB,SB+1);
          MN.MDiv =(Divider=Name.substring(SB,SB + 1));          
                //ChapNDW = Name.substring(SB + 1,SB + 2);
          MN.Chap=(Integer.parseInt(ChapNDW = Name.substring( SB + 1, SA)));
                // Extencion= Name.substring(SB+2);
          MN.Exten = (Extencion = Name.substring(SA));
          
          
                int data = Name.length();//Bandera
                System.out.println("Dato Recibido: "+CodeDN + "  " + Divider + "   " + ChapNDW + " " + Extencion+ "  :LARGO:  " + data);//Bandera
          
          System.out.println("Dato Procesado: MULTIMEDIA: " + MN.MCode + MN.MDiv+MN.Chap+MN.Exten+"   :LARGO: "+ data );//Bandera 
                  
          ND.Get_Locat_Data();// llamada a metodo que genera los datos de Locat
        
          //*****************************Finaliza descompartimiento y generacion de nombre Download*************************************************************************
          
          
          //*********************Inicia Genracion de Datos para Location***********************************************
    }
    
    public void Get_Locat_Data(){
        int p=0;
        String[] Lista_Locat = Location.list();
                      
      try{
            
            for(int S=0;S<Lista_Locat.length;S++){//String Lista_Locat1 : Lista_Locat
            System.out.println(Lista_Locat[S]+ " :Listado de directorios a comparar");
            TempName = Lista_Locat[S];
            
            System.out.println("Numero de Directorio: "+ (p++) + " Directorio: " +Lista_Locat[S]);
            
            ND.Get_SubLocat_Data(TempName);
                     
        }
      
      
      }catch(Exception e){}           
    }
    //*********************Finaliza Generacion de Datos para Location***********************************************
    
    
    //********************Inicia Genracion de Datos para SubLocation***********************************************
       
             //*Extencion en carpetas por año****************************
    
    public void Get_SubLocat_Data(String Name){
        int p=0;
        SubPath = Location.getAbsolutePath()+ "\\" + Name+"\\";
        System.out.println("Ruta de carpeta:  " +SubPath  );
        SubLocat = new File(SubPath);
        
        String[] Lista_SubLocat = SubLocat.list();
        
        
            try{
            
                for(int Z =0; Z<Lista_SubLocat.length;Z++){
                    System.out.println(Lista_SubLocat[Z]+ " :Listado de SUBdirectorios a comparar");
                TempName =Lista_SubLocat[Z];
                System.out.println("Numero de SubDirectorio: " + (p++) + " Directorio: " +Lista_SubLocat[Z]);
                
                  ND.Gen_Locat_Name(TempName);
                          
                    }
            
            }catch(Exception e){
                
                
             }       
    }
    
    //*****************Finaliza Genracion de Datos para SubLocation*****************************************
    
    
    //**************************************************************************
    public void Gen_Locat_Name(String Name){
        
        
        String Divider01="",CodeLT="",Divider02="",FNames="";
        char  split= ')';
        int Ctr = 0,SB=0;         
        char[] GenName = Name.toCharArray();
                  
        for (int x=0;x<GenName.length;x++){           
            if (split == GenName[x]){
                SB=Ctr;
                System.out.println("Es igual a: " + GenName[x]+ "  " + Ctr);
            break;    
            }else{
                Ctr++;
             }
        }
        
        
        
        
          FN.FDiv01 =(Divider01 = Name.substring(0, 1));  
          //Divider01 = Name.substring(0,1);
          System.out.println("*" + SB +" "+ FN.FDiv01);
          
          FN.FCode = (CodeLT=Name.substring(1, SB));
          System.out.println("*" + SB +" "+ FN.FCode);
          //CodeLT  = Name.substring(1,SB-1);
          FN.FDiv02 =(Divider02 = Name.substring(SB, SB+1));
          System.out.println("*" + SB+1+" " + FN.FDiv02);
          //Divider02 = Name.substring(SB,SB+1);
          FN.FName = (FNames = Name.substring(SB + 1 ));
          System.out.println("*" + SB+1+" " + FN.FName);
          //FNames = Name.substring(SB + 1);
          //
          int data = Name.length();
         System.out.println("DIRECTORIO: "+Divider01 + " " + CodeLT + "  " + Divider02  + "   " + FNames + "  :LARGO:  " + data);
         System.out.println("Dato Procesado: Directorio: " + FN.FDiv01 + FN.FCode + FN.FDiv02 + FN.FName +"   :LARGO: "+ data );//Bandera 
          
          ND.Compare();
     
    }
    
    public void Compare(){
        Boolean TwoBE = false;
        System.out.println("Compracion de Code's " + MN.MCode + " :: " + FN.FCode);
        if(MN.MCode.equals(FN.FCode)){
            
            System.out.println(" comparacion Es igual");
            Good_Comparation++;
            ND.GenNewName();
            
            
        }else{
            
           System.out.println("Incompatible");
           Bad_Comparation++;
       System.out.println(MN.MCode +"::::"+ FN.FCode + " :PRUEBA");
           
        }
        
    }
    
    public void GenNewName(){
        String Split01 = "[", Split02="]";
        boolean th = true; int ff= 0;
    
    System.out.println(Download.getAbsolutePath());
            
            OrigenPath = Download.getAbsolutePath()+"\\"  + MN.MCode + MN.MDiv + MN.Chap + MN.Exten; 
           System.out.println(OrigenPath + "Prueba 0002");
            EndPath = /*Location.getAbsolutePath()*/SubPath + "\\" + FN.FDiv01 + FN.FCode + FN.FDiv02 + FN.FName 
                    +"\\" +Split01 + FN.FCode + Split02 +FN.FName + " " +MN.Chap + MN.Exten;
            System.out.println(" PRUEBA D:   " + EndPath);
            
            Origen  = FileSystems.getDefault().getPath(OrigenPath);
            Destino = FileSystems.getDefault().getPath(EndPath);
            
            //********
         ND.Re_Move();
            
         
            
                    
                    
    }
    
    public void Re_Move(){
         
        System.out.println("KKKKKKKK:  "+Origen);
       System.out.println("MMMMMM: "+Destino);
        try {
            
            Files.move(Origen,Destino, StandardCopyOption.REPLACE_EXISTING);
            Re_Name_Counter++;
        } catch (IOException e) {
            
            System.err.println(e);
            
        }
        System.out.println("Counter: " + Re_Name_Counter);
    
        
    }
    
    
    
    
    
}
