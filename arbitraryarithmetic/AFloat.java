package myJava.arbitraryarithmetic;

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
}
