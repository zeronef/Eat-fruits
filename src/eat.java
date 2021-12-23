enum state {
    no, apple, orange
}

class sharedata {
    state data;

    sharedata() {
        data = state.no;
    }

    public synchronized void put(state s,String ls) {
        System.out.println(ls+"尝试向盘子放"+s);
        while (data != state.no) {
            System.out.println("放置失败，盘子中有"+data);
            try {
                wait();
            } catch (Exception e) {
            }
            System.out.println(ls+"尝试向盘子放"+s);

        }
        data = s;
        System.out.println("放置成功，"+ls+"向盘子放了"+s);
        //System.out.println("state:" + data);
        notifyAll();
    }

    public synchronized void get(state s,String ls) {
        System.out.println(ls+"尝试从盘中拿"+s);
        while(data!=s){
            System.out.println("拿取失败，盘子中没有"+s);
            try {
                wait();
            } catch (Exception e) {
            }
            System.out.println(ls+"尝试从盘中拿"+s);
        }
        data=state.no;
        System.out.println("拿取成功，"+ls+"从盘中拿了"+s);
        //System.out.println("state:" + data);
        notifyAll();
    }
}

class parent extends Thread {
    sharedata d;
    String s;

    parent(sharedata d, String s) {
        this.d = d;
        this.s = s;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            if (s == "爸爸") {
                d.put(state.apple,s);
            } else {
                d.put(state.orange,s);
            }
        }

    }

}

class children extends Thread {
    sharedata d;
    String s;

    children(sharedata d, String s) {
        this.d = d;
        this.s = s;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            if (s == "儿子") {
                d.get(state.orange,s);
            } else {
                d.get(state.apple,s);
            }
        }

    }

}

