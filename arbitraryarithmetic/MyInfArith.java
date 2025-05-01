package arbitraryarithmetic;

public class MyInfArith {
    public static void main(String[] args) {
        if((args.length != 4)){
            System.out.println("Usage: java MyInfArith int/float add/sub/mul/div num1 num2");
            return;
        }
        String type = args[0];
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];

        if((!(type.equals("int") || type.equals("float"))) ||
        (!(operation.equals("add") || operation.equals("sub") || operation.equals("mul") || operation.equals("div")))){
            System.out.println("Usage: java MyInfArith int/float add/sub/mul/div num1 num2");
            return;
        }     

        if(type.equals("int")){
            AInteger first = new AInteger(num1);
            AInteger second = new AInteger(num2);
            AInteger result = new AInteger();
            switch(operation) {
                case "add":
                    result = first.add(second);
                    break;
                case "sub":
                    result = first.substract(second);
                    break;
                case "mul":
                    result = first.multiply(second);
                    break;
                case "div":
                    result = first.division(second);
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;    
            }
            result.printValue();
        }
        else if(type.equals("float")){
            AFloat first = new AFloat(num1);
            AFloat second = new AFloat(num2);
            AFloat result = new AFloat();
            switch(operation) {
                case "add":
                    result = first.add_Float(second);
                    break;
                case "sub":
                    result = first.substract_Float(second);
                    break;
                case "mul":
                    result = first.multiply_Float(second);
                    break;
                case "div":
                    result = first.division_Float(second);
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;    
            }
            result.printValue();
        }
    }
}
