import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();

        int[] notes={1000,500,200,100,50,20,10,5,2,1};
        int cnt=0;
        for(int note:notes){
            if(v==0){
                break;
            }
            int div=v/note;
            cnt+=div;
            v-=div*note;
        }

        System.out.println(cnt);
        sc.close();
    }
}
