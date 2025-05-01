package arbitraryarithmetic;

public class MyInfArith {
    public static void main(String[] args) {
        String type = args[0];
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];

        if((!(type == "int" || type == "float")) ||
        (!(operation == "add" || operation == "sub" || operation == "mul" || operation == "div")) ||
        (args.length != 4)){
            System.out.println("Usage: java MyInfArith int/float add/sub/mul/div <number1> <number2>");
            return;
        }

        if(type.equals("int")){
            AInteger first = new AInteger(num1);
            AInteger second = new AInteger(num2);
            switch(operation) {
                case "add":
                    System.out.println(first.add(second));
                    break;
                case "sub":
                    System.out.println(first.substract(second));
                    break;
                case "mul":
                    System.out.println(first.multiply(second));
                    break;
                case "div":
                    System.out.println(first.division(second));
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;    
            }
        }
        else if(type.equals("float")){
            AFloat first = new AFloat(num1);
            AFloat second = new AFloat(num2);
            switch(operation) {
                case "add":
                    System.out.println(first.add_Float(second));
                    break;
                case "sub":
                    System.out.println(first.substract_Float(second));
                    break;
                case "mul":
                    System.out.println(first.multiply_Float(second));
                    break;
                case "div":
                    System.out.println(first.division_Float(second));
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;    
            }
        }
    }
}
