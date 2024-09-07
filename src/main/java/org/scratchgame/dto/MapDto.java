package org.scratchgame.dto;

public class MapDto {
  private int row;
  private int col;
  private String[][] map;

  public MapDto(int row, int col) {
    this.row = row;
    this.col = col;
    this.map = new String[row][col];
  }

  public MapDto() {
  }

  public void setCell(int row, int col, String value) {
    map[row][col] = value;
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

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public void setMap(String[][] map) {
    this.map = map;
  }
}
