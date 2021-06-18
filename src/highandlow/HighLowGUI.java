package highandlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighLowGUI extends JPanel{

    public static void main(String[] args) {
        JFrame window = new JFrame("Null Layout Demo");
        HighLowGUI content = new HighLowGUI();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        //TODO research method pack()
        window.setResizable(false);
        window.setVisible(true);
    }

    public HighLowGUI(){
        setBackground(new Color(130,50,40));
        setLayout(new BorderLayout(3,3));
        CardPanel board = new CardPanel();  // where the cards are drawn.
        add(board, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();  // The subpanel that holds the buttons.
        buttonPanel.setBackground(new Color(220,220,180));
        add(buttonPanel, BorderLayout.SOUTH);

        JButton higher = new JButton("Higher");
        higher.addActionListener(board);    // The CardPanel listens for events.
            //TODO recap the meaning of the param.
        buttonPanel.add(higher);

        JButton lower = new JButton("Lower");
        lower.addActionListener(board);    // The CardPanel listens for events.
        //TODO recap the meaning of the param.
        buttonPanel.add(lower);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(board);
        buttonPanel.add(newGame);

        setBorder(BorderFactory.createLineBorder((new Color(130,50,40)), 3));
    } // end: constructor

    /**
     * A nested class that displays the cards and does all the work of keeping track of the states
     * and responding to user events.
     */
    private class CardPanel extends JPanel implements ActionListener{

        Deck deck;              // A deck of cards to be used in the game.
        Hand hand;              // The cards that have been dealt.
        String message;         // A message drawn on the canvas, which changes to reflect the state of the game.
        boolean gameInProgress; // Set to true when a game begins and to false when the game ends.
        Font bigFont;           // Font that will be used to display the message.
        Font smallFont;         // Font that will be used to draw the cards.

        /**
         * Constructor creates fonts, sets the foreground and background colours and
         * starts the first game. It also sets a "preferred size" for the panel.
         * This size is respected when the program is run as an application, since
         * the pack() method is used to set the size of the window.
         */
        CardPanel(){
            setBackground(new Color(0,120,0));
            setForeground(Color.GREEN);
            smallFont = new Font("Suns-serif",Font.BOLD, 12);
            bigFont = new Font("Suns-serif", Font.BOLD, 14);
            setPreferredSize(new Dimension(370, 150));
            doNewGame();
        }

        /**
         * Respond when the user clicks on a button by calling the appropriate method.
         * Note: the buttons are created and listening is set up in the constructor of the HighLowPanel class.
         * @param evt ActionListener
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
            if(command.equals("Higher"))
                doHigher();
            else if(command.equals("Lower"))
                doLower();
            else if(command.equals("New Game"))
                doNewGame();
        }

        /**
         * Called by actionPerformed() when user clicks "Higher" button.
         * Check the user's prediction.
         * Game ends if user guessed wrong or if the user has made three correct predictions.
         */
        void doHigher(){
            if(!gameInProgress){
                message = "Click \"New Game\" to start a new game.";
                repaint();
                return;
            }
            hand.addCard(deck.dealCard());      // Deal a card to the hand.
            int cardCt = hand.getCardCount();
            Card thisCard = hand.getCard(cardCt - 1);   // Card just dealt.
            Card prevCard = hand.getCard(cardCt - 2);   // the previous card.
            if(thisCard.getValue() < prevCard.getValue()){
                gameInProgress = false;
                message = "Too bad! You lose.";
            } else if (thisCard.getValue() == prevCard.getValue()){
                gameInProgress = false;
                message = "Too bad! You lose on ties.";
            } else if(cardCt == 4){
                gameInProgress = false;
                message = "You win! You made three correct guesses.";
            } else {
                message = "Got it right! Try for " + cardCt + ".";
            }
            repaint();
        } // end doHigher()

        /**
         * Called by actionPerformed() when user clicks "Lower" button.
         * Check the user's prediction.
         * Game ends if user guessed wrong or if the user has made three correct predictions.
         */
        void doLower(){
            if(!gameInProgress){
                message = "Click \"New Game\" to start a new game.";
                repaint();
                return;
            }
            hand.addCard(deck.dealCard());      // Deal a card to the hand.
            int cardCt = hand.getCardCount();
            Card thisCard = hand.getCard(cardCt - 1);   // Card just dealt.
            Card prevCard = hand.getCard(cardCt - 2);   // the previous card.
            if(thisCard.getValue() > prevCard.getValue()){
                gameInProgress = false;
                message = "Too bad! You lose.";
            } else if (thisCard.getValue() == prevCard.getValue()){
                gameInProgress = false;
                message = "Too bad! You lose on ties.";
            } else if(cardCt == 4){
                gameInProgress = false;
                message = "You win! You made three correct guesses.";
            } else {
                message = "Got it right! Try for " + cardCt + ".";
            }
            repaint();
        } // end doLower()

        /**
         * Called by the constructor, and called by actionPerformed()
         * if the user clicks the "New Game" button. Start a new game.
         */
        void doNewGame(){
            if(gameInProgress){
                message = "You still have to finish this game!";
                repaint();
                return;
            }
            deck = new Deck();                  // Create the deck and hand to user for this game.
            hand = new Hand();
            deck.shuffle();
            hand.addCard( deck.dealCard() );    // Deal the first card into the hand.
            message = "Is the next card higher or lower?";
            gameInProgress = true;
            repaint();
        } // end doNewGame()

        /**
         * This method draws the message at the bottom of the panel,
         * and it draws all of the dealt cards spread out across the canvas.
         * If the game is in progress an extra card is drawn face down
         * representing the card to be dealt next.
         * @param g Graphics context
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(bigFont);
            g.drawString(message,10,135);
            g.setFont(smallFont);
            int cardCt = hand.getCardCount();
            for(int i = 0; i < cardCt; i++)
                drawCard(g, hand.getCard(i), 10 + i * 90, 10);
            if(gameInProgress)
                drawCard(g, null, 10 + cardCt * 90, 10);
        }   // end paintComponent()

        void drawCard(Graphics g, Card card, int x, int y){
            if(card == null){
                // Draw a face-down card
                g.setColor(Color.BLUE);
                g.fillRect(x,y,80,100);
                g.setColor(Color.WHITE);
                g.drawRect(x + 3, y + 3, 73, 93);
                g.drawRect( x + 4, y + 4, 71, 91);
            }
            else{
                g.setColor(Color.WHITE);
                g.fillRect(x,y,80,100);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, 79, 99);
                g.drawRect( x + 1, y + 1, 77, 97);
                if(card.getSuit() == Card.DIAMONDS || card.getSuit() == Card.HEARTS)
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.BLACK);
                g.drawString(card.getValueAsString(), x + 10, y + 30);
                g.drawString("of", x+10, y+50);
                g.drawString(card.getSuitAsString(), x+10, y+70);
            }
        } // end drawCard()
    } // end nested class CardPanel
}
