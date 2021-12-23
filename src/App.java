public class App {
    public static void main(String[] args) throws Exception {
        sharedata d = new sharedata();
        parent dad = new parent(d, "爸爸");
        parent mom = new parent(d, "妈妈");
        children son = new children(d, "儿子");
        children daughter = new children(d, "女儿");
        dad.start();
        mom.start();
        son.start();
        daughter.start();
    }
}
