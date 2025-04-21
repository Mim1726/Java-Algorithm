import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int r=sc.nextInt();
        int c=sc.nextInt();

        sc.nextLine();

        int[][] grid=new int[r][c];

        for(int i=0;i<r;i++){
            String line=sc.nextLine();
            String[] values=line.split(",");
            for(int j=0;j<c;j++){
                grid[i][j]=Integer.parseInt(values[j]);
            }
        }

        int perimeter=0;

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j]==1){
                    if(i==0 || grid[i-1][j]==0) perimeter++;
                    if(i==r-1 || grid[i+1][j]==0) perimeter++;
                    if(j==0 || grid[i][j-1]==0) perimeter++;
                    if(j==c-1 || grid[i][j+1]==0) perimeter++;
                }
            }
        }

        System.out.println(perimeter);
    }
}
