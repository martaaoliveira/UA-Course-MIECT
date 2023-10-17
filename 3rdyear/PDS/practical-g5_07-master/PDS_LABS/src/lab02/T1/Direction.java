package lab02.T1;

/**
 * When used in a word, it indicates the word's direction.
 * When in a 2D array, indicates the direction of a word starting in this position, if any (NONE otherwise). This is used to meet the "no overlapping with subwords condition"
 * None is the default value
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, NONE;
}