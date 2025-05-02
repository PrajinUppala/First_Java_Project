package arbitraryarithmetic;

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

    // Method to convert a String which contains a floating point number to an array with removing the decimal
    public static int[] convertFloatStringToIntArray(String floatStr) {
        // Remove the decimal point
        String cleanStr = floatStr.replace(".", "");

        // Create an array to hold digits
        int[] digits = new int[cleanStr.length()];

        // Convert each character to integer
        for (int i = 0; i < cleanStr.length(); i++) {
            digits[i] = cleanStr.charAt(i) - '0';
        }

        return digits;
    }
    
    public static String removeTrailingZeros(String s) {
        // Remove trailing zeros
        while (s.endsWith("0")) {
            s = s.substring(0, s.length() - 1);
        }

        if(s.endsWith(".")){
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    // Main addition method handling both the signs
    public AFloat add_Float(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        // Case 1: If both num1 and num2 are negative add the absolute value and then put '-' sign
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat("-" + add_strings_floating(num1.substring(1), num2.substring(1)));
        }
        // Case 2: If num1 positive, num2 negative then Subtract absolute value of num2 from num1
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num1, num2.substring(1)));
        }
        // Case 3: If num1 negative, num2 positive then Subtract absolute value of num1 from num2
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num2, num1.substring(1)));
        }
        // Case 4: Both positive then Simple addition
        else {
            return new AFloat(add_strings_floating(num1, num2));
        }
    }

    // Main substraction method handling both signs
    public AFloat substract_Float(AFloat other) {
        String num1 = this.floating;
        String num2 = other.floating;
    
        // Case 1: (-num1) - (-num2) then do num2 - num1
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num2.substring(1), num1.substring(1)));
        }
    
        // Case 2: num1 - (-num2) →then do num1 + num2
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

        if(!(num1.contains("."))){
            StringBuilder num1Builder = new StringBuilder(num1);
            num1Builder.append(".");
            num1 = num1Builder.toString();
        }
        if(!(num2.contains("."))){
            StringBuilder num2Builder = new StringBuilder(num2);
            num2Builder.append(".");
            num2 = num2Builder.toString();
        }

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

        int maxDecimalDigits = Math.max(numDigitsAfterDecimal_num1, numDigitsAfterDecimal_num2);

        if (sum_Builder.length() <= maxDecimalDigits) {
            while (sum_Builder.length() <= maxDecimalDigits) {
                sum_Builder.insert(0, "0");
            }
        }

        sum_Builder.insert(sum_Builder.length() - maxDecimalDigits, '.');
        

        String sum_float = sum_Builder.toString();
        sum_float = removeTrailingZeros(sum_float);
        return sum_float;
    }

    public String substract_strings_floating(String num1 , String num2){

        if(!(num1.contains("."))){
            StringBuilder num1Builder = new StringBuilder(num1);
            num1Builder.append(".");
            num1 = num1Builder.toString();
        }
        if(!(num2.contains("."))){
            StringBuilder num2Builder = new StringBuilder(num2);
            num2Builder.append(".");
            num2 = num2Builder.toString();
        }

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

        int maxDecimalDigits = Math.max(numDigitsAfterDecimal_num1, numDigitsAfterDecimal_num2);

        if (substract_Builder.length() <= maxDecimalDigits) {
            if(substract_Builder.charAt(0) == '-'){
                while (substract_Builder.length() <= maxDecimalDigits + 1) {
                    substract_Builder.insert(1, "0");
                }
            }
            else{
                while (substract_Builder.length() <= maxDecimalDigits) {
                    substract_Builder.insert(0, "0");
                }
            }
        }
        
        substract_Builder.insert(substract_Builder.length() - maxDecimalDigits, '.');

        String substract_float = substract_Builder.toString();
        substract_float = removeTrailingZeros(substract_float);
        return substract_float;
    }

    public AFloat multiply_Float(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        if(!(num1.contains("."))){
            StringBuilder num1Builder = new StringBuilder(num1);
            num1Builder.append(".");
            num1 = num1Builder.toString();
        }
        if(!(num2.contains("."))){
            StringBuilder num2Builder = new StringBuilder(num2);
            num2Builder.append(".");
            num2 = num2Builder.toString();
        }

        // Store the decimal index of both the numbers
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');

        // Store number of digits after the decimal point
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;
        
        num1 = num1.replace(".", "");
        num2 = num2.replace(".", "");

        AInteger num1Int = new AInteger(num1);
        AInteger num2Int = new AInteger(num2);

        AInteger resultInt = num1Int.multiply(num2Int);

        StringBuilder resultBuilder = new StringBuilder(resultInt.integer);
        
        int maxDecimalDigits = numDigitsAfterDecimal_num1 + numDigitsAfterDecimal_num2;

        if (resultBuilder.length() <= maxDecimalDigits) {
            if(resultBuilder.charAt(0) == '-'){
                while (resultBuilder.length() <= maxDecimalDigits + 1) {
                    resultBuilder.insert(1, "0");
                }
            }
            else{
                while (resultBuilder.length() <= maxDecimalDigits) {
                    resultBuilder.insert(0, "0");
                }
            }
        }

        resultBuilder.insert(resultBuilder.length() - maxDecimalDigits, '.');

        truncateTo30DecimalPlaces(resultBuilder);

        return new AFloat(resultBuilder.toString());
    }

    public AFloat division_Float(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        if(!(num1.contains("."))){
            num1 = num1 + ".0" ;
        }
        if(!(num2.contains("."))){
            num2 = num2 + ".0" ;
        }
        
        // Store the decimal index of both the numbers
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');

        // Store number of digits after the decimal point
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        num1 = num1.replace(".", "");
        num2 = num2.replace(".", "");

        StringBuilder num1Builder = new StringBuilder(num1);

        int numOfZerosNeededToAdd_num1 = 30 - numDigitsAfterDecimal_num1 + numDigitsAfterDecimal_num2;
        for(int i = 0; i < numOfZerosNeededToAdd_num1 ; i++){
            num1Builder.append("0");
        }

        num1 = num1Builder.toString();
        
        AInteger num1Int = new AInteger(num1);
        AInteger num2Int = new AInteger(num2);

        AInteger resultInt = num1Int.division(num2Int);
        if(resultInt.integer == "Division by zero error"){
            return new AFloat("Division by zero error");
        }

        StringBuilder resultBuilder = new StringBuilder(resultInt.integer);

        while (resultBuilder.length() <= 30) {
            resultBuilder.insert(0, '0');
        }

        resultBuilder.insert(resultBuilder.length() - 30, '.');

        truncateTo30DecimalPlaces(resultBuilder);
        String result = removeTrailingZeros(resultBuilder.toString());
        
        return new AFloat(result);
    }

    public static void truncateTo30DecimalPlaces(StringBuilder sb) {
        int decimalIndex = sb.indexOf(".");
        
        // If there's no decimal point, nothing to do
        if (decimalIndex == -1) return;
    
        int decimals = sb.length() - decimalIndex - 1;
    
        // If there are more than 30 digits after the decimal, truncate
        if (decimals > 30) {
            sb.delete(decimalIndex + 31, sb.length());
        }
    }
    
    // Prints the value of the floating point number
    public void printValue() {
        System.out.println(this.floating);
    }

    public static void main(String[] args) {
        AFloat a = new AFloat("3227");
        AFloat b =new AFloat("555");
        AFloat result = a.division_Float(b);
        result.printValue();
    }
}
