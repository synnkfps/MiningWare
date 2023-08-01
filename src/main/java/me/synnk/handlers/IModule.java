package me.synnk.handlers;

import org.lwjgl.input.Keyboard;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**

 */

@Retention(RetentionPolicy.RUNTIME)
public @interface IModule {
    String name();
    String description() default "No description provided!";
    int key() default Keyboard.KEY_NONE;
}