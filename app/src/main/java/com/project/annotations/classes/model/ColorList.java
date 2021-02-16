package com.project.annotations.classes.model;


import com.project.annotations.classes.model.enums.ColorsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorList {
    private static final List<Color> colorsList = new ArrayList<>(Arrays.asList(
            new Color(ColorsEnum.WHITE),
            new Color(ColorsEnum.BLUE),
            new Color(ColorsEnum.RED),
            new Color(ColorsEnum.GREEN),
            new Color(ColorsEnum.YELLOW),
            new Color(ColorsEnum.LILAC),
            new Color(ColorsEnum.GRAY),
            new Color(ColorsEnum.BROWN),
            new Color(ColorsEnum.PURPLE)));

    public final List<Color> getAllColors() {
        return new ArrayList<>(colorsList);
    }
}
