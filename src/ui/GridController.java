package ui;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GridController implements Initializable{

    @FXML
    private Label player1_name;

    @FXML
    private Label player2_name;

    @FXML
    private Label player1_score;

    @FXML
    private Label player2_score;
    
    @FXML
    private Label round_label;

    @FXML
    private Label game_state_list;

    @FXML
    private GridPane grid_box;

    @FXML
    private Label l1;

    @FXML
    private Label l5;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;
    
    int count = 1;
    String[] game_history = new String[6];
    int history_count = 0;
  
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		grid_box.getStyleClass().add("grid-pane-hover");
		applyEffect();
    	for(Node child : grid_box.getChildren()) {
    		if(!child.getClass().toString().equalsIgnoreCase("class javafx.scene.Group") ) { 
		    		Label label = (Label)child;
		    		label.textProperty().addListener(new ChangeListener<String>() {
						@Override
						public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
							// TODO Auto-generated method stub
							String state = gameState();
							reArray();
							if(state.equalsIgnoreCase("X") || state.equalsIgnoreCase("O")) {
								if(state.equalsIgnoreCase("X")) {
									game_history[history_count] = "X";
									Integer score = Integer.parseInt(player1_score.getText());
									player1_score.setText(String.valueOf(score+1));
									
								}else {
									game_history[history_count] = "O";
									Integer score = Integer.parseInt(player2_score.getText());
									player2_score.setText(String.valueOf(score+1));
								}
								history_count += 1;
								round_label.setText("Round "+(history_count+1));
								updateLabels();
								appendGameHistory();
							}else if(state.equalsIgnoreCase("draw")){
								game_history[history_count] = "draw";
								history_count += 1;
								round_label.setText("Round "+(history_count+1));
								updateLabels();
								appendGameHistory();
							}
							
						}
					});
		    		
	    		}
    		
    		}
	}
    
    // main activity of ticking the board
    @FXML
    public void gridClick(MouseEvent e) {
    	Node cell = (Node)e.getTarget();
    	Integer col = GridPane.getColumnIndex(cell);
    	Integer row = GridPane.getRowIndex(cell);
    	ObservableList<Node> childrens =grid_box.getChildren();
    	for(Node child : childrens) {
    		if(!child.getClass().toString().equalsIgnoreCase("class javafx.scene.Group") ) { 
	    		if(GridPane.getRowIndex(child)==row && GridPane.getColumnIndex(child)==col){ 
		    		Label label = (Label)child;
		    		if(label.getText().isEmpty()) {
		    			setText(label);
		    			applyEffect();
		    		}else {
		    			System.out.println("Already filled up");
		    		}
	    		}
    	}
    		
    	}
    	
    	
    	
    }
    
    // a function that updates the grid by placing corresponding "x" or "o" tick
    private void setText(Label label) {
    	if(count % 2 == 0) {
			label.setStyle("-fx-background-color:red");
    		label.setText("O");
		}else {
			label.setStyle("-fx-background-color:yellow");
    		label.setText("X");
		}
	count = count + 1;
    }
	
    //this function checks if a winner has emerged or draw or if the game is still open
    private String gameState() {
    	//checking row wins
    	if(l1.getText().equalsIgnoreCase("X") && l2.getText().equalsIgnoreCase("X") 
    			&& l3.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l1.getText().equalsIgnoreCase("O") && l2.getText().equalsIgnoreCase("O") 
    			&& l3.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	if(l4.getText().equalsIgnoreCase("X") && l5.getText().equalsIgnoreCase("X") 
    			&& l6.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l4.getText().equalsIgnoreCase("O") && l5.getText().equalsIgnoreCase("O") 
    			&& l6.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	if(l7.getText().equalsIgnoreCase("X") && l8.getText().equalsIgnoreCase("X") 
    			&& l9.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l7.getText().equalsIgnoreCase("O") && l8.getText().equalsIgnoreCase("O") 
    			&& l9.getText().equalsIgnoreCase("O")) {
    		return "O";
    	
    		// END OF ROW WINS
    	}
    	
    	// COLUMN WINS
    	if(l1.getText().equalsIgnoreCase("X") && l4.getText().equalsIgnoreCase("X") 
    			&& l7.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l1.getText().equalsIgnoreCase("O") && l4.getText().equalsIgnoreCase("O") 
    			&& l7.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	if(l2.getText().equalsIgnoreCase("X") && l5.getText().equalsIgnoreCase("X") 
    			&& l8.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l2.getText().equalsIgnoreCase("O") && l5.getText().equalsIgnoreCase("O") 
    			&& l8.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	if(l3.getText().equalsIgnoreCase("X") && l6.getText().equalsIgnoreCase("X") 
    			&& l9.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l3.getText().equalsIgnoreCase("O") && l6.getText().equalsIgnoreCase("O") 
    			&& l9.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	//DIAGONAL CHECK
    	if(l1.getText().equalsIgnoreCase("X") && l5.getText().equalsIgnoreCase("X") 
    			&& l9.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l1.getText().equalsIgnoreCase("O") && l5.getText().equalsIgnoreCase("O") 
    			&& l9.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	if(l3.getText().equalsIgnoreCase("X") && l5.getText().equalsIgnoreCase("X") 
    			&& l7.getText().equalsIgnoreCase("X")) {
    		return "X";
    	}else if(l3.getText().equalsIgnoreCase("O") && l5.getText().equalsIgnoreCase("O") 
    			&& l7.getText().equalsIgnoreCase("O")) {
    		return "O";
    	}
    	
    	//AFTER CHECKING AND NO WINNER CHECK IF THERE ARE EMPTY GRID SPOT
    	ObservableList<Node> childrens =grid_box.getChildren();
    	for(Node child : childrens) {
    		if(!child.getClass().toString().equalsIgnoreCase("class javafx.scene.Group") ) { 
		    		Label label = (Label)child;
		    		if(label.getText().isEmpty()) {
		    			return "open";
		    			//this denotes there is still an empty slot for users to play on
		    		}
	    		}
    	}
    	return "draw";
    }
    
    // this function reset all label
    private void updateLabels() {
    	ObservableList<Node> childrens =grid_box.getChildren();
    	for(Node child : childrens) {
    		if(!child.getClass().toString().equalsIgnoreCase("class javafx.scene.Group") ) { 
		    		Label label = (Label)child;
		    		label.setStyle("-fx-background-color:transparent");
		    		label.setText("");
	    		}
    	}
    }
    
    // this function insert game_history result into label
    private void appendGameHistory() {
    	String history = "[";
    	for(int x = 0; x<game_history.length; x++) {
    		if(game_history[x] != null)
    			if(x == game_history.length-2) {
    				history = history.concat(game_history[x]);
    			}else {
    				history = history.concat(game_history[x]+",");
    			}		
    	}
    	game_state_list.setText(history);
    }
    
    private void reArray() {
    	if(game_history[game_history.length-1] !=null) {
			game_history = new String[6];
			history_count = 0;
		
		}
    }
    
    private void applyEffect() {
    	if(count % 2 == 0) {
    		player2_name.getStyleClass().set(1,"active-player-label");
    		player1_name.getStyleClass().set(1, "inactive-player-label");
    		dropShadow(player2_name);
    		player1_name.setEffect(null);
    		
		}else {
			player1_name.getStyleClass().set(1,"active-player-label");
    		player2_name.getStyleClass().set(1, "inactive-player-label");
    		dropShadow(player1_name);
    		player2_name.setEffect(null);
		}
    }
    private void dropShadow(Label label) {
    	Effect effect= new DropShadow(BlurType.TWO_PASS_BOX, Color.BLACK, 0.22, 2.17, -2.0,1.0);
        label.setEffect(effect);
       
    }
}
