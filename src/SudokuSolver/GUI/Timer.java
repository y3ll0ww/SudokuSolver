package SudokuSolver.GUI;

public class Timer {
    static long start = 0L;
    static long finish = 0L;
    static long idle = 0L;
   // static long timeElapsed = 0L;
    static boolean pause = false;

    static public void start(){
        start = System.currentTimeMillis();
    }
    public static long track(){
        if(start > 0L && finish == 0L) {
            return System.currentTimeMillis() - start - idle;
        } else if(finish > 0L){
            return finish - start - idle;
        } else {
            return 0L;
        }
    }
    public static void go(){
     //   timeElapsed = 0L;
        idle += System.currentTimeMillis() - finish;
        finish = 0L;
        pause = false;
    }
    static public void end(){
        finish = System.currentTimeMillis();
    }
    static public void pause(){
        pause = true;
    }
    static public void reset(){
        start = 0L;
        finish = 0L;
        idle = 0L;
        pause = false;
    }
    static public boolean isPaused(){
        return pause;
    }
    public static long getStartTime(){
        return start;
    }
  //  public static long getElapsedTime(){
  //      return finish - start;
   // }
}
