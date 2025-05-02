package arbitraryarithmetic;

public class AInteger {

    protected String integer;

    // Default constructor sets the integer to "0"
    public AInteger() {
        integer = "0";
    }

    // Constructor from string
    public AInteger(String s) {
        integer = s;
    }

    // Copy constructor
    public AInteger(AInteger other) {
        this.integer = other.integer;
    }

    // Static method to parse string into AInteger
    public static AInteger parse(String s) { 
        AInteger integer = new AInteger(s);  
        return integer;  
    }

    // Removing Leading zeros if the given variable type is String
    private static String removeLeadingZeros(String s) {
        while (s.length() > 1 && s.charAt(0) == '0') {
            s = s.substring(1);
        }
        return s;
    }

    // Removing Leading zeros if the given variable type is StringBuilder
    private static StringBuilder removeLeadingZeros(StringBuilder s) {
        while (s.length() > 1 && s.charAt(0) == '0') {
            s.deleteCharAt(0);
        }
        return s;
    }
    
    // Main addition method handling both signs
    public AInteger add (AInteger other) {
        String num1 = this.integer;
        String num2 = other.integer;

        // Case 1: Both negative -> Add absolute values and prefix "-"
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AInteger("-" + add_strings_integer(num1.substring(1), num2.substring(1)));
        }
        // Case 2: num1 positive, num2 negative -> Subtract abs(num2) from num1
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AInteger(substract_strings_integer(num1, num2.substring(1)));
        }
        // Case 3: num1 negative, num2 positive -> Subtract abs(num1) from num2
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AInteger(substract_strings_integer(num2, num1.substring(1)));
        }
        // Case 4: Both positive -> Simple addition
        else {
            return new AInteger(add_strings_integer(num1, num2));
        }

    }

    // Main substraction method handling both signs
    public AInteger substract(AInteger other) {
        String num1 = this.integer;
        String num2 = other.integer;
    
        // Case 1: (-num1) - (-num2) → same as num2 - num1
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return new AInteger(substract_strings_integer(num2.substring(1), num1.substring(1)));
        }
    
        // Case 2: num1 - (-num2) → same as num1 + num2
        else if (!num1.startsWith("-") && num2.startsWith("-")) {
            return new AInteger(add_strings_integer(num1, num2.substring(1)));
        }
    
        // Case 3: (-num1) - num2 → result is negative of (num1 + num2)
        else if (num1.startsWith("-") && !num2.startsWith("-")) {
            return new AInteger("-" + add_strings_integer(num1.substring(1), num2));
        }
    
        // Case 4: num1 - num2 → normal subtraction
        else {
            return new AInteger(substract_strings_integer(num1, num2));
        }
    }
    
    // Method to add two non-negative integers represented as strings
    public String add_strings_integer(String num1, String num2){
        
        // Remove leading zeros from both num1, num2 strings
        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);

        // Swap to make num1 always longer
        if(num1.length() < num2.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // Define variable sum of type StringBuilder 
        StringBuilder sum = new StringBuilder();
        // Initialize the variable carry to 0
        int carry = 0;
        
        // Add digits from right to left
        for (int i = 0; i < num1.length(); i++) {
            int digit1 = num1.charAt(num1.length() - 1 - i) - '0';
            int digit2 = (i < num2.length()) ? num2.charAt(num2.length() - 1 - i) - '0' : 0;

            int digitSum = digit1 + digit2 + carry;
            sum.insert(0, digitSum % 10);
            carry = digitSum / 10;
        }

        // Final carry if exists
        if(carry != 0) {
            sum.insert(0, carry);
        }

        // Remove leading zeros from result
        sum = removeLeadingZeros(sum);

        return sum.toString();
    }

    // Method to subtract num2 from num1 (both strings, non-negative)
    public String substract_strings_integer(String num1, String num2) {
        boolean negative_result = false;

        // Remove leading zeros from both num1, num2 strings
        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);

        // Check if result will be negative, swap if needed
        if ((num1.length() < num2.length()) || 
            (num1.length() == num2.length() && (num1.compareTo(num2) < 0))) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
            negative_result = true;
        }

        // Define a variable difference StringBuilder type
        StringBuilder difference = new StringBuilder();
        int borrow = 0;

        // Subtract common length part from right to left
        for(int i = 0 ; i < num2.length() ; i++){
            int digit1 = num1.charAt(num1.length() - i - 1) - '0';
            int digit2 = num2.charAt(num2.length() - i - 1) - '0';
            int substracter;

            digit1 = digit1 - borrow;
            if(digit1 < digit2){
                substracter = digit1 + 10 - digit2;
                borrow = 1;
            } 
            else {
                substracter = digit1 - digit2;
                borrow = 0;
            }
            difference.insert(0, substracter);
        }

        // Continue subtraction for remaining digits
        for(int i = num1.length() - num2.length() - 1; i >= 0; i--){
            int digit1 = num1.charAt(i) - '0' - borrow;
            int substracter;

            if(digit1 < 0){
                substracter = digit1 + 10;
                borrow = 1;
            } else {
                substracter = digit1;
                borrow = 0;
            }
            difference.insert(0, substracter);
        }

        // Remove leading zeros from final result
        difference = removeLeadingZeros(difference);

        // Return result with optional minus sign
        return negative_result ? "-" + difference.toString() : difference.toString();
    }

    // Method for multiplication of two integers hangling both the signs
    public AInteger multiply(AInteger other) {
        String num1 = this.integer;
        String num2 = other.integer;
    
        boolean negative_result = false;
    
        // Determine if the final result should be negative
        if ((num1.startsWith("-") && !num2.startsWith("-")) || 
            (num2.startsWith("-") && !num1.startsWith("-"))) {
            negative_result = true;
    
            // Remove negative sign from whichever number has it
            if (num1.startsWith("-")) {
                num1 = num1.substring(1);
            } else {
                num2 = num2.substring(1);
            }
        }

        // Remove leading zeros from both num1, num2 strings
        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);
    
        // Store the lengths of the modified strings num1 and num2
        int len1 = num1.length();
        int len2 = num2.length();
        
        // Result can be at most len1 + len2 digits (e.g. 99 * 99 = 9801 = 4 digits)
        int[] arr = new int[len1 + len2];
    
        // Multiply each digit of num2 with each digit of num1
        for (int i = len2 - 1; i >= 0; i--) {
            int carry = 0;
            int digit1 = num2.charAt(i) - '0';
    
            for (int j = len1 - 1; j >= 0; j--) {
                int digit2 = num1.charAt(j) - '0';
    
                // Multiply digits, add current position value and carry
                int sum = digit1 * digit2 + arr[i + j + 1] + carry;
    
                arr[i + j + 1] = sum % 10;  // Place unit digit in the correct spot
                carry = sum / 10;           // Carry for next iteration
            }
    
            // Add leftover carry to the left adjacent position
            arr[i] += carry;
        }
    
        // Build the final product string, skipping leading zeros
        StringBuilder product = new StringBuilder();
    
        int count = 0;  // Count leading zeros
        for (int i = 0; i < len1 + len2; i++) {
            if (arr[i] == 0) {
                count++;
            } else {
                break;
            }
        }
    
        // Append non-zero digits to product
        for (int i = count; i < len1 + len2; i++) {
            product.append(arr[i]);
        }
    
        // If result is all zeros (e.g. 0 * 123), make sure to return "0"
        if (count == len1 + len2) {
            product.append("0");
        }
    
        // Add negative sign if needed
        if (negative_result) {
            product.insert(0, "-");
        }
    
        String result = product.toString();
        return new AInteger(result);  // Wrap result into AInteger and return
    }    

    public AInteger division(AInteger other) {
        String num1 = this.integer;
        String num2 = other.integer;

        boolean negative_result = false;

        try{
            // Determine if the final result should be negative
            if ((num1.startsWith("-") && !num2.startsWith("-")) || 
                (num2.startsWith("-") && !num1.startsWith("-"))) {
                negative_result = true;
                
                // Remove negative sign from whichever number has it
                if (num1.startsWith("-")) {
                    num1 = num1.substring(1);
                } else {
                    num2 = num2.substring(1);
                }
            }

            // If both num1 and num2 are negative, just remove the negative signs from those
            if(num1.startsWith("-")&&num2.startsWith("-")){
                num1 = num1.substring(1);
                num2 = num2.substring(1);
            }

            // Remove leading zeros from both num1, num2 strings
            num1 = removeLeadingZeros(num1);
            num2 = removeLeadingZeros(num2);

            // Checking if the second number is 0 
            if (num2.equals("0")) {
                throw new ArithmeticException("Division by zero error");
            }

            // Storing the length of the num1 and num2 in new variables
            int len1 = num1.length();
            int len2 = num2.length();

            // If the second number is greater than return 0
            if (len1 < len2 || (len1 == len2 && num1.compareTo(num2) < 0)){
                return new AInteger("0");
            }

            // If the num1 and num2 are exactly same return -1 or 1 accordingly
            if (num1.equals(num2)) {
                return new AInteger(negative_result ? "-1" : "1");
            }

            // StringBuilder to construct the quotient (final division result)
            StringBuilder quotient = new StringBuilder();

            // A string to build the current part of the dividend as we iterate
            String current = "";

            // Loop through each digit of the dividend (num1)
            for (int i = 0; i < num1.length(); i++) {
                // Append the current digit to 'current' to simulate bringing down the next digit in long division
                current += num1.charAt(i);
            
                // Remove any leading zeros from 'current' to ensure accurate comparisons
                current = removeLeadingZeros(current);
            
                // Initialize count for how many times num2 fits into 'current'
                int count = 0;
            
                // Keep subtracting num2 from current until current becomes less than num2
                while (!current.equals("0") && !substract_strings_integer(current, num2).startsWith("-")) {
                    current = substract_strings_integer(current, num2);
                    count++; // Increment the quotient digit
                }
            
                // Append the calculated digit (count) to the quotient
                quotient.append(count);
            }

            // Remove leading zeros
            while (quotient.length() > 0 && quotient.charAt(0) == '0') {
                quotient.deleteCharAt(0);
            }

            // Add negative sign if needed
            if (negative_result) {
                quotient.insert(0, "-");
            }

            String result = quotient.toString();

            return new AInteger(result);
        }

        catch (ArithmeticException e) {
            return new AInteger(e.getMessage());
        }
    }

    // Prints the value of the integer
    public void printValue() {
        System.out.println(this.integer);
    }

}
