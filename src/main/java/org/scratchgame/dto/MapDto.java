package org.scratchgame.dto;

public class MapDto {
  private final int row;
  private final int col;
  private final String[][] map;

  public MapDto(int row, int col, String[][] map) {
    this.row = row;
    this.col = col;
    this.map = map;
  }

  public MapDto(int row, int col) {
    this.row = row;
    this.col = col;
    this.map = new String[row][col];
  }

  public void setCell(int row, int col, String value) {
    map[row][col] = value;
  }

  public String getCell(int row, int col) {
    return map[row][col];
  }

  public void printMap() {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public String[][] getMap() {
    return map;
  }
}
