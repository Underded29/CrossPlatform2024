package task2;

public class Main {

    float pi = 3.1415F;
    private double randomNum = 534.42111;

    int e = 432;

    public static void main(String[] args) {
        Main main = new Main();
        inspectObject inspect = new inspectObject();
        inspect.inspectObject(main);
    }

    public void functionTest1(){
        System.out.println("RANDOM");
    }

    public float functionTest2(int a, float b){
        float c = a + b;
        return c;
    }

    private double functionTest3(){
        System.out.println("строка нової функції");
        return 5.2;
    }

    private float functionTest4() {
        System.out.println("Була визвана нова функція");
        return 42.44324234F;
    }

}

