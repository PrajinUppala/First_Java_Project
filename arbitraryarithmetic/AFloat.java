public class AFloat {
    
    private String floating;

    // Default constructor sets the floating to "0.0"
    public AFloat() {
        floating = "0.0";
    }

    // Constructor from string
    public AFloat(String s) {
        floating = s;
    }

    // Copy Constructor
    public AFloat(AFloat other) {
        this.floating = other.floating;
    }

    // Static method to parse string into AFloat
    public static AFloat parse (String s) {
        return new AFloat(s);
    }

    // Removing Leading zeros if the given variable type is String
    private static String removeLeadingZeros(String s) {
        int decimal_index = s.indexOf('.');

        for(int i = 0; i < decimal_index - 1; i++){
            if(s.charAt(0) == '0'){
                s = s.substring(1);
            }
            else{
                break;
            }
        }
        return s;
    }

    // Removing Leading zeros if the given variable type is StringBuilder
    private static StringBuilder removeLeadingZeros(StringBuilder s) {
        int decimal_index = s.indexOf(".");

        for(int i = 0; i < decimal_index - 1; i++){
            if(s.charAt(0) == '0'){
                s.deleteCharAt(0);
            }
            else{
                break;
            }
        }
        return s;
    }
    
    // Method to convert a String which contains a floating point number to an array with removing the decimal
    public static int[] convertFloatStringToIntArray(String floatStr) {
        // Remove the decimal point
        String cleanStr = floatStr.replace(".", "");

        // Create an array to hold digits
        int[] digits = new int[cleanStr.length()];

        // Convert each character to integer
        for (int i = 0; i < cleanStr.length(); i++) {
            digits[i] = Character.getNumericValue(cleanStr.charAt(i));
        }

        return digits;
    }

    // Main addition method handling both the signs
    public AFloat add(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        // Case 1: Both negative -> Add absolute values and prefix "-"
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat("-" + add_strings_floating(num1.substring(1), num2.substring(1)));
        }
        // Case 2: num1 positive, num2 negative -> Subtract abs(num2) from num1
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num1, num2.substring(1)));
        }
        // Case 3: num1 negative, num2 positive -> Subtract abs(num1) from num2
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num2, num1.substring(1)));
        }
        // Case 4: Both positive -> Simple addition
        else {
            return new AFloat(add_strings_floating(num1, num2));
        }
    }

    // Main substraction method handling both signs
    public AFloat substract(AFloat other) {
        String num1 = this.floating;
        String num2 = other.floating;
    
        // Case 1: (-num1) - (-num2) → same as num2 - num1
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num2.substring(1), num1.substring(1)));
        }
    
        // Case 2: num1 - (-num2) → same as num1 + num2
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(add_strings_floating(num1, num2.substring(1)));
        }
    
        // Case 3: (-num1) - num2 → result is negative of (num1 + num2)
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AFloat("-" + add_strings_floating(num1.substring(1), num2));
        }
    
        // Case 4: num1 - num2 → normal subtraction
        else {
            return new AFloat(substract_strings_floating(num1, num2));
        }
    }
    
    // Method to add two non-negative floating point numbers represented as strings
    public String add_strings_floating (String num1 , String num2){
                
        // Remove leading zeros from both num1, num2 strings
        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);

        // Store the decimal index of both the numbers
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');

        // Store number of digits after the decimal point
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        StringBuilder num1_Builder = new StringBuilder(num1);
        StringBuilder num2_Builder = new StringBuilder(num2);

        num1_Builder.deleteCharAt(decimal_index_num1);
        num2_Builder.deleteCharAt(decimal_index_num2);

        if(numDigitsAfterDecimal_num1 > numDigitsAfterDecimal_num2){
            for(int i = 0 ; i < numDigitsAfterDecimal_num1 - numDigitsAfterDecimal_num2 ; i++){
                num2_Builder.append("0");
            }
        }
        else if(numDigitsAfterDecimal_num1 < numDigitsAfterDecimal_num2){
            for(int i = 0 ; i < numDigitsAfterDecimal_num2 - numDigitsAfterDecimal_num1 ; i++){
                num1_Builder.append("0");
            }
        }

        AInteger aInt = new AInteger();

        String sum_without_decimal = aInt.add_strings_integer(num1_Builder.toString(),num2_Builder.toString());
        StringBuilder sum_Builder = new StringBuilder(sum_without_decimal);

        if (numDigitsAfterDecimal_num1 > numDigitsAfterDecimal_num2){
            sum_Builder.insert(sum_without_decimal.length() - numDigitsAfterDecimal_num1, '.');
        }
        else{
            sum_Builder.insert(sum_without_decimal.length() - numDigitsAfterDecimal_num2, '.');
        }

        String sum_float = sum_Builder.toString();
        return sum_float;
    }

    public String substract_strings_floating(String num1 , String num2){
                
        // Remove leading zeros from both num1, num2 strings
        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);

        // Store the decimal index of both the numbers
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');

        // Store number of digits after the decimal point
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        StringBuilder num1_Builder = new StringBuilder(num1);
        StringBuilder num2_Builder = new StringBuilder(num2);

        num1_Builder.deleteCharAt(decimal_index_num1);
        num2_Builder.deleteCharAt(decimal_index_num2);

        if(numDigitsAfterDecimal_num1 > numDigitsAfterDecimal_num2){
            for(int i = 0 ; i < numDigitsAfterDecimal_num1 - numDigitsAfterDecimal_num2 ; i++){
                num2_Builder.append("0");
            }
        }
        else if(numDigitsAfterDecimal_num1 < numDigitsAfterDecimal_num2){
            for(int i = 0 ; i < numDigitsAfterDecimal_num2 - numDigitsAfterDecimal_num1 ; i++){
                num1_Builder.append("0");
            }
        }

        AInteger aInt = new AInteger();

        String substract_without_decimal = aInt.substract_strings_integer(num1_Builder.toString(),num2_Builder.toString());
        StringBuilder substract_Builder = new StringBuilder(substract_without_decimal);

        if (numDigitsAfterDecimal_num1 > numDigitsAfterDecimal_num2){
            substract_Builder.insert(substract_without_decimal.length() - numDigitsAfterDecimal_num1, '.');
        }
        else{
            substract_Builder.insert(substract_without_decimal.length() - numDigitsAfterDecimal_num2, '.');
        }

        String substract_float = substract_Builder.toString();
        return substract_float;
    }

    // Prints the value of the floating point number
    public void printValue() {
        System.out.println(this.floating);
    }

    public static void main(String[] args) {
        AFloat a = new AFloat( "100.");
        AFloat b =new AFloat("200.");
        AFloat result = a.add(b);
        result.printValue();
    }
}
