package com.qhwy.getway;

public class Test {
	
	public static void main(String[] args) {
		Integer i=1;
		Integer x=1;
		Integer j=new Integer(1);
		if(i==j){
			System.out.println("aa");
			
		}
		if(i.equals(j)){
			System.out.println("bbb");
			
		}
		if(i.equals(x)){
			System.out.println("ccc");
			
		}
	}

}
