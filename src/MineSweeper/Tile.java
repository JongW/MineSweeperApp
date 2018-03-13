package MineSweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private Boolean hasBomb;
    private Text text = new Text();
    private int x,y;
    private Boolean isOpen = false;
    private Boolean game = true;
    private Boolean hasFlag = false;

    private Rectangle border = new Rectangle(20,20);

    public Tile(int x, int y, Boolean hasBomb){
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        //set bomb
        if(hasBomb){
            text.setText("X");
        }
        else {
            text.setText("");
        }
//      debug purposes
//      text.setVisible(true);
        text.setVisible(false);

        //set x & y co-ordinate
        setTranslateX(x*20);
        setTranslateY(y*20);

        //set border
        border.setFill(Color.LIGHTGREY);
//      debug purposes
//      border.setFill(null);
        border.setStroke(Color.BLACK);

        getChildren().addAll(border, text);

    }

    public void openTile(){
        if(isOpen){
            return;
        }
        else{
            if(hasBomb){
                border.setFill(Color.RED);
                text.setVisible(true);
                game = false;
            }
            else{
                isOpen = true;
                border.setFill(null);
                text.setVisible(true);
            }
        }
    }

    public boolean hasFlag(){
        return hasFlag;
    }

    public void setFlag(boolean setFlag){
        hasFlag = setFlag;
    }
    public Text getText(){
        return text;
    }

    public Rectangle getTileBorder() {
        return border;
    }

    public String getString(){
        return text.getText();
    }

    public boolean getOpen(){
        return isOpen;
    }
    public boolean getGame(){
        return game;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean hasBomb(){
        return hasBomb;
    }

    public void setBomb(boolean setBomb){
        hasBomb = setBomb;
    }

    public void setText(String text){
        this.text.setText(text);

        switch(text){
            case "0":
                this.text.setText(null);
            case "1" :
                this.text.setFill(Color.BLUE);
                break;
            case "2" :
                this.text.setFill(Color.GREEN);
                break;
            case "3" :
                this.text.setFill(Color.RED);
                break;
            case "4":
                this.text.setFill(Color.DARKBLUE);
                break;
            case "5":
                this.text.setFill(Color.PURPLE);
                break;
        }
    }

}
