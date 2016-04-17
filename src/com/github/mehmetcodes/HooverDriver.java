package com.github.mehmetcodes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.SecurityManager;
import com.github.mehmetcodes.Hoover;

public class HooverDriver
{
	public static final boolean verbose = false;
	/**
  * This is our driver class.  We take one file
	* under a specific format as an argument, then
	* as the input is good, we create a hoover instance
	* and follow the file's directives
  *
	* @return void
  */
	public static void main(String [] args)
	{
		//Grab file
    try{
      if(args == null ){
				throw new NullPointerException();
      }
			String test = args[0];
			SecurityManager security = System.getSecurityManager();
			if(security != null){
      	security.checkRead(test);
			}
			//Begin processing the file and create the Hoover
			FileInputStream fstream = new FileInputStream(args[0]);
      BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line = "";
			line = br.readLine();
			String [] words = line.split(" ");
			Hoover roombit = new Hoover();
			//Get dimensions and set the corner
			if(words.length == 2 ){
				roombit.setTopRightCorner(Integer.parseInt(words[0]),Integer.parseInt(words[1]));
				if(verbose){System.out.print(roombit);}
			}else{
				System.err.println("You must have input dimensions in the file <x y>");
				System.exit(-1);
			}
			//Get the initial location
			line = br.readLine();
			if(words.length == 2 ){
				words = line.split(" ");
				roombit.setStart(Integer.parseInt(words[0]),Integer.parseInt(words[1]));
				if(verbose){System.out.print(roombit);}
			}else{
				System.err.println("You must have input a start location on the second line");
				System.exit(-1);
			}
			boolean parseletters = false;
			while( (line = br.readLine() ) != null ){
				String [] testit = line.split(" ");

				try{
					if(testit.length == 2 && Integer.parseInt(testit[0]) >= 0  ){
						roombit.addDirtpatch(Integer.parseInt(testit[0]),Integer.parseInt(testit[1]));
					}else{
						parseletters = true;
					}
				}catch(NumberFormatException e){
        	parseletters = true;
    		}finally{
					if(parseletters){
						for (int i = 0;i < line.length(); i++){
	    				 if(line.charAt(i) == 'N'){
								 roombit.goNorth();
								 if(verbose){System.out.print(roombit);}
							 }else if(line.charAt(i) == 'E'){
								 roombit.goEast();
								 if(verbose){System.out.print(roombit);}
							 }else if(line.charAt(i) == 'S'){
								 roombit.goSouth();
								 if(verbose){System.out.print(roombit);}
							 }else if(line.charAt(i) == 'W'){
								 roombit.goWest();
								 if(verbose){System.out.print(roombit);}
							 }else{
								 //Just ignore and move on to the next letter
							 }
						}
						System.out.print( roombit.status() );
						break;
					}
				}
			}
    }catch(IOException ie){
			System.err.println("Problem reading the file");
		}catch(SecurityException se){
      System.err.println("You aren't allowed to read "+args[0]);
    }catch(NullPointerException ne){
			  ne.printStackTrace();
        System.err.println("You must supply a filename as an argument");
    }catch(Exception e){
			System.err.println(e.getMessage());
		}
  }

}
