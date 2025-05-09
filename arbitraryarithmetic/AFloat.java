package arbitraryarithmetic;

public class AFloat {
    
    private String floating; // Stores the float value as a string

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

    // Helper method to remove unnecessary trailing zeros after decimal point
    public static String removeTrailingZeros(String s) {
        while (s.endsWith("0")) {
            s = s.substring(0, s.length() - 1);
        }
        if(s.endsWith(".")){
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    // Addition method for floating point numbers (with sign handling)
    public AFloat add_Float(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        // Both negative: add absolute values and prepend minus sign
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat("-" + add_strings_floating(num1.substring(1), num2.substring(1)));
        }
        // num1 positive, num2 negative: subtract absolute value of num2 from num1
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num1, num2.substring(1)));
        }
        // num1 negative, num2 positive: subtract absolute value of num1 from num2
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num2, num1.substring(1)));
        }
        // Both positive: regular addition
        else {
            return new AFloat(add_strings_floating(num1, num2));
        }
    }

    // Subtraction method for floating point numbers (with sign handling)
    public AFloat substract_Float(AFloat other) {
        String num1 = this.floating;
        String num2 = other.floating;
    
        // (-num1) - (-num2) → num2 - num1
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(substract_strings_floating(num2.substring(1), num1.substring(1)));
        }
        // num1 - (-num2) → num1 + num2
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AFloat(add_strings_floating(num1, num2.substring(1)));
        }
        // (-num1) - num2 → negative of (num1 + num2)
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AFloat("-" + add_strings_floating(num1.substring(1), num2));
        }
        // num1 - num2 → normal subtraction
        else {
            return new AFloat(substract_strings_floating(num1, num2));
        }
    }
    
    // Adds two non-negative floating point numbers represented as strings
    public String add_strings_floating (String num1 , String num2){

        // Append ".0" if input doesn't have decimal point
        if(!(num1.contains("."))){
            num1 += ".";
        }
        if(!(num2.contains("."))){
            num2 += ".";
        }

        // Get index of decimal point
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');

        // Count digits after decimal
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        // Remove decimal point
        StringBuilder num1_Builder = new StringBuilder(num1);
        StringBuilder num2_Builder = new StringBuilder(num2);
        num1_Builder.deleteCharAt(decimal_index_num1);
        num2_Builder.deleteCharAt(decimal_index_num2);

        // Pad shorter fractional part with zeros
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

        // Perform addition on integer strings
        AInteger aInt = new AInteger();
        String sum_without_decimal = aInt.add_strings_integer(num1_Builder.toString(), num2_Builder.toString());
        StringBuilder sum_Builder = new StringBuilder(sum_without_decimal);

        int maxDecimalDigits = Math.max(numDigitsAfterDecimal_num1, numDigitsAfterDecimal_num2);

        // Pad result if needed
        if (sum_Builder.length() <= maxDecimalDigits) {
            while (sum_Builder.length() <= maxDecimalDigits) {
                sum_Builder.insert(0, "0");
            }
        }

        // Reinsert decimal point
        sum_Builder.insert(sum_Builder.length() - maxDecimalDigits, '.');

        // Return final result without trailing zeros
        return removeTrailingZeros(sum_Builder.toString());
    }

    // Subtracts two non-negative floating point numbers represented as strings
    public String substract_strings_floating(String num1 , String num2){

        // Ensure decimal part exists
        if(!(num1.contains("."))){
            num1 += ".";
        }
        if(!(num2.contains("."))){
            num2 += ".";
        }

        // Get index of decimal point
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');

        // Count digits after decimal
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        // Remove decimal point
        StringBuilder num1_Builder = new StringBuilder(num1);
        StringBuilder num2_Builder = new StringBuilder(num2);
        num1_Builder.deleteCharAt(decimal_index_num1);
        num2_Builder.deleteCharAt(decimal_index_num2);

        // Pad shorter fractional part with zeros
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

        // Perform subtraction on integer strings
        AInteger aInt = new AInteger();
        String substract_without_decimal = aInt.substract_strings_integer(num1_Builder.toString(), num2_Builder.toString());
        StringBuilder substract_Builder = new StringBuilder(substract_without_decimal);

        int maxDecimalDigits = Math.max(numDigitsAfterDecimal_num1, numDigitsAfterDecimal_num2);

        // Pad result if needed
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

        // Reinsert decimal point
        substract_Builder.insert(substract_Builder.length() - maxDecimalDigits, '.');

        // Return final result without trailing zeros
        return removeTrailingZeros(substract_Builder.toString());
    }

    // Multiplies two floating point numbers
    public AFloat multiply_Float(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        // Add ".0" if missing
        if(!(num1.contains("."))){
            num1 += ".";
        }
        if(!(num2.contains("."))){
            num2 += ".";
        }

        // Get number of digits after decimal point
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        // Remove decimal points
        num1 = num1.replace(".", "");
        num2 = num2.replace(".", "");

        // Convert to AInteger and multiply
        AInteger num1Int = new AInteger(num1);
        AInteger num2Int = new AInteger(num2);
        AInteger resultInt = num1Int.multiply(num2Int);

        StringBuilder resultBuilder = new StringBuilder(resultInt.integer);

        // Total decimal digits in result
        int maxDecimalDigits = numDigitsAfterDecimal_num1 + numDigitsAfterDecimal_num2;

        // Pad result if needed
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

        // Reinsert decimal point
        resultBuilder.insert(resultBuilder.length() - maxDecimalDigits, '.');

        // Truncate to 30 digits after decimal
        truncateTo30DecimalPlaces(resultBuilder);

        return new AFloat(resultBuilder.toString());
    }

    // Divides this float by another float
    public AFloat division_Float(AFloat other){
        String num1 = this.floating;
        String num2 = other.floating;

        // Add ".0" if missing
        if(!(num1.contains("."))){
            num1 = num1 + ".0" ;
        }
        if(!(num2.contains("."))){
            num2 = num2 + ".0" ;
        }

        // Get number of digits after decimal point
        int decimal_index_num1 = num1.indexOf('.');
        int decimal_index_num2 = num2.indexOf('.');
        int numDigitsAfterDecimal_num1 = num1.length() - decimal_index_num1 - 1;
        int numDigitsAfterDecimal_num2 = num2.length() - decimal_index_num2 - 1;

        // Remove decimal point
        num1 = num1.replace(".", "");
        num2 = num2.replace(".", "");

        // Append zeros to numerator for precision
        StringBuilder num1Builder = new StringBuilder(num1);
        int numOfZerosNeededToAdd_num1 = 30 - numDigitsAfterDecimal_num1 + numDigitsAfterDecimal_num2;
        for(int i = 0; i < numOfZerosNeededToAdd_num1 ; i++){
            num1Builder.append("0");
        }

        num1 = num1Builder.toString();

        // Perform division using AInteger
        AInteger num1Int = new AInteger(num1);
        AInteger num2Int = new AInteger(num2);
        AInteger resultInt = num1Int.division(num2Int);

        if(resultInt.integer == "Division by zero error"){
            return new AFloat("Division by zero error");
        }

        StringBuilder resultBuilder = new StringBuilder(resultInt.integer);

        // Pad result if shorter than 30 decimal digits
        while (resultBuilder.length() <= 30) {
            resultBuilder.insert(0, '0');
        }

        // Reinsert decimal point
        resultBuilder.insert(resultBuilder.length() - 30, '.');

        // Truncate and cleanup
        truncateTo30DecimalPlaces(resultBuilder);
        String result = removeTrailingZeros(resultBuilder.toString());

        return new AFloat(result);
    }

    // Truncates a float string to 30 decimal places
    public static void truncateTo30DecimalPlaces(StringBuilder sb) {
        int decimalIndex = sb.indexOf(".");
        if (decimalIndex == -1) return;

        int decimals = sb.length() - decimalIndex - 1;

        if (decimals > 30) {
            sb.delete(decimalIndex + 31, sb.length());
        }
    }

    // Print value of float
    public void printValue() {
        System.out.println(this.floating);
    }

}
