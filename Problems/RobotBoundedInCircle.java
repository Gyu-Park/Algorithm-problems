/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:
 * The north direction is the positive direction of the y-axis.
 * The south direction is the negative direction of the y-axis.
 * The east direction is the positive direction of the x-axis.
 * The west direction is the negative direction of the x-axis.
 * 
 * The robot can receive one of three instructions:
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 * "R": turn 90 degrees to the right (i.e., clockwise direction).
 * 
 * The robot performs the instructions given in order, and repeats them forever.
 * 
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
package Problems;

public class RobotBoundedInCircle {
    public static boolean isRobotBounded(String instructions) {
        int[] location = {0, 0};
        char direction = 'n';
        int count = 0;
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'G') {
                location = goOneUnit(direction, location);
            } else if (instructions.charAt(i) == 'L') {
                direction = directionChange(direction, 'L');
            } else if (instructions.charAt(i) == 'R') {
                direction = directionChange(direction, 'R');
            }
        }
        if (direction != 'n')
            return true;
        return location[0] == 0 && location[1] == 0 ? true : false;
    }

    private static char directionChange(char direction, char instruction) {
        if ((direction == 'e' && instruction == 'L') ||  (direction == 'w' && instruction == 'R')) {
            return 'n';
        } else if ((direction == 'e' && instruction == 'R') || (direction == 'w' && instruction == 'L')) {
            return 's';
        } else if ((direction == 'n' && instruction == 'R') || (direction == 's' && instruction == 'L')) {
            return 'e';
        } else if ((direction == 'n' && instruction == 'L') || (direction == 's' && instruction == 'R')) {
            return 'w';
        }
        return 'z';
    }

    private static int[] goOneUnit(char direction, int[] location) {
        if (direction == 'n') {
            location[1]++;
        } else if (direction == 's') {
            location[1]--;
        } else if (direction == 'w') {
            location[0]--;
        } else if (direction == 'e') {
            location[0]++;
        }
        return location;
    }

    public boolean anotherIsRobotBounded(String ins) {
        // i represents direction.
        // d[0] = north, d[1] = east, d[2] = south, d[3] = west
        int x = 0, y = 0, i = 0, d[][] = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};
        for (int j = 0; j < ins.length(); ++j)
            if (ins.charAt(j) == 'R')
                // (i + 1) % 4 will turn right
                i = (i + 1) % 4;
            else if (ins.charAt(j) == 'L')
                // (i + 3) % 4 will turn left
                i = (i + 3) % 4;
            else {
                x += d[i][0]; y += d[i][1];
            }
        return x == 0 && y == 0 || i > 0;
    }

    public static void main(String[] args) {
        String instructions = "GGLLGGGGRR";
        System.out.println(isRobotBounded(instructions));
    }
}
