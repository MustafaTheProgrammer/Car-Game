import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.ArrayList;

class cargame extends JFrame implements KeyListener{
    public static int width = 48;
    public static int height = 36;
    public static String[] pixels = new String[width * height];
    public static String black = "\u001B[47m  ";
    public static String white = "\u001B[100m  ";
    public static String red = "\u001B[101m  ";
    public static String orange = "\u001B[103m  ";
    public static String green = "\u001B[102m  ";
    public static String blue = "\u001B[46m  ";
    public static boolean leftkey;
    public static boolean upkey;
    public static boolean rightkey;
    public static boolean downkey;
    public static float timer;
    public static boolean timeron = false;
    public static ArrayList<Float> tiles = new ArrayList<Float>();
    public static ArrayList<Float> points = new ArrayList<Float>();
    public static int pointsactive;

    public static float playerx = 9;
    public static float playery = -12;
    public static float camx;
    public static float camy;
    public static float playerv;
    public static float playermaxv = 2;
    public static float playerdir = -1.5707964f;
    public static float playerdir2 = 270;

    public static char[][] level = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                                    {0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
                                    {0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0},
                                    {0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 3, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0},
                                    {3, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                                    {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 3, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                                    {0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                                    {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                                    {0, 0, 0, 1, 1, 1, 0, 3, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}};

    public static int[][] tehe = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    
    public static int[][] car0 = {{1, 1, 1, 0, 1, 1, 1, 0, 0},
                                  {1, 1, 1, 0, 1, 1, 1, 0, 0},
                                  {2, 2, 2, 2, 2, 2, 2, 0, 0},
                                  {2, 2, 2, 2, 2, 2, 1, 2, 0},
                                  {2, 2, 2, 2, 2, 2, 1, 2, 2},
                                  {2, 2, 2, 2, 2, 2, 1, 2, 0},
                                  {2, 2, 2, 2, 2, 2, 2, 0, 0},
                                  {1, 1, 1, 0, 1, 1, 1, 0, 0},
                                  {1, 1, 1, 0, 1, 1, 1, 0, 0}};

    public static int[][] car90 = {{0, 0, 0, 0, 2, 0, 0, 0, 0},
                                   {0, 0, 0, 2, 2, 2, 0, 0, 0},
                                   {1, 1, 2, 1, 1, 1, 2, 1, 1},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1},
                                   {0, 0, 2, 2, 2, 2, 2, 0, 0},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1}};

    public static int[][] car45 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                   {0, 0, 1, 0, 1, 2, 2, 2, 2, 2, 0, 0, 0},
                                   {0, 1, 1, 1, 2, 2, 1, 1, 2, 2, 0, 0, 0},
                                   {0, 0, 1, 2, 2, 2, 2, 1, 1, 2, 0, 0, 0},
                                   {0, 0, 2, 2, 2, 2, 2, 2, 1, 2, 0, 0, 0},
                                   {0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0},
                                   {0, 0, 0, 2, 2, 2, 2, 2, 2, 1, 1, 1, 0},
                                   {0, 0, 0, 0, 2, 2, 2, 2, 1, 0, 1, 0, 0},
                                   {0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public static int[][] car22 = {{0, 0, 0, 0, 1, 1, 1, 0, 0},
                                   {1, 1, 1, 0, 1, 1, 1, 0, 0},
                                   {1, 1, 1, 2, 2, 2, 2, 0, 0},
                                   {2, 2, 2, 2, 2, 1, 2, 2, 2},
                                   {2, 2, 2, 2, 2, 1, 1, 2, 0},
                                   {2, 2, 2, 2, 2, 2, 1, 2, 0},
                                   {2, 2, 2, 2, 2, 2, 2, 0, 0},
                                   {2, 2, 2, 2, 1, 1, 1, 0, 0},
                                   {1, 1, 1, 0, 1, 1, 1, 0, 0},
                                   {1, 1, 1, 0, 0, 0, 0, 0, 0}};

    public static int[][] car67 = {{0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
                                   {0, 0, 0, 0, 2, 2, 2, 0, 0, 0},
                                   {0, 1, 1, 2, 1, 1, 2, 2, 1, 1},
                                   {0, 1, 1, 2, 2, 1, 1, 2, 1, 1},
                                   {0, 1, 1, 2, 2, 2, 2, 2, 1, 1},
                                   {0, 0, 2, 2, 2, 2, 2, 2, 0, 0},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1, 0},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1, 0},
                                   {1, 1, 2, 2, 2, 2, 2, 1, 1, 0},
                                   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public static int[][] goal = {{1, 4, 1, 4, 1, 4},
                                  {4, 1, 4, 1, 4, 1},
                                  {1, 4, 1, 4, 1, 4},
                                  {4, 1, 4, 1, 4, 1},
                                  {1, 4, 1, 4, 1, 4},
                                  {4, 1, 4, 1, 4, 1},
                                  {1, 4, 1, 4, 1, 4},
                                  {4, 1, 4, 1, 4, 1},
                                  {1, 4, 1, 4, 1, 4},
                                  {4, 1, 4, 1, 4, 1},
                                  {1, 4, 1, 4, 1, 4},
                                  {4, 1, 4, 1, 4, 1}};
    
    public cargame(){
        this.setTitle("car game");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        gameLoop();
    }

    static void gameLoop(){
        for (int i = 0; i < level.length; i++){
            for (int j = 0; j < level[0].length; j++){
                if (level[i][j] == 0){
                    addTile(j*6, -(i*6)+1, 0);
                }
                else if (level[i][j] == 1){
                    addTile(j*6, -(i*6)+1, 1);
                }
                else if (level[i][j] == 2){
                    addTile(j*6, -(i*6)+1, 2);
                }
                else if (level[i][j] == 3){
                    addTile(j*6, -(i*6)+1, 0);
                    addPoint(j*6 + 1, -(i*6)+1, 0);
                }
            }
        }
        
        while (1==1){
            System.out.print("\033[H\033[2J");
            System.out.flush();

            for (int i = 0; i < pixels.length; i++){
                pixels[i] = white;
            }

            //start timer
            if (rightkey || leftkey || upkey || downkey){
                if (timer == 0 && timeron == false){
                    timeron = true;
                }
            }

            //timer
            if (timeron){
                timer += 0.040;
            }

            //player physics
            playermaxv = 0.5f;
            for (int i = 0; i < tiles.size(); i += 3){
                if (tiles.get(i) + 5 >= playerx && tiles.get(i) <= playerx + 8 && tiles.get(i+1) + 5 >= playery && tiles.get(i+1) <= playery + 8){
                    playermaxv = 2;
                    break;
                }
            }

            if (rightkey){
                playerdir += Math.PI/8;
                playerdir2 += 22.5;
            }
            if (leftkey){
                playerdir -= Math.PI/8;
                playerdir2 -= 22.5;
            }

            if (upkey){
                if (playerv < playermaxv){
                    playerv += 0.5;
                }
            }
            else{
                if (playerv > 0){
                    playerv -= 0.5;
                }
            }

            if (downkey){
                if (playerv > -playermaxv){
                    playerv -= 0.5;
                }
            }
            else{
                if (playerv < 0){
                    playerv += 0.5;
                }
            }

            if (playerx + camx <= (width-1)/2 || playerx + 8 + camx >= ((level[0].length*6))-((width-1)/2)){
                playerx += playerv * Math.cos(playerdir);
            }
            else{
                camx += playerv * Math.cos(playerdir);
            }
            
            if (playerx + 8 > width-1){
                playerx = (width-1)-8;
            }
            if (playerx < 0){
                playerx = 0;
            }

            if (playery + camy >= -((height-1)/2) || playery + 8 + camy <= -((level.length * 6)-12) + (height-1)/2){
                playery += playerv * Math.sin(playerdir);
            }
            else{
                camy += playerv * Math.sin(playerdir);
            }

            
            if (playery + 8 > 0){
                playery = -8;
            }
            if (playery < -(height-1)){
                playery = -(height-1);
            }

            if (playerdir2 % 360 == 0 || playerdir2 % -360 == 0){
                playerdir2 = 0;
            }

            //handling points
            for (int i = 0; i < points.size(); i += 3){
                if (playerx + 8 + camx >= points.get(i) - 8 && playerx + camx <= points.get(i) + 10 && playery + 8 + camy >= points.get(i+1) - 8 && playery + camy <= points.get(i+1) + 10){
                    if (points.get(i+2) == 0){
                        points.set(i+2, 1.0f);
                        pointsactive++;
                    }
                }
            }

            //end timer
            for (int i = 0; i < tiles.size(); i += 3){
                if (tiles.get(i) + 5 - camx >= playerx && tiles.get(i) - camx <= playerx + 8 && tiles.get(i+1) + 11 - camy >= playery && tiles.get(i+1) - camy <= playery + 8){
                    if (tiles.get(i+2) == 2){
                        if (timeron == true && pointsactive == 6){
                            timeron = false;
                        }
                    }
                }
            }

            //rendering
            
            for (int i = 0; i < tiles.size(); i += 3){
                if (tiles.get(i+2) == 0){
                    rect(tiles.get(i) - camx, tiles.get(i+1) - camy, 6, 6, green);
                }
                else if (tiles.get(i+2) == 1){
                    rect(tiles.get(i) - camx, tiles.get(i+1) - camy, 6, 6, orange);
                }
                else{
                    drawSprite(goal, tiles.get(i) - camx, tiles.get(i+1) - camy, false, false);
                }
            }

            for (int i = 0; i < points.size(); i += 3){
                if (points.get(i+2) == 0){
                    rect(points.get(i) - camx, points.get(i+1) - camy, 3, 3, red);
                }
                else{
                    rect(points.get(i) - camx, points.get(i+1) - camy, 3, 3, blue);
                }
            }

            if (playerdir2 == 0){
                drawSprite(car0, playerx, playery, false, false);
            }
            else if (playerdir2 == 22.5 || playerdir2 == -337.5){
                drawSprite(car22, playerx, playery, false, true);
            }
            else if (playerdir2 == 45 || playerdir2 == -315){
                drawSprite(car45, playerx, playery, false, true);
            }
            else if (playerdir2 == 67.5 || playerdir2 == -292.5){
                drawSprite(car67, playerx, playery, false, true);
            }
            else if (playerdir2 == 90 || playerdir2 == -270){
                drawSprite(car90, playerx, playery, false, true);
            }
            else if (playerdir2 == 112.5 || playerdir2 == -247.5){
                drawSprite(car67, playerx, playery, true, true);
            }
            else if (playerdir2 == 135 || playerdir2 == -225){
                drawSprite(car45, playerx, playery, true, true);
            }
            else if (playerdir2 == 157.5 || playerdir2 == -202.5){
                drawSprite(car22, playerx, playery, true, true);
            }
            else if (playerdir2 == 180 || playerdir2 == -180){
                drawSprite(car0, playerx, playery, true, true);
            }
            else if (playerdir2 == 202.5 || playerdir2 == -157.5){
                drawSprite(car22, playerx, playery, true, false);
            }
            else if (playerdir2 == 225 || playerdir2 == -135){
                drawSprite(car45, playerx, playery, true, false);
            }
            else if (playerdir2 == 247.5 || playerdir2 == -112.5){
                drawSprite(car67, playerx, playery, true, false);
            }
            else if (playerdir2 == 270 || playerdir2 == -90){
                drawSprite(car90, playerx, playery, true, false);
            }
            else if (playerdir2 == 292.5 || playerdir2 == -67.5){
                drawSprite(car67, playerx, playery, false, false);
            }
            else if (playerdir2 == 315 || playerdir2 == -45){
                drawSprite(car45, playerx, playery, false, false);
            }
            else if (playerdir2 == 337.5 || playerdir2 == -22.5){
                drawSprite(car22, playerx, playery, false, false);
            }
            
            System.out.println("Time: " + Math.ceil(timer));
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    System.out.print(pixels[i * width + j]);
                }
                System.out.println("\u001B[0m");
            }

            try{
                Thread.sleep(40);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new cargame();
    }

    static void drawSprite(int[][] sprite, float x, float y, boolean hflip, boolean vflip){
        for (float i = y; i < y + sprite.length; i++){
            for (float j = x; j < x + sprite[0].length; j++){
                if (!hflip && !vflip){
                    switch (sprite[Math.round(i-y)][Math.round(j-x)]){
                            case 1: setPixel(j, i, black);
                            break;
                            case 2: setPixel(j, i, red);
                            break;
                            case 3: setPixel(j, i, orange);
                            break;
                            case 4: setPixel(j, i, white);
                            break;
                    }
                }
                else if (hflip && !vflip){
                    switch (sprite[Math.round(i-y)][Math.round(Math.abs(j-(x+(sprite[0].length-1))))]){
                            case 1: setPixel(j, i, black);
                            break;
                            case 2: setPixel(j, i, red);
                            break;
                            case 3: setPixel(j, i, orange);
                            break;
                            case 4: setPixel(j, i, white);
                            break;
                    }
                }
                else if (!hflip && vflip){
                    switch (sprite[Math.round(Math.abs(i-(y+(sprite.length-1))))][Math.round(j-x)]){
                            case 1: setPixel(j, i, black);
                            break;
                            case 2: setPixel(j, i, red);
                            break;
                            case 3: setPixel(j, i, orange);
                            break;
                            case 4: setPixel(j, i, white);
                            break;
                    }
                }
                else if (hflip && vflip){
                    switch (sprite[Math.round(Math.abs(i-(y+(sprite.length-1))))][Math.round(Math.abs(j-(x+(sprite[0].length-1))))]){
                            case 1: setPixel(j, i, black);
                            break;
                            case 2: setPixel(j, i, red);
                            break;
                            case 3: setPixel(j, i, orange);
                            break;
                            case 4: setPixel(j, i, white);
                            break;
                    }
                }
            }
        }
    }

    static void rect(float x, float y, float w, float h, String color){
        for (float i = x; i < x + w; i++){
            for (float j = y; j < y + h; j++){
                setPixel(i, j, color);
            }
        }
    }

    static void setPixel(float x, float y, String color){
        if (x >= 0 && x <= width-1 && y <= 0 && y >= -(height-1)){
            pixels[Math.round(Math.abs(y)) * width + Math.round(x)] = color;
        }
    }

    static void addTile(float x, float y, float id){
        tiles.add(x);
        tiles.add(y);
        tiles.add(id);
    }

    static void addPoint(float x, float y, float on){
        points.add(x);
        points.add(y);
        points.add(on);
    }

    public void keyTyped(KeyEvent e){
        
    }
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
                case 37: leftkey = true;
                break;
                case 38: upkey = true;
                break;
                case 39: rightkey = true;
                break;
                case 40: downkey = true;
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
                case 37: leftkey = false;
                break;
                case 38: upkey = false;
                break;
                case 39: rightkey = false;
                break;
                case 40: downkey = false;
                break;
        }
    }
}