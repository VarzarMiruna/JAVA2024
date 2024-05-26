package home;

public class Game {
    private Player p1,p2;
    private String name;
    int[][] position = new int[15][15];
    boolean won=false;
    int turn;
    public Game(String name) {
        this.name = name;
    }
    public void setPlayer1(Player p){
        p1=p;
    }
    public void setPlayer2(Player p){
        p2=p;
    }
    public boolean isGameReady(){

        return p1!=null && p2!=null;
    }
    public String getName(){

        return name;
    }
    public boolean isFree(int lin, int col){
        if(lin>15||col>15) return false;
        return position[lin][col]==0;
    }
    public boolean setPiece(int lin, int col, int playerNr){
        lin--;
        col--;
        if(playerNr==1){
            p1.thread.place(lin,col,p1.color);
            p2.thread.place(lin,col,p1.color);
        }
        else {
            p1.thread.place(lin,col,p2.color);
            p2.thread.place(lin,col,p2.color);
        }
        position[lin][col]=playerNr;
        return checkLine(lin, col, playerNr);
    }
    public boolean checkLine(int lin, int col, int playerNr){
        int i;
        for(i=1;i<=4&&(lin-i)>0;i++)
            if(position[lin-i][col]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(lin-i)>0&&(col-i)>0;i++)
            if(position[lin-i][col-i]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(lin-i)>0&&(col+i)<16;i++)
            if(position[lin-i][col+i]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(col+i)<16;i++)
            if(position[lin][col+i]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(lin+i)<16&&(col+i)<16;i++)
            if(position[lin+i][col+i]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(lin+i)<16;i++)
            if(position[lin+i][col]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(lin+i)<16&&(col-i)>0;i++)
            if(position[lin+i][col-i]!=playerNr)
                break;
        if(i==5) return true;
        for(i=1;i<=4&&(col-i)>0;i++)
            if(position[lin][col-i]!=playerNr)
                break;
        if(i==5) return true;
        return false;
    }
    public void setWon(String response){
        p1.thread.won(response);
        p2.thread.won(response);
    }
    public void setTurn(int playerNr){
        turn=playerNr;
        if(playerNr==1){
            p1.timer.running=true;
            p2.timer.running=false;
        }
        else{
            p2.timer.running=true;
            p1.timer.running=false;
        }
    }
    public boolean isTurn(int playerNr){
        return playerNr==turn;
    }
    public boolean outOfTime(int playerNr){
        if(playerNr==1)
            return p1.timer.done;
        return p2.timer.done;
    }
    public Player getPlayer(int playerNr){
        if(playerNr==1)
            return p1;
        else return p2;
    }
}