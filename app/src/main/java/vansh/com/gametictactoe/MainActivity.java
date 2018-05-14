package vansh.com.gametictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //0 for yellow 1 for red
    int activePlayer = 0;
    boolean gameActive=true;
    //2 means unplayed
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningpos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedCounter] == 2&&gameActive==true) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(180f).setDuration(300);
        }
        for (int[] winning : winningpos) {
            if (gamestate[winning[0]] == gamestate[winning[1]] && gamestate[winning[1]] == gamestate[winning[2]] && gamestate[winning[0]] != 2)

            {
                gameActive=false;
                String winner = "Red";
                if (gamestate[winning[0]] == 0)
                    winner = "Yellow";
                TextView winmsg = (TextView) findViewById(R.id.winmsg);
                winmsg.setText(winner + " has won!!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
                layout.setVisibility(View.VISIBLE);
            }
            else {
                boolean gameisover = true;
                for (int i = 0; i < 9; i++) {
                    if (gamestate[i] == 2)
                        gameisover = false;
                }
                if(gameisover) {
                    TextView winmsg = (TextView) findViewById(R.id.winmsg);
                    winmsg.setText("Match Draw");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
                    layout.setVisibility(View.VISIBLE);
                }

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void playAgain(View view) {
        gameActive=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int g = 0; g < 9; g++) {
            gamestate[g] = 2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.playagain);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
}