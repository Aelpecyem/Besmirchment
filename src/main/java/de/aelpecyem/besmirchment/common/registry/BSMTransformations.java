package de.aelpecyem.besmirchment.common.registry;

import moriyashiine.bewitchment.api.registry.Transformation;
import moriyashiine.bewitchment.common.registry.BWRegistries;

public class BSMTransformations {
    public static final Transformation WEREPYRE = new Transformation();
    public static void init(){
        Util.register(BWRegistries.TRANSFORMATIONS, "werepyre", WEREPYRE);
    }
}
