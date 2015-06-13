package com.online.market.util;
import java.text.DecimalFormat;

//by net with tony 20110726
public class NumberToWords {

  private final String[] tensNames = {
    "",
    " ten",
    " twenty",
    " thirty",
    " forty",
    " fifty",
    " sixty",
    " seventy",
    " eighty",
    " ninety"
  };

  private final String[] numNames = {
    "",
    " one",
    " two",
    " three",
    " four",
    " five",
    " six",
    " seven",
    " eight",
    " nine",
    " ten",
    " eleven",
    " twelve",
    " thirteen",
    " fourteen",
    " fifteen",
    " sixteen",
    " seventeen",
    " eighteen",
    " nineteen"
  };

  private String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    }else {
      soFar = numNames[number % 10];
      number /= 10;

      if(soFar.equals("")){
    	  soFar = tensNames[number % 10] + soFar;
      }else{
    	  soFar = tensNames[number % 10] + " and " + soFar;
      }
      number /= 10;
    }
    if (number == 0){
    	return soFar;
    }
    return numNames[number] + " hundred" + soFar;
  }


  public String convert(long number) {
    // 0 to 999 999 999 999
    if (number == 0) { return "zero"; }

    String snumber = Long.toString(number);

    // pad with "0"
    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);

    // XXXnnnnnnnnn 
    int billions = Integer.parseInt(snumber.substring(0,3));
    // nnnXXXnnnnnn
    int millions  = Integer.parseInt(snumber.substring(3,6)); 
    // nnnnnnXXXnnn
    int hundredThousands = Integer.parseInt(snumber.substring(6,9)); 
    // nnnnnnnnnXXX
    int thousands = Integer.parseInt(snumber.substring(9,12));    

    String tradBillions;
    switch (billions) {
    case 0:
      tradBillions = "";
      break;
    case 1 :
      tradBillions = convertLessThanOneThousand(billions) 
      + " billion ";
      break;
    default :
      tradBillions = convertLessThanOneThousand(billions) 
      + " billion ";
    }
    String result =  tradBillions;

    String tradMillions;
    switch (millions) {
    case 0:
      tradMillions = "";
      break;
    case 1 :
      tradMillions = convertLessThanOneThousand(millions) 
      + " million ";
      break;
    default :
      tradMillions = convertLessThanOneThousand(millions) 
      + " million ";
    }
    result =  result + tradMillions;

    String tradHundredThousands;
    switch (hundredThousands) {
    case 0:
      tradHundredThousands = "";
      break;
    case 1 :
      tradHundredThousands = "one thousand ";
      break;
    default :
      tradHundredThousands = convertLessThanOneThousand(hundredThousands) 
      + " thousand ";
    }
    result =  result + tradHundredThousands;

    String tradThousand;
    tradThousand = convertLessThanOneThousand(thousands);
    result =  result + tradThousand;

    // remove extra spaces!
    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
  }

  /**
   * testing
   * @param args
   */
  public static void main(String[] args) {
	  NumberToWords nw = new NumberToWords();

	  System.out.println("*** " + nw.convert(1031));
	  System.out.println("*** " + nw.convert(1030));
    System.out.println("*** " + nw.convert(0));
    System.out.println("*** " + nw.convert(1));
    System.out.println("*** " + nw.convert(36));
    System.out.println("*** " + nw.convert(136));
    System.out.println("*** " + nw.convert(1030));
    System.out.println("*** " + nw.convert(1036));
    System.out.println("*** " + nw.convert(1236));

    System.out.println("*** " + nw.convert(3000000010L));
	  
	  
    /*
     *** zero
     *** one
     *** sixteen
     *** one hundred
     *** one hundred eighteen
     *** two hundred
     *** two hundred nineteen
     *** eight hundred
     *** eight hundred one
     *** one thousand three hundred sixteen
     *** one million 
     *** two millions 
     *** three millions two hundred
     *** seven hundred thousand 
     *** nine millions 
     *** nine millions one thousand 
     *** one hundred twenty three millions four hundred 
     **      fifty six thousand seven hundred eighty nine
     *** two billion one hundred forty seven millions 
     **      four hundred eighty three thousand six hundred forty seven
     *** three billion ten
     **/
  }
}

