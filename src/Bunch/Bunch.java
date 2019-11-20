package Bunch;

import PortableEntity.GameEntity;

import java.awt.*;
import java.util.ArrayList;

public abstract class Bunch {
    public abstract void add(GameEntity gameEntity);
    public abstract void delete(int index);
    public abstract void draw(Graphics g);
}
