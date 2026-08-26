package net.quedoom.francium.recipe;

public enum GlueMixerGlueType {
    NORMAL,
    VEGAN,
    ECHO,
    SUPER;

    public static GlueMixerGlueType fromInt(int value) {
        return switch (value) {
            default -> NORMAL;
            case 1 -> VEGAN;
            case 2 -> ECHO;
            case 3 -> SUPER;
        };
    }

    public static boolean test(GlueMixerGlueType type, GlueMixerInput input) {
        return type == input.type();
    }
}
