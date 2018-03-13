package MineSweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

public class Tile extends StackPane {

    private Boolean hasBomb;
    private Text text = new Text();
    private int x,y;
    private Boolean isOpen = false;
    private Boolean game = true;

    public Tile(int x, int y, Boolean hasBomb){
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        Rectangle border = new Rectangle(20,20);

        //set bomb
        if(hasBomb){
            text.setText("X");
        }
        else {
            text.setText("");
        }

        text.setVisible(false);

        //set x & y co-ordinate
        setTranslateX(x*20);
        setTranslateY(y*20);

        //set border
        border.setFill(null);
        border.setStroke(Color.LIGHTGREY);

        getChildren().addAll(border, text);

    }

    public void openTile(){
        if(isOpen){
            return;
        }
        else{
            if(hasBomb){
                game = false;
            }
            else{
                isOpen = true;
                text.setVisible(true);
            }
        }
    }

    public Text getText(){
        return text;
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

    public void setText(String text){
        this.text.setText(text);

        switch(text){
//            case "0":
//                this.text.setText("");
//                this.text.setVisible(false);
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
                this.text.setFill(Color.BEIGE);
                break;
        }
    }

}
