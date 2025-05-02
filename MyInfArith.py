import subprocess

def build_with_ant():
    subprocess.run(["ant", "clean", "compile"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)


def run_java_class(args):
    cmd = ["java", "-cp", "build", "arbitraryarithmetic.MyInfArith"] + args
    result = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

    print(result.stdout.strip())

def main():
    user_input = input("Enter input in format: <type> <operation> <num1> <num2>\n")

    tokens = user_input.strip().split()
    if len(tokens) != 4:
        print(" Invalid input. Use: <type> <operation> <num1> <num2>")
        return

    # Step 1: Build with Ant
    build_with_ant()

    # Step 2: Run the MyInfArith class
    run_java_class(tokens)

main()
