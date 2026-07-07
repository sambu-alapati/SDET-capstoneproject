package utils;

import java.util.Scanner;

public class CheckingValidPassword {

	public static boolean isValidpassword(String pass) {
		String symbols="!@#$%^&*";
		
		 boolean isUpper=false;
		 boolean isLower=false;
		 boolean isDigit=false;
		 boolean isSymbol=false;
		 
			 for(int i=0;i<pass.length();i++) {
				 char c=pass.charAt(i);
				 
				 if(Character.isUpperCase(c)) {
					 isUpper=true;
				 }else if(Character.isLowerCase(c)) {
					 isLower=true;
				 }else if(Character.isDigit(c)) {
					 isDigit=true;
				 }else if(symbols.indexOf(c)!=-1) {
					 isSymbol=true;
				 }
			 }
		 
		 if(isUpper && isLower &&  isDigit && isSymbol && (pass.length()>=8 && pass.length()<=16)) {
			 return true;
		 }
		 return false;
	 }

	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		// System.out.print("Enter Password : ");
	     String pass=sc.nextLine();
	    // System.out.println(isValidpassword(pass));
	     isValidpassword(pass);

	}

}
