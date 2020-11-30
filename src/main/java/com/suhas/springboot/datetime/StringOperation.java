package com.suhas.springboot.datetime;

public class StringOperation {
	
	
	public int lengthOfString(String inputString){
		int length = 0;
		char[] charArray = inputString.toCharArray();
		 for(char c:charArray)
		  {
			 length++;
		  }
		return length;
		
	}
	
	public int findCharacterInString(char inputChar, String inputString){
		int pos = 1;
		char[] charArray = inputString.toCharArray();
		 for(char c:charArray)
		  {
			 if(inputChar == c){
				 return pos;
			 } else {
				 pos++;
			 }
		  }
		return pos;
		
	}
	
	public String findSubString(String inputString, int startFrom, int endBefore){
        String newStr = "";
        for (int i = startFrom; i < endBefore; i++)
            newStr += String.valueOf(inputString.charAt(i));
        return newStr;
	}
	
	
	public static void main(String[] args) {
		StringOperation stringOperation = new StringOperation();
		System.out.println("Length of string :" +stringOperation.lengthOfString("Hello World!"));
		System.out.println("position of Character :" +stringOperation.findCharacterInString('W',"Hello World!"));
		String str = "Hello World!";
		int startFrom = 2, endBefore = 5;
		System.out.println(stringOperation.findSubString(str,startFrom,endBefore));
	}

}
