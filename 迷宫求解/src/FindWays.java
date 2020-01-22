import java.util.LinkedList;
import java.util.Queue;

public class FindWays {
    public int[][] map;
    private int[][] map2;
    private int mapX;
    private int mapY;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public FindWays(int[][] arr) {
        this.map = arr;
        mapX = map.length-1;
        mapY = map[0].length-1;
        startX = 1;
        startY = 1;
        endX = mapX-1;
        endY = mapY-1;
        map2 = new int[map.length][map[0].length];
        for(int i = 0;i < map.length;i++) {
            System.arraycopy(map[i], 0, map2[i], 0, map[i].length);
        }
    }

    private class Point {
        private int x;
        private int y;

        private Point parent;

        public Point(int x, int y, Point p) {
            this.x = x;
            this.y = y;
            this.parent = p;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point getParent() {
            return parent;
        }
    }

    Point result;
    public boolean findPath() {
        Point start = new Point(startX, startY, null);
        Queue<Point> queue = new LinkedList<>();
        map[startX][startY] = 1;
        queue.offer(start);

        while (queue.peek() != null) {
            Point p = queue.poll();
            int x = p.getX();
            int y = p.getY();
            if(x == endX && y == endY) {
                result = p;
                return true;
            }

            if (x - 1 >= 0 && map[x - 1][y] == 0) {
                queue.offer(new Point(x - 1 , y , p));
                map[x - 1][y] = 1;
            }
            if (y + 1 <= mapY && map[x][y + 1] == 0) {
                queue.offer(new Point(x , y + 1 , p));
                map[x][y + 1] = 1;
            }
            if (x + 1 <= mapX && map[x + 1][y] == 0) {
                queue.offer(new Point(x + 1 , y , p));
                map[x + 1][y] = 1;
            }
            if (y - 1 >= 0 && map[x][y - 1] == 0) {
                queue.offer(new Point(x, y - 1, p));
                map[x][y - 1] = 1;
            }
        }
        return false;
    }

    public int[][] mapResult() {
        Point p = result;
        while(p != null) {
            map2[p.getX()][p.getY()] = 2;
            p = p.getParent();
        }
        return map2;
    }
}
