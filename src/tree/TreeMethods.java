/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Basian
 */
public class TreeMethods {
  
    
    private Direction dir;
    private int level;

    public TreeMethods(Direction dir, int level) {
        this.dir = dir;
        this.level = level;
    }

    public void drawingTree() {
        int width = level * 2 - 1;
        if (dir == Direction.UP) {

            StringBuilder line = new StringBuilder(width);
            line.append("*");

            for (int i = 0; i < level; i++) {

                drawLine(level + i, line);
                line.append("**");
            }
        } else if (dir == Direction.DOWN) {

            StringBuilder line = new StringBuilder(width);
            for (int i = 0; i < line.capacity(); i++) {
                line.append("*");
            }

            for (int i = 0; i < level; i++) {

                drawLine(width - i, line);
                line.delete(0, 2);
            }

        } else if (dir == Direction.LEFT) {

            StringBuilder line = new StringBuilder(level);

            for (int i = 0; i < width; i++) {
                if (i < level) {
                    line.append("*");
                    drawLine(level, line);
                } else {
                    line.delete(0, 1);
                    drawLine(level, line);
                }
            }
        } else if (dir == Direction.RIGHT) {

            StringBuilder line = new StringBuilder(level);

            for (int i = 0; i < width; i++) {
                if (i < level) {
                    line.append("*");
                    System.out.println(line);
                } else {
                    line.delete(0, 1);
                    System.out.println(line);
                }
            }
        }
    }

    private void drawLine(int format, StringBuilder line) {
        System.out.println(String.format("%" + String.valueOf(format) + "s", line));
    }

    
}
