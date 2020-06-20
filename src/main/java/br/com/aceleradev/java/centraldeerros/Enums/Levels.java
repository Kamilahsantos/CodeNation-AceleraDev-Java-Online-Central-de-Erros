package br.com.aceleradev.java.centraldeerros.Enums;

public enum Levels {

  ERROR("ERROR"), WARNING("WARNING"), DEBUG("DEBUG");

  private String levels;

  Levels(String levels) {
    this.levels = levels;
  }

  public String getLevels() {
    return levels;
  }

  public static Levels find(String value) {
    for (Levels level : Levels.values()) {
      if(value.equalsIgnoreCase((level.levels)))
        return level;
    }
    return null;
  }

}
