import sys
import subprocess

def build_with_ant():
    subprocess.run(["ant", "clean", "compile"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

def run_java_class(args):
    cmd = ["java", "-cp", "build", "arbitraryarithmetic.MyInfArith"] + args
    result = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    print(result.stdout.strip())

def main():
    if len(sys.argv) != 5:
        print("Usage: python3 MyInfArith.py <type> <operation> <num1> <num2>")
        return

    build_with_ant()
    run_java_class(sys.argv[1:])

if __name__ == "__main__":
    main()
